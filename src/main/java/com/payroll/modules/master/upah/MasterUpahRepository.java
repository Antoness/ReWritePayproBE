package com.payroll.modules.master.upah;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MasterUpahRepository extends JpaRepository<MasterUpah, Long> {
    List<MasterUpah> findByCompanyId(Long companyId);
    List<MasterUpah> findByTahun(String tahun);
    List<MasterUpah> findByCompanyIdAndTahun(Long companyId, String tahun);
}
