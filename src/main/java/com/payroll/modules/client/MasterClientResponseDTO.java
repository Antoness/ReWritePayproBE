package com.payroll.modules.client;

import lombok.Data;

@Data
public class MasterClientResponseDTO {
    private Long id;
    private Long no;
    private String division;
    private String unit;
    private String position;
    private String branch;
    private String employeeType;
    private String createdDate;
    private String updateDate;
    private String createdBy;
    private String status;
    private String keterangan;
}