package com.payroll.modules.settings;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MasterSettingRepository extends JpaRepository<MasterSetting, Long> {

    Optional<MasterSetting> findByCompanyIdAndSettingKey(Long companyId, String settingKey);

    Optional<MasterSetting> findByCompanyIdAndId(Long companyId, Long id);

    boolean existsByCompanyIdAndSettingKey(Long companyId, String settingKey);

    boolean existsByCompanyIdAndSettingKeyAndIdNot(Long companyId, String settingKey, Long id);

    @Query("SELECT s FROM MasterSetting s WHERE s.companyId = :companyId AND " +
           "(:search IS NULL OR :search = '' OR " +
           "LOWER(s.settingKey) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(s.settingValue) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<MasterSetting> searchSettings(@Param("companyId") Long companyId,
                                       @Param("search") String search,
                                       Pageable pageable);

    long countByCompanyId(Long companyId);
}
