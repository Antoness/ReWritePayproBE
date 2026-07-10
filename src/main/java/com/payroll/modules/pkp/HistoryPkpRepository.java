package com.payroll.modules.pkp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryPkpRepository extends JpaRepository<HistoryPkp, Long> {

    /**
     * Optimasi:
     * - SELECT kolom spesifik, bukan SELECT *
     * - value_update bertipe TEXT, di-FORMAT hanya jika numeric (REGEXP check)
     * - LIKE hanya pada kolom VARCHAR/TEXT (tax, n_tax, created_by, status)
     * - Kolom bigint (value) & datetime (created_date) TIDAK di-LIKE → performa
     * - countQuery dipisah supaya tidak re-run ORDER BY
     */
    @Query(value =
            "SELECT " +
            "  id AS id, " +
            "  CASE WHEN value IS NULL OR value = 0 THEN '-' ELSE TO_CHAR(CAST(value AS NUMERIC), 'FM999,999,999,999') END AS value, " +
            "  CASE WHEN value_update IS NULL OR value_update = '' OR value_update = '0' THEN '-' " +
            "       WHEN value_update ~ '^[0-9]+$' THEN TO_CHAR(CAST(value_update AS NUMERIC), 'FM999,999,999,999') " +
            "       ELSE value_update END AS valueUpdate, " +
            "  tax           AS tax, " +
            "  tax_update    AS taxUpdate, " +
            "  n_tax         AS nTax, " +
            "  n_tax_update  AS nTaxUpdate, " +
            "  created_by    AS createdBy, " +
            "  created_date  AS createdDate, " +
            "  status        AS status " +
            "FROM history_pkp " +
            "WHERE (:search IS NULL " +
            "  OR tax           LIKE CONCAT('%',:search,'%') " +
            "  OR tax_update    LIKE CONCAT('%',:search,'%') " +
            "  OR n_tax         LIKE CONCAT('%',:search,'%') " +
            "  OR n_tax_update  LIKE CONCAT('%',:search,'%') " +
            "  OR created_by    LIKE CONCAT('%',:search,'%') " +
            "  OR status        LIKE CONCAT('%',:search,'%') " +
            ") " +
            "ORDER BY created_date DESC",
           countQuery =
            "SELECT COUNT(1) FROM history_pkp " +
            "WHERE (:search IS NULL " +
            "  OR tax           LIKE CONCAT('%',:search,'%') " +
            "  OR tax_update    LIKE CONCAT('%',:search,'%') " +
            "  OR n_tax         LIKE CONCAT('%',:search,'%') " +
            "  OR n_tax_update  LIKE CONCAT('%',:search,'%') " +
            "  OR created_by    LIKE CONCAT('%',:search,'%') " +
            "  OR status        LIKE CONCAT('%',:search,'%') " +
            ")",
           nativeQuery = true)
    Page<HistoryPkpProjection> searchHistoryNative(@Param("search") String search, Pageable pageable);
}
