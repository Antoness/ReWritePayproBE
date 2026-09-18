package com.payroll.modules.role;

import java.util.HashSet;
import java.util.Set;

public class RoleDto {
    private String name;
    private String description;
    private Set<PermissionItem> permissions = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<PermissionItem> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionItem> permissions) {
        this.permissions = permissions;
    }

    public static class PermissionItem {
        private String menuKey;
        private boolean canAccess;

        public String getMenuKey() {
            return menuKey;
        }

        public void setMenuKey(String menuKey) {
            this.menuKey = menuKey;
        }

        public boolean isCanAccess() {
            return canAccess;
        }

        public void setCanAccess(boolean canAccess) {
            this.canAccess = canAccess;
        }
    }

    public Set<MenuPermission> toPermissionSet() {
        Set<MenuPermission> set = new HashSet<>();
        if (permissions != null) {
            for (PermissionItem pi : permissions) {
                MenuPermission mp = new MenuPermission();
                mp.setMenuKey(pi.getMenuKey());
                mp.setCanAccess(pi.isCanAccess());
                set.add(mp);
            }
        }
        return set;
    }
}