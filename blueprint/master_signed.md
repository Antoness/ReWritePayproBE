# Blueprint Rewrite Spring Boot: Modul `master_signed` & Child Forms

> **Deskripsi Modul:** Modul `master_signed` digunakan untuk mengelola konfigurasi penandatangan (Digital Signature / Authorized Signer) dan parameter sistem lainnya yang disimpan dalam tabel `master_settings` (seperti nama direktur/pejabat penandatangan SPT21, NPWP pejabat, dsb.).
> Dokumen ini menyatukan seluruh logika dari **form utama (`master_signed`)** dan seluruh child form (`add_signed`, `edit_signed`, `log_history_libur`), rincian skema database, verifikasi query/SP/Function, serta padanan arsitektur implementasi di Spring Boot (Controller, Service, Repository, DTO).

---

## 1. Daftar Form Terintegrasi dalam Modul Ini

| No | Nama Form (Low-Code) | Judul / Fungsi | Tipe Interaksi |
|---|---|---|---|
| 1 | `master_signed` | Master Signed | **Form Utama** (Grid Data Settings, Search, Tambah, Edit, Hapus, History) |
| 2 | `add_signed` | Add Signed | Child / Modal Form (Form Tambah Setting Key & Value) |
| 3 | `edit_signed` | Edit Signed | Child / Modal Form (Form Ubah Setting Value) |
| 4 | `log_history_libur` | History Libur / Audit Log | Child Form (Audit log yang terhubung pada tombol history di low-code) |

---

## 2. Analisis MySQL Stored Procedure & Function

### Status Pemanggilan SP & Function:
- **Stored Procedure:** `TIDAK ADA` pemanggilan Stored Procedure (`CALL ...`).
- **Custom Function:** `TIDAK ADA` pemanggilan custom function database.
- **Operasi Database:** Berjalan secara Native CRUD SQL (SELECT, INSERT, UPDATE, DELETE) langsung ke tabel `master_settings`.

> [!NOTE]
> **Temuan Legacy Low-Code (Artifact):**
> Pada aksi `res` di form `master_signed`, `add_signed`, dan `edit_signed`, terdapat query `INSERT INTO history_holiday` yang merupakan artefak copy-paste low-code warisan modul holiday/libur. Pada rewrite **Spring Boot**, audit trail disarankan menggunakan tabel audit khusus setting (misal `master_settings_history` atau Spring Data Envers / `@EntityListeners(AuditingEntityListener.class)`) agar bersih dan terstruktur.

---

## 3. Struktur Tabel & Skema Database (`master_settings`)

### A. Tabel Utama: `master_settings`

| Kolom | Tipe Data | Null | Key | Keterangan |
|---|---|---|---|---|
| `settingkey` | `varchar(255)` | `NO` | `PRI` | Primary Key |
| `settingvalue` | `varchar(4096)` | `YES` | `` | - |
| `settingnumber` | `int` | `YES` | `` | - |
| `created_by` | `varchar(255)` | `YES` | `` | - |
| `created_Date` | `datetime` | `YES` | `` | - |
| `modify_by` | `varchar(255)` | `YES` | `` | - |
| `modify_Date` | `datetime` | `YES` | `` | - |

### B. Contoh Data Riil (`master_settings`):

```json
[
  {"settingkey": "SPT21.DIR.NAME", "settingvalue": "SULIST", "settingnumber": null, "created_by": null, "created_Date": null, "modify_by": "Staff HRD", "modify_Date": "2024-06-05T13:26:45"},
  {"settingkey": "SPT21.DIR.NPWP", "settingvalue": "12345", "settingnumber": null, "created_by": null, "created_Date": null, "modify_by": "SPV HRD", "modify_Date": "2023-10-31T05:48:43"},
]
```

---

## 4. Rincian Teknis Form & Business Logic

### FORM: `master_signed` (Master Signed)

#### A. Komponen Input & Validasi Mandatori
- _Tidak ada validasi mandatori bawaan UI (validasi dilakukan via logic route / backend)._

