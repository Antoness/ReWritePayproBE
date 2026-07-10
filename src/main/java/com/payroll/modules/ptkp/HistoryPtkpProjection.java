package com.payroll.modules.ptkp;

import java.time.LocalDateTime;

public interface HistoryPtkpProjection {
    Long getId();
    String getTipe();
    String getNominal();
    String getNominalUpdate();
    String getCreatedBy();
    LocalDateTime getCreatedDate();
    String getStatus();
}
