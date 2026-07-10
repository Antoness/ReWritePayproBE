package com.payroll.modules.umk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MasterUmkRepository extends JpaRepository<MasterUmk, Long> {

    @Query(value = "SELECT area FROM master_umk GROUP BY area ORDER BY area ASC", nativeQuery = true)
    List<String> findUniqueAreas();

    @Query(value = "SELECT approval FROM master_umk WHERE approval IS NOT NULL AND approval != '' GROUP BY approval", nativeQuery = true)
    List<String> findUniqueStatuses();

    @Query(value = "SELECT tahun FROM master_umk WHERE tahun IS NOT NULL GROUP BY tahun ORDER BY tahun DESC", nativeQuery = true)
    List<String> findUniqueYears();
}
