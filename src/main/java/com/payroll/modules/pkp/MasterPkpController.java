package com.payroll.modules.pkp;

import com.payroll.security.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/master-pkp")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MasterPkpController {

    private final MasterPkpService masterPkpService;
    private final JwtTokenProvider jwtTokenProvider;

    private String getUsernameFromToken(String token) {
        String jwt = token.substring(7); // strip "Bearer "
        Claims claims = jwtTokenProvider.getClaims(jwt);
        String fullName = claims.get("fullName", String.class);
        return (fullName != null && !fullName.isBlank()) ? fullName : claims.getSubject();
    }

    /**
     * GET /api/master-pkp/list?search=&page=0&size=10
     */
    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            return ResponseEntity.ok(masterPkpService.findPkpData(search, page, size));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "System Error: " + e.getMessage()));
        }
    }

    /**
     * GET /api/master-pkp/history?search=&page=0&size=10
     */
    @GetMapping("/history")
    public ResponseEntity<?> getHistory(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            return ResponseEntity.ok(masterPkpService.getHistoryData(search, page, size));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "System Error: " + e.getMessage()));
        }
    }

    /**
     * POST /api/master-pkp/add
     * Body: { value, tax, taxTanpaNpwp }
     */
    @PostMapping("/add")
    public ResponseEntity<?> addData(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        try {
            String username = getUsernameFromToken(token);
            return ResponseEntity.ok(masterPkpService.addPkp(payload, username));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "System Error: " + e.getMessage()));
        }
    }

    /**
     * POST /api/master-pkp/update
     * Body: { id, value, tax, taxTanpaNpwp }
     */
    @PostMapping("/update")
    public ResponseEntity<?> updateData(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        try {
            String username = getUsernameFromToken(token);
            return ResponseEntity.ok(masterPkpService.updatePkp(payload, username));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "System Error: " + e.getMessage()));
        }
    }
}
