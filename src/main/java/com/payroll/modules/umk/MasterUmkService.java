package com.payroll.modules.umk;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigInteger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class MasterUmkService {

    private final MasterUmkRepository masterUmkRepository;
    private final EntityManager entityManager;

    public Map<String, List<String>> getDropdownData() {
        Map<String, List<String>> data = new HashMap<>();
        data.put("branches", masterUmkRepository.findUniqueAreas());
        data.put("statuses", masterUmkRepository.findUniqueStatuses());
        data.put("years", masterUmkRepository.findUniqueYears());
        return data;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> findUmkData(String position, String fullName, String search, String branch, String status, String year, int page, int size) {
        StringBuilder sql = new StringBuilder();
        StringBuilder countSql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        String pos = position != null ? position.toUpperCase().trim() : "";

        String filter = "";
        StringBuilder filterBuilder = new StringBuilder();
        appendFilters(filterBuilder, params, search, branch, status, year);
        filter = filterBuilder.toString();

        if (pos.equals("SPV") || pos.equals("SUPERVISOR")) {
            sql.append("WITH a AS ( ");
            sql.append("  SELECT a.id, a.approval, a.area AS Branch, a.tahun as Tahun, ");
            sql.append("  CASE WHEN a.nominal IS NULL OR a.nominal = '' OR a.nominal = '0' THEN '-' ELSE TO_CHAR(CAST(a.nominal AS NUMERIC), 'FM999,999,999,999') END AS Nominal, ");
            sql.append("  b.leader AS leader, ");
            sql.append("  (SELECT STRING_AGG(nama_upliner, ',') FROM master_upliner WHERE nik = b.nik GROUP BY nik) AS upliner, ");
            sql.append("  CONCAT('%', :fullName, '%') as fullname_pattern ");
            sql.append("  FROM master_umk a ");
            sql.append("  LEFT JOIN users b ON a.request_by = b.nik ");
            sql.append("  WHERE 1=1 ");
            sql.append(filter);
            sql.append("  GROUP BY a.id, a.area, a.nominal, a.tahun, a.approval, a.request_by, b.leader, b.nik ");
            sql.append(") ");
            sql.append("SELECT id, approval, Branch, Tahun, Nominal, ");
            sql.append("CASE ");
            sql.append("  WHEN approval = 'REQUEST' AND (leader = :fullName OR upliner LIKE fullname_pattern) ");
            sql.append("  THEN approval ");
            sql.append("  WHEN approval = 'APPROVED' THEN approval ");
            sql.append("  ELSE '' ");
            sql.append("END AS Status FROM a");
            params.put("fullName", fullName);

            countSql.append("SELECT COUNT(DISTINCT a.id) FROM master_umk a LEFT JOIN users b ON a.request_by = b.nik WHERE 1=1 ").append(filter);

        } else if (pos.equals("MANAJER") || pos.equals("MANAGER")) {
            sql.append("SELECT a.id, a.approval, a.area AS Branch, a.tahun as Tahun, ");
            sql.append("CASE WHEN a.nominal IS NULL OR a.nominal = '' OR a.nominal = '0' THEN '-' ELSE TO_CHAR(CAST(a.nominal AS NUMERIC), 'FM999,999,999,999') END AS Nominal, ");
            sql.append("CASE ");
            sql.append("  WHEN a.approval = 'REQUEST' THEN a.approval ");
            sql.append("  WHEN a.approval = 'APPROVED' THEN a.approval ");
            sql.append("  ELSE '' ");
            sql.append("END AS Status ");
            sql.append("FROM master_umk a ");
            sql.append("LEFT JOIN users b ON a.request_by = b.nik ");
            sql.append("WHERE 1=1 ");
            sql.append(filter);

            countSql.append("SELECT COUNT(*) FROM master_umk a LEFT JOIN users b ON a.request_by = b.nik WHERE 1=1 ").append(filter);

        } else { // Default to STAFF
            sql.append("SELECT a.id, a.approval, a.area AS Branch, a.tahun as Tahun, ");
            sql.append("CASE WHEN a.nominal IS NULL OR a.nominal = '' OR a.nominal = '0' THEN '-' ELSE TO_CHAR(CAST(a.nominal AS NUMERIC), 'FM999,999,999,999') END AS Nominal, ");
            sql.append("CASE ");
            sql.append("  WHEN a.approval = 'REQUEST' AND b.full_name = :fullName ");
            sql.append("  THEN a.approval ");
            sql.append("  WHEN a.approval = 'APPROVED' AND b.full_name = :fullName ");
            sql.append("  THEN a.approval ");
            sql.append("  ELSE '' ");
            sql.append("END AS Status ");
            sql.append("FROM master_umk a ");
            sql.append("LEFT JOIN users b ON a.request_by = b.nik ");
            sql.append("WHERE 1=1 ");
            sql.append(filter);
            params.put("fullName", fullName);

            countSql.append("SELECT COUNT(*) FROM master_umk a LEFT JOIN users b ON a.request_by = b.nik WHERE 1=1 ").append(filter);
        }

        sql.append(" LIMIT :limit OFFSET :offset ");
        params.put("limit", size);
        params.put("offset", page * size);

        Query query = entityManager.createNativeQuery(sql.toString());
        Query countQuery = entityManager.createNativeQuery(countSql.toString());
        
        params.forEach((k, v) -> {
            if (sql.toString().contains(":" + k)) query.setParameter(k, v);
            if (countSql.toString().contains(":" + k)) countQuery.setParameter(k, v);
        });

        List<Object[]> results = query.getResultList();
        long totalElements = ((Number) countQuery.getSingleResult()).longValue();

        List<Map<String, Object>> content = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", row[0]);
            map.put("approval", row[1]);
            map.put("Branch", row[2]);
            map.put("Tahun", row[3]);
            map.put("Nominal", row[4]);
            map.put("Status", row[5]);
            content.add(map);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("content", content);
        response.put("totalElements", totalElements);
        response.put("totalPages", (int) Math.ceil((double) totalElements / size));
        
        return response;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> findUmkHistory(String search, int page, int size) {
        StringBuilder sql = new StringBuilder();
        StringBuilder countSql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        String baseQuery = " FROM audit_logs WHERE entity_name = 'UMK' ";
        
        // Smart Search: Remove commas if search looks like a formatted number
        String cleanSearch = search != null ? search.replace(",", "") : "";

        String filter = "";
        if (search != null && !search.isEmpty()) {
            filter = " AND (new_values->>'area' ILIKE :search OR new_values->>'nominal' ILIKE :cleanSearch OR new_values->>'code_type' ILIKE :search OR action_type ILIKE :search OR created_by ILIKE :search) ";
            params.put("search", "%" + search + "%");
            params.put("cleanSearch", "%" + cleanSearch + "%");
        }

        sql.append("SELECT log_id AS id, new_values->>'area' AS Branch, new_values->>'tahun' as Tahun, ");
        sql.append("CASE WHEN new_values->>'nominal' IS NULL OR new_values->>'nominal' = '' OR new_values->>'nominal' = '0' THEN '-' ELSE TO_CHAR(CAST(new_values->>'nominal' AS NUMERIC), 'FM999,999,999,999') END AS Nominal, ");
        sql.append("CASE WHEN new_values->>'nominal_update' IS NULL OR new_values->>'nominal_update' = '' OR new_values->>'nominal_update' = '0' THEN '-' ELSE TO_CHAR(CAST(new_values->>'nominal_update' AS NUMERIC), 'FM999,999,999,999') END AS nominalUpdate, ");
        sql.append("created_at AS createdDate, ");
        sql.append("created_by AS createdBy, ");
        sql.append("action_type AS Approval ");
        sql.append(baseQuery).append(filter);
        sql.append(" ORDER BY created_at DESC ");
        sql.append(" LIMIT :limit OFFSET :offset ");

        countSql.append("SELECT COUNT(*) ").append(baseQuery).append(filter);

        params.put("limit", size);
        params.put("offset", page * size);

        Query query = entityManager.createNativeQuery(sql.toString());
        Query countQuery = entityManager.createNativeQuery(countSql.toString());
        
        params.forEach((k, v) -> {
            if (sql.toString().contains(":" + k)) query.setParameter(k, v);
            if (countSql.toString().contains(":" + k)) countQuery.setParameter(k, v);
        });

        List<Object[]> results = query.getResultList();
        long totalElements = ((Number) countQuery.getSingleResult()).longValue();

        List<Map<String, Object>> content = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", row[0]);
            map.put("branch", row[1]);
            map.put("tahun", row[2]);
            map.put("nominal", row[3]);
            map.put("nominalUpdate", row[4]);
            map.put("createdDate", row[5]);
            map.put("createdBy", row[6]);
            map.put("approval", row[7]);
            content.add(map);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("content", content);
        response.put("totalElements", totalElements);
        response.put("totalPages", (int) Math.ceil((double) totalElements / size));
        
        return response;
    }

    @Transactional
    public Map<String, Object> saveUmk(Map<String, Object> data, String fullName) {
        String branch = data.get("branch") != null ? data.get("branch").toString() : "";
        Object nominalObj = data.get("nominal");
        Object tahunObj = data.get("tahun");
        
        Map<String, Object> response = new HashMap<>();

        // 1. Validasi Branch Kosong
        if (branch.trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "[Branch] can't be empty!");
            return response;
        }

        // 2. Validasi Nominal Kosong / 0
        Long nominal = 0L;
        try {
            if (nominalObj != null) {
                String cleanNom = nominalObj.toString().replace(".", "").replace(",", "").trim();
                if (!cleanNom.isEmpty()) {
                    nominal = Long.parseLong(cleanNom);
                }
            }
        } catch (Exception e) {
            nominal = 0L;
        }

        if (nominal == 0) {
            response.put("success", false);
            response.put("message", "[Nominal] can't be empty!");
            return response;
        }

        // 3. Ambil Tahun dengan aman
        Integer tahun = 0;
        try {
            if (tahunObj != null) {
                tahun = Integer.parseInt(tahunObj.toString());
            } else {
                tahun = java.time.Year.now().getValue();
            }
        } catch (Exception e) {
            tahun = java.time.Year.now().getValue();
        }

        // 3. Cek Duplikasi Branch
        String checkSql = "SELECT COUNT(*) FROM master_umk WHERE area = UPPER(:branch) AND tahun = :tahun";
        Query checkQuery = entityManager.createNativeQuery(checkSql);
        checkQuery.setParameter("branch", branch);
        checkQuery.setParameter("tahun", String.valueOf(tahun));
        long count = ((Number) checkQuery.getSingleResult()).longValue();
        if (count > 0) {
            response.put("success", false);
            response.put("message", "Branch already exist");
            return response;
        }

        // 4. Insert Master UMK
        String insertMasterSql = "INSERT INTO master_umk (area, nominal, code_type, tahun, created_date, created_by, approval) " +
                                "VALUES (UPPER(:area), :nominal, '1', :tahun, CURRENT_TIMESTAMP, :createdBy, 'ADD') RETURNING id";
        Query insertMasterQuery = entityManager.createNativeQuery(insertMasterSql);
        insertMasterQuery.setParameter("area", branch);
        insertMasterQuery.setParameter("nominal", nominal);
        insertMasterQuery.setParameter("tahun", String.valueOf(tahun));
        insertMasterQuery.setParameter("createdBy", fullName);
        
        // Execute and get the generated ID
        Number lastId = (Number) insertMasterQuery.getSingleResult();

        // 5. Insert Audit Log
        String insertHistorySql = "INSERT INTO audit_logs (entity_name, entity_id, action_type, new_values, created_by, created_at) " +
                                 "VALUES ('UMK', :idParent, 'ADD', CAST(:newValues AS JSONB), :createdBy, CURRENT_TIMESTAMP)";
        Query insertHistoryQuery = entityManager.createNativeQuery(insertHistorySql);
        
        String newValuesJson = String.format("{\"area\": \"%s\", \"nominal\": \"%d\", \"tahun\": \"%d\", \"code_type\": \"1\"}", branch, nominal, tahun);
        
        insertHistoryQuery.setParameter("idParent", String.valueOf(lastId.longValue()));
        insertHistoryQuery.setParameter("newValues", newValuesJson);
        insertHistoryQuery.setParameter("createdBy", fullName);
        insertHistoryQuery.executeUpdate();

        response.put("success", true);
        response.put("message", "Data saved successfully");
        return response;
    }

    @Transactional
    public Map<String, Object> updateUmk(Map<String, Object> data, String fullName, String nik) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (data.get("id") == null || data.get("action") == null) {
                response.put("success", false);
                response.put("message", "Invalid update request: ID or Action missing.");
                return response;
            }

            Long id = Long.parseLong(data.get("id").toString());
            String action = (String) data.get("action"); // APPROVE, REJECT, REQUEST, UPDATE
            Object nominalRequestObj = data.get("nominalRequest");
            
            Long nominalRequest = 0L;
            if (nominalRequestObj != null) {
                String cleanNom = nominalRequestObj.toString().replace(".", "").replace(",", "").trim();
                if (!cleanNom.isEmpty()) {
                    nominalRequest = Long.parseLong(cleanNom);
                }
            }

            // Manual mapping from Object[] to Map if needed, but let's use direct query for clarity
            Object[] row = (Object[]) entityManager.createNativeQuery(
                "SELECT area, nominal, tahun, code_type FROM master_umk WHERE id = :id")
                .setParameter("id", id)
                .getSingleResult();
            
            String area = row[0] != null ? (String) row[0] : "";
            Long oldNominal = row[1] != null ? Long.parseLong(row[1].toString()) : 0L;
            Integer tahun = row[2] != null ? Integer.parseInt(row[2].toString()) : java.time.Year.now().getValue();
            String codeType = row[3] != null ? (String) row[3] : "1";

            String reqBranch = data.get("branch") != null ? data.get("branch").toString() : area;
            Integer reqTahun = data.get("tahun") != null ? Integer.parseInt(data.get("tahun").toString()) : tahun;

            // Base SQL: Always update area, tahun, update_date, and update_by for all actions
            String updateMasterSql = "UPDATE master_umk SET area = UPPER(:area), tahun = :tahun, update_date = CURRENT_TIMESTAMP, update_by = :user";
            String approvalStatus = "";

            if ("APPROVE".equals(action)) {
                updateMasterSql += ", nominal = :nomReq, nominal_request = :nomReq, approval = 'APPROVED', request_by = :nik WHERE id = :id";
                approvalStatus = "APPROVED";
            } else if ("REJECT".equals(action)) {
                updateMasterSql += ", nominal_request = 0, approval = 'REJECTED' WHERE id = :id";
                approvalStatus = "REJECT"; // User requested 'REJECT' for history
                nominalRequest = 0L; // Set nominal update to 0 in history
            } else if ("REQUEST".equals(action)) {
                updateMasterSql += ", nominal_request = :nomReq, approval = 'REQUEST', request_by = :nik WHERE id = :id";
                approvalStatus = "REQUEST";
            } else if ("UPDATE".equals(action)) {
                updateMasterSql += ", nominal = :nomReq, nominal_request = :nomReq WHERE id = :id";
                approvalStatus = "UPDATE";
            }

            Query q = entityManager.createNativeQuery(updateMasterSql);
            q.setParameter("id", id);
            q.setParameter("user", fullName);
            q.setParameter("area", reqBranch);
            q.setParameter("tahun", String.valueOf(reqTahun));
            if (updateMasterSql.contains(":nomReq")) q.setParameter("nomReq", nominalRequest);
            if (updateMasterSql.contains(":nik")) q.setParameter("nik", nik);
            q.executeUpdate();

            // Update local variables for history log so it reflects the newly saved changes
            area = reqBranch;
            tahun = reqTahun;

            // Insert Audit Log
            String histSql = "INSERT INTO audit_logs (entity_name, entity_id, action_type, old_values, new_values, created_by, created_at) " +
                             "VALUES ('UMK', :idParent, :approval, CAST(:oldValues AS JSONB), CAST(:newValues AS JSONB), :user, CURRENT_TIMESTAMP)";
            Query hq = entityManager.createNativeQuery(histSql);
            
            String oldValuesJson = String.format("{\"area\": \"%s\", \"nominal\": \"%d\", \"tahun\": \"%d\", \"code_type\": \"%s\"}", area, oldNominal, tahun, codeType);
            String newValuesJson = String.format("{\"area\": \"%s\", \"nominal\": \"%d\", \"nominal_update\": \"%d\", \"tahun\": \"%d\", \"code_type\": \"%s\"}", area, oldNominal, nominalRequest, tahun, codeType);
            
            hq.setParameter("idParent", String.valueOf(id));
            hq.setParameter("oldValues", oldValuesJson);
            hq.setParameter("newValues", newValuesJson);
            hq.setParameter("user", fullName);
            hq.setParameter("approval", approvalStatus);
            hq.executeUpdate();

            response.put("success", true);
            response.put("message", "Data updated successfully");
            return response;
            
        } catch (Exception e) {
            e.printStackTrace(); // Log error to console
            response.put("success", false);
            response.put("message", "System Error: " + e.getMessage());
            return response;
        }
    }

    @Transactional
    public Map<String, Object> processUpload(MultipartFile file, String year, String fullName) {
        Map<String, Object> response = new HashMap<>();
        try (InputStream is = file.getInputStream(); Workbook workbook = WorkbookFactory.create(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            
            List<Map<String, Object>> parsedData = new ArrayList<>();
            
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                
                Cell areaCell = row.getCell(0);
                Cell nominalCell = row.getCell(1);
                
                if (areaCell == null || nominalCell == null) continue;
                
                String area = "";
                if (areaCell.getCellType() == CellType.STRING) {
                    area = areaCell.getStringCellValue().trim();
                } else if (areaCell.getCellType() == CellType.NUMERIC) {
                    area = String.valueOf((int) areaCell.getNumericCellValue());
                }
                
                String rawNominal = "";
                if (nominalCell.getCellType() == CellType.NUMERIC) {
                    rawNominal = String.valueOf((long) nominalCell.getNumericCellValue());
                } else if (nominalCell.getCellType() == CellType.STRING) {
                    rawNominal = nominalCell.getStringCellValue();
                }
                
                if (area.isEmpty() || rawNominal.isEmpty()) continue;
                
                String cleanNom = rawNominal.replaceAll("[^0-9]", "");
                if (cleanNom.isEmpty()) continue;
                long nominal = Long.parseLong(cleanNom);
                
                // Validasi data sudah ada
                String checkSql = "SELECT CONCAT('Branch ', area , ' Tahun ', tahun , ' Sudah ada, Mohon Perbaiki Data Upload .. !! ') as validasi " +
                                  "FROM master_umk WHERE area = :area AND tahun = :tahun";
                Query checkQuery = entityManager.createNativeQuery(checkSql);
                checkQuery.setParameter("area", area);
                checkQuery.setParameter("tahun", year);
                
                List<?> checkResult = checkQuery.getResultList();
                if (!checkResult.isEmpty()) {
                    response.put("success", false);
                    response.put("message", checkResult.get(0).toString());
                    return response; // Langsung stop upload
                }
                
                Map<String, Object> dataRow = new HashMap<>();
                dataRow.put("area", area);
                dataRow.put("nominal", nominal);
                parsedData.add(dataRow);
            }
            
            if (parsedData.isEmpty()) {
                response.put("success", false);
                response.put("message", "File Excel kosong atau format tidak sesuai (Area/Nominal tidak ditemukan).");
                return response;
            }
            
            // Insert langsung ke master_umk dan catat audit log
            String insertMainSql = "INSERT INTO master_umk (area, tahun, nominal, code_type, approval, created_date, created_by) " +
                                   "VALUES (:area, :tahun, :nominal, '1', 'ADD', CURRENT_TIMESTAMP, :createdBy) RETURNING id";
                                   
            String insertHistorySql = "INSERT INTO audit_logs (entity_name, entity_id, action_type, new_values, created_by, created_at) " +
                                      "VALUES ('UMK', :idParent, 'ADD', CAST(:newValues AS JSONB), :createdBy, CURRENT_TIMESTAMP)";

            for (Map<String, Object> dataRow : parsedData) {
                // Insert data utama
                Query insertMainQuery = entityManager.createNativeQuery(insertMainSql);
                insertMainQuery.setParameter("area", dataRow.get("area"));
                insertMainQuery.setParameter("tahun", year);
                insertMainQuery.setParameter("nominal", dataRow.get("nominal"));
                insertMainQuery.setParameter("createdBy", fullName);
                
                Number lastId = (Number) insertMainQuery.getSingleResult();
                
                // Tambahkan audit log
                Query insertHistoryQuery = entityManager.createNativeQuery(insertHistorySql);
                String newValuesJson = String.format("{\"area\": \"%s\", \"nominal\": \"%s\", \"tahun\": \"%s\", \"code_type\": \"1\"}", 
                        dataRow.get("area"), dataRow.get("nominal"), year);
                
                insertHistoryQuery.setParameter("idParent", String.valueOf(lastId.longValue()));
                insertHistoryQuery.setParameter("newValues", newValuesJson);
                insertHistoryQuery.setParameter("createdBy", fullName);
                insertHistoryQuery.executeUpdate();
            }
            
            response.put("success", true);
            response.put("message", "Upload berhasil!");
            return response;
            
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "System Error saat membaca Excel: " + e.getMessage());
            return response;
        }
    }

    private void appendFilters(StringBuilder sql, Map<String, Object> params, String search, String branch, String status, String year) {
        if (search != null && !search.isEmpty()) {
            sql.append(" AND (a.area ILIKE :search OR a.approval ILIKE :search OR CAST(a.nominal AS VARCHAR) ILIKE :search) ");
            params.put("search", "%" + search + "%");
        }
        
        if (branch != null && !branch.isEmpty()) {
            sql.append(" AND a.area = :branch ");
            params.put("branch", branch);
        }
        
        if (status != null && !status.isEmpty()) {
            sql.append(" AND a.approval = :status ");
            params.put("status", status);
        }
        
        if (year != null && !year.isEmpty()) {
            sql.append(" AND a.tahun = :year ");
            params.put("year", year);
        } else {
            sql.append(" AND a.tahun = TO_CHAR(CURRENT_DATE, 'YYYY') ");
        }
    }
}
