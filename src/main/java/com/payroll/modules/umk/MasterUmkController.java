package com.payroll.modules.umk;

import com.payroll.security.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/master-umk")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MasterUmkController {

    private final MasterUmkService masterUmkService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/dropdowns")
    public ResponseEntity<?> getDropdowns() {
        return ResponseEntity.ok(masterUmkService.getDropdownData());
    }

    @GetMapping("/list")
    public ResponseEntity<?> getUmkList(
            @RequestHeader("Authorization") String token,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String branch,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String year,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        String jwt = token.substring(7); // Remove "Bearer "
        Claims claims = jwtTokenProvider.getClaims(jwt);
        String position = claims.get("position", String.class);
        String fullName = claims.get("fullName", String.class);

        return ResponseEntity.ok(masterUmkService.findUmkData(position, fullName, search, branch, status, year, page, size));
    }

    @GetMapping("/history")
    public ResponseEntity<?> getUmkHistory(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(masterUmkService.findUmkHistory(search, page, size));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUmk(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> data
    ) {
        String jwt = token.substring(7);
        Claims claims = jwtTokenProvider.getClaims(jwt);
        String fullName = claims.get("fullName", String.class);
        return ResponseEntity.ok(masterUmkService.saveUmk(data, fullName));
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUmk(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> data
    ) {
        String jwt = token.substring(7);
        Claims claims = jwtTokenProvider.getClaims(jwt);
        String fullName = claims.get("fullName", String.class);
        String nik = claims.get("nik", String.class);
        return ResponseEntity.ok(masterUmkService.updateUmk(data, fullName, nik));
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadUmkData(
            @RequestHeader("Authorization") String token,
            @RequestParam("file") MultipartFile file,
            @RequestParam("year") String year
    ) {
        String jwt = token.substring(7);
        Claims claims = jwtTokenProvider.getClaims(jwt);
        String fullName = claims.get("fullName", String.class);
        return ResponseEntity.ok(masterUmkService.processUpload(file, year, fullName));
    }

    @GetMapping("/template")
    public ResponseEntity<byte[]> downloadTemplate() {
        try (Workbook workbook = new HSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Template UMK");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("area");
            headerRow.createCell(1).setCellValue("nominal");

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
            headers.setContentDispositionFormData("attachment", "Template_Umk.xls");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(outputStream.toByteArray());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
