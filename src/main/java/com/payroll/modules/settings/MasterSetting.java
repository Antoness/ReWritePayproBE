package com.payroll.modules.settings;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "master_settings", indexes = {
    @Index(name = "idx_master_settings_company_key", columnList = "company_id, settingkey")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MasterSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id", nullable = false)
    @Builder.Default
    private Long companyId = 1L;

    @Column(name = "settingkey", length = 255, nullable = false)
    private String settingKey;

    @Column(name = "settingvalue", columnDefinition = "TEXT")
    private String settingValue;

    @Column(name = "settingnumber")
    private Integer settingNumber;

    @Column(name = "created_by", length = 255)
    private String createdBy;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "modify_by", length = 255)
    private String modifyBy;

    @UpdateTimestamp
    @Column(name = "modify_date")
    private LocalDateTime modifyDate;
}
