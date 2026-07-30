package com.payroll.modules.libur;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryHolidayRepository extends JpaRepository<HistoryHoliday, Long> {
    Page<HistoryHoliday> findAllByOrderByCreatedDateDesc(Pageable pageable);
}
