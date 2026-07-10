package com.payroll.modules.ptkp;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "master_ptkp")
@Data
public class MasterPtkp {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String value;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "update_date")
    private LocalDateTime updateDate;
    
    @Column(name = "update_by")
    private String updateBy;
}
