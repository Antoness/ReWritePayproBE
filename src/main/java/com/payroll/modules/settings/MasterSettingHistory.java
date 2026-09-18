package com.payroll.modules.settings;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "master_settings_history", indexes = {
    @Index(name = "idx_master_settings_hist_company", columnList = "company_id")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MasterSettingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id", nullable = false)
    @Builder.Default
    private Long companyId = 1L;

    @Column(name = "settingkey", length = 255)
    private String settingKey;

    @Column(name = "old_value", columnDefinition = "TEXT")
    private String oldValue;

    @Column(name = "new_value", columnDefinition = "TEXT")
    private String newValue;

    @Column(name = "action_type", length = 50)
    private String actionType; // "CREATE", "UPDATE", "DELETE"

    @Column(name = "action_by", length = 255)
    private String actionBy;

    @CreationTimestamp
    @Column(name = "action_date", updatable = false)
    private LocalDateTime actionDate;
}
