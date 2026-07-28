package com.payroll.modules.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.payroll.modules.master.MasterAllowance;
import com.payroll.modules.master.MasterAllowanceRepository;

@Service
public class MasterClientDropdownService {

    @Autowired
    private MasterClientDropdownRepository dropdownRepository;

    @Autowired
    private MasterAllowanceRepository masterAllowanceRepository;

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

    public List<String> getAllowances() {
        return masterAllowanceRepository.findAllByOrderByIdAsc().stream()
                .map(MasterAllowance::getFieldDeskripsi)
                .collect(Collectors.toList());
    }

    public List<DropdownOptionDTO> getKomponenUpah() {
        List<MasterAllowance> allowances = masterAllowanceRepository.findAllByOrderByIdAsc();
        return allowances.stream()
                .map(a -> new DropdownOptionDTO(String.valueOf(a.getId()), a.getFieldDeskripsi()))
                .collect(Collectors.toList());
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
