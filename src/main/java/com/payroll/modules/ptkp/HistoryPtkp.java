package com.payroll.modules.ptkp;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "history_ptkp")
@Data
public class HistoryPtkp {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "id_parent")
    private Long idParent;
    
    private String name;
    
    private String value;
    
    @Column(name = "value_update")
    private String valueUpdate;
    
    private String category;
    
    private String precentage;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    private String status;
}
