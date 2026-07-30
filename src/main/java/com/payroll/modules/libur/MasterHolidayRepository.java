package com.payroll.modules.libur;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterHolidayRepository extends JpaRepository<MasterHoliday, Long> {

    @Query("SELECT h FROM MasterHoliday h WHERE " +
           "(:year IS NULL OR CAST(EXTRACT(YEAR FROM h.bulanLibur) AS string) = CAST(:year AS string)) AND " +
           "(:month IS NULL OR LPAD(CAST(EXTRACT(MONTH FROM h.bulanLibur) AS string), 2, '0') = CAST(:month AS string)) AND " +
           "(:keyword IS NULL OR LOWER(h.hari) LIKE LOWER(CONCAT('%', CAST(:keyword AS string), '%')) OR " +
           "TO_CHAR(h.bulanLibur, 'DD-Mon-YYYY') LIKE CONCAT('%', CAST(:keyword AS string), '%'))")
    Page<MasterHoliday> findHolidaysWithFilters(
            @Param("year") String year,
            @Param("month") String month,
            @Param("keyword") String keyword,
            Pageable pageable);
}
