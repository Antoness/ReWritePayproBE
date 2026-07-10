package com.payroll.modules.client;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "master_pic")
public class MasterPic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "master_salary_id")
    private Long masterSalaryId;

    @Column(name = "\"user\"")
    private String user;
}
