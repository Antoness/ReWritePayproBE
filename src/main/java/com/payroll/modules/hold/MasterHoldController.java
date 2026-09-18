package com.payroll.modules.hold;

import com.payroll.security.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/api/master-hold", "/api/v1/master-hold"})
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class MasterHoldController {

    private final MasterHoldService holdService;
    private final JwtTokenProvider jwtTokenProvider;

    private Long getCompanyIdFromToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                String token = authHeader.substring(7);
                Claims claims = jwtTokenProvider.getClaims(token);
                Object compIdObj = claims.get("companyId");
                if (compIdObj instanceof Number) {
                    return ((Number) compIdObj).longValue();
                }
            } catch (Exception ignored) {}
        }
        return 1L;
    }

    private String getUsernameFromToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                String token = authHeader.substring(7);
                Claims claims = jwtTokenProvider.getClaims(token);
                String user = claims.get("username", String.class);
                if (user != null) return user;
                String nik = claims.get("nik", String.class);
                if (nik != null) return nik;
            } catch (Exception ignored) {}
        }
        return "Staff HRD";
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('MASTER_HOLD', 'MENU_MASTER_HOLD', 'ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_STAFF', 'ROLE_SPV') or isAuthenticated()")
    public ResponseEntity<?> getHoldRecords(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String division,
            @RequestParam(required = false) String unitName,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String branch,
            @RequestParam(required = false) String employeeType,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String month,
            @RequestParam(required = false) String year,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        Long companyId = getCompanyIdFromToken(authHeader);
        Page<MasterHold> result = holdService.getHoldRecords(companyId, search, division, unitName, position, branch, employeeType, status, month, year, page, size);
        Map<String, Object> kpiSummary = holdService.getKpiSummary(companyId);

        Map<String, Object> response = new HashMap<>();
        response.put("data", result.getContent());
        response.put("totalElements", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());
        response.put("page", page);
        response.put("size", size);
        response.put("kpi", kpiSummary);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter-options")
    @PreAuthorize("hasAnyAuthority('MASTER_HOLD', 'MENU_MASTER_HOLD', 'ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_STAFF', 'ROLE_SPV') or isAuthenticated()")
    public ResponseEntity<?> getFilterOptions(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        Long companyId = getCompanyIdFromToken(authHeader);
        return ResponseEntity.ok(holdService.getFilterOptions(companyId));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('MASTER_HOLD', 'MENU_MASTER_HOLD', 'ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_STAFF', 'ROLE_SPV') or isAuthenticated()")
    public ResponseEntity<?> getHoldDetail(
            @PathVariable Long id,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        Long companyId = getCompanyIdFromToken(authHeader);
        return ResponseEntity.ok(holdService.getHoldDetail(companyId, id));
    }

    @GetMapping("/history")
    @PreAuthorize("hasAnyAuthority('MASTER_HOLD', 'MENU_MASTER_HOLD', 'ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_STAFF', 'ROLE_SPV') or isAuthenticated()")
    public ResponseEntity<?> getHistoryLogs(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String division,
            @RequestParam(required = false) String unitName,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String branch,
            @RequestParam(required = false) String employeeType,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        Long companyId = getCompanyIdFromToken(authHeader);
        Page<MasterHoldHistory> result = holdService.getHistoryLogs(companyId, search, division, unitName, position, branch, employeeType, page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("data", result.getContent());
        response.put("totalElements", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());
        response.put("page", page);
        response.put("size", size);
        return ResponseEntity.ok(response);
    }

    @PostMapping({"/cancel", "/cancel-hold"})
    @PreAuthorize("hasAnyAuthority('MASTER_HOLD', 'MENU_MASTER_HOLD', 'ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_STAFF', 'ROLE_SPV') or isAuthenticated()")
    public ResponseEntity<?> cancelHold(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @RequestBody Map<String, Object> payload) {

        Long companyId = getCompanyIdFromToken(authHeader);
        String username = getUsernameFromToken(authHeader);

        List<Long> ids = null;
        if (payload.get("ids") instanceof List<?>) {
            ids = ((List<?>) payload.get("ids")).stream()
                    .map(item -> Long.valueOf(item.toString()))
                    .toList();
        }

        String reason = (String) payload.getOrDefault("reason", payload.get("reasonNote"));

        if (ids != null && !ids.isEmpty()) {
            holdService.cancelHold(companyId, ids, reason, username);
        }
        return ResponseEntity.ok(Map.of("message", "Berhasil membatalkan status hold gaji"));
    }

    @PostMapping({"/approve", "/approve-release"})
    @PreAuthorize("hasAnyAuthority('MASTER_HOLD', 'MENU_MASTER_HOLD', 'ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_STAFF', 'ROLE_SPV') or isAuthenticated()")
    public ResponseEntity<?> approveHold(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @RequestBody Map<String, Object> payload) {

        Long companyId = getCompanyIdFromToken(authHeader);
        String username = getUsernameFromToken(authHeader);

        List<Long> ids = null;
        if (payload.get("ids") instanceof List<?>) {
            ids = ((List<?>) payload.get("ids")).stream()
                    .map(item -> Long.valueOf(item.toString()))
                    .toList();
        }

        if (ids != null && !ids.isEmpty()) {
            holdService.approveHold(companyId, ids, username);
        }
        return ResponseEntity.ok(Map.of("message", "Berhasil menyetujui release gaji"));
    }

    @PostMapping({"/reject", "/reject-release"})
    @PreAuthorize("hasAnyAuthority('MASTER_HOLD', 'MENU_MASTER_HOLD', 'ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_STAFF', 'ROLE_SPV') or isAuthenticated()")
    public ResponseEntity<?> rejectHold(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @RequestBody Map<String, Object> payload) {

        Long companyId = getCompanyIdFromToken(authHeader);
        String username = getUsernameFromToken(authHeader);

        List<Long> ids = null;
        if (payload.get("ids") instanceof List<?>) {
            ids = ((List<?>) payload.get("ids")).stream()
                    .map(item -> Long.valueOf(item.toString()))
                    .toList();
        }

        String reason = (String) payload.getOrDefault("reason", payload.get("reasonNote"));

        if (ids != null && !ids.isEmpty()) {
            holdService.rejectHold(companyId, ids, reason, username);
        }
        return ResponseEntity.ok(Map.of("message", "Berhasil menolak release gaji"));
    }

    @PostMapping({"/release/{id}", "/{id}/release"})
    @PreAuthorize("hasAnyAuthority('MASTER_HOLD', 'MENU_MASTER_HOLD', 'ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_STAFF', 'ROLE_SPV') or isAuthenticated()")
    public ResponseEntity<?> updateRelease(
            @PathVariable Long id,
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @RequestParam(value = "keterangan", required = false) String keterangan,
            @RequestParam(value = "tanggalRelease", required = false) String tanggalRelease,
            @RequestParam(value = "file", required = false) MultipartFile file) {

        Long companyId = getCompanyIdFromToken(authHeader);
        String username = getUsernameFromToken(authHeader);

        MasterHold updated = holdService.updateRelease(companyId, id, keterangan, tanggalRelease, file, username);
        return ResponseEntity.ok(Map.of("message", "Detail release hold berhasil disimpan", "data", updated));
    }

    @GetMapping({"/export", "/export-excel"})
    @PreAuthorize("hasAnyAuthority('MASTER_HOLD', 'MENU_MASTER_HOLD', 'ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_STAFF', 'ROLE_SPV') or isAuthenticated()")
    public ResponseEntity<byte[]> exportExcel(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String division,
            @RequestParam(required = false) String unitName,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String branch,
            @RequestParam(required = false) String employeeType,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String month,
            @RequestParam(required = false) String year) {

        Long companyId = getCompanyIdFromToken(authHeader);
        byte[] excelBytes = holdService.exportExcel(companyId, search, division, unitName, position, branch, employeeType, status, month, year);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "Master_Hold_Export.xlsx");
        return ResponseEntity.ok().headers(headers).body(excelBytes);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getProofFile(@PathVariable String filename) {
        Resource resource = holdService.loadProofFile(filename);
        String contentType = "application/octet-stream";
        if (filename.toLowerCase().endsWith(".pdf")) {
            contentType = "application/pdf";
        } else if (filename.toLowerCase().endsWith(".png")) {
            contentType = "image/png";
        } else if (filename.toLowerCase().endsWith(".jpg") || filename.toLowerCase().endsWith(".jpeg")) {
            contentType = "image/jpeg";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
