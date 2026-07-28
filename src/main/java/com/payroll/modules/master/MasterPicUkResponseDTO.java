package com.payroll.modules.master;

import lombok.Data;

@Data
public class MasterPicUkResponseDTO {
    private Long id;
    private String division;
    private String unit;
    private String position;
    private String branch;
    private String employeeType;
    
    private String modeUk;
    private Double mfee;
    private String approval;
}
