package com.payroll.modules.employee;

import lombok.Data;

@Data
public class WnaUpdateRequest {
    private String passportNumber;
    private String kitasNumber;
    private String tglIzinKerja;
    private Boolean isApprove;
}