#### B. Logika Komponen, Routes, Query & Aksi

##### Komponen: `load` (Tipe: `navload`, Label: ``)

- **Route ID:** `154170`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi Query Database:**
```sql
SELECT
settingkey AS "Setting Key",
settingvalue AS "value",
created_date AS "Created Date",
created_by AS "Created By",
modify_date AS "Modify Date",
modify_by AS "Modify By"
FROM master_settings
WHERE 1=1 @?
```

- **Route ID:** `154171`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (smartgrid):** Param1: `$grid`, Param2: `@view_data_libur`

- **Route ID:** `154172`
  - **Kondisi (IF):** `$search <> `
  - **Aksi String Concat:** `@search` + `AND (settingkey LIKE '%` + `$search` + `%'` -> `@search`

- **Route ID:** `154173`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi String Concat:** `@search` + `OR settingvalue LIKE '%` + `$search` + `%')` -> `@search`

- **Route ID:** `154174`
  - **Kondisi (IF):** `$cb_bulan <> `
  - **Aksi String Concat:** `@search` + `AND DATE_FORMAT(bulan_libur,'%m') = '` + `$cb_bulan` + `''` -> `@search`

- **Route ID:** `154175`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Dialog/Pesan:** Teks: `@search`, Request Code: ``, Buttons: `` / ``


##### Komponen: `res` (Tipe: `navresult`, Label: ``)

- **Route ID:** `154176`
  - **Kondisi (IF):** `@+REQUESTCODE  1 OR @+RESPONSECODE  1`
  - **Aksi Trigger Logic:** Eksekusi komponen `$load`

- **Route ID:** `154177`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Trigger Logic:** Eksekusi komponen `$load_combo`

- **Route ID:** `154178`
  - **Kondisi (IF):** `@+REQUESTCODE  delete_data AND @+RESPONSECODE  @+BUTTON1`
  - **Aksi UI Component (setdata):** Param1: `@view_data_libur`, Param2: `$grid (nset)`

- **Route ID:** `154179`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `@id`, Param2: `@view_data_libur ["currentdata",0]`

- **Route ID:** `154180`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi DB (DELETE):** Tabel `master_settings`
    - Fields: `{}`
    - Where: `{"paramargs": {"parameter2": "", "parameter1": "@id"}, "logic": "0", "type": "1", "sqlwhere": "", "param": {"parameter2": "settingkey", "parameter1": "settingkey"}}` (Args: `{}`)

- **Route ID:** `154181`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Trigger Logic:** Eksekusi komponen `$load`

- **Route ID:** `154182`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Trigger Logic:** Eksekusi komponen `$load_combo`

- **Route ID:** `154183`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Query Database:**
```sql
INSERT INTO history_holiday (
      id_parent,
      bulan_libur,
      hari,
      created_date,
      created_by,
      status
) SELECT
id,
bulan_libur,
hari,
?,
?,
"DELETE"
FROM master_holiday
WHERE id=?
```


##### Komponen: `search` (Tipe: `text`, Label: ``)

- **Route ID:** `154184`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi Trigger Logic:** Eksekusi komponen `$load`


##### Komponen: `btn_search` (Tipe: `button`, Label: `SEARCH`)

- **Route ID:** `154185`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi Trigger Logic:** Eksekusi komponen `$load`


##### Komponen: `btn_add` (Tipe: `button`, Label: `+ADD`)

- **Route ID:** `154186`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi Buka Form Baru:** Buka Modal/Form `add_signed` dengan Parameter: `normal`


##### Komponen: `grid` (Tipe: `smartgrid`, Label: ``)

- **Route ID:** `154187`
  - **Kondisi:** `Filter Event`
  - **Aksi Trigger Logic:** Eksekusi komponen `$load`

- **Route ID:** `154188`
  - **Kondisi (IF):** `@+BUTTONGRID  edit`
  - **Aksi UI Component (setdata):** Param1: `@view_data_libur`, Param2: `$grid (nset)`

- **Route ID:** `154189`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Buka Form Baru:** Buka Modal/Form `edit_signed` dengan Parameter: ``

