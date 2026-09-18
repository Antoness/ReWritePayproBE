package com.payroll.modules.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final MenuPermissionRepository permissionRepository;

    @jakarta.annotation.PostConstruct
    public void seedDefaultRoles() {
        String[] menuKeys = {
            "DASHBOARD",
            "USER_MANAGEMENT", "USER_MANAGEMENT_USER", "USER_MANAGEMENT_ROLE_PRIVILEGE",
            "MASTER", "MASTER_UMK", "MASTER_UPAH", "MASTER_POSISI", "MASTER_UNIT_KERJA_PENEMPATAN", "MASTER_TER", "MASTER_PTKP", "MASTER_PKP", "MASTER_EMPLOYEE", "MASTER_CLIENT", "MASTER_PIC_PROJECT", "MASTER_UANG_KOMPENSASI", "MASTER_LIBUR", "MASTER_SWIFT_CODE", "MASTER_HOLD", "MASTER_SETTING_ASSIGN", "MASTER_TUNJANGAN_INSENTIF",
            "PAYROLL", "PAYROLL_RETRIEVE", "PAYROLL_PROSES", "PAYROLL_PPH",
            "PPH21", "PPH21_LIST", "PPH21_SLIP", "PPH21_SIMULASI", "PPH21_SUMMARY",
            "BUKTI_PEMOTONGAN", "BUKTI_PEMOTONGAN_A1", "BUKTI_PEMOTONGAN_21_PAYROLL", "BUKTI_PEMOTONGAN_26_PAYROLL", "BUKTI_PEMOTONGAN_21_UK",
            "LEMBUR", "LEMBUR_ENTRY",
            "PINJAMAN", "PINJAMAN_ENTRY",
            "KERTAS_KERJA", "KERTAS_KERJA_RETRIEVE", "KERTAS_KERJA_JASA",
            "UANG_KOMPENSASI", "KOMPENSASI_RETRIEVE", "KOMPENSASI_UPLOAD", "KOMPENSASI_UPLOAD_CADANGAN", "KOMPENSASI_SLIP",
            "REPORT", "REPORT_BPJS", "REPORT_REKAP_PPH21", "REPORT_BUPOT_21", "REPORT_BUPOT_A1", "REPORT_BUPOT_26", "REPORT_BUPOT_KOMPENSASI", "REPORT_UBAH_UPAH", "REPORT_IURAN_BPJS", "REPORT_TK_MASUK", "REPORT_TK_KELUAR",
            "SETTING", "SETTING_CHANGE_PASSWORD", "SETTING_TOGGLE_PROJECT"
        };

        // Standard positions matching existing system
        List<RoleSeedConfig> configs = List.of(
            new RoleSeedConfig("SUPER_ADMIN", "Super Administrator Full Access", Set.of(menuKeys)),
            new RoleSeedConfig("BizOps", "Business Operations & Executive Management", Set.of(menuKeys)),
            new RoleSeedConfig("Manajer", "Manager Level Access", Set.of(menuKeys)),
            new RoleSeedConfig("SPV", "General Payroll Supervisor", Set.of(
                "DASHBOARD", "MASTER", "MASTER_UMK", "MASTER_UPAH", "MASTER_POSISI", "MASTER_UNIT_KERJA_PENEMPATAN", "MASTER_TER", "MASTER_PTKP", "MASTER_PKP", "MASTER_EMPLOYEE", "MASTER_CLIENT", "MASTER_PIC_PROJECT", "MASTER_LIBUR", "MASTER_HOLD", "MASTER_SETTING_ASSIGN",
                "KERTAS_KERJA", "KERTAS_KERJA_RETRIEVE", "KERTAS_KERJA_JASA",
                "PAYROLL", "PAYROLL_RETRIEVE", "PAYROLL_PROSES", "PAYROLL_PPH", "PPH21", "PPH21_LIST", "PPH21_SLIP", "PPH21_SUMMARY",
                "BUKTI_PEMOTONGAN", "BUKTI_PEMOTONGAN_A1", "BUKTI_PEMOTONGAN_21_PAYROLL",
                "LEMBUR", "LEMBUR_ENTRY", "PINJAMAN", "PINJAMAN_ENTRY",
                "REPORT", "REPORT_REKAP_PPH21", "REPORT_BUPOT_21", "REPORT_BUPOT_A1",
                "SETTING", "SETTING_CHANGE_PASSWORD"
            )),
            new RoleSeedConfig("Staff", "General Payroll Staff", Set.of(
                "DASHBOARD", "MASTER", "MASTER_EMPLOYEE", "MASTER_CLIENT", "MASTER_LIBUR", "MASTER_HOLD", "MASTER_SETTING_ASSIGN",
                "KERTAS_KERJA", "KERTAS_KERJA_RETRIEVE", "KERTAS_KERJA_JASA",
                "PAYROLL", "PAYROLL_RETRIEVE", "PAYROLL_PROSES",
                "PPH21", "PPH21_LIST", "PPH21_SLIP",
                "LEMBUR", "LEMBUR_ENTRY", "PINJAMAN", "PINJAMAN_ENTRY",
                "SETTING", "SETTING_CHANGE_PASSWORD"
            )),
            new RoleSeedConfig("PIC BPJS", "PIC BPJS Ketenagakerjaan & Kesehatan", Set.of(
                "DASHBOARD", "MASTER", "MASTER_EMPLOYEE", "MASTER_CLIENT",
                "REPORT", "REPORT_BPJS", "REPORT_IURAN_BPJS", "REPORT_TK_MASUK", "REPORT_TK_KELUAR", "REPORT_UBAH_UPAH",
                "SETTING", "SETTING_CHANGE_PASSWORD"
            )),
            new RoleSeedConfig("PIC BPJS(PAYROLL SERVICE)", "PIC BPJS Divisi Payroll Service", Set.of(
                "DASHBOARD", "MASTER", "MASTER_EMPLOYEE", "MASTER_CLIENT",
                "REPORT", "REPORT_BPJS", "REPORT_IURAN_BPJS", "REPORT_TK_MASUK", "REPORT_TK_KELUAR",
                "SETTING", "SETTING_CHANGE_PASSWORD"
            )),
            new RoleSeedConfig("SPV (Kertas Kerja Jasa)", "Supervisor Modul Kertas Kerja Jasa & Kompensasi", Set.of(
                "DASHBOARD", "MASTER", "MASTER_CLIENT", "MASTER_PIC_PROJECT", "MASTER_UANG_KOMPENSASI", "MASTER_SETTING_ASSIGN", "MASTER_UPAH", "MASTER_POSISI", "MASTER_UNIT_KERJA_PENEMPATAN",
                "KERTAS_KERJA", "KERTAS_KERJA_RETRIEVE", "KERTAS_KERJA_JASA",
                "UANG_KOMPENSASI", "KOMPENSASI_RETRIEVE", "KOMPENSASI_UPLOAD", "KOMPENSASI_UPLOAD_CADANGAN", "KOMPENSASI_SLIP",
                "BUKTI_PEMOTONGAN", "BUKTI_PEMOTONGAN_21_UK",
                "REPORT", "REPORT_BUPOT_KOMPENSASI",
                "SETTING", "SETTING_CHANGE_PASSWORD"
            )),
            new RoleSeedConfig("Staff (Kertas Kerja Jasa)", "Staff Entry Kertas Kerja Jasa", Set.of(
                "DASHBOARD", "MASTER", "MASTER_CLIENT", "MASTER_PIC_PROJECT", "MASTER_UANG_KOMPENSASI", "MASTER_UPAH", "MASTER_POSISI", "MASTER_UNIT_KERJA_PENEMPATAN",
                "KERTAS_KERJA", "KERTAS_KERJA_RETRIEVE", "KERTAS_KERJA_JASA",
                "UANG_KOMPENSASI", "KOMPENSASI_RETRIEVE", "KOMPENSASI_UPLOAD", "KOMPENSASI_SLIP",
                "SETTING", "SETTING_CHANGE_PASSWORD"
            )),
            new RoleSeedConfig("SPV (Sales)", "Supervisor Sales & Komisi", Set.of(
                "DASHBOARD", "MASTER", "MASTER_CLIENT", "MASTER_EMPLOYEE",
                "PAYROLL", "PAYROLL_RETRIEVE", "PAYROLL_PROSES",
                "PPH21", "PPH21_LIST", "PPH21_SLIP",
                "SETTING", "SETTING_CHANGE_PASSWORD"
            )),
            new RoleSeedConfig("Staff (Sales)", "Staff Sales & Commission Entry", Set.of(
                "DASHBOARD", "MASTER", "MASTER_EMPLOYEE", "MASTER_CLIENT",
                "PAYROLL", "PAYROLL_RETRIEVE", "PAYROLL_PROSES",
                "PPH21", "PPH21_SLIP",
                "SETTING", "SETTING_CHANGE_PASSWORD"
            )),
            new RoleSeedConfig("SPV(PAYROLL SERVICE)", "Supervisor Payroll Divisi Service", Set.of(
                "DASHBOARD", "MASTER", "MASTER_CLIENT", "MASTER_EMPLOYEE", "MASTER_LIBUR",
                "PAYROLL", "PAYROLL_RETRIEVE", "PAYROLL_PROSES",
                "LEMBUR", "LEMBUR_ENTRY", "PINJAMAN", "PINJAMAN_ENTRY",
                "PPH21", "PPH21_LIST", "PPH21_SLIP",
                "SETTING", "SETTING_CHANGE_PASSWORD"
            )),
            new RoleSeedConfig("Staff(PAYROLL SERVICE)", "Staff Payroll Divisi Service", Set.of(
                "DASHBOARD", "MASTER", "MASTER_CLIENT", "MASTER_EMPLOYEE",
                "PAYROLL", "PAYROLL_RETRIEVE", "PAYROLL_PROSES",
                "LEMBUR", "LEMBUR_ENTRY", "PINJAMAN", "PINJAMAN_ENTRY",
                "PPH21", "PPH21_SLIP",
                "SETTING", "SETTING_CHANGE_PASSWORD"
            ))
        );

        for (RoleSeedConfig cfg : configs) {
            Role role = roleRepository.findByNameIgnoreCase(cfg.name).orElse(null);
            if (role == null) {
                role = new Role();
                role.setName(cfg.name);
                role.setDescription(cfg.description);
                role = roleRepository.save(role);
            }

            if (permissionRepository.findByRoleId(role.getId()).isEmpty()) {
                for (String key : menuKeys) {
                    MenuPermission perm = new MenuPermission();
                    perm.setRoleId(role.getId());
                    perm.setMenuKey(key);
                    perm.setCanAccess(cfg.allowedKeys.contains(key));
                    permissionRepository.save(perm);
                }
            }
        }
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<MenuPermission> getPermissionsByPosition(String position) {
        if (position == null || position.isBlank()) return List.of();
        String posTrimmed = position.trim();

        // 1. Direct role name match in database (case-insensitive exact match)
        var directRole = roleRepository.findByNameIgnoreCase(posTrimmed);
        if (directRole.isPresent()) {
            return permissionRepository.findByRoleId(directRole.get().getId());
        }

        // 2. Normalize by removing extra whitespaces or case
        for (Role r : roleRepository.findAll()) {
            if (r.getName() != null && r.getName().equalsIgnoreCase(posTrimmed)) {
                return permissionRepository.findByRoleId(r.getId());
            }
        }

        // 3. Normalized alias fallback for existing system titles
        String posUpper = posTrimmed.toUpperCase();
        if (posUpper.contains("IT") || posUpper.contains("ADMIN")) {
            return roleRepository.findByNameIgnoreCase("SUPER_ADMIN")
                    .map(r -> permissionRepository.findByRoleId(r.getId()))
                    .orElse(List.of());
        } else if (posUpper.contains("BIZOPS")) {
            return roleRepository.findByNameIgnoreCase("BizOps")
                    .map(r -> permissionRepository.findByRoleId(r.getId()))
                    .orElse(List.of());
        }

        return List.of();
    }

    @Transactional
    public Role create(Role role, Set<MenuPermission> permissions) {
        Role saved = roleRepository.save(role);
        permissions.forEach(p -> p.setRoleId(saved.getId()));
        permissionRepository.saveAll(permissions);
        return saved;
    }

    @Transactional
    public Role update(Long id, Role role, Set<MenuPermission> permissions) {
        Role existing = roleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Role not found"));
        existing.setName(role.getName());
        existing.setDescription(role.getDescription());
        roleRepository.save(existing);

        // replace old permissions
        permissionRepository.deleteByRoleId(id);
        permissions.forEach(p -> p.setRoleId(id));
        permissionRepository.saveAll(permissions);
        return existing;
    }

    @Transactional
    public void delete(Long id) {
        permissionRepository.deleteByRoleId(id);
        roleRepository.deleteById(id);
    }

    public List<MenuPermission> getPermissions(Long roleId) {
        return permissionRepository.findByRoleId(roleId);
    }

    private static class RoleSeedConfig {
        String name;
        String description;
        Set<String> allowedKeys;

        RoleSeedConfig(String name, String description, Set<String> allowedKeys) {
            this.name = name;
            this.description = description;
            this.allowedKeys = allowedKeys;
        }
    }
}