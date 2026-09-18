package com.payroll.modules.hold;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterHoldRepository extends JpaRepository<MasterHold, Long> {

    @Query("SELECT h FROM MasterHold h WHERE h.companyId = :companyId " +
           "AND (:search IS NULL OR LOWER(h.nik) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(h.nama) LIKE LOWER(CONCAT('%', :search, '%'))) " +
           "AND (:division IS NULL OR h.division = :division) " +
           "AND (:unitName IS NULL OR h.unitName = :unitName) " +
           "AND (:position IS NULL OR h.position = :position) " +
           "AND (:branch IS NULL OR h.branch = :branch) " +
           "AND (:employeeType IS NULL OR LOWER(h.statusKetenagakerjaan) = LOWER(:employeeType)) " +
           "AND (:status IS NULL OR h.statusApprovalRelease = :status) " +
           "AND (:month IS NULL OR h.monthPayroll = :month OR h.periodePenggajian LIKE CONCAT('%', :month, '%')) " +
           "AND (:year IS NULL OR h.yearPayroll = :year OR h.periodePenggajian LIKE CONCAT('%', :year, '%'))")
    Page<MasterHold> findByFilters(
            @Param("companyId") Long companyId,
            @Param("search") String search,
            @Param("division") String division,
            @Param("unitName") String unitName,
            @Param("position") String position,
            @Param("branch") String branch,
            @Param("employeeType") String employeeType,
            @Param("status") String status,
            @Param("month") String month,
            @Param("year") String year,
            Pageable pageable
    );

    @Query("SELECT h FROM MasterHold h WHERE h.companyId = :companyId " +
           "AND (:search IS NULL OR LOWER(h.nik) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(h.nama) LIKE LOWER(CONCAT('%', :search, '%'))) " +
           "AND (:division IS NULL OR h.division = :division) " +
           "AND (:unitName IS NULL OR h.unitName = :unitName) " +
           "AND (:position IS NULL OR h.position = :position) " +
           "AND (:branch IS NULL OR h.branch = :branch) " +
           "AND (:employeeType IS NULL OR LOWER(h.statusKetenagakerjaan) = LOWER(:employeeType)) " +
           "AND (:status IS NULL OR h.statusApprovalRelease = :status) " +
           "AND (:month IS NULL OR h.monthPayroll = :month OR h.periodePenggajian LIKE CONCAT('%', :month, '%')) " +
           "AND (:year IS NULL OR h.yearPayroll = :year OR h.periodePenggajian LIKE CONCAT('%', :year, '%'))")
    List<MasterHold> findAllByFilters(
            @Param("companyId") Long companyId,
            @Param("search") String search,
            @Param("division") String division,
            @Param("unitName") String unitName,
            @Param("position") String position,
            @Param("branch") String branch,
            @Param("employeeType") String employeeType,
            @Param("status") String status,
            @Param("month") String month,
            @Param("year") String year
    );

    List<MasterHold> findByCompanyIdAndIdIn(Long companyId, List<Long> ids);

    @Query("SELECT DISTINCT h.division FROM MasterHold h WHERE h.companyId = :companyId AND h.division IS NOT NULL AND h.division != '' ORDER BY h.division ASC")
    List<String> findDistinctDivisions(@Param("companyId") Long companyId);

    @Query("SELECT DISTINCT h.unitName FROM MasterHold h WHERE h.companyId = :companyId AND h.unitName IS NOT NULL AND h.unitName != '' ORDER BY h.unitName ASC")
    List<String> findDistinctUnits(@Param("companyId") Long companyId);

    @Query("SELECT DISTINCT h.position FROM MasterHold h WHERE h.companyId = :companyId AND h.position IS NOT NULL AND h.position != '' ORDER BY h.position ASC")
    List<String> findDistinctPositions(@Param("companyId") Long companyId);

    @Query("SELECT DISTINCT h.branch FROM MasterHold h WHERE h.companyId = :companyId AND h.branch IS NOT NULL AND h.branch != '' ORDER BY h.branch ASC")
    List<String> findDistinctBranches(@Param("companyId") Long companyId);

    @Query("SELECT DISTINCT h.statusKetenagakerjaan FROM MasterHold h WHERE h.companyId = :companyId AND h.statusKetenagakerjaan IS NOT NULL AND h.statusKetenagakerjaan != '' ORDER BY h.statusKetenagakerjaan ASC")
    List<String> findDistinctEmployeeTypes(@Param("companyId") Long companyId);

    @Query("SELECT DISTINCT h.monthPayroll FROM MasterHold h WHERE h.companyId = :companyId AND h.monthPayroll IS NOT NULL AND h.monthPayroll != '' ORDER BY h.monthPayroll ASC")
    List<String> findDistinctMonths(@Param("companyId") Long companyId);

    @Query("SELECT DISTINCT h.yearPayroll FROM MasterHold h WHERE h.companyId = :companyId AND h.yearPayroll IS NOT NULL AND h.yearPayroll != '' ORDER BY h.yearPayroll DESC")
    List<String> findDistinctYears(@Param("companyId") Long companyId);

    @Query("SELECT DISTINCT h.statusApprovalRelease FROM MasterHold h WHERE h.companyId = :companyId AND h.statusApprovalRelease IS NOT NULL AND h.statusApprovalRelease != '' ORDER BY h.statusApprovalRelease ASC")
    List<String> findDistinctApprovalStatuses(@Param("companyId") Long companyId);

    Long countByCompanyId(Long companyId);

    Long countByCompanyIdAndStatusApprovalRelease(Long companyId, String statusApprovalRelease);

    @Query("SELECT COALESCE(SUM(h.thpHold), 0) FROM MasterHold h WHERE h.companyId = :companyId")
    Double sumThpHoldByCompanyId(@Param("companyId") Long companyId);
}
