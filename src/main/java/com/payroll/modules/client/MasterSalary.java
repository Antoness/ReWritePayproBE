package com.payroll.modules.client;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "master_salary")
public class MasterSalary {

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

    @Column(name = "tunjangan", columnDefinition = "TEXT")
    private String tunjangan;

    @Column(name = "approval")
    private String approval; // REQUEST, APPROVED, DONE

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "pic")
    private String pic;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "keterangan")
    private String keterangan;
    
    @Column(name = "periode_payroll")
    private String periodePayroll;

    // --- NEW FIELDS FOR ADD CLIENT FORM ---
    @Column(name = "salary_type")
    private String salaryType;
    
    @Column(name = "nominal")
    private Double nominal;
    
    @Column(name = "work_days")
    private String workDays;
    
    @Column(name = "bpjs_tk_type")
    private String bpjsTkType;
    
    @Column(name = "manajemen_fee")
    private Double manajemenFee;
    
    @Column(name = "metode_pajak")
    private String metodePajak;
    
    @Column(name = "komponen_project")
    private String komponenProject;
    
    @Column(name = "persen_bpjs_kesehatan")
    private String persenBpjsKesehatan;
    
    @Column(name = "bpjs_ketenagakerjaan")
    private String bpjsKetenagakerjaan;
    
    @Column(name = "komponen_upah")
    private String komponenUpah;
    
    @Column(name = "komponen_lembur")
    private String komponenLembur;
    
    @Column(name = "biaya_jasa")
    private Double biayaJasa;
    
    @Column(name = "training")
    private Double training;
    
    @Column(name = "bonus")
    private Double bonus;
    
    @Column(name = "tunjangan_tetap", columnDefinition = "TEXT")
    private String tunjanganTetap;
    
    @Column(name = "tunjangan_tidak_tetap", columnDefinition = "TEXT")
    private String tunjanganTidakTetap;
    
    @Column(name = "tunjangan_beda_periode")
    private Boolean tunjanganBedaPeriode;
    
    @org.hibernate.annotations.JdbcTypeCode(org.hibernate.type.SqlTypes.JSON)
    @Column(name = "tunjangan_details", columnDefinition = "jsonb")
    private String tunjanganDetails;

    
    @Column(name = "ditanggung_oleh")
    private String ditanggungOleh;
    
    @Column(name = "insentif")
    private Double insentif;
    
    @Column(name = "lembur")
    private Double lembur;
    
    @Column(name = "tunjangan_kesehatan")
    private Double tunjanganKesehatan;
    
    @Column(name = "performance_pay")
    private Double performancePay;
    
    @Column(name = "monthly_commission")
    private Double monthlyCommission;
    
    @Column(name = "shift_allowance")
    private Double shiftAllowance;
    
    @Column(name = "thr")
    private Double thr;
    
    @Column(name = "kompensasi")
    private Double kompensasi;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;
    
    @PrePersist
    public void prePersist() {
        if (createdDate == null) {
            createdDate = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = LocalDateTime.now();
    }
}
