package com.payroll.modules.ptkp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterPtkpRepository extends JpaRepository<MasterPtkp, Long> {
    
    @Query(value = "SELECT id as id, " +
                   "name as tipe, " +
                   "CASE WHEN value is null OR value = '' OR value = '0' THEN '-' ELSE TO_CHAR(CAST(value AS NUMERIC), 'FM999,999,999,999') END as nominal, " +
                   "created_by as createdBy, " +
                   "created_date as createdDate, " +
                   "update_by as updateBy, " +
                   "update_date as updateDate " +
                   "FROM master_ptkp " +
                   "WHERE (:search IS NULL OR name LIKE CONCAT('%',:search,'%') OR value LIKE CONCAT('%',:search,'%') OR created_by LIKE CONCAT('%',:search,'%') OR update_by LIKE CONCAT('%',:search,'%'))",
           countQuery = "SELECT count(1) FROM master_ptkp " +
                        "WHERE (:search IS NULL OR name LIKE CONCAT('%',:search,'%') OR value LIKE CONCAT('%',:search,'%') OR created_by LIKE CONCAT('%',:search,'%') OR update_by LIKE CONCAT('%',:search,'%'))",
           nativeQuery = true)
    Page<PtkpProjection> searchPtkpNative(@Param("search") String search, Pageable pageable);

    boolean existsByNameIgnoreCase(String name);
}
