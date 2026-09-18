package com.payroll.modules.master.upah;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "master_upah_kertas_kerja")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterUpah {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "lokasi_unit_kerja", nullable = false)
    private String lokasiUnitKerja;

    @Column(name = "jabo_status", length = 20)
    private String jaboStatus; // JABO / NON JABO

    @Column(name = "upah_minimum", precision = 15, scale = 2)
    private BigDecimal upahMinimum;

    @Column(name = "tahun", length = 10)
    private String tahun;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
