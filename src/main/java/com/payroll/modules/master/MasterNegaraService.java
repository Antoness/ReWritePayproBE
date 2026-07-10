package com.payroll.modules.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterNegaraService {
    
    @Autowired
    private MasterNegaraRepository masterNegaraRepository;

    public List<MasterNegaraProjection> getKodeAndAsalNegara() {
        return masterNegaraRepository.findKodeAndAsalNegara();
    }
}
