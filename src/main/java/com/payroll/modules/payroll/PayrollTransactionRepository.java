package com.payroll.modules.payroll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollTransactionRepository extends JpaRepository<PayrollTransaction, String> {

    List<PayrollTransaction> findByStatusData(String statusData);

    @Query("SELECT p FROM PayrollTransaction p WHERE p.statusData = 'REQUEST_APPROVAL'")
    List<PayrollTransaction> findPendingApprovals();

    @Query("SELECT p FROM PayrollTransaction p WHERE p.nik IN :niks AND p.statusData = 'REQUEST_APPROVAL'")
    List<PayrollTransaction> findPendingApprovalsByNiks(@Param("niks") List<String> niks);
}
