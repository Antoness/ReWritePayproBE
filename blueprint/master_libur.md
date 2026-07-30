# Blueprint Rewrite Spring Boot: Modul `master_libur`

Dokumen ini berisi kumpulan query, logic, parameter, dan mandatori yang diekstrak langsung dari _low-code engine_ secara dinamis beserta form turunannya. Gunakan dokumen ini sebagai referensi utama saat melakukan rewrite ke Spring Boot (Controller, Service, dan Repository).

---

## FORM: `master_libur`

### 1. Validasi & Mandatori Field (Input Components)
- _Tidak ada field mandatori yang didefinisikan secara eksplisit di komponen._

### 2. Business Logic, Queries & Actions
#### Komponen: `load` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 164347
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT id,
DATE_FORMAT(bulan_libur,'%m'),
DATE_FORMAT(bulan_libur, '%d-%M-%Y') AS "Tanggal",
hari AS "Keterangan",
created_date AS "Created Date",
created_by AS "Created By",
modify_date AS "Modify Date",
modify_by AS "Modify By"
FROM master_holiday WHERE 1=1 @? ORDER BY bulan_libur ASC
```

---
##### Route ID: 164348
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`smartgrid`)
- **Parameter:** $grid -> @view_data_libur

---
##### Route ID: 164349
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $search <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%'",
  "param3": "$search",
  "param2": "AND (hari LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 164350
**Kondisi (Expression):**
- Raw: `{
  "result": "",
  "flag": "",
  "args": {},
  "code": "",
  "class": "",
  "id": ""
}`

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%')",
  "param3": "$search",
  "param2": "OR DATE_FORMAT(bulan_libur, '%d-%M-%Y') LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 164351
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_bulan <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "''",
  "param3": "$cb_bulan",
  "param2": "AND DATE_FORMAT(bulan_libur,'%m') = '",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 164352
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tahun <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "''",
  "param3": "$cb_tahun",
  "param2": "AND DATE_FORMAT(bulan_libur,'%Y') = '",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
#### Komponen: `load_combo` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 164353
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
WITH RECURSIVE YearSequence AS (
    SELECT YEAR(NOW()) - 2 AS year
    UNION ALL
    SELECT year + 1
    FROM YearSequence
    WHERE year < YEAR(NOW()) + 5
)
SELECT year
FROM YearSequence ORDER BY year asc;
```

---
##### Route ID: 164354
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cb_tahun -> @data_tahun

---
##### Route ID: 164355
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT YEAR(NOW())
```

---
##### Route ID: 164356
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $cb_tahun -> @tahun_ini[0,0]

---
##### Route ID: 164357
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$load"
}
```

---
#### Komponen: `res` (Tipe: navresult)
Komponen ini memicu aliran logika berikut:

##### Route ID: 164358
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  1`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$load"
}
```

---
##### Route ID: 164359
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$load_combo"
}
```

---
##### Route ID: 164360
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  delete_data`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** @view_data_libur -> $grid (nset)

---
##### Route ID: 164361
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** @id -> @view_data_libur ["currentdata",0]

---
##### Route ID: 164362
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@delete_data",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"@id\"},\"logic\":\"0\",\"type\":\"1\",\"sqlwhere\":\"\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"master_holiday\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{},\"callz\":\"\",\"dbmode\":\"delete\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 164363
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$load"
}
```

---
##### Route ID: 164364
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$load_combo"
}
```

---
##### Route ID: 164365
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
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

---
##### Route ID: 164366
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  123`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$load"
}
```

---
##### Route ID: 164367
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$load_combo"
}
```

---
#### Komponen: `search` (Tipe: text)
Komponen ini memicu aliran logika berikut:

##### Route ID: 164368
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$load"
}
```

---
#### Komponen: `btn_search` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 164369
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$load"
}
```

---
#### Komponen: `btn_add` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 164370
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Form Baru (`add_libur`)

---
#### Komponen: `log_history` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 164377
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Form Baru (`log_history_libur`)

---
#### Komponen: `btn_upload` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 164378
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Form Baru (`upload_master_libur`)

---
#### Komponen: `load_visible` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 177178
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Manajer`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 177179
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_add -> false

