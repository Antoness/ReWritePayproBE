package com.payroll.modules.employee;

import com.payroll.security.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/master-employee")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MasterEmployeeController {

    private final MasterEmployeeService masterEmployeeService;
    private final JwtTokenProvider jwtTokenProvider;

    private String getUsernameFromToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return "SYSTEM";
        }
        String jwt = token.substring(7);
        Claims claims = jwtTokenProvider.getClaims(jwt);
        return claims.get("fullName", String.class) != null ? claims.get("fullName", String.class) : claims.getSubject();
    }

    @GetMapping("/list")
    public ResponseEntity<Page<EmployeeResponse>> getList(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String division,
            @RequestParam(required = false) String unit,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String employeeType,
            @RequestParam(required = false) String branch,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String nationality,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(masterEmployeeService.findEmployeeData(search, division, unit, position, employeeType, branch, status, nationality, page, size));
    }

    @GetMapping("/wna-list")
    public ResponseEntity<Page<WnaResponse>> getWnaData(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String division,
            @RequestParam(required = false) String unit,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String employeeType,
            @RequestParam(required = false) String branch,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader("Authorization") String token
    ) {
        String jwt = token.substring(7);
        Claims claims = jwtTokenProvider.getClaims(jwt);
        String userPosition = claims.get("position", String.class);
        return ResponseEntity.ok(masterEmployeeService.findWnaData(search, division, unit, position, employeeType, branch, page, size, userPosition));
    }

    @GetMapping("/tax")
    public ResponseEntity<Page<EmployeeResponse>> getTaxData(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String division,
            @RequestParam(required = false) String unit,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String employeeType,
            @RequestParam(required = false) String branch,
            @RequestParam(required = false) String statusEmployee,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(masterEmployeeService.findTaxData(search, division, unit, position, employeeType, branch, statusEmployee, page, size));
    }

    @GetMapping("/tax/history")
    public ResponseEntity<Page<TaxHistoryResponse>> getTaxHistory(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(masterEmployeeService.findTaxHistory(search, page, size));
    }

    @PostMapping("/tax/request")
    public ResponseEntity<Map<String, String>> requestTax(
            @RequestBody Map<String, Object> payload,
            @RequestHeader("Authorization") String token
    ) {
        String username = getUsernameFromToken(token);
        List<String> niks = (List<String>) payload.get("niks");
        String metodePajak = (String) payload.get("metodePajak");
        String komponenProject = (String) payload.get("komponenProject");
        masterEmployeeService.requestTax(niks, metodePajak, komponenProject, username);
        return ResponseEntity.ok(Map.of("message", "Tax update requested successfully"));
    }

    @PostMapping("/tax/approve")
    public ResponseEntity<Map<String, String>> approveTax(
            @RequestBody Map<String, List<String>> payload,
            @RequestHeader("Authorization") String token
    ) {
        String username = getUsernameFromToken(token);
        masterEmployeeService.approveTax(payload.get("niks"), username);
        return ResponseEntity.ok(Map.of("message", "Tax updates approved successfully"));
    }

    @PostMapping("/tax/reject")
    public ResponseEntity<Map<String, String>> rejectTax(
            @RequestBody Map<String, List<String>> payload,
            @RequestHeader("Authorization") String token
    ) {
        String username = getUsernameFromToken(token);
        masterEmployeeService.rejectTax(payload.get("niks"), username);
        return ResponseEntity.ok(Map.of("message", "Tax updates rejected successfully"));
    }

    @GetMapping("/dropdowns")
    public ResponseEntity<Map<String, Object>> getDropdowns() {
        return ResponseEntity.ok(masterEmployeeService.getDropdowns());
    }

    @GetMapping("/last-update")
    public ResponseEntity<Map<String, Object>> getLastUpdate() {
        return ResponseEntity.ok(masterEmployeeService.getLastUpdate());
    }

    @GetMapping("/request-data")
    public ResponseEntity<List<Employee>> getRequestData() {
        return ResponseEntity.ok(masterEmployeeService.getRequestDataList());
    }

    @GetMapping("/validate-sync")
    public ResponseEntity<Map<String, Object>> validateSync() {
        List<Employee> requests = masterEmployeeService.getRequestDataList();
        return ResponseEntity.ok(Map.of(
            "hasRequest", !requests.isEmpty(),
            "data", requests
        ));
    }

    @GetMapping("/download-template")
    public ResponseEntity<byte[]> downloadTemplate(@RequestParam("type") String type) {
        try (org.apache.poi.ss.usermodel.Workbook workbook = new org.apache.poi.hssf.usermodel.HSSFWorkbook()) {
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Template " + type);
            org.apache.poi.ss.usermodel.Row headerRow = sheet.createRow(0);
            
            if ("WNA".equalsIgnoreCase(type)) {
                headerRow.createCell(0).setCellValue("NIK");
                headerRow.createCell(1).setCellValue("Passport Number");
                headerRow.createCell(2).setCellValue("Kitas Number");
                headerRow.createCell(3).setCellValue("Tanggal Izin Kerja");
                headerRow.createCell(4).setCellValue("Kode Negara");
            } else if ("NPWP".equalsIgnoreCase(type)) {
                headerRow.createCell(0).setCellValue("NIK");
                headerRow.createCell(1).setCellValue("NPWP");
            } else if ("ID TKU".equalsIgnoreCase(type)) {
                headerRow.createCell(0).setCellValue("NIK");
                headerRow.createCell(1).setCellValue("ID TKU");
            } else if ("PAJAK".equalsIgnoreCase(type)) {
                headerRow.createCell(0).setCellValue("NIK");
                headerRow.createCell(1).setCellValue("Metode Pajak");
                headerRow.createCell(2).setCellValue("Komponen Project");
            }

            java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
            workbook.write(out);
            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=Template_" + type + ".xls");
            return new org.springframework.http.ResponseEntity<>(out.toByteArray(), headers, org.springframework.http.HttpStatus.OK);
        } catch (java.io.IOException e) {
            return org.springframework.http.ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{nik}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable String nik,
            @RequestBody EmployeeUpdateRequest request
    ) {
        return ResponseEntity.ok(masterEmployeeService.updateEmployee(nik, request));
    }

    @PostMapping("/sync-hris")
    public ResponseEntity<Map<String, String>> syncHris() {
        masterEmployeeService.syncHris();
        return ResponseEntity.ok(Map.of("message", "HRIS sync triggered successfully"));
    }

    @PostMapping("/preview-upload")
    public ResponseEntity<Map<String, Object>> previewUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("type") String type
    ) {
        return ResponseEntity.ok(masterEmployeeService.previewUpload(file, type));
    }

    @PostMapping("/upload-npwp")
    public ResponseEntity<Map<String, Object>> uploadNpwp(
            @RequestHeader("Authorization") String token,
            @RequestParam("file") MultipartFile file
    ) {
        String username = getUsernameFromToken(token);
        Map<String, Object> result = masterEmployeeService.uploadNpwp(file, username);
        if (Boolean.TRUE.equals(result.get("success"))) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PostMapping("/upload-tku")
    public ResponseEntity<Map<String, Object>> uploadTku(
            @RequestHeader("Authorization") String token,
            @RequestParam("file") MultipartFile file
    ) {
        String username = getUsernameFromToken(token);
        Map<String, Object> result = masterEmployeeService.uploadTku(file, username);
        if (Boolean.TRUE.equals(result.get("success"))) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PostMapping("/upload-wna")
    public ResponseEntity<Map<String, Object>> uploadWna(
            @RequestHeader("Authorization") String token,
            @RequestParam("file") MultipartFile file
    ) {
        String username = getUsernameFromToken(token);
        Map<String, Object> result = masterEmployeeService.uploadWna(file, username);
        if (Boolean.TRUE.equals(result.get("success"))) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PutMapping("/wna/{nik}")
    public ResponseEntity<WnaResponse> updateWnaData(
            @PathVariable String nik,
            @RequestBody WnaUpdateRequest request,
            @RequestHeader("Authorization") String token
    ) {
        String username = getUsernameFromToken(token);
        return ResponseEntity.ok(masterEmployeeService.updateWnaData(nik, request, username));
    }

    @PostMapping("/wna/bulk-approve")
    public ResponseEntity<Map<String, String>> bulkApproveWna(
            @RequestBody Map<String, List<String>> payload,
            @RequestHeader("Authorization") String token
    ) {
        String username = getUsernameFromToken(token);
        masterEmployeeService.bulkApproveWna(payload.get("niks"), username);
        return ResponseEntity.ok(Map.of("message", "WNA updates approved successfully"));
    }

    @PostMapping("/wna/bulk-reject")
    public ResponseEntity<Map<String, String>> bulkRejectWna(
            @RequestBody Map<String, List<String>> payload,
            @RequestHeader("Authorization") String token
    ) {
        String username = getUsernameFromToken(token);
        masterEmployeeService.bulkRejectWna(payload.get("niks"), username);
        return ResponseEntity.ok(Map.of("message", "WNA updates rejected successfully"));
    }
}
