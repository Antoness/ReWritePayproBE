package com.payroll.modules.client;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "master_salary")
public class MasterClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "division")
    private String division;

    @Column(name = "unit_name")
    private String unitName;

    @Column(name = "position")
    private String position;

    @Column(name = "branch")
    private String branch;

    @Column(name = "employee_type")
    private String employeeType;

    @Column(name = "approval")
    private String approval;

    @Column(name = "keterangan")
    private String keterangan;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "pic")
    private String pic;

    @Column(name = "periode_payroll")
    private String periodePayroll;

    @Column(name = "gaji")
    private Double gaji;

    @Column(name = "bpjs_kesehatan")
    private String bpjsKesehatan;

    @Column(name = "bp_jamsostek")
    private String bpJamsostek;

    @Column(name = "bpjs_pensiun")
    private String bpjsPensiun;

    @Column(name = "asuransi_kesehatan")
    private String asuransiKesehatan;

    @Column(name = "asuransi_kecelakaan")
    private String asuransiKecelakaan;

    @Column(name = "tunjangan")
    private Double tunjangan;

    // Master Uang Kompensasi (PIC UK) Fields
    @Column(name = "mode_uk")
    private String modeUk;

    @Column(name = "mfee_uk")
    private Double mfeeUk;

    @Column(name = "mfee_uk_old")
    private Double mfeeUkOld;

    @Column(name = "approval_mfee_uk")
    private String approvalMfeeUk;
}