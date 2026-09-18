package com.payroll.modules.settings;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterSettingHistoryRepository extends JpaRepository<MasterSettingHistory, Long> {

    @Query("SELECT h FROM MasterSettingHistory h WHERE h.companyId = :companyId AND " +
           "(:search IS NULL OR :search = '' OR " +
           "LOWER(h.settingKey) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(h.oldValue) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(h.newValue) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(h.actionBy) LIKE LOWER(CONCAT('%', :search, '%'))) " +
           "ORDER BY h.actionDate DESC")
    Page<MasterSettingHistory> searchHistory(@Param("companyId") Long companyId,
                                             @Param("search") String search,
                                             Pageable pageable);
}
