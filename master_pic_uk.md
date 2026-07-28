# Blueprint Rewrite Spring Boot: Modul `master_pic_uk`

Dokumen ini berisi kumpulan query, logic, parameter, dan mandatori yang diekstrak langsung dari _low-code engine_. Gunakan dokumen ini sebagai referensi utama saat melakukan rewrite ke Spring Boot (Controller, Service, dan Repository).

---

## FORM: `master_pic_uk`

### 1. Validasi & Mandatori Field (Input Components)
- _Tidak ada field mandatori yang didefinisikan secara eksplisit di komponen._

### 2. Business Logic, Queries & Actions
#### Komponen: `load` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

#### Route ID: 179312
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$btn_search"
}
```

---
#### Komponen: `res` (Tipe: navresult)
Komponen ini memicu aliran logika berikut:

#### Route ID: 226724
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$btn_uncheck_all"
}
```

---
#### Komponen: `search` (Tipe: text)
Komponen ini memicu aliran logika berikut:

#### Route ID: 179322
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$btn_search"
}
```

---
#### Komponen: `btn_search` (Tipe: button)
Komponen ini memicu aliran logika berikut:

#### Route ID: 199331
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_mode_uk <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "''",
  "param3": "$cb_mode_uk",
  "param2": "AND ms.mode_uk = '",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
#### Komponen: `load_visible` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

#### Route ID: 227059
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_history -> false

---
#### Komponen: `load_combo` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

#### Route ID: 199329
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cb_mode_uk -> @mode_uk

---
#### Komponen: `btn_check_all` (Tipe: button)
Komponen ini memicu aliran logika berikut:

#### Route ID: 179396
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
#### Komponen: `btn_uncheck_all` (Tipe: button)
Komponen ini memicu aliran logika berikut:

#### Route ID: 179398
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
#### Komponen: `btn_assign` (Tipe: button)
Komponen ini memicu aliran logika berikut:

#### Route ID: 179404
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
select 
	case 
		when ? = '[]' then 'Centang Salah Satu Porject'
		when ? = '' then 'Pilih Mode Terlebih Dahulu'
	end as validasi
```

---
#### Komponen: `btn_update` (Tipe: button)
Komponen ini memicu aliran logika berikut:

#### Route ID: 227972
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
#### Komponen: `btn_approve` (Tipe: button)
Komponen ini memicu aliran logika berikut:

#### Route ID: 227064
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
#### Komponen: `btn_reject` (Tipe: button)
Komponen ini memicu aliran logika berikut:

#### Route ID: 227066
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
#### Komponen: `btn_history` (Tipe: button)
Komponen ini memicu aliran logika berikut:

#### Route ID: 226794
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showform`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "",
  "param3": "",
  "param2": "normal",
  "param1": "{\"args\":{\"slip_gaji\":\"\"},\"formname\":\"log_history_mfee_uk\"}"
}
```

---
---

## FORM: `log_history_mfee_uk`

### 1. Validasi & Mandatori Field (Input Components)
- _Tidak ada field mandatori yang didefinisikan secara eksplisit di komponen._

### 2. Business Logic, Queries & Actions
#### Komponen: `load` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

#### Route ID: 226798
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cbx_employee_type <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "''",
  "param3": "$cbx_employee_type",
  "param2": "AND p.employee_type= '",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
#### Komponen: `search` (Tipe: text)
Komponen ini memicu aliran logika berikut:

#### Route ID: 226783
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

#### Route ID: 226784
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
#### Komponen: `load_combo` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

#### Route ID: 226797
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbx_employee_type -> @employee_type

---
