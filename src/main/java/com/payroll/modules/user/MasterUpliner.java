package com.payroll.modules.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "master_upliner")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MasterUpliner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String nik;

    @Column(name = "nik_upliner", length = 50)
    private String nikUpliner;

    @Column(name = "nama_upliner", length = 255)
    private String namaUpliner;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "created_by", length = 50)
    private String createdBy;
}
