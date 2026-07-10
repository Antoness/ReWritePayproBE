package com.payroll.modules.ptkp;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MasterPtkpService {

    private final MasterPtkpRepository ptkpRepository;
    private final HistoryPtkpRepository historyRepository;

    public Page<?> findPtkpData(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        
        String searchTerm = (search != null && !search.trim().isEmpty()) ? search.trim() : null;
        return ptkpRepository.searchPtkpNative(searchTerm, pageable);
    }

    public Page<?> getHistoryData(String search, int page, int size) {
        // We use PageRequest without Sort since the native query has ORDER BY embedded.
        Pageable pageable = PageRequest.of(page, size);
        String searchTerm = (search != null && !search.trim().isEmpty()) ? search.trim() : null;
        return historyRepository.searchHistoryNative(searchTerm, pageable);
    }

    @Transactional
    public Map<String, Object> addPtkp(Map<String, Object> payload, String username) {
        try {
            String tipe = (String) payload.get("tipe");
            String nominal = (String) payload.get("nominal");

            if (ptkpRepository.existsByNameIgnoreCase(tipe)) {
                return Map.of("success", false, "message", "Tipe PTKP already exists!");
            }

            MasterPtkp ptkp = new MasterPtkp();
            ptkp.setName(tipe);
            ptkp.setValue(nominal);
            ptkp.setCreatedBy(username);
            ptkp.setCreatedDate(LocalDateTime.now());
            
            ptkp = ptkpRepository.save(ptkp);

            HistoryPtkp history = new HistoryPtkp();
            history.setIdParent(ptkp.getId());
            history.setName(tipe);
            history.setValue(nominal);
            history.setCreatedBy(username);
            history.setCreatedDate(LocalDateTime.now());
            history.setStatus("ADD");
            historyRepository.save(history);

            return Map.of("success", true, "message", "Data added successfully");
        } catch (Exception e) {
            return Map.of("success", false, "message", "Error adding data: " + e.getMessage());
        }
    }

    @Transactional
    public Map<String, Object> updatePtkp(Map<String, Object> payload, String username) {
        try {
            Long id = Long.valueOf(payload.get("id").toString());
            String tipe = (String) payload.get("tipe");
            String nominal = (String) payload.get("nominal");

            MasterPtkp ptkp = ptkpRepository.findById(id).orElse(null);
            if (ptkp == null) {
                return Map.of("success", false, "message", "Data not found!");
            }

            HistoryPtkp history = new HistoryPtkp();
            history.setIdParent(ptkp.getId());
            history.setName(tipe);
            history.setValue(ptkp.getValue()); // old value
            history.setValueUpdate(nominal);   // new value
            history.setCreatedBy(username);
            history.setCreatedDate(LocalDateTime.now());
            history.setStatus("UPDATE");
            historyRepository.save(history);

            ptkp.setName(tipe);
            ptkp.setValue(nominal);
            ptkp.setUpdateBy(username);
            ptkp.setUpdateDate(LocalDateTime.now());
            
            ptkpRepository.save(ptkp);

            return Map.of("success", true, "message", "Data updated successfully");
        } catch (Exception e) {
            return Map.of("success", false, "message", "Error updating data: " + e.getMessage());
        }
    }
}
