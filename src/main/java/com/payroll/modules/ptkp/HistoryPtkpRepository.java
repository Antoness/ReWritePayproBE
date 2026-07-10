package com.payroll.modules.ptkp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryPtkpRepository extends JpaRepository<HistoryPtkp, Long> {

    @Query(value = "SELECT " +
                   "id as id, " +
                   "name as tipe, " +
                   "CASE WHEN value is null OR value = '' OR value = '0' THEN '-' ELSE TO_CHAR(CAST(value AS NUMERIC), 'FM999,999,999,999') END as nominal, " +
                   "CASE WHEN value_update is null OR value_update = '' OR value_update = '0' THEN '-' ELSE TO_CHAR(CAST(value_update AS NUMERIC), 'FM999,999,999,999') END as nominalUpdate, " +
                   "created_by as createdBy, " +
                   "created_date as createdDate, " +
                   "status as status " +
                   "FROM history_ptkp " +
                   "WHERE (:search IS NULL OR name LIKE CONCAT('%',:search,'%') OR value LIKE CONCAT('%',:search,'%') OR created_by LIKE CONCAT('%',:search,'%') OR status LIKE CONCAT('%',:search,'%')) " +
                   "ORDER BY created_date DESC",
           countQuery = "SELECT count(1) FROM history_ptkp " +
                        "WHERE (:search IS NULL OR name LIKE CONCAT('%',:search,'%') OR value LIKE CONCAT('%',:search,'%') OR created_by LIKE CONCAT('%',:search,'%') OR status LIKE CONCAT('%',:search,'%'))",
           nativeQuery = true)
    Page<HistoryPtkpProjection> searchHistoryNative(@Param("search") String search, Pageable pageable);
}
