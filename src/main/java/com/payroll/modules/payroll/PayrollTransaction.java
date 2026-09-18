package com.payroll.modules.payroll;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "payroll_transactions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayrollTransaction {

    @Id
    @Column(name = "payroll_id", length = 50, nullable = false)
    private String payrollId;

    @Column(name = "nik", length = 50)
    private String nik;

    @Column(name = "payroll_type", length = 50)
    private String payrollType; // REGULER, SALES, SERVICE, KERTAS_KERJA

    @Column(name = "periode_bulan", length = 2)
    private String periodeBulan;

    @Column(name = "periode_tahun", length = 4)
    private String periodeTahun;

    @Column(name = "basic_salary")
    private BigDecimal basicSalary;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "dynamic_components", columnDefinition = "jsonb")
    private List<PayrollComponent> dynamicComponents;

    @Column(name = "gross_salary")
    private BigDecimal grossSalary;

    @Column(name = "net_salary")
    private BigDecimal netSalary;

    @Column(name = "company_id")
    @Builder.Default
    private Long companyId = 1L;

    @Column(name = "status_data", length = 50)
    private String statusData; // NEW, REQUEST_APPROVAL, APPROVED, RETURNED, PROCESSED, PAID
}
