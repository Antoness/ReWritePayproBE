package com.payroll.modules.libur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/master-holiday")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MasterHolidayController {

    @Autowired
    private MasterHolidayService holidayService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadHolidays(
            @RequestParam("file") MultipartFile file,
            @RequestHeader(value = "fullname", defaultValue = "System") String currentUser) {
        try {
            return ResponseEntity.ok(holidayService.processUpload(file, currentUser));
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/history")
    public ResponseEntity<?> getHistory(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createdDate").descending());
            return ResponseEntity.ok(holidayService.getHolidayHistory(pageable));
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllHolidays(
            @RequestParam(required = false) String year,
            @RequestParam(required = false) String month,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        try {
            Pageable pageable = PageRequest.of(page - 1, size, Sort.by("bulanLibur").ascending());
            Page<MasterHolidayResponseDTO> holidays = holidayService.getAllHolidays(year, month, keyword, pageable);
            
            Map<String, Object> response = new HashMap<>();
            response.put("data", holidays.getContent());
            response.put("currentPage", holidays.getNumber() + 1);
            response.put("totalItems", holidays.getTotalElements());
            response.put("totalPages", holidays.getTotalPages());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping
    public ResponseEntity<?> addHoliday(
            @RequestBody MasterHolidayRequestDTO request,
            @RequestHeader(value = "fullname", defaultValue = "System") String fullname) {
        try {
            MasterHolidayResponseDTO savedHoliday = holidayService.addHoliday(request, fullname);
            return ResponseEntity.ok(savedHoliday);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHoliday(
            @PathVariable Long id,
            @RequestBody MasterHolidayRequestDTO request,
            @RequestHeader(value = "fullname", defaultValue = "System") String fullname) {
        try {
            MasterHolidayResponseDTO updatedHoliday = holidayService.updateHoliday(id, request, fullname);
            return ResponseEntity.ok(updatedHoliday);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHoliday(
            @PathVariable Long id,
            @RequestHeader(value = "fullname", defaultValue = "System") String fullname) {
        try {
            holidayService.deleteHoliday(id, fullname);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
