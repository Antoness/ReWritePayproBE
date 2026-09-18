package com.payroll.modules.hold;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterHoldHistoryRepository extends JpaRepository<MasterHoldHistory, Long> {

    @Query("SELECT h FROM MasterHoldHistory h WHERE h.companyId = :companyId " +
           "AND (:search IS NULL OR LOWER(h.nik) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(h.name) LIKE LOWER(CONCAT('%', :search, '%'))) " +
           "AND (:division IS NULL OR h.division = :division) " +
           "AND (:unitName IS NULL OR h.unitName = :unitName) " +
           "AND (:position IS NULL OR h.position = :position) " +
           "AND (:branch IS NULL OR h.branch = :branch) " +
           "AND (:employeeType IS NULL OR LOWER(h.employeeType) = LOWER(:employeeType))")
    Page<MasterHoldHistory> findByFilters(
            @Param("companyId") Long companyId,
            @Param("search") String search,
            @Param("division") String division,
            @Param("unitName") String unitName,
            @Param("position") String position,
            @Param("branch") String branch,
            @Param("employeeType") String employeeType,
            Pageable pageable
    );

    List<MasterHoldHistory> findByCompanyIdAndIdHoldOrderByCreatedAtDesc(Long companyId, Long idHold);
}
