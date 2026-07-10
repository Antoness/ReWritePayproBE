package com.payroll.modules.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface MasterNegaraRepository extends JpaRepository<MasterNegara, Long> {
    List<MasterNegara> findAllByOrderByAsalNegaraAsc();
    
    @Query("SELECT m.kodeNegara as kodeNegara, m.asalNegara as asalNegara FROM MasterNegara m ORDER BY m.asalNegara ASC")
    List<MasterNegaraProjection> findKodeAndAsalNegara();
}