---
##### Route ID: 177180
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_upload -> false

---
---

## FORM: `add_libur`

### 1. Validasi & Mandatori Field (Input Components)
- _Tidak ada field mandatori yang didefinisikan secara eksplisit di komponen._

### 2. Business Logic, Queries & Actions
#### Komponen: `res` (Tipe: navresult)
Komponen ini memicu aliran logika berikut:

##### Route ID: 163915
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  insert_data`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@add_data",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"0\",\"sqlwhere\":\"\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"master_holiday\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"created_date\":\"@+NOW\",\"created_by\":\"@+SESSION-FULL_NAME\",\"hari\":\"$keterangan\",\"bulan_libur\":\"$tanggal(fdatedb)\"},\"callz\":\"\",\"dbmode\":\"insert\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 163916
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `DataAction` (`setresult`)
- **Detail:**
```json
{
  "param2": "",
  "param1": "1"
}
```

---
##### Route ID: 163917
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`closeform`)
- **Detail:**
```json
{
  "param1": ""
}
```

---
##### Route ID: 163918
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
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

---
##### Route ID: 163919
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT max(id) FROM master_holiday
```

---
#### Komponen: `save` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 163920
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $tanggal  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Tanggal] can't be empty !`

---
##### Route ID: 163921
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Are You Sure ?`

---
##### Route ID: 163922
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 163923
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $keterangan  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Keterangan] can't be empty!`

---
##### Route ID: 163924
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 1 FROM master_holiday 
WHERE bulan_libur = ?
```

---
##### Route ID: 163925
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @select[0,0](rows) > 0`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Tanggal] already available !`

---
##### Route ID: 163926
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 203751
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT DATE_FORMAT(bulan_libur,'%d/%m/%Y') FROM master_holiday 
WHERE DATE_FORMAT(bulan_libur,'%d/%m/%Y') = ?
```

---
---

## FORM: `log_history_libur`

### 1. Validasi & Mandatori Field (Input Components)
- _Tidak ada field mandatori yang didefinisikan secara eksplisit di komponen._

### 2. Business Logic, Queries & Actions
#### Komponen: `load` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 165993
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-FULL_NAME <> SPV Kertas Kerja`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT id,DATE_FORMAT(bulan_libur,"%d/%m/%Y") AS "Tanggal",
hari AS "Keterangan", 
created_by AS "Created By",
created_date AS "Created Date",
status AS "Status"
FROM history_holiday WHERE 1=1 @?
```

---
##### Route ID: 165994
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`smartgrid`)
- **Parameter:** $grid -> @view_data_libur

---
##### Route ID: 165995
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $search <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%'",
  "param3": "$search",
  "param2": "AND (DATE_FORMAT(bulan_libur,\"%d/%m/%Y\") LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 165996
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%')",
  "param3": "$search",
  "param2": "OR hari LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 165997
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%'",
  "param3": "$search",
  "param2": "OR status LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 165998
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%'",
  "param3": "$search",
  "param2": "OR created_by LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 181761
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-FULL_NAME  SPV Kertas Kerja`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT id,DATE_FORMAT(bulan_libur,"%d/%m/%Y") AS "Tanggal",
hari AS "Keterangan", 
created_by AS "Created By",
created_date AS "Created Date",
status AS "Status"
FROM history_holiday_kertas_kerja WHERE 1=1 @?
```

---
##### Route ID: 181762
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`smartgrid`)
- **Parameter:** $grid -> @view_data_libur

---
#### Komponen: `search` (Tipe: text)
Komponen ini memicu aliran logika berikut:

##### Route ID: 165999
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$load"
}
```

---
#### Komponen: `btn_search` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 166000
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$load"
}
```

---
---

## FORM: `upload_master_libur`

### 1. Validasi & Mandatori Field (Input Components)
- _Tidak ada field mandatori yang didefinisikan secara eksplisit di komponen._

