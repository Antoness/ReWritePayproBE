package com.payroll.modules.jabatan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JabatanService {

    private final JabatanRepository repository;
    private final JabatanMapper mapper;

    public List<JabatanDTO> getAll() {
        return mapper.toDtoList(repository.findAll());
    }

    public JabatanDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Jabatan not found with id: " + id));
    }

    @Transactional
    public JabatanDTO create(JabatanDTO dto) {
        if (repository.findByKodeJabatan(dto.getKodeJabatan()).isPresent()) {
            throw new RuntimeException("Kode jabatan already exists");
        }
        Jabatan entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public JabatanDTO update(Long id, JabatanDTO dto) {
        Jabatan entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jabatan not found with id: " + id));
        
        mapper.updateEntityFromDto(dto, entity);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Jabatan not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
