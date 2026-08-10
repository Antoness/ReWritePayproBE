# Blueprint Rewrite Spring Boot: Modul `master_bank`

Dokumen ini berisi kumpulan query, logic, parameter, dan mandatori yang diekstrak langsung dari _low-code engine_ secara dinamis beserta form turunannya. Gunakan dokumen ini sebagai referensi utama saat melakukan rewrite ke Spring Boot (Controller, Service, dan Repository).

---

## FORM: `master_bank`

### 1. Validasi & Mandatori Field (Input Components)
- _Tidak ada field mandatori yang didefinisikan secara eksplisit di komponen._

### 2. Business Logic, Queries & Actions
#### Komponen: `load` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 199216
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 
    m.id,
    m.nama_bank AS 'Nama Bank',
    m.swift_code AS 'Kode SWIFT',
    m.kode_bi AS 'Kode BI',
    COALESCE(m.updated_date,m.created_date) AS 'Tanggal Input / Update',
    u.full_name AS 'PIC Input / Update'
FROM master_bank m
LEFT JOIN users u 
    ON u.id = COALESCE(m.updated_by, m.created_by)
LEFT JOIN bank_alias b
    ON b.id_bank = m.id
WHERE 1=1 @?
GROUP BY m.id
```

---
##### Route ID: 199217
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`smartgrid`)
- **Parameter:** $grid -> @data_grid

---
##### Route ID: 199218
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
  "param2": "AND (m.nama_bank LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 199254
**Kondisi (Expression):**
- Raw: `{
  "result": "",
  "flag": "",
  "args": {},
  "code": "",
  "class": "",
  "id": "110"
}`

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%'",
  "param3": "$search",
  "param2": "OR m.swift_code LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 199255
**Kondisi (Expression):**
- Raw: `{
  "result": "",
  "flag": "",
  "args": {},
  "code": "",
  "class": "",
  "id": "110"
}`

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%')",
  "param3": "$search",
  "param2": "OR m.kode_bi LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 199256
**Kondisi (Expression):**
- Raw: `{
  "result": "",
  "flag": "hide",
  "args": {},
  "code": "",
  "class": "",
  "id": "110"
}`

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%')",
  "param3": "$search",
  "param2": "OR b.nama_alias LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 218890
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 218892
**Kondisi (Expression):**
- Raw: `{
  "result": "",
  "flag": "",
  "args": {},
  "code": "",
  "class": "",
  "id": "110"
}`

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%'",
  "param3": "$search",
  "param2": "OR b.nama_alias LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
#### Komponen: `res` (Tipe: navresult)
Komponen ini memicu aliran logika berikut:

##### Route ID: 199227
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  reload`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 199228
**Kondisi (Expression):**
- Raw: `{
  "result": "",
  "flag": "",
  "args": {},
  "code": "",
  "class": "",
  "id": "110"
}`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$load"
}
```

---
#### Komponen: `search` (Tipe: text)
Komponen ini memicu aliran logika berikut:

##### Route ID: 199234
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

##### Route ID: 199235
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
#### Komponen: `load_visible` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 199242
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Staff`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_add -> false

---
##### Route ID: 199243
**Kondisi (Expression):**
- Raw: `{
  "result": "",
  "flag": "hide",
  "args": {},
  "code": "",
  "class": "",
  "id": ""
}`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $lbl -> false

---
#### Komponen: `btn_add` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 218876
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Form Baru (`add_bank`)

---
---

## FORM: `add_bank`

### 1. Validasi & Mandatori Field (Input Components)
- _Tidak ada field mandatori yang didefinisikan secara eksplisit di komponen._

### 2. Business Logic, Queries & Actions
#### Komponen: `btn_add` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 218853
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Are You Sure ?`

---
##### Route ID: 218858
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $kode_bi  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Complete all the fields!`

---
##### Route ID: 218887
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 
  CASE 
    WHEN nama_bank = ? THEN 'Nama Bank already exists'
	WHEN swift_code = ? THEN 'Swift Code already exist'
  END AS error_msg
FROM master_bank
WHERE nama_bank = ?
OR swift_code = ?
LIMIT 1
```

---
##### Route ID: 218888
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @validasi_duplikat[0,0] <> `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@validasi_duplikat[0,0]`

---
##### Route ID: 218889
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 226282
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $nama_bank = BCA`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Swift Code hanya diisi untuk bank selain BCA`

---
##### Route ID: 226283
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
#### Komponen: `res` (Tipe: navresult)
Komponen ini memicu aliran logika berikut:

##### Route ID: 218854
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  insert_data`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@insert",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"0\",\"sqlwhere\":\"\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"master_bank\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"status\":\"Aktif\",\"created_date\":\"@+NOW\",\"kode_bi\":\"$kode_bi\",\"nama_bank\":\"$nama_bank\",\"created_by\":\"@+SESSION-ID\",\"swift_code\":\"$swift_code\"},\"callz\":\"\",\"dbmode\":\"insert\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 218855
**Kondisi (Expression):**
- Raw: `{
  "result": "",
  "flag": "",
  "args": {},
  "code": "",
  "class": "",
  "id": "110"
}`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Successfull`

---
##### Route ID: 218856
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
##### Route ID: 218857
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
- **Tipe Eksekusi:** `DataAction` (`setresult`)
- **Detail:**
```json
{
  "param2": "",
  "param1": "add_data"
}
```

---
##### Route ID: 218893
**Kondisi (Expression):**
- Raw: `{
  "result": "",
  "flag": "hide",
  "args": {},
  "code": "",
  "class": "",
  "id": ""
}`

**Aksi (Action):**
- **Tipe Eksekusi:** `DataAction` (`setresult`)
- **Detail:**
```json
{
  "param2": "",
  "param1": "insert_bank_alias"
}
```

---
