package com.payroll.modules.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TkuPajakRepository extends JpaRepository<TkuPajak, Integer> {
    Optional<TkuPajak> findByNik(String nik);
}
