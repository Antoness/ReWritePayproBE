package com.payroll.modules.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryMfeeUkRepository extends JpaRepository<HistoryMfeeUk, Long> {
}
