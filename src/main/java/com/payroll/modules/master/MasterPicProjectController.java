package com.payroll.modules.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/master-pic-project")
public class MasterPicProjectController {

    @Autowired
    private MasterPicProjectService masterPicProjectService;

    @PostMapping("/list")
    public ResponseEntity<?> getList(
            @RequestBody MasterPicProjectRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(value = "fullname", defaultValue = "") String fullname,
            @RequestHeader(value = "role", defaultValue = "") String role) {
        
        Page<MasterPicProjectResponseDTO> result = masterPicProjectService.getList(request, page, size, fullname, role);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assignPic(
            @RequestBody MasterPicProjectAssignRequest request,
            @RequestHeader(value = "fullname", defaultValue = "") String fullname) {
        
        masterPicProjectService.assignPic(request.getIds(), request.getPic(), fullname);
        return ResponseEntity.ok().body("{\"message\": \"Success\"}");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathVariable Long id) {
        MasterPicProjectDetailDTO detail = masterPicProjectService.getDetail(id);
        return ResponseEntity.ok(detail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePic(
            @PathVariable Long id,
            @RequestBody MasterPicProjectUpdateRequest request,
            @RequestHeader(value = "fullname", defaultValue = "") String fullname) {
        
        masterPicProjectService.updatePic(id, request, fullname);
        return ResponseEntity.ok().body("{\"message\": \"Success\"}");
    }
}
