package com.payroll.modules.pkp;

import java.time.LocalDateTime;

public interface HistoryPkpProjection {
    Long getId();
    String getValue();         // value lama (sudah di-FORMAT di query)
    String getValueUpdate();   // value baru (sudah di-FORMAT di query)
    String getTax();
    String getTaxUpdate();
    String getNTax();
    String getNTaxUpdate();
    String getCreatedBy();
    LocalDateTime getCreatedDate();
    String getStatus();
}
