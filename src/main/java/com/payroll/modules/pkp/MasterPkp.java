package com.payroll.modules.pkp;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "master_pkp")
@Data
public class MasterPkp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value_min")
    private Long valueMin;

    @Column(name = "value")
    private Long value;

    @Column(name = "tax")
    private String tax;

    @Column(name = "n_tax")
    private String nTax;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_date")
    private LocalDateTime updateDate;
}
