package com.payroll.modules.master;

import java.time.LocalDateTime;

public interface MasterBankProjection {
    Long getId();
    String getNamaBank();
    String getSwiftCode();
    String getKodeBi();
    String getStatus();
    LocalDateTime getTanggalInputUpdate();
    String getPicInputUpdate();
}
