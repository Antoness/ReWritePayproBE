package com.payroll.modules.master;

import com.payroll.security.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/master-bank")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MasterBankController {

    @Autowired
    private MasterBankService bankService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private String getNikFromToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return "System";
        }
        try {
            String token = authHeader.substring(7);
            Claims claims = jwtTokenProvider.getClaims(token);
            return claims.get("nik", String.class);
        } catch (Exception e) {
            return "System";
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllBanks(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
            Page<MasterBankProjection> banks = bankService.searchBanks(keyword, pageable);
            
            Map<String, Object> response = new HashMap<>();
            response.put("data", banks.getContent());
            response.put("totalElements", banks.getTotalElements());
            response.put("totalPages", banks.getTotalPages());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping
    public ResponseEntity<?> addBank(
            @RequestBody MasterBank bank,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        try {
            String userNik = getNikFromToken(authHeader);
            return ResponseEntity.ok(bankService.addBank(bank, userNik));
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBank(
            @PathVariable Long id,
            @RequestBody MasterBank bank,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        try {
            String userNik = getNikFromToken(authHeader);
            return ResponseEntity.ok(bankService.updateBank(id, bank, userNik));
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBank(@PathVariable Long id) {
        try {
            bankService.deleteBank(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/{bankId}/alias")
    public ResponseEntity<?> getAliases(
            @PathVariable Long bankId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page - 1, size);
            Page<Map<String, Object>> aliases = bankService.getAliases(bankId, pageable);
            Map<String, Object> response = new HashMap<>();
            response.put("data", aliases.getContent());
            response.put("totalElements", aliases.getTotalElements());
            response.put("totalPages", aliases.getTotalPages());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/{bankId}/alias")
    public ResponseEntity<?> addAlias(
            @PathVariable Long bankId,
            @RequestBody BankAlias alias,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        try {
            String userNik = getNikFromToken(authHeader);
            return ResponseEntity.ok(bankService.addAlias(bankId, alias, userNik));
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/alias/{aliasId}")
    public ResponseEntity<?> updateAlias(
            @PathVariable Long aliasId,
            @RequestBody BankAlias alias,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        try {
            String userNik = getNikFromToken(authHeader);
            return ResponseEntity.ok(bankService.updateAlias(aliasId, alias, userNik));
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @DeleteMapping("/alias/{aliasId}")
    public ResponseEntity<?> deleteAlias(@PathVariable Long aliasId) {
        try {
            bankService.deleteAlias(aliasId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
