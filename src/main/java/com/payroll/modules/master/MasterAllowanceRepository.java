package com.payroll.modules.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MasterAllowanceRepository extends JpaRepository<MasterAllowance, Long> {
    
    List<MasterAllowance> findAllByOrderByIdAsc();
}
