package com.payroll.modules.pkp;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MasterPkpService {

    private final MasterPkpRepository pkpRepository;
    private final HistoryPkpRepository historyRepository;

    public Page<?> findPkpData(String search, int page, int size) {
        // Normalize: kosongkan string jadi null supaya IS NULL check di query jalan
        String searchTerm = (search != null && !search.trim().isEmpty()) ? search.trim() : null;
        Pageable pageable = PageRequest.of(page, size);
        return pkpRepository.searchPkpNative(searchTerm, pageable);
    }

    public Page<?> getHistoryData(String search, int page, int size) {
        String searchTerm = (search != null && !search.trim().isEmpty()) ? search.trim() : null;
        Pageable pageable = PageRequest.of(page, size);
        return historyRepository.searchHistoryNative(searchTerm, pageable);
    }

    @Transactional
    public Map<String, Object> addPkp(Map<String, Object> payload, String username) {
        try {
            String rawValue = String.valueOf(payload.getOrDefault("value", "0")).replaceAll("[^0-9]", "");
            String tax      = (String) payload.get("tax");
            String nTax     = (String) payload.get("taxTanpaNpwp");

            long valueNum = rawValue.isEmpty() ? 0L : Long.parseLong(rawValue);

            MasterPkp pkp = new MasterPkp();
            pkp.setValue(valueNum);
            pkp.setTax(tax);
            pkp.setNTax(nTax);
            pkp.setCreatedBy(username);
            pkp.setCreatedDate(LocalDateTime.now());

            pkp = pkpRepository.save(pkp);

            // Simpan ke history — ADD: hanya data baru, tidak ada "sebelumnya"
            HistoryPkp history = new HistoryPkp();
            history.setIdParent(pkp.getId());   // id master_pkp yang baru di-insert
            history.setValue(valueNum);          // value
            history.setTax(tax);                 // tax
            history.setNTax(nTax);               // n_tax (tax tanpa npwp)
            history.setCreatedBy(username);      // dari JWT fullname
            history.setCreatedDate(LocalDateTime.now());
            history.setStatus("ADD");
            historyRepository.save(history);

            return Map.of("success", true, "message", "Data PKP berhasil ditambahkan");
        } catch (Exception e) {
            return Map.of("success", false, "message", "Gagal menambahkan data: " + e.getMessage());
        }
    }

    @Transactional
    public Map<String, Object> updatePkp(Map<String, Object> payload, String username) {
        try {
            Long id         = Long.valueOf(payload.get("id").toString());
            String rawValue = String.valueOf(payload.getOrDefault("value", "0")).replaceAll("[^0-9]", "");
            String tax      = (String) payload.get("tax");
            String nTax     = (String) payload.get("taxTanpaNpwp");

            long valueNum = rawValue.isEmpty() ? 0L : Long.parseLong(rawValue);

            MasterPkp pkp = pkpRepository.findById(id).orElse(null);
            if (pkp == null) {
                return Map.of("success", false, "message", "Data tidak ditemukan!");
            }

            // Ambil data lama sebelum diupdate (berdasarkan id)
            Long   oldValue = pkp.getValue();   // data sebelumnya: value
            String oldTax   = pkp.getTax();     // data sebelumnya: tax
            String oldNTax  = pkp.getNTax();    // data sebelumnya: n_tax

            // Update master_pkp terlebih dahulu
            pkp.setValue(valueNum);
            pkp.setTax(tax);
            pkp.setNTax(nTax);
            pkp.setUpdateBy(username);           // ambil dari JWT fullname
            pkp.setUpdateDate(LocalDateTime.now());
            pkpRepository.save(pkp);

            // Insert ke history_pkp
            HistoryPkp history = new HistoryPkp();
            history.setIdParent(pkp.getId());                        // id master_pkp
            history.setValue(valueNum);                              // value baru
            history.setValueUpdate(String.valueOf(oldValue != null ? oldValue : 0)); // data sebelumnya
            history.setTax(tax);                                     // tax baru
            history.setTaxUpdate(oldTax);                            // data sebelumnya
            history.setNTax(nTax);                                   // n_tax (tax tanpa npwp) baru
            history.setNTaxUpdate(oldNTax);                          // data sebelumnya
            history.setCreatedBy(username);                          // dari JWT fullname
            history.setCreatedDate(LocalDateTime.now());
            history.setStatus("UPDATE");
            historyRepository.save(history);

            return Map.of("success", true, "message", "Data PKP berhasil diperbarui");
        } catch (Exception e) {
            return Map.of("success", false, "message", "Gagal memperbarui data: " + e.getMessage());
        }
    }
}
