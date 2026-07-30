package com.payroll.modules.libur;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MasterHolidayRequestDTO {
    private LocalDate tanggal;
    private String keterangan;
}
