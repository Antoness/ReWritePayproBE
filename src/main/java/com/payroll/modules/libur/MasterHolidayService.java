package com.payroll.modules.libur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.*;
import com.payroll.modules.audit.AuditLog;
import com.payroll.modules.audit.AuditLogRepository;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class MasterHolidayService {

    @Autowired
    private MasterHolidayRepository holidayRepository;

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private HistoryHolidayRepository historyHolidayRepository;

    public Page<HistoryHoliday> getHolidayHistory(Pageable pageable) {
        return historyHolidayRepository.findAllByOrderByCreatedDateDesc(pageable);
    }

    public Page<MasterHolidayResponseDTO> getAllHolidays(String year, String month, String keyword, Pageable pageable) {
        Page<MasterHoliday> holidays = holidayRepository.findHolidaysWithFilters(year, month, keyword, pageable);
        return holidays.map(MasterHolidayResponseDTO::new);
    }

    @Transactional
    public MasterHolidayResponseDTO addHoliday(MasterHolidayRequestDTO dto, String currentUser) {
        MasterHoliday holiday = new MasterHoliday();
        holiday.setBulanLibur(dto.getTanggal());
        holiday.setHari(dto.getKeterangan());
        holiday.setCreatedBy(currentUser);
        
        holiday = holidayRepository.save(holiday);
        
        saveHistoryHoliday(holiday, "ADD", currentUser);
        saveAuditLog("ADD", "Created holiday for " + dto.getTanggal(), currentUser);
        
        return new MasterHolidayResponseDTO(holiday);
    }

    @Transactional
    public MasterHolidayResponseDTO updateHoliday(Long id, MasterHolidayRequestDTO dto, String currentUser) {
        Optional<MasterHoliday> optionalHoliday = holidayRepository.findById(id);
        if (optionalHoliday.isEmpty()) {
            throw new RuntimeException("Holiday not found with ID: " + id);
        }

        MasterHoliday holiday = optionalHoliday.get();
        holiday.setBulanLibur(dto.getTanggal());
        holiday.setHari(dto.getKeterangan());
        holiday.setModifyBy(currentUser);
        
        holiday = holidayRepository.save(holiday);
        
        saveHistoryHoliday(holiday, "UPDATE", currentUser);
        saveAuditLog("UPDATE", "Updated holiday ID " + id + " to " + dto.getTanggal(), currentUser);
        
        return new MasterHolidayResponseDTO(holiday);
    }

    @Transactional
    public void deleteHoliday(Long id, String currentUser) {
        Optional<MasterHoliday> optionalHoliday = holidayRepository.findById(id);
        if (optionalHoliday.isEmpty()) {
            throw new RuntimeException("Holiday not found with ID: " + id);
        }
        MasterHoliday holiday = optionalHoliday.get();
        saveHistoryHoliday(holiday, "DELETE", currentUser);
        holidayRepository.deleteById(id);
        
        saveAuditLog("DELETE", "Deleted holiday ID " + id, currentUser);
    }

    @Transactional
    public Map<String, Object> processUpload(MultipartFile file, String currentUser) {
        Map<String, Object> response = new HashMap<>();
        try (InputStream is = file.getInputStream(); Workbook workbook = WorkbookFactory.create(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            
            int successCount = 0;
            
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                
                Cell tanggalCell = row.getCell(0);
                Cell keteranganCell = row.getCell(1);
                
                if (tanggalCell == null || keteranganCell == null) continue;
                
                DataFormatter dataFormatter = new DataFormatter();
                
                LocalDate parsedDate = null;
                String rawCellVal = "";
                String cellTypeStr = "";
                
                try {
                    cellTypeStr = tanggalCell.getCellType().toString();
                    if (tanggalCell.getCellType() == CellType.NUMERIC) {
                        rawCellVal = String.valueOf(tanggalCell.getNumericCellValue());
                        if (DateUtil.isCellDateFormatted(tanggalCell)) {
                            parsedDate = tanggalCell.getLocalDateTimeCellValue().toLocalDate();
                        } else {
                            // Even if isCellDateFormatted is false, check if it's a valid excel date number
                            double val = tanggalCell.getNumericCellValue();
                            if (DateUtil.isValidExcelDate(val)) {
                                java.util.Date javaDate = DateUtil.getJavaDate(val);
                                parsedDate = javaDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                            }
                        }
                    } else {
                        rawCellVal = dataFormatter.formatCellValue(tanggalCell).trim();
                        if (!rawCellVal.isEmpty()) {
                            String[] patterns = {
                                "dd/MM/yyyy", "yyyy-MM-dd", "d/M/yyyy", "M/d/yy", "d/M/yy", "dd/MM/yy", "dd-MM-yyyy", "yyyy/MM/dd",
                                "dd MMM yyyy", "d MMM yyyy", "dd MMMM yyyy", "d MMMM yyyy"
                            };
                            for (String pattern : patterns) {
                                try {
                                    parsedDate = LocalDate.parse(rawCellVal, DateTimeFormatter.ofPattern(pattern));
                                    break;
                                } catch (Exception e) {
                                    // Try next pattern
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error parsing tanggal cell: " + e.getMessage());
                }
                
                String keterangan = dataFormatter.formatCellValue(keteranganCell).trim();
                
                if (parsedDate == null || keterangan.isEmpty()) {
                    System.out.println("Skipped row " + i + " -> cellType=" + cellTypeStr + ", rawCellVal=" + rawCellVal + ", parsedDate=" + parsedDate + ", keterangan=" + keterangan);
                    continue;
                }
                
                MasterHoliday holiday = new MasterHoliday();
                holiday.setBulanLibur(parsedDate);
                holiday.setHari(keterangan);
                holiday.setCreatedBy(currentUser);
                
                holiday = holidayRepository.save(holiday);
                saveHistoryHoliday(holiday, "UPLOAD", currentUser);
                successCount++;
            }
            
            saveAuditLog("UPLOAD", "Uploaded " + successCount + " holidays", currentUser);
            
            response.put("success", true);
            response.put("message", "Berhasil mengupload " + successCount + " data libur.");
            return response;
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Gagal memproses file: " + e.getMessage());
            return response;
        }
    }

    private void saveHistoryHoliday(MasterHoliday holiday, String status, String currentUser) {
        HistoryHoliday history = new HistoryHoliday();
        history.setIdParent(holiday.getId());
        history.setBulanLibur(holiday.getBulanLibur());
        history.setHari(holiday.getHari());
        history.setStatus(status);
        history.setCreatedBy(currentUser);
        historyHolidayRepository.save(history);
    }

    private void saveAuditLog(String action, String description, String createdBy) {
        AuditLog log = AuditLog.builder()
                .module("Master Libur")
                .action(action)
                .description(description)
                .createdBy(createdBy)
                .createdDate(LocalDateTime.now())
                .build();
        auditLogRepository.save(log);
    }
}
