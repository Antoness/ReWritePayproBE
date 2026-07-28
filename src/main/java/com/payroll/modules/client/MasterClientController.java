package com.payroll.modules.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/master-client")
public class MasterClientController {

    @Autowired
    private MasterClientService masterClientService;

    // TODO: Extract userId and fullname from JWT token in the future.
    // For now, passing them as parameters to simulate JWT extraction.
    
    @PostMapping("/staff/list")
    public ResponseEntity<Page<MasterClientResponseDTO>> getStaffList(
            @RequestHeader(value = "user-id", required = false) Long userId,
            @RequestHeader(value = "fullname", required = false) String fullname,
            @RequestBody MasterClientSearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        // Fallback for testing if headers are not provided
        if (fullname == null) fullname = "Staff HRD";
        
        try {
            Long fetchedUserId = masterClientService.getUserIdByFullname(fullname);
            if (fetchedUserId != null) {
                userId = fetchedUserId;
            }
        } catch(Exception e) {
            if (userId == null) userId = 16L;
        }
        
        return ResponseEntity.ok(masterClientService.getStaffList(userId, fullname, request, page, size));
    }

    @PostMapping("/staff/export")
    public ResponseEntity<List<MasterClientResponseDTO>> exportStaffData(
            @RequestHeader(value = "user-id", required = false) Long userId,
            @RequestHeader(value = "fullname", required = false) String fullname,
            @RequestHeader(value = "upliner-name", required = false) String uplinerName,
            @RequestBody MasterClientSearchRequest request) {
        
        if (userId == null) userId = 16L;
        if (fullname == null) fullname = "Staff HRD";
        if (uplinerName == null) uplinerName = "SPV HRD";

        if (request.getIds() != null && !request.getIds().isEmpty()) {
            return ResponseEntity.ok(masterClientService.getStaffExportByChecklist(request.getIds()));
        } else {
            return ResponseEntity.ok(masterClientService.getStaffExportByFilter(userId, fullname, uplinerName, request));
        }
    }

    @PostMapping("/spv/list")
    public ResponseEntity<Page<MasterClientResponseDTO>> getSpvList(
            @RequestHeader(value = "fullname", required = false) String fullname,
            @RequestBody MasterClientSearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        if (fullname == null) fullname = "SPV HRD";
        
        return ResponseEntity.ok(masterClientService.getSpvList(fullname, request, page, size));
    }

