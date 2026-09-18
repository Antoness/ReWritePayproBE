package com.payroll.modules.master;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PayrollComponentRequestDTO {
    private String name;
    private String type;         // TUNJANGAN or INSENTIF
    private String taxGroup;     // TAX or NON_TAX
    private String calcMethod;   // FIXED_VALUE or PERCENTAGE
    private BigDecimal defaultValue;
    private String applyToDivision;
    private String applyToPosition;
    private String applyToUnitName;
    private String applyToEmployeeType;
    private String createdBy;
    private String modifiedBy;
}
