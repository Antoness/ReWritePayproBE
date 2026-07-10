package com.payroll.modules.pkp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterPkpRepository extends JpaRepository<MasterPkp, Long> {

    /**
     * Native query dengan optimasi:
     * - SELECT hanya kolom yang dibutuhkan (bukan SELECT *)
     * - COUNT query dipisah agar tidak re-run main SELECT
     * - IF() di DB layer supaya tidak perlu mapping ulang di Java
     * - LIKE hanya pada kolom relevan (tax, n_tax, created_by, update_by)
     * - value dan date tidak di-LIKE (non-string performa buruk)
     */
    @Query(value =
            "SELECT " +
            "  id AS id, " +
            "  CASE WHEN value IS NULL OR value = 0 THEN '-' ELSE TO_CHAR(CAST(value AS NUMERIC), 'FM999,999,999,999') END AS value, " +
            "  tax AS tax, " +
            "  n_tax AS taxTanpaNpwp, " +
            "  created_by AS createdBy, " +
            "  created_date AS createdDate, " +
            "  update_by AS updateBy, " +
            "  update_date AS updateDate " +
            "FROM master_pkp " +
            "WHERE (:search IS NULL " +
            "  OR tax LIKE CONCAT('%',:search,'%') " +
            "  OR n_tax LIKE CONCAT('%',:search,'%') " +
            "  OR created_by LIKE CONCAT('%',:search,'%') " +
            "  OR update_by LIKE CONCAT('%',:search,'%') " +
            ") " +
            "ORDER BY id DESC",
           countQuery =
            "SELECT COUNT(1) FROM master_pkp " +
            "WHERE (:search IS NULL " +
            "  OR tax LIKE CONCAT('%',:search,'%') " +
            "  OR n_tax LIKE CONCAT('%',:search,'%') " +
            "  OR created_by LIKE CONCAT('%',:search,'%') " +
            "  OR update_by LIKE CONCAT('%',:search,'%') " +
            ")",
           nativeQuery = true)
    Page<PkpProjection> searchPkpNative(@Param("search") String search, Pageable pageable);
}
