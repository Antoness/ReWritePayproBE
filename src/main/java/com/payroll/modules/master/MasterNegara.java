package com.payroll.modules.master;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "master_negara")
public class MasterNegara {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kode_negara")
    private String kodeNegara;

    @Column(name = "asal_negara")
    private String asalNegara;
}
