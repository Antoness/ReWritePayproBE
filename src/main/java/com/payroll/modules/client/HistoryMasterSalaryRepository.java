package com.payroll.modules.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryMasterSalaryRepository extends JpaRepository<HistoryMasterSalary, Long> {
}
