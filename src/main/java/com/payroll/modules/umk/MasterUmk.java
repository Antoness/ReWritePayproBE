package com.payroll.modules.umk;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "master_umk")
@Data
public class MasterUmk {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String area;
    private String tahun;
    private String nominal;
    
    @Column(name = "nominal_update")
    private String nominalUpdate;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "nominal_request")
    private Long nominalRequest;

    @Column(name = "request_by")
    private String requestBy;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "code_type")
    private String codeType;

    private String approval;
}
