package com.payroll.modules.settings;

import com.payroll.security.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping({
    "/api/master-signed",
    "/api/v1/master-signed",
    "/api/master-settings",
    "/api/v1/master-settings",
    "/api/master-setting-assign",
    "/api/v1/master-setting-assign"
})
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class MasterSignedController {

    private final MasterSettingService settingService;
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
                String name = claims.get("nama", String.class);
                if (name != null) return name;
                String nik = claims.get("nik", String.class);
                if (nik != null) return nik;
            } catch (Exception ignored) {}
        }
        return "Staff HRD";
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('MASTER_SETTING_ASSIGN', 'MENU_MASTER_SETTING_ASSIGN', 'MASTER_SIGNED', 'ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_STAFF', 'ROLE_SPV') or isAuthenticated()")
    public ResponseEntity<?> getSettings(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        Long companyId = getCompanyIdFromToken(authHeader);
        Page<MasterSettingDTO.Response> result = settingService.getSettings(companyId, search, page, size);
        Map<String, Object> kpiSummary = settingService.getKpiSummary(companyId);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", result.getContent());
        response.put("totalElements", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());
        response.put("page", page);
        response.put("size", size);
        response.put("kpi", kpiSummary);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{keyOrId}")
    @PreAuthorize("hasAnyAuthority('MASTER_SETTING_ASSIGN', 'MENU_MASTER_SETTING_ASSIGN', 'MASTER_SIGNED', 'ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_STAFF', 'ROLE_SPV') or isAuthenticated()")
    public ResponseEntity<?> getSettingDetail(
            @PathVariable String keyOrId,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        Long companyId = getCompanyIdFromToken(authHeader);
        MasterSettingDTO.Response result = settingService.getSettingByKeyOrId(companyId, keyOrId);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", result);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('MASTER_SETTING_ASSIGN', 'MENU_MASTER_SETTING_ASSIGN', 'MASTER_SIGNED', 'ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_STAFF', 'ROLE_SPV') or isAuthenticated()")
    public ResponseEntity<?> createSetting(
            @Valid @RequestBody MasterSettingDTO.Request request,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        Long companyId = getCompanyIdFromToken(authHeader);
        String username = getUsernameFromToken(authHeader);

        MasterSettingDTO.Response created = settingService.createSetting(companyId, request, username);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Setting berhasil ditambahkan");
        response.put("data", created);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{keyOrId}")
    @PreAuthorize("hasAnyAuthority('MASTER_SETTING_ASSIGN', 'MENU_MASTER_SETTING_ASSIGN', 'MASTER_SIGNED', 'ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_STAFF', 'ROLE_SPV') or isAuthenticated()")
    public ResponseEntity<?> updateSetting(
            @PathVariable String keyOrId,
            @Valid @RequestBody MasterSettingDTO.Request request,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        Long companyId = getCompanyIdFromToken(authHeader);
        String username = getUsernameFromToken(authHeader);

        MasterSettingDTO.Response updated = settingService.updateSetting(companyId, keyOrId, request, username);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Setting berhasil diupdate");
        response.put("data", updated);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{keyOrId}")
    @PreAuthorize("hasAnyAuthority('MASTER_SETTING_ASSIGN', 'MENU_MASTER_SETTING_ASSIGN', 'MASTER_SIGNED', 'ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_STAFF', 'ROLE_SPV') or isAuthenticated()")
    public ResponseEntity<?> deleteSetting(
            @PathVariable String keyOrId,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        Long companyId = getCompanyIdFromToken(authHeader);
        String username = getUsernameFromToken(authHeader);

        settingService.deleteSetting(companyId, keyOrId, username);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Setting berhasil dihapus");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/history")
    @PreAuthorize("hasAnyAuthority('MASTER_SETTING_ASSIGN', 'MENU_MASTER_SETTING_ASSIGN', 'MASTER_SIGNED', 'ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_STAFF', 'ROLE_SPV') or isAuthenticated()")
    public ResponseEntity<?> getHistory(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        Long companyId = getCompanyIdFromToken(authHeader);
        Page<MasterSettingDTO.HistoryResponse> result = settingService.getHistoryLogs(companyId, search, page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", result.getContent());
        response.put("totalElements", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());
        response.put("page", page);
        response.put("size", size);

        return ResponseEntity.ok(response);
    }
}
