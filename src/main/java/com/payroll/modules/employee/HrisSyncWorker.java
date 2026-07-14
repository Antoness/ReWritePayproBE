package com.payroll.modules.employee;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payroll.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import jakarta.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Slf4j
public class HrisSyncWorker {

    private final MasterEmployeeRepository employeeRepository;
    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void cleanupDuplicates() {
        try {
            String deleteSql = "DELETE FROM master_salary " +
                               "WHERE id IN ( " +
                               "  SELECT id FROM ( " +
                               "    SELECT id, ROW_NUMBER() OVER ( " +
                               "      PARTITION BY UPPER(COALESCE(division, '')), UPPER(COALESCE(unit_name, '')), UPPER(COALESCE(position, '')), UPPER(COALESCE(branch, '')), UPPER(COALESCE(employee_type, '')) " +
                               "      ORDER BY id ASC " +
                               "    ) AS rnum " +
                               "    FROM master_salary " +
                               "  ) t " +
                               "  WHERE t.rnum > 1 " +
                               ")";
            int deleted = jdbcTemplate.update(deleteSql);
            log.info("Cleanup successful: Deleted {} duplicate records from master_salary on startup.", deleted);
        } catch (Exception e) {
            log.warn("Failed to cleanup master_salary duplicates on startup", e);
        }
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_HR_EMPLOYEE_SYNC)
    public void processHrisData(String payload) {
        log.info("Received HRIS Sync Payload");
        try {
            JsonNode rootNode = objectMapper.readTree(payload);
            JsonNode dataArray = rootNode.get("data");
            String syncBy = rootNode.has("sync_by") ? rootNode.get("sync_by").asText() : "SYSTEM";

            if (dataArray != null && dataArray.isArray()) {
                log.info("Processing {} records from HRIS...", dataArray.size());
                
                List<Employee> employeesToSave = new ArrayList<>();
                // Track employees processed in this payload to avoid duplicate key errors
                Map<String, Employee> processedEmployees = new HashMap<>();
                // Map to hold unique combinations for master_salary
                Map<String, MasterSalaryData> uniqueMasterSalaries = new HashMap<>();

                for (JsonNode node : dataArray) {
                    // --- 1. PROCESS CORE EMPLOYEE ---
                    String nik = node.path("NIK").asText(null);
                    if (nik != null && !nik.trim().isEmpty()) {
                        processEmployeeNode(node, nik, employeesToSave, processedEmployees);
                    }

                    // --- 2. GATHER UNIQUE MASTER SALARY DATA ---
                    String division = node.path("Division").asText("");
                    String unit = node.path("Unit").asText("");
                    String position = node.path("Position").asText("");
                    String branch = node.path("Branch").asText("");
                    String employeeType = node.path("Employee_Type").asText("");
                    
                    String key = division + "|" + unit + "|" + position + "|" + branch + "|" + employeeType;
                    
                    Double salary = parseDoubleOrNull(node.path("Salary").asText(null));
                    String bpjsKes = parseStringOrNull(node.path("BPJS_Kesehatan").asText(null));
                    String bpJam = parseStringOrNull(node.path("BP_Jamsostek").asText(null));
                    String bpjsPen = parseStringOrNull(node.path("BPJS_Pensiun").asText(null));
                    String asuransiKes = parseStringOrNull(node.path("Asuransi_Kesehatan").asText(null));
                    String asuransiKec = parseStringOrNull(node.path("Asuransi_Kecelakaan").asText(null));

                    // Aggregate (Take the last non-null value, or MAX logic)
                    if (!uniqueMasterSalaries.containsKey(key)) {
                        uniqueMasterSalaries.put(key, new MasterSalaryData(division, unit, position, branch, employeeType, salary, bpjsKes, bpJam, bpjsPen, asuransiKes, asuransiKec));
                    } else {
                        MasterSalaryData existing = uniqueMasterSalaries.get(key);
                        if (salary != null && salary > 0) existing.salary = salary;
                        if (bpjsKes != null) existing.bpjsKesehatan = bpjsKes;
                        if (bpJam != null) existing.bpJamsostek = bpJam;
                        if (bpjsPen != null) existing.bpjsPensiun = bpjsPen;
                        if (asuransiKes != null) existing.asuransiKesehatan = asuransiKes;
                        if (asuransiKec != null) existing.asuransiKecelakaan = asuransiKec;
                    }
                }

                log.info("Saving {} employees to database...", employeesToSave.size());
                employeeRepository.saveAll(employeesToSave);
                
                log.info("Upserting {} unique components to master_salary with PIC {}...", uniqueMasterSalaries.size(), syncBy);
                processMasterSalaryUpsert(uniqueMasterSalaries.values(), syncBy);

                log.info("HRIS Data synced successfully via RabbitMQ!");
            } else {
                log.warn("Invalid HRIS payload: 'data' array not found");
            }
        } catch (Exception e) {
            log.error("Error processing HRIS Sync payload", e);
        }
    }

