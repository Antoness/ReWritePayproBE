package com.payroll.modules.payroll;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/api/v1/payroll", "/api/v1/payroll/approval"})
@RequiredArgsConstructor
public class PayrollApprovalController {

    private final PayrollApprovalService approvalService;
    private final PayrollTransactionRepository transactionRepository;

    @GetMapping("/transactions")
    @PreAuthorize("hasAuthority('MENU_PAYROLL_PROSES') or hasAuthority('ROLE_STAFF') or hasAuthority('ROLE_SPV') or hasAuthority('ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<PayrollTransaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionRepository.findAll());
    }

    @GetMapping("/inbox")
    @PreAuthorize("hasAuthority('MENU_PAYROLL_PROSES') or hasAuthority('ROLE_SPV') or hasAuthority('ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<PayrollTransaction>> getInboxApprovals(
            @RequestParam(name = "uplinerNik", required = false) String uplinerNik) {
        return ResponseEntity.ok(approvalService.getInboxApprovals(uplinerNik));
    }

    @PostMapping("/request")
    @PreAuthorize("hasAuthority('MENU_PAYROLL_PROSES') or hasAuthority('ROLE_STAFF') or hasAuthority('ROLE_SUPER_ADMIN')")
    public ResponseEntity<Map<String, Object>> requestApproval(@RequestBody ApprovalActionRequest request) {
        approvalService.requestApproval(request.getPayrollIds());
        return ResponseEntity.ok(Map.of("success", true, "message", "Permohonan approval berhasil diajukan"));
    }

    @PostMapping("/approve")
    @PreAuthorize("hasAuthority('MENU_PAYROLL_PROSES') or hasAuthority('ROLE_SPV') or hasAuthority('ROLE_SUPER_ADMIN')")
    public ResponseEntity<Map<String, Object>> approvePayroll(@RequestBody ApprovalActionRequest request) {
        approvalService.approvePayroll(request.getPayrollIds());
        return ResponseEntity.ok(Map.of("success", true, "message", "Data payroll berhasil disetujui"));
    }

    @PostMapping("/reject")
    @PreAuthorize("hasAuthority('MENU_PAYROLL_PROSES') or hasAuthority('ROLE_SPV') or hasAuthority('ROLE_SUPER_ADMIN')")
    public ResponseEntity<Map<String, Object>> rejectPayroll(@RequestBody ApprovalActionRequest request) {
        approvalService.rejectPayroll(request.getPayrollIds(), request.getReason());
        return ResponseEntity.ok(Map.of("success", true, "message", "Data payroll berhasil direject"));
    }

    @Data
    public static class ApprovalActionRequest {
        private List<String> payrollIds;
        private String reason;
    }
}