- **Route ID:** `154190`
  - **Kondisi (IF):** `@+BUTTONGRID  delete`
  - **Aksi Dialog/Pesan:** Teks: `Are You Sure ?`, Request Code: `delete_data`, Buttons: `OK` / `Cancel`


##### Komponen: `cb_bulan` (Tipe: `combobox`, Label: ``)

- **Route ID:** `154191`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi Trigger Logic:** Eksekusi komponen `$load`


##### Komponen: `log_history` (Tipe: `button`, Label: `LOG HISTORY`)

- **Route ID:** `154192`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi Buka Form Baru:** Buka Modal/Form `log_history_libur` dengan Parameter: ``


---

### FORM: `add_signed` (Add Signed)

#### A. Komponen Input & Validasi Mandatori
- _Tidak ada validasi mandatori bawaan UI (validasi dilakukan via logic route / backend)._

#### B. Logika Komponen, Routes, Query & Aksi

##### Komponen: `res` (Tipe: `navresult`, Label: ``)

- **Route ID:** `154193`
  - **Kondisi (IF):** `@+REQUESTCODE  insert_data AND @+RESPONSECODE  @+BUTTON1`
  - **Aksi DB (INSERT):** Tabel `master_settings`
    - Fields: `{"settingvalue": "$setting_value", "created_by": "@+SESSION-FULL_NAME", "created_Date": "@+NOW", "settingkey": "$setting_key"}`
    - Where: `{"paramargs": {"parameter2": "", "parameter1": ""}, "logic": "0", "type": "0", "sqlwhere": "", "param": {"parameter2": "settingkey", "parameter1": "settingkey"}}` (Args: `{}`)

- **Route ID:** `154194`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Set Result:** Mengirim status hasil (`1`) kembali ke form pemanggil.

- **Route ID:** `154195`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Tutup Form:** Menutup form aktif.

- **Route ID:** `154196`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Query Database:**
```sql
INSERT INTO history_holiday (
      id_parent,
      bulan_libur,
      hari,
      created_date,
      created_by,
      status
) SELECT
id,
bulan_libur,
hari,
created_date,
created_by,
"ADD"
FROM master_holiday
WHERE id=?
```

- **Route ID:** `154197`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Query Database:**
```sql
SELECT max(id) FROM master_holiday
```


##### Komponen: `save` (Tipe: `button`, Label: `SAVE`)

- **Route ID:** `154198`
  - **Kondisi (IF):** `$setting_key  `
  - **Aksi Dialog/Pesan:** Teks: `[Setting Key] can't be empty !`, Request Code: ``, Buttons: `OK` / ``

- **Route ID:** `154199`
  - **Kondisi:** `ELSE`
  - **Aksi Dialog/Pesan:** Teks: `Are You Sure ?`, Request Code: `insert_data`, Buttons: `Yes` / `No`

- **Route ID:** `154200`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `SystemAction` (`break`):** `{}`

- **Route ID:** `154201`
  - **Kondisi (IF):** `$keterangan   OR $keterangan           `
  - **Aksi Dialog/Pesan:** Teks: `[Keterangan] can't be empty!`, Request Code: ``, Buttons: `OK` / ``

- **Route ID:** `154202`
  - **Kondisi (IF):** `$setting_value = `
  - **Aksi Dialog/Pesan:** Teks: `[value] can't be empty !`, Request Code: ``, Buttons: `OK` / ``

- **Route ID:** `154203`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `SystemAction` (`break`):** `{}`


---

### FORM: `edit_signed` (Edit Signed)

#### A. Komponen Input & Validasi Mandatori
- _Tidak ada validasi mandatori bawaan UI (validasi dilakukan via logic route / backend)._

#### B. Logika Komponen, Routes, Query & Aksi

##### Komponen: `load` (Tipe: `navload`, Label: ``)

- **Route ID:** `154204`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `DefinitionAction` (`arg`):** `{"param5": "", "param4": "", "param3": "", "param2": "", "param1": "id", "param10": "", "param9": "", "param8": "", "param7": "", "param6": ""}`

