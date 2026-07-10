package com.payroll.modules.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterUplinerRepository extends JpaRepository<MasterUpliner, Integer> {
    
    @Query(value = "SELECT DISTINCT nik_upliner, nama_upliner FROM master_upliner WHERE nik = :nik", nativeQuery = true)
    List<Object[]> findExistingUpliners(@Param("nik") String nik);
    
    boolean existsByNikAndNikUpliner(String nik, String nikUpliner);
    
    void deleteByNikAndNikUpliner(String nik, String nikUpliner);
}