    @PostMapping("/spv/export")
    public ResponseEntity<List<MasterClientResponseDTO>> exportSpvData(
            @RequestHeader(value = "fullname", required = false) String fullname,
            @RequestBody MasterClientSearchRequest request) {
        
        if (fullname == null) fullname = "SPV HRD";

        if (request.getIds() != null && !request.getIds().isEmpty()) {
            return ResponseEntity.ok(masterClientService.getSpvExportByChecklist(request.getIds()));
        } else {
            return ResponseEntity.ok(masterClientService.getSpvExportByFilter(fullname, request));
        }
    }
    @PostMapping("/{role}/history")
    public ResponseEntity<Page<MasterClientResponseDTO>> getHistoryLog(
            @PathVariable String role,
            @RequestHeader(value = "fullname", required = false) String fullname,
            @RequestBody MasterClientSearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        if (fullname == null) {
            if ("STAFF".equalsIgnoreCase(role)) fullname = "Staff HRD";
            else if ("SPV".equalsIgnoreCase(role)) fullname = "SPV HRD";
            else fullname = "Manager";
        }

        return ResponseEntity.ok(masterClientService.getHistoryLog(role, fullname, request, page, size));
    }
    @PostMapping("/add")
    public ResponseEntity<?> addClient(
            @RequestHeader(value = "fullname", required = false) String fullname,
            @RequestBody MasterSalaryRequestDTO request) {
        
        if (fullname == null) fullname = "System";

        try {
            MasterSalary saved = masterClientService.addClient(request, fullname);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(
            @PathVariable Long id,
            @RequestHeader(value = "fullname", required = false) String fullname,
            @RequestBody MasterSalaryRequestDTO request) {
        
        if (fullname == null) fullname = "System";

        try {
            MasterSalary updated = masterClientService.updateClient(id, request, fullname);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(masterClientService.getClientById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/download-template")
    public ResponseEntity<byte[]> downloadTemplate() {
        try (org.apache.poi.xssf.usermodel.XSSFWorkbook workbook = new org.apache.poi.xssf.usermodel.XSSFWorkbook()) {
            org.apache.poi.xssf.usermodel.XSSFSheet sheet = workbook.createSheet("Template Client");
            org.apache.poi.xssf.usermodel.XSSFRow headerRow = sheet.createRow(0);
            headerRow.setHeightInPoints(40.5f);

            org.apache.poi.xssf.usermodel.XSSFFont font = workbook.createFont();
            font.setFontName("Calibri");
            font.setFontHeightInPoints((short) 10);
            font.setBold(true);

            org.apache.poi.xssf.usermodel.XSSFCellStyle[] styles = new org.apache.poi.xssf.usermodel.XSSFCellStyle[6];
            byte[][] colors = {
                {(byte)0x9B, (byte)0xC2, (byte)0xE6}, // 0-8
                {(byte)0xFF, (byte)0xFF, (byte)0x99}, // 9-11
                {(byte)0xB4, (byte)0xC6, (byte)0xE7}, // 12-43
                {(byte)0xF6, (byte)0xF4, (byte)0xEB}, // 44-45
                {(byte)0x8E, (byte)0xA9, (byte)0xDB}, // 46-53
                {(byte)0xD9, (byte)0xE1, (byte)0xF2}  // 54-56
            };

            for (int i = 0; i < styles.length; i++) {
                styles[i] = workbook.createCellStyle();
                styles[i].setFont(font);
                styles[i].setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);
                styles[i].setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
                styles[i].setWrapText(true);
                styles[i].setFillForegroundColor(new org.apache.poi.xssf.usermodel.XSSFColor(colors[i], new org.apache.poi.xssf.usermodel.DefaultIndexedColorMap()));
                styles[i].setFillPattern(org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND);
            }

            String[] headers = {
                "No.", "Division", "Unit", "Position", "Employee Type", "Branch", "Works Days", 
                "Bpjs TK Type", "Manajemen Fee", "Variable", "Fix", "Daily", 
                "Tunjangan Supervisor", "Tunjangan Jabatan", "Skill Allowance", "Grading Allowance", 
                "Montly Allowance", "Performance Allowance", "Position Allowance", "Tunjangan Bensin", 
                "Tunjangan Komunikasi", "Tunjangan Transportasi", "Tunjangan Productivity", 
                "Tunjangan Khusus", "Sewa Laptop", "Tunjangan Uang Makan", "Career Allowence", 
                "Tunjangan Premium", "Tunjangan Kerja", "Tunjangan Parkir", "Tunjangan Kehadiran", 
                "Tunjangan Tugas Harian", "Tunjangan Operasional", "Tunjangan Sewa dan Service Motor", 
                "Tunjangan Akomodasi", "Tunjangan Project", "Project Allowance", "Tunjangan Cuti", 
                "Tunjangan Kerajinan", "Tunjangan Masa Kontrak (Karyawan Layoff)", "Tunjangan Service Charge", 
                "Tunjangan Surveyor Bengkel", "Tunjangan Tempat Tinggal", "Tunjangan Bensin dan Parkir", 
                "Biaya Jasa Training", "Bonus", "Insentif", "Lembur", "Tunjangan Kesehatan", 
                "Performance Pay", "Monthly Commision", "Shift Allowance", "THR", "Kompensasi", 
                "BPJS Ketenagakerjaan", "Status Asuransi", "Methode Pajak"
            };

            double[] widths = {8.66666666666667, 12.0, 14.552380952381, 14.6666666666667, 12.1047619047619, 15.1047619047619, 13.0, 9.43809523809524, 13.8857142857143, 10.4380952380952, 13.0, 13.0, 12.4380952380952, 9.88571428571429, 13.0, 13.0, 13.0, 12.8857142857143, 13.0, 10.552380952381, 10.1047619047619, 11.1047619047619, 10.8857142857143, 13.0, 13.0, 11.4285714285714, 13.0, 12.1047619047619, 13.0, 12.4380952380952, 12.552380952381, 14.552380952381, 13.0, 17.4380952380952, 13.0, 14.552380952381, 13.0, 10.152380952381, 13.0, 16.0285714285714, 14.6, 14.9142857142857, 14.7619047619048, 15.552380952381, 11.8857142857143, 13.0, 13.0, 13.0, 10.1047619047619, 10.8857142857143, 13.4380952380952, 13.0, 13.0, 10.4380952380952, 14.4380952380952, 10.6666666666667, 13.0};

            for (int i = 0; i < headers.length; i++) {
                org.apache.poi.xssf.usermodel.XSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                
                int styleIdx = 0;
                if (i >= 54) styleIdx = 5;
                else if (i >= 46) styleIdx = 4;
                else if (i >= 44) styleIdx = 3;
                else if (i >= 12) styleIdx = 2;
                else if (i >= 9) styleIdx = 1;
                
                cell.setCellStyle(styles[styleIdx]);
                sheet.setColumnWidth(i, (int) ((widths[i] + 0.71) * 256));
            }

            java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
            workbook.write(out);
            org.springframework.http.HttpHeaders responseHeaders = new org.springframework.http.HttpHeaders();
            responseHeaders.add("Content-Disposition", "attachment; filename=\"Template_Client.xlsx\"");
            return new org.springframework.http.ResponseEntity<>(out.toByteArray(), responseHeaders, org.springframework.http.HttpStatus.OK);
        } catch (Exception e) {
            return org.springframework.http.ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/update-preview")
    public ResponseEntity<?> getUpdatePreview(
            @RequestBody MasterClientSearchRequest request) {
        if (request.getIds() != null && !request.getIds().isEmpty()) {
            return ResponseEntity.ok(masterClientService.getUpdatePreviewByChecklist(request.getIds()));
        }
        return ResponseEntity.badRequest().body("No IDs provided");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(
            @PathVariable Long id,
            @RequestHeader(value = "fullname", defaultValue = "System") String fullname) {
        try {
            masterClientService.deleteClient(id, fullname);
            return ResponseEntity.ok().body("{\"message\": \"Success\"}");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/update-komponen-upah")
    public ResponseEntity<?> updateKomponenUpah(
            @RequestBody java.util.Map<String, Object> payload) {
        try {
            List<Number> rawIds = (List<Number>) payload.get("ids");
            List<Long> ids = rawIds.stream().map(Number::longValue).collect(java.util.stream.Collectors.toList());
            String upahTk = (String) payload.get("upah_tk");
            
            masterClientService.updateKomponenUpah(ids, upahTk);
            return ResponseEntity.ok().body("{\"message\": \"Success\"}");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/status")
    public ResponseEntity<?> updateStatus(
            @RequestBody java.util.Map<String, Object> payload) {
        try {
            List<Number> rawIds = (List<Number>) payload.get("ids");
            List<Long> ids = rawIds.stream().map(Number::longValue).collect(java.util.stream.Collectors.toList());
            String status = (String) payload.get("status");
            String keterangan = (String) payload.get("keterangan");
            if (keterangan == null) keterangan = "";
            
            masterClientService.updateStatus(ids, status, keterangan);
            return ResponseEntity.ok().body("{\"message\": \"Success\"}");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}