package com.payroll.modules.employee;

import com.payroll.modules.employee.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MasterEmployeeService {

    private final EntityManager entityManager;
    private final MasterEmployeeRepository employeeRepository;
    private final TkuPajakRepository tkuRepository;
    private final com.payroll.modules.master.MasterNegaraService masterNegaraService;
    private final com.payroll.modules.audit.AuditLogRepository auditLogRepository;
    private final com.fasterxml.jackson.databind.ObjectMapper objectMapper;

    /**
     * Optimized search using dynamic native query building
     */
    public Page<EmployeeResponse> findEmployeeData(
            String search, String division, String unit, String position,
            String employeeType, String branch, String statusEmployee, String nationality,
            int page, int size
    ) {
        StringBuilder sql = new StringBuilder();
        StringBuilder countSql = new StringBuilder();

        String selectFields = "SELECT " +
                "  a.id as id, " +
                "  NULL as idJson, " +
                "  a.nik as nik, " +
                "  a.full_name as name, " +
                "  a.id_number as noKtp, " +
                "  CASE " +
                "    WHEN a.nik = tp.nik THEN tp.id_tku " +
                "    ELSE CONCAT(a.id_number, '000000') " +
                "  END as idTku, " +
                "  a.employee_type as employeeType, " +
                "  NULL as department, " +
                "  a.division as division, " +
                "  a.unit_name as unit, " +
                "  a.position as position, " +
                "  a.branch_code as branch, " +
                "  NULL as joinDate, " +
                "  NULL as resignDate, " +
                "  CASE WHEN a.is_active = true THEN 'ACTIVE' ELSE 'INACTIVE' END as statusEmployee, " +
                "  a.nationality as nationality, " +
                "  NULL as numberOfContract, " +
                "  tp.metode_pajak as metodePajak, " +
                "  tp.komponen_project as komponenProject ";

        sql.append(selectFields)
           .append("FROM employees a ")
           .append("LEFT JOIN tku_pajak tp ON tp.nik = a.nik ")
           .append("WHERE 1=1 ");

        countSql.append("SELECT COUNT(*) FROM employees a ")
                .append("LEFT JOIN tku_pajak tp ON tp.nik = a.nik ")
                .append("WHERE 1=1 ");

        Map<String, Object> params = new HashMap<>();

        if (search != null && !search.trim().isEmpty()) {
            String searchPattern = "%" + search.trim() + "%";
            String searchFilter = " AND (a.nik LIKE :search OR a.full_name LIKE :search OR a.employee_type LIKE :search " +
                    "OR a.id_number LIKE :search OR a.division LIKE :search OR a.unit_name LIKE :search " +
                    "OR a.position LIKE :search OR a.branch_code LIKE :search) ";
            sql.append(searchFilter);
            countSql.append(searchFilter);
            params.put("search", searchPattern);
        }

        if (division != null && !division.trim().isEmpty()) {
            sql.append(" AND a.division = :division ");
            countSql.append(" AND a.division = :division ");
            params.put("division", division.trim());
        }

        if (unit != null && !unit.trim().isEmpty()) {
            sql.append(" AND a.unit_name = :unit ");
            countSql.append(" AND a.unit_name = :unit ");
            params.put("unit", unit.trim());
        }

        if (position != null && !position.trim().isEmpty()) {
            sql.append(" AND a.position = :position ");
            countSql.append(" AND a.position = :position ");
            params.put("position", position.trim());
        }

        if (employeeType != null && !employeeType.trim().isEmpty()) {
            sql.append(" AND a.employee_type = :employeeType ");
            countSql.append(" AND a.employee_type = :employeeType ");
            params.put("employeeType", employeeType.trim());
        }

        if (branch != null && !branch.trim().isEmpty()) {
            sql.append(" AND a.branch_code = :branch ");
            countSql.append(" AND a.branch_code = :branch ");
            params.put("branch", branch.trim());
        }

        if (statusEmployee != null && !statusEmployee.trim().isEmpty()) {
            boolean isActive = "ACTIVE".equalsIgnoreCase(statusEmployee.trim());
            sql.append(" AND a.is_active = :isActive ");
            countSql.append(" AND a.is_active = :isActive ");
            params.put("isActive", isActive);
        }

        if (nationality != null && !nationality.trim().isEmpty()) {
            sql.append(" AND a.employee_category = :nationality ");
            countSql.append(" AND a.employee_category = :nationality ");
            params.put("nationality", nationality.trim());
        }

        sql.append(" ORDER BY a.nik ASC ");

        // Execute count
        Query countQuery = entityManager.createNativeQuery(countSql.toString());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            countQuery.setParameter(entry.getKey(), entry.getValue());
        }
        long totalElements = ((Number) countQuery.getSingleResult()).longValue();

        // Execute paginated query
        sql.append(" LIMIT :limit OFFSET :offset ");
        Query query = entityManager.createNativeQuery(sql.toString());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        query.setParameter("limit", size);
        query.setParameter("offset", page * size);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();
        List<EmployeeResponse> content = new ArrayList<>();

        for (Object[] row : results) {
            EmployeeResponse emp = new EmployeeResponse();
            emp.setId(row[0] != null ? ((Number) row[0]).intValue() : null);
            emp.setIdJson(row[1] != null ? ((Number) row[1]).intValue() : null);
            emp.setNik(row[2] != null ? row[2].toString() : null);
            emp.setName(row[3] != null ? row[3].toString() : null);
            emp.setNoKtp(row[4] != null ? row[4].toString() : null);
            emp.setIdTku(row[5] != null ? row[5].toString() : null);
            emp.setEmployeeType(row[6] != null ? row[6].toString() : null);
            emp.setDepartment(row[7] != null ? row[7].toString() : null);
            emp.setDivision(row[8] != null ? row[8].toString() : null);
            emp.setUnit(row[9] != null ? row[9].toString() : null);
            emp.setPosition(row[10] != null ? row[10].toString() : null);
            emp.setBranch(row[11] != null ? row[11].toString() : null);
            emp.setJoinDate(row[12] != null ? row[12].toString() : null);
            emp.setResignDate(row[13] != null ? row[13].toString() : null);
            emp.setStatusEmployee(row[14] != null ? row[14].toString() : null);
            emp.setNationality(row[15] != null ? row[15].toString() : null);
            emp.setNumberOfContract(row[16] != null ? row[16].toString() : null);
            emp.setMetodePajak(row[17] != null ? row[17].toString() : null);
            emp.setKomponenProject(row[18] != null ? row[18].toString() : null);
            content.add(emp);
        }

        Pageable pageable = PageRequest.of(page, size);
        return new PageImpl<>(content, pageable, totalElements);
    }

    @Transactional(readOnly = true)
    public Page<WnaResponse> findWnaData(
            String search, String division, String unit, String position,
            String employeeType, String branch, int page, int size, String userPosition
    ) {
        boolean isSupervisor = userPosition != null && (
            userPosition.toUpperCase().contains("SPV") ||
            userPosition.toUpperCase().contains("SUPERVISOR") ||
            userPosition.toUpperCase().contains("IT")
        );

        StringBuilder queryStr;
        StringBuilder countQueryStr;

        if (isSupervisor) {
            queryStr = new StringBuilder(
                "SELECT a.id, a.nik, a.full_name, a.id_number, a.employee_type, a.division, a.unit_name, a.position, a.branch_code, " +
                "NULL as join_date, NULL as resign_date, " +
                "CASE WHEN a.is_active = true THEN 'ACTIVE' ELSE 'INACTIVE' END as status_employee, a.employee_category, a.tgl_izin_kerja, " +
                "a.passport_number, a.kitas_number, a.kode_negara, a.status, a.created_by, a.created_date " +
                "FROM employees a WHERE a.employee_category='WNA' "
            );
            countQueryStr = new StringBuilder(
                "SELECT COUNT(*) FROM employees a WHERE a.employee_category='WNA' "
            );
        } else {
            queryStr = new StringBuilder(
                "SELECT a.id, a.nik, a.full_name, a.id_number, a.employee_type, a.division, a.unit_name, a.position, a.branch_code, " +
                "NULL as join_date, NULL as resign_date, " +
                "CASE WHEN a.is_active = true THEN 'ACTIVE' ELSE 'INACTIVE' END as status_employee, a.employee_category, a.tgl_izin_kerja, " +
                "a.passport_number, a.kitas_number, a.kode_negara, a.status, a.created_by, " +
                "a.created_date " +
                "FROM employees a WHERE a.employee_category='WNA' "
            );
            countQueryStr = new StringBuilder(
                "SELECT COUNT(*) FROM employees a WHERE a.employee_category='WNA' "
            );
        }

        if (search != null && !search.isEmpty()) {
            String searchCondition = "AND (a.nik LIKE :search OR a.full_name LIKE :search) ";
            queryStr.append(searchCondition);
            countQueryStr.append(searchCondition);
        }
        if (division != null && !division.isEmpty()) {
            queryStr.append("AND a.division = :division ");
            countQueryStr.append("AND a.division = :division ");
        }
        if (unit != null && !unit.isEmpty()) {
            queryStr.append("AND a.unit_name = :unit ");
            countQueryStr.append("AND a.unit_name = :unit ");
        }
        if (position != null && !position.isEmpty()) {
            queryStr.append("AND a.position = :position ");
            countQueryStr.append("AND a.position = :position ");
        }
        if (employeeType != null && !employeeType.isEmpty()) {
            queryStr.append("AND a.employee_type = :employeeType ");
            countQueryStr.append("AND a.employee_type = :employeeType ");
        }
        if (branch != null && !branch.isEmpty()) {
            queryStr.append("AND a.branch_code = :branch ");
            countQueryStr.append("AND a.branch_code = :branch ");
        }

        Query query = entityManager.createNativeQuery(queryStr.toString());
        Query countQuery = entityManager.createNativeQuery(countQueryStr.toString());

        if (search != null && !search.isEmpty()) {
            query.setParameter("search", "%" + search + "%");
            countQuery.setParameter("search", "%" + search + "%");
        }
        if (division != null && !division.isEmpty()) {
            query.setParameter("division", division);
            countQuery.setParameter("division", division);
        }
        if (unit != null && !unit.isEmpty()) {
            query.setParameter("unit", unit);
            countQuery.setParameter("unit", unit);
        }
        if (position != null && !position.isEmpty()) {
            query.setParameter("position", position);
            countQuery.setParameter("position", position);
        }
        if (employeeType != null && !employeeType.isEmpty()) {
            query.setParameter("employeeType", employeeType);
            countQuery.setParameter("employeeType", employeeType);
        }
        if (branch != null && !branch.isEmpty()) {
            query.setParameter("branch", branch);
            countQuery.setParameter("branch", branch);
        }

        long totalElements = ((Number) countQuery.getSingleResult()).longValue();

        query.setFirstResult(page * size);
        query.setMaxResults(size);

        List<Object[]> results = query.getResultList();
        List<WnaResponse> content = new ArrayList<>();

        for (Object[] row : results) {
            WnaResponse wna = new WnaResponse();
            wna.setId(row[0] != null ? ((Number) row[0]).intValue() : null);
            wna.setNik(row[1] != null ? row[1].toString() : null);
            wna.setName(row[2] != null ? row[2].toString() : null);
            wna.setNoKtp(row[3] != null ? row[3].toString() : null);
            wna.setEmployeeType(row[4] != null ? row[4].toString() : null);
            wna.setDivision(row[5] != null ? row[5].toString() : null);
            wna.setUnit(row[6] != null ? row[6].toString() : null);
            wna.setPosition(row[7] != null ? row[7].toString() : null);
            wna.setBranch(row[8] != null ? row[8].toString() : null);
            wna.setJoinDate(row[9] != null ? row[9].toString() : null);
            wna.setResignDate(row[10] != null ? row[10].toString() : null);
            wna.setStatusEmployee(row[11] != null ? row[11].toString() : null);
            wna.setNationality(row[12] != null ? row[12].toString() : null);
            wna.setTglIzinKerja(row[13] != null ? row[13].toString() : null);
            wna.setPassportNumber(row[14] != null ? row[14].toString() : null);
            wna.setKitasNumber(row[15] != null ? row[15].toString() : null);
            wna.setKodeNegara(row[16] != null ? row[16].toString() : null);
            wna.setStatus(row[17] != null ? row[17].toString() : null);
            wna.setCreatedBy(row[18] != null ? row[18].toString() : null);
            wna.setCreatedDate(row[19] != null ? row[19].toString() : null);
            content.add(wna);
        }

        Pageable pageable = PageRequest.of(page, size);
        return new PageImpl<>(content, pageable, totalElements);
    }

    @Transactional(readOnly = true)
    public Page<EmployeeResponse> findTaxData(
            String search, String division, String unit, String position,
            String employeeType, String branch, String statusEmployee, int page, int size
    ) {
        StringBuilder queryStr = new StringBuilder();
        StringBuilder countQueryStr = new StringBuilder();

        String selectFields = "SELECT " +
                "  a.id as id, " +
                "  NULL as idJson, " +
                "  a.nik as nik, " +
                "  a.full_name as name, " +
                "  a.id_number as noKtp, " +
                "  tp.id_tku as idTku, " +
                "  a.employee_type as employeeType, " +
                "  NULL as department, " +
                "  a.division as division, " +
                "  a.unit_name as unit, " +
                "  a.position as position, " +
                "  a.branch_code as branch, " +
                "  NULL as joinDate, " +
                "  NULL as resignDate, " +
                "  CASE WHEN a.is_active = true THEN 'ACTIVE' ELSE 'INACTIVE' END as statusEmployee, " +
                "  a.nationality as nationality, " +
                "  NULL as numberOfContract, " +
                "  a.metode_pajak as metodePajak, " +
                "  a.komponen_project as komponenProject, " +
                "  a.status_pajak as statusPajak, " +
                "  a.created_by_pajak as createdByPajak, " +
                "  a.created_date_pajak as createdDatePajak ";

        queryStr.append(selectFields)
                .append("FROM employees a ")
                .append("LEFT JOIN tku_pajak tp ON tp.nik = a.nik ")
                .append("WHERE 1=1 ");

        countQueryStr.append("SELECT COUNT(a.id) FROM employees a ")
                .append("LEFT JOIN tku_pajak tp ON tp.nik = a.nik ")
                .append("WHERE 1=1 ");

        if (search != null && !search.trim().isEmpty()) {
            queryStr.append("AND (a.nik LIKE :search OR a.full_name LIKE :search) ");
            countQueryStr.append("AND (a.nik LIKE :search OR a.full_name LIKE :search) ");
        }
        if (division != null && !division.trim().isEmpty()) {
            queryStr.append("AND a.division = :division ");
            countQueryStr.append("AND a.division = :division ");
        }
        if (unit != null && !unit.trim().isEmpty()) {
            queryStr.append("AND a.unit_name = :unit ");
            countQueryStr.append("AND a.unit_name = :unit ");
        }
        if (position != null && !position.trim().isEmpty()) {
            queryStr.append("AND a.position = :position ");
            countQueryStr.append("AND a.position = :position ");
        }
        if (employeeType != null && !employeeType.trim().isEmpty()) {
            queryStr.append("AND a.employee_type = :employeeType ");
            countQueryStr.append("AND a.employee_type = :employeeType ");
        }
        if (branch != null && !branch.trim().isEmpty()) {
            queryStr.append("AND a.branch_code = :branch ");
            countQueryStr.append("AND a.branch_code = :branch ");
        }
        if (statusEmployee != null && !statusEmployee.trim().isEmpty()) {
            if ("ACTIVE".equalsIgnoreCase(statusEmployee.trim())) {
                queryStr.append("AND a.is_active = true ");
                countQueryStr.append("AND a.is_active = true ");
            } else {
                queryStr.append("AND a.is_active = false ");
                countQueryStr.append("AND a.is_active = false ");
            }
        }

        queryStr.append("ORDER BY a.status_pajak DESC, a.nik ASC ");

        Query query = entityManager.createNativeQuery(queryStr.toString());
        Query countQuery = entityManager.createNativeQuery(countQueryStr.toString());

        if (search != null && !search.trim().isEmpty()) {
            query.setParameter("search", "%" + search.trim() + "%");
            countQuery.setParameter("search", "%" + search.trim() + "%");
        }
        if (division != null && !division.trim().isEmpty()) {
            query.setParameter("division", division.trim());
            countQuery.setParameter("division", division.trim());
        }
        if (unit != null && !unit.trim().isEmpty()) {
            query.setParameter("unit", unit.trim());
            countQuery.setParameter("unit", unit.trim());
        }
        if (position != null && !position.trim().isEmpty()) {
            query.setParameter("position", position.trim());
            countQuery.setParameter("position", position.trim());
        }
        if (employeeType != null && !employeeType.trim().isEmpty()) {
            query.setParameter("employeeType", employeeType.trim());
            countQuery.setParameter("employeeType", employeeType.trim());
        }
        if (branch != null && !branch.trim().isEmpty()) {
            query.setParameter("branch", branch.trim());
            countQuery.setParameter("branch", branch.trim());
        }

        long totalElements = ((Number) countQuery.getSingleResult()).longValue();

        query.setFirstResult(page * size);
        query.setMaxResults(size);

        List<Object[]> results = query.getResultList();
        List<EmployeeResponse> content = new ArrayList<>();

        for (Object[] row : results) {
            EmployeeResponse emp = new EmployeeResponse();
            emp.setId(row[0] != null ? ((Number) row[0]).intValue() : null);
            emp.setIdJson(row[1] != null ? ((Number) row[1]).intValue() : null);
            emp.setNik(row[2] != null ? row[2].toString() : null);
            emp.setName(row[3] != null ? row[3].toString() : null);
            emp.setNoKtp(row[4] != null ? row[4].toString() : null);
            emp.setIdTku(row[5] != null ? row[5].toString() : null);
            emp.setEmployeeType(row[6] != null ? row[6].toString() : null);
            emp.setDepartment(row[7] != null ? row[7].toString() : null);
            emp.setDivision(row[8] != null ? row[8].toString() : null);
            emp.setUnit(row[9] != null ? row[9].toString() : null);
            emp.setPosition(row[10] != null ? row[10].toString() : null);
            emp.setBranch(row[11] != null ? row[11].toString() : null);
            emp.setJoinDate(row[12] != null ? row[12].toString() : null);
            emp.setResignDate(row[13] != null ? row[13].toString() : null);
            emp.setStatusEmployee(row[14] != null ? row[14].toString() : null);
            emp.setNationality(row[15] != null ? row[15].toString() : null);
            emp.setNumberOfContract(row[16] != null ? row[16].toString() : null);
            emp.setMetodePajak(row[17] != null ? row[17].toString() : null);
            emp.setKomponenProject(row[18] != null ? row[18].toString() : null);
            emp.setStatus(row[19] != null ? row[19].toString() : null);
            emp.setCreatedBy(row[20] != null ? row[20].toString() : null);
            emp.setCreatedDate(row[21] != null ? row[21].toString() : null);
            content.add(emp);
        }

        Pageable pageable = PageRequest.of(page, size);
        return new PageImpl<>(content, pageable, totalElements);
    }

    @Transactional(readOnly = true)
    public Page<TaxHistoryResponse> findTaxHistory(String search, int page, int size) {
        StringBuilder queryStr = new StringBuilder();
        StringBuilder countQueryStr = new StringBuilder();

        String selectFields = "SELECT " +
                "  e.nik as nik, " +
                "  e.full_name as name, " +
                "  e.id_number as noKtp, " +
                "  e.employee_type as employeeType, " +
                "  e.division as division, " +
                "  e.unit_name as unit, " +
                "  e.position as position, " +
                "  e.branch_code as branch, " +
                "  e.metode_pajak as metodePajak, " +
                "  e.komponen_project as komponenProject, " +
                "  a.action as status, " +
                "  a.created_by as createdBy, " +
                "  a.created_date as createdDate ";

        queryStr.append(selectFields)
                .append("FROM audit_logs a ")
                .append("JOIN employees e ON a.reference_id = e.nik ")
                .append("WHERE a.module = 'TAX_CONFIG' ");

        countQueryStr.append("SELECT COUNT(a.id) FROM audit_logs a ")
                .append("JOIN employees e ON a.reference_id = e.nik ")
                .append("WHERE a.module = 'TAX_CONFIG' ");

        if (search != null && !search.trim().isEmpty()) {
            queryStr.append("AND (e.nik LIKE :search OR e.full_name LIKE :search) ");
            countQueryStr.append("AND (e.nik LIKE :search OR e.full_name LIKE :search) ");
        }

        queryStr.append("ORDER BY a.created_date DESC ");

        Query query = entityManager.createNativeQuery(queryStr.toString());
        Query countQuery = entityManager.createNativeQuery(countQueryStr.toString());

        if (search != null && !search.trim().isEmpty()) {
            query.setParameter("search", "%" + search.trim() + "%");
            countQuery.setParameter("search", "%" + search.trim() + "%");
        }

        long totalElements = ((Number) countQuery.getSingleResult()).longValue();

        query.setFirstResult(page * size);
        query.setMaxResults(size);

        List<Object[]> results = query.getResultList();
        List<TaxHistoryResponse> content = new ArrayList<>();

        for (Object[] row : results) {
            TaxHistoryResponse th = new TaxHistoryResponse();
            th.setNik(row[0] != null ? row[0].toString() : null);
            th.setName(row[1] != null ? row[1].toString() : null);
            th.setNoKtp(row[2] != null ? row[2].toString() : null);
            th.setEmployeeType(row[3] != null ? row[3].toString() : null);
            th.setDivision(row[4] != null ? row[4].toString() : null);
            th.setUnit(row[5] != null ? row[5].toString() : null);
            th.setPosition(row[6] != null ? row[6].toString() : null);
            th.setBranch(row[7] != null ? row[7].toString() : null);
            th.setMetodePajak(row[8] != null ? row[8].toString() : null);
            th.setKomponenProject(row[9] != null ? row[9].toString() : null);
            th.setStatus(row[10] != null ? row[10].toString() : null);
            th.setCreatedBy(row[11] != null ? row[11].toString() : null);
            th.setCreatedDate(row[12] != null ? row[12].toString() : null);
            content.add(th);
        }

        Pageable pageable = PageRequest.of(page, size);
        return new PageImpl<>(content, pageable, totalElements);
    }

    @Transactional
    public void requestTax(List<String> niks, String metodePajak, String komponenProject, String operator) {
        if (niks == null || niks.isEmpty()) return;
        try {
            Map<String, String> newValuesMap = new HashMap<>();
            newValuesMap.put("metodePajak", metodePajak);
            newValuesMap.put("komponenProject", komponenProject);
            String newValuesJson = objectMapper.writeValueAsString(newValuesMap);
            
            for (String nik : niks) {
                com.payroll.modules.audit.AuditLog log = com.payroll.modules.audit.AuditLog.builder()
                        .module("TAX_CONFIG")
                        .referenceId(nik)
                        .action("REQUEST")
                        .description("Tax configuration request")
                        .newValues(newValuesJson)
                        .createdBy(operator)
                        .createdDate(java.time.LocalDateTime.now())
                        .build();
                auditLogRepository.save(log);
            }
            
            entityManager.createNativeQuery(
                    "UPDATE employees SET metode_pajak = :metode, komponen_project = :komponen, status_pajak = 'REQUEST', created_by_pajak = :operator, created_date_pajak = NOW() WHERE nik IN (:niks)"
            )
            .setParameter("metode", metodePajak)
            .setParameter("komponen", komponenProject)
            .setParameter("operator", operator)
            .setParameter("niks", niks)
            .executeUpdate();
        } catch(Exception e) {
            log.error("Failed to request tax", e);
            throw new RuntimeException("Failed to request tax");
        }
    }

    @Transactional
    public void approveTax(List<String> niks, String operator) {
        if (niks == null || niks.isEmpty()) return;
        try {
            for (String nik : niks) {
                com.payroll.modules.audit.AuditLog log = com.payroll.modules.audit.AuditLog.builder()
                        .module("TAX_CONFIG")
                        .referenceId(nik)
                        .action("APPROVED")
                        .description("Tax configuration approved")
                        .createdBy(operator)
                        .createdDate(java.time.LocalDateTime.now())
                        .build();
                auditLogRepository.save(log);
            }
            entityManager.createNativeQuery(
                    "UPDATE employees SET status_pajak = 'APPROVED', created_by_pajak = :operator, created_date_pajak = NOW() WHERE nik IN (:niks)"
            )
            .setParameter("operator", operator)
            .setParameter("niks", niks)
            .executeUpdate();
        } catch(Exception e) {
            log.error("Failed to approve tax", e);
            throw new RuntimeException("Failed to approve tax");
        }
    }

    @Transactional
    public void rejectTax(List<String> niks, String operator) {
        if (niks == null || niks.isEmpty()) return;
        try {
            for (String nik : niks) {
                com.payroll.modules.audit.AuditLog log = com.payroll.modules.audit.AuditLog.builder()
                        .module("TAX_CONFIG")
                        .referenceId(nik)
                        .action("REJECTED")
                        .description("Tax configuration rejected")
                        .createdBy(operator)
                        .createdDate(java.time.LocalDateTime.now())
                        .build();
                auditLogRepository.save(log);
            }
            entityManager.createNativeQuery(
                    "UPDATE employees SET status_pajak = 'REJECTED', metode_pajak = NULL, komponen_project = NULL, created_by_pajak = :operator, created_date_pajak = NOW() WHERE nik IN (:niks)"
            )
            .setParameter("operator", operator)
            .setParameter("niks", niks)
            .executeUpdate();
        } catch(Exception e) {
            log.error("Failed to reject tax", e);
            throw new RuntimeException("Failed to reject tax");
        }
    }

    /**
     * Get distinct dropdown values for master employee search filters
     */
    public Map<String, Object> getDropdowns() {
        Map<String, Object> dropdowns = new HashMap<>();
        // Legacy flat lists for backwards compatibility during transition
        dropdowns.put("divisions", employeeRepository.findUniqueDivisions());
        dropdowns.put("units", employeeRepository.findUniqueUnits());
        dropdowns.put("positions", employeeRepository.findUniquePositions());
        dropdowns.put("employeeTypes", employeeRepository.findUniqueEmployeeTypes());
        dropdowns.put("branches", employeeRepository.findUniqueBranches());
        // Fetch master_negara via its Service complying with PAYPRO_RULES (Domain-Driven boundaries)
        dropdowns.put("negara", masterNegaraService.getKodeAndAsalNegara());
        
        // Independent dropdowns
        dropdowns.put("statuses", employeeRepository.findUniqueStatuses());
        dropdowns.put("nationalities", employeeRepository.findUniqueNationalities());
        
        // New combinations list for cascading filters
        dropdowns.put("combinations", employeeRepository.findDropdownCombinations());
        
        return dropdowns;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getLastUpdate() {
        try {
            String sql = "SELECT TO_CHAR(created_at, 'DD-Mon-YYYY HH24:MI:SS') " +
                         "FROM audit_logs WHERE action_type = 'SYNC_HRIS' " +
                         "ORDER BY created_at DESC LIMIT 1";
            Query query = entityManager.createNativeQuery(sql);
            String lastUpdateStr = (String) query.getSingleResult();
            if (lastUpdateStr != null) {
                return Map.of("success", true, "lastUpdate", "*Tanggal Update: " + lastUpdateStr);
            }
        } catch (Exception e) {
            log.warn("Could not fetch last update from audit_logs: " + e.getMessage());
        }
        return Map.of("success", true, "lastUpdate", "*Tanggal Update: -");
    }

    /**
     * Get pending request data
     */
    public List<Employee> getRequestDataList() {
        return java.util.Collections.emptyList(); // TODO: implement if needed
    }

    @Transactional
    public EmployeeResponse updateEmployee(String nik, EmployeeUpdateRequest request) {
        Employee employee = employeeRepository.findByNik(nik)
                .orElseThrow(() -> new RuntimeException("Employee not found with NIK: " + nik));

        if (request.getName() != null) employee.setFullName(request.getName());
        if (request.getNoKtp() != null) employee.setIdNumber(request.getNoKtp());
        if (request.getDivision() != null) employee.setDivision(request.getDivision());
        if (request.getUnit() != null) employee.setUnitName(request.getUnit());
        if (request.getPosition() != null) employee.setPosition(request.getPosition());
        if (request.getBranch() != null) employee.setBranchCode(request.getBranch());
        if (request.getNationality() != null) employee.setNationality(request.getNationality());
        // Department is not in Employee yet, skip for now.

        employeeRepository.save(employee);

        TkuPajak tku = tkuRepository.findByNik(nik).orElse(new TkuPajak());
        tku.setNik(nik);
        if (request.getNoKtp() != null) tku.setIdNumber(request.getNoKtp());
        if (request.getIdTku() != null) tku.setIdTku(request.getIdTku());
        if (request.getMetodePajak() != null) tku.setMetodePajak(request.getMetodePajak());
        if (request.getKomponenProject() != null) tku.setKomponenProject(request.getKomponenProject());
        
        tkuRepository.save(tku);

        return EmployeeResponse.builder()
                .nik(nik)
                .name(employee.getFullName())
                .idTku(tku.getIdTku())
                .build();
    }

    /**
     * Mock HRIS synchronization
     */
    public void syncHris() {
        log.info("Starting HRIS employee list synchronization...");
        // Mock success
    }

    /**
     * Preview Excel Upload without inserting
     */
    public Map<String, Object> previewUpload(MultipartFile file, String type) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, String>> data = new ArrayList<>();

        try (InputStream is = file.getInputStream(); Workbook workbook = WorkbookFactory.create(is)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= Math.min(sheet.getLastRowNum(), 100); i++) { // Limit preview to 100 rows
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String nik = getCellStringValue(row.getCell(0));
                if (nik.isEmpty()) continue;

                Map<String, String> rowData = new HashMap<>();
                rowData.put("nik", nik);

                if ("NPWP".equalsIgnoreCase(type)) {
                    Cell dateCell = row.getCell(1);
                    LocalDateTime regDate = parseDateCell(dateCell);
                    String dateStr = regDate != null ? regDate.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) : getCellStringValue(dateCell);
                    rowData.put("tglNpwp", dateStr);
                } else if ("WNA".equalsIgnoreCase(type)) {
                    // Column order: NIK | Passport Number | Kitas Number | Tanggal Izin Kerja | Kode Negara
                    String passportNumber = getCellStringValue(row.getCell(1));
                    String kitasNumber = getCellStringValue(row.getCell(2));
                    Cell tglCell = row.getCell(3);
                    LocalDateTime tglIzin = parseDateCell(tglCell);
                    String tglIzinStr = tglIzin != null ? tglIzin.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) : getCellStringValue(tglCell);
                    String kodeNegara = getCellStringValue(row.getCell(4));
                    rowData.put("passportNumber", passportNumber);
                    rowData.put("kitasNumber", kitasNumber);
                    rowData.put("tglIzinKerja", tglIzinStr);
                    rowData.put("kodeNegara", kodeNegara);
                } else {
                    String idNumber = getCellStringValue(row.getCell(1));
                    String idTku = getCellStringValue(row.getCell(2));
                    rowData.put("idNumber", idNumber);
                    rowData.put("idTku", idTku);
                }
                data.add(rowData);
            }
            result.put("success", true);
            result.put("data", data);
        } catch (Exception e) {
            log.error("Error previewing file", e);
            result.put("success", false);
            result.put("message", "Error membaca file: " + e.getMessage());
        }
        return result;
    }

    /**
     * Process NPWP upload Excel
     */
    @Transactional
    public Map<String, Object> uploadNpwp(MultipartFile file, String operator) {
        Map<String, Object> result = new HashMap<>();
        int successCount = 0;
        int failCount = 0;
        List<String> errors = new ArrayList<>();

        try (InputStream is = file.getInputStream(); Workbook workbook = WorkbookFactory.create(is)) {
            Sheet sheet = workbook.getSheetAt(0);

            int emptyRowCount = 0;
            // Row 0 is header: NIK | Tgl NPWP Terdaftar
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    emptyRowCount++;
                    if (emptyRowCount > 10) break;
                    continue;
                }

                String nik = getCellStringValue(row.getCell(0));
                Cell dateCell = row.getCell(1);

                if (nik.isEmpty()) {
                    emptyRowCount++;
                    if (emptyRowCount > 10) break;
                    continue;
                }
                emptyRowCount = 0;

                LocalDateTime regDate = parseDateCell(dateCell);
                if (regDate == null) {
                    failCount++;
                    errors.add("Baris " + (i + 1) + ": Format tanggal salah atau kosong");
                    continue;
                }

                Optional<Employee> empOpt = employeeRepository.findByNik(nik);
                if (empOpt.isPresent()) {
                    Employee emp = empOpt.get();
                    // emp.setCreatedDatePajak(regDate);
                    // emp.setStatusPajak("APPROVED");
                    // emp.setCreatedByPajak(operator);
                    employeeRepository.save(emp);
                    successCount++;
                } else {
                    failCount++;
                    errors.add("Baris " + (i + 1) + ": Karyawan dengan NIK " + nik + " tidak ditemukan");
                }
            }

            result.put("success", true);
            result.put("message", "Proses NPWP selesai. Berhasil: " + successCount + ", Gagal: " + failCount);
            if (!errors.isEmpty()) {
                result.put("errors", errors);
            }
        } catch (Exception e) {
            log.error("Error processing NPWP upload", e);
            result.put("success", false);
            result.put("message", "Error membaca file: " + e.getMessage());
        }
        return result;
    }

    /**
     * Process ID TKU upload Excel
     */
    @Transactional
    public Map<String, Object> uploadTku(MultipartFile file, String operator) {
        Map<String, Object> result = new HashMap<>();
        int successCount = 0;
        int failCount = 0;
        List<String> errors = new ArrayList<>();

        try (InputStream is = file.getInputStream(); Workbook workbook = WorkbookFactory.create(is)) {
            Sheet sheet = workbook.getSheetAt(0);

            int emptyRowCount = 0;
            // Row 0 is header: NIK | ID Number | ID TKU
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    emptyRowCount++;
                    if (emptyRowCount > 10) break;
                    continue;
                }

                String nik = getCellStringValue(row.getCell(0));
                String idNumber = getCellStringValue(row.getCell(1));
                String idTku = getCellStringValue(row.getCell(2));

                if (nik.isEmpty()) {
                    emptyRowCount++;
                    if (emptyRowCount > 10) break;
                    continue;
                }
                emptyRowCount = 0;

                if (idTku.length() != 22) {
                    failCount++;
                    errors.add("Baris " + (i + 1) + ": ID TKU harus 22 digit (" + idTku + ")");
                    continue;
                }

                Optional<TkuPajak> tkuOpt = tkuRepository.findByNik(nik);
                TkuPajak tku;
                if (tkuOpt.isPresent()) {
                    tku = tkuOpt.get();
                } else {
                    tku = new TkuPajak();
                    tku.setNik(nik);
                }
                tku.setIdNumber(idNumber);
                tku.setIdTku(idTku);
                tkuRepository.save(tku);
                successCount++;
            }

            result.put("success", true);
            result.put("message", "Proses ID TKU selesai. Berhasil: " + successCount + ", Gagal: " + failCount);
            if (!errors.isEmpty()) {
                result.put("errors", errors);
            }
        } catch (Exception e) {
            log.error("Error processing ID TKU upload", e);
            result.put("success", false);
            result.put("message", "Error membaca file: " + e.getMessage());
        }
        return result;
    }

    @Transactional
    public Map<String, Object> uploadWna(MultipartFile file, String operator) {
        Map<String, Object> result = new HashMap<>();
        int successCount = 0;
        int failCount = 0;
        List<String> errors = new ArrayList<>();

        try (InputStream is = file.getInputStream(); Workbook workbook = WorkbookFactory.create(is)) {
            Sheet sheet = workbook.getSheetAt(0);

            int emptyRowCount = 0;
            // Column order: NIK | Passport Number | Kitas Number | Tanggal Izin Kerja | Kode Negara
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    emptyRowCount++;
                    if (emptyRowCount > 10) break;
                    continue;
                }

                String nik = getCellStringValue(row.getCell(0));
                if (nik.isEmpty()) {
                    emptyRowCount++;
                    if (emptyRowCount > 10) break;
                    continue;
                }
                emptyRowCount = 0;

                String passportNumber = getCellStringValue(row.getCell(1));
                String kitasNumber = getCellStringValue(row.getCell(2));
                Cell tglCell = row.getCell(3);
                LocalDateTime tglIzin = parseDateCell(tglCell);
                String tglIzinStr = tglIzin != null
                    ? tglIzin.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    : getCellStringValue(tglCell);
                String kodeNegara = getCellStringValue(row.getCell(4));

                // Update employee WNA data using native query
                try {
                    int updated = entityManager.createNativeQuery(
                        "UPDATE employees SET passport_number = :passport, kitas_number = :kitas, " +
                        "tgl_izin_kerja = :tglIzin, kode_negara = :kodeNegara, " +
                        "status = 'INCOMPLETE', " +
                        "created_by = COALESCE(created_by, :operator), " +
                        "created_date = COALESCE(created_date, NOW()), " +
                        "updated_by = :operator, updated_date = NOW() " +
                        "WHERE nik = :nik AND (status IS NULL OR status != 'APPROVED')"
                    )
                    .setParameter("passport", passportNumber)
                    .setParameter("kitas", kitasNumber)
                    .setParameter("tglIzin", tglIzinStr)
                    .setParameter("kodeNegara", kodeNegara)
                    .setParameter("operator", operator)
                    .setParameter("nik", nik)
                    .executeUpdate();

                    if (updated > 0) {
                        successCount++;
                    } else {
                        // Cek apakah karena NIK tidak ada atau sudah APPROVED
                        Optional<Employee> emp = employeeRepository.findByNik(nik);
                        if (emp.isPresent() && "APPROVED".equalsIgnoreCase(emp.get().getStatus())) {
                            failCount++;
                            errors.add("Baris " + (i + 1) + ": NIK " + nik + " sudah APPROVED (Data dikunci)");
                        } else {
                            failCount++;
                            errors.add("Baris " + (i + 1) + ": NIK " + nik + " tidak ditemukan");
                        }
                    }
                } catch (Exception ex) {
                    failCount++;
                    errors.add("Baris " + (i + 1) + ": Error update NIK " + nik + " - " + ex.getMessage());
                }
            }

            result.put("success", true);
            result.put("message", "Proses Data WNA selesai. Berhasil: " + successCount + ", Gagal: " + failCount);
            if (!errors.isEmpty()) {
                result.put("errors", errors);
            }
        } catch (Exception e) {
            log.error("Error processing WNA upload", e);
            result.put("success", false);
            result.put("message", "Error membaca file: " + e.getMessage());
        }
        return result;
    }

    @Transactional
    public WnaResponse updateWnaData(String nik, WnaUpdateRequest request, String operator) {
        Employee employee = employeeRepository.findByNik(nik)
                .orElseThrow(() -> new RuntimeException("Employee not found with NIK: " + nik));

        if ("APPROVED".equalsIgnoreCase(employee.getStatus())) {
            throw new RuntimeException("Data WNA sudah di-Approve dan tidak bisa diubah.");
        }

        if (request.getPassportNumber() != null) employee.setPassportNumber(request.getPassportNumber());
        if (request.getKitasNumber() != null) employee.setKitasNumber(request.getKitasNumber());
        if (request.getTglIzinKerja() != null) employee.setTglIzinKerja(request.getTglIzinKerja());
        
        employee.setUpdatedBy(operator);
        employee.setUpdatedDate(LocalDateTime.now());

        if (Boolean.TRUE.equals(request.getIsApprove())) {
            employee.setStatus("APPROVED");
        } else {
            employee.setStatus("INCOMPLETE");
        }

        if (employee.getCreatedBy() == null) {
            employee.setCreatedBy(operator);
            employee.setCreatedDate(LocalDateTime.now());
        }

        employeeRepository.save(employee);

        return WnaResponse.builder()
                .nik(nik)
                .name(employee.getFullName())
                .status(employee.getStatus())
                .build();
    }

    @Transactional
    public void bulkApproveWna(List<String> niks, String operator) {
        if (niks == null || niks.isEmpty()) return;
        entityManager.createNativeQuery(
                "UPDATE employees SET status = 'APPROVED', updated_by = :operator, updated_date = NOW() WHERE nik IN (:niks)"
        )
        .setParameter("operator", operator)
        .setParameter("niks", niks)
        .executeUpdate();
    }

    @Transactional
    public void bulkRejectWna(List<String> niks, String operator) {
        if (niks == null || niks.isEmpty()) return;
        entityManager.createNativeQuery(
                "UPDATE employees SET status = 'REJECTED', updated_by = :operator, updated_date = NOW() WHERE nik IN (:niks)"
        )
        .setParameter("operator", operator)
        .setParameter("niks", niks)
        .executeUpdate();
    }

    private String getCellStringValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> {
                double d = cell.getNumericCellValue();
                long l = (long) d;
                yield l == d ? String.valueOf(l) : String.valueOf(d);
            }
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }

    private LocalDateTime parseDateCell(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                return cell.getDateCellValue().toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDateTime();
            } else {
                double val = cell.getNumericCellValue();
                java.util.Date date = DateUtil.getJavaDate(val);
                return date.toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDateTime();
            }
        } else if (cell.getCellType() == CellType.STRING) {
            String str = cell.getStringCellValue().trim();
            if (str.isEmpty()) return null;
            try {
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("d/M/yyyy");
                return LocalDate.parse(str, formatter).atStartOfDay();
            } catch (Exception e1) {
                try {
                    java.time.format.DateTimeFormatter formatter2 = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    return LocalDate.parse(str, formatter2).atStartOfDay();
                } catch (Exception e2) {
                    return null;
                }
            }
        }
        return null;
    }
}
