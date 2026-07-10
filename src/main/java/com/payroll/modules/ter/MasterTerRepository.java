package com.payroll.modules.ter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterTerRepository extends JpaRepository<MasterTer, Long> {
}
