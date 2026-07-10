package com.payroll.modules.employee;

import lombok.Data;

@Data
public class EmployeeUpdateRequest {
    private String name;
    private String noKtp;
    private String department; 
    private String division;
    private String unit;
    private String position;
    private String branch;
    private String idTku;
    private String metodePajak;
    private String komponenProject;
    private String nationality;
}
