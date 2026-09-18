package com.payroll.modules.master;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PayrollComponentResponseDTO {
    private Long id;
    private String name;
    private String type;
    private String taxGroup;
    private String calcMethod;
    private BigDecimal defaultValue;
    private String applyToDivision;
    private String applyToPosition;
    private String applyToUnitName;
    private String applyToEmployeeType;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    public static PayrollComponentResponseDTO from(PayrollComponent p) {
        PayrollComponentResponseDTO dto = new PayrollComponentResponseDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setType(p.getType());
        dto.setTaxGroup(p.getTaxGroup());
        dto.setCalcMethod(p.getCalcMethod());
        dto.setDefaultValue(p.getDefaultValue());
        dto.setApplyToDivision(p.getApplyToDivision());
        dto.setApplyToPosition(p.getApplyToPosition());
        dto.setApplyToUnitName(p.getApplyToUnitName());
        dto.setApplyToEmployeeType(p.getApplyToEmployeeType());
        dto.setIsActive(p.getIsActive());
        dto.setCreatedAt(p.getCreatedAt());
        dto.setCreatedBy(p.getCreatedBy());
        dto.setModifiedAt(p.getModifiedAt());
        dto.setModifiedBy(p.getModifiedBy());
        return dto;
    }
}
