package com.payroll.modules.role;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/{id}/permissions")
    public ResponseEntity<List<MenuPermission>> getRolePermissions(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getPermissions(id));
    }

    @GetMapping("/permissions/by-position")
    public ResponseEntity<List<MenuPermission>> getPermissionsByPosition(@RequestParam String position) {
        return ResponseEntity.ok(roleService.getPermissionsByPosition(position));
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody RoleDto dto) {
        Role role = new Role();
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        return ResponseEntity.ok(roleService.create(role, dto.toPermissionSet()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody RoleDto dto) {
        Role role = new Role();
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        return ResponseEntity.ok(roleService.update(id, role, dto.toPermissionSet()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}