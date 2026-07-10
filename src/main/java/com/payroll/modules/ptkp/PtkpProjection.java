package com.payroll.modules.ptkp;

import java.time.LocalDateTime;

public interface PtkpProjection {
    Long getId();
    String getTipe();
    String getNominal();
    String getCreatedBy();
    LocalDateTime getCreatedDate();
    String getUpdateBy();
    LocalDateTime getUpdateDate();
}
