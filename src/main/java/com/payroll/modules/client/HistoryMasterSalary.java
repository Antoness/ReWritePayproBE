package com.payroll.modules.client;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "history_master_salary")
public class HistoryMasterSalary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "action")
    private String action; // e.g. "CREATE", "UPDATE"

    @Column(name = "master_salary_id")
    private Long masterSalaryId;

    @Column(name = "division")
    private String division;

    @Column(name = "unit_name")
    private String unitName;

    @Column(name = "position")
    private String position;

    @Column(name = "branch")
    private String branch;

    @Column(name = "employee_type")
    private String employeeType;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    @Column(name = "details", columnDefinition = "TEXT")
    private String details;
    
    @PrePersist
    public void prePersist() {
        if (createdDate == null) {
            createdDate = LocalDateTime.now();
        }
    }
}
