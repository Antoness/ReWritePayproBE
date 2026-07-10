package com.payroll.modules.pkp;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "history_pkp")
@Data
public class HistoryPkp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_parent")
    private Long idParent;

    @Column(name = "value")
    private Long value;

    @Column(name = "value_update", columnDefinition = "TEXT")
    private String valueUpdate;

    @Column(name = "tax")
    private String tax;

    @Column(name = "tax_update")
    private String taxUpdate;

    @Column(name = "n_tax")
    private String nTax;

    @Column(name = "n_tax_update")
    private String nTaxUpdate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "status")
    private String status;
}
