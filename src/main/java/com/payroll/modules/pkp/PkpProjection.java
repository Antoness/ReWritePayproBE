package com.payroll.modules.pkp;

import java.time.LocalDateTime;

public interface PkpProjection {
    Long getId();
    String getValue();
    String getTax();
    String getTaxTanpaNpwp();
    String getCreatedBy();
    LocalDateTime getCreatedDate();
    String getUpdateBy();
    LocalDateTime getUpdateDate();
}
