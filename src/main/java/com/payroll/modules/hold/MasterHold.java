package com.payroll.modules.hold;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "master_hold")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MasterHold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id", nullable = false)
    @Builder.Default
    private Long companyId = 1L;

    @Column(name = "id_payroll")
    private Long idPayroll;

    @Column(name = "id_uk")
    private Long idUk;

    @Column(length = 50, nullable = false)
    private String nik;

    @Column(name = "nama", length = 150)
    private String nama;

    @Column(name = "month_payroll", length = 20)
    private String monthPayroll;

    @Column(name = "year_payroll", length = 10)
    private String yearPayroll;

    @Column(name = "periode_penggajian", length = 50)
    private String periodePenggajian;

    @Column(length = 100)
    private String department;

    @Column(length = 100)
    private String division;

    @Column(name = "unit_name", length = 100)
    private String unitName;

    @Column(length = 100)
    private String position;

    @Column(length = 100)
    private String branch;

    @Column(name = "status_ketenagakerjaan", length = 50)
    private String statusKetenagakerjaan;

    @Column(name = "nama_bank", length = 50)
    private String namaBank;

    @Column(name = "cabang_bank", length = 100)
    private String cabangBank;

    @Column(length = 50)
    private String norek;

    @Column(name = "thp_hold")
    private Double thpHold;

    @Column(columnDefinition = "TEXT")
    private String keterangan;

    @Column(name = "tanggal_release", length = 20)
    private String tanggalRelease;

    @Column(name = "status_approval_release", length = 50)
    @Builder.Default
    private String statusApprovalRelease = "New";

    @Column(name = "file_upload_name", length = 255)
    private String fileUploadName;

    @Column(name = "file_url", columnDefinition = "TEXT")
    private String fileUrl;

    @Column(name = "payroll_date", length = 20)
    private String payrollDate;

    @Column(name = "uploaded_by", length = 50)
    private String uploadedBy;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by", length = 50)
    private String createdBy;
}
