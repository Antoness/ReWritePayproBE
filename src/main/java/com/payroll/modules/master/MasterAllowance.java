package com.payroll.modules.master;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "master_allowance")
public class MasterAllowance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "field_deskripsi")
    private String fieldDeskripsi;
    
    @Column(name = "field_name")
    private String fieldName;
    
    @Column(name = "field_name_payroll")
    private String fieldNamePayroll;
    
    @Column(name = "is_base_salary")
    private Integer isBaseSalary;
}
