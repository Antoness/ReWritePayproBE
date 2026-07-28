# Blueprint Rewrite Spring Boot: Modul `master_pic_project`

Dokumen ini berisi kumpulan query, logic, parameter, dan mandatori yang diekstrak langsung dari _low-code engine_ secara dinamis beserta form turunannya. Gunakan dokumen ini sebagai referensi utama saat melakukan rewrite ke Spring Boot (Controller, Service, dan Repository).

---

## FORM: `master_pic_project`

### 1. Validasi & Mandatori Field (Input Components)
- _Tidak ada field mandatori yang didefinisikan secara eksplisit di komponen._

### 2. Business Logic, Queries & Actions
#### Komponen: `load` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 155228
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

##### Route ID: 155229
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  assign_user`

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)

---
##### Route ID: 155230
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)
- **Detail:**
```json
{
  "param2": "green",
  "param1": "Assign project"
}
```

---
##### Route ID: 155231
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** @array -> $grid (nset)

---
##### Route ID: 155232
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** @array -> @array [selected]

---
##### Route ID: 155233
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
update master_salary set pic = ? where id in @?
```

---
##### Route ID: 155234
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
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Success`

---
##### Route ID: 155235
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
##### Route ID: 155236
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  1`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 155237
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

##### Route ID: 155238
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

##### Route ID: 155239
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT DISTINCT ms.id,
ms.division AS "Division",
ms.unit_name AS "Unit",
ms.position AS "Position",
ms.branch AS "Branch",
ms.employee_type AS "Employee Type",
pic as Pic
FROM master_salary ms 
LEFT JOIN users u ON u.id=ms.id_user
LEFT JOIN master_upliner mup on u.nik = mup.nik
WHERE (  
mup.nama_upliner = ? OR
u.leader = ?) AND (ms.approval = "APPROVED" or ms.approval = "PROCESSED") @?
```

---
##### Route ID: 155240
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`smartgrid`)
- **Parameter:** $grid -> @data_client

---
##### Route ID: 155241
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
  "param2": "AND (ms.division LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 155242
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%'",
  "param3": "$search",
  "param2": " OR ms.unit_name LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 155243
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%'",
  "param3": "$search",
  "param2": " OR ms.position LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 155244
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%')",
  "param3": "$search",
  "param2": " OR ms.branch LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 155245
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cbx_division <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "''",
  "param3": "$cbx_division",
  "param2": "AND ms.division = '",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 155246
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cbx_unit <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "''",
  "param3": "$cbx_unit",
  "param2": "AND ms.unit_name = '",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 155247
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cbx_position <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "''",
  "param3": "$cbx_position",
  "param2": "AND ms.position = '",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 155248
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cbx_branch <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "''",
  "param3": "$cbx_branch",
  "param2": "AND ms.branch = '",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 155249
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cbx_periode <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "''",
  "param3": "$cbx_periode",
  "param2": "AND ms.periode_payroll = '",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 155250
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
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $buffer -> @data_client (arrayselected)

---
##### Route ID: 155251
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@search`

---
##### Route ID: 155252
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** @array -> $grid (nset)

---
##### Route ID: 155253
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** @array -> @array [selected]

---
#### Komponen: `load_visible` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 155273
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  SPV`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_proses -> true

---
##### Route ID: 155274
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Staff`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_request -> true

---
##### Route ID: 155275
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  SPV`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $cbx_division -> true

---
##### Route ID: 155276
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Staff`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $cbx_divisionstaff -> true

---
##### Route ID: 155277
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
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $cbx_unit -> true

---
##### Route ID: 155278
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
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $cbx_unitstaff -> true

---
##### Route ID: 155279
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
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $cbx_position -> true

---
##### Route ID: 155280
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
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $cbx_positionstaff -> true

---
##### Route ID: 155281
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
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $cbx_branch -> true

---
##### Route ID: 155282
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
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $cbx_branchstaff -> true

---
##### Route ID: 155283
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
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_check_all -> true

---
##### Route ID: 155284
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
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_uncheck_all -> true

---
##### Route ID: 155285
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
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_approve -> true

---
##### Route ID: 155286
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $cbx_status -> true

---
#### Komponen: `load_combo` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 155287
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT DISTINCT ms.division FROM master_salary ms
LEFT JOIN users u ON u.id=ms.id_user
LEFT JOIN master_upliner mup on u.nik = mup.nik
WHERE (  
mup.nama_upliner = ? or
u.leader = ?) AND
(ms.approval = "APPROVED" or ms.approval = "PROCESSED") ORDER BY ms.division ASC
```

---
##### Route ID: 155288
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbx_division -> @division

---
##### Route ID: 155289
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT DISTINCT approval FROM master_salary
```

---
##### Route ID: 155290
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbx_status -> @status

---
##### Route ID: 155291
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT full_name from users where position = 'Staff' AND user_status='ACTIVE'
```

---
##### Route ID: 155292
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbx_user -> @users

---
#### Komponen: `btn_check_all` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 155294
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** @dt -> $grid (nset)

---
##### Route ID: 155295
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "",
  "param3": "$buffer (trim)",
  "param2": "@dt [\"selected\"] (json)",
  "param1": "",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@dt",
  "param6": ""
}
```

---
##### Route ID: 155296
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`replace `)
- **Detail:**
```json
{
  "result": "@dt",
  "param3": ",",
  "param2": "][",
  "param1": "@dt"
}
```

---
##### Route ID: 155297
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`replace `)
- **Detail:**
```json
{
  "result": "@dt",
  "param3": "'[",
  "param2": "'[,",
  "param1": "@dt"
}
```

---
##### Route ID: 155298
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
##### Route ID: 155299
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
#### Komponen: `btn_uncheck_all` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 155300
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`smartgrid`)
- **Parameter:** $grid -> @data_client

---
##### Route ID: 155301
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

##### Route ID: 155302
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Are You Sure?`

---
##### Route ID: 155303
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF !validasi <> `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `!validasi`

---
##### Route ID: 155304
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
##### Route ID: 155305
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** @array -> $grid (nset)

---
##### Route ID: 155306
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** @array -> @array [selected]

---
##### Route ID: 155307
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
select 
	case 
		when ? = '[]' then 'Centang Salah Satu Porject'
		when ? = '' then 'Pilih User Terlebih Dahulu'
	end as validasi
```

---
