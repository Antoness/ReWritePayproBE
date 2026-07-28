package com.payroll.modules.master;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterPicProjectResponseDTO {
    private Long id;
    private String division;
    private String unit;
    private String position;
    private String branch;
    private String employeeType;
    private String pic;
}
