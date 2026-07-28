package com.payroll.modules.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MasterSalaryRepository extends JpaRepository<MasterSalary, Long> {
    Optional<MasterSalary> findByDivisionIgnoreCaseAndUnitNameIgnoreCaseAndPositionIgnoreCaseAndBranchIgnoreCaseAndEmployeeTypeIgnoreCase(
        String division, String unitName, String position, String branch, String employeeType
    );
}
