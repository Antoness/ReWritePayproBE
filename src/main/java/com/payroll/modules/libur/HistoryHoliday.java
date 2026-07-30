package com.payroll.modules.libur;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "history_holiday")
public class HistoryHoliday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_parent")
    private Long idParent;

    @Column(name = "bulan_libur", nullable = false)
    private LocalDate bulanLibur;

    @Column(name = "hari", nullable = false)
    private String hari;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "status")
    private String status;
}
