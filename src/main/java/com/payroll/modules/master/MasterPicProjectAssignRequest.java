package com.payroll.modules.master;

import lombok.Data;
import java.util.List;

@Data
public class MasterPicProjectAssignRequest {
    private List<Long> ids;
    private String pic;
}
