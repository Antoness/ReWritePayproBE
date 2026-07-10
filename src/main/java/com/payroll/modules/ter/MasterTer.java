package com.payroll.modules.ter;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rfter")
public class MasterTer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ter", length = 50)
    private String ter;

    @Column(name = "nilai_min")
    private Double nilaiMin;

    @Column(name = "nilai_max")
    private Double nilaiMax;

    @Column(name = "persen")
    private Double persen;

    @Column(name = "tahun")
    private Integer tahun;

    @Column(name = "status", columnDefinition = "int default 0")
    private Integer status;

    @Column(name = "proccess_id")
    private Integer proccessId;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
