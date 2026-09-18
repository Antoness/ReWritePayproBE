# Blueprint Rewrite Spring Boot: Modul `master_hold` & Child Forms

> **Deskripsi Modul:** Modul `master_hold` digunakan untuk mengelola, menahan (hold), melepaskan (release), menyetujui/menolak pelepasan (approval/reject release), serta membatalkan status hold (cancel hold) pada data penggajian (payroll) dan uang kompensasi (table_uk) karyawan.
> Dokumen blueprint ini menyatukan seluruh logika dari **form utama (`master_hold`)** dan **5 form pendukung/anak**, lengkap dengan full DDL MySQL Function / Stored Procedure dan implementasi padanannya di Spring Boot (Java Service/Method).

---

## 1. Daftar Form Terintegrasi dalam Modul Ini

| No | Nama Form (Low-Code) | Judul / Fungsi | Tipe Interaksi |
|---|---|---|---|
| 1 | `master_hold` | Master Data Hold Payroll | **Form Utama** (Grid, Filter, Action Buttons) |
| 2 | `detail_hold` | Detail Data Hold & Upload Proof | Child / Modal Form (Detail View & File Upload) |
| 3 | `history_hold` | Log Riwayat Aksi Hold & Release | Child / Sub-module (Audit Trail Grid) |
| 4 | `reason_cancel_hold` | Form Alasan Pembatalan Hold | Child / Dialog Modal (Input Reason) |
| 5 | `reason_reject_release` | Form Alasan Penolakan Release | Child / Dialog Modal (Input Reason) |
| 6 | `upload_release_data` | Upload Bukti Release Dokumen | Child / Modal Form (Lampiran Gambar/PDF) |

---

## 2. MySQL Stored Functions / Procedures & Spring Boot Java Equivalents

Di dalam query modul ini, terdeteksi pemanggilan 2 MySQL Custom Function (`format_uang` dan `concat_button`). Berikut adalah full DDL SQL dan padanan implementasi method Spring Boot:

### A. Function: `format_uang`

**Full Query DDL (MySQL):**
```sql
CREATE DEFINER=`root`@`%` FUNCTION `format_uang`(nominal VARCHAR(50)) RETURNS text CHARSET utf8mb4
    DETERMINISTIC
BEGIN 
		DECLARE output TEXT;	
		SET output = (select concat("Rp.", format(IFNULL(nominal,0),0 )));
	
RETURN output;
END
```

**Penjelasan Logika:** Mengubah nilai nominal numeric/string menjadi format rupiah berpemisah ribuan dengan prefix `Rp.`, contoh `1500000` -> `Rp.1,500,000`.

**Spring Boot Java Method Equivalent (Util / Formatter):**
```java
package com.dika.payroll.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CurrencyUtil {
    public static String formatUang(BigDecimal nominal) {
        if (nominal == null) {
            return "Rp.0";
        }
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setGroupingSeparator(',');
        DecimalFormat df = new DecimalFormat("#,##0", symbols);
        return "Rp." + df.format(nominal);
    }
}
```

### B. Function: `concat_button`

**Full Query DDL (MySQL):**
```sql
CREATE DEFINER=`root`@`%` FUNCTION `concat_button`(data_ VARCHAR(100)) RETURNS text CHARSET utf8mb4 COLLATE utf8mb4_unicode_ci
    DETERMINISTIC
BEGIN
		DECLARE output TEXT;
		set @font_color = (select hx_warna2  from rfwarna where kode = data_ COLLATE utf8mb4_general_ci);
		set @background_color = (select hx_warna  from rfwarna where kode = data_ COLLATE utf8mb4_general_ci);


		set output =  CONCAT('<Button style="border-radius: 8px;background-color: ',@background_color, '; color:',@font_color, '" disabled>', `data_`, '</button>');
RETURN output;
END
```

**Penjelasan Logika:** Di sistem low-code legacy, status di-render langsung sebagai tombol HTML dengan warna latar (`hx_warna`) dan warna font (`hx_warna2`) yang diambil dari tabel `rfwarna`. Di arsitektur Spring Boot + Modern Frontend (React/Vue/Angular), backend cukup mengembalikan status string dan frontend merender Badge component yang sesuai.

**Spring Boot / Frontend DTO Equivalent:**
```java
public enum HoldStatusColor {
    APPROVED("#28a745", "#ffffff"),
    REJECT("#dc3545", "#ffffff"),
    PENDING("#ffc107", "#212529");

    private final String bgColor;
    private final String fontColor;
    // constructor & getters
}
```

---

## 3. Struktur Tabel & Database Mapping

1. **`master_hold`**: Tabel utama penyimpan data hold karyawan.
   - `id_hold` (PK, int auto_increment)
   - `id_payroll` (int): Referensi ke penggajian
   - `id_uk` (int): Referensi ke tabel kompensasi `table_uk`
   - `month_payroll` (varchar), `year_payroll` (varchar)
   - `nik`, `name`, `employee_type`, `department`, `division`, `unit_name`, `position`, `branch`, `account_no`, `bank_code`, `thp`
   - `release_date` (date/datetime): Tanggal rilis pembayaran
   - `release_approval_status` (varchar): Status persetujuan ('Approve', 'Reject', 'Pending Cancel', 'Pending Release', dsb.)
   - `release_proof_file` (varchar): URL / nama file bukti transfer release
   - `uploaded_by`, `uploaded_at`
2. **`history_hold`**: Tabel log audit trail untuk setiap aksi hold/release/reject/cancel.
   - `id_history` (PK), `id_hold`
   - `action_type` (varchar): 'Hold', 'Release', 'Reject', 'Pending Cancel', 'Cancel Hold', 'Approve'
   - `action_by` (varchar/int), `action_at` (datetime), `action_note` (text)
   - `nik`, `name`, `employee_type`, `department`, `division`, `unit_name`, `position`, `branch`, `account_no`, `bank_code`, `thp`
3. **`table_uk`**: Tabel data uang kompensasi karyawan (`hold_status` = 0 / 1).
4. **`gambar`**: Tabel lampiran bukti file (`id_gambar`, `url`, `nama_file`, `id_hold_data`).
5. **`buka_tutup_project`**: Tabel pengecekan status buka/tutup periode payroll (`deskripsi = 'unlock'`).
6. **`log_exports`**: Tabel pencatatan log export excel/laporan.

---

## 4. Rincian Teknis Form & Business Logic

### FORM: `master_hold` (Master Hold Payroll)

#### A. Komponen Input & Validasi Mandatori
- _Tidak ada validasi mandatori bawaan UI (semua divalidasi via backend/action route)._

#### B. Logika Komponen, Routes, Query & Aksi

##### Komponen: `load` (Tipe: `navload`, Label: ``)

- **Route ID:** `199343`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi Query Database:**
```sql
SELECT 
id_hold,
id_payroll,
id_uk,
m.nik AS "NIK",
m.name AS "Nama",
CONCAT(MONTHNAME(STR_TO_DATE(m.month_payroll, '%m')),' ',m.year_payroll) AS "Periode Penggajian (bulan)",
m.division AS "Division",
m.unit_name AS "Unit Name",
-- employment_status AS "Status Ketenagakerjaan",
m.employee_type AS "Status Ketenagakerjaan",
bank_code AS "Nama Bank",
bank_branch AS "Cabang Bank",
bank_account_number AS "Norek",
FORMAT_UANG(m.thp) AS "THP Hold",
hold_reason AS "Keterangan",
DATE_FORMAT(release_date,"%d/%m/%Y") AS "Tanggal Release",
-- release_approval_status AS "Status Approval Release",
concat_button(release_approval_status) AS "Status Approval Release",
CASE 
    WHEN LOWER(g.nama_file) NOT LIKE '%.pdf' THEN
        CONCAT(
            '<a href="https://dev.dikahadir.com',
            SUBSTRING(g.url, LOCATE('/payroll/res/storage', g.url)),
            '" target="_blank">Lihat</a>'
        )

    WHEN LOWER(g.nama_file) LIKE '%.pdf' THEN
        CONCAT(
            '<a href="https://dev.dikahadir.com',
            SUBSTRING(g.url, LOCATE('/payroll/res/storage', g.url)),
            '&gen&dname=',
            REPLACE(g.nama_file, '''', ''),
            '" target="_blank">Lihat</a>'
        )
	WHEN g.url IS NULL THEN ''	
END AS "File Upload",
DATE_FORMAT(payroll_date,"%d/%m/%Y") AS "Payroll Date"

FROM master_hold m 
LEFT JOIN gambar g ON m.id_hold = g.id_hold_data
-- LEFT JOIN users u ON p.nik = u.nik
-- LEFT JOIN master_salary ms ON p.master_salary_id = ms.id 
-- LEFT JOIN master_pic mp ON p.master_salary_id = mp.master_salary_id
WHERE m.release_approval_status != 'Cancel Hold' @?
-- AND  (ms.pic =  OR mp.`user` = ) 
ORDER BY m.id_hold DESC
```

- **Route ID:** `199344`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi UI Component (smartgrid):** Param1: `$grid`, Param2: `@data_grid`

- **Route ID:** `199345`
  - **Kondisi (IF):** `$search <> `
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "%'", "param3": "$search", "param2": "AND (nik LIKE '%", "param1": "@search", "param9": "", "param8": "", "param7": "", "result": "@search", "param6": ""}`

- **Route ID:** `199346`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "%'", "param3": "$search", "param2": "OR name LIKE '%", "param1": "@search", "param9": "", "param8": "", "param7": "", "result": "@search", "param6": ""}`

