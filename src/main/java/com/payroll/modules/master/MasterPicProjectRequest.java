package com.payroll.modules.master;

import lombok.Data;

@Data
public class MasterPicProjectRequest {
    private String search;
    private String division;
    private String unitName;
    private String position;
    private String branch;
    private String periodePayroll;
}
