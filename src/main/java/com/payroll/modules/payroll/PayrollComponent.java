package com.payroll.modules.payroll;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayrollComponent {
    private String code;
    private String name;
    private BigDecimal amount;
    private String type; // EARNING / DEDUCTION
    private Boolean isTaxable;
}
