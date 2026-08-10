package com.payroll.modules.master;

import com.payroll.modules.user.User;
import com.payroll.modules.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class MasterBankService {

    @Autowired
    private MasterBankRepository bankRepository;

    @Autowired
    private BankAliasRepository aliasRepository;

    @Autowired
    private UserRepository userRepository;

    private Long getUserIdByNik(String nik) {
        if (nik == null || nik.isEmpty()) {
            return 1L; // Fallback
        }
        return userRepository.findByNik(nik)
                .map(User::getId)
                .orElse(1L);
    }

    public Page<MasterBankProjection> searchBanks(String keyword, Pageable pageable) {
        return bankRepository.searchBanks(keyword, pageable);
    }

    @Transactional
    public MasterBank addBank(MasterBank bank, String userNik) {
        // Validation: BCA must not have swift code
        if ("BCA".equalsIgnoreCase(bank.getNamaBank().trim()) && 
            bank.getSwiftCode() != null && !bank.getSwiftCode().trim().isEmpty()) {
            throw new RuntimeException("Swift Code hanya diisi untuk bank selain BCA");
        }

        // Validate duplicates
        Optional<MasterBank> existingName = bankRepository.findByNamaBank(bank.getNamaBank().trim());
        if (existingName.isPresent()) {
            throw new RuntimeException("Nama Bank already exists");
        }

        if (bank.getSwiftCode() != null && !bank.getSwiftCode().trim().isEmpty()) {
            Optional<MasterBank> existingSwift = bankRepository.findBySwiftCode(bank.getSwiftCode().trim());
            if (existingSwift.isPresent()) {
                throw new RuntimeException("Swift Code already exist");
            }
        }

        Long userId = getUserIdByNik(userNik);
        bank.setCreatedBy(userId);
        bank.setStatus("Aktif");
        return bankRepository.save(bank);
    }

    @Transactional
    public MasterBank updateBank(Long id, MasterBank updatedBank, String userNik) {
        Optional<MasterBank> opt = bankRepository.findById(id);
        if (opt.isEmpty()) {
            throw new RuntimeException("Bank not found");
        }
        MasterBank bank = opt.get();

        // Validation: BCA must not have swift code
        if ("BCA".equalsIgnoreCase(updatedBank.getNamaBank().trim()) && 
            updatedBank.getSwiftCode() != null && !updatedBank.getSwiftCode().trim().isEmpty()) {
            throw new RuntimeException("Swift Code hanya diisi untuk bank selain BCA");
        }

        // Validate duplicates excluding current bank
        Optional<MasterBank> existingName = bankRepository.findByNamaBank(updatedBank.getNamaBank().trim());
        if (existingName.isPresent() && !existingName.get().getId().equals(id)) {
            throw new RuntimeException("Nama Bank already exists");
        }

        if (updatedBank.getSwiftCode() != null && !updatedBank.getSwiftCode().trim().isEmpty()) {
            Optional<MasterBank> existingSwift = bankRepository.findBySwiftCode(updatedBank.getSwiftCode().trim());
            if (existingSwift.isPresent() && !existingSwift.get().getId().equals(id)) {
                throw new RuntimeException("Swift Code already exist");
            }
        }

        Long userId = getUserIdByNik(userNik);
        bank.setNamaBank(updatedBank.getNamaBank());
        bank.setSwiftCode(updatedBank.getSwiftCode());
        bank.setKodeBi(updatedBank.getKodeBi());
        bank.setStatus(updatedBank.getStatus());
        bank.setUpdatedBy(userId);
        return bankRepository.save(bank);
    }

    @Transactional
    public void deleteBank(Long id) {
        bankRepository.deleteById(id);
    }

    public Page<Map<String, Object>> getAliases(Long bankId, Pageable pageable) {
        Page<Object[]> results = aliasRepository.findAliasesByBankIdNative(bankId, pageable);
        List<Map<String, Object>> content = new ArrayList<>();
        for (Object[] row : results.getContent()) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", row[0]);
            map.put("idBank", row[1]);
            map.put("namaAlias", row[2]);
            map.put("status", row[3]);
            
            if (row[4] != null) {
                if (row[4] instanceof Timestamp) {
                    map.put("createdDate", ((Timestamp) row[4]).toLocalDateTime());
                } else if (row[4] instanceof LocalDateTime) {
                    map.put("createdDate", row[4]);
                } else {
                    map.put("createdDate", row[4].toString());
                }
            } else {
                map.put("createdDate", null);
            }
            map.put("createdBy", row[5]);
            content.add(map);
        }
        return new PageImpl<>(content, pageable, results.getTotalElements());
    }

    @Transactional
    public BankAlias addAlias(Long bankId, BankAlias alias, String userNik) {
        Optional<BankAlias> existing = aliasRepository.findByNamaAliasAndIdBank(alias.getNamaAlias().trim(), bankId);
        if (existing.isPresent()) {
            throw new RuntimeException("Nama Alias already exists for this bank");
        }

        Long userId = getUserIdByNik(userNik);
        alias.setIdBank(bankId);
        alias.setCreatedBy(userId);
        alias.setStatus("Aktif");
        return aliasRepository.save(alias);
    }

    @Transactional
    public BankAlias updateAlias(Long aliasId, BankAlias updatedAlias, String userNik) {
        Optional<BankAlias> opt = aliasRepository.findById(aliasId);
        if (opt.isEmpty()) {
            throw new RuntimeException("Alias not found");
        }
        BankAlias alias = opt.get();

        Optional<BankAlias> existing = aliasRepository.findByNamaAliasAndIdBank(updatedAlias.getNamaAlias().trim(), alias.getIdBank());
        if (existing.isPresent() && !existing.get().getId().equals(aliasId)) {
            throw new RuntimeException("Nama Alias already exists for this bank");
        }

        Long userId = getUserIdByNik(userNik);
        alias.setNamaAlias(updatedAlias.getNamaAlias());
        alias.setStatus(updatedAlias.getStatus());
        alias.setUpdatedBy(userId);
        return aliasRepository.save(alias);
    }

    @Transactional
    public void deleteAlias(Long aliasId) {
        aliasRepository.deleteById(aliasId);
    }
}
