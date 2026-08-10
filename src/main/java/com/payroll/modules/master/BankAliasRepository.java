package com.payroll.modules.master;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAliasRepository extends JpaRepository<BankAlias, Long> {

    @Query(value = "SELECT b.id AS id, b.id_bank AS idBank, b.nama_alias AS namaAlias, b.status AS status, " +
                   "COALESCE(b.updated_date, b.created_date) AS createdDate, " +
                   "u.full_name AS createdBy " +
                   "FROM bank_alias b " +
                   "LEFT JOIN users u ON u.id = COALESCE(b.updated_by, b.created_by) " +
                   "WHERE b.id_bank = :idBank",
           countQuery = "SELECT COUNT(*) FROM bank_alias WHERE id_bank = :idBank",
           nativeQuery = true)
    Page<Object[]> findAliasesByBankIdNative(@Param("idBank") Long idBank, Pageable pageable);

    Optional<BankAlias> findByNamaAliasAndIdBank(String namaAlias, Long idBank);
}
