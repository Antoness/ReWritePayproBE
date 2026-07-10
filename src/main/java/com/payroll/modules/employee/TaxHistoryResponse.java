package com.payroll.modules.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxHistoryResponse {
    private String nik;
    private String name;
    private String noKtp;
    private String employeeType;
    private String division;
    private String unit;
    private String position;
    private String branch;
    private String metodePajak;
    private String komponenProject;
    private String status;
    private String createdBy;
    private String createdDate;
}
