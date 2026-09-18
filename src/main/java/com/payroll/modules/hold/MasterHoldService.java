package com.payroll.modules.hold;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MasterHoldService {

    private final EntityManager entityManager;
    private final MasterHoldRepository holdRepository;
    private final MasterHoldHistoryRepository historyRepository;

    private final Path uploadDir = Paths.get("uploads", "hold");

    @PostConstruct
    public void init() {
        try {
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
        } catch (IOException e) {
            System.err.println("Could not initialize hold upload folder: " + e.getMessage());
        }

        seedInitialData();
    }

    public void seedInitialData() {
        if (holdRepository.count() == 0) {
            MasterHold h1 = MasterHold.builder()
                    .companyId(1L)
                    .idPayroll(101L)
                    .nik("D8210663")
                    .nama("POPI ANGRAINI")
                    .monthPayroll("12")
                    .yearPayroll("2026")
                    .periodePenggajian("December 2026")
                    .department("Finance")
                    .division("Tax, Accounting & Biz Plan")
                    .unitName("Accounting")
                    .statusKetenagakerjaan("PKWT")
                    .namaBank("BCA")
                    .cabangBank("KCP Thamrin")
                    .norek("7310291023")
                    .thpHold(12096913.0)
                    .keterangan("Karyawan tanpa data rekening awal")
                    .tanggalRelease("2026-07-23")
                    .statusApprovalRelease("Approved")
                    .fileUploadName("MoM Sistem Payroll - 08 April 2026.pdf")
                    .payrollDate("2026-12-25")
                    .position("Supervisor")
                    .branch("JAKARTA")
                    .createdBy("system")
                    .build();

            MasterHold h2 = MasterHold.builder()
                    .companyId(1L)
                    .idPayroll(102L)
                    .nik("D0000331")
                    .nama("Pegawai Dummy 331")
                    .monthPayroll("12")
                    .yearPayroll("2026")
                    .periodePenggajian("December 2026")
                    .department("Operations")
                    .division("Business Development")
                    .unitName("Laku Pandai")
                    .statusKetenagakerjaan("PKWT")
                    .namaBank("BCA")
                    .cabangBank("KCP Kebon Jeruk")
                    .norek("")
                    .thpHold(1637831.0)
                    .keterangan("Karyawan tanpa data rekening")
                    .tanggalRelease("")
                    .statusApprovalRelease("New")
                    .fileUploadName("")
                    .payrollDate("2026-12-25")
                    .position("Supervisor")
                    .branch("Bogor")
                    .createdBy("system")
                    .build();

            MasterHold h3 = MasterHold.builder()
                    .companyId(1L)
                    .idPayroll(103L)
                    .nik("D0000332")
                    .nama("Ahmad Fauzi")
                    .monthPayroll("12")
                    .yearPayroll("2026")
                    .periodePenggajian("December 2026")
                    .department("Technology")
                    .division("Business Development")
                    .unitName("Laku Pandai")
                    .statusKetenagakerjaan("PKWT")
                    .namaBank("BCA")
                    .cabangBank("KCP Kebon Jeruk")
                    .norek("4520163283")
                    .thpHold(9137831.0)
                    .keterangan("Perbaikan nomor rekening baru")
                    .tanggalRelease("2026-07-14")
                    .statusApprovalRelease("Pending Approval")
                    .fileUploadName("Buku_Tabungan_Ahmad.pdf")
                    .payrollDate("2026-12-25")
                    .position("Staff")
                    .branch("Bogor")
                    .createdBy("system")
                    .build();

            MasterHold h4 = MasterHold.builder()
                    .companyId(1L)
                    .idPayroll(104L)
                    .nik("D0000335")
                    .nama("Siti Nurhaliza")
                    .monthPayroll("12")
                    .yearPayroll("2026")
                    .periodePenggajian("December 2026")
                    .department("Human Capital")
                    .division("Tax, Accounting & Biz Plan")
                    .unitName("Tax")
                    .statusKetenagakerjaan("PKWT")
                    .namaBank("Mandiri")
                    .cabangBank("KC Sudirman")
                    .norek("1240008899123")
                    .thpHold(6750000.0)
                    .keterangan("Hold karena cuti tidak berbayar")
                    .tanggalRelease("")
                    .statusApprovalRelease("Rejected")
                    .fileUploadName("")
                    .payrollDate("2026-12-25")
                    .position("Senior Staff")
                    .branch("JAKARTA")
                    .createdBy("system")
                    .build();

            holdRepository.saveAll(List.of(h1, h2, h3, h4));
        }

        if (historyRepository.count() == 0) {
            String nowStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            MasterHoldHistory l1 = MasterHoldHistory.builder()
                    .companyId(1L)
                    .idHold(1L)
                    .nik("D8210663")
                    .name("POPI ANGRAINI")
                    .division("Tax, Accounting & Biz Plan")
                    .unitName("Accounting")
                    .employeeType("PKWT")
                    .position("Supervisor")
                    .branch("JAKARTA")
                    .thp(12096913.0)
                    .actionType("Approve")
                    .status("Approved")
                    .dilakukanOleh("SPV HRD")
                    .actionNote("Disetujui untuk release pembayaran")
                    .tanggal(nowStr)
                    .build();

            MasterHoldHistory l2 = MasterHoldHistory.builder()
                    .companyId(1L)
                    .idHold(3L)
                    .nik("D0000332")
                    .name("Ahmad Fauzi")
                    .division("Business Development")
                    .unitName("Laku Pandai")
                    .employeeType("PKWT")
                    .position("Staff")
                    .branch("Bogor")
                    .thp(9137831.0)
                    .actionType("Release")
                    .status("Pending Approval")
                    .dilakukanOleh("Staff HRD")
                    .actionNote("Upload Bukti Buku Tabungan")
                    .tanggal(nowStr)
                    .build();

            historyRepository.saveAll(List.of(l1, l2));
        }
    }

    public Map<String, Object> getFilterOptions(Long companyId) {
        Map<String, Object> options = new HashMap<>();
        options.put("divisions", holdRepository.findDistinctDivisions(companyId != null ? companyId : 1L));
        options.put("units", holdRepository.findDistinctUnits(companyId != null ? companyId : 1L));
        options.put("positions", holdRepository.findDistinctPositions(companyId != null ? companyId : 1L));
        options.put("branches", holdRepository.findDistinctBranches(companyId != null ? companyId : 1L));
        options.put("employeeTypes", holdRepository.findDistinctEmployeeTypes(companyId != null ? companyId : 1L));
        options.put("months", holdRepository.findDistinctMonths(companyId != null ? companyId : 1L));
        options.put("years", holdRepository.findDistinctYears(companyId != null ? companyId : 1L));
        options.put("approvalStatuses", List.of("New", "Pending Approval", "Approved", "Rejected"));
        return options;
    }

    @SuppressWarnings("unchecked")
    public Page<MasterHold> getHoldRecords(Long companyId, String search, String division, String unitName,
                                          String position, String branch, String employeeType, String status,
                                          String month, String year, int page, int size) {
        StringBuilder where = new StringBuilder(" WHERE h.companyId = :companyId ");
        Map<String, Object> params = new HashMap<>();
        params.put("companyId", companyId != null ? companyId : 1L);

        if (search != null && !search.trim().isEmpty()) {
            where.append(" AND (LOWER(h.nik) LIKE :search OR LOWER(h.nama) LIKE :search) ");
            params.put("search", "%" + search.trim().toLowerCase() + "%");
        }
        if (division != null && !division.trim().isEmpty()) {
            where.append(" AND h.division = :division ");
            params.put("division", division.trim());
        }
        if (unitName != null && !unitName.trim().isEmpty()) {
            where.append(" AND h.unitName = :unitName ");
            params.put("unitName", unitName.trim());
        }
        if (position != null && !position.trim().isEmpty()) {
            where.append(" AND h.position = :position ");
            params.put("position", position.trim());
        }
        if (branch != null && !branch.trim().isEmpty()) {
            where.append(" AND h.branch = :branch ");
            params.put("branch", branch.trim());
        }
        if (employeeType != null && !employeeType.trim().isEmpty()) {
            where.append(" AND LOWER(h.statusKetenagakerjaan) = :employeeType ");
            params.put("employeeType", employeeType.trim().toLowerCase());
        }
        if (status != null && !status.trim().isEmpty()) {
            where.append(" AND h.statusApprovalRelease = :status ");
            params.put("status", status.trim());
        }
        if (month != null && !month.trim().isEmpty()) {
            where.append(" AND (h.monthPayroll = :month OR h.periodePenggajian LIKE :monthLike) ");
            params.put("month", month.trim());
            params.put("monthLike", "%" + month.trim() + "%");
        }
        if (year != null && !year.trim().isEmpty()) {
            where.append(" AND (h.yearPayroll = :year OR h.periodePenggajian LIKE :yearLike) ");
            params.put("year", year.trim());
            params.put("yearLike", "%" + year.trim() + "%");
        }

        // Count Query
        Query countQuery = entityManager.createQuery("SELECT COUNT(h) FROM MasterHold h " + where.toString());
        params.forEach(countQuery::setParameter);
        long total = ((Number) countQuery.getSingleResult()).longValue();

        // Data Query
        Query dataQuery = entityManager.createQuery("SELECT h FROM MasterHold h " + where.toString() + " ORDER BY h.id DESC", MasterHold.class);
        params.forEach(dataQuery::setParameter);
        int p = Math.max(0, page - 1);
        dataQuery.setFirstResult(p * size);
        dataQuery.setMaxResults(size);

        List<MasterHold> content = dataQuery.getResultList();
        return new PageImpl<>(content, PageRequest.of(p, size), total);
    }

    public Map<String, Object> getKpiSummary(Long companyId) {
        Long cId = companyId != null ? companyId : 1L;
        Map<String, Object> summary = new HashMap<>();
        long totalHold = holdRepository.countByCompanyId(cId);
        long pendingRelease = holdRepository.countByCompanyIdAndStatusApprovalRelease(cId, "Pending Approval");
        long approved = holdRepository.countByCompanyIdAndStatusApprovalRelease(cId, "Approved");
        long rejected = holdRepository.countByCompanyIdAndStatusApprovalRelease(cId, "Rejected");
        Double totalThpHold = holdRepository.sumThpHoldByCompanyId(cId);

        summary.put("totalHoldCount", totalHold);
        summary.put("totalThpHold", totalThpHold != null ? totalThpHold : 0.0);
        summary.put("pendingReleaseCount", pendingRelease);
        summary.put("approvedCount", approved);
        summary.put("rejectedCount", rejected);
        return summary;
    }

    public Map<String, Object> getHoldDetail(Long companyId, Long id) {
        MasterHold hold = holdRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data hold tidak ditemukan dengan ID: " + id));
        if (companyId != null && !hold.getCompanyId().equals(companyId)) {
            throw new RuntimeException("Unauthorized access to company data");
        }

        List<MasterHoldHistory> histories = historyRepository.findByCompanyIdAndIdHoldOrderByCreatedAtDesc(hold.getCompanyId(), id);

        Map<String, Object> response = new HashMap<>();
        response.put("hold", hold);
        response.put("history", histories);
        return response;
    }

    @SuppressWarnings("unchecked")
    public Page<MasterHoldHistory> getHistoryLogs(Long companyId, String search, String division, String unitName,
                                                 String position, String branch, String employeeType, int page, int size) {
        StringBuilder where = new StringBuilder(" WHERE h.companyId = :companyId ");
        Map<String, Object> params = new HashMap<>();
        params.put("companyId", companyId != null ? companyId : 1L);

        if (search != null && !search.trim().isEmpty()) {
            where.append(" AND (LOWER(h.nik) LIKE :search OR LOWER(h.name) LIKE :search) ");
            params.put("search", "%" + search.trim().toLowerCase() + "%");
        }
        if (division != null && !division.trim().isEmpty()) {
            where.append(" AND h.division = :division ");
            params.put("division", division.trim());
        }
        if (unitName != null && !unitName.trim().isEmpty()) {
            where.append(" AND h.unitName = :unitName ");
            params.put("unitName", unitName.trim());
        }
        if (position != null && !position.trim().isEmpty()) {
            where.append(" AND h.position = :position ");
            params.put("position", position.trim());
        }
        if (branch != null && !branch.trim().isEmpty()) {
            where.append(" AND h.branch = :branch ");
            params.put("branch", branch.trim());
        }
        if (employeeType != null && !employeeType.trim().isEmpty()) {
            where.append(" AND LOWER(h.employeeType) = :employeeType ");
            params.put("employeeType", employeeType.trim().toLowerCase());
        }

        Query countQuery = entityManager.createQuery("SELECT COUNT(h) FROM MasterHoldHistory h " + where.toString());
        params.forEach(countQuery::setParameter);
        long total = ((Number) countQuery.getSingleResult()).longValue();

        Query dataQuery = entityManager.createQuery("SELECT h FROM MasterHoldHistory h " + where.toString() + " ORDER BY h.id DESC", MasterHoldHistory.class);
        params.forEach(dataQuery::setParameter);
        int p = Math.max(0, page - 1);
        dataQuery.setFirstResult(p * size);
        dataQuery.setMaxResults(size);

        List<MasterHoldHistory> content = dataQuery.getResultList();
        return new PageImpl<>(content, PageRequest.of(p, size), total);
    }

    @Transactional
    public void cancelHold(Long companyId, List<Long> ids, String reasonNote, String username) {
        Long cId = companyId != null ? companyId : 1L;
        List<MasterHold> list = holdRepository.findByCompanyIdAndIdIn(cId, ids);
        String nowStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String note = (reasonNote != null && !reasonNote.isBlank()) ? reasonNote : "Pembatalan status hold gaji";

        for (MasterHold h : list) {
            historyRepository.save(MasterHoldHistory.builder()
                    .companyId(cId)
                    .idHold(h.getId())
                    .nik(h.getNik())
                    .name(h.getNama())
                    .division(h.getDivision())
                    .unitName(h.getUnitName())
                    .employeeType(h.getStatusKetenagakerjaan())
                    .position(h.getPosition())
                    .branch(h.getBranch())
                    .thp(h.getThpHold())
                    .actionType("Cancel Hold")
                    .status("Cancelled")
                    .dilakukanOleh(username)
                    .actionNote(note)
                    .tanggal(nowStr)
                    .build());
        }
        holdRepository.deleteAll(list);
    }

    @Transactional
    public void approveHold(Long companyId, List<Long> ids, String username) {
        Long cId = companyId != null ? companyId : 1L;
        List<MasterHold> list = holdRepository.findByCompanyIdAndIdIn(cId, ids);
        String nowStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        for (MasterHold h : list) {
            h.setStatusApprovalRelease("Approved");
            holdRepository.save(h);

            historyRepository.save(MasterHoldHistory.builder()
                    .companyId(cId)
                    .idHold(h.getId())
                    .nik(h.getNik())
                    .name(h.getNama())
                    .division(h.getDivision())
                    .unitName(h.getUnitName())
                    .employeeType(h.getStatusKetenagakerjaan())
                    .position(h.getPosition())
                    .branch(h.getBranch())
                    .thp(h.getThpHold())
                    .actionType("Approve")
                    .status("Approved")
                    .dilakukanOleh(username)
                    .actionNote("Approval pelepasan hold gaji")
                    .tanggal(nowStr)
                    .build());
        }
    }

    @Transactional
    public void rejectHold(Long companyId, List<Long> ids, String reasonNote, String username) {
        Long cId = companyId != null ? companyId : 1L;
        List<MasterHold> list = holdRepository.findByCompanyIdAndIdIn(cId, ids);
        String nowStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String note = (reasonNote != null && !reasonNote.isBlank()) ? reasonNote : "Penolakan pelepasan hold gaji";

        for (MasterHold h : list) {
            h.setStatusApprovalRelease("Rejected");
            h.setTanggalRelease(null);
            holdRepository.save(h);

            historyRepository.save(MasterHoldHistory.builder()
                    .companyId(cId)
                    .idHold(h.getId())
                    .nik(h.getNik())
                    .name(h.getNama())
                    .division(h.getDivision())
                    .unitName(h.getUnitName())
                    .employeeType(h.getStatusKetenagakerjaan())
                    .position(h.getPosition())
                    .branch(h.getBranch())
                    .thp(h.getThpHold())
                    .actionType("Reject")
                    .status("Rejected")
                    .dilakukanOleh(username)
                    .actionNote(note)
                    .tanggal(nowStr)
                    .build());
        }
    }

    @Transactional
    public MasterHold updateRelease(Long companyId, Long id, String keterangan, String tanggalRelease,
                                   MultipartFile file, String username) {
        MasterHold h = holdRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data hold tidak ditemukan dengan ID: " + id));
        if (companyId != null && !h.getCompanyId().equals(companyId)) {
            throw new RuntimeException("Unauthorized access to company data");
        }

        h.setKeterangan(keterangan);
        h.setTanggalRelease(tanggalRelease);

        if (file != null && !file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            String ext = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                ext = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            String storedFileName = "hold_" + id + "_" + System.currentTimeMillis() + ext;
            try {
                Path targetLocation = this.uploadDir.resolve(storedFileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                h.setFileUploadName(originalFilename);
                h.setFileUrl("/api/v1/master-hold/files/" + storedFileName);
            } catch (IOException e) {
                throw new RuntimeException("Gagal menyimpan file lampiran bukti: " + e.getMessage());
            }
        }

        h.setStatusApprovalRelease("Pending Approval");
        h.setUploadedBy(username);
        h.setUploadedAt(LocalDateTime.now());
        MasterHold saved = holdRepository.save(h);

        String nowStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        historyRepository.save(MasterHoldHistory.builder()
                .companyId(h.getCompanyId())
                .idHold(h.getId())
                .nik(h.getNik())
                .name(h.getNama())
                .division(h.getDivision())
                .unitName(h.getUnitName())
                .employeeType(h.getStatusKetenagakerjaan())
                .position(h.getPosition())
                .branch(h.getBranch())
                .thp(h.getThpHold())
                .actionType("Release")
                .status("Pending Approval")
                .dilakukanOleh(username)
                .actionNote(keterangan != null && !keterangan.isBlank() ? keterangan : "Pengajuan release hold gaji")
                .tanggal(nowStr)
                .build());

        return saved;
    }

    public Resource loadProofFile(String filename) {
        try {
            Path filePath = this.uploadDir.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("File tidak ditemukan: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("File error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public byte[] exportExcel(Long companyId, String search, String division, String unitName,
                              String position, String branch, String employeeType, String status,
                              String month, String year) {
        StringBuilder where = new StringBuilder(" WHERE h.companyId = :companyId ");
        Map<String, Object> params = new HashMap<>();
        params.put("companyId", companyId != null ? companyId : 1L);

        if (search != null && !search.trim().isEmpty()) {
            where.append(" AND (LOWER(h.nik) LIKE :search OR LOWER(h.nama) LIKE :search) ");
            params.put("search", "%" + search.trim().toLowerCase() + "%");
        }
        if (division != null && !division.trim().isEmpty()) {
            where.append(" AND h.division = :division ");
            params.put("division", division.trim());
        }
        if (unitName != null && !unitName.trim().isEmpty()) {
            where.append(" AND h.unitName = :unitName ");
            params.put("unitName", unitName.trim());
        }
        if (position != null && !position.trim().isEmpty()) {
            where.append(" AND h.position = :position ");
            params.put("position", position.trim());
        }
        if (branch != null && !branch.trim().isEmpty()) {
            where.append(" AND h.branch = :branch ");
            params.put("branch", branch.trim());
        }
        if (employeeType != null && !employeeType.trim().isEmpty()) {
            where.append(" AND LOWER(h.statusKetenagakerjaan) = :employeeType ");
            params.put("employeeType", employeeType.trim().toLowerCase());
        }
        if (status != null && !status.trim().isEmpty()) {
            where.append(" AND h.statusApprovalRelease = :status ");
            params.put("status", status.trim());
        }
        if (month != null && !month.trim().isEmpty()) {
            where.append(" AND (h.monthPayroll = :month OR h.periodePenggajian LIKE :monthLike) ");
            params.put("month", month.trim());
            params.put("monthLike", "%" + month.trim() + "%");
        }
        if (year != null && !year.trim().isEmpty()) {
            where.append(" AND (h.yearPayroll = :year OR h.periodePenggajian LIKE :yearLike) ");
            params.put("year", year.trim());
            params.put("yearLike", "%" + year.trim() + "%");
        }

        Query dataQuery = entityManager.createQuery("SELECT h FROM MasterHold h " + where.toString() + " ORDER BY h.id DESC", MasterHold.class);
        params.forEach(dataQuery::setParameter);
        List<MasterHold> dataList = dataQuery.getResultList();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Master Hold");

            // Header Style
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // Currency Style
            CellStyle currencyStyle = workbook.createCellStyle();
            DataFormat df = workbook.createDataFormat();
            currencyStyle.setDataFormat(df.getFormat("#,##0"));

            String[] headers = {
                    "No", "NIK", "Nama Karyawan", "Periode Penggajian", "Divisi", "Unit Kerja",
                    "Status Ketenagakerjaan", "Nama Bank", "Cabang Bank", "Nomor Rekening",
                    "THP Hold", "Keterangan", "Tanggal Release", "Status Approval Release",
                    "File Upload", "Payroll Date"
            };

            Row headerRow = sheet.createRow(0);
            headerRow.setHeightInPoints(24);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            int rowIdx = 1;
            for (MasterHold item : dataList) {
                Row row = sheet.createRow(rowIdx);
                row.createCell(0).setCellValue(rowIdx);
                row.createCell(1).setCellValue(item.getNik() != null ? item.getNik() : "");
                row.createCell(2).setCellValue(item.getNama() != null ? item.getNama() : "");
                row.createCell(3).setCellValue(item.getPeriodePenggajian() != null ? item.getPeriodePenggajian() : "");
                row.createCell(4).setCellValue(item.getDivision() != null ? item.getDivision() : "");
                row.createCell(5).setCellValue(item.getUnitName() != null ? item.getUnitName() : "");
                row.createCell(6).setCellValue(item.getStatusKetenagakerjaan() != null ? item.getStatusKetenagakerjaan() : "");
                row.createCell(7).setCellValue(item.getNamaBank() != null ? item.getNamaBank() : "");
                row.createCell(8).setCellValue(item.getCabangBank() != null ? item.getCabangBank() : "");
                row.createCell(9).setCellValue(item.getNorek() != null ? item.getNorek() : "");

                Cell thpCell = row.createCell(10);
                thpCell.setCellValue(item.getThpHold() != null ? item.getThpHold() : 0.0);
                thpCell.setCellStyle(currencyStyle);

                row.createCell(11).setCellValue(item.getKeterangan() != null ? item.getKeterangan() : "");
                row.createCell(12).setCellValue(item.getTanggalRelease() != null ? item.getTanggalRelease() : "");
                row.createCell(13).setCellValue(item.getStatusApprovalRelease() != null ? item.getStatusApprovalRelease() : "");
                row.createCell(14).setCellValue(item.getFileUploadName() != null ? item.getFileUploadName() : "");
                row.createCell(15).setCellValue(item.getPayrollDate() != null ? item.getPayrollDate() : "");

                rowIdx++;
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error generating Excel: " + e.getMessage());
        }
    }
}
