package com.payroll.modules.master;

import lombok.Data;
import java.util.List;

@Data
public class MasterPicProjectUpdateRequest {
    private String picUtama;
    private List<String> picTambahanList;
}
