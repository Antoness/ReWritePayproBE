package com.payroll.modules.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MasterEmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByNik(String nik);

    // TODO: Review logic for status, currently mapped to isActive
    boolean existsByIsActive(Boolean isActive);
    
    List<Employee> findByIsActive(Boolean isActive);

    @Query(value = "SELECT DISTINCT division FROM employees WHERE division IS NOT NULL AND division != '' ORDER BY division ASC", nativeQuery = true)
    List<String> findUniqueDivisions();

    @Query(value = "SELECT DISTINCT unit_name FROM employees WHERE unit_name IS NOT NULL AND unit_name != '' ORDER BY unit_name ASC", nativeQuery = true)
    List<String> findUniqueUnits();

    @Query(value = "SELECT DISTINCT position FROM employees WHERE position IS NOT NULL AND position != '' ORDER BY position ASC", nativeQuery = true)
    List<String> findUniquePositions();

    @Query(value = "SELECT DISTINCT employee_type FROM employees WHERE employee_type IS NOT NULL AND employee_type != '' ORDER BY employee_type ASC", nativeQuery = true)
    List<String> findUniqueEmployeeTypes();

    @Query(value = "SELECT DISTINCT branch_code FROM employees WHERE branch_code IS NOT NULL AND branch_code != '' ORDER BY branch_code ASC", nativeQuery = true)
    List<String> findUniqueBranches();

    @Query(value = "SELECT DISTINCT CASE WHEN is_active = true THEN 'ACTIVE' ELSE 'INACTIVE' END FROM employees", nativeQuery = true)
    List<String> findUniqueStatuses();

    @Query(value = "SELECT DISTINCT employee_category FROM employees WHERE employee_category IS NOT NULL AND employee_category != '' ORDER BY employee_category ASC", nativeQuery = true)
    List<String> findUniqueNationalities();

    @Query(value = "SELECT DISTINCT division, unit_name AS unit, position, employee_type AS employeeType, branch_code AS branch FROM employees WHERE is_active = true", nativeQuery = true)
    List<java.util.Map<String, Object>> findDropdownCombinations();
}
