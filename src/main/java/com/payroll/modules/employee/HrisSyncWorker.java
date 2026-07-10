package com.payroll.modules.employee;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payroll.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class HrisSyncWorker {

    private final MasterEmployeeRepository employeeRepository;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_HR_EMPLOYEE_SYNC)
    public void processHrisData(String payload) {
        log.info("Received HRIS Sync Payload");
        try {
            JsonNode rootNode = objectMapper.readTree(payload);
            JsonNode dataArray = rootNode.get("data");

            if (dataArray != null && dataArray.isArray()) {
                List<Employee> employeesToSave = new ArrayList<>();

                for (JsonNode node : dataArray) {
                    String nik = node.path("NIK").asText(null);
                    if (nik == null || nik.trim().isEmpty()) {
                        continue; // Skip without NIK
                    }

                    String idNumber = node.path("ID_Number").asText(null);
                    String name = node.path("Name").asText(null);
                    String employeeType = node.path("Employee_Type").asText(null);
                    String position = node.path("Position").asText(null);
                    String division = node.path("Division").asText(null);
                    String unit = node.path("Unit").asText(null);
                    String branch = node.path("Branch").asText(null);
                    String nationality = node.path("Nationality").asText(null);
                    String status = node.path("Status").asText(null);

                    // Determine active status
                    boolean isActive = true;
                    if (status != null && (status.equalsIgnoreCase("BATAL JOIN") || status.equalsIgnoreCase("PENDING") || status.equalsIgnoreCase("INACTIVE"))) {
                        isActive = false;
                    }

                    // Find existing employee or create new
                    Optional<Employee> existingEmpOpt = employeeRepository.findByNik(nik);
                    Employee emp = existingEmpOpt.orElseGet(Employee::new);

                    emp.setNik(nik);
                    emp.setIdNumber(idNumber);
                    emp.setFullName(name);
                    emp.setEmployeeType(employeeType);
                    
                    if (position != null) {
                        emp.setPosition(position.toUpperCase()); // Normalization based on PAYPRO_RULES
                    }
                    emp.setDivision(division);
                    emp.setUnitName(unit);
                    emp.setBranchCode(branch);
                    emp.setNationality(nationality);
                    emp.setIsActive(isActive);
                    
                    if(emp.getEmployeeCategory() == null) {
                        // Infer category from nationality or employee type if possible
                        if(nationality != null && nationality.equalsIgnoreCase("WNA")) {
                            emp.setEmployeeCategory("WNA");
                        } else {
                            emp.setEmployeeCategory("REGULER");
                        }
                    }

                    employeesToSave.add(emp);
                }

                log.info("Saving {} employees to database...", employeesToSave.size());
                employeeRepository.saveAll(employeesToSave);
                log.info("HRIS Data synced successfully!");
            } else {
                log.warn("Invalid HRIS payload: 'data' array not found");
            }
        } catch (Exception e) {
            log.error("Error processing HRIS Sync payload", e);
        }
    }
}
