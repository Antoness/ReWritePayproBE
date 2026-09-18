package com.payroll.modules.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PayrollComponentService {

    @Autowired
    private PayrollComponentRepository repository;

    public List<PayrollComponentResponseDTO> findAll() {
        return repository.findAllByIsActiveTrueOrderByTypeAscNameAsc()
                .stream()
                .map(PayrollComponentResponseDTO::from)
                .collect(Collectors.toList());
    }

    public List<PayrollComponentResponseDTO> findForClient(
            String division, String position, String unitName, String employeeType) {
        String d = division == null ? "" : division;
        String p = position == null ? "" : position;
        String u = unitName == null ? "" : unitName;
        String e = employeeType == null ? "" : employeeType;
        return repository.findMatchingComponents(d, p, u, e)
                .stream()
                .map(PayrollComponentResponseDTO::from)
                .collect(Collectors.toList());
    }

    public PayrollComponentResponseDTO create(PayrollComponentRequestDTO req) {
        PayrollComponent entity = new PayrollComponent();
        mapToEntity(req, entity);
        entity.setIsActive(true);
        return PayrollComponentResponseDTO.from(repository.save(entity));
    }

    public PayrollComponentResponseDTO update(Long id, PayrollComponentRequestDTO req) {
        PayrollComponent entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll component not found: " + id));
        mapToEntity(req, entity);
        entity.setModifiedBy(req.getModifiedBy());
        return PayrollComponentResponseDTO.from(repository.save(entity));
    }

    public void delete(Long id, String deletedBy) {
        PayrollComponent entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll component not found: " + id));
        entity.setIsActive(false);
        entity.setModifiedBy(deletedBy);
        repository.save(entity);
    }

    private void mapToEntity(PayrollComponentRequestDTO req, PayrollComponent entity) {
        if (req.getName() != null) entity.setName(req.getName());
        if (req.getType() != null) entity.setType(req.getType().toUpperCase());
        if (req.getTaxGroup() != null) entity.setTaxGroup(req.getTaxGroup().toUpperCase());
        if (req.getCalcMethod() != null) entity.setCalcMethod(req.getCalcMethod());
        if (req.getDefaultValue() != null) entity.setDefaultValue(req.getDefaultValue());
        entity.setApplyToDivision(req.getApplyToDivision());
        entity.setApplyToPosition(req.getApplyToPosition());
        entity.setApplyToUnitName(req.getApplyToUnitName());
        entity.setApplyToEmployeeType(req.getApplyToEmployeeType());
        if (req.getCreatedBy() != null) entity.setCreatedBy(req.getCreatedBy());
    }
}
