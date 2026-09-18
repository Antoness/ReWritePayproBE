package com.payroll.modules.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.payroll.modules.master.MasterAllowance;
import com.payroll.modules.master.MasterAllowanceRepository;
import com.payroll.modules.master.PayrollComponent;
import com.payroll.modules.master.PayrollComponentRepository;

@Service
public class MasterClientDropdownService {

    @Autowired
    private MasterClientDropdownRepository dropdownRepository;

    // Default/base allowances - dari tabel master_allowance yang lama (selalu muncul)
    @Autowired
    private MasterAllowanceRepository masterAllowanceRepository;

    // Tambahan allowances yang dinamis (dari Master Setting) - filtered by division/unit/position/employeeType
    @Autowired
    private PayrollComponentRepository payrollComponentRepository;

    public List<String> getDivisions() {
        return dropdownRepository.getDivisions();
    }

    public List<String> getUnits(String division) {
        return dropdownRepository.getUnits(division);
    }

    public List<String> getPositions(String division, String unitName) {
        return dropdownRepository.getPositions(division, unitName);
    }

    public List<String> getEmployeeTypes(String division, String unitName, String position) {
        return dropdownRepository.getEmployeeTypes(division, unitName, position);
    }

    public List<String> getBranches(String division, String unitName, String position, String employeeType) {
        return dropdownRepository.getBranches(division, unitName, position, employeeType);
    }

    public List<DropdownOptionDTO> getSalaryTypes() {
        return Arrays.asList(
            new DropdownOptionDTO("1", "Daily"),
            new DropdownOptionDTO("2", "Monthly"),
            new DropdownOptionDTO("3", "Weekly")
        );
    }

    /**
     * Default allowances from master_allowance table.
     * Always shown for all clients regardless of division/unit/position/employeeType.
     */
    public List<String> getAllowances() {
        return masterAllowanceRepository.findAllByOrderByIdAsc().stream()
                .map(MasterAllowance::getFieldDeskripsi)
                .collect(Collectors.toList());
    }

    public List<DropdownOptionDTO> getKomponenUpah() {
        return masterAllowanceRepository.findAllByOrderByIdAsc().stream()
                .map(a -> new DropdownOptionDTO(String.valueOf(a.getId()), a.getFieldDeskripsi()))
                .collect(Collectors.toList());
    }

    /**
     * Combined allowances for a specific client context:
     * 1. Base list from master_allowance (always shown)
     * 2. PLUS payroll_components that match division/unit/position/employeeType
     *    (components with no filter = global = also shown for all)
     * Uses LinkedHashSet to maintain order and remove duplicates.
     */
    public List<String> getAllowancesForClient(String division, String position,
                                               String unitName, String employeeType) {
        Set<String> combined = new LinkedHashSet<>();

        // 1. Add base defaults from master_allowance
        masterAllowanceRepository.findAllByOrderByIdAsc().stream()
                .map(MasterAllowance::getFieldDeskripsi)
                .forEach(combined::add);

        // 2. Add matching payroll_components (global ones with null filter + division-specific ones)
        String d = division == null ? "" : division;
        String p = position == null ? "" : position;
        String u = unitName == null ? "" : unitName;
        String e = employeeType == null ? "" : employeeType;
        payrollComponentRepository.findMatchingComponents(d, p, u, e).stream()
                .map(PayrollComponent::getName)
                .forEach(combined::add);

        return new ArrayList<>(combined);
    }

    // Hardcoded dropdowns
    public List<String> getWorkDays() {
        return Arrays.asList("5+2", "6+1", "7+0");
    }

    public List<String> getBpjsTkTypes() {
        return Arrays.asList("FIX", "Variable", "None");
    }

    public List<String> getMetodePajak() {
        return Arrays.asList("Gross", "Net", "Gross Up");
    }

    public List<String> getKomponenProject() {
        return Arrays.asList("Gross", "Net");
    }

    public List<String> getBpjsKetenagakerjaanOptions() {
        return Arrays.asList("Yes", "No");
    }

    public List<String> getDitanggungOlehOptions() {
        return Arrays.asList("Perusahaan", "Employee");
    }
}
