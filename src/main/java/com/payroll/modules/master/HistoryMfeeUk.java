package com.payroll.modules.master;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "history_mfee_uk")
public class HistoryMfeeUk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "master_salary_id")
    private Long masterSalaryId;

    @Column(name = "mfee")
    private Double mfee;

    @Column(name = "mfee_uk_old")
    private Double mfeeUkOld;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "approval")
    private String approval;
}
