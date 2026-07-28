package com.payroll.modules.master;

import lombok.Data;

@Data
public class HistoryMfeeUkResponseDTO {
    private String division;
    private String unit;
    private String position;
    private String branch;
    private String employeeType;
    private String modeUk;
    private Double mfee;
    private Double mfeeUkOld;
    private String createdDate;
    private String createdBy;
    private String approval;
}
