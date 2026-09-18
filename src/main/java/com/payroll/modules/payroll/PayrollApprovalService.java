package com.payroll.modules.payroll;

import com.payroll.modules.user.MasterUplinerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayrollApprovalService {

    private final PayrollTransactionRepository transactionRepository;
    private final MasterUplinerRepository uplinerRepository;

    @Transactional(readOnly = true)
    public List<PayrollTransaction> getInboxApprovals(String uplinerNik) {
        if (uplinerNik == null || uplinerNik.isBlank()) {
            return transactionRepository.findPendingApprovals();
        }
        List<String> downlinerNiks = uplinerRepository.findDownlinersByNikUpliner(uplinerNik);
        if (downlinerNiks.isEmpty()) {
            return transactionRepository.findPendingApprovals();
        }
        return transactionRepository.findPendingApprovalsByNiks(downlinerNiks);
    }

    @Transactional
    public void requestApproval(List<String> payrollIds) {
        List<PayrollTransaction> transactions = transactionRepository.findAllById(payrollIds);
        for (PayrollTransaction trx : transactions) {
            trx.setStatusData("REQUEST_APPROVAL");
        }
        transactionRepository.saveAll(transactions);
    }

    @Transactional
    public void approvePayroll(List<String> payrollIds) {
        List<PayrollTransaction> transactions = transactionRepository.findAllById(payrollIds);
        for (PayrollTransaction trx : transactions) {
            trx.setStatusData("APPROVED");
        }
        transactionRepository.saveAll(transactions);
    }

    @Transactional
    public void rejectPayroll(List<String> payrollIds, String reason) {
        List<PayrollTransaction> transactions = transactionRepository.findAllById(payrollIds);
        for (PayrollTransaction trx : transactions) {
            trx.setStatusData("REJECTED");
        }
        transactionRepository.saveAll(transactions);
    }
}
