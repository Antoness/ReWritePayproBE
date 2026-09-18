package com.payroll.modules.settings;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class MasterSettingService {

    private final MasterSettingRepository settingRepository;
    private final MasterSettingHistoryRepository historyRepository;

    @PostConstruct
    public void seedInitialSettings() {
        try {
            if (settingRepository.countByCompanyId(1L) == 0) {
                log.info("Seeding initial master_settings for company_id 1...");
                List<MasterSetting> defaults = List.of(
                    MasterSetting.builder()
                        .companyId(1L)
                        .settingKey("SPT21.DIR.NAME")
                        .settingValue("SULIST")
                        .createdBy("System")
                        .modifyBy("Staff HRD")
                        .createdDate(LocalDateTime.now())
                        .modifyDate(LocalDateTime.now())
                        .build(),
                    MasterSetting.builder()
                        .companyId(1L)
                        .settingKey("SPT21.DIR.NPWP")
                        .settingValue("12345")
                        .createdBy("System")
                        .modifyBy("SPV HRD")
                        .createdDate(LocalDateTime.now())
                        .modifyDate(LocalDateTime.now())
                        .build(),
                    MasterSetting.builder()
                        .companyId(1L)
                        .settingKey("SPT21.SIGNER.TITLE")
                        .settingValue("Direktur Utama")
                        .createdBy("System")
                        .modifyBy("Staff HRD")
                        .createdDate(LocalDateTime.now())
                        .modifyDate(LocalDateTime.now())
                        .build(),
                    MasterSetting.builder()
                        .companyId(1L)
                        .settingKey("SPT21.SIGNER.LOCATION")
                        .settingValue("Jakarta")
                        .createdBy("System")
                        .modifyBy("SPV HRD")
                        .createdDate(LocalDateTime.now())
                        .modifyDate(LocalDateTime.now())
                        .build(),
                    MasterSetting.builder()
                        .companyId(1L)
                        .settingKey("PAYROLL.AUTO_APPROVE_THRESHOLD")
                        .settingValue("10000000")
                        .settingNumber(10000000)
                        .createdBy("System")
                        .modifyBy("System")
                        .createdDate(LocalDateTime.now())
                        .modifyDate(LocalDateTime.now())
                        .build()
                );
                settingRepository.saveAll(defaults);
                log.info("Default master_settings seeded successfully.");
            }
        } catch (Exception e) {
            log.warn("Failed to seed initial master_settings: {}", e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public Page<MasterSettingDTO.Response> getSettings(Long companyId, String search, int page, int size) {
        Pageable pageable = PageRequest.of(Math.max(0, page - 1), size, Sort.by("settingKey").ascending());
        Page<MasterSetting> result = settingRepository.searchSettings(companyId, search, pageable);
        return result.map(this::mapToResponse);
    }

    @Transactional(readOnly = true)
    public MasterSettingDTO.Response getSettingByKeyOrId(Long companyId, String keyOrId) {
        MasterSetting setting = findEntity(companyId, keyOrId);
        return mapToResponse(setting);
    }

    @Transactional
    public MasterSettingDTO.Response createSetting(Long companyId, MasterSettingDTO.Request request, String username) {
        String keyTrimmed = request.getSettingKey().trim();
        if (settingRepository.existsByCompanyIdAndSettingKey(companyId, keyTrimmed)) {
            throw new IllegalArgumentException("Setting key '" + keyTrimmed + "' sudah terdaftar!");
        }

        MasterSetting setting = MasterSetting.builder()
            .companyId(companyId)
            .settingKey(keyTrimmed)
            .settingValue(request.getSettingValue() != null ? request.getSettingValue().trim() : "")
            .settingNumber(request.getSettingNumber())
            .createdBy(username != null ? username : "System")
            .createdDate(LocalDateTime.now())
            .build();

        MasterSetting saved = settingRepository.save(setting);

        // Record history log
        recordHistory(companyId, saved.getSettingKey(), null, saved.getSettingValue(), "CREATE", username);

        return mapToResponse(saved);
    }

    @Transactional
    public MasterSettingDTO.Response updateSetting(Long companyId, String keyOrId, MasterSettingDTO.Request request, String username) {
        MasterSetting existing = findEntity(companyId, keyOrId);
        String oldVal = existing.getSettingValue();

        String newKey = request.getSettingKey() != null ? request.getSettingKey().trim() : existing.getSettingKey();
        if (!existing.getSettingKey().equalsIgnoreCase(newKey) &&
            settingRepository.existsByCompanyIdAndSettingKeyAndIdNot(companyId, newKey, existing.getId())) {
            throw new IllegalArgumentException("Setting key '" + newKey + "' sudah digunakan pada setting lain!");
        }

        existing.setSettingKey(newKey);
        existing.setSettingValue(request.getSettingValue() != null ? request.getSettingValue().trim() : "");
        existing.setSettingNumber(request.getSettingNumber());
        existing.setModifyBy(username != null ? username : "System");
        existing.setModifyDate(LocalDateTime.now());

        MasterSetting updated = settingRepository.save(existing);

        // Record history log
        recordHistory(companyId, updated.getSettingKey(), oldVal, updated.getSettingValue(), "UPDATE", username);

        return mapToResponse(updated);
    }

    @Transactional
    public void deleteSetting(Long companyId, String keyOrId, String username) {
        MasterSetting existing = findEntity(companyId, keyOrId);
        String oldKey = existing.getSettingKey();
        String oldVal = existing.getSettingValue();

        settingRepository.delete(existing);

        // Record history log
        recordHistory(companyId, oldKey, oldVal, null, "DELETE", username);
    }

    @Transactional(readOnly = true)
    public Page<MasterSettingDTO.HistoryResponse> getHistoryLogs(Long companyId, String search, int page, int size) {
        Pageable pageable = PageRequest.of(Math.max(0, page - 1), size);
        Page<MasterSettingHistory> hist = historyRepository.searchHistory(companyId, search, pageable);
        return hist.map(h -> MasterSettingDTO.HistoryResponse.builder()
            .id(h.getId())
            .companyId(h.getCompanyId())
            .settingKey(h.getSettingKey())
            .actionType(h.getActionType())
            .oldValue(h.getOldValue())
            .newValue(h.getNewValue())
            .actionBy(h.getActionBy())
            .actionDate(h.getActionDate())
            .build());
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getKpiSummary(Long companyId) {
        List<MasterSetting> all = settingRepository.findAll().stream()
            .filter(s -> companyId.equals(s.getCompanyId()))
            .toList();

        long totalCount = all.size();
        long totalSpt = all.stream().filter(s -> s.getSettingKey() != null && s.getSettingKey().toUpperCase().startsWith("SPT")).count();
        long totalCustom = totalCount - totalSpt;

        Map<String, Object> kpi = new HashMap<>();
        kpi.put("totalSettings", totalCount);
        kpi.put("totalSpt", totalSpt);
        kpi.put("totalCustom", totalCustom);
        kpi.put("statusConfig", "Aktif");
        return kpi;
    }

    private MasterSetting findEntity(Long companyId, String keyOrId) {
        if (keyOrId == null || keyOrId.isBlank()) {
            throw new IllegalArgumentException("Identifier setting tidak boleh kosong");
        }
        try {
            Long id = Long.parseLong(keyOrId);
            var opt = settingRepository.findByCompanyIdAndId(companyId, id);
            if (opt.isPresent()) return opt.get();
        } catch (NumberFormatException ignored) {}

        return settingRepository.findByCompanyIdAndSettingKey(companyId, keyOrId)
            .orElseThrow(() -> new IllegalArgumentException("Setting dengan key atau id '" + keyOrId + "' tidak ditemukan"));
    }

    private void recordHistory(Long companyId, String settingKey, String oldValue, String newValue, String actionType, String actionBy) {
        try {
            MasterSettingHistory hist = MasterSettingHistory.builder()
                .companyId(companyId)
                .settingKey(settingKey)
                .oldValue(oldValue)
                .newValue(newValue)
                .actionType(actionType)
                .actionBy(actionBy != null ? actionBy : "System")
                .actionDate(LocalDateTime.now())
                .build();
            historyRepository.save(hist);
        } catch (Exception e) {
            log.error("Failed to record setting history: {}", e.getMessage());
        }
    }

    private MasterSettingDTO.Response mapToResponse(MasterSetting s) {
        return MasterSettingDTO.Response.builder()
            .id(s.getId())
            .companyId(s.getCompanyId())
            .settingKey(s.getSettingKey())
            .settingValue(s.getSettingValue())
            .settingNumber(s.getSettingNumber())
            .createdBy(s.getCreatedBy())
            .createdDate(s.getCreatedDate())
            .modifyBy(s.getModifyBy())
            .modifyDate(s.getModifyDate())
            .build();
    }
}
