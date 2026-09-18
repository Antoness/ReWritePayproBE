package com.payroll.modules.master.upah;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/master/upah")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MasterUpahController {

    private final MasterUpahService service;

    @GetMapping
    public ResponseEntity<List<MasterUpah>> getAll(
        @RequestParam(required = false) Long companyId,
        @RequestParam(required = false) String tahun
    ) {
        return ResponseEntity.ok(service.getAll(companyId, tahun));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MasterUpah> getById(@PathVariable Long id) {
        return service.getById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MasterUpah> create(
        @RequestBody MasterUpah entity,
        @RequestParam(required = false) Long companyId
    ) {
        return ResponseEntity.ok(service.create(entity, companyId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MasterUpah> update(
        @PathVariable Long id,
        @RequestBody MasterUpah entity
    ) {
        return ResponseEntity.ok(service.update(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(Map.of("success", true, "message", "Master Upah deleted successfully"));
    }
}
