package com.payroll.modules.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payroll-components")
public class PayrollComponentController {

    @Autowired
    private PayrollComponentService service;

    /**
     * GET /api/v1/payroll-components
     * Returns all active components (for Admin/Manajer management view)
     */
    @GetMapping
    public ResponseEntity<List<PayrollComponentResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    /**
     * GET /api/v1/payroll-components/for-client
     * Returns components matching given division/position/unit/employeeType filters
     * Used by Master Client to populate dropdowns
     */
    @GetMapping("/for-client")
    public ResponseEntity<List<PayrollComponentResponseDTO>> getForClient(
            @RequestParam(required = false, defaultValue = "") String division,
            @RequestParam(required = false, defaultValue = "") String position,
            @RequestParam(required = false, defaultValue = "") String unitName,
            @RequestParam(required = false, defaultValue = "") String employeeType) {
        return ResponseEntity.ok(service.findForClient(division, position, unitName, employeeType));
    }

    /**
     * POST /api/v1/payroll-components
     * Create a new payroll component
     */
    @PostMapping
    public ResponseEntity<PayrollComponentResponseDTO> create(@RequestBody PayrollComponentRequestDTO req) {
        return ResponseEntity.ok(service.create(req));
    }

    /**
     * PUT /api/v1/payroll-components/{id}
     * Update existing payroll component
     */
    @PutMapping("/{id}")
    public ResponseEntity<PayrollComponentResponseDTO> update(
            @PathVariable Long id,
            @RequestBody PayrollComponentRequestDTO req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    /**
     * DELETE /api/v1/payroll-components/{id}
     * Soft-delete: sets is_active = false
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "system") String deletedBy) {
        service.delete(id, deletedBy);
        return ResponseEntity.ok(Map.of("message", "Payroll component deleted successfully"));
    }
}
