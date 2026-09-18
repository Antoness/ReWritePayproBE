package com.payroll.modules.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MenuPermissionRepository extends JpaRepository<MenuPermission, Long> {
    List<MenuPermission> findByRoleId(Long roleId);
    List<MenuPermission> findByMenuKeyIn(List<String> menuKeys);
    void deleteByRoleId(Long roleId);
}