### 2. Business Logic, Queries & Actions
#### Komponen: `btn_upload` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161629
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`submit`)
- **Detail:**
```json
{
  "param2": "1",
  "param1": "$file"
}
```

---
#### Komponen: `btn_process` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161630
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Form Baru (`upload_master_libur_dialog`)

---
#### Komponen: `btn_template` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161631
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT bulan_libur AS "Tanggal",hari AS "Keterangan" FROM master_holiday 
WHERE 1=2
```

---
##### Route ID: 161632
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** @result -> @template_master_libur

---
##### Route ID: 161633
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `ReportAction` (`export`)
- **Detail:**
```json
{
  "param3": "",
  "param2": "Template Libur.xlsx",
  "param1": "@result"
}
```

---
#### Komponen: `navfile` (Tipe: navresult)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161643
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+RESPONSECODE  1`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $fname -> @+RESULT (json)

---
##### Route ID: 161644
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$rfile"
}
```

---
##### Route ID: 161645
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  123`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Successfull`

---
##### Route ID: 161646
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `DataAction` (`setresult`)
- **Detail:**
```json
{
  "param2": "",
  "param1": "1"
}
```

---
##### Route ID: 161647
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`closeform`)
- **Detail:**
```json
{
  "param1": ""
}
```

---
##### Route ID: 161648
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  123`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Data Kosong !`

---
#### Komponen: `load` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 181725
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `DefinitionAction` (`arg`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "",
  "param3": "",
  "param2": "",
  "param1": "@parameter",
  "param10": "",
  "param9": "",
  "param8": "",
  "param7": "",
  "param6": ""
}
```

---
##### Route ID: 181726
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $parameter -> @parameter

---
---

## FORM: `upload_master_libur_dialog`

### 1. Validasi & Mandatori Field (Input Components)
- _Tidak ada field mandatori yang didefinisikan secara eksplisit di komponen._

### 2. Business Logic, Queries & Actions
#### Komponen: `load` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161582
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `DefinitionAction` (`arg`)
- **Detail:**
```json
{
  "param5": "@filename",
  "param4": "@champion",
  "param3": "@product",
  "param2": "@max",
  "param1": "@fname",
  "param10": "",
  "param9": "",
  "param8": "",
  "param7": "",
  "param6": "@parameter"
}
```

---
##### Route ID: 161583
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $lbldata -> @fname

---
##### Route ID: 161584
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $max -> @max

---
##### Route ID: 161585
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $ll -> @filename

---
##### Route ID: 161586
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $product -> @product

---
##### Route ID: 161587
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $champion -> @champion

---
##### Route ID: 161588
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$timer"
}
```

---
##### Route ID: 181727
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $parameter -> @parameter

---
##### Route ID: 181728
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $parameter  master libur kertas kerja`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 181729
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $simpan_kertas_kerja -> true

---
##### Route ID: 181730
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 181731
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $simpan -> true

---
#### Komponen: `simpan` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161589
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setenable`)
- **Parameter:** $simpan -> false

---
##### Route ID: 161590
**Kondisi (Expression):**
- Raw: `{
  "result": "",
  "flag": "",
  "args": {},
  "code": "first",
  "class": "BooleanExpression",
  "id": "111"
}`

**Aksi (Action):**
- **Tipe Eksekusi:** `StorageAction` (`storage`)
- **Detail:**
```json
{
  "param7": "",
  "param6": "",
  "param5": "",
  "param4": "@dt",
  "param3": "readxlsx",
  "param2": "$lbldata",
  "param1": "temp"
}
```

---
##### Route ID: 161591
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$timer"
}
```

