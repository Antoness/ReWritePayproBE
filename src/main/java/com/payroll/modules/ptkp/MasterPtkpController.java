package com.payroll.modules.ptkp;

import com.payroll.security.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/master-ptkp")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MasterPtkpController {

    private final MasterPtkpService masterPtkpService;
    private final JwtTokenProvider jwtTokenProvider;

    private String getUsernameFromToken(String token) {
        String jwt = token.substring(7);
        Claims claims = jwtTokenProvider.getClaims(jwt);
        return claims.get("fullName", String.class) != null ? claims.get("fullName", String.class) : claims.getSubject();
    }

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            return ResponseEntity.ok(masterPtkpService.findPtkpData(search, page, size));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "System Error: " + e.getMessage()));
        }
    }

    @GetMapping("/history")
    public ResponseEntity<?> getHistory(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            return ResponseEntity.ok(masterPtkpService.getHistoryData(search, page, size));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "System Error: " + e.getMessage()));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addData(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        try {
            String username = getUsernameFromToken(token);
            return ResponseEntity.ok(masterPtkpService.addPtkp(payload, username));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("success", false, "message", "System Error: " + e.getMessage()));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateData(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        try {
            String username = getUsernameFromToken(token);
            return ResponseEntity.ok(masterPtkpService.updatePtkp(payload, username));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("success", false, "message", "System Error: " + e.getMessage()));
        }
    }
}