- **Route ID:** `154205`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$id`, Param2: `@id`

- **Route ID:** `154206`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi Query Database:**
```sql
SELECT * from master_settings WHERE settingkey = ?
```

- **Route ID:** `154207`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$setting_value`, Param2: `!settingvalue`

- **Route ID:** `154208`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$setting_number`, Param2: `!settingnumber`

- **Route ID:** `154209`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$setting_key`, Param2: `!settingkey`


##### Komponen: `update` (Tipe: `button`, Label: `UPDATE`)

- **Route ID:** `154210`
  - **Kondisi (IF):** `$setting_key  `
  - **Aksi Dialog/Pesan:** Teks: `[Setting Key] can't be empty!`, Request Code: ``, Buttons: `OK` / ``

- **Route ID:** `154211`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `SystemAction` (`break`):** `{}`

- **Route ID:** `154212`
  - **Kondisi:** `ELSE`
  - **Aksi Dialog/Pesan:** Teks: `Are You Sure ?`, Request Code: `update_data`, Buttons: `Yes` / `No`


##### Komponen: `res` (Tipe: `navresult`, Label: ``)

- **Route ID:** `154213`
  - **Kondisi (IF):** `@+REQUESTCODE  update_data AND @+RESPONSECODE  @+BUTTON1`
  - **Aksi DB (UPDATE):** Tabel `master_settings`
    - Fields: `{"settingvalue": "$setting_value", "modify_Date": "@+NOW", "modify_by": "@+SESSION-FULL_NAME", "settingkey": "$setting_key"}`
    - Where: `{"paramargs": {"parameter2": "", "parameter1": "$id"}, "logic": "0", "type": "1", "sqlwhere": "", "param": {"parameter2": "settingkey", "parameter1": "settingkey"}}` (Args: `{}`)

- **Route ID:** `154214`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Set Result:** Mengirim status hasil (`1`) kembali ke form pemanggil.

- **Route ID:** `154215`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Tutup Form:** Menutup form aktif.

- **Route ID:** `154216`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Query Database:**
```sql
INSERT INTO history_holiday (
      id_parent,
      bulan_libur,
      hari,
      created_date,
      created_by,
      status
) SELECT
id,
bulan_libur,
hari,
created_date,
created_by,
"UPDATE"
FROM master_holiday
WHERE id=?
```

- **Route ID:** `154217`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Dialog/Pesan:** Teks: `@update(error)`, Request Code: ``, Buttons: `` / ``


---

### FORM: `log_history_libur` (History Libur)

#### A. Komponen Input & Validasi Mandatori
- _Tidak ada validasi mandatori bawaan UI (validasi dilakukan via logic route / backend)._

#### B. Logika Komponen, Routes, Query & Aksi

##### Komponen: `load` (Tipe: `navload`, Label: ``)

- **Route ID:** `165993`
  - **Kondisi (IF):** `@+SESSION-FULL_NAME <> SPV Kertas Kerja OR @+SESSION-FULL_NAME <> Staff Kertas Kerja`
  - **Aksi Query Database:**
```sql
SELECT id,DATE_FORMAT(bulan_libur,"%d/%m/%Y") AS "Tanggal",
hari AS "Keterangan", 
created_by AS "Created By",
created_date AS "Created Date",
status AS "Status"
FROM history_holiday WHERE 1=1 @?
```

- **Route ID:** `165994`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (smartgrid):** Param1: `$grid`, Param2: `@view_data_libur`

- **Route ID:** `165995`
  - **Kondisi (IF):** `$search <> `
  - **Aksi String Concat:** `@search` + `AND (DATE_FORMAT(bulan_libur,"%d/%m/%Y") LIKE '%` + `$search` + `%'` -> `@search`

- **Route ID:** `165996`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi String Concat:** `@search` + `OR hari LIKE '%` + `$search` + `%')` -> `@search`

- **Route ID:** `165997`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi String Concat:** `@search` + `OR status LIKE '%` + `$search` + `%'` -> `@search`

