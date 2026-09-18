package com.payroll.modules.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollComponentRepository extends JpaRepository<PayrollComponent, Long> {

    List<PayrollComponent> findAllByIsActiveTrueOrderByTypeAscNameAsc();

    /**
     * Fetch components that match the given criteria using AND logic.
     * A null/empty filter field on the component means "applies to all".
     */
    @Query("""
        SELECT p FROM PayrollComponent p
        WHERE p.isActive = true
          AND (p.applyToDivision IS NULL OR p.applyToDivision = '' OR UPPER(p.applyToDivision) = UPPER(:division))
          AND (p.applyToPosition IS NULL OR p.applyToPosition = '' OR UPPER(p.applyToPosition) = UPPER(:position))
          AND (p.applyToUnitName IS NULL OR p.applyToUnitName = '' OR UPPER(p.applyToUnitName) = UPPER(:unitName))
          AND (p.applyToEmployeeType IS NULL OR p.applyToEmployeeType = '' OR UPPER(p.applyToEmployeeType) = UPPER(:employeeType))
        ORDER BY p.type ASC, p.name ASC
    """)
    List<PayrollComponent> findMatchingComponents(
            @Param("division") String division,
            @Param("position") String position,
            @Param("unitName") String unitName,
            @Param("employeeType") String employeeType
    );
}
