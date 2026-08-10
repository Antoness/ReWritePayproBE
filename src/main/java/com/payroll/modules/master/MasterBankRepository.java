package com.payroll.modules.master;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MasterBankRepository extends JpaRepository<MasterBank, Long> {

    Optional<MasterBank> findByNamaBank(String namaBank);
    Optional<MasterBank> findBySwiftCode(String swiftCode);

    @Query(value = "SELECT m.id AS id, m.nama_bank AS namaBank, m.swift_code AS swiftCode, " +
                   "m.kode_bi AS kodeBi, m.status AS status, " +
                   "COALESCE(m.updated_date, m.created_date) AS tanggalInputUpdate, " +
                   "u.full_name AS picInputUpdate " +
                   "FROM master_bank m " +
                   "LEFT JOIN users u ON u.id = COALESCE(m.updated_by, m.created_by) " +
                   "LEFT JOIN bank_alias b ON b.id_bank = m.id " +
                   "WHERE (:keyword IS NULL OR " +
                   "      LOWER(m.nama_bank) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                   "      LOWER(m.swift_code) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                   "      LOWER(m.kode_bi) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                   "      LOWER(b.nama_alias) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
                   "GROUP BY m.id, u.full_name",
           countQuery = "SELECT COUNT(DISTINCT m.id) FROM master_bank m " +
                        "LEFT JOIN bank_alias b ON b.id_bank = m.id " +
                        "WHERE (:keyword IS NULL OR " +
                        "      LOWER(m.nama_bank) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                        "      LOWER(m.swift_code) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                        "      LOWER(m.kode_bi) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                        "      LOWER(b.nama_alias) LIKE LOWER(CONCAT('%', :keyword, '%')))",
           nativeQuery = true)
    Page<MasterBankProjection> searchBanks(@Param("keyword") String keyword, Pageable pageable);
}