- **Route ID:** `165998`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi String Concat:** `@search` + `OR created_by LIKE '%` + `$search` + `%'` -> `@search`

- **Route ID:** `181761`
  - **Kondisi (IF):** `@+SESSION-FULL_NAME  SPV Kertas Kerja OR @+SESSION-FULL_NAME  Staff Kertas Kerja`
  - **Aksi Query Database:**
```sql
SELECT id,DATE_FORMAT(bulan_libur,"%d/%m/%Y") AS "Tanggal",
hari AS "Keterangan", 
created_by AS "Created By",
created_date AS "Created Date",
status AS "Status"
FROM history_holiday_kertas_kerja WHERE 1=1 @?
```

- **Route ID:** `181762`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (smartgrid):** Param1: `$grid`, Param2: `@view_data_libur`


##### Komponen: `search` (Tipe: `text`, Label: ``)

- **Route ID:** `165999`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi Trigger Logic:** Eksekusi komponen `$load`


##### Komponen: `btn_search` (Tipe: `button`, Label: `Search`)

- **Route ID:** `166000`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi Trigger Logic:** Eksekusi komponen `$load`


##### Komponen: `grid` (Tipe: `smartgrid`, Label: ``)

- **Route ID:** `166001`
  - **Kondisi:** `Filter Event`
  - **Aksi Trigger Logic:** Eksekusi komponen `$load`


---

## 5. Blueprint Arsitektur Spring Boot (Rewrite)

### A. Entity (`MasterSetting.java`)

```java
package com.dika.payroll.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "master_settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterSetting {

    @Id
    @Column(name = "settingkey", length = 255, nullable = false)
    private String settingKey;

    @Column(name = "settingvalue", length = 4096)
    private String settingValue;

    @Column(name = "settingnumber")
    private Integer settingNumber;

    @Column(name = "created_by", length = 255)
    private String createdBy;

    @CreationTimestamp
    @Column(name = "created_Date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "modify_by", length = 255)
    private String modifyBy;

    @UpdateTimestamp
    @Column(name = "modify_Date")
    private LocalDateTime modifyDate;
}
```

### B. Repository (`MasterSettingRepository.java`)

```java
package com.dika.payroll.repository;

import com.dika.payroll.entity.MasterSetting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MasterSettingRepository extends JpaRepository<MasterSetting, String> {

    @Query("SELECT s FROM MasterSetting s WHERE " +
           "(:keyword IS NULL OR :keyword = '' OR " +
           "LOWER(s.settingKey) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(s.settingValue) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<MasterSetting> searchSettings(@Param("keyword") String keyword, Pageable pageable);

    Optional<MasterSetting> findBySettingKey(String settingKey);
}
```

### C. DTOs (`MasterSettingRequestDTO.java` & `MasterSettingResponseDTO.java`)

```java
package com.dika.payroll.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

public class MasterSettingDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateRequest {
        @NotBlank(message = "Setting key tidak boleh kosong")
        private String settingKey;

        @NotBlank(message = "Setting value tidak boleh kosong")
        private String settingValue;

        private Integer settingNumber;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UpdateRequest {
        @NotBlank(message = "Setting value tidak boleh kosong")
        private String settingValue;

        private Integer settingNumber;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private String settingKey;
        private String settingValue;
        private Integer settingNumber;
        private String createdBy;
        private LocalDateTime createdDate;
        private String modifyBy;
        private LocalDateTime modifyDate;
    }
}
```

### D. Service (`MasterSettingService.java`)

