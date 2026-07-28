package com.payroll.modules.master;

import lombok.Data;
import java.util.List;

@Data
public class MasterPicProjectDetailDTO {
    private Long id;
    private String division;
    private String unit;
    private String position;
    private String employeeType;
    private String branch;
    private String picUtama;
    private List<String> picTambahanList;
}
