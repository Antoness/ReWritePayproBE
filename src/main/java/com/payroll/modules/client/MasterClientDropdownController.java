package com.payroll.modules.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.payroll.modules.master.PayrollComponentResponseDTO;
import com.payroll.modules.master.PayrollComponentService;

@RestController
@RequestMapping("/api/master-client/dropdowns")
public class MasterClientDropdownController {

    @Autowired
    private PayrollComponentService payrollComponentService;

    @Autowired
    private MasterClientDropdownService dropdownService;

    @GetMapping("/divisions")
    public ResponseEntity<List<String>> getDivisions() {
        return ResponseEntity.ok(dropdownService.getDivisions());
    }

    @GetMapping("/units")
    public ResponseEntity<List<String>> getUnits(@RequestParam String division) {
        return ResponseEntity.ok(dropdownService.getUnits(division));
    }

    @GetMapping("/positions")
    public ResponseEntity<List<String>> getPositions(
            @RequestParam String division,
            @RequestParam String unitName) {
        return ResponseEntity.ok(dropdownService.getPositions(division, unitName));
    }

    @GetMapping("/employee-types")
    public ResponseEntity<List<String>> getEmployeeTypes(
            @RequestParam String division,
            @RequestParam String unitName,
            @RequestParam String position) {
        return ResponseEntity.ok(dropdownService.getEmployeeTypes(division, unitName, position));
    }

    @GetMapping("/branches")
    public ResponseEntity<List<String>> getBranches(
            @RequestParam String division,
            @RequestParam String unitName,
            @RequestParam String position,
            @RequestParam String employeeType) {
        return ResponseEntity.ok(dropdownService.getBranches(division, unitName, position, employeeType));
    }

    @GetMapping("/salary-types")
    public ResponseEntity<List<DropdownOptionDTO>> getSalaryTypes() {
        return ResponseEntity.ok(dropdownService.getSalaryTypes());
    }

    @GetMapping("/allowances")
    public ResponseEntity<List<String>> getAllowances() {
        return ResponseEntity.ok(dropdownService.getAllowances());
    }

    @GetMapping("/komponen-upah")
    public ResponseEntity<List<DropdownOptionDTO>> getKomponenUpah() {
        return ResponseEntity.ok(dropdownService.getKomponenUpah());
    }

    /**
     * GET /api/master-client/dropdowns/payroll-components
     * Returns MERGED list of allowances:
     *   1. Base defaults from master_allowance (always shown)
     *   2. PLUS payroll_components matching the given division/unit/position/employeeType filter
     * Used by Master Client form to populate Tunjangan Tetap & Tidak Tetap dropdowns.
     */
    @GetMapping("/payroll-components")
    public ResponseEntity<List<String>> getPayrollComponentsForClient(
            @RequestParam(required = false, defaultValue = "") String division,
            @RequestParam(required = false, defaultValue = "") String position,
            @RequestParam(required = false, defaultValue = "") String unitName,
            @RequestParam(required = false, defaultValue = "") String employeeType) {
        return ResponseEntity.ok(
                dropdownService.getAllowancesForClient(division, position, unitName, employeeType));
    }

    // Hardcoded endpoints
    @GetMapping("/work-days")
    public ResponseEntity<List<String>> getWorkDays() {
        return ResponseEntity.ok(dropdownService.getWorkDays());
    }

    @GetMapping("/bpjs-tk-types")
    public ResponseEntity<List<String>> getBpjsTkTypes() {
        return ResponseEntity.ok(dropdownService.getBpjsTkTypes());
    }

    @GetMapping("/metode-pajak")
    public ResponseEntity<List<String>> getMetodePajak() {
        return ResponseEntity.ok(dropdownService.getMetodePajak());
    }

    @GetMapping("/komponen-project")
    public ResponseEntity<List<String>> getKomponenProject() {
        return ResponseEntity.ok(dropdownService.getKomponenProject());
    }

    @GetMapping("/bpjs-ketenagakerjaan")
    public ResponseEntity<List<String>> getBpjsKetenagakerjaanOptions() {
        return ResponseEntity.ok(dropdownService.getBpjsKetenagakerjaanOptions());
    }

    @GetMapping("/ditanggung-oleh")
    public ResponseEntity<List<String>> getDitanggungOlehOptions() {
        return ResponseEntity.ok(dropdownService.getDitanggungOlehOptions());
    }
}
