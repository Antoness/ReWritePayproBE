package com.payroll.modules.role;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role_menu_permissions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Column(name = "menu_key", length = 100, nullable = false)
    private String menuKey;

    @Column(name = "can_access", nullable = false)
    private boolean canAccess;

    public Long getId() { return this.id; }
    public void setId(Long id) { this.id = id; }
    public Long getRoleId() { return this.roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }
    public String getMenuKey() { return this.menuKey; }
    public void setMenuKey(String menuKey) { this.menuKey = menuKey; }
    public boolean isCanAccess() { return this.canAccess; }
    public void setCanAccess(boolean canAccess) { this.canAccess = canAccess; }
}