package com.payroll.modules.ter;

import com.payroll.security.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/master-ter")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MasterTerController {

    private final JwtTokenProvider jwtTokenProvider;
    private final MasterTerService masterTerService;

    @GetMapping("/dropdowns")
    public ResponseEntity<?> getDropdowns() {
        try {
            return ResponseEntity.ok(masterTerService.getDropdowns());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(java.util.Map.of("error", "System Error: " + e.getMessage()));
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getTerList(
            @RequestParam(required = false) String year,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            return ResponseEntity.ok(masterTerService.findTerData(year, page, size));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(java.util.Map.of("error", "System Error: " + e.getMessage()));
        }
    }

    @PostMapping("/update-status")
    public ResponseEntity<?> updateStatusTahunActive(
            @RequestParam String year
    ) {
        try {
            return ResponseEntity.ok(masterTerService.updateStatusTahunActive(year));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(java.util.Map.of("success", false, "message", "System Error: " + e.getMessage()));
        }
    }

    @GetMapping("/upload-years")
    public ResponseEntity<?> getUploadYears() {
        try {
            return ResponseEntity.ok(java.util.Map.of("years", masterTerService.getUploadYears()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(java.util.Map.of("error", "System Error: " + e.getMessage()));
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadTerData(
            @RequestHeader("Authorization") String token,
            @RequestParam("file") MultipartFile file,
            @RequestParam("year") String year
    ) {
        try {
            String jwt = token.substring(7);
            Claims claims = jwtTokenProvider.getClaims(jwt);
            String fullName = claims.get("fullName", String.class);
            return ResponseEntity.ok(masterTerService.processUploadTer(file, year, fullName));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(java.util.Map.of("success", false, "message", "System Error: " + e.getMessage()));
        }
    }

    @GetMapping("/template")
    public ResponseEntity<byte[]> downloadTemplate(
            @RequestHeader("Authorization") String token
    ) {
        String jwt = token.substring(7);
        jwtTokenProvider.getClaims(jwt);

        try (Workbook workbook = new HSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Template TER");

            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            Row headerRow = sheet.createRow(0);
            String[] headers = { "ter", "Nilai Min", "Nilai Max", "persen" };
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
                sheet.setColumnWidth(i, 5000);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
            responseHeaders.setContentDispositionFormData("attachment", "Template_TER.xls");

            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(outputStream.toByteArray());

        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