```java
package com.dika.payroll.service;

import com.dika.payroll.dto.MasterSettingDTO;
import com.dika.payroll.entity.MasterSetting;
import com.dika.payroll.repository.MasterSettingRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MasterSettingService {

    private final MasterSettingRepository masterSettingRepository;

    @Transactional(readOnly = true)
    public Page<MasterSettingDTO.Response> findAll(String keyword, Pageable pageable) {
        return masterSettingRepository.searchSettings(keyword, pageable)
                .map(this::mapToResponse);
    }

    @Transactional(readOnly = true)
    public MasterSettingDTO.Response findByKey(String settingKey) {
        MasterSetting setting = masterSettingRepository.findBySettingKey(settingKey)
                .orElseThrow(() -> new EntityNotFoundException("Setting key '" + settingKey + "' tidak ditemukan"));
        return mapToResponse(setting);
    }

    @Transactional
    public MasterSettingDTO.Response create(MasterSettingDTO.CreateRequest request, String username) {
        if (masterSettingRepository.existsById(request.getSettingKey())) {
            throw new EntityExistsException("Setting key '" + request.getSettingKey() + "' sudah ada");
        }

        MasterSetting setting = MasterSetting.builder()
                .settingKey(request.getSettingKey())
                .settingValue(request.getSettingValue())
                .settingNumber(request.getSettingNumber())
                .createdBy(username)
                .build();

        MasterSetting saved = masterSettingRepository.save(setting);
        return mapToResponse(saved);
    }

    @Transactional
    public MasterSettingDTO.Response update(String settingKey, MasterSettingDTO.UpdateRequest request, String username) {
        MasterSetting setting = masterSettingRepository.findBySettingKey(settingKey)
                .orElseThrow(() -> new EntityNotFoundException("Setting key '" + settingKey + "' tidak ditemukan"));

        setting.setSettingValue(request.getSettingValue());
        setting.setSettingNumber(request.getSettingNumber());
        setting.setModifyBy(username);

        MasterSetting updated = masterSettingRepository.save(setting);
        return mapToResponse(updated);
    }

    @Transactional
    public void delete(String settingKey) {
        if (!masterSettingRepository.existsById(settingKey)) {
            throw new EntityNotFoundException("Setting key '" + settingKey + "' tidak ditemukan");
        }
        masterSettingRepository.deleteById(settingKey);
    }

    private MasterSettingDTO.Response mapToResponse(MasterSetting s) {
        return MasterSettingDTO.Response.builder()
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
```

### E. REST Controller (`MasterSignedController.java`)

```java
package com.dika.payroll.controller;

import com.dika.payroll.dto.MasterSettingDTO;
import com.dika.payroll.dto.ApiResponse;
import com.dika.payroll.service.MasterSettingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payroll/master-signed")
@RequiredArgsConstructor
public class MasterSignedController {

    private final MasterSettingService masterSettingService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<MasterSettingDTO.Response>>> getAll(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 10, sort = "settingKey") Pageable pageable) {
        Page<MasterSettingDTO.Response> result = masterSettingService.findAll(search, pageable);
        return ResponseEntity.ok(ApiResponse.success("Data berhasil diambil", result));
    }

    @GetMapping("/{settingKey}")
    public ResponseEntity<ApiResponse<MasterSettingDTO.Response>> getByKey(@PathVariable String settingKey) {
        MasterSettingDTO.Response result = masterSettingService.findByKey(settingKey);
        return ResponseEntity.ok(ApiResponse.success("Detail data berhasil diambil", result));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MasterSettingDTO.Response>> create(
            @Valid @RequestBody MasterSettingDTO.CreateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails != null ? userDetails.getUsername() : "SYSTEM";
        MasterSettingDTO.Response result = masterSettingService.create(request, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Data berhasil ditambahkan", result));
    }

    @PutMapping("/{settingKey}")
    public ResponseEntity<ApiResponse<MasterSettingDTO.Response>> update(
            @PathVariable String settingKey,
            @Valid @RequestBody MasterSettingDTO.UpdateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails != null ? userDetails.getUsername() : "SYSTEM";
        MasterSettingDTO.Response result = masterSettingService.update(settingKey, request, username);
        return ResponseEntity.ok(ApiResponse.success("Data berhasil diupdate", result));
    }

    @DeleteMapping("/{settingKey}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String settingKey) {
        masterSettingService.delete(settingKey);
        return ResponseEntity.ok(ApiResponse.success("Data berhasil dihapus", null));
    }
}
```
