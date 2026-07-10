package com.payroll.modules.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private Integer id;
    private Integer idJson;
    private String nik;
    private String name;
    private String noKtp;
    private String idTku;
    private String employeeType;
    private String department;
    private String division;
    private String unit;
    private String position;
    private String branch;
    private String joinDate;
    private String resignDate;
    private String statusEmployee;
    private String nationality;
    private String numberOfContract;
    private String metodePajak;
    private String komponenProject;
    private String status;
    private String createdBy;
    private String createdDate;
}
