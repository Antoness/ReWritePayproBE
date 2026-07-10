package com.payroll.modules.ter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MasterTerService {

    private final EntityManager entityManager;

    // ─── Dropdown filter (data yang sudah ada di DB) ────────────────────────
    @SuppressWarnings("unchecked")
    public Map<String, Object> getDropdowns() {
        Map<String, Object> result = new HashMap<>();
        List<String> years = new ArrayList<>();
        try {
            List<Object> yearResults = entityManager
                .createNativeQuery("SELECT DISTINCT tahun FROM rfter ORDER BY tahun DESC")
                .getResultList();
            for (Object y : yearResults) {
                if (y != null) years.add(y.toString());
            }
        } catch (Exception ignored) {}
        result.put("years", years);
        return result;
    }

    // ─── Dropdown upload (tahun sekarang s/d +5 tahun) ──────────────────────
    @SuppressWarnings("unchecked")
    public List<String> getUploadYears() {
        List<String> years = new ArrayList<>();
        try {
            String sql = "WITH RECURSIVE YearSequence AS (" +
                "SELECT CAST(EXTRACT(YEAR FROM CURRENT_DATE) AS INTEGER) AS year " +
                "UNION ALL " +
                "SELECT year + 1 FROM YearSequence WHERE year < EXTRACT(YEAR FROM CURRENT_DATE) + 5" +
                ") SELECT year FROM YearSequence ORDER BY year ASC";
            List<Object> results = entityManager.createNativeQuery(sql).getResultList();
            for (Object y : results) {
                if (y != null) years.add(y.toString());
            }
        } catch (Exception ignored) {}
        return years;
    }

    // ─── Update status tahun active ─────────────────────────────────────────
    @Transactional
    public Map<String, Object> updateStatusTahunActive(String year) {
        Map<String, Object> response = new HashMap<>();
        try {
            int tahun = Integer.parseInt(year);
            entityManager.createNativeQuery("UPDATE rfter SET status = 1 WHERE tahun = :tahun")
                .setParameter("tahun", tahun).executeUpdate();
            entityManager.createNativeQuery("UPDATE rfter SET status = 0 WHERE tahun != :tahun")
                .setParameter("tahun", tahun).executeUpdate();
            response.put("success", true);
            response.put("message", "Status Tahun " + year + " berhasil diaktifkan");
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Gagal update status: " + e.getMessage());
        }
        return response;
    }

    // ─── Upload Excel TER ────────────────────────────────────────────────────
    @Transactional
    @SuppressWarnings("unchecked")
    public Map<String, Object> processUploadTer(MultipartFile file, String year, String fullName) {
        Map<String, Object> response = new HashMap<>();
        try (InputStream is = file.getInputStream(); Workbook workbook = WorkbookFactory.create(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            List<Map<String, Object>> parsedRows = new ArrayList<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                // Baca raw string dari masing-masing cell
                String ter       = getCellStringValue(row.getCell(0));
                String nilaiMinStr = getCellStringValue(row.getCell(1));
                String nilaiMaxStr = getCellStringValue(row.getCell(2));
                String persenStr   = getCellStringValue(row.getCell(3));

                // Skip baris benar-benar kosong
                if (ter.isEmpty() && nilaiMinStr.isEmpty() && nilaiMaxStr.isEmpty() && persenStr.isEmpty()) continue;

                // Parse numerik untuk cek duplikat di DB
                double nilaiMin = nilaiMinStr.isEmpty() ? 0 : Double.parseDouble(nilaiMinStr.replaceAll("[^0-9.]", ""));
                double nilaiMax = nilaiMaxStr.isEmpty() ? 0 : Double.parseDouble(nilaiMaxStr.replaceAll("[^0-9.]", ""));

                // Validasi: duplicate + empty field (sesuai query yang diminta)
                String validationSql =
                    "SELECT * FROM (" +
                    "  SELECT CONCAT('ter ', ter, ' Nilai Mix ', nilai_min, ' Nilai Max ', nilai_max, ' Sudah Ada Mohon Perbaiki Data Upload') AS validasi" +
                    "  FROM rfter WHERE ter = ?1 AND nilai_min = ?2 AND nilai_max = ?3 AND tahun = ?4" +
                    "  UNION" +
                    "  SELECT CASE WHEN CAST(?5 AS VARCHAR) = '' THEN 'Field Ter Tidak Boleh Kosong' ELSE '' END AS validasi" +
                    "  UNION" +
                    "  SELECT CASE WHEN CAST(?6 AS VARCHAR) = '' THEN 'Field Nilai Min Tidak Boleh Kosong' ELSE '' END AS validasi" +
                    "  UNION" +
                    "  SELECT CASE WHEN CAST(?7 AS VARCHAR) = '' THEN 'Field Nilai Max Tidak Boleh Kosong' ELSE '' END AS validasi" +
                    "  UNION" +
                    "  SELECT CASE WHEN CAST(?8 AS VARCHAR) = '' THEN 'Field Persen Tidak Boleh Kosong' ELSE '' END AS validasi" +
                    ") AS val_query WHERE validasi != '' LIMIT 1";

                Query valQuery = entityManager.createNativeQuery(validationSql);
                valQuery.setParameter(1, ter);
                valQuery.setParameter(2, nilaiMin);
                valQuery.setParameter(3, nilaiMax);
                valQuery.setParameter(4, Integer.parseInt(year));
                valQuery.setParameter(5, ter);
                valQuery.setParameter(6, nilaiMinStr);
                valQuery.setParameter(7, nilaiMaxStr);
                valQuery.setParameter(8, persenStr);

                List<?> valResult = valQuery.getResultList();
                if (!valResult.isEmpty()) {
                    // Validasi gagal — karena @Transactional, semua insert sebelumnya auto-rollback
                    response.put("success", false);
                    response.put("message", valResult.get(0).toString());
                    return response;
                }

                double persen = persenStr.isEmpty() ? 0 : Double.parseDouble(persenStr.replaceAll("[^0-9.]", ""));
                Map<String, Object> dataRow = new HashMap<>();
                dataRow.put("ter", ter);
                dataRow.put("nilaiMin", nilaiMin);
                dataRow.put("nilaiMax", nilaiMax);
                dataRow.put("persen", persen);
                parsedRows.add(dataRow);
            }

            if (parsedRows.isEmpty()) {
                response.put("success", false);
                response.put("message", "File Excel kosong atau format tidak sesuai (ter/nilai_min/nilai_max/persen tidak ditemukan).");
                return response;
            }

            // Insert semua baris yang sudah lolos validasi
            String insertSql =
                "INSERT INTO rfter (ter, nilai_min, nilai_max, persen, tahun, proccess_id, created_by, created_date) " +
                "VALUES (?1, ?2, ?3, ?4, ?5, 1, ?6, NOW())";

            for (Map<String, Object> dataRow : parsedRows) {
                Query insertQuery = entityManager.createNativeQuery(insertSql);
                insertQuery.setParameter(1, dataRow.get("ter"));
                insertQuery.setParameter(2, dataRow.get("nilaiMin"));
                insertQuery.setParameter(3, dataRow.get("nilaiMax"));
                insertQuery.setParameter(4, dataRow.get("persen"));
                insertQuery.setParameter(5, Integer.parseInt(year));
                insertQuery.setParameter(6, fullName);
                insertQuery.executeUpdate();
            }

            response.put("success", true);
            response.put("message", "Upload berhasil! " + parsedRows.size() + " data TER Tahun " + year + " berhasil ditambahkan.");
            return response;

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "System Error saat membaca Excel: " + e.getMessage());
            return response;
        }
    }

    // ─── Fetch list TER ──────────────────────────────────────────────────────
    @SuppressWarnings("unchecked")
    public Map<String, Object> findTerData(String year, int page, int size) {
        StringBuilder sql = new StringBuilder();
        StringBuilder countSql = new StringBuilder();

        sql.append(" SELECT ")
           .append("   ter AS Ter, ")
           .append("   CASE WHEN nilai_min = 0 THEN '-' ELSE TO_CHAR(nilai_min, 'FM999,999,999,999') END AS \"Nilai Min\", ")
           .append("   CASE WHEN nilai_max = 0 THEN '-' ELSE TO_CHAR(nilai_max, 'FM999,999,999,999') END AS \"Nilai Max\", ")
           .append("   CONCAT(TO_CHAR(persen * 100, 'FM999.00'), ' %') AS \"Persen\", ")
           .append("   tahun AS Tahun, ")
           .append("   CASE WHEN status = 1 THEN 'ACTIVE' ELSE 'TIDAK ACTIVE' END AS Status ")
           .append(" FROM rfter ")
           .append(" WHERE 1=1 ");

        countSql.append(" SELECT COUNT(*) FROM rfter WHERE 1=1 ");

        if (year != null && !year.isEmpty()) {
            sql.append(" AND tahun = :year ");
            countSql.append(" AND tahun = :year ");
        }

        sql.append(" ORDER BY ter ");
        sql.append(" LIMIT :limit OFFSET :offset ");

        Query query = entityManager.createNativeQuery(sql.toString());
        Query countQuery = entityManager.createNativeQuery(countSql.toString());

        if (year != null && !year.isEmpty()) {
            query.setParameter("year", Integer.parseInt(year));
            countQuery.setParameter("year", Integer.parseInt(year));
        }

        query.setParameter("limit", size);
        query.setParameter("offset", page * size);

        List<Object[]> results = query.getResultList();
        long totalElements = ((Number) countQuery.getSingleResult()).longValue();

        List<Map<String, Object>> content = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("Ter", row[0]);
            map.put("NilaiMin", row[1]);
            map.put("NilaiMax", row[2]);
            map.put("Persen", row[3]);
            map.put("Tahun", row[4]);
            map.put("Status", row[5]);
            content.add(map);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("content", content);
        response.put("totalElements", totalElements);
        response.put("totalPages", (int) Math.ceil((double) totalElements / size));
        return response;
    }

    // ─── Helper: baca cell sebagai String ───────────────────────────────────
    private String getCellStringValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> {
                double d = cell.getNumericCellValue();
                long l = (long) d;
                yield l == d ? String.valueOf(l) : String.valueOf(d);
            }
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }
}
