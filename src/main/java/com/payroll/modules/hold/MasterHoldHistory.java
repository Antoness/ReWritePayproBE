package com.payroll.modules.hold;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "master_hold_history")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MasterHoldHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id", nullable = false)
    @Builder.Default
    private Long companyId = 1L;

    @Column(name = "id_hold")
    private Long idHold;

    @Column(length = 50, nullable = false)
    private String nik;

    @Column(length = 150)
    private String name;

    @Column(length = 100)
    private String department;

    @Column(length = 100)
    private String division;

    @Column(name = "unit_name", length = 100)
    private String unitName;

    @Column(name = "employee_type", length = 50)
    private String employeeType;

    @Column(length = 100)
    private String position;

    @Column(length = 100)
    private String branch;

    @Column(name = "thp")
    private Double thp;

    @Column(name = "action_type", length = 50)
    private String actionType;

    @Column(length = 50)
    private String status;

    @Column(name = "dilakukan_oleh", length = 100)
    private String dilakukanOleh;

    @Column(name = "action_note", columnDefinition = "TEXT")
    private String actionNote;

    @Column(name = "tanggal_log")
    private String tanggal;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
