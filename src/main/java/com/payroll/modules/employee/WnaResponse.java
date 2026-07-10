package com.payroll.modules.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WnaResponse {
    private Integer id;
    private String nik;
    private String name;
    private String noKtp;
    private String employeeType;
    private String division;
    private String unit;
    private String position;
    private String branch;
    private String joinDate;
    private String resignDate;
    private String statusEmployee;
    private String nationality;
    private String tglIzinKerja;
    private String passportNumber;
    private String kitasNumber;
    private String kodeNegara;
    private String status;
    private String createdBy;
    private String createdDate;
}