---
##### Route ID: 161592
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @dt (rows)  0`

**Aksi (Action):**
- **Tipe Eksekusi:** `DataAction` (`setresult`)
- **Detail:**
```json
{
  "param2": "",
  "param1": "111"
}
```

---
##### Route ID: 161593
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`closeform`)
- **Detail:**
```json
{
  "param1": ""
}
```

---
##### Route ID: 161594
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 161595
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `DataAction` (`nikitaset`)
- **Detail:**
```json
{
  "result": "@currdt",
  "param6": "",
  "param4": "",
  "param3": "@+LOGICCOUNT",
  "param2": "@dt"
}
```

---
##### Route ID: 161596
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `VariableAction` (`new`)
- **Detail:**
```json
{
  "param4": "@currdt (nset)",
  "param3": "0",
  "param2": "",
  "param1": "@currdt"
}
```

---
##### Route ID: 161597
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `DataAction` (`newnikitaset`)
- **Detail:**
```json
{
  "result": "@currns",
  "param4": "",
  "param3": "",
  "param2": "@currdt",
  "param1": "@dt[\"header\"]"
}
```

---
##### Route ID: 161598
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 161599
**Kondisi (Expression):**
- Raw: `{
  "result": "",
  "flag": "",
  "args": {},
  "code": "",
  "class": "",
  "id": ""
}`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT bulan_libur FROM master_holiday WHERE bulan_libur = ?
```

---
##### Route ID: 161600
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @currns[0,"Tanggal"]  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Date can't be empty`

---
##### Route ID: 161601
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`closeform`)
- **Detail:**
```json
{
  "param1": ""
}
```

---
##### Route ID: 161602
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 161603
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT
(SELECT 1 WHERE ? REGEXP '^[0-3][0-9]/[0-1][0-9]/[0-9]{4}$')
```

---
##### Route ID: 161604
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_tanggal[0,0]  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Date format  is incorrect`

---
##### Route ID: 161605
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`closeform`)
- **Detail:**
```json
{
  "param1": ""
}
```

---
##### Route ID: 161606
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 161607
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_duplikat[0,0]  @currns [0,"Tanggal"] (fdatedb)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Data Already Exists`

---
##### Route ID: 161608
**Kondisi (Expression):**
- Raw: `{
  "result": "",
  "flag": "",
  "args": {},
  "code": "",
  "class": "",
  "id": ""
}`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`closeform`)
- **Detail:**
```json
{
  "param1": ""
}
```

---
##### Route ID: 161609
**Kondisi (Expression):**
- Raw: `{
  "result": "",
  "flag": "",
  "args": {},
  "code": "",
  "class": "",
  "id": ""
}`

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 161610
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** `DataAction` (`setresult`)
- **Detail:**
```json
{
  "param2": "",
  "param1": "123"
}
```

---
##### Route ID: 161611
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`closeform`)
- **Detail:**
```json
{
  "param1": ""
}
```

---
##### Route ID: 161612
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_duplikat[0,0] <> @currns[0,"Tanggal"] (fdatedb)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@x",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"0\",\"sqlwhere\":\"\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"master_holiday\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"created_date\":\"@+NOW()\",\"created_by\":\"@+SESSION-FULL_NAME\",\"hari\":\"@currns[0,\\\"Keterangan\\\"]\",\"bulan_libur\":\"@currns[0,\\\"Tanggal\\\"](fdatedb)\"},\"callz\":\"\",\"dbmode\":\"insert\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 161613
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`loop`)
- **Detail:**
```json
{
  "param1": "@dt (rows)"
}
```

---
##### Route ID: 161614
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT MAX(id) FROM master_holiday
```

---
##### Route ID: 161615
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
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
"UPLOAD"
FROM master_holiday
WHERE id=?
```

---
#### Komponen: `simpan_kertas_kerja` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 181732
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 181733
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setenable`)
- **Parameter:** $simpan -> false

---
##### Route ID: 181734
**Kondisi (Expression):**
- Raw: `{
  "result": "",
  "flag": "",
  "args": {},
  "code": "first",
  "class": "BooleanExpression",
  "id": "111"
}`

**Aksi (Action):**
- **Tipe Eksekusi:** `StorageAction` (`storage`)
- **Detail:**
```json
{
  "param7": "",
  "param6": "",
  "param5": "",
  "param4": "@dt",
  "param3": "readxlsx",
  "param2": "$lbldata",
  "param1": "temp"
}
```

