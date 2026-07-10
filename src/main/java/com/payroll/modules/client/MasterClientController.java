package com.payroll.modules.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/master-client")
public class MasterClientController {

    @Autowired
    private MasterClientService masterClientService;

    // TODO: Extract userId and fullname from JWT token in the future.
    // For now, passing them as parameters to simulate JWT extraction.
    
    @PostMapping("/staff/list")
    public ResponseEntity<List<MasterClientResponseDTO>> getStaffList(
            @RequestHeader(value = "user-id", required = false) Long userId,
            @RequestHeader(value = "fullname", required = false) String fullname,
            @RequestBody MasterClientSearchRequest request) {
        // Fallback for testing if headers are not provided
        if (userId == null) userId = 1L;
        if (fullname == null) fullname = "Staff HRD";
        
        return ResponseEntity.ok(masterClientService.getStaffList(userId, fullname, request));
    }

    @PostMapping("/staff/export")
    public ResponseEntity<List<MasterClientResponseDTO>> exportStaffData(
            @RequestHeader(value = "user-id", required = false) Long userId,
            @RequestHeader(value = "fullname", required = false) String fullname,
            @RequestHeader(value = "upliner-name", required = false) String uplinerName,
            @RequestBody MasterClientSearchRequest request) {
        
        if (userId == null) userId = 1L;
        if (fullname == null) fullname = "Staff HRD";
        if (uplinerName == null) uplinerName = "SPV HRD";

        if (request.getIds() != null && !request.getIds().isEmpty()) {
            return ResponseEntity.ok(masterClientService.getStaffExportByChecklist(request.getIds()));
        } else {
            return ResponseEntity.ok(masterClientService.getStaffExportByFilter(userId, fullname, uplinerName, request));
        }
    }

    @PostMapping("/spv/list")
    public ResponseEntity<List<MasterClientResponseDTO>> getSpvList(@RequestBody MasterClientSearchRequest request) {
        return ResponseEntity.ok(masterClientService.getSpvList(request));
    }

    @PostMapping("/spv/export")
    public ResponseEntity<List<MasterClientResponseDTO>> exportSpvData(
            @RequestHeader(value = "fullname", required = false) String fullname,
            @RequestBody MasterClientSearchRequest request) {
        
        if (fullname == null) fullname = "SPV HRD";

        if (request.getIds() != null && !request.getIds().isEmpty()) {
            return ResponseEntity.ok(masterClientService.getSpvExportByChecklist(request.getIds()));
        } else {
            return ResponseEntity.ok(masterClientService.getSpvExportByFilter(fullname, request));
        }
    }
}