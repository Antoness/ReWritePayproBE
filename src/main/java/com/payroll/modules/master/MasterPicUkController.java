package com.payroll.modules.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/master-pic-uk")
public class MasterPicUkController {

    @Autowired
    private MasterPicUkService masterPicUkService;

    @PostMapping("/list")
    public ResponseEntity<Page<MasterPicUkResponseDTO>> getList(
            @RequestHeader(value = "fullname", required = false) String fullname,
            @RequestHeader(value = "role", required = false) String role,
            @RequestBody MasterPicUkRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        if (fullname == null) fullname = "Staff HRD";
        
        return ResponseEntity.ok(masterPicUkService.searchMasterPicUk(request, page, size, fullname, role));
    }

    @PostMapping("/history-log")
    public ResponseEntity<Page<HistoryMfeeUkResponseDTO>> getHistoryLog(
            @RequestHeader(value = "fullname", required = false) String fullname,
            @RequestHeader(value = "role", required = false) String role,
            @RequestBody MasterPicUkRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        if (fullname == null) fullname = "Staff HRD";
        
        return ResponseEntity.ok(masterPicUkService.getHistoryLog(request, page, size, fullname, role));
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assignModeUk(@RequestBody MasterPicUkRequest request) {
        masterPicUkService.assignModeUk(request);
        return ResponseEntity.ok(Map.of("message", "Success", "status", "success"));
    }

    @PostMapping("/request-update-mfee")
    public ResponseEntity<?> requestUpdateMfee(
            @RequestHeader(value = "fullname", required = false) String fullname,
            @RequestBody MasterPicUkRequest request) {
        if (fullname == null) fullname = "Staff HRD";
        masterPicUkService.requestUpdateMfee(request, fullname);
        return ResponseEntity.ok(Map.of("message", "Success", "status", "success"));
    }

    @PostMapping("/approve-mfee")
    public ResponseEntity<?> approveMfee(@RequestBody MasterPicUkRequest request) {
        masterPicUkService.approveMfee(request);
        return ResponseEntity.ok(Map.of("message", "Success", "status", "success"));
    }

    @PostMapping("/reject-mfee")
    public ResponseEntity<?> rejectMfee(@RequestBody MasterPicUkRequest request) {
        masterPicUkService.rejectMfee(request);
        return ResponseEntity.ok(Map.of("message", "Success", "status", "success"));
    }
}
