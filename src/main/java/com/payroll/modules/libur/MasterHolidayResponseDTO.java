package com.payroll.modules.libur;

import lombok.Data;
import java.time.format.DateTimeFormatter;

@Data
public class MasterHolidayResponseDTO {
    private Long id;
    private String tanggal;
    private String keterangan;
    private String createdDate;
    private String createdBy;
    private String modifyDate;
    private String modifyBy;

    public MasterHolidayResponseDTO(MasterHoliday holiday) {
        this.id = holiday.getId();
        this.tanggal = holiday.getBulanLibur() != null ? holiday.getBulanLibur().toString() : "";
        this.keterangan = holiday.getHari();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        this.createdDate = holiday.getCreatedDate() != null ? holiday.getCreatedDate().format(formatter) : "";
        this.createdBy = holiday.getCreatedBy();
        this.modifyDate = holiday.getModifyDate() != null ? holiday.getModifyDate().format(formatter) : "";
        this.modifyBy = holiday.getModifyBy();
    }
}