- **Route ID:** `199347`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "%')", "param3": "$search", "param2": "OR division LIKE '%", "param1": "@search", "param9": "", "param8": "", "param7": "", "result": "@search", "param6": ""}`

- **Route ID:** `208329`
  - **Kondisi:** `ELSE`
  - **Aksi Query Database:**
```sql
SELECT 
id_hold,
id_payroll,
id_uk,
nik AS "NIK",
name AS "Nama",
CONCAT(MONTHNAME(STR_TO_DATE(month_payroll, '%m')),' ',year_payroll) AS "Periode Penggajian (bulan)",
division AS "Division",
unit_name AS "Unit Name",
-- employment_status AS "Status Ketenagakerjaan",
employee_type AS "Status Ketenagakerjaan",
bank_code AS "Nama Bank",
bank_branch AS "Cabang Bank",
bank_account_number AS "Norek",
FORMAT_UANG(thp) AS "THP Hold",
hold_reason AS "Keterangan",
DATE_FORMAT(release_date,"%d/%m/%Y") AS "Tanggal Release",
-- release_approval_status AS "Status Approval Release",
concat_button(release_approval_status) AS "Status Approval Release",
CASE 
    WHEN LOWER(g.nama_file) NOT LIKE '%.pdf' THEN
        CONCAT(
            '<a href="https://dev.dikahadir.com',
            SUBSTRING(g.url, LOCATE('/payroll/res/storage', g.url)),
            '" target="_blank">Lihat</a>'
        )

    WHEN LOWER(g.nama_file) LIKE '%.pdf' THEN
        CONCAT(
            '<a href="https://dev.dikahadir.com',
            SUBSTRING(g.url, LOCATE('/payroll/res/storage', g.url)),
            '&gen&dname=',
            REPLACE(g.nama_file, '''', ''),
            '" target="_blank">Lihat</a>'
        )
	WHEN g.url IS NULL THEN ''	
END AS "File Upload",
DATE_FORMAT(payroll_date,"%d/%m/%Y") AS "Payroll Date"
FROM master_hold  m LEFT JOIN gambar g ON m.id_hold = g.id_hold_data
WHERE release_approval_status != 'Cancel Hold' @?
ORDER BY m.id_hold DESC
```

- **Route ID:** `208364`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi UI Component (setdata):** Param1: `@array`, Param2: `$grid (nset)`

- **Route ID:** `208365`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `@array`, Param2: `@array [selected]`

- **Route ID:** `208375`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi UI Component (settext):** Param1: `$buffer`, Param2: `@data_grid(arrayselected)`

- **Route ID:** `208536`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi UI Component (smartgrid):** Param1: `$grid`, Param2: `@data_grid`

- **Route ID:** `210070`
  - **Kondisi (IF):** `$cb_division <> `
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "''", "param3": "$cb_division", "param2": " AND m.division = '", "param1": "@search", "param9": "", "param8": "", "param7": "", "result": "@search", "param6": ""}`

- **Route ID:** `210071`
  - **Kondisi:** `Langsung Eksekusi`

- **Route ID:** `210072`
  - **Kondisi (IF):** `$cb_unit <> `
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "''", "param3": "$cb_unit", "param2": " AND m.unit_name = '", "param1": "@search", "param9": "", "param8": "", "param7": "", "result": "@search", "param6": ""}`

- **Route ID:** `210551`
  - **Kondisi (IF):** `$cb_status_ketenagakerjaan <> `
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "''", "param3": "$cb_status_ketenagakerjaan", "param2": " AND m.employee_type = '", "param1": "@search", "param9": "", "param8": "", "param7": "", "result": "@search", "param6": ""}`

- **Route ID:** `210552`
  - **Kondisi (IF):** `$cb_status_approval <> `
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "''", "param3": "$cb_status_approval", "param2": " AND m.release_approval_status = '", "param1": "@search", "param9": "", "param8": "", "param7": "", "result": "@search", "param6": ""}`

- **Route ID:** `210553`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi Dialog/Pesan:** Teks: `@data_grid(error)`, Request Code: ``, Buttons: `@search` / ``

- **Route ID:** `218846`
  - **Kondisi (IF):** `$cb_month_payroll <> `
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "''", "param3": "$cb_month_payroll", "param2": " AND m.month_payroll = '", "param1": "@search", "param9": "", "param8": "", "param7": "", "result": "@search", "param6": ""}`

- **Route ID:** `218847`
  - **Kondisi (IF):** `$cb_year_payroll <> `
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "''", "param3": "$cb_year_payroll", "param2": " AND m.year_payroll= '", "param1": "@search", "param9": "", "param8": "", "param7": "", "result": "@search", "param6": ""}`

- **Route ID:** `228732`
  - **Kondisi (IF):** `@+SESSION-POSITION  Staff`
  - **Aksi `` (``):** `{}`

- **Route ID:** `228735`
  - **Kondisi:** `ELSE`
  - **Aksi `` (``):** `{}`


##### Komponen: `res` (Tipe: `navresult`, Label: ``)

- **Route ID:** `199349`
  - **Kondisi (IF):** `@+REQUESTCODE  reload`
  - **Aksi Dialog/Pesan:** Teks: `Successfull`, Request Code: ``, Buttons: `OK` / ``

- **Route ID:** `199350`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load"}`

- **Route ID:** `208381`
  - **Kondisi (IF):** `@+REQUESTCODE  approve`
  - **Aksi DB (UPDATE):** Tabel `master_hold`
    - Fields: `{"release_approval_status": "Approved"}`
    - Where: `{"paramargs": {"parameter2": "", "parameter1": ""}, "logic": "0", "type": "2", "sqlwhere": "id_hold IN @?", "param": {"parameter2": "id_payroll", "parameter1": "id_payroll"}}` (Args: `{"0": "@array(arraydb)"}`)

- **Route ID:** `208382`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Dialog/Pesan:** Teks: `Successfull`, Request Code: ``, Buttons: `OK` / ``

- **Route ID:** `208383`
  - **Kondisi:** `Langsung Eksekusi`

- **Route ID:** `208385`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi UI Component (setdata):** Param1: `@array`, Param2: `$grid (nset)`

- **Route ID:** `208386`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `@array`, Param2: `@array [selected]`

- **Route ID:** `208390`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load"}`

- **Route ID:** `208398`
  - **Kondisi (IF):** `@+REQUESTCODE  reject`
  - **Aksi Buka Form Baru:** Buka Modal/Form `reason_reject_release` dengan Parameter: ``

- **Route ID:** `208414`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `DataAction` (`broadcastglobal`):** `{"param2": "@[]", "param1": "pop_up"}`

- **Route ID:** `208776`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
INSERT INTO history_hold 
(id_hold,
action_type,
action_by,
action_at,
nik,
name,
employee_type,
department,
division,
unit_name,
position,
branch,
thp,
uploaded_by
)
SELECT 
id_hold,
release_approval_status,
?,
NOW(),
nik,
name,
employee_type,
department,
division,
unit_name,
position,
branch,
thp,
uploaded_by
FROM master_hold 
WHERE id_hold IN @? AND release_approval_status = 'Approved'
ON DUPLICATE KEY UPDATE
action_at = action_at;
```

- **Route ID:** `208777`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
UPDATE payroll p
JOIN master_hold h ON p.id = h.id_payroll
SET p.hold_status = 2
WHERE h.id_payroll IS NOT NULL AND h.id_hold IN @? AND h.release_approval_status = 'Approved'
```

- **Route ID:** `208778`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
UPDATE table_uk t
JOIN master_hold h ON t.id = h.id_uk
SET t.hold_status = 0
WHERE h.id_uk IS NOT NULL AND h.id_hold IN @? AND h.release_approval_status = 'Approved'
```

- **Route ID:** `208781`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$btn_uncheck_all"}`

- **Route ID:** `210633`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Dialog/Pesan:** Teks: `@array(arraydb)`, Request Code: ``, Buttons: `` / ``

- **Route ID:** `210634`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `DataAction` (`broadcastglobal`):** `{"param2": "@[]", "param1": "pop_up"}`

- **Route ID:** `211761`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load"}`

- **Route ID:** `218827`
  - **Kondisi (IF):** `@+REQUESTCODE  cancel_data`
  - **Aksi Query Database:**
```sql
UPDATE payroll p JOIN master_hold m ON m.id_payroll = p.id
SET p.hold_status = 2 WHERE m.id_hold IN @?
```

- **Route ID:** `218828`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `DataAction` (`broadcastglobal`):** `{"param2": "@[]", "param1": "pop_up"}`

- **Route ID:** `218831`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi DB (DELETE):** Tabel `master_hold`
    - Fields: `{}`
    - Where: `{"paramargs": {"parameter2": "", "parameter1": ""}, "logic": "0", "type": "2", "sqlwhere": "id_hold IN @?", "param": {"parameter2": "id_hold", "parameter1": "id_hold"}}` (Args: `{"0": "@array(arraydb)"}`)

- **Route ID:** `218842`
  - **Kondisi (IF):** `@cancel_data = true`
  - **Aksi Dialog/Pesan:** Teks: `@update_cancel_payroll(error)`, Request Code: ``, Buttons: `OK` / ``

- **Route ID:** `218843`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
INSERT INTO history_hold 
(id_hold,
action_type,
action_by,
action_at,
nik,
name,
employee_type,
department,
division,
unit_name,
position,
branch,
thp,
uploaded_by)
SELECT 
id_hold,
'Cancel',
?,
NOW(),
nik,
name,
employee_type,
department,
division,
unit_name,
position,
branch,
thp,
uploaded_by
FROM master_hold 
WHERE id_hold IN @?
ON DUPLICATE KEY UPDATE
action_at = action_at;
```

- **Route ID:** `218907`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi DB (DELETE):** Tabel `gambar`
    - Fields: `{}`
    - Where: `{"paramargs": {"parameter2": "", "parameter1": ""}, "logic": "0", "type": "2", "sqlwhere": "id_hold_data IN @?", "param": {"parameter2": "url", "parameter1": "url"}}` (Args: `{"0": "@array(arraydb)"}`)

- **Route ID:** `227060`
  - **Kondisi (IF):** `@cancel_data  true`
  - **Aksi Dialog/Pesan:** Teks: `Success`, Request Code: ``, Buttons: `OK` / ``


##### Komponen: `search` (Tipe: `text`, Label: ``)

- **Route ID:** `199351`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$btn_search"}`


##### Komponen: `btn_search` (Tipe: `button`, Label: `SEARCH`)

- **Route ID:** `199352`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load"}`


##### Komponen: `grid` (Tipe: `smartgrid`, Label: ``)

- **Route ID:** `199353`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "filter", "class": "BooleanExpression", "id": "116"}`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load"}`

- **Route ID:** `199354`
  - **Kondisi (IF):** `@+BUTTONGRID  edit`
  - **Aksi UI Component (setdata):** Param1: `@data_grid`, Param2: `$grid(nset)`

- **Route ID:** `199355`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Buka Form Baru:** Buka Modal/Form `upload_release_data` dengan Parameter: ``

- **Route ID:** `199567`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi UI Component (setdata):** Param1: `@data_grid`, Param2: `$grid(nset)`

- **Route ID:** `199569`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi `SystemAction` (`break`):** `{}`

- **Route ID:** `200124`
  - **Kondisi (IF):** `@+BUTTONGRID  new`
  - **Aksi UI Component (setdata):** Param1: `@data_grid`, Param2: `$grid(nset)`

- **Route ID:** `200125`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Buka Form Baru:** Buka Modal/Form `detail_hold` dengan Parameter: ``

- **Route ID:** `208196`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Query Database:**
```sql
SELECT id_payroll,release_proof_file FROM master_hold WHERE id_payroll = ?
```

- **Route ID:** `208197`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Query Database:**
```sql
SELECT url FROM gambar WHERE id_hold_data = ? AND nama_file = ?
```

- **Route ID:** `208198`
  - **Kondisi:** `Langsung Eksekusi`

- **Route ID:** `208199`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi `VariableAction` (`new`):** `{"param4": "@preview[0,0]", "param3": "url", "param2": "@args", "param1": ""}`

- **Route ID:** `208200`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi `VariableAction` (`set`):** `{"param5": "", "param4": "", "param3": "_blank", "param2": "target", "param1": "@args", "param9": "", "param8": "", "param7": "", "param6": ""}`

- **Route ID:** `208201`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi HTTP Request:** Method: `GET`, URL: `generator://webopen/`, Data: `@args`

- **Route ID:** `208300`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Buka Form Baru:** Buka Modal/Form `detail_hold` dengan Parameter: ``

- **Route ID:** `208768`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
SELECT IF(? IS NULL,'payroll project','uang kompensasi')
```

- **Route ID:** `208769`
  - **Kondisi (IF):** `@+BUTTONGRID  view`
  - **Aksi `` (``):** `{}`

- **Route ID:** `208770`
  - **Kondisi (IF):** `@+BUTTONGRID  view`
  - **Aksi `` (``):** `{}`

- **Route ID:** `208771`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Buka Form Baru:** Buka Modal/Form `detail_hold` dengan Parameter: ``


##### Komponen: `load_visible` (Tipe: `navload`, Label: ``)

- **Route ID:** `208391`
  - **Kondisi (IF):** `@+SESSION-POSITION  SPV`
  - **Aksi UI Component (setvisible):** Param1: `$btn_approve`, Param2: `true`

- **Route ID:** `208393`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (setvisible):** Param1: `$btn_rejected`, Param2: `true`

- **Route ID:** `218832`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (setvisible):** Param1: `$btn_cancel`, Param2: `false`


##### Komponen: `cb_division` (Tipe: `combobox`, Label: ``)

- **Route ID:** `199558`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load_combo"}`

- **Route ID:** `199559`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$btn_search"}`


##### Komponen: `cb_unit` (Tipe: `combobox`, Label: ``)

- **Route ID:** `199554`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load_combo"}`

- **Route ID:** `199555`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$btn_search"}`


##### Komponen: `cb_status_approval` (Tipe: `combobox`, Label: ``)

- **Route ID:** `208774`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load_combo"}`

- **Route ID:** `208775`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$btn_search"}`

- **Route ID:** `218841`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi UI Component (smartgrid):** Param1: `$grid`, Param2: `@data_agree1`


##### Komponen: `cb_status_ketenagakerjaan` (Tipe: `combobox`, Label: ``)

- **Route ID:** `218924`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$btn_search"}`

- **Route ID:** `218925`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load_combo"}`


##### Komponen: `load_combo` (Tipe: `navload`, Label: ``)

- **Route ID:** `199544`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi Query Database:**
```sql
SELECT division FROM master_hold GROUP BY division
```

- **Route ID:** `199545`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (setdata):** Param1: `$cb_division`, Param2: `@division`

- **Route ID:** `199548`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi Query Database:**
```sql
SELECT m.release_approval_status FROM master_hold m 
-- WHERE m.release_approval_status != 'Approved'
WHERE m.release_approval_status != 'Cancel Hold'
GROUP BY release_approval_status

UNION

SELECT "Pending Cancel"
```

- **Route ID:** `199549`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
SELECT employee_type FROM master_hold 
WHERE division=? AND unit_name = ? GROUP BY employee_type
```

- **Route ID:** `199550`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (setdata):** Param1: `$cb_status_ketenagakerjaan`, Param2: `@status_ketenagakerjaan`

- **Route ID:** `199556`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi Query Database:**
```sql
SELECT unit_name FROM master_hold WHERE division= ? GROUP BY unit_name
```

- **Route ID:** `199557`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (setdata):** Param1: `$cb_unit`, Param2: `@unit_name`

- **Route ID:** `208772`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (setdata):** Param1: `$cb_status_approval`, Param2: `@approval`

- **Route ID:** `210554`
  - **Kondisi (IF):** `@+SESSION-POSITION  SPV`

- **Route ID:** `210555`
  - **Kondisi:** `ELSE`

- **Route ID:** `210556`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi Query Database:**
```sql
SELECT m.division FROM master_hold m 
WHERE release_approval_status != 'Approved' 
GROUP BY division
```

- **Route ID:** `210557`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi Query Database:**
```sql
SELECT m.unit_name FROM master_hold m 
WHERE m.division= ? AND m.release_approval_status != 'Approved' 
GROUP BY unit_name
```

- **Route ID:** `210558`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
SELECT m.employee_type FROM master_hold m
WHERE m.division=? AND m.unit_name = ? AND m.release_approval_status != 'Approved' 
GROUP BY employee_type
```

- **Route ID:** `210620`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (setdata):** Param1: `$cb_data`, Param2: `Payroll Project`

- **Route ID:** `218844`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
SELECT m.year_payroll FROM master_hold m GROUP BY m.year_payroll ORDER BY m.year_payroll
```

- **Route ID:** `218845`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (setdata):** Param1: `$cb_year_payroll`, Param2: `@year_payroll`


##### Komponen: `btn_check_all` (Tipe: `button`, Label: `Check All / Page`)

- **Route ID:** `208367`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi UI Component (setdata):** Param1: `@dt`, Param2: `$grid (nset)`

- **Route ID:** `208368`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "", "param3": "$buffer (trim)", "param2": "@dt [\"selected\"] (json)", "param1": "", "param9": "", "param8": "", "param7": "", "result": "@dt", "param6": ""}`

- **Route ID:** `208369`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`replace `):** `{"result": "@dt", "param3": ",", "param2": "][", "param1": "@dt"}`

- **Route ID:** `208370`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`replace `):** `{"result": "@dt", "param3": "'[", "param2": "'[,", "param1": "@dt"}`

- **Route ID:** `208371`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load"}`


##### Komponen: `btn_uncheck_all` (Tipe: `button`, Label: `Uncheck All / Page`)

- **Route ID:** `208373`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi UI Component (smartgrid):** Param1: `$grid`, Param2: `@data_agree1`

- **Route ID:** `208374`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load"}`


##### Komponen: `btn_approve` (Tipe: `button`, Label: `Approve`)

- **Route ID:** `208377`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi UI Component (setdata):** Param1: `@array`, Param2: `$grid (nset)`

- **Route ID:** `208378`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi UI Component (settext):** Param1: `@array`, Param2: `@array [selected]`

- **Route ID:** `208379`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi Dialog/Pesan:** Teks: `Are you sure you want to approve this data release?`, Request Code: `approve`, Buttons: `Yes` / `Cancel`


##### Komponen: `btn_rejected` (Tipe: `button`, Label: `Reject`)

- **Route ID:** `208396`
  - **Kondisi:** `Langsung Eksekusi`

- **Route ID:** `208397`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi Dialog/Pesan:** Teks: `Are you sure you want to reject this data release?`, Request Code: `reject`, Buttons: `Yes` / `Cancel`


##### Komponen: `btn_history` (Tipe: `button`, Label: `HISTORY`)

- **Route ID:** `208511`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi Buka Form Baru:** Buka Modal/Form `history_hold` dengan Parameter: ``


##### Komponen: `btn_export_old` (Tipe: `button`, Label: `EXPORT`)

- **Route ID:** `209833`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load"}`

- **Route ID:** `209834`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
SELECT 
    ROW_NUMBER() OVER (ORDER BY id_hold ASC) AS NO,
	MONTHNAME(STR_TO_DATE(month_payroll, '%m')) AS "Bulan",
	year_payroll AS "Year Payroll",
    bank_code AS "Kode Bank",
    bank_account_number AS "No Rek Bank",
    bank_branch AS "Cabang Bank",
    nik AS "NIK",
    name AS "Nama Pegawai",
    thp AS "THP",
    unit_name AS "Dept",
    swift_code AS "Swift Code"
FROM master_hold m
WHERE 1=1 @?

UNION ALL

SELECT 
    NULL AS NO,
    NULL,
    NULL,
	NULL,
    NULL,
    NULL,
    NULL,
    'Total Rekap Hold',
    SUM(thp),
    NULL,
    NULL
FROM master_hold m
WHERE 1=1 @?;
```

- **Route ID:** `210066`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (setdata):** Param1: `@report_hold`, Param2: `@export`

- **Route ID:** `210067`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (setdata):** Param1: `@format`, Param2: `@{"cells":{"A1":""},"copy":2,"start":3,"template":"true","sheetrename":"Template Hold","file":"/home/generator/payroll/storage/Template Hold.xlsx"}`

- **Route ID:** `210068`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `VariableAction` (`set`):** `{"param5": "@periode[0,0]", "param4": "A1", "param3": "@cell", "param2": "cells", "param1": "@format", "param9": "", "param8": "", "param7": "", "param6": ""}`

- **Route ID:** `210069`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `ReportAction` (`export`):** `{"param3": "@format", "param2": "Data hold.xlsx", "param1": "@report_hold"}`

- **Route ID:** `218904`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
SELECT IF(? = '' AND ? = '','',CONCAT("Periode ",MONTHNAME(STR_TO_DATE(?, '%m')),' ',?)) AS "Periode Penggajian (bulan)"
```

- **Route ID:** `218913`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi Query Database:**
```sql
SET lc_time_names = 'id_ID';
```

- **Route ID:** `224033`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
SELECT JSON_OBJECT('division', ?,"unit_name",?,"status_ketenagakerjaan",?,"status_approval",?,"month_payroll",?,"year_payroll",?, 'searchKeyword', ?) ,
concat("Export Master Hold "),
case 
	when ? = "" then "SUCCESS"
	when ? <> "" then "ERROR"
end
```

- **Route ID:** `224034`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi DB (INSERT):** Tabel `log_exports`
    - Fields: `{"created_at": "@+NOW()", "export_filter": "@log_export[0,0]", "status": "@log_export[0,2]", "export_type": "EXCEL", "username": "@+SESSION-FULL_NAME", "menu_name": "Menu Master Hold", "file_name": "Data hold.xlsx", "export_name": "@log_export[0,1]", "user_id": "@+SESSION-ID"}`
    - Where: `{"paramargs": {"parameter2": "", "parameter1": ""}, "logic": "0", "type": "0", "sqlwhere": "", "param": {"parameter2": "id", "parameter1": "id"}}` (Args: `{}`)


##### Komponen: `btn_cancel` (Tipe: `button`, Label: `Cancel Hold`)

- **Route ID:** `218826`
  - **Kondisi:** `ELSE`
  - **Aksi Buka Form Baru:** Buka Modal/Form `reason_cancel_hold` dengan Parameter: ``

- **Route ID:** `218833`
  - **Kondisi (IF):** `$cb_status_approval  `
  - **Aksi Dialog/Pesan:** Teks: `Please select the status you want to cancel first`, Request Code: ``, Buttons: `OK` / ``

- **Route ID:** `218834`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi `SystemAction` (`break`):** `{}`

- **Route ID:** `218835`
  - **Kondisi (IF):** `@array(arraydb)  ()`
  - **Aksi Dialog/Pesan:** Teks: `No data selected. Please check the data you want to process`, Request Code: ``, Buttons: `OK` / ``

- **Route ID:** `218836`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi UI Component (setdata):** Param1: `@array`, Param2: `$grid (nset)`

- **Route ID:** `218837`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi UI Component (settext):** Param1: `@array`, Param2: `@array [selected]`


##### Komponen: `cb_month_payroll` (Tipe: `combobox`, Label: ``)

- **Route ID:** `218848`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$btn_search"}`


##### Komponen: `cb_year_payroll` (Tipe: `combobox`, Label: ``)

- **Route ID:** `218850`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$btn_search"}`


##### Komponen: `load_date` (Tipe: `navload`, Label: ``)

- **Route ID:** `220893`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi Query Database:**
```sql
-- SELECT YEAR(Now())
SELECT MAX(year_payroll) FROM payroll
```

- **Route ID:** `220894`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi UI Component (settext):** Param1: `$cb_year_payroll`, Param2: `@tahun_sekarang[0,0]`

- **Route ID:** `220895`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Query Database:**
```sql
SELECT 
  SUBSTRING(periode, 5) AS lbl_bulan
FROM
	`buka_tutup_project` 
WHERE
	deskripsi = 'unlock' 
ORDER BY
	LEFT ( periode, 4 ) DESC,
	LPAD( SUBSTRING( periode, 5 ), 2, '0' ) DESC 
	LIMIT 1;
```

- **Route ID:** `220896`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi UI Component (settext):** Param1: `$cb_month_payroll`, Param2: `@bulan[0,0]`

- **Route ID:** `220897`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load"}`


##### Komponen: `btn_export` (Tipe: `button`, Label: `EXPORT`)

- **Route ID:** `228725`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load"}`

- **Route ID:** `228726`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Query Database:**
```sql
SELECT IF(? = '' AND ? = '','',CONCAT("Periode ",MONTHNAME(STR_TO_DATE(?, '%m')),' ',?)) AS "Periode Penggajian (bulan)"
```

- **Route ID:** `228728`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
SELECT JSON_OBJECT('division', ?,"unit_name",?,"status_ketenagakerjaan",?,"status_approval",?,"month_payroll",?,"year_payroll",?, 'searchKeyword', ?) ,
concat("Export Master Hold "),
case 
	when ? = "" then "SUCCESS"
	when ? <> "" then "ERROR"
end
```

- **Route ID:** `228729`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi DB (INSERT):** Tabel `log_exports`
    - Fields: `{"created_at": "@+NOW()", "export_filter": "@log_export[0,0]", "status": "@log_export[0,2]", "export_type": "EXCEL", "username": "@+SESSION-FULL_NAME", "menu_name": "Menu Master Hold", "file_name": "Data hold.xlsx", "export_name": "@log_export[0,1]", "user_id": "@+SESSION-ID"}`
    - Where: `{"paramargs": {"parameter2": "", "parameter1": ""}, "logic": "0", "type": "0", "sqlwhere": "", "param": {"parameter2": "id", "parameter1": "id"}}` (Args: `{}`)

- **Route ID:** `228737`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
select url from link_endpoint where id = 1
```

- **Route ID:** `228738`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "'&position=", "param4": "$cb_unit", "param3": "'&unit_name=", "param2": "$cb_division", "param1": "'&division=", "param9": "", "param8": "$cb_status_ketenagakerjaan", "param7": "'&employee_type=", "result": "@param", "param6": "$cb_position"}`

- **Route ID:** `228745`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "$cb_month_payroll", "param4": "'&month_payroll=", "param3": "$cb_status_approval", "param2": "'&release_approval_status=", "param1": "@param", "param9": "$search", "param8": "'&search_keyword=", "param7": "$cb_year_payroll", "result": "@param", "param6": "'&year_payroll="}`

- **Route ID:** `228746`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "", "param3": "@+SESSION-FULL_NAME", "param2": "'&username=", "param1": "@param", "param9": "", "param8": "", "param7": "", "result": "@param", "param6": ""}`

- **Route ID:** `228747`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "", "param3": "@param", "param2": "payroll/report-hold?", "param1": "@endpoint[0,0]", "param9": "", "param8": "", "param7": "", "result": "@url", "param6": ""}`

- **Route ID:** `228748`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `VariableAction` (`new`):** `{"param4": "@url", "param3": "url", "param2": "@args", "param1": ""}`

- **Route ID:** `228749`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `VariableAction` (`set`):** `{"param5": "", "param4": "", "param3": "_blank", "param2": "target", "param1": "@args", "param9": "", "param8": "", "param7": "", "param6": ""}`

- **Route ID:** `228750`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi HTTP Request:** Method: `GET`, URL: `generator://webopen/`, Data: `@args`


---

### FORM: `detail_hold` (No Title)

#### A. Komponen Input & Validasi Mandatori
- _Tidak ada validasi mandatori bawaan UI (semua divalidasi via backend/action route)._

#### B. Logika Komponen, Routes, Query & Aksi

##### Komponen: `load` (Tipe: `navload`, Label: ``)

- **Route ID:** `200126`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `DefinitionAction` (`arg`):** `{"param5": "", "param4": "", "param3": "", "param2": "type", "param1": "id", "param10": "", "param9": "", "param8": "", "param7": "", "param6": ""}`

- **Route ID:** `200127`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi Query Database:**
```sql
SELECT a.*,
DATE_FORMAT(a.release_date,'%d/%m/%Y') AS tgl_release,
format_uang(thp) AS thp1
FROM master_hold a WHERE id_hold = ?
```

- **Route ID:** `200128`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$url`, Param2: `@image[0,0]`

- **Route ID:** `208180`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi DB (SELECT):** Tabel `gambar`
    - Fields: `{}`
    - Where: `{"paramargs": {"parameter2": "", "parameter1": ""}, "logic": "0", "type": "2", "sqlwhere": "nama_file = ? AND id_hold_data = ?", "param": {"parameter2": "url", "parameter1": "url"}}` (Args: `{"1": "@id", "0": "!release_proof_file"}`)

- **Route ID:** `208181`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Dialog/Pesan:** Teks: `@image[0,0]`, Request Code: ``, Buttons: `!release_proof_file` / ``

- **Route ID:** `208182`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$nik`, Param2: `!nik`

- **Route ID:** `208183`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$nama`, Param2: `!name`

- **Route ID:** `208184`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$division`, Param2: `!division`

- **Route ID:** `208185`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$unit`, Param2: `!unit_name`

- **Route ID:** `208186`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$position`, Param2: `!position`

- **Route ID:** `208187`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$branch`, Param2: `!branch`

- **Route ID:** `208188`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$kode_bank`, Param2: `!bank_code`

- **Route ID:** `208189`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$norek`, Param2: `!bank_account_number`

- **Route ID:** `208190`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$thp`, Param2: `!thp1`

- **Route ID:** `208191`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$keterangan`, Param2: `!hold_reason`

- **Route ID:** `208192`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$release_proof_file`, Param2: `!release_proof_file`

- **Route ID:** `208299`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi UI Component (settext):** Param1: `$id`, Param2: `@id`

- **Route ID:** `208421`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
SELECT a.*,DATE_FORMAT(a.action_at, '%d/%m/%Y %H:%i:%s') AS reject_at
FROM history_hold a 
WHERE id_hold = ? AND action_type = 'Reject' ORDER BY id_hold LIMIT 1
```

- **Route ID:** `208434`
  - **Kondisi (IF):** `!release_approval_status  Reject`
  - **Aksi UI Component (setvisible):** Param1: `$layout_vertikal3`, Param2: `true`

- **Route ID:** `208435`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$alasan_reject`, Param2: `!action_note`

- **Route ID:** `208436`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$reject_at`, Param2: `!reject_at`

- **Route ID:** `208437`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (setvisible):** Param1: `$lbl`, Param2: `true`

- **Route ID:** `208535`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$tgl_release`, Param2: `!tgl_release`

- **Route ID:** `208765`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$type`, Param2: `!type`

- **Route ID:** `208785`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi UI Component (setvisible):** Param1: `$btn_save`, Param2: `false`

- **Route ID:** `208786`
  - **Kondisi (IF):** `$release_proof_file <> `
  - **Aksi UI Component (setvisible):** Param1: `$btn_reupload_save`, Param2: `true`

- **Route ID:** `227101`
  - **Kondisi (IF):** `!release_approval_status  Pending Cancel`
  - **Aksi Query Database:**
```sql
SELECT action_note 
FROM history_hold 
WHERE id_hold = ? AND action_type = 'Pending Cancel' 
ORDER BY action_at DESC
LIMIT 1
```

- **Route ID:** `227103`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$cancel_reason`, Param2: `!action_note`

- **Route ID:** `227112`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (setvisible):** Param1: `$cancel_reason`, Param2: `true`


##### Komponen: `preview` (Tipe: `button`, Label: `PREVIEW`)

- **Route ID:** `208193`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `VariableAction` (`new`):** `{"param4": "$url", "param3": "url", "param2": "@args", "param1": ""}`

- **Route ID:** `208194`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `VariableAction` (`set`):** `{"param5": "", "param4": "", "param3": "_blank", "param2": "target", "param1": "@args", "param9": "", "param8": "", "param7": "", "param6": ""}`

- **Route ID:** `208195`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi HTTP Request:** Method: `GET`, URL: `generator://webopen/`, Data: `@args`


##### Komponen: `file_upload` (Tipe: `file`, Label: ``)

- **Route ID:** `208323`
  - **Kondisi (IF):** `$file_upload <> `
  - **Aksi `StyleAction` (`style`):** `{"param2": "color: black;", "param1": "$file_upload"}`


##### Komponen: `upload` (Tipe: `button`, Label: `Upload`)

- **Route ID:** `208276`
  - **Kondisi (IF):** `$file_upload  `
  - **Aksi Dialog/Pesan:** Teks: `Pilih file upload terlebih dahulu`, Request Code: ``, Buttons: `OK` / ``

- **Route ID:** `208277`
  - **Kondisi:** `ELSE`
  - **Aksi `` (``):** `{}`

- **Route ID:** `208278`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi `FormAction` (`submit`):** `{"param2": "upload", "param1": "$file_upload"}`

- **Route ID:** `208301`
  - **Kondisi:** `Langsung Eksekusi`


##### Komponen: `res` (Tipe: `navresult`, Label: ``)

- **Route ID:** `208280`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi UI Component (setdata):** Param1: `@datapic`, Param2: `@+RESULT(nset)`

- **Route ID:** `208281`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "", "param3": "", "param2": "", "param1": "@datapic [\"filename\"] (string)", "param9": "", "param8": "", "param7": "", "result": "@filename", "param6": ""}`

- **Route ID:** `208282`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "", "param3": "", "param2": "'.tmp", "param1": "@datapic [\"fsavename\"]", "param9": "", "param8": "", "param7": "", "result": "@fsavename", "param6": ""}`

- **Route ID:** `208283`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "", "param3": "", "param2": "gen", "param1": "@datapic [\"fsavename\"]", "param9": "", "param8": "", "param7": "", "result": "@fsavenamepdf", "param6": ""}`

- **Route ID:** `208284`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StorageAction` (`storage`):** `{"param7": "@datapic [\"fsavename\"] (string)", "param6": "storage", "param5": "", "param4": "", "param3": "copy", "param2": "@fsavename", "param1": "temp"}`

- **Route ID:** `208285`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StorageAction` (`storage`):** `{"param7": "@fsavenamepdf", "param6": "storage", "param5": "", "param4": "", "param3": "copy", "param2": "@fsavename", "param1": "temp"}`

- **Route ID:** `208286`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$url`, Param2: `@fsavename`

- **Route ID:** `208287`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "", "param3": "", "param2": "@datapic [\"fsavename\"] (string)", "param1": "https://dev.dikahadir.com/payroll/res/storage/?name=", "param9": "", "param8": "", "param7": "", "result": "@url", "param6": ""}`

- **Route ID:** `208288`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "", "param3": "", "param2": "@fsavenamepdf", "param1": "https://dev.dikahadir.com/payroll/res/storage/?name=", "param9": "", "param8": "", "param7": "", "result": "@urlpdf", "param6": ""}`

- **Route ID:** `208289`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi UI Component (settext):** Param1: `$file`, Param2: `@filename`

- **Route ID:** `208290`
  - **Kondisi (IF):** `@cek_upload  true`
  - **Aksi UI Component (settext):** Param1: `$link_pdf`, Param2: `@urlpdf`

- **Route ID:** `208291`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi UI Component (settext):** Param1: `$url`, Param2: `@urlpdf`

- **Route ID:** `208293`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi UI Component (settext):** Param1: `$url`, Param2: `@url`

- **Route ID:** `208294`
  - **Kondisi (IF):** `@+REQUESTCODE  update`
  - **Aksi `` (``):** `{}`

- **Route ID:** `208295`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi DB (UPDATE):** Tabel `master_hold`
    - Fields: `{"uploaded_by": "@+SESSION-ID", "release_date": "$tgl_release", "uploaded_at": "@+NOW()", "release_proof_file": "$file", "release_approval_status": "Pending Approval"}`
    - Where: `{"paramargs": {"parameter2": "", "parameter1": "$id"}, "logic": "0", "type": "1", "sqlwhere": "", "param": {"parameter2": "id_payroll", "parameter1": "id_hold"}}` (Args: `{}`)

- **Route ID:** `208296`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi DB (INSERT):** Tabel `gambar`
    - Fields: `{"url": "$url", "nama_file": "$file", "id_hold_data": "$id"}`
    - Where: `{"paramargs": {"parameter2": "", "parameter1": ""}, "logic": "0", "type": "0", "sqlwhere": "", "param": {"parameter2": "url", "parameter1": "url"}}` (Args: `{}`)

- **Route ID:** `208297`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Tutup Form:** Menutup form aktif.

- **Route ID:** `208298`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Set Result:** Mengirim status hasil (`reload`) kembali ke form pemanggil.

- **Route ID:** `208310`
  - **Kondisi (IF):** `@+REQUESTCODE  update`
  - **Aksi `` (``):** `{}`

- **Route ID:** `208311`
  - **Kondisi (IF):** `@+RESPONSECODE  upload`

- **Route ID:** `208312`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$release_proof_file`, Param2: `@filename`

- **Route ID:** `208313`
  - **Kondisi (IF):** `@cek_upload  true`

- **Route ID:** `208319`
  - **Kondisi (IF):** `@+REQUESTCODE  upload_ulang`
  - **Aksi `` (``):** `{}`

- **Route ID:** `208320`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi DB (DELETE):** Tabel `gambar`
    - Fields: `{}`
    - Where: `{"paramargs": {"parameter2": "", "parameter1": "$id"}, "logic": "0", "type": "1", "sqlwhere": "", "param": {"parameter2": "url", "parameter1": "id_hold_data"}}` (Args: `{}`)

- **Route ID:** `208527`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
INSERT INTO history_hold 
(id_hold,
action_type,
action_by,
action_at,
action_note,
nik,
name,
employee_type,
department,
division,
unit_name,
position,
branch,
thp,
uploaded_by)
SELECT 
id_hold,
release_approval_status,
?,
NOW(),
'Upload Bukti Release',
nik,
name,
employee_type,
department,
division,
unit_name,
position,
branch,
thp,
uploaded_by
FROM master_hold 
WHERE id_hold = ? AND release_approval_status = 'Pending Approval'
```

- **Route ID:** `211749`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi UI Component (settext):** Param1: `$file`, Param2: `123`

- **Route ID:** `211750`
  - **Kondisi (IF):** `@+RESPONSECODE  upload`

- **Route ID:** `211751`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi UI Component (settext):** Param1: `$link_pdf`, Param2: `@urlpdf`

- **Route ID:** `211752`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Dialog/Pesan:** Teks: `@update(error)`, Request Code: ``, Buttons: ` @urlpdf` / ` @url`

- **Route ID:** `211753`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Query Database:**
```sql
SELECT IF(? LIKE '%.pdf',?,?)
```

- **Route ID:** `211754`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi UI Component (settext):** Param1: `$url`, Param2: `@link[0,0]`

- **Route ID:** `211755`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi UI Component (settext):** Param1: `$link_pdf`, Param2: `@link[0,0]`

- **Route ID:** `211756`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Dialog/Pesan:** Teks: `@link[0,0]`, Request Code: ``, Buttons: `@link(error)` / ``

- **Route ID:** `211757`
  - **Kondisi:** `Langsung Eksekusi`

- **Route ID:** `211764`
  - **Kondisi (IF):** `@+REQUESTCODE  update`

- **Route ID:** `211765`
  - **Kondisi:** `Langsung Eksekusi`

- **Route ID:** `211766`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
SELECT CASE
WHEN ? = 'https://dev.dikahadir.com/payroll/res/storage/?name=' 
THEN = 'Maaf, silahkan ulangi upload file Anda'
ELSE ''
END
```

- **Route ID:** `211767`
  - **Kondisi (IF):** `@validasi[0,0] <> `
  - **Aksi Dialog/Pesan:** Teks: `@validasi[0,0]`, Request Code: ``, Buttons: `` / ``

- **Route ID:** `211768`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `SystemAction` (`break`):** `{}`


##### Komponen: `btn_save` (Tipe: `button`, Label: `Simpan`)

- **Route ID:** `208303`
  - **Kondisi (IF):** `$file_upload  `
  - **Aksi Dialog/Pesan:** Teks: `Pilih file upload terlebih dahulu`, Request Code: ``, Buttons: `OK` / ``

- **Route ID:** `208304`
  - **Kondisi:** `ELSE`
  - **Aksi `` (``):** `{}`

- **Route ID:** `208305`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi `FormAction` (`submit`):** `{"param2": "upload", "param1": "$file_upload"}`

- **Route ID:** `208308`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Dialog/Pesan:** Teks: `Are You Sure ?`, Request Code: `update`, Buttons: `OK` / `Cancel`

- **Route ID:** `208316`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi `SystemAction` (`break`):** `{}`

- **Route ID:** `208318`
  - **Kondisi (IF):** `$release_proof_file <> `
  - **Aksi Dialog/Pesan:** Teks: `Apakah Anda yakin ingin mengubah bukti data release`, Request Code: `upload_ulang`, Buttons: `OK` / `Cancel`

- **Route ID:** `208321`
  - **Kondisi (IF):** `$tgl_release  `
  - **Aksi Dialog/Pesan:** Teks: `Silahkan pilih tanggal release terlebih dahulu`, Request Code: ``, Buttons: `OK` / ``

- **Route ID:** `208322`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi `SystemAction` (`break`):** `{}`

- **Route ID:** `208784`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Dialog/Pesan:** Teks: `Are You Sure ?`, Request Code: `update`, Buttons: `OK` / `Cancel`

- **Route ID:** `211762`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi Query Database:**
```sql
SELECT ? NOT REGEXP '\\.(jpeg|gif|png|pdf|jpg)$';
```

- **Route ID:** `211763`
  - **Kondisi (IF):** `@format[0,0]  1`
  - **Aksi Dialog/Pesan:** Teks: `Format file tidak didukung pada upload bukti release`, Request Code: ``, Buttons: `OK` / ``


##### Komponen: `tgl_release` (Tipe: `datetime`, Label: ``)

- **Route ID:** `208438`
  - **Kondisi (IF):** `$tgl_release <> `
  - **Aksi UI Component (setvisible):** Param1: `$lbl`, Param2: `false`


##### Komponen: `btn_reupload_save` (Tipe: `button`, Label: `Simpan`)

- **Route ID:** `208787`
  - **Kondisi:** `Langsung Eksekusi`

- **Route ID:** `208788`
  - **Kondisi (IF):** `$file_upload  `
  - **Aksi Dialog/Pesan:** Teks: `Pilih file upload terlebih dahulu`, Request Code: ``, Buttons: `OK` / ``

- **Route ID:** `208789`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi `SystemAction` (`break`):** `{}`

- **Route ID:** `208790`
  - **Kondisi (IF):** `$tgl_release  `
  - **Aksi Dialog/Pesan:** Teks: `Silahkan pilih tanggal release terlebih dahulu`, Request Code: ``, Buttons: `OK` / ``

- **Route ID:** `208791`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi `SystemAction` (`break`):** `{}`

- **Route ID:** `208792`
  - **Kondisi (IF):** `$release_proof_file <> `
  - **Aksi Dialog/Pesan:** Teks: `Apakah Anda yakin ingin mengubah bukti data release`, Request Code: `upload_ulang`, Buttons: `OK` / `Cancel`


---

### FORM: `history_hold` (History Hold)

#### A. Komponen Input & Validasi Mandatori
- _Tidak ada validasi mandatori bawaan UI (semua divalidasi via backend/action route)._

#### B. Logika Komponen, Routes, Query & Aksi

##### Komponen: `load` (Tipe: `navload`, Label: ``)

- **Route ID:** `208439`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$btn_search"}`


##### Komponen: `grid` (Tipe: `smartgrid`, Label: ``)

- **Route ID:** `208441`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "filter", "class": "BooleanExpression", "id": "116"}`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load"}`


##### Komponen: `cb_division` (Tipe: `combobox`, Label: ``)

- **Route ID:** `208442`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load_combo"}`

- **Route ID:** `208443`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi UI Component (settext):** Param1: `$cb_unit`, Param2: ``

- **Route ID:** `208444`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$cb_position`, Param2: ``

- **Route ID:** `208445`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$cb_employee_type`, Param2: ``

- **Route ID:** `208446`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$cb_branch`, Param2: ``

- **Route ID:** `208447`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load"}`

- **Route ID:** `218910`
  - **Kondisi:** `Langsung Eksekusi`


##### Komponen: `cb_unit` (Tipe: `combobox`, Label: ``)

- **Route ID:** `208449`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi UI Component (settext):** Param1: `$cb_position`, Param2: ``

- **Route ID:** `208450`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$cb_employee_type`, Param2: ``

- **Route ID:** `208451`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$cb_branch`, Param2: ``

- **Route ID:** `208452`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load"}`

- **Route ID:** `218909`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load_combo"}`


##### Komponen: `cb_position` (Tipe: `combobox`, Label: ``)

- **Route ID:** `208455`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi UI Component (settext):** Param1: `$cb_employee_type`, Param2: ``

- **Route ID:** `208456`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$cb_branch`, Param2: ``

- **Route ID:** `208457`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load"}`

- **Route ID:** `218916`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load_combo"}`


##### Komponen: `cb_branch` (Tipe: `combobox`, Label: ``)

- **Route ID:** `208460`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load_combo"}`

- **Route ID:** `208461`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi UI Component (settext):** Param1: `$cb_employee_type`, Param2: ``

- **Route ID:** `208462`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load"}`


##### Komponen: `cb_employee_type` (Tipe: `combobox`, Label: ``)

- **Route ID:** `208463`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load_combo"}`

- **Route ID:** `208464`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$load"}`


##### Komponen: `load_combo` (Tipe: `navload`, Label: ``)

- **Route ID:** `208465`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (setdata):** Param1: `$cb_division`, Param2: `@division`

- **Route ID:** `208466`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (setdata):** Param1: `$cb_unit`, Param2: `@unit_name`

- **Route ID:** `208467`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (setdata):** Param1: `$cb_position`, Param2: `@position`

- **Route ID:** `208468`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (setdata):** Param1: `$cb_branch`, Param2: `@branch`

- **Route ID:** `208469`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (setdata):** Param1: `$cb_employee_type`, Param2: `@employee_type`

- **Route ID:** `208470`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi Query Database:**
```sql
SELECT 
h.division AS 'Division' 
FROM master_hold p JOIN history_hold h ON p.id_hold = h.id_hold 
WHERE (h.uploaded_by = ? OR h.action_by = ?) 
GROUP BY h.division
```

- **Route ID:** `208471`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
SELECT h.unit_name
FROM master_hold p JOIN history_hold h ON p.id_hold = h.id_hold 
WHERE h.division = ? AND (h.uploaded_by = ? OR h.action_by = ?) 
GROUP BY h.unit_name
```

- **Route ID:** `208472`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
SELECT 
  h.position AS 'Position'
FROM master_hold p JOIN history_hold h ON p.id_hold = h.id_hold 
WHERE h.division = ? AND h.unit_name = ? AND (h.uploaded_by = ? OR h.action_by = ?)
GROUP BY h.position
```

- **Route ID:** `208473`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
SELECT 
  h.branch AS 'Branch'
FROM master_hold p JOIN history_hold h ON p.id_hold = h.id_hold 
WHERE h.division = ? AND h.unit_name = ? AND h.position = ? AND (h.uploaded_by = ? OR h.action_by = ?)
GROUP BY h.branch
```

- **Route ID:** `208474`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
SELECT 
  h.employee_type AS 'Employee Type'
FROM master_hold p JOIN history_hold h ON p.id_hold = h.id_hold 
WHERE h.division = ? AND h.unit_name = ? AND h.position = ? AND h.branch = ?
AND (h.uploaded_by = ? OR h.action_by = ?)
GROUP BY h.employee_type
```

- **Route ID:** `218908`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Dialog/Pesan:** Teks: `@division(nset)`, Request Code: ``, Buttons: `` / ``


##### Komponen: `search` (Tipe: `text`, Label: ``)

- **Route ID:** `208475`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$btn_search"}`


##### Komponen: `btn_search` (Tipe: `button`, Label: `SEARCH`)

- **Route ID:** `208476`
  - **Kondisi (IF):** `$search <> `
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "%'", "param3": "$search", "param2": "AND (p.name LIKE '%", "param1": "@search", "param9": "", "param8": "", "param7": "", "result": "@search", "param6": ""}`

- **Route ID:** `208477`
  - **Kondisi (IF):** `$cb_division <> `
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "''", "param3": "$cb_division", "param2": "AND p.division = '", "param1": "@search", "param9": "", "param8": "", "param7": "", "result": "@search", "param6": ""}`

- **Route ID:** `208478`
  - **Kondisi (IF):** `$cb_unit <> `
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "''", "param3": "$cb_unit", "param2": "AND p.unit_name = '", "param1": "@search", "param9": "", "param8": "", "param7": "", "result": "@search", "param6": ""}`

- **Route ID:** `208479`
  - **Kondisi (IF):** `$cb_position <> `
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "''", "param3": "$cb_position", "param2": "AND p.position = '", "param1": "@search", "param9": "", "param8": "", "param7": "", "result": "@search", "param6": ""}`

- **Route ID:** `208480`
  - **Kondisi (IF):** `$cb_employee_type <> `
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "''", "param3": "$cb_employee_type", "param2": "AND p.employee_type = '", "param1": "@search", "param9": "", "param8": "", "param7": "", "result": "@search", "param6": ""}`

- **Route ID:** `208481`
  - **Kondisi (IF):** `$cb_branch <> `
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "''", "param3": "$cb_branch", "param2": "AND p.branch = '", "param1": "@search", "param9": "", "param8": "", "param7": "", "result": "@search", "param6": ""}`

- **Route ID:** `208482`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi UI Component (smartgrid):** Param1: `$grid`, Param2: `@data`

- **Route ID:** `208483`
  - **Kondisi:** `ELSE`
  - **Aksi Query Database:**
```sql
SELECT
p.nik AS "NIK",
p.name AS "Name",
p.division AS "Division",
p.unit_name AS "Unit Name",
p.employee_type AS "Employee Type",
p.position AS "Position",
p.branch AS "Branch",
format_uang(thp) AS "THP",
action_type AS "Status",
u.full_name AS "Dilakukan Oleh",
IF(action_type='Reject', CONCAT('Alasan Reject : ',action_note), action_note) AS "Keterangan",
action_at AS "Tanggal"
FROM history_hold p
LEFT JOIN users u on p.action_by = u.id
WHERE (p.uploaded_by = ? OR p.action_by = ?) @?
ORDER BY p.action_at DESC
```

- **Route ID:** `208484`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "%')", "param3": "$search", "param2": "OR p.nik LIKE '%", "param1": "@search", "param9": "", "param8": "", "param7": "", "result": "@search", "param6": ""}`

- **Route ID:** `208512`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi Dialog/Pesan:** Teks: `@data(error)`, Request Code: ``, Buttons: `@data(rows)` / ``

- **Route ID:** `208779`
  - **Kondisi (IF):** `@+SESSION-POSITION  SPV`
  - **Aksi Query Database:**
```sql
SELECT
p.nik AS "NIK",
p.name AS "Name",
p.division AS "Division",
p.unit_name AS "Unit Name",
p.employee_type AS "Employee Type",
p.position AS "Position",
p.branch AS "Branch",
format_uang(thp) AS "THP",
action_type AS "Status",
u.full_name AS "Dilakukan Oleh",
IF(action_type='Reject', CONCAT('Alasan Reject : ',action_note), action_note) AS "Keterangan",
action_at AS "Tanggal"
FROM history_hold p
LEFT JOIN users u on p.action_by = u.id
WHERE 1=1 @?
ORDER BY p.action_at DESC
```


---

### FORM: `reason_cancel_hold` (Cancel Hold)

#### A. Komponen Input & Validasi Mandatori
- _Tidak ada validasi mandatori bawaan UI (semua divalidasi via backend/action route)._

#### B. Logika Komponen, Routes, Query & Aksi

##### Komponen: `load` (Tipe: `navload`, Label: ``)

- **Route ID:** `227068`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `DefinitionAction` (`arg`):** `{"param5": "", "param4": "", "param3": "", "param2": "type", "param1": "set_id", "param10": "", "param9": "", "param8": "", "param7": "", "param6": ""}`

- **Route ID:** `227069`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$set_id`, Param2: `@set_id`

- **Route ID:** `227070`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$type`, Param2: `@type`

- **Route ID:** `227071`
  - **Kondisi (IF):** `$type  uang kompensasi`
  - **Aksi UI Component (setvisible):** Param1: `$btn_update`, Param2: `false`

- **Route ID:** `227072`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (setvisible):** Param1: `$btn_update_uk`, Param2: `true`


##### Komponen: `res` (Tipe: `navresult`, Label: ``)

- **Route ID:** `227074`
  - **Kondisi (IF):** `@+REQUESTCODE  cancel_hold`

- **Route ID:** `227078`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi UI Component (smartgrid):** Param1: `$grid`, Param2: `@data_employee_payroll`

- **Route ID:** `227079`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Tutup Form:** Menutup form aktif.

- **Route ID:** `227080`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Set Result:** Mengirim status hasil (`reload`) kembali ke form pemanggil.

- **Route ID:** `227082`
  - **Kondisi (IF):** `@+REQUESTCODE  hold_uk`

- **Route ID:** `227083`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
INSERT INTO master_hold (
  id_uk,
  month_payroll,
  year_payroll,
  nik,
  name,
  employee_type,
  department,
  division,
  unit_name,
  position,
  branch,
  bank_code,
  bank_branch,
  bank_account_number,
  thp,
  hold_reason,
  hold_at,
  release_approval_status,
  swift_code)
  SELECT 
  t.id,
  t.bulan_pajak,
  t.tahun_pajak,
  t.nik,
  t.name,
  t.employee_type,
  t.department,
  t.division,
  t.unit_name,
  t.position,
  t.branch,
  t.bank_name,
  '-',
  t.no_rekening,
  t.thp,
  ?,
  NOW(),
  'New',
  mb.swift_code
  FROM table_uk t 
  LEFT JOIN bank_alias ba ON t.bank_name COLLATE utf8mb4_general_ci = ba.nama_alias
  LEFT JOIN master_bank mb ON ba.id_bank = mb.id 
  WHERE t.id IN @?
ON DUPLICATE KEY UPDATE 
    hold_at = NOW(),
	release_approval_status = 'New',
    thp = VALUES(thp);
```

- **Route ID:** `227084`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
INSERT INTO history_hold 
(id_hold,
action_type,
action_by,
action_at,
nik,
name,
employee_type,
department,
division,
unit_name,
position,
branch,
thp)
SELECT 
id_hold,
release_approval_status,
?,
NOW(),
nik,
name,
employee_type,
department,
division,
unit_name,
position,
branch,
thp
FROM master_hold 
WHERE id_uk IN @? AND release_approval_status = 'New'
ON DUPLICATE KEY UPDATE
action_at = action_at;
```

- **Route ID:** `227085`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Dialog/Pesan:** Teks: `Successfull`, Request Code: ``, Buttons: `` / ``

- **Route ID:** `227086`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Tutup Form:** Menutup form aktif.

- **Route ID:** `227087`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Set Result:** Mengirim status hasil (`22`) kembali ke form pemanggil.

- **Route ID:** `227088`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi DB (UPDATE):** Tabel `table_uk`
    - Fields: `{"hold_status": "1"}`
    - Where: `{"paramargs": {"parameter2": "", "parameter1": ""}, "logic": "0", "type": "2", "sqlwhere": "id IN @?", "param": {"parameter2": "id", "parameter1": "id"}}` (Args: `{"0": "$set_id"}`)

- **Route ID:** `227095`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi DB (UPDATE):** Tabel `master_hold`
    - Fields: `{"uploaded_by": "@+SESSION-ID", "release_date": "$release_date", "uploaded_at": "@+NOW()", "release_approval_status": "Pending Cancel"}`
    - Where: `{"paramargs": {"parameter2": "", "parameter1": ""}, "logic": "0", "type": "2", "sqlwhere": "id_hold IN @?", "param": {"parameter2": "id_hold", "parameter1": "id_hold"}}` (Args: `{"0": "$set_id"}`)

- **Route ID:** `227096`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
INSERT INTO history_hold 
(id_hold,
action_type,
action_by,
action_at,
action_note,
nik,
name,
employee_type,
department,
division,
unit_name,
position,
branch,
thp,
uploaded_by)
SELECT 
id_hold,
release_approval_status,
?,
NOW(),
?,
nik,
name,
employee_type,
department,
division,
unit_name,
position,
branch,
thp,
uploaded_by
FROM master_hold 
WHERE id_hold IN @? AND release_approval_status = 'Pending Cancel'
```


##### Komponen: `btn_update` (Tipe: `button`, Label: `Update`)

- **Route ID:** `227090`
  - **Kondisi:** `ELSE`
  - **Aksi Dialog/Pesan:** Teks: `Are You Sure ?`, Request Code: `cancel_hold`, Buttons: `Yes` / `No`

- **Route ID:** `227091`
  - **Kondisi (IF):** `$note  `
  - **Aksi Dialog/Pesan:** Teks: `Keterangan tidak boleh kosong`, Request Code: ``, Buttons: `OK` / ``

- **Route ID:** `227098`
  - **Kondisi (IF):** `$release_date  `
  - **Aksi Dialog/Pesan:** Teks: `Tanggal Release tidak boleh kosong`, Request Code: ``, Buttons: `OK` / ``


##### Komponen: `btn_update_uk` (Tipe: `button`, Label: `Update`)

- **Route ID:** `227092`
  - **Kondisi (IF):** `$keterangan  `
  - **Aksi Dialog/Pesan:** Teks: `Keterangan tidak boleh kosong`, Request Code: ``, Buttons: `OK` / ``

- **Route ID:** `227093`
  - **Kondisi:** `ELSE`
  - **Aksi Dialog/Pesan:** Teks: `Are You Sure ?`, Request Code: `hold_uk`, Buttons: `Yes` / `No`


---

### FORM: `reason_reject_release` (Informasi Reject)

#### A. Komponen Input & Validasi Mandatori
- _Tidak ada validasi mandatori bawaan UI (semua divalidasi via backend/action route)._

#### B. Logika Komponen, Routes, Query & Aksi

##### Komponen: `load` (Tipe: `navload`, Label: ``)

- **Route ID:** `208402`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `DefinitionAction` (`arg`):** `{"param5": "", "param4": "", "param3": "", "param2": "", "param1": "set_id", "param10": "", "param9": "", "param8": "", "param7": "", "param6": ""}`

- **Route ID:** `208403`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$set_id`, Param2: `@set_id`

- **Route ID:** `208404`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi UI Component (settext):** Param1: `$type`, Param2: `@type`


##### Komponen: `res` (Tipe: `navresult`, Label: ``)

- **Route ID:** `208405`
  - **Kondisi (IF):** `@+REQUESTCODE  hold`

- **Route ID:** `208406`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
UPDATE master_hold 
SET release_date = NULL,
release_approval_status = 'Reject'
WHERE id_hold IN @?
```

- **Route ID:** `208410`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Tutup Form:** Menutup form aktif.

- **Route ID:** `208411`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Set Result:** Mengirim status hasil (`reload`) kembali ke form pemanggil.

- **Route ID:** `208425`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
INSERT INTO history_hold 
(id_hold,
action_type,
action_by,
action_note,
action_at,
nik,
name,
employee_type,
department,
division,
unit_name,
position,
branch,
thp,
uploaded_by)
SELECT 
id_hold,
release_approval_status,
?,
?,
NOW(),
nik,
name,
employee_type,
department,
division,
unit_name,
position,
branch,
thp,
uploaded_by
FROM master_hold 
WHERE id_hold IN @? AND release_approval_status = 'Reject'
```

- **Route ID:** `227097`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Dialog/Pesan:** Teks: `Successfull`, Request Code: ``, Buttons: `OK` / ``


##### Komponen: `btn_update` (Tipe: `button`, Label: `Update`)

- **Route ID:** `208412`
  - **Kondisi:** `ELSE`
  - **Aksi Dialog/Pesan:** Teks: `Are You Sure ?`, Request Code: `hold`, Buttons: `Yes` / `No`

- **Route ID:** `208413`
  - **Kondisi (IF):** `$keterangan  `
  - **Aksi Dialog/Pesan:** Teks: `Keterangan tidak boleh kosong`, Request Code: ``, Buttons: `OK` / ``


---

### FORM: `upload_release_data` (Upload Bukti Release)

#### A. Komponen Input & Validasi Mandatori
- _Tidak ada validasi mandatori bawaan UI (semua divalidasi via backend/action route)._

#### B. Logika Komponen, Routes, Query & Aksi

##### Komponen: `load` (Tipe: `navload`, Label: ``)

- **Route ID:** `199506`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`calllogic`):** `{"param1": "$res"}`

- **Route ID:** `199507`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$gambar`, Param2: `$url`

- **Route ID:** `199583`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `DefinitionAction` (`arg`):** `{"param5": "", "param4": "", "param3": "", "param2": "", "param1": "id", "param10": "", "param9": "", "param8": "", "param7": "", "param6": ""}`

- **Route ID:** `199584`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Query Database:**
```sql
SELECT * FROM master_hold WHERE id_payroll = ?
```

- **Route ID:** `199585`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$nik`, Param2: `!nik`

- **Route ID:** `199586`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$nama`, Param2: `!name`

- **Route ID:** `208177`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$id`, Param2: `@id`

- **Route ID:** `208223`
  - **Kondisi:** `Langsung Eksekusi`


##### Komponen: `res_old` (Tipe: `label`, Label: ``)

- **Route ID:** `199509`
  - **Kondisi:** `Langsung Eksekusi`

- **Route ID:** `199510`
  - **Kondisi (IF):** `@+RESPONSECODE  upload_gambar`
  - **Aksi UI Component (setdata):** Param1: `@datapic`, Param2: `@+RESULT (nset)`

- **Route ID:** `199511`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "", "param3": "", "param2": "", "param1": "@datapic [\"filename\"] (string)", "param9": "", "param8": "", "param7": "", "result": "@filename", "param6": ""}`

- **Route ID:** `199512`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "", "param3": "", "param2": "'.tmp", "param1": "@datapic [\"fsavename\"]", "param9": "", "param8": "", "param7": "", "result": "@fsavename", "param6": ""}`

- **Route ID:** `199513`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StorageAction` (`storage`):** `{"param7": "@datapic [\"fsavename\"] (string)", "param6": "storage", "param5": "", "param4": "", "param3": "copy", "param2": "@fsavename", "param1": "temp"}`

- **Route ID:** `199514`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi UI Component (settext):** Param1: `$url`, Param2: `@fsavename`

- **Route ID:** `199515`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "", "param3": "", "param2": "@datapic [\"fsavename\"] (string)", "param1": "https://dev.dikahadir.com/payroll/res/storage/?name=", "param9": "", "param8": "", "param7": "", "result": "@url", "param6": ""}`

- **Route ID:** `199516`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$url`, Param2: `@url`

- **Route ID:** `199518`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$url`, Param2: `@urlpdf`

- **Route ID:** `199533`
  - **Kondisi (IF):** `@+RESPONSECODE  upload_pdf`

- **Route ID:** `199534`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi UI Component (setdata):** Param1: `@datapic`, Param2: `@+RESULT (nset)`

- **Route ID:** `199535`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "", "param3": "", "param2": "", "param1": "@datapic [\"filename\"] (string)", "param9": "", "param8": "", "param7": "", "result": "@filename", "param6": ""}`

- **Route ID:** `199536`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "", "param3": "", "param2": "gen", "param1": "@datapic [\"fsavename\"]", "param9": "", "param8": "", "param7": "", "result": "@fsavenamepdf", "param6": ""}`

- **Route ID:** `199537`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StorageAction` (`storage`):** `{"param7": "@fsavenamepdf", "param6": "storage", "param5": "", "param4": "", "param3": "copy", "param2": "@fsavename", "param1": "temp"}`

- **Route ID:** `199538`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "", "param3": "", "param2": "@fsavenamepdf", "param1": "https://dev.dikahadir.com/payroll/res/storage/?name=", "param9": "", "param8": "", "param7": "", "result": "@urlpdf", "param6": ""}`

- **Route ID:** `199570`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Dialog/Pesan:** Teks: `@fsavenamepdf`, Request Code: ``, Buttons: `@fsavename` / ``

- **Route ID:** `199580`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$file`, Param2: `@filename`

- **Route ID:** `199581`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$file`, Param2: `@filename`

- **Route ID:** `199582`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$link_pdf`, Param2: `@urlpdf`

- **Route ID:** `199587`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi DB (UPDATE):** Tabel `master_hold`
    - Fields: `{"uploaded_by": "@+SESSION_ID", "uploaded_at": "@+NOW()", "release_proof_file": "$file", "release_approval_status": "REQUEST"}`
    - Where: `{"paramargs": {"parameter2": "", "parameter1": "$id"}, "logic": "0", "type": "1", "sqlwhere": "", "param": {"parameter2": "id_payroll", "parameter1": "id_payroll"}}` (Args: `{}`)

- **Route ID:** `199605`
  - **Kondisi (IF):** `@+REQUESTCODE  upload`

- **Route ID:** `208178`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Dialog/Pesan:** Teks: `@update(error)`, Request Code: ``, Buttons: `` / ``

- **Route ID:** `208179`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi DB (INSERT):** Tabel `gambar`
    - Fields: `{"url": "$url", "nama_file": "$file", "id_hold_data": "$id"}`
    - Where: `{"paramargs": {"parameter2": "", "parameter1": ""}, "logic": "0", "type": "0", "sqlwhere": "", "param": {"parameter2": "url", "parameter1": "url"}}` (Args: `{}`)


##### Komponen: `file_upload` (Tipe: `file`, Label: ``)

- **Route ID:** `208224`
  - **Kondisi (IF):** `$file_upload contain pdf`
  - **Aksi UI Component (setvisible):** Param1: `$view_pdf`, Param2: `true`

- **Route ID:** `208225`
  - **Kondisi:** `ELSE`
  - **Aksi UI Component (setvisible):** Param1: `$view_gambar`, Param2: `true`

- **Route ID:** `208226`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi UI Component (setvisible):** Param1: `$view_gambar`, Param2: `false`

- **Route ID:** `208227`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi UI Component (setvisible):** Param1: `$view_pdf`, Param2: `false`


##### Komponen: `add` (Tipe: `button`, Label: `ADD`)

- **Route ID:** `199508`
  - **Kondisi (IF):** `$file_upload contain png`
  - **Aksi `FormAction` (`submit`):** `{"param2": "upload_gambar", "param1": "$file_upload"}`

- **Route ID:** `199530`
  - **Kondisi (IF):** `$file_upload contain pdf`
  - **Aksi `FormAction` (`submit`):** `{"param2": "upload_pdf", "param1": "$file_upload"}`

- **Route ID:** `199531`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi `SystemAction` (`break`):** `{}`

- **Route ID:** `207893`
  - **Kondisi:** `Selalu Berjalan (True)`
  - **Aksi `FormAction` (`submit`):** `{"param2": "upload", "param1": "$file_upload"}`


##### Komponen: `view_pdf` (Tipe: `button`, Label: `VIEW`)

- **Route ID:** `199517`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi UI Component (settext):** Param1: `$gambar`, Param2: `$url`

- **Route ID:** `199525`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "", "param3": "$file", "param2": "'&dname=", "param1": "$link_pdf", "param9": "", "param8": "", "param7": "", "result": "@file", "param6": ""}`

- **Route ID:** `199526`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `VariableAction` (`new`):** `{"param4": "@file", "param3": "url", "param2": "@args", "param1": ""}`

- **Route ID:** `199527`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `VariableAction` (`set`):** `{"param5": "", "param4": "", "param3": "_blank", "param2": "target", "param1": "@args", "param9": "", "param8": "", "param7": "", "param6": ""}`

- **Route ID:** `199528`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi HTTP Request:** Method: `GET`, URL: `generator://webopen/`, Data: `@args`

- **Route ID:** `199540`
  - **Kondisi (IF):** `$file_upload contain gambar`
  - **Aksi `` (``):** `{}`

- **Route ID:** `199541`
  - **Kondisi (IF):** `$file_upload contain pdf`
  - **Aksi `` (``):** `{}`

- **Route ID:** `199543`
  - **Kondisi Raw:** `{"result": "", "flag": "hide", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi `SystemAction` (`break`):** `{}`

- **Route ID:** `208222`
  - **Kondisi:** `Selalu Berjalan (True)`


##### Komponen: `upload` (Tipe: `button`, Label: `UPLOAD`)

- **Route ID:** `199604`
  - **Kondisi:** `ELSE`
  - **Aksi `` (``):** `{}`

- **Route ID:** `208230`
  - **Kondisi (IF):** `$file_upload  `
  - **Aksi Dialog/Pesan:** Teks: `Pilih file upload terlebih dahulu`, Request Code: ``, Buttons: `OK` / ``

- **Route ID:** `208263`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi `FormAction` (`submit`):** `{"param2": "upload", "param1": "$file_upload"}`


##### Komponen: `res` (Tipe: `navresult`, Label: ``)

- **Route ID:** `208206`
  - **Kondisi (IF):** `@+RESPONSECODE  upload`
  - **Aksi UI Component (setdata):** Param1: `@datapic`, Param2: `@+RESULT(nset)`

- **Route ID:** `208207`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "", "param3": "", "param2": "", "param1": "@datapic [\"filename\"] (string)", "param9": "", "param8": "", "param7": "", "result": "@filename", "param6": ""}`

- **Route ID:** `208209`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "", "param3": "", "param2": "'.tmp", "param1": "@datapic [\"fsavename\"]", "param9": "", "param8": "", "param7": "", "result": "@fsavename", "param6": ""}`

- **Route ID:** `208210`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "", "param3": "", "param2": "gen", "param1": "@datapic [\"fsavename\"]", "param9": "", "param8": "", "param7": "", "result": "@fsavenamepdf", "param6": ""}`

- **Route ID:** `208211`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StorageAction` (`storage`):** `{"param7": "@datapic [\"fsavename\"] (string)", "param6": "storage", "param5": "", "param4": "", "param3": "copy", "param2": "@fsavename", "param1": "temp"}`

- **Route ID:** `208212`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StorageAction` (`storage`):** `{"param7": "@fsavenamepdf", "param6": "storage", "param5": "", "param4": "", "param3": "copy", "param2": "@fsavename", "param1": "temp"}`

- **Route ID:** `208214`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi UI Component (settext):** Param1: `$url`, Param2: `@fsavename`

- **Route ID:** `208215`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "", "param3": "", "param2": "@datapic [\"fsavename\"] (string)", "param1": "https://dev.dikahadir.com/payroll/res/storage/?name=", "param9": "", "param8": "", "param7": "", "result": "@url", "param6": ""}`

- **Route ID:** `208216`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi `StringManipulationAction` (`concat`):** `{"param5": "", "param4": "", "param3": "", "param2": "@fsavenamepdf", "param1": "https://dev.dikahadir.com/payroll/res/storage/?name=", "param9": "", "param8": "", "param7": "", "result": "@urlpdf", "param6": ""}`

- **Route ID:** `208217`
  - **Kondisi:** `ELSE`
  - **Aksi UI Component (settext):** Param1: `$url`, Param2: `@url`

- **Route ID:** `208218`
  - **Kondisi (IF):** `@upload  true`
  - **Aksi UI Component (settext):** Param1: `$link_pdf`, Param2: `@urlpdf`

- **Route ID:** `208219`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": ""}`
  - **Aksi UI Component (settext):** Param1: `$url`, Param2: `@urlpdf`

- **Route ID:** `208220`
  - **Kondisi (IF):** `@upload  true`
  - **Aksi `` (``):** `{}`

- **Route ID:** `208256`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi DB (UPDATE):** Tabel `master_hold`
    - Fields: `{"uploaded_by": "@+SESSION_ID", "uploaded_at": "@+NOW()", "release_proof_file": "$file", "release_approval_status": "REQUEST"}`
    - Where: `{"paramargs": {"parameter2": "", "parameter1": "$id"}, "logic": "0", "type": "1", "sqlwhere": "", "param": {"parameter2": "id_payroll", "parameter1": "id_payroll"}}` (Args: `{}`)

- **Route ID:** `208257`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi DB (INSERT):** Tabel `gambar`
    - Fields: `{"url": "$url", "nama_file": "$file", "id_hold_data": "$id"}`
    - Where: `{"paramargs": {"parameter2": "", "parameter1": ""}, "logic": "0", "type": "0", "sqlwhere": "", "param": {"parameter2": "url", "parameter1": "url"}}` (Args: `{}`)

- **Route ID:** `208258`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Tutup Form:** Menutup form aktif.

- **Route ID:** `208264`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Dialog/Pesan:** Teks: `@urlpdf`, Request Code: ``, Buttons: `$file_upload` / `$file`

- **Route ID:** `208265`
  - **Kondisi:** `Langsung Eksekusi`
  - **Aksi Set Result:** Mengirim status hasil (`reload`) kembali ke form pemanggil.

- **Route ID:** `208267`
  - **Kondisi:** `Langsung Eksekusi`

- **Route ID:** `208274`
  - **Kondisi Raw:** `{"result": "", "flag": "", "args": {}, "code": "", "class": "", "id": "110"}`
  - **Aksi UI Component (settext):** Param1: `$file`, Param2: `@filename`


##### Komponen: `view_gambar` (Tipe: `button`, Label: `VIEW`)

- **Route ID:** `208228`
  - **Kondisi:** `Langsung Eksekusi`


---

## 5. Panduan Arsitektur & Endpoint Spring Boot (Rewrite)

### A. REST Controller Endpoints (`MasterHoldController`)

| HTTP Method | Endpoint Path | Fungsi | Query/Body Param |
|---|---|---|---|
| `GET` | `/api/v1/payroll/hold/list` | Mendapatkan data daftar master hold (paginated & filterable) | `division, unitName, position, branch, employeeType, month, year, statusApproval, keyword, page, size` |
| `GET` | `/api/v1/payroll/hold/filter-options` | Mendapatkan opsi dropdown filter (Division, Unit, dsb.) | `division, unitName, ...` |
| `GET` | `/api/v1/payroll/hold/{idHold}` | Mendapatkan detail data hold tertentu | `idHold` (Path Variable) |
| `GET` | `/api/v1/payroll/hold/{idHold}/history` | Mendapatkan riwayat log reject/release | `idHold` |
| `POST` | `/api/v1/payroll/hold/{idHold}/release` | Request release hold data (Upload bukti & tanggal rilis) | `ReleaseHoldRequestDTO` (releaseDate, file, note) |
| `POST` | `/api/v1/payroll/hold/approve-release` | Menyetujui release hold (Batch / Single) | `List<Long> idHoldList` |
| `POST` | `/api/v1/payroll/hold/reject-release` | Menolak release hold | `RejectHoldRequestDTO` (idHoldList, reasonNote) |
| `POST` | `/api/v1/payroll/hold/cancel-hold` | Membatalkan status hold | `CancelHoldRequestDTO` (idHoldList, reasonNote, type) |
| `GET` | `/api/v1/payroll/hold/history-all` | Mendapatkan seluruh log audit history hold | Filter params (Division, Unit, Position, Keyword, dsb.) |
| `GET` | `/api/v1/payroll/hold/export-excel` | Export data master hold ke file Excel | Same filter params |

### B. Service Logic Flow (`MasterHoldService`)

1. **Find Master Hold List:**
   - Melakukan query dinamis ke `master_hold` dengan filter NIK, Name, Division, Unit, Position, Branch, Month, Year, Approval Status.
   - Mengonversi nominal numeric ke formatted currency di DTO atau membiarkan frontend memformatnya.
2. **Release Hold Logic:**
   - Validasi ekstensi file bukti release: `jpeg, gif, png, pdf, jpg`.
   - Simpan file ke Object Storage / File System lokal.
   - Update `master_hold`: `release_date = request.releaseDate`, `release_proof_file = uploadedUrl`, `uploaded_by = currentUser`, `uploaded_at = NOW()`, `release_approval_status = 'Pending Release'`.
   - Insert ke `history_hold`: `action_type = 'Release'`, `action_note = request.note`, snapshot data karyawan.
3. **Approve Release Logic:**
   - Update `master_hold`: `release_approval_status = 'Approve'` WHERE `id_hold` IN (:idHoldList).
   - Insert ke `history_hold`: `action_type = 'Approve'`, `action_by = currentUser`, `action_at = NOW()`.
4. **Reject Release Logic:**
   - Update `master_hold`: `release_date = NULL`, `release_approval_status = 'Reject'` WHERE `id_hold` IN (:idHoldList).
   - Insert ke `history_hold`: `action_type = 'Reject'`, `action_note = request.reasonNote`, snapshot data karyawan.
5. **Cancel Hold Logic:**
   - Jika type = UK (`table_uk`), update status `hold_status = 0` pada `table_uk`.
   - Update `master_hold`: `release_approval_status = 'Pending Cancel'` atau hapus hold.
   - Insert ke `history_hold`: `action_type = 'Pending Cancel'` / `'Cancel Hold'` beserta `action_note`.
