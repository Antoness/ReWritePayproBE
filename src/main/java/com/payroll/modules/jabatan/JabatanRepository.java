package com.payroll.modules.jabatan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JabatanRepository extends JpaRepository<Jabatan, Long> {
    Optional<Jabatan> findByKodeJabatan(String kodeJabatan);
}
