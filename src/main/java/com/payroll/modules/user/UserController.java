package com.payroll.modules.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserListResponse>> listUsers(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(userService.getUserList(search, pageable));
    }

    @GetMapping("/potential-upliners/{nik}")
    public ResponseEntity<List<Map<String, String>>> getPotentialUpliners(@PathVariable String nik) {
        return ResponseEntity.ok(userService.getPotentialUpliners(nik));
    }

    @GetMapping("/existing-upliners/{nik}")
    public ResponseEntity<List<Map<String, String>>> getExistingUpliners(@PathVariable String nik) {
        return ResponseEntity.ok(userService.getExistingUpliners(nik));
    }

    @PostMapping("/add-upliner")
    public ResponseEntity<Map<String, String>> addUpliner(@RequestBody Map<String, String> request) {
        String nik = request.get("nik");
        String nikUpliner = request.get("nikUpliner");
        String result = userService.addUpliner(nik, nikUpliner);
        if ("DUPLICATE".equals(result)) {
            return ResponseEntity.badRequest().body(Map.of("message", "Data upliner dengan nik tersebut sudah ada"));
        }
        return ResponseEntity.ok(Map.of("message", "Upliner berhasil ditambahkan"));
    }

    @DeleteMapping("/delete-upliner")
    public ResponseEntity<Map<String, String>> deleteUpliner(
            @RequestParam("nik") String nik,
            @RequestParam("nikUpliner") String nikUpliner) {
        userService.deleteUpliner(nik, nikUpliner);
        return ResponseEntity.ok(Map.of("message", "Upliner berhasil dihapus"));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestBody Map<String, List<Long>> request) {
        List<Long> ids = request.get("ids");
        userService.resetPasswords(ids);
        return ResponseEntity.ok(Map.of("message", "Password berhasil di-reset untuk " + (ids != null ? ids.size() : 0) + " user"));
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequest request) {
        userService.createUser(request);
        return ResponseEntity.ok("User created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserRequest request) {
        userService.updateUser(id, request);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