---
##### Route ID: 181735
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$timer"
}
```

---
##### Route ID: 181736
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @dt (rows)  0`

**Aksi (Action):**
- **Tipe Eksekusi:** `DataAction` (`setresult`)
- **Detail:**
```json
{
  "param2": "",
  "param1": "111"
}
```

---
##### Route ID: 181737
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`closeform`)
- **Detail:**
```json
{
  "param1": ""
}
```

---
##### Route ID: 181738
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 181739
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `DataAction` (`nikitaset`)
- **Detail:**
```json
{
  "result": "@currdt",
  "param6": "",
  "param4": "",
  "param3": "@+LOGICCOUNT",
  "param2": "@dt"
}
```

---
##### Route ID: 181740
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `VariableAction` (`new`)
- **Detail:**
```json
{
  "param4": "@currdt (nset)",
  "param3": "0",
  "param2": "",
  "param1": "@currdt"
}
```

---
##### Route ID: 181741
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `DataAction` (`newnikitaset`)
- **Detail:**
```json
{
  "result": "@currns",
  "param4": "",
  "param3": "",
  "param2": "@currdt",
  "param1": "@dt[\"header\"]"
}
```

---
##### Route ID: 181742
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 181743
**Kondisi (Expression):**
- Raw: `{
  "result": "",
  "flag": "",
  "args": {},
  "code": "",
  "class": "",
  "id": ""
}`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT bulan_libur FROM master_holiday_kertas_kerja WHERE bulan_libur = ?
```

---
##### Route ID: 181744
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT
(SELECT 1 WHERE ? REGEXP '^[0-3][0-9]/[0-1][0-9]/[0-9]{4}$')
```

---
##### Route ID: 181745
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_duplikat[0,0] <> @currns[0,"Tanggal"] (fdatedb)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@x",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"0\",\"sqlwhere\":\"\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"master_holiday_kertas_kerja\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"created_date\":\"@+NOW()\",\"created_by\":\"@+SESSION-FULL_NAME\",\"hari\":\"@currns[0,\\\"Keterangan\\\"]\",\"bulan_libur\":\"@currns[0,\\\"Tanggal\\\"](fdatedb)\"},\"callz\":\"\",\"dbmode\":\"insert\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 181746
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT MAX(id) FROM master_holiday_kertas_kerja
```

---
##### Route ID: 181747
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
INSERT INTO history_holiday_kertas_kerja (
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
"UPLOAD"
FROM master_holiday_kertas_kerja
WHERE id=?
```

---
##### Route ID: 181748
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`loop`)
- **Detail:**
```json
{
  "param1": "@dt (rows)"
}
```

---
##### Route ID: 181749
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @currns[0,"Tanggal"]  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Date can't be empty`

---
##### Route ID: 181750
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`closeform`)
- **Detail:**
```json
{
  "param1": ""
}
```

---
##### Route ID: 181751
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 181752
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_tanggal[0,0]  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Date format  is incorrect`

---
##### Route ID: 181753
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`closeform`)
- **Detail:**
```json
{
  "param1": ""
}
```

---
##### Route ID: 181754
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 181755
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_duplikat[0,0]  @currns [0,"Tanggal"] (fdatedb)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Data Already Exists`

---
##### Route ID: 181756
**Kondisi (Expression):**
- Raw: `{
  "result": "",
  "flag": "",
  "args": {},
  "code": "",
  "class": "",
  "id": ""
}`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`closeform`)
- **Detail:**
```json
{
  "param1": ""
}
```

---
##### Route ID: 181757
**Kondisi (Expression):**
- Raw: `{
  "result": "",
  "flag": "",
  "args": {},
  "code": "",
  "class": "",
  "id": ""
}`

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 181758
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** `DataAction` (`setresult`)
- **Detail:**
```json
{
  "param2": "",
  "param1": "123"
}
```

---
##### Route ID: 181759
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`closeform`)
- **Detail:**
```json
{
  "param1": ""
}
```

---
