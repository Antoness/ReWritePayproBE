package com.payroll.modules.employee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tku_pajak", indexes = {
    @Index(name = "idx_tku_pajak_nik", columnList = "nik")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TkuPajak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10)
    private String nik;

    @Column(name = "id_number", length = 25)
    private String idNumber;

    @Column(name = "id_tku", length = 30)
    private String idTku;

    @Column(name = "metode_pajak", length = 100)
    private String metodePajak;

    @Column(name = "komponen_project", length = 100)
    private String komponenProject;
}
