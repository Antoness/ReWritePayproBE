package com.payroll.modules.client;

import lombok.Data;
import java.util.List;

@Data
public class MasterClientSearchRequest {
    private String search;
    private String division;
    private String unitName;
    private String position;
    private String branch;
    private String status;
    private String employeeType;
    private List<Long> ids; // Used for export by checklist
}