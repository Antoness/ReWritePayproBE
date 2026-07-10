package com.payroll.modules.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/master-negara")
public class MasterNegaraController {

    @Autowired
    private MasterNegaraRepository masterNegaraRepository;

    @GetMapping
    public ResponseEntity<List<MasterNegara>> getAllNegara() {
        return ResponseEntity.ok(masterNegaraRepository.findAllByOrderByAsalNegaraAsc());
    }
}
