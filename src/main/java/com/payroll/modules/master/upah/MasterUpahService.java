package com.payroll.modules.master.upah;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MasterUpahService {

    private final MasterUpahRepository repository;

    @Transactional(readOnly = true)
    public List<MasterUpah> getAll(Long companyId, String tahun) {
        if (companyId != null && tahun != null && !tahun.isBlank()) {
            return repository.findByCompanyIdAndTahun(companyId, tahun);
        } else if (companyId != null) {
            return repository.findByCompanyId(companyId);
        } else if (tahun != null && !tahun.isBlank()) {
            return repository.findByTahun(tahun);
        }
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<MasterUpah> getById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public MasterUpah create(MasterUpah entity, Long companyId) {
        if (companyId != null) {
            entity.setCompanyId(companyId);
        }
        return repository.save(entity);
    }

    @Transactional
    public MasterUpah update(Long id, MasterUpah updated) {
        return repository.findById(id)
            .map(existing -> {
                existing.setLokasiUnitKerja(updated.getLokasiUnitKerja());
                existing.setJaboStatus(updated.getJaboStatus());
                existing.setUpahMinimum(updated.getUpahMinimum());
                existing.setTahun(updated.getTahun());
                return repository.save(existing);
            })
            .orElseThrow(() -> new IllegalArgumentException("Master Upah not found with id: " + id));
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
