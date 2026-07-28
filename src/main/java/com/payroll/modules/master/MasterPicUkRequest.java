package com.payroll.modules.master;

import lombok.Data;
import java.util.List;

@Data
public class MasterPicUkRequest {
    private String search;
    private String division;
    private String unitName;
    private String position;
    private String branch;
    private String employeeType;
    private String modeUk;

    // For Assign/Update operations
    private List<Long> ids;
    private String assignModeUk;
    private Double updateMfee;
}
