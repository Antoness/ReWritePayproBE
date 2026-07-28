package com.payroll.modules.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterClientDropdownRepository extends JpaRepository<MasterClient, Long> {

    @Query(value = "SELECT division FROM employees WHERE division IS NOT NULL AND division != '' " +
            "AND is_active = true " +
            "GROUP BY division ORDER BY division ASC", nativeQuery = true)
    List<String> getDivisions();

    @Query(value = "SELECT unit_name FROM employees WHERE division = :division " +
            "AND unit_name IS NOT NULL AND unit_name != '' " +
            "AND is_active = true " +
            "GROUP BY unit_name ORDER BY unit_name ASC", nativeQuery = true)
    List<String> getUnits(@Param("division") String division);

    @Query(value = "SELECT position FROM employees WHERE division = :division AND unit_name = :unitName " +
            "AND position IS NOT NULL AND position != '' " +
            "AND is_active = true " +
            "GROUP BY position ORDER BY position ASC", nativeQuery = true)
    List<String> getPositions(@Param("division") String division, @Param("unitName") String unitName);

    @Query(value = "SELECT employee_type FROM employees WHERE division = :division AND unit_name = :unitName AND position = :position " +
            "AND employee_type IS NOT NULL AND employee_type != '' " +
            "AND is_active = true " +
            "GROUP BY employee_type ORDER BY employee_type ASC", nativeQuery = true)
    List<String> getEmployeeTypes(@Param("division") String division, @Param("unitName") String unitName, @Param("position") String position);

    @Query(value = "SELECT branch_code FROM employees WHERE division = :division AND unit_name = :unitName AND position = :position AND employee_type = :employeeType " +
            "AND branch_code IS NOT NULL AND branch_code != '' " +
            "AND is_active = true " +
            "GROUP BY branch_code ORDER BY branch_code ASC", nativeQuery = true)
    List<String> getBranches(@Param("division") String division, @Param("unitName") String unitName, @Param("position") String position, @Param("employeeType") String employeeType);

    // Removed the queries for missing tables
}