    private void processEmployeeNode(JsonNode node, String nik, List<Employee> employeesToSave, Map<String, Employee> processedEmployees) {
        String idNumber = node.path("ID_Number").asText(null);
        String name = node.path("Name").asText(null);
        String employeeType = node.path("Employee_Type").asText(null);
        String position = node.path("Position").asText(null);
        String division = node.path("Division").asText(null);
        String unit = node.path("Unit").asText(null);
        String branch = node.path("Branch").asText(null);
        String nationality = node.path("Nationality").asText(null);
        String status = node.path("Status").asText(null);

        boolean isActive = true;
        if (status != null && (status.equalsIgnoreCase("BATAL JOIN") || status.equalsIgnoreCase("PENDING") || status.equalsIgnoreCase("INACTIVE"))) {
            isActive = false;
        }

        Employee emp;
        if (processedEmployees.containsKey(nik)) {
            emp = processedEmployees.get(nik);
        } else {
            Optional<Employee> existingEmpOpt = employeeRepository.findByNik(nik);
            emp = existingEmpOpt.orElseGet(Employee::new);
            processedEmployees.put(nik, emp);
            employeesToSave.add(emp);
        }

        emp.setNik(nik);
        emp.setIdNumber(idNumber);
        emp.setFullName(name);
        emp.setEmployeeType(employeeType);
        
        if (position != null) {
            emp.setPosition(position.toUpperCase());
        }
        emp.setDivision(division);
        emp.setUnitName(unit);
        emp.setBranchCode(branch);
        emp.setNationality(nationality);
        emp.setIsActive(isActive);
        
        if(emp.getEmployeeCategory() == null) {
            if(nationality != null && nationality.equalsIgnoreCase("WNA")) {
                emp.setEmployeeCategory("WNA");
            } else {
                emp.setEmployeeCategory("REGULER");
            }
        }
    }

    private void processMasterSalaryUpsert(Collection<MasterSalaryData> dataList, String syncBy) {
        for (MasterSalaryData data : dataList) {
            String checkSql = "SELECT id, gaji, bpjs_kesehatan, bp_jamsostek, bpjs_pensiun, asuransi_kesehatan, asuransi_kecelakaan, pic FROM master_salary " +
                              "WHERE UPPER(COALESCE(division, '')) = UPPER(?) AND UPPER(COALESCE(unit_name, '')) = UPPER(?) AND UPPER(COALESCE(position, '')) = UPPER(?) AND UPPER(COALESCE(branch, '')) = UPPER(?) AND UPPER(COALESCE(employee_type, '')) = UPPER(?) LIMIT 1";
            
            List<Map<String, Object>> existing = jdbcTemplate.queryForList(checkSql, data.division, data.unit, data.position, data.branch, data.employeeType);
            
            if (!existing.isEmpty()) {
                // Update existing record, keeping non-null values if incoming is null
                Map<String, Object> row = existing.get(0);
                Long id = ((Number) row.get("id")).longValue();
                
                Double newSalary = data.salary != null && data.salary > 0 ? data.salary : (row.get("gaji") != null ? ((Number) row.get("gaji")).doubleValue() : null);
                String newBpjsKes = data.bpjsKesehatan != null && !data.bpjsKesehatan.isEmpty() ? data.bpjsKesehatan : (String) row.get("bpjs_kesehatan");
                String newBpJam = data.bpJamsostek != null && !data.bpJamsostek.isEmpty() ? data.bpJamsostek : (String) row.get("bp_jamsostek");
                String newBpjsPen = data.bpjsPensiun != null && !data.bpjsPensiun.isEmpty() ? data.bpjsPensiun : (String) row.get("bpjs_pensiun");
                String newAsuransiKes = data.asuransiKesehatan != null && !data.asuransiKesehatan.isEmpty() ? data.asuransiKesehatan : (String) row.get("asuransi_kesehatan");
                String newAsuransiKec = data.asuransiKecelakaan != null && !data.asuransiKecelakaan.isEmpty() ? data.asuransiKecelakaan : (String) row.get("asuransi_kecelakaan");

                String updateSql = "UPDATE master_salary SET gaji = ?, bpjs_kesehatan = ?, bp_jamsostek = ?, bpjs_pensiun = ?, asuransi_kesehatan = ?, asuransi_kecelakaan = ?, update_date = NOW(), pic = COALESCE(pic, ?) WHERE id = ?";
                jdbcTemplate.update(updateSql, newSalary, newBpjsKes, newBpJam, newBpjsPen, newAsuransiKes, newAsuransiKec, syncBy, id);
            } else {
                // Insert new record with PIC
                String insertSql = "INSERT INTO master_salary (division, unit_name, position, branch, employee_type, gaji, bpjs_kesehatan, bp_jamsostek, bpjs_pensiun, asuransi_kesehatan, asuransi_kecelakaan, approval, created_by, pic, created_date) " +
                                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'APPROVED', ?, ?, NOW())";
                jdbcTemplate.update(insertSql, data.division, data.unit, data.position, data.branch, data.employeeType, data.salary, data.bpjsKesehatan, data.bpJamsostek, data.bpjsPensiun, data.asuransiKesehatan, data.asuransiKecelakaan, syncBy, syncBy);
            }
        }
    }

    private Double parseDoubleOrNull(String val) {
        if (val == null || val.trim().isEmpty() || val.equalsIgnoreCase("null")) return null;
        try { return Double.parseDouble(val); } catch (Exception e) { return null; }
    }

    private String parseStringOrNull(String val) {
        if (val == null || val.trim().isEmpty() || val.equalsIgnoreCase("null")) return null;
        return val.trim();
    }

    private static class MasterSalaryData {
        String division, unit, position, branch, employeeType;
        Double salary;
        String bpjsKesehatan, bpJamsostek, bpjsPensiun, asuransiKesehatan, asuransiKecelakaan;

        public MasterSalaryData(String d, String u, String p, String b, String et, Double s, String bk, String bj, String bp, String ak, String ake) {
            this.division = d; this.unit = u; this.position = p; this.branch = b; this.employeeType = et;
            this.salary = s; this.bpjsKesehatan = bk; this.bpJamsostek = bj; this.bpjsPensiun = bp; this.asuransiKesehatan = ak; this.asuransiKecelakaan = ake;
        }
    }
}
