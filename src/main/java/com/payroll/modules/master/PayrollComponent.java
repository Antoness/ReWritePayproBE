package com.payroll.modules.master;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "payroll_components")
public class PayrollComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    // TUNJANGAN or INSENTIF
    @Column(name = "type", nullable = false)
    private String type;

    // TAX or NON_TAX
    @Column(name = "tax_group", nullable = false)
    private String taxGroup;

    // FIXED_VALUE or PERCENTAGE
    @Column(name = "calc_method", nullable = false)
    private String calcMethod = "FIXED_VALUE";

    @Column(name = "default_value", precision = 20, scale = 2)
    private BigDecimal defaultValue;

    // Filters (nullable = applies to all)
    @Column(name = "apply_to_division")
    private String applyToDivision;

    @Column(name = "apply_to_position")
    private String applyToPosition;

    @Column(name = "apply_to_unit_name")
    private String applyToUnitName;

    @Column(name = "apply_to_employee_type")
    private String applyToEmployeeType;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "modified_by")
    private String modifiedBy;
}
