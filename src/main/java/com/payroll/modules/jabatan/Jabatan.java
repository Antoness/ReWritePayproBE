package com.payroll.modules.jabatan;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "master_jabatan")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jabatan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String kodeJabatan;

    @Column(nullable = false)
    private String namaJabatan;

    private String deskripsi;

    @Column(nullable = false)
    private Double gajiPokok;
}
