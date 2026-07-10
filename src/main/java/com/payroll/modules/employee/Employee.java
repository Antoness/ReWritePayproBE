package com.payroll.modules.employee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees", indexes = {
    @Index(name = "idx_emp_nik", columnList = "nik", unique = true)
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nik", length = 50, nullable = false, unique = true)
    private String nik;

    @Column(name = "id_number", length = 50)
    private String idNumber;

    @Column(name = "full_name", length = 150)
    private String fullName;

    @Column(name = "employee_type", length = 50)
    private String employeeType;

    @Column(name = "employee_category", length = 50)
    private String employeeCategory;

    @Column(name = "position", length = 100)
    private String position;

    @Column(name = "division", length = 100)
    private String division;

    @Column(name = "unit_name", length = 100)
    private String unitName;

    @Column(name = "branch_code", length = 50)
    private String branchCode;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "nationality", length = 50)
    private String nationality;

    @Column(name = "passport_number", length = 50)
    private String passportNumber;

    @Column(name = "kitas_number", length = 50)
    private String kitasNumber;

    @Column(name = "tgl_izin_kerja", length = 50)
    private String tglIzinKerja;

    @Column(name = "kode_negara", length = 50)
    private String kodeNegara;
    
    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "created_date")
    private java.time.LocalDateTime createdDate;

    @Column(name = "updated_by", length = 100)
    private String updatedBy;

    @Column(name = "updated_date")
    private java.time.LocalDateTime updatedDate;

    // TAX CONFIGURATION FIELDS
    @Column(name = "metode_pajak", length = 100)
    private String metodePajak;

    @Column(name = "komponen_project", length = 100)
    private String komponenProject;

    @Column(name = "status_pajak", length = 50)
    private String statusPajak;

    @Column(name = "created_by_pajak", length = 100)
    private String createdByPajak;

    @Column(name = "created_date_pajak")
    private java.time.LocalDateTime createdDatePajak;


    @PrePersist
    @PreUpdate
    public void prePersist() {
        if (this.position != null) {
            this.position = this.position.toUpperCase();
        }
    }
}
