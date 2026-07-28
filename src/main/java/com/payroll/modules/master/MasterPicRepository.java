package com.payroll.modules.master;

import com.payroll.modules.client.MasterPic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterPicRepository extends JpaRepository<MasterPic, Long> {
    List<MasterPic> findByMasterSalaryId(Long masterSalaryId);
    void deleteByMasterSalaryId(Long masterSalaryId);
}
