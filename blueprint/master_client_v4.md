# Blueprint Rewrite Spring Boot: Modul `master_client_v4`

Dokumen ini berisi kumpulan query, logic, parameter, dan mandatori yang diekstrak langsung dari _low-code engine_ secara dinamis beserta form turunannya. Gunakan dokumen ini sebagai referensi utama saat melakukan rewrite ke Spring Boot (Controller, Service, dan Repository).

---

## FORM: `master_client_v4`

### 1. Validasi & Mandatori Field (Input Components)
- _Tidak ada field mandatori yang didefinisikan secara eksplisit di komponen._

### 2. Business Logic, Queries & Actions
#### Komponen: `load` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212292
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

##### Route ID: 212293
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  delete_client`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** @data_client -> $grid (nset)

---
##### Route ID: 212294
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** @id -> @data_client ["currentdata",0]

---
##### Route ID: 212295
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)

---
##### Route ID: 212296
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  11`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$load"
}
```

---
##### Route ID: 212297
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
##### Route ID: 212298
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  request`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$btn_search"
}
```

---
##### Route ID: 212299
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
##### Route ID: 212300
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$load_combo2"
}
```

---
##### Route ID: 212301
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
SELECT created_by FROM master_salary WHERE id = ?
```

---
##### Route ID: 212302
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @session[0,0] = @+SESSION-UPPLINER`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `You not have access to delete this data`

---
##### Route ID: 212303
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)
- **Detail:**
```json
{
  "param2": "red",
  "param1": "Process"
}
```

---
##### Route ID: 212304
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)
- **Detail:**
```json
{
  "param2": "red",
  "param1": "Request"
}
```

---
##### Route ID: 212305
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)
- **Detail:**
```json
{
  "param2": "red",
  "param1": "delete"
}
```

---
##### Route ID: 212306
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  process`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$btn_search"
}
```

---
##### Route ID: 212307
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@history_client",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"0\",\"sqlwhere\":\"\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"history_salary\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"submitted_user\":\"$nominal_submiteduser\",\"employee_type\":\"$employee_type\",\"jht_employee\":\"$jht_employee\",\"sewa_laptop\":\"$sewa_laptop\",\"grading_allowance\":\"$grading_allowance\",\"jamsostek_perusahaan\":\"$jht_perusahaan\",\"branch\":\"$branch\",\"khusus\":\"$khusus\",\"jabatan\":\"$jabatan\",\"cl_retention\":\"$nominal_retention\",\"performance_allowance\":\"$performance_allowance\",\"spesial_threatment\":\"$biaya_jasa_training\",\"lembur_non_upah\":\"$lembur\",\"periode_payroll\":\"$periode (trim)\",\"productivity_non_upah\":\"$productivity_non_tetap\",\"tunjangan_jabatan\":\"$tunjangan_jabatan\",\"created_date\":\"@+NOW()\",\"periode_end\":\"$periode_end (fdatedb)\",\"created_by\":\"@+SESSION-FULL_NAME\",\"salary_type_id\":\"$salary_type\",\"bpjs_perusahaan\":\"$bpjs_perusahaan\",\"workday_type_id\":\"$works_days\",\"jip_type\":\"$jip_type\",\"tunjangan_supervisor\":\"$tunjangan_spv\",\"motorcycle_collector_non_upah\":\"$motorcycle_collector\",\"monthly_comission_non_upah\":\"$monthly_commision\",\"manajemen_fee\":\"$manajemen_fee\",\"shift_allowance_non_upah\":\"$shift_allowance\",\"periode_start\":\"$periode_start (fdatedb)\",\"montly_allowance\":\"$montly_allowance\",\"jkk_perusahaan\":\"$jkk_perusahaan\",\"perdiem_non_upah\":\"$perdiem\",\"transportasi\":\"$transportasi\",\"thr\":\"$thr\",\"komunikasi\":\"$komunikasi\",\"productivity\":\"$productivity\",\"career_allowence\":\"$career_allowance\",\"jkm_perusahaan\":\"$jkm_perusahaan\",\"allowance\":\"$nominal_allowance\",\"bbm\":\"$bbm\",\"skill_allowance\":\"$skill_allowance\",\"position_allowance\":\"$position_allowance\",\"insentif_non_upah\":\"$insentif\",\"birthday_gift__non_upah\":\"$birthday_gift\",\"status\":\"DELETE\",\"makan_meal\":\"$makan_meal\",\"tunjangan_parkir\":\"$tunjangan_parkir\",\"bonus\":\"$bonus\",\"bpjs_employee\":\"$bpjs_employee\",\"sop_reward_non_upah\":\"$sop_reward\",\"jip_employee\":\"$jip_employee\",\"tj_kesehatan_non_upah\":\"$tj_kesehatan\",\"fix\":\"$nominal_fix\",\"total_works\":\"$total_work_day\",\"jip_perusahaan\":\"$jip_perusahaan\",\"position\":\"$position\",\"variable\":\"$nominal_variabel\",\"performance_pay_non_upah\":\"$performance_pay\",\"pay_later_first_transaction\":\"$nominal_pay\",\"daily\":\"$nominal_daily\",\"leader_board_non_upah\":\"$leaderboard\",\"unit_name\":\"$unit\",\"kompensasi_hln_non_upah\":\"$kompensasi_hln\",\"tunjangan_premium\":\"$tunjangan_premium\",\"cl_first_transaction\":\"$nominal_firsttransaction\",\"achivement\":\"$achivment\",\"division\":\"$division\",\"bonus_non_upah\":\"$bonus_non_upah\",\"kinerja\":\"$kinerja\"},\"callz\":\"\",\"dbmode\":\"insert\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 212308
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 212309
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
INSERT INTO history_salary (
	id_parent,
	division,
	unit_name,
	employee_type,
	employee_type_id,
	workday_type_id,
	cut_off,
	position,
	branch,
	salary_type_id,
	fix_allowance,
	notfix_allowance,
	tunjangan_supervisor,
	tunjangan_jabatan,
	skill_allowance,
	grading_allowance,
	montly_allowance,
	performance_allowance,
	position_allowance,
	bbm,
	komunikasi,
	transportasi,
	productivity,
	khusus,
	sewa_laptop,
	makan_meal,
	jabatan,
	career_allowence,
	tunjangan_premium,
	kinerja,
	tunjangan_parkir,
	bonus,
	spesial_threatment,
	variable,
	fix,
	daily,
	allowance,
	submitted_user,
	cl_first_transaction,
	cl_retention,
	pay_later_first_transaction,
	productivity_non_upah,
	insentif_non_upah,
	lembur_non_upah,
	tj_kesehatan_non_upah,
	perdiem_non_upah,
	performance_pay_non_upah,
	leader_board_non_upah,
	monthly_comission_non_upah,
	sop_reward_non_upah,
	kompensasi_hln_non_upah,
	motorcycle_collector_non_upah,
	birthday_gift__non_upah,
	shift_allowance_non_upah,
	cut_off_periode_start,
	cut_off_periode_end,
	bpjs_perusahaan,
	jip_perusahaan,
	jkm_perusahaan,
	jkk_perusahaan,
	jamsostek_perusahaan,
	jip_type,
	bpjs_employee,
	jip_employee,
	jht_employee,
	achivement,
	total_works,
	created_date,
	created_by,
	periode_payroll,
  	periode_start,
	periode_end,
    status
) SELECT
id,
division,
unit_name,
employee_type,
employee_type_id,
workday_type_id,
cut_off,
position,
branch,
salary_type_id,
fix_allowance,
notfix_allowance,
tunjangan_supervisor,
tunjangan_jabatan,
skill_allowance,
grading_allowance,
montly_allowance,
performance_allowance,
position_allowance,
bbm,
komunikasi,
transportasi,
productivity,
khusus,
sewa_laptop,
makan_meal,
jabatan,
career_allowence,
tunjangan_premium,
kinerja,
tunjangan_parkir,
bonus,
spesial_threatment,
variable,
fix,
daily,
allowance,
submitted_user,
cl_first_transaction,
cl_retention,
pay_later_first_transaction,
productivity_non_upah,
insentif_non_upah,
lembur_non_upah,
tj_kesehatan_non_upah,
perdiem_non_upah,
performance_pay_non_upah,
leader_board_non_upah,
monthly_comission_non_upah,
sop_reward_non_upah,
kompensasi_hln_non_upah,
motorcycle_collector_non_upah,
birthday_gift__non_upah,
shift_allowance_non_upah,
cut_off_periode_start,
cut_off_periode_end,
bpjs_perusahaan,
jip_perusahaan,
jkm_perusahaan,
jkk_perusahaan,
jamsostek_perusahaan,
jip_type,
bpjs_employee,
jip_employee,
jht_employee,
achivement,
total_works,
?,
?,
periode_payroll,
periode_start,
periode_end,
'DELETE'
FROM
	master_salary
WHERE id = ?
```

---
##### Route ID: 212310
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@delete",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"@id\"},\"logic\":\"0\",\"type\":\"1\",\"sqlwhere\":\"\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"master_salary\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{},\"callz\":\"\",\"dbmode\":\"delete\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 212311
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  approved`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$btn_search"
}
```

---
##### Route ID: 212312
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)
- **Detail:**
```json
{
  "param2": "red",
  "param1": "Approval"
}
```

---
##### Route ID: 212313
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
- **Tipe Eksekusi:** `DataAction` (`broadcastglobal`)
- **Detail:**
```json
{
  "param2": "@[]",
  "param1": "pop_up"
}
```

---
##### Route ID: 212314
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)
- **Detail:**
```json
{
  "param2": "red",
  "param1": "Update Komponen Upah"
}
```

---
##### Route ID: 212315
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  update_komponen_upah`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$btn_search"
}
```

---
##### Route ID: 212316
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  add_client`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 212317
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 212318
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
- **Tipe Eksekusi:** Menampilkan Form Baru (`add_client_v4`)

---
##### Route ID: 212319
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 212320
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  add_client`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 212321
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
- **Tipe Eksekusi:** Menampilkan Form Baru (`upload_client_v2`)

---
##### Route ID: 212322
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
#### Komponen: `search` (Tipe: text)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212323
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

##### Route ID: 212324
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  SPV`

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
DATE_FORMAT(ms.created_date,'%d/%m/%Y') AS "Created Date",
DATE_FORMAT(ms.update_date,'%d/%m/%Y') AS "Update Date",
ms.created_by AS "Created By",
#Case When ms.approval="REQUEST" then CONCAT('<Button style="border-radius: 8px;background-color: #FFD700; color:black" disabled>', approval, '</button>') else "APPROVED" end AS "Status"
ms.approval AS "Status",
IF(ms.approval='Request',ms.keterangan,'') AS Keterangan
FROM master_salary ms 
LEFT JOIN users u ON u.id=ms.id_user
LEFT JOIN master_upliner mup on u.nik = mup.nik
WHERE (  
mup.nama_upliner = ? or
u.leader = ?) AND
(ms.approval = "REQUEST" or ms.approval = "APPROVED" or ms.approval = "PROCESSED") @?
ORDER BY ms.created_date DESC
```

---
##### Route ID: 212325
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`smartgrid`)
- **Parameter:** $grid -> @data_client

---
##### Route ID: 212326
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
##### Route ID: 212327
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%')",
  "param3": "$search",
  "param2": " OR monthname(STR_TO_DATE(ms.periode_payroll,'%m')) LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 212328
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
##### Route ID: 212329
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
##### Route ID: 212330
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%'",
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
##### Route ID: 212331
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%'",
  "param3": "$search",
  "param2": " OR ms.employee_type LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 212332
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%'",
  "param3": "$search",
  "param2": " OR ms.approval LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 212333
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
##### Route ID: 212334
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
##### Route ID: 212335
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
##### Route ID: 212336
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
##### Route ID: 212337
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
##### Route ID: 212338
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Staff`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT ms.id,
ms.division AS "Division",
ms.unit_name AS "Unit",
ms.position AS "Position",
ms.branch AS "Branch",
ms.employee_type AS "Employee Type",
DATE_FORMAT(ms.created_date,'%d/%m/%Y') AS "Created Date",
DATE_FORMAT(ms.update_date,'%d/%m/%Y') AS "Update Date",
ms.created_by AS "Created By",
ms.approval AS "Status"
FROM master_salary ms LEFT JOIN users u ON u.id=ms.id_user
WHERE  (  ms.id_user = ? OR (ms.created_by = ? AND ms.approval = "APPROVED"))  AND (ms.approval != "DONE" ) @? ORDER BY ms.created_date DESC
```

---
##### Route ID: 212339
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`smartgrid`)
- **Parameter:** $grid -> @data_client

---
##### Route ID: 212340
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cbx_divisionstaff <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "''",
  "param3": "$cbx_divisionstaff",
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
##### Route ID: 212341
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cbx_unitstaff <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "''",
  "param3": "$cbx_unitstaff",
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
##### Route ID: 212342
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cbx_positionstaff <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "''",
  "param3": "$cbx_positionstaff",
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
##### Route ID: 212343
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cbx_branchstaff <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "''",
  "param3": "$cbx_branchstaff",
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
##### Route ID: 212344
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $periode_start <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "''",
  "param3": "$periode_start (fdatedb)",
  "param2": "AND date(ms.periode_start) ='",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 212345
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $periode_end <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "''",
  "param3": "$periode_end (fdatedb)",
  "param2": " AND date(ms.periode_end) ='",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 212346
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)
- **Detail:**
```json
{
  "param2": "red",
  "param1": "coba request dengan filter"
}
```

---
##### Route ID: 212347
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  request`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
UPDATE master_salary SET approval = "REQUEST" 
WHERE approval  LIKE "%NEW%" AND id IN @? @?
```

---
##### Route ID: 212348
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
INSERT INTO history_salary (
	id_parent,
	division,
	unit_name,
	employee_type,
	employee_type_id,
	workday_type_id,
	cut_off,
	position,
	branch,
	salary_type_id,
	fix_allowance,
	notfix_allowance,
	tunjangan_supervisor,
	tunjangan_jabatan,
	skill_allowance,
	grading_allowance,
	montly_allowance,
	performance_allowance,
	position_allowance,
	bbm,
	komunikasi,
	transportasi,
	productivity,
	khusus,
	sewa_laptop,
	makan_meal,
	jabatan,
	career_allowence,
	tunjangan_premium,
	kinerja,
	tunjangan_parkir,
	bonus,
	spesial_threatment,
	variable,
	fix,
	daily,
	allowance,
	submitted_user,
	cl_first_transaction,
	cl_retention,
	pay_later_first_transaction,
	productivity_non_upah,
	insentif_non_upah,
	lembur_non_upah,
	tj_kesehatan_non_upah,
	perdiem_non_upah,
	performance_pay_non_upah,
	leader_board_non_upah,
	monthly_comission_non_upah,
	sop_reward_non_upah,
	kompensasi_hln_non_upah,
	motorcycle_collector_non_upah,
	birthday_gift__non_upah,
	shift_allowance_non_upah,
	cut_off_periode_start,
	cut_off_periode_end,
	bpjs_perusahaan,
	jip_perusahaan,
	jkm_perusahaan,
	jkk_perusahaan,
	jamsostek_perusahaan,
	jip_type,
	bpjs_employee,
	jip_employee,
	jht_employee,
	achivement,
	total_works,
	created_date,
	update_date,
	created_by,
	periode_start,
  	periode_end,
    status,
  	methode_pajak
) SELECT
id,
division,
unit_name,
employee_type,
employee_type_id,
workday_type_id,
cut_off,
position,
branch,
salary_type_id,
fix_allowance,
notfix_allowance,
tunjangan_supervisor,
tunjangan_jabatan,
skill_allowance,
grading_allowance,
montly_allowance,
performance_allowance,
position_allowance,
bbm,
komunikasi,
transportasi,
productivity,
khusus,
sewa_laptop,
makan_meal,
jabatan,
career_allowence,
tunjangan_premium,
kinerja,
tunjangan_parkir,
bonus,
spesial_threatment,
variable,
fix,
daily,
allowance,
submitted_user,
cl_first_transaction,
cl_retention,
pay_later_first_transaction,
productivity_non_upah,
insentif_non_upah,
lembur_non_upah,
tj_kesehatan_non_upah,
perdiem_non_upah,
performance_pay_non_upah,
leader_board_non_upah,
monthly_comission_non_upah,
sop_reward_non_upah,
kompensasi_hln_non_upah,
motorcycle_collector_non_upah,
birthday_gift__non_upah,
shift_allowance_non_upah,
cut_off_periode_start,
cut_off_periode_end,
bpjs_perusahaan,
jip_perusahaan,
jkm_perusahaan,
jkk_perusahaan,
jamsostek_perusahaan,
jip_type,
bpjs_employee,
jip_employee,
jht_employee,
achivement,
total_works,
?,
update_date,
created_by,
periode_start,
periode_end,
'REQUEST',
methode_pajak
FROM
	master_salary
```

---
##### Route ID: 212349
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
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@request(error)`

---
##### Route ID: 212350
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)
- **Detail:**
```json
{
  "param2": "red",
  "param1": "Coba process data dengan filter"
}
```

---
##### Route ID: 212351
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  process`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
INSERT INTO employee_payroll_data (
`nik`,
`name`, 
`department`, 
`division`,
`unit_name`,
`position`,
`branch`,
`payroll_mode`, 
`npwp`, 
`join_date`, 
`total_of_child`, 
`marital_status`,
`approval`,
`periode_start`,
`periode_end`,
`manajemen_fee`,
`result`,
`resign_date`,
`status_employee`,
`bsu`,
`gender`  
 )
SELECT 
eu.nik,
eu.name,  
eu.department, 
eu.division,
eu.unit_name,
eu.position,
eu.branch,
eu.payroll_mode,
eu.npwp, 
eu.join_date, 
eu.total_of_child, 
eu.marital_status,
"NEW",
ms.periode_start,
ms.periode_end,
ms.manajemen_fee,
"0",
eu.resign_date,
eu.status_employee,
ms.bsu,
eu.gender
FROM employee_update_data eu LEFT JOIN master_salary ms LEFT JOIN users u ON u.id=ms.id_user
ON eu.division = ms.division
WHERE eu.division = ms.division
AND eu.unit_name=ms.unit_name
AND eu.position=ms.position
AND eu.branch=ms.branch
AND eu.employee_type = ms.employee_type 
AND ms.approval = "APPROVED" 
AND (eu.join_date <= ms.periode_start OR eu.join_date <= ms.periode_end) 
AND ((eu.resign_date is NULL AND eu.status_employee="ACTIVE" ) OR eu.resign_date > ms.periode_start OR (eu.resign_date<=ms.periode_end 
AND eu.join_date >= ms.periode_start) OR (eu.resign_date <=ms.periode_end AND eu.resign_date >=ms.periode_start)) @?
```

---
##### Route ID: 212352
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
UPDATE employee_payroll_data x, master_salary z SET 
x.employee_type = z.employee_type,
x.tunjangan_supervisor = z.tunjangan_supervisor,
x.tunjangan_jabatan = z.tunjangan_jabatan,
x.skill_allowance = z.skill_allowance,
x.grading_allowance = z.grading_allowance,
x.montly_allowance = z.montly_allowance,
x.performance_allowance = z.performance_allowance,
x.position_allowance = z.position_allowance,
x.bbm = z.bbm,
x.komunikasi = z.komunikasi,
x.transportasi = z.transportasi,
x.productivity = z.productivity,
x.khusus = z.khusus,
x.sewa_laptop = z.sewa_laptop,
x.makan_meal = z.makan_meal,
x.jabatan = z.jabatan,
x.career_allowence = z.career_allowence,
x.tunjangan_premium = z.tunjangan_premium,
x.kinerja = z.kinerja,
x.tunjangan_parkir = z.tunjangan_parkir,
x.variable = z.variable,
x.fix = z.fix,
x.daily = z.daily,
x.allowance = z.allowance,
x.submitted_user = z.submitted_user,
x.cl_first_transaction = z.cl_first_transaction,
x.cl_retention = z.cl_retention,
x.pay_later_first_transaction = z.pay_later_first_transaction,
x.bonus = z.bonus,
x.spesial_threatment = z.spesial_threatment,
x.created_date = z.created_date,
x.update_date = z.update_date,
x.created_by = z.created_by,
x.insentif_non_upah = z.insentif_non_upah,
x.lembur_non_upah = z.lembur_non_upah,
x.productivity_non_upah = z.productivity_non_upah,
x.tj_kesehatan_non_upah = z.tj_kesehatan_non_upah,
x.perdiem_non_upah = z.perdiem_non_upah,
x.performance_pay_non_upah = z.performance_pay_non_upah,
x.leader_board_non_upah = z.leader_board_non_upah,
x.monthly_comission_non_upah = z.monthly_comission_non_upah,
x.sop_reward_non_upah = z.sop_reward_non_upah,
x.kompensasi_hln_non_upah = z.kompensasi_hln_non_upah,
x.motorcycle_collector_non_upah = z.motorcycle_collector_non_upah,
x.birthday_gift_non_upah = z.birthday_gift__non_upah,
x.shift_allowance_non_upah = z.shift_allowance_non_upah,
x.bonus_non_upah = z.bonus_non_upah,
x.thr = z.thr,

x.bpjs_perusahaan =z.bpjs_perusahaan,
x.jamsostek_perusahaan = z.jamsostek_perusahaan,
x.jip_perusahaan = z.jip_perusahaan,
x.jkk_perusahaan = z.jkm_perusahaan,
x.jkm_perusahaan = z.jkm_perusahaan,
x.jip_type = z.jip_type,
x.bpjs_employee = z.bpjs_employee,
x.jht_employee = z.jht_employee,
x.jip_employee = z.jip_employee,
x.total_works = z.total_works,
x.manajemen_fee = z.manajemen_fee,
x.achivement = z.achivement
WHERE x.division = z.division
AND x.unit_name=z.unit_name
AND x.position=z.position
AND x.branch=z.branch
AND z.approval = "APPROVED"
AND x.periode_start = z.periode_start
AND x.periode_end = z.periode_end
```

---
##### Route ID: 212353
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
UPDATE employee_payroll_data ep SET ep.payroll_mode = CASE 
	WHEN ep.variable != "0"  THEN "MODE1"
	WHEN ep.fix != "0"  THEN "MODE2"
	WHEN ep.daily != "0" THEN "MODE3"
	WHEN ep.allowance != "0" THEN "MODE4"
	ELSE 'not yet'
END ;
```

---
##### Route ID: 212354
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
UPDATE employee_payroll_data
SET 
	tunjangan_supervisor = IF (tunjangan_supervisor = " ",0,tunjangan_supervisor),
	tunjangan_jabatan = IF(tunjangan_jabatan = " " , 0, tunjangan_jabatan),
	skill_allowance = IF (skill_allowance = " ",0,skill_allowance),
	grading_allowance = IF(grading_allowance = " ",0,grading_allowance),
	montly_allowance = IF (montly_allowance = " ",0,montly_allowance),
	performance_allowance = IF (performance_allowance = " ",0,performance_allowance),
	position_allowance = IF (position_allowance = " ",0,position_allowance)
WHERE payroll_mode = "MODE1" or payroll_mode = "MODE2" OR payroll_mode = "MODE3" or payroll_mode = "MODE4"
```

---
##### Route ID: 212355
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
UPDATE employee_payroll_data
SET 
	bbm = IF (bbm = " ",0,bbm),
	komunikasi = IF (komunikasi = " ",0,komunikasi),
	transportasi = IF (transportasi  = " ",0,transportasi),
	productivity = IF (productivity = " ",0,productivity),
	khusus = IF (khusus = " ",0,khusus),
	sewa_laptop = IF (sewa_laptop = " ",0,sewa_laptop),
	makan_meal = IF (makan_meal = " ",0,makan_meal),
	jabatan = IF (jabatan = " ",0,jabatan),
	career_allowence = IF (career_allowence = " ",0,career_allowence),
	tunjangan_premium = IF (tunjangan_premium = " ",0,tunjangan_premium),
	kinerja = IF (kinerja = " ",0,kinerja),
	tunjangan_parkir = IF(tunjangan_parkir = " ",0,tunjangan_parkir),
	bonus = IF (bonus = " ",0,bonus),
	spesial_threatment = IF (spesial_threatment = " ",0,spesial_threatment)
WHERE payroll_mode = "MODE1" or payroll_mode = "MODE2" OR payroll_mode = "MODE3" or payroll_mode = "MODE4"
```

---
##### Route ID: 212356
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
UPDATE employee_payroll_data
SET 
	wagely = IF (wagely is Null ,0,wagely),
	asuransi = IF (asuransi is Null,0, asuransi),
	potongan_lain = IF (potongan_lain is Null,0,potongan_lain)
WHERE payroll_mode = "MODE1" or payroll_mode = "MODE2" OR payroll_mode = "MODE3" or payroll_mode = "MODE4"
```

---
##### Route ID: 212357
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
UPDATE employee_payroll_data
SET 
	productivity_non_upah = IF (productivity_non_upah = " ",0,productivity_non_upah),
	insentif_non_upah = IF (insentif_non_upah = " ",0, insentif_non_upah),
	lembur_non_upah = IF (lembur_non_upah = " ",0,lembur_non_upah),
	tj_kesehatan_non_upah = IF (tj_kesehatan_non_upah = " ",0 ,tj_kesehatan_non_upah),
	perdiem_non_upah = IF (perdiem_non_upah = " ",0,perdiem_non_upah),
	performance_pay_non_upah = IF (performance_pay_non_upah = " ",0,performance_pay_non_upah),
	leader_board_non_upah = IF (leader_board_non_upah = " ",0,leader_board_non_upah),
	monthly_comission_non_upah = IF (monthly_comission_non_upah = " ",0,monthly_comission_non_upah),
	sop_reward_non_upah = IF (sop_reward_non_upah = " ",0,sop_reward_non_upah),
	kompensasi_hln_non_upah = IF (kompensasi_hln_non_upah = " ",0,kompensasi_hln_non_upah),
	motorcycle_collector_non_upah = IF (motorcycle_collector_non_upah = " ",0,motorcycle_collector_non_upah),
	birthday_gift_non_upah = IF (birthday_gift_non_upah = " ",0,birthday_gift_non_upah),
	shift_allowance_non_upah = IF (shift_allowance_non_upah = " ",0,shift_allowance_non_upah),
	bonus_non_upah = IF (bonus_non_upah = " ",0,bonus_non_upah),
	thr = IF(thr = " ",0,thr),
	achivement = IF (achivement = " ",0,achivement),
	bsu = IF (bsu = " ",0,bsu),
	result = IF (result = " ",0,result)
WHERE payroll_mode = "MODE1" or payroll_mode = "MODE2" OR payroll_mode = "MODE3" or payroll_mode = "MODE4"
```

---
##### Route ID: 212358
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
UPDATE master_salary ms LEFT JOIN users u ON u.id=ms.id_user
SET ms.approval = 'DONE' 
WHERE
	approval = "APPROVED" and (  ms.id_user = ? OR u.leader = ?) @?
```

---
##### Route ID: 212359
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
DELETE t1 FROM employee_payroll_data t1
  JOIN employee_payroll_data t2
  ON t2.name = t1.name 
	AND t2.employee_type = t1.employee_type
	AND t2.division = t1.division
	AND t2.unit_name = t1.unit_name 
	AND t2.position = t1.position 
	AND t2.branch = t1.branch
	AND t2.periode_start = t1.periode_start
	AND t2.periode_end = t2.periode_end
	WHERE t2.id>t1.id
```

---
##### Route ID: 212360
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
INSERT INTO history_salary (
	id_parent,
	division,
	unit_name,
	employee_type,
	employee_type_id,
	workday_type_id,
	cut_off,
	position,
	branch,
	salary_type_id,
	fix_allowance,
	notfix_allowance,
	tunjangan_supervisor,
	tunjangan_jabatan,
	skill_allowance,
	grading_allowance,
	montly_allowance,
	performance_allowance,
	position_allowance,
	bbm,
	komunikasi,
	transportasi,
	productivity,
	khusus,
	sewa_laptop,
	makan_meal,
	jabatan,
	career_allowence,
	tunjangan_premium,
	kinerja,
	tunjangan_parkir,
	bonus,
	spesial_threatment,
	variable,
	fix,
	daily,
	allowance,
	submitted_user,
	cl_first_transaction,
	cl_retention,
	pay_later_first_transaction,
	productivity_non_upah,
	insentif_non_upah,
	lembur_non_upah,
	tj_kesehatan_non_upah,
	perdiem_non_upah,
	performance_pay_non_upah,
	leader_board_non_upah,
	monthly_comission_non_upah,
	sop_reward_non_upah,
	kompensasi_hln_non_upah,
	motorcycle_collector_non_upah,
	birthday_gift__non_upah,
	shift_allowance_non_upah,
	cut_off_periode_start,
	cut_off_periode_end,
	bpjs_perusahaan,
	jip_perusahaan,
	jkm_perusahaan,
	jkk_perusahaan,
	jamsostek_perusahaan,
	jip_type,
	bpjs_employee,
	jip_employee,
	jht_employee,
	achivement,
	total_works,
	created_date,
	update_date,
	created_by,
    periode_start,
    periode_end,
    status,
    bsu,
  	methode_pajak
) SELECT
id,
division,
unit_name,
employee_type,
employee_type_id,
workday_type_id,
cut_off,
position,
branch,
salary_type_id,
fix_allowance,
notfix_allowance,
tunjangan_supervisor,
tunjangan_jabatan,
skill_allowance,
grading_allowance,
montly_allowance,
performance_allowance,
position_allowance,
bbm,
komunikasi,
transportasi,
productivity,
khusus,
sewa_laptop,
makan_meal,
jabatan,
career_allowence,
tunjangan_premium,
kinerja,
tunjangan_parkir,
bonus,
spesial_threatment,
variable,
fix,
daily,
allowance,
submitted_user,
cl_first_transaction,
cl_retention,
pay_later_first_transaction,
productivity_non_upah,
insentif_non_upah,
lembur_non_upah,
tj_kesehatan_non_upah,
perdiem_non_upah,
performance_pay_non_upah,
leader_board_non_upah,
monthly_comission_non_upah,
sop_reward_non_upah,
kompensasi_hln_non_upah,
motorcycle_collector_non_upah,
birthday_gift__non_upah,
shift_allowance_non_upah,
cut_off_periode_start,
cut_off_periode_end,
bpjs_perusahaan,
jip_perusahaan,
jkm_perusahaan,
jkk_perusahaan,
jamsostek_perusahaan,
jip_type,
bpjs_employee,
jip_employee,
jht_employee,
achivement,
total_works,
created_date,
update_date,
created_by,
periode_start,
periode_end,
'DONE',
bsu,
methode_pajak
FROM
	master_salary
WHERE approval="APPROVED"
```

---
##### Route ID: 212361
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
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@insert_employee(error)`

---
##### Route ID: 212362
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
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@insert_employee(error)`

---
##### Route ID: 212363
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
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@insert_employee(error)`

---
##### Route ID: 212364
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
##### Route ID: 212365
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $buffer -> @data_client (arrayselected)

---
##### Route ID: 212366
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** @array -> $grid (nset)

---
##### Route ID: 212367
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** @array -> @array [selected]

---
##### Route ID: 212368
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `DataAction` (`broadcastglobal`)
- **Detail:**
```json
{
  "param2": "@[]",
  "param1": "pop_up"
}
```

---
##### Route ID: 212369
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)
- **Detail:**
```json
{
  "param2": "red",
  "param1": "coba approved dengan filter"
}
```

---
##### Route ID: 212370
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  approved`

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)

---
##### Route ID: 212371
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
INSERT INTO history_salary (
	id_parent,
	division,
	unit_name,
	employee_type,
	employee_type_id,
	workday_type_id,
	cut_off,
	position,
	branch,
	salary_type_id,
	fix_allowance,
	notfix_allowance,
	tunjangan_supervisor,
	tunjangan_jabatan,
	skill_allowance,
	grading_allowance,
	montly_allowance,
	performance_allowance,
	position_allowance,
	bbm,
	komunikasi,
	transportasi,
	productivity,
	khusus,
	sewa_laptop,
	makan_meal,
	jabatan,
	career_allowence,
	tunjangan_premium,
	kinerja,
	tunjangan_parkir,
	bonus,
	spesial_threatment,
	variable,
	fix,
	daily,
	allowance,
	submitted_user,
	cl_first_transaction,
	cl_retention,
	pay_later_first_transaction,
	productivity_non_upah,
	insentif_non_upah,
	lembur_non_upah,
	tj_kesehatan_non_upah,
	perdiem_non_upah,
	performance_pay_non_upah,
	leader_board_non_upah,
	monthly_comission_non_upah,
	sop_reward_non_upah,
	kompensasi_hln_non_upah,
	motorcycle_collector_non_upah,
	birthday_gift__non_upah,
	shift_allowance_non_upah,
	cut_off_periode_start,
	cut_off_periode_end,
	bpjs_perusahaan,
	jip_perusahaan,
	jkm_perusahaan,
	jkk_perusahaan,
	jamsostek_perusahaan,
	jip_type,
	bpjs_employee,
	jip_employee,
	jht_employee,
	achivement,
	total_works,
	created_date,
	created_by,
	periode_start,
  	periode_end,
    status,
  	methode_pajak
) SELECT DISTINCT
id,
division,
unit_name,
employee_type,
employee_type_id,
workday_type_id,
cut_off,
position,
branch,
salary_type_id,
fix_allowance,
notfix_allowance,
tunjangan_supervisor,
tunjangan_jabatan,
skill_allowance,
grading_allowance,
montly_allowance,
performance_allowance,
position_allowance,
bbm,
komunikasi,
transportasi,
productivity,
khusus,
sewa_laptop,
makan_meal,
jabatan,
career_allowence,
tunjangan_premium,
kinerja,
tunjangan_parkir,
bonus,
spesial_threatment,
variable,
fix,
daily,
allowance,
submitted_user,
cl_first_transaction,
cl_retention,
pay_later_first_transaction,
productivity_non_upah,
insentif_non_upah,
lembur_non_upah,
tj_kesehatan_non_upah,
perdiem_non_upah,
performance_pay_non_upah,
leader_board_non_upah,
monthly_comission_non_upah,
sop_reward_non_upah,
kompensasi_hln_non_upah,
motorcycle_collector_non_upah,
birthday_gift__non_upah,
shift_allowance_non_upah,
cut_off_periode_start,
cut_off_periode_end,
bpjs_perusahaan,
jip_perusahaan,
jkm_perusahaan,
jkk_perusahaan,
jamsostek_perusahaan,
jip_type,
bpjs_employee,
jip_employee,
jht_employee,
achivement,
total_works,
?,
?,
periode_start,
periode_end,
'APPROVED',
methode_pajak
FROM
	master_salary
WHERE approval="APPROVED" AND id IN @?
AND NOT EXISTS (
        SELECT 1
        FROM history_salary
        WHERE history_salary.id_parent = master_salary.id
        AND history_salary.status = 'APPROVED' AND history_salary.created_date = ?
    )
```

---
##### Route ID: 212372
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `DataAction` (`broadcastglobal`)
- **Detail:**
```json
{
  "param2": "@[]",
  "param1": "pop_up"
}
```

---
##### Route ID: 212373
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
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@approved(error)`

---
##### Route ID: 212374
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Successfull`

---
##### Route ID: 212375
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 212376
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cbx_status <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "''",
  "param3": "$cbx_status",
  "param2": "AND ms.approval = '",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 212377
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @set[0,0] <> ()`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 
   @NO := CAST(@NO + 1 AS UNSIGNED) AS "No",
    ms.division AS "Division",
    ms.unit_name AS "Unit",
    ms.position AS "Position",
    ms.branch AS "Branch",
    UPPER(ms.employee_type) AS "Employee Type"
FROM 
    master_salary ms 
    LEFT JOIN users u ON u.id = ms.id_user,
    (SELECT @NO := 0) AS nomor 
WHERE 
 ms.id IN @? 
ORDER BY 
    ms.division,
    ms.unit_name,
    ms.position,
    ms.branch,
    ms.employee_type ASC
```

---
##### Route ID: 212378
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
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Success`

---
##### Route ID: 212379
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  update_komponen_upah`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
UPDATE master_salary SET upah_tk= ?
WHERE id IN @?
```

---
##### Route ID: 212380
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
UPDATE payroll_buffer pb LEFT JOIN master_salary ms 
ON pb.master_salary_id = ms.id
SET
-- update gaji dan tipenya
pb.payroll_mode = IF(ms.keterangan LIKE '%Salary Type%',(select salary_mode from master_salary_type where id = ms.salary_type_id),pb.payroll_mode),
pb.variable = IF(ms.keterangan LIKE '%Salary Type%', ms.variable, pb.variable),
pb.fix = IF(ms.keterangan LIKE '%Salary Type%', ms.fix, pb.fix),
pb.daily = IF(ms.keterangan LIKE '%Salary Type%', ms.daily, pb.daily),
pb.allowance = IF(ms.keterangan LIKE '%Salary Type%', ms.allowance, pb.allowance),
pb.achivement = IF(ms.keterangan LIKE '%Salary Type%', ms.achivement, pb.achivement), 
-- update manajemen fee
pb.manajemen_fee = IF(ms.keterangan LIKE '%Manajemen Fee%', ms.manajemen_fee, pb.manajemen_fee),
-- update komponen project
pb.komponen_project = IF(ms.keterangan LIKE '%Komponen Project%', ms.komponen_project, pb.komponen_project),
-- update methode pajak
pb.methode_pajak = IF(ms.keterangan LIKE '%Metode Pajak%', ms.methode_pajak, pb.methode_pajak),
-- update komponen bpjs
pb.upah_tk = IF(ms.keterangan LIKE '%Kategori BPJS%', ms.upah_tk, pb.upah_tk),
pb.bpjs_kesehatan = IF(ms.keterangan LIKE '%Kategori BPJS%', ms.bpjs_kesehatan, pb.bpjs_kesehatan),
pb.bpjs_ketenagakerjaan = IF(ms.keterangan LIKE '%Kategori BPJS%', ms.bpjs_ketenagakerjaan, pb.bpjs_ketenagakerjaan),
pb.persen_bpjs_kesehatan = IF(ms.keterangan LIKE '%Kategori BPJS%', ms.persen_bpjs_kesehatan, pb.persen_bpjs_kesehatan),
-- update bpjs type
pb.jip_type = IF(ms.keterangan LIKE '%Kategori BPJS%', ms.jip_type, pb.jip_type),
-- update komponen asuransi
pb.status_asuransi = IF(ms.keterangan LIKE '%Asuransi%', ms.status_asuransi, pb.status_asuransi),
pb.asuransi_kesehatan = IF(ms.keterangan LIKE '%Asuransi%', ms.asuransi_kesehatan, pb.asuransi_kesehatan),
pb.asuransi_kecelakaan = IF(ms.keterangan LIKE '%Asuransi%', ms.asuransi_kecelakaan,pb.asuransi_kecelakaan),
-- update komponen bpu
pb.bsu = IF(ms.keterangan LIKE '%BPU%', ms.bsu, pb.bsu),
pb.bpu_jkm = IF(ms.keterangan LIKE '%BPU%', ms.bpu_jkm, pb.bpu_jkm),
pb.bpu_jht = IF(ms.keterangan LIKE '%BPU%', ms.bpu_jht, pb.bpu_jht),
-- update kategori tunjangan
pb.kategori_tunjangan_tetap = IF(ms.keterangan LIKE '%Kategori Tunjangan%', ms.kategori_tunjangan_tetap, pb.kategori_tunjangan_tetap),
pb.kategori_tunjangan_tidak_tetap = IF(ms.keterangan LIKE '%Kategori Tunjangan%', ms.kategori_tunjangan_tidak_tetap, pb.kategori_tunjangan_tidak_tetap),
-- update komponen nominal tunjangan
pb.tunjangan_supervisor = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.tunjangan_supervisor), pb.tunjangan_supervisor),
    pb.tunjangan_jabatan = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.tunjangan_jabatan), pb.tunjangan_jabatan),
    pb.grading_allowance = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.grading_allowance), pb.grading_allowance),
    pb.skill_allowance = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.skill_allowance), pb.skill_allowance),
    pb.montly_allowance = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.montly_allowance), pb.montly_allowance),
    pb.performance_allowance = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.performance_allowance), pb.performance_allowance),
    pb.position_allowance = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.position_allowance), pb.position_allowance),
    pb.bbm = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.bbm), pb.bbm),
    pb.komunikasi = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.komunikasi), pb.komunikasi),
    pb.transportasi = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.transportasi), pb.transportasi),
    pb.productivity = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.productivity), pb.productivity),
    pb.khusus = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.khusus), pb.khusus),
    pb.sewa_laptop = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.sewa_laptop), pb.sewa_laptop),
    pb.makan_meal = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.makan_meal), pb.makan_meal),
    pb.career_allowence = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.career_allowence), pb.career_allowence),
    pb.tunjangan_premium = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.tunjangan_premium), pb.tunjangan_premium),
    pb.kinerja = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.kinerja), pb.kinerja),
    pb.tunjangan_parkir = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.tunjangan_parkir), pb.tunjangan_parkir),
    pb.bonus = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.bonus), pb.bonus),
    pb.spesial_threatment = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.spesial_threatment), pb.spesial_threatment),
    pb.insentif_non_upah = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.insentif_non_upah), pb.insentif_non_upah),
    pb.lembur_non_upah = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.lembur_non_upah), pb.lembur_non_upah),
    pb.tj_kesehatan_non_upah = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.tj_kesehatan_non_upah), pb.tj_kesehatan_non_upah),
    pb.perdiem_non_upah = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.perdiem_non_upah), pb.perdiem_non_upah),
    pb.performance_pay_non_upah = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.performance_pay_non_upah), pb.performance_pay_non_upah),
    pb.shift_allowance_non_upah = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.shift_allowance_non_upah), pb.shift_allowance_non_upah),
    pb.bonus_non_upah = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.bonus_non_upah), pb.bonus_non_upah),
    pb.tj_kehadiran = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.tj_kehadiran), pb.tj_kehadiran),
    pb.tj_tugas_harian = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.tj_tugas_harian), pb.tj_tugas_harian),
    pb.tunjangan_operasional = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.tunjangan_operasional), pb.tunjangan_operasional),
    pb.tunjangan_sewa_service_motor = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.tunjangan_sewa_service_motor), pb.tunjangan_sewa_service_motor),
    pb.tunjangan_akomodasi = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.tunjangan_akomodasi), pb.tunjangan_akomodasi),
	pb.project_allowance = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.project_allowance), pb.project_allowance),
	pb.tj_cuti = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.tj_cuti), pb.tj_cuti),
	pb.tj_kerajinan = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.tj_kerajinan), pb.tj_kerajinan),
	pb.tj_masa_kontrak = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.tj_masa_kontrak), pb.tj_masa_kontrak),
	pb.tj_service_charge = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.tj_service_charge), pb.tj_service_charge),
	pb.tj_surveyor_bengkel = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.tj_surveyor_bengkel), pb.tj_surveyor_bengkel),
	pb.tj_tempat_tinggal = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.tj_tempat_tinggal), pb.tj_tempat_tinggal),
	pb.tj_bensin_parkir = IF(ms.keterangan LIKE '%Nominal Tunjangan%', format_nominal(ms.tj_bensin_parkir), pb.tj_bensin_parkir)
		
WHERE ms.id IN @?
  AND pb.approval LIKE "%NEW%" 
  AND (pb.status_data = 'Returned' OR pb.status_data IS NULL) 
  AND ms.approval='REQUEST'
```

---
##### Route ID: 212381
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
UPDATE master_salary SET approval = "APPROVED" 
WHERE approval = "REQUEST" AND id IN @?
```

---
##### Route ID: 212382
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @set[0,0] = ()`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 
   	ROW_NUMBER() OVER (ORDER BY ms.division, ms.unit_name, ms.position, ms.branch, ms.employee_type ASC) AS "No",
    ms.division AS "Division",
    ms.unit_name AS "Unit",
    ms.position AS "Position",
    ms.branch AS "Branch",
    UPPER(ms.employee_type) AS "Employee Type"
FROM 
    master_salary ms 
	LEFT JOIN master_pic mp ON mp.master_salary_id = ms.id 
    LEFT JOIN users u ON u.id = ms.id_user

WHERE 
ms.division = ? AND (ms.id_user = ?  OR mp.`user` = ? OR (ms.created_by = ? AND ms.approval = "APPROVED")) 
AND (ms.approval != "DONE") @?
GROUP BY ms.id 
ORDER BY 
    ms.division,
    ms.unit_name,
    ms.position,
    ms.branch,
    ms.employee_type ASC
```

---
##### Route ID: 212383
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** ``

---
##### Route ID: 212384
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT REPLACE(?, '''', '') AS cleaned_values;
```

---
##### Route ID: 212385
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
UPDATE master_salary SET salary_type_id = CASE 
WHEN variable !=0 AND variable!='' THEN 1
WHEN fix !=0 AND fix!='' THEN 2
WHEN daily !=0 AND daily!='' THEN 3
WHEN allowance !=0 AND allowance !='' THEN 4
WHEN submitted_user !=0 AND submitted_user!='' THEN 5
WHEN cl_first_transaction !=0 OR cl_first_transaction!='' THEN 6
WHEN cl_retention !=0 AND cl_retention!='' THEN 7
WHEN pay_later_first_transaction !=0 AND pay_later_first_transaction!='' THEN 8 END
WHERE id IN @?
```

---
##### Route ID: 212386
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 212387
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 212388
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @set[0,0]  ()`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 
   @NO := CAST(@NO + 1 AS UNSIGNED) AS No,
    ms.division AS Division,
    ms.unit_name AS Unit,
    ms.position AS Position,
    ms.branch AS Branch,
    UPPER(ms.employee_type) AS Employee_Type
FROM master_salary ms
LEFT JOIN users u ON u.id = ms.id_user
LEFT JOIN master_upliner mup ON u.nik = mup.nik,
    (SELECT @NO := 0) AS nomor
WHERE ms.division = ? AND
    (mup.nama_upliner = ? OR u.leader = ?)
    AND (ms.approval IN ('REQUEST', 'APPROVED', 'PROCESSED')) @?
GROUP BY ms.id
```

---
##### Route ID: 212389
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @set[0,0] <> ()`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 
   @NO := CAST(@NO + 1 AS UNSIGNED) AS "No",
    ms.division AS "Division",
    ms.unit_name AS "Unit",
    ms.position AS "Position",
    ms.branch AS "Branch",
    UPPER(ms.employee_type) AS "Employee Type"
FROM 
    master_salary ms 
    LEFT JOIN users u ON u.id = ms.id_user,
    (SELECT @NO := 0) AS nomor 
WHERE 
 ms.id IN @? 
ORDER BY 
    ms.division,
    ms.unit_name,
    ms.position,
    ms.branch,
    ms.employee_type ASC
```

---
##### Route ID: 212390
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Manajer`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 212391
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

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
DATE_FORMAT(ms.created_date,'%d/%m/%Y') AS "Created Date",
DATE_FORMAT(ms.update_date,'%d/%m/%Y') AS "Update Date",
ms.created_by AS "Created By",
#Case When ms.approval="REQUEST" then CONCAT('<Button style="border-radius: 8px;background-color: #FFD700; color:black" disabled>', approval, '</button>') else "APPROVED" end AS "Status"
ms.approval AS "Status",
IF(ms.approval='Request',ms.keterangan,'') AS Keterangan
FROM master_salary ms 
LEFT JOIN users u ON u.id=ms.id_user
LEFT JOIN master_upliner mup on u.nik = mup.nik
WHERE
(ms.approval = "REQUEST" or ms.approval = "APPROVED" or ms.approval = "PROCESSED") @?
ORDER BY ms.created_date DESC
```

---
##### Route ID: 212392
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** ` @search`

---
##### Route ID: 212393
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
##### Route ID: 212394
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`smartgrid`)
- **Parameter:** $grid -> @data_client

---
##### Route ID: 212395
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @set[0,0]  ()`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 
   @NO := CAST(@NO + 1 AS UNSIGNED) AS No,
    ms.division AS Division,
    ms.unit_name AS Unit,
    ms.position AS Position,
    ms.branch AS Branch,
    UPPER(ms.employee_type) AS Employee_Type
FROM master_salary ms
LEFT JOIN users u ON u.id = ms.id_user
LEFT JOIN master_upliner mup ON u.nik = mup.nik,
    (SELECT @NO := 0) AS nomor
WHERE ms.division = ? 
    AND (ms.approval IN ('REQUEST', 'APPROVED', 'PROCESSED')) @?
GROUP BY ms.id
```

---
##### Route ID: 212396
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @set[0,0] <> ()`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 
   @NO := CAST(@NO + 1 AS UNSIGNED) AS "No",
    ms.division AS "Division",
    ms.unit_name AS "Unit",
    ms.position AS "Position",
    ms.branch AS "Branch",
    UPPER(ms.employee_type) AS "Employee Type"
FROM 
    master_salary ms 
    LEFT JOIN users u ON u.id = ms.id_user,
    (SELECT @NO := 0) AS nomor 
WHERE 
 ms.id IN @? 
ORDER BY 
    ms.division,
    ms.unit_name,
    ms.position,
    ms.branch,
    ms.employee_type ASC
```

---
##### Route ID: 212397
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
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$btn_uncheck_all"
}
```

---
##### Route ID: 212398
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Staff`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT DISTINCT 
ms.id,
ms.division AS "Division",
ms.unit_name AS "Unit",
ms.position AS "Position",
ms.branch AS "Branch",
ms.employee_type AS "Employee Type",
DATE_FORMAT(ms.created_date,'%d/%m/%Y') AS "Created Date",
DATE_FORMAT(ms.update_date,'%d/%m/%Y') AS "Update Date",
ms.created_by AS "Created By",
ms.approval AS "Status"
FROM master_salary ms 
LEFT JOIN users u ON u.id=ms.id_user
LEFT JOIN master_pic mp ON mp.master_salary_id = ms.id 
WHERE  ( (ms.id_user = ? AND ms.approval = "REQUEST") 
		OR (mp.`user` = ?  AND ms.approval = "APPROVED") 
		OR (ms.pic = ? AND ms.approval = "APPROVED"))   
		AND (ms.approval != "DONE" ) @?
ORDER BY ms.created_date DESC;
```

---
##### Route ID: 225589
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  SPV`

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
DATE_FORMAT(ms.created_date,'%d/%m/%Y') AS "Created Date",
DATE_FORMAT(ms.update_date,'%d/%m/%Y') AS "Update Date",
ms.created_by AS "Created By",
#Case When ms.approval="REQUEST" then CONCAT('<Button style="border-radius: 8px;background-color: #FFD700; color:black" disabled>', approval, '</button>') else "APPROVED" end AS "Status"
ms.approval AS "Status",
IF(ms.approval='Request',ms.keterangan,'') AS Keterangan
FROM master_salary ms 

WHERE 
(ms.approval = "REQUEST" or ms.approval = "APPROVED" or ms.approval = "PROCESSED") @?
ORDER BY ms.created_date DESC
```

---
#### Komponen: `btn_add` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212404
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Form Baru (`add_client_v2`)

---
##### Route ID: 212405
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Pilihan Input Data`

---
#### Komponen: `btn_proses` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212422
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Process Data?`

---
#### Komponen: `btn_export` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212423
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$load"
}
```

---
##### Route ID: 212424
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Staff`

**Aksi (Action):**
- **Tipe Eksekusi:** `ReportAction` (`export`)
- **Detail:**
```json
{
  "param3": "",
  "param2": "@nama_file[0,0]",
  "param1": "@export_data_client"
}
```

---
##### Route ID: 212425
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cbx_divisionstaff  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Pilih division terlebih dahulu`

---
##### Route ID: 212426
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
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@array(arraydb)`

---
##### Route ID: 212427
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  SPV`

**Aksi (Action):**
- **Tipe Eksekusi:** `ReportAction` (`export`)
- **Detail:**
```json
{
  "param3": "",
  "param2": "@nama_file[0,0]",
  "param1": "@export_data_client_spv"
}
```

---
##### Route ID: 212428
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Manajer`

**Aksi (Action):**
- **Tipe Eksekusi:** `ReportAction` (`export`)
- **Detail:**
```json
{
  "param3": "",
  "param2": "@nama_file[0,0]",
  "param1": "@export_data_client_manajer"
}
```

---
##### Route ID: 212429
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 
CONCAT('Rekap master client_',
DATE_FORMAT(NOW(), '%Y-%m-%d_%H-%i-%s'),'_',REPLACE(INITCAP(?), ' ', ''),'.xls') 
AS file_name
```

---
##### Route ID: 224026
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@insert_log_export",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"0\",\"sqlwhere\":\"\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"log_exports\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"created_at\":\"@+NOW()\",\"export_filter\":\"@log_export[0,0]\",\"status\":\"@log_export[0,2]\",\"export_type\":\"EXCEL\",\"username\":\"@+SESSION-FULL_NAME\",\"menu_name\":\"Master Client\",\"file_name\":\"@nama_file[0,0]\",\"export_name\":\"@log_export[0,1]\",\"user_id\":\"@+SESSION-ID\"},\"callz\":\"\",\"dbmode\":\"insert\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 224027
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT JSON_OBJECT('division', ?,"unit_name",?,"position",?,"branch",?,"Status",?, 'searchKeyword', ?,"User Login",?) ,
concat("Export Report Master Client By ",?),
case
	when ?  = "SPV" then 
		case 
			when ? = "" then "SUCCESS"
			when ? <> "" then "ERROR"
		end
	when ? = "staff" then
		case 
			when ? = "" then "SUCCESS"
			when ? <> "" then "ERROR"
		end
	when ? = "Manajer" then
		case 
			when ? = "" then "SUCCESS"
			when ? <> "" then "ERROR"
		end
end
```

---
#### Komponen: `btn_request` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212430
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Are You Sure?`

---
#### Komponen: `load_visible` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212431
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  SPV`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_proses -> true

---
##### Route ID: 212432
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Staff`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_request -> true

---
##### Route ID: 212433
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  SPV`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $cbx_division -> true

---
##### Route ID: 212434
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Staff`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $cbx_divisionstaff -> true

---
##### Route ID: 212435
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
##### Route ID: 212436
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
##### Route ID: 212437
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
##### Route ID: 212438
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
##### Route ID: 212439
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
##### Route ID: 212440
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
##### Route ID: 212441
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
- **Parameter:** $btn_check_all -> true

---
##### Route ID: 212442
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
- **Parameter:** $btn_uncheck_all -> true

---
##### Route ID: 212443
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
##### Route ID: 212444
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $cbx_status -> true

---
##### Route ID: 212445
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_add -> true

---
##### Route ID: 212446
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
- **Parameter:** $btn_upload -> true

---
##### Route ID: 212447
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_update -> true

---
##### Route ID: 212448
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Manajer`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 212449
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
- **Parameter:** $btn_approve -> false

---
#### Komponen: `load_combo` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212450
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT ms.division FROM master_salary ms
LEFT JOIN users u ON u.id=ms.id_user
LEFT JOIN master_upliner mup on u.nik = mup.nik
WHERE (  
mup.nama_upliner = ? or
u.leader = ?) AND
(ms.approval LIKE "REQUEST%" or ms.approval = "APPROVED" or ms.approval = "PROCESSED")
GROUP BY ms.division ORDER BY ms.division ASC
```

---
##### Route ID: 212451
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT position FROM master_salary WHERE division = ? AND unit_name = ?
GROUP BY position ORDER BY position ASC
```

---
##### Route ID: 212452
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbx_position -> @position

---
##### Route ID: 212453
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT unit_name FROM master_salary
WHERE division = ? GROUP BY unit_name ORDER BY unit_name ASC
```

---
##### Route ID: 212454
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbx_unit -> @unit_name

---
##### Route ID: 212455
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbx_division -> @division

---
##### Route ID: 212456
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT branch FROM master_salary WHERE division = ? AND unit_name = ?
AND position = ? GROUP BY branch ORDER BY branch ASC
```

---
##### Route ID: 212457
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbx_branch -> @branch

---
##### Route ID: 212458
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  SPV`

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)

---
##### Route ID: 212459
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 212460
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT approval FROM master_salary WHERE approval IS NOT NULL GROUP BY approval
```

---
##### Route ID: 212461
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbx_status -> @status

---
##### Route ID: 212462
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT kode,komponen_upah FROM master_upah_tk
```

---
##### Route ID: 212463
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $komponen_upah -> @upah_tk

---
#### Komponen: `load_combo2` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212469
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 
DISTINCT ms.division AS "Division"
FROM master_salary ms 
LEFT JOIN users u ON u.id=ms.id_user
LEFT JOIN master_pic mp ON mp.master_salary_id = ms.id 
WHERE  ( (ms.id_user = ? AND (ms.approval = "REQUEST" OR ms.approval="PROCESSED")) 
        OR (mp.`user` = ?  AND (ms.approval = "APPROVED" OR ms.approval="PROCESSED")) 
        OR (ms.pic = ? AND (ms.approval = "APPROVED" OR ms.approval="PROCESSED")))   
        AND (ms.approval != "DONE" )
ORDER BY ms.division ASC;
```

---
##### Route ID: 212470
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbx_divisionstaff -> @division1

---
##### Route ID: 212471
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Staff`

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)

---
##### Route ID: 212472
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT unit_name FROM master_salary
WHERE division = ? GROUP BY unit_name ORDER BY unit_name ASC
```

---
##### Route ID: 212473
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbx_unitstaff -> @unit_name

---
##### Route ID: 212474
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT position FROM master_salary WHERE division = ? AND unit_name = ?
GROUP BY position ORDER BY position ASC
```

---
##### Route ID: 212475
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbx_positionstaff -> @position

---
##### Route ID: 212476
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT branch FROM master_salary WHERE division = ? AND unit_name = ?
AND position = ? GROUP BY branch ORDER BY branch ASC
```

---
##### Route ID: 212477
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbx_branchstaff -> @branch

---
#### Komponen: `coba` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212486
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
##### Route ID: 212487
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** @datagrid2 -> $grid(nset)

---
##### Route ID: 212488
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@search`

---
#### Komponen: `load_date` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212490
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $periode_start -> @+NOW (fdateview)

---
##### Route ID: 212491
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
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $periode_end -> @+NOW (fdateview)

---
#### Komponen: `btn_check_all` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212492
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** @dt -> $grid (nset)

---
##### Route ID: 212493
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
##### Route ID: 212494
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
##### Route ID: 212495
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
##### Route ID: 212496
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
#### Komponen: `btn_uncheck_all` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212497
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`smartgrid`)
- **Parameter:** $grid -> @data_client

---
##### Route ID: 212498
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
#### Komponen: `btn_approve` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212499
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)

---
##### Route ID: 212500
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT * FROM master_salary WHERE upah_tk is null AND bpjs_kesehatan!=0 AND bpjs_ketenagakerjaan!=0 AND id IN @?
```

---
##### Route ID: 212501
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_upah_tk[0,0] <> `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Terdapat data yang komponen upah BPJS TK nya belum diinput`

---
##### Route ID: 212502
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** @array -> $grid (nset)

---
##### Route ID: 212503
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** @array -> @array [selected]

---
##### Route ID: 212504
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 212505
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_methode_pajak[0,0] <> `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Terdapat data yang methode pajak nya belum diinput`

---
##### Route ID: 212506
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT * FROM master_salary 
WHERE methode_pajak !='Gross' AND methode_pajak !='Net'
AND id IN @?
```

---
##### Route ID: 212507
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @keterangan[0,0]  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Apakah Anda yakin ingin approval data tersebut ?`

---
##### Route ID: 212508
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT keterangan FROM master_salary WHERE id IN @?
```

---
##### Route ID: 212509
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @keterangan[0,0] <> `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Approval data ini berpengaruh pada data employee yang berada dibuket proses payroll, apakah Anda Yakin ingin approval ?`

---
#### Komponen: `btn_upload` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212511
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Form Baru (`upload_client_v2`)

---
#### Komponen: `btn_update_komponen_upah_tk` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212512
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Are You Sure ?`

---
#### Komponen: `btn_update` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212513
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @id_array <> []`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Form Baru (`upload_update_client_v4`)

---
##### Route ID: 212514
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** @array -> $grid (nset)

---
##### Route ID: 212515
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** @id_array -> @array[selected]

---
##### Route ID: 212516
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
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@id_array`

---
##### Route ID: 212517
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @id_array  []`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Pilih data client terlebih dahulu`

---
#### Komponen: `btn_log_history` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212522
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Form Baru (`log_history_master_client`)

---
---

## FORM: `add_client_v4`

### 1. Validasi & Mandatori Field (Input Components)
- _Tidak ada field mandatori yang didefinisikan secara eksplisit di komponen._

### 2. Business Logic, Queries & Actions
#### Komponen: `load_combo` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 211522
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT division FROM employee_update_data WHERE division!='' 
  AND status_employee NOT IN ("BATAL JOIN","PENDING","INACTIVE") 
  AND ((resign_date is Null OR YEAR(resign_Date) >= YEAR(CURDATE()) - 2) 
  OR YEAR(contract_end_date) BETWEEN 
	YEAR(CURDATE()) - 1 AND YEAR(CURDATE()))
 AND employee_type!='PKWTT'
 AND employee_type IS NOT NULL
 AND nik IS NOT NULL
GROUP BY division ORDER BY division
```

---
##### Route ID: 211523
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $divisi -> @division

---
##### Route ID: 211524
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT unit_name FROM employee_update_data WHERE division = ?
  AND status_employee NOT IN ("BATAL JOIN","PENDING","INACTIVE") 
  AND ((resign_date is Null OR YEAR(resign_Date) >= YEAR(CURDATE()) - 2) 
  OR YEAR(contract_end_date) BETWEEN 
	YEAR(CURDATE()) - 1 AND YEAR(CURDATE()))
 AND employee_type!='PKWTT'
 AND employee_type IS NOT NULL
 AND nik IS NOT NULL
GROUP BY unit_name ORDER BY unit_name ASC
```

---
##### Route ID: 211525
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $unit -> @unit_name

---
##### Route ID: 211526
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT position FROM employee_update_data 
WHERE division = ? AND unit_name = ? 
  AND status_employee NOT IN ("BATAL JOIN","PENDING","INACTIVE") 
  AND ((resign_date is Null OR YEAR(resign_Date) >= YEAR(CURDATE()) - 2) 
  OR YEAR(contract_end_date) BETWEEN 
	YEAR(CURDATE()) - 1 AND YEAR(CURDATE()))
 AND employee_type!='PKWTT'
 AND employee_type IS NOT NULL
 AND nik IS NOT NULL
GROUP BY position ORDER BY position ASC
```

---
##### Route ID: 211527
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $posisi -> @position

---
##### Route ID: 211528
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT employee_type FROM employee_update_data 
WHERE division = ? AND unit_name = ? AND position = ? 
  AND status_employee NOT IN ("BATAL JOIN","PENDING","INACTIVE") 
  AND ((resign_date is Null OR YEAR(resign_Date) >= YEAR(CURDATE()) - 2) 
  OR YEAR(contract_end_date) BETWEEN 
	YEAR(CURDATE()) - 1 AND YEAR(CURDATE()))
 AND employee_type!='PKWTT'
 AND employee_type IS NOT NULL
 AND nik IS NOT NULL
GROUP BY employee_type ORDER BY employee_type ASC
```

---
##### Route ID: 211529
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $employee_type -> @employee_type

---
##### Route ID: 211530
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
SELECT branch FROM employee_update_data
WHERE division = ? AND unit_name = ? AND position = ? AND employee_type = ?
  AND status_employee NOT IN ("BATAL JOIN","PENDING","INACTIVE") 
  AND ((resign_date is Null OR YEAR(resign_Date) >= YEAR(CURDATE()) - 2) 
  OR YEAR(contract_end_date) BETWEEN 
	YEAR(CURDATE()) - 1 AND YEAR(CURDATE()))
 AND employee_type!='PKWTT'
 AND employee_type IS NOT NULL
 AND nik IS NOT NULL
GROUP BY branch ORDER BY branch ASC
```

---
##### Route ID: 211531
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $branch -> @branch

---
##### Route ID: 211532
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 211533
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT kode,komponen_upah FROM master_upah_tk
```

---
##### Route ID: 211534
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT upah_tk FROM master_salary WHERE id = ?
```

---
##### Route ID: 211535
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT kode,komponen_upah FROM master_upah_tk 
WHERE kode in @?
```

---
##### Route ID: 211536
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
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $komponen_upah -> @upah_tk(arrayselected)

---
##### Route ID: 211537
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
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@upah_tk[0,0]`

---
##### Route ID: 211538
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT type FROM master_works_day WHERE type!="not assigned" GROUP BY type
```

---
##### Route ID: 211539
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $work_day -> @work_day

---
##### Route ID: 211540
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT id,salary_type  FROM master_salary_type GROUP BY salary_type
LIMIT 3
```

---
##### Route ID: 211541
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $salary_type -> @salary_type

---
##### Route ID: 211542
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT field_deskripsi FROM master_allowance WHERE is_base_salary!=1
```

---
##### Route ID: 211543
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 211544
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cb_tunjangan_tetap -> @tunjangan

---
##### Route ID: 211545
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cb_tunjangan_tidak_tetap -> @tunjangan

---
##### Route ID: 211546
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $komponen_upah -> @upah_tk

---
##### Route ID: 211547
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $komponen_lembur -> @upah_tk

---
#### Komponen: `save` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 211548
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $divisi  `

**Aksi (Action):**
- **Tipe Eksekusi:** `StyleAction` (`style`)
- **Detail:**
```json
{
  "param2": "n-list-default:--Pilih--; color: red;",
  "param1": "$divisi"
}
```

---
##### Route ID: 211549
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $unit  `

**Aksi (Action):**
- **Tipe Eksekusi:** `StyleAction` (`style`)
- **Detail:**
```json
{
  "param2": "n-list-default:--Pilih--; color: red;",
  "param1": "$unit"
}
```

---
##### Route ID: 211550
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $branch  `

**Aksi (Action):**
- **Tipe Eksekusi:** `StyleAction` (`style`)
- **Detail:**
```json
{
  "param2": "n-list-default:--Pilih--; color: red;",
  "param1": "$branch"
}
```

---
##### Route ID: 211551
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $employee_type  `

**Aksi (Action):**
- **Tipe Eksekusi:** `StyleAction` (`style`)
- **Detail:**
```json
{
  "param2": "n-list-default:--Pilih--; color: red;",
  "param1": "$employee_type"
}
```

---
##### Route ID: 211552
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $salary_type  `

**Aksi (Action):**
- **Tipe Eksekusi:** `StyleAction` (`style`)
- **Detail:**
```json
{
  "param2": "n-list-default:--Pilih--; color: red;",
  "param1": "$salary_type"
}
```

---
##### Route ID: 211553
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $metode_pajak  `

**Aksi (Action):**
- **Tipe Eksekusi:** `StyleAction` (`style`)
- **Detail:**
```json
{
  "param2": "n-list-default:--Pilih--; width:40mm; color: red;",
  "param1": "$metode_pajak"
}
```

---
##### Route ID: 211554
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $komponen_project  `

**Aksi (Action):**
- **Tipe Eksekusi:** `StyleAction` (`style`)
- **Detail:**
```json
{
  "param2": "n-list-default:--Pilih--; width:40mm; color: red;",
  "param1": "$komponen_project"
}
```

---
##### Route ID: 211555
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_persen_bpjs_kesehatan <> []`

**Aksi (Action):**
- **Tipe Eksekusi:** `StyleAction` (`style`)
- **Detail:**
```json
{
  "param2": "n-list-default:--Pilih--; width:40mm; color: red;",
  "param1": "$bpjs_tk_type"
}
```

---
##### Route ID: 211556
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $asuransi_kesehatan <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `StyleAction` (`style`)
- **Detail:**
```json
{
  "param2": "n-list-default:--Pilih--; width:40mm; color: red;",
  "param1": "$asuransi"
}
```

---
##### Route ID: 211557
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $bpjs_tk_type <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)

---
##### Route ID: 211558
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $asuransi_kecelakaan <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `StyleAction` (`style`)
- **Detail:**
```json
{
  "param2": "n-list-default:--Pilih--; width:40mm; color: red;",
  "param1": "$asuransi"
}
```

---
##### Route ID: 211559
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $work_day  `

**Aksi (Action):**
- **Tipe Eksekusi:** `StyleAction` (`style`)
- **Detail:**
```json
{
  "param2": "n-list-default:--Pilih--; width:40mm; color: red;",
  "param1": "$work_day"
}
```

---
##### Route ID: 211560
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $posisi  `

**Aksi (Action):**
- **Tipe Eksekusi:** `StyleAction` (`style`)
- **Detail:**
```json
{
  "param2": "n-list-default:--Pilih--; color: red;",
  "param1": "$posisi"
}
```

---
##### Route ID: 211561
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Apakah Anda Yakin ingin menambahkan data ?`

---
##### Route ID: 211562
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `$work_day`

---
##### Route ID: 211563
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT CASE WHEN
(? = '' OR 
? = '' OR 
? = '' OR 
? = '' OR 
? = '' OR 
? = '' OR 
? = '' OR 
? = '' OR 
? = '' OR 
(? != '' AND ? = '') OR
(? != 0 AND ? = '') OR
(? != '' AND ? = 0) OR
(? != '' AND ? = 0) OR
(? = 'Yes' AND ? = '') OR
(? !='' AND ? != 'None' AND ?= '' ) OR
(? !='' AND ? !='None' AND ? ='[]') OR 
(? !='' AND ? !='None' AND ?!='' AND ? ='[]'))
 THEN 'Wajib isi' END
```

---
##### Route ID: 211564
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
##### Route ID: 211565
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
##### Route ID: 211566
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
##### Route ID: 211567
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
##### Route ID: 211568
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
##### Route ID: 211569
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
##### Route ID: 211570
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @validasi_mandatory[0,0] <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 211571
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_bpjs_ketenagakerjaan  1`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Komponen Upah harus dipilih terlebih dahulu`

---
##### Route ID: 211572
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $bpjs_tk_type <> `

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 211573
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @bpjs_tk  true`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Bpjs Ketenagakerjaan harus dipilih`

---
##### Route ID: 211574
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 211575
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 211576
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT
	concat('Division 	: ',?, 
', Unit Name : ', ?, 
', Position 	: ', ?,
', Branch  	: ', ?, 
', Employee Type 	: ', ?,
' Already Exist, Created by ',created_by) 
FROM
	master_salary
WHERE division = ? 
	AND unit_name = ?
	AND position = ? 
	AND branch = ? 
	AND employee_type = ?
LIMIT 1
```

---
##### Route ID: 211577
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_data_already_exist[0,0] <> `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@cek_data_already_exist[0,0]`

---
##### Route ID: 211578
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
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `$cb_bpjs_ketenagakerjaan`

---
##### Route ID: 211579
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_bpjs_ketenagakerjaan <> 0`

**Aksi (Action):**
- **Tipe Eksekusi:** `StyleAction` (`style`)
- **Detail:**
```json
{
  "param2": "n-list-default:--Pilih--; width:40mm; color: red;",
  "param1": "$bpjs_tk_type"
}
```

---
##### Route ID: 211580
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Persen BPJS Kesehatan harus diisi`

---
##### Route ID: 211581
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
#### Komponen: `load_grid` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 211611
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 211612
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 1,'Tunjangan Supervisor' AS Tunjangan, format_uang(tunjangan_supervisor) AS Value 
FROM master_salary 
WHERE 'Tunjangan Supervisor' IN @? AND id = ?
UNION ALL
SELECT 2,'Tunjangan Jabatan' AS Tunjangan, format_uang(tunjangan_jabatan)  AS Value 
FROM master_salary 
WHERE 'Tunjangan Jabatan' IN @? AND id = ?
UNION ALL
SELECT 3,'Skill Allowance' AS Tunjangan, format_uang(skill_allowance)  AS Value 
FROM master_salary 
WHERE 'Skill Allowance' IN @? AND id = ?
UNION ALL
SELECT 4,'Grading Allowance' AS Tunjangan, format_uang(grading_allowance)  AS Value 
FROM master_salary 
WHERE 'Grading Allowance' IN @? AND id = ?
UNION ALL
SELECT 5,'Montly Allowance' AS Tunjangan, format_uang(montly_allowance)  AS Value 
FROM master_salary 
WHERE 'Montly Allowance' IN @? AND id = ?
UNION ALL
SELECT 6,'Performance Allowance' AS Tunjangan, format_uang(performance_allowance)  AS Value 
FROM master_salary 
WHERE 'Performance Allowance' IN @? AND id = ?
UNION ALL
SELECT 7,'Position Allowance' AS Tunjangan, format_uang(position_allowance)  AS Value 
FROM master_salary 
WHERE 'Position Allowance' IN @? AND id = ?
UNION ALL
SELECT 8,'BBM' AS Tunjangan, format_uang(bbm)  AS Value 
FROM master_salary 
WHERE 'BBM' IN @? AND id = ?
UNION ALL
SELECT 9,'Komunikasi' AS Tunjangan, format_uang(komunikasi)  AS Value 
FROM master_salary
WHERE 'Komunikasi' IN @? AND id = ?
UNION ALL
SELECT 10,'Transportasi' AS Tunjangan, format_uang(transportasi)  AS Value 
FROM master_salary
WHERE 'Transportasi' IN @? AND id = ?
UNION ALL
SELECT 11,'Productivity' AS Tunjangan, format_uang(productivity)  AS Value 
FROM master_salary
WHERE 'Productivity' IN @? AND id = ?
UNION ALL
SELECT 12,'Khusus' AS Tunjangan, format_uang(khusus)  AS Value 
FROM master_salary
WHERE 'Khusus' IN @? AND id = ?
UNION ALL
SELECT 13,'Sewa Laptop' AS Tunjangan, format_uang(sewa_laptop)  AS Value 
FROM master_salary
WHERE 'Sewa Laptop' IN @? AND id = ?
UNION ALL
SELECT 14,'Tunjangan Makan' AS Tunjangan, format_uang(makan_meal)  AS Value 
FROM master_salary
WHERE 'Makan Meal' IN @? AND id = ?
UNION ALL
SELECT 15,'Career Allowance' AS Tunjangan, format_uang(career_allowence)  AS Value 
FROM master_salary
WHERE 'Career Allowance' IN @? AND id = ?
UNION ALL
SELECT 16,'Tunjangan Premium' AS Tunjangan, format_uang(tunjangan_premium)  AS Value 
FROM master_salary
WHERE 'Tunjangan Premium' IN @? AND id = ?
UNION ALL
SELECT 17,'Kinerja' AS Tunjangan, format_uang(kinerja)  AS Value 
FROM master_salary
WHERE 'Kinerja' IN @? AND id = ?
UNION ALL
SELECT 18,'Tunjangan Parkir' AS Tunjangan, format_uang(tunjangan_parkir)  AS Value 
FROM master_salary
WHERE 'Tunjangan Parkir' IN @? AND id = ?
UNION ALL
SELECT 19,'Tunjangan Kehadiran' AS Tunjangan, format_uang(tj_kehadiran)  AS Value 
FROM master_salary
WHERE 'Tunjangan Kehadiran' IN @? AND id = ?
UNION ALL
SELECT 20,'Tunjangan Tugas Harian' AS Tunjangan, format_uang(tj_tugas_harian)  AS Value 
FROM master_salary
WHERE 'Tunjangan Tugas Harian' IN @? AND id = ?
UNION ALL
SELECT 21,'Tunjangan Operasional' AS Tunjangan, format_uang(tunjangan_operasional)  AS Value 
FROM master_salary
WHERE 'Tunjangan Operasional' IN @? AND id = ?UNION ALL
SELECT 22,'Tunjangan Sewa dan Service Motor' AS Tunjangan, format_uang(tunjangan_sewa_service_motor)  AS Value 
FROM master_salary
WHERE 'Tunjangan Sewa dan Service Motor' IN @? AND id = ?
UNION ALL
SELECT 23,'Tunjangan Akomodasi' AS Tunjangan, format_uang(tunjangan_akomodasi)  AS Value 
FROM master_salary
WHERE 'Tunjangan Akomodasi' IN @? AND id = ?
UNION ALL
SELECT 24,'Tunjangan Project' AS Tunjangan, format_uang(tunjangan_project)  AS Value 
FROM master_salary
WHERE 'Tunjangan Project' IN @? AND id = ?
```

---
##### Route ID: 211613
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
- **Tipe Eksekusi:** Set Data UI (`smartgrid`)
- **Parameter:** $table -> @tunjangan_tetap

---
##### Route ID: 211614
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 1,'Tunjangan Supervisor' AS Tunjangan, format_uang(tunjangan_supervisor) AS Value 
FROM master_salary 
WHERE 'Tunjangan Supervisor' IN @? AND id = ?
UNION ALL
SELECT 2,'Tunjangan Jabatan' AS Tunjangan, format_uang(tunjangan_jabatan)  AS Value 
FROM master_salary 
WHERE 'Tunjangan Jabatan' IN @? AND id = ?
UNION ALL
SELECT 3,'Skill Allowance' AS Tunjangan, format_uang(skill_allowance)  AS Value 
FROM master_salary 
WHERE 'Skill Allowance' IN @? AND id = ?
UNION ALL
SELECT 4,'Grading Allowance' AS Tunjangan, format_uang(grading_allowance)  AS Value 
FROM master_salary 
WHERE 'Grading Allowance' IN @? AND id = ?
UNION ALL
SELECT 5,'Montly Allowance' AS Tunjangan, format_uang(montly_allowance)  AS Value 
FROM master_salary 
WHERE 'Montly Allowance' IN @? AND id = ?
UNION ALL
SELECT 6,'Performance Allowance' AS Tunjangan, format_uang(performance_allowance)  AS Value 
FROM master_salary 
WHERE 'Performance Allowance' IN @? AND id = ?
UNION ALL
SELECT 7,'Position Allowance' AS Tunjangan, format_uang(position_allowance)  AS Value 
FROM master_salary 
WHERE 'Position Allowance' IN @? AND id = ?
UNION ALL
SELECT 8,'BBM' AS Tunjangan, format_uang(bbm)  AS Value 
FROM master_salary 
WHERE 'BBM' IN @? AND id = ?
UNION ALL
SELECT 9,'Komunikasi' AS Tunjangan, format_uang(komunikasi)  AS Value 
FROM master_salary
WHERE 'Komunikasi' IN @? AND id = ?
UNION ALL
SELECT 10,'Transportasi' AS Tunjangan, format_uang(transportasi)  AS Value 
FROM master_salary
WHERE 'Transportasi' IN @? AND id = ?
UNION ALL
SELECT 11,'Productivity' AS Tunjangan, format_uang(productivity)  AS Value 
FROM master_salary
WHERE 'Productivity' IN @? AND id = ?
UNION ALL
SELECT 12,'Khusus' AS Tunjangan, format_uang(khusus)  AS Value 
FROM master_salary
WHERE 'Khusus' IN @? AND id = ?
UNION ALL
SELECT 13,'Sewa Laptop' AS Tunjangan, format_uang(sewa_laptop)  AS Value 
FROM master_salary
WHERE 'Sewa Laptop' IN @? AND id = ?
UNION ALL
SELECT 14,'Tunjangan Makan' AS Tunjangan, format_uang(makan_meal)  AS Value 
FROM master_salary
WHERE 'Makan Meal' IN @? AND id = ?
UNION ALL
SELECT 15,'Career Allowance' AS Tunjangan, format_uang(career_allowence)  AS Value 
FROM master_salary
WHERE 'Career Allowance' IN @? AND id = ?
UNION ALL
SELECT 16,'Tunjangan Premium' AS Tunjangan, format_uang(tunjangan_premium)  AS Value 
FROM master_salary
WHERE 'Tunjangan Premium' IN @? AND id = ?
UNION ALL
SELECT 17,'Kinerja' AS Tunjangan, format_uang(kinerja)  AS Value 
FROM master_salary
WHERE 'Kinerja' IN @? AND id = ?
UNION ALL
SELECT 18,'Tunjangan Parkir' AS Tunjangan, format_uang(tunjangan_parkir)  AS Value 
FROM master_salary
WHERE 'Tunjangan Parkir' IN @? AND id = ?
UNION ALL
SELECT 19,'Tunjangan Kehadiran' AS Tunjangan, format_uang(tj_kehadiran)  AS Value 
FROM master_salary
WHERE 'Tunjangan Kehadiran' IN @? AND id = ?
UNION ALL
SELECT 20,'Tunjangan Tugas Harian' AS Tunjangan, format_uang(tj_tugas_harian)  AS Value 
FROM master_salary
WHERE 'Tunjangan Tugas Harian' IN @? AND id = ?
UNION ALL
SELECT 21,'Tunjangan Operasional' AS Tunjangan, format_uang(tunjangan_operasional)  AS Value 
FROM master_salary
WHERE 'Tunjangan Operasional' IN @? AND id = ?
UNION ALL
SELECT 22,'Tunjangan Sewa dan Service Motor' AS Tunjangan, format_uang(tunjangan_sewa_service_motor)  AS Value 
FROM master_salary
WHERE 'Tunjangan Sewa dan Service Motor' IN @? AND id = ?
UNION ALL
SELECT 23,'Tunjangan Akomodasi' AS Tunjangan, format_uang(tunjangan_akomodasi)  AS Value 
FROM master_salary
WHERE 'Tunjangan Akomodasi' IN @? AND id = ?
UNION ALL
SELECT 24,'Tunjangan Project' AS Tunjangan, format_uang(tunjangan_project)  AS Value 
FROM master_salary
WHERE 'Tunjangan Project' IN @? AND id = ?
```

---
##### Route ID: 211615
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
- **Tipe Eksekusi:** Set Data UI (`smartgrid`)
- **Parameter:** $grid_tunjangan_tidak_tetap -> @tunjangan_tidak_tetap

---
##### Route ID: 211616
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 
CONCAT ('Rp', FORMAT (insentif_non_upah,0)) AS "Insentif Non Upah",
CONCAT ('Rp', FORMAT (lembur_non_upah,0)) AS "Lembur Non Upah",
CONCAT ('Rp', FORMAT (productivity_non_upah,0)) AS "Productivity Non Upah",
CONCAT ('Rp', FORMAT (tj_kesehatan_non_upah,0)) AS "Tunjangan Kesehatan Non Upah",
CONCAT ('Rp', FORMAT (perdiem_non_upah,0)) AS "Perdiem Non Upah",
CONCAT ('Rp', FORMAT (performance_pay_non_upah,0)) AS "Performance Non Upah",
CONCAT ('Rp', FORMAT (leader_board_non_upah,0)) AS "Leader Non Upah",
CONCAT ('Rp', FORMAT (monthly_comission_non_upah,0)) AS "Monthly Commission Non Upah",
CONCAT ('Rp', FORMAT (sop_reward_non_upah,0)) AS "Sop reward Non Upah",
CONCAT ('Rp', FORMAT (kompensasi_hln_non_upah,0)) AS "Kompensansi HLN Non Upah",
CONCAT ('Rp', FORMAT (motorcycle_collector_non_upah,0)) AS "Motorcycle Collector Non Upah",
CONCAT ('Rp', FORMAT (birthday_gift__non_upah,0)) AS "Birthday Gift Non Upah",
CONCAT ('Rp', FORMAT (shift_allowance_non_upah,0)) AS "Shift Allowance Non Upah",
CONCAT ('Rp', FORMAT (bonus_non_upah,0)) AS "Bonus Non Upah",
CONCAT ('Rp', FORMAT (thr,0)) AS "THR",
CONCAT ('Rp', FORMAT (kompensasi,0)) AS "Kompensasi"

FROM master_salary WHERE id = ?
```

---
##### Route ID: 211617
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`smartgrid`)
- **Parameter:** $grid_non_upah -> @non_upah

---
##### Route ID: 211618
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT id,
 CONCAT('Rp', FORMAT(IF((rapelan=0 OR rapelan is Null),0,rapelan), 0)) AS "Rapelan",
 CONCAT('Rp', FORMAT(IF((tj_lain=0 OR tj_lain is Null),0,tj_lain), 0)) AS "Tunjangan Lain"
FROM master_salary
WHERE id = ?
```

---
##### Route ID: 211619
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`smartgrid`)
- **Parameter:** $grid_opsional -> @opsional

---
##### Route ID: 211620
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 211621
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
- **Tipe Eksekusi:** Set Data UI (`smartgrid`)
- **Parameter:** $grid_tunjangan_tetap -> @tunjangan_tetap

---
#### Komponen: `visible_tidak_digunakan` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 211622
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)
- **Detail:**
```json
{
  "param2": "red",
  "param1": "Tunjangan"
}
```

---
##### Route ID: 211623
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "1"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_spv -> true

---
##### Route ID: 211624
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_spv -> false

---
##### Route ID: 211625
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "2"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_jabatan -> true

---
##### Route ID: 211626
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_jabatan -> false

---
##### Route ID: 211627
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "3"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $skill_allowance -> true

---
##### Route ID: 211628
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $skill_allowance -> false

---
##### Route ID: 211629
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "4"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $grading_allowance -> true

---
##### Route ID: 211630
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $grading_allowance -> false

---
##### Route ID: 211631
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "5"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $montly_allowance -> true

---
##### Route ID: 211632
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $montly_allowance -> false

---
##### Route ID: 211633
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "6"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $performance_allowance -> true

---
##### Route ID: 211634
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $performance_allowance -> false

---
##### Route ID: 211635
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "7"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $position_allowance -> true

---
##### Route ID: 211636
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $position_allowance -> false

---
##### Route ID: 211637
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "8"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $bbm -> true

---
##### Route ID: 211638
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $bbm -> false

---
##### Route ID: 211639
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "9"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $komunikasi -> true

---
##### Route ID: 211640
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $komunikasi -> false

---
##### Route ID: 211641
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "10"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $transportasi -> true

---
##### Route ID: 211642
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $transportasi -> false

---
##### Route ID: 211643
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "11"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $productivity -> true

---
##### Route ID: 211644
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $productivity -> false

---
##### Route ID: 211645
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "12"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $khusus -> true

---
##### Route ID: 211646
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $khusus -> false

---
##### Route ID: 211647
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "13"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $sewa_laptop -> true

---
##### Route ID: 211648
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $sewa_laptop -> false

---
##### Route ID: 211649
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "14"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $makan_meal -> true

---
##### Route ID: 211650
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $makan_meal -> false

---
##### Route ID: 211651
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "15"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $career_allowance -> true

---
##### Route ID: 211652
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $career_allowance -> false

---
##### Route ID: 211653
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "16"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_premium -> true

---
##### Route ID: 211654
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_premium -> false

---
##### Route ID: 211655
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "17"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $kinerja -> true

---
##### Route ID: 211656
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $kinerja -> false

---
##### Route ID: 211657
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "18"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_parkir -> true

---
##### Route ID: 211658
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_parkir -> false

---
##### Route ID: 211659
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "19"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_hadir -> true

---
##### Route ID: 211660
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_hadir -> false

---
##### Route ID: 211661
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "20"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_tugas_harian -> true

---
##### Route ID: 211662
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_tugas_harian -> false

---
##### Route ID: 211663
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "21"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_operasional -> true

---
##### Route ID: 211664
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_operasional -> false

---
##### Route ID: 211665
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "22"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_sewa_service -> true

---
##### Route ID: 211666
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_sewa_service -> false

---
##### Route ID: 211667
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "23"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_akomodasi -> true

---
##### Route ID: 211668
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_akomodasi -> false

---
##### Route ID: 211669
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain "24"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_project -> true

---
##### Route ID: 211670
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_project -> false

---
#### Komponen: `res` (Tipe: navresult)
Komponen ini memicu aliran logika berikut:

##### Route ID: 211671
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  save data`

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)

---
##### Route ID: 211672
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT IF(?=1,?,0) AS variable,
IF(?=2,?,0) AS Fix,
IF(?=3,?,0) AS Daily
```

---
##### Route ID: 211673
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT MAX(id) FROM master_salary WHERE created_by = ?
```

---
##### Route ID: 211674
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
- **Aksi Raw:** `{
  "result": "@history_client",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"0\",\"sqlwhere\":\"\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"history_salary\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"employee_type\":\"$employee_type\",\"tj_kehadiran\":\"$tj_hadir (int)\",\"id_parent\":\"@tinggi[0,0]\",\"sewa_laptop\":\"$sewa_laptop (int)\",\"grading_allowance\":\"$grading_allowance (int)\",\"branch\":\"$branch\",\"khusus\":\"$khusus (int)\",\"performance_allowance\":\"$performance_allowance (int)\",\"spesial_threatment\":\"$biaya_jasa_training (int)\",\"lembur_non_upah\":\"$lembur (int)\",\"periode_payroll\":\"$periode (trim)\",\"kategori_tunjangan_tetap\":\"$cb_tunjangan_tetap (arraydb)\",\"tunjangan_project\":\"$tj_project (int)\",\"productivity_non_upah\":\"$productivity_non_tetap (int)\",\"tunjangan_jabatan\":\"$tunjangan_jabatan (int)\",\"upah_tk\":\"$komponen_upah(arraydb)\",\"created_date\":\"@+NOW()\",\"bpjs_ketenagakerjaan\":\"$cb_bpjs_ketenagakerjaan\",\"periode_end\":\"$periode_end (fdatedb)\",\"created_by\":\"@+SESSION-FULL_NAME\",\"salary_type_id\":\"$salary_type\",\"workday_type_id\":\"$work_day\",\"jip_type\":\"$jip_type\",\"tj_tugas_harian\":\"$tj_tugas_harian (int)\",\"tunjangan_supervisor\":\"$tunjangan_spv (int)\",\"motorcycle_collector_non_upah\":\"$motorcycle_collector (int)\",\"monthly_comission_non_upah\":\"$monthly_commision (int)\",\"manajemen_fee\":\"$manajemen_fee\",\"shift_allowance_non_upah\":\"$shift_allowance (int)\",\"status_asuransi\":\"$asuransi\",\"periode_start\":\"$periode_start (fdatedb)\",\"montly_allowance\":\"$montly_allowance (int)\",\"perdiem_non_upah\":\"$perdiem (int)\",\"transportasi\":\"$transportasi (int)\",\"thr\":\"$thr (int)\",\"komunikasi\":\"$komunikasi (int)\",\"productivity\":\"$productivity (int)\",\"career_allowence\":\"$career_allowance (int)\",\"allowance\":\"@salary_type[0,3]\",\"tunjangan_operasional\":\"$tj_operasional (int)\",\"bbm\":\"$bbm (int)\",\"skill_allowance\":\"$skill_allowance (int)\",\"position_allowance\":\"$position_allowance (int)\",\"tunjangan_akomodasi\":\"$tj_akomodasi (int)\",\"insentif_non_upah\":\"$insentif (int)\",\"birthday_gift__non_upah\":\"$birthday_gift (int)\",\"methode_pajak\":\"$metode_pajak\",\"status\":\"ADD\",\"makan_meal\":\"$makan_meal (int)\",\"tunjangan_parkir\":\"$tunjangan_parkir (int)\",\"bonus\":\"$bonus (int)\",\"sop_reward_non_upah\":\"$sop_reward (int)\",\"tj_kesehatan_non_upah\":\"$tj_kesehatan (int)\",\"fix\":\"@salary_type[0,1]\",\"kompensasi\":\"$kompensasi (int)\",\"asuransi_kecelakaan\":\"$asuransi_kecelakaan(int)\",\"asuransi_kesehatan\":\"$asuransi_kesehatan(int)\",\"position\":\"$posisi\",\"variable\":\"@salary_type[0,0]\",\"performance_pay_non_upah\":\"$performance_pay (int)\",\"daily\":\"@salary_type[0,2]\",\"leader_board_non_upah\":\"$leaderboard (int)\",\"kategori_tunjangan_tidak_tetap\":\"$cb_tunjangan_tidak_tetap (arraydb)\",\"unit_name\":\"$unit\",\"kompensasi_hln_non_upah\":\"$kompensasi_hln (int)\",\"tunjangan_premium\":\"$tunjangan_premium (int)\",\"tunjangan_tetap\":\"$tunjangan_tetap_periode (arraydb)\",\"tunjangan_sewa_service_motor\":\"$tj_sewa_service (int)\",\"division\":\"$divisi\",\"bonus_non_upah\":\"$bonus_non_upah (int)\",\"kinerja\":\"$kinerja (int)\"},\"callz\":\"\",\"dbmode\":\"insert\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 211675
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `DataAction` (`broadcastglobal`)
- **Detail:**
```json
{
  "param2": "@[]",
  "param1": "pop_up"
}
```

---
##### Route ID: 211676
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
  "param1": "11"
}
```

---
##### Route ID: 211677
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
##### Route ID: 211678
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
##### Route ID: 211679
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $tunjangan_beda_preiode  []`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** @beda_tunjangan -> 0

---
##### Route ID: 211680
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $tunjangan_beda_preiode  ["Tunjangan Beda Periode"]`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** @beda_tunjangan -> 1

---
##### Route ID: 211681
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
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@insert(error)`

---
##### Route ID: 211682
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
INSERT INTO client_categories_allowance(salary_id,allowance_id,type)
SELECT
  ?,
  jt.allowance_id,
  'Tetap'
FROM JSON_TABLE(
  CONCAT(
    '[',
    REPLACE(REPLACE(REPLACE(?, '(', ''), ')', ''), '''', ''),
    ']'
  ),
  '$[*]' COLUMNS (
    allowance_id INT PATH '$'
  )
) jt;
```

---
##### Route ID: 211683
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
INSERT INTO client_categories_allowance(salary_id,allowance_id,type)
SELECT
  ?,
  jt.allowance_id,
  'Tidak Tetap'
FROM JSON_TABLE(
  CONCAT(
    '[',
    REPLACE(REPLACE(REPLACE(?, '(', ''), ')', ''), '''', ''),
    ']'
  ),
  '$[*]' COLUMNS (
    allowance_id INT PATH '$'
  )
) jt;
```

---
##### Route ID: 225370
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
- **Aksi Raw:** `{
  "result": "@insert",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"0\",\"sqlwhere\":\"\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"master_salary\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"employee_type\":\"$employee_type\",\"tj_kehadiran\":\"$tj_hadir(int)\",\"persen_bpjs_kesehatan\":\"$cb_persen_bpjs_kesehatan\",\"sewa_laptop\":\"$sewa_laptop(int)\",\"grading_allowance\":\"$grading_allowance(int)\",\"project_allowance\":\"$project_allowance(int)\",\"branch\":\"$branch\",\"khusus\":\"$tj_khusus(int)\",\"performance_allowance\":\"$performance_allowance(int)\",\"spesial_threatment\":\"$biaya_jasa_training(int)\",\"tj_cuti\":\"$tj_cuti(int)\",\"lembur_non_upah\":\"$lembur(int)\",\"kategori_tunjangan_tetap\":\"$cb_tunjangan_tetap(arrayin)\",\"tunjangan_project\":\"$tj_project(int)\",\"tunjangan_jabatan\":\"$tunjangan_jabatan(int)\",\"upah_tk\":\"$komponen_upah(arraydb)\",\"created_date\":\"@+NOW()\",\"bpjs_ketenagakerjaan\":\"$cb_bpjs_ketenagakerjaan\",\"created_by\":\"@+SESSION-FULL_NAME\",\"salary_type_id\":\"$salary_type\",\"jip_type\":\"$bpjs_tk_type\",\"tj_tugas_harian\":\"$tj_tugas_harian(int)\",\"tunjangan_supervisor\":\"$tunjangan_spv(int)\",\"workday_type\":\"$work_day\",\"tunjangan_beda_periode\":\"@beda_tunjangan\",\"manajemen_fee\":\"$manajemen_fee\",\"monthly_comission_non_upah\":\"$monthly_commision(int)\",\"shift_allowance_non_upah\":\"$shift_allowance(int)\",\"tj_tempat_tinggal\":\"$tj_tempat_tinggal(int)\",\"status_asuransi\":\"$asuransi\",\"montly_allowance\":\"$montly_allowance(int)\",\"tj_bensin_parkir\":\"$tj_bensin_parkir(int)\",\"id_user\":\"@+SESSION-ID\",\"transportasi\":\"$transportasi(int)\",\"thr\":\"$thr(int)\",\"komunikasi\":\"$komunikasi(int)\",\"komponen_lembur\":\"$komponen_lembur(arraydb)\",\"productivity\":\"$productivity(int)\",\"career_allowence\":\"$career_allowance(int)\",\"tunjangan_operasional\":\"$tj_operasional(int)\",\"bbm\":\"$tj_bensin(int)\",\"skill_allowance\":\"$skill_allowance(int)\",\"position_allowance\":\"$position_allowance(int)\",\"tj_surveyor_bengkel\":\"$tj_surveyor_bengkel (int)\",\"tunjangan_akomodasi\":\"$tj_akomodasi(int)\",\"insentif_non_upah\":\"$insentif(int)\",\"methode_pajak\":\"$metode_pajak\",\"approval\":\"REQUEST\",\"makan_meal\":\"$tj_makan(int)\",\"tunjangan_parkir\":\"$tunjangan_parkir(int)\",\"bonus\":\"$bonus(int)\",\"tj_kesehatan_non_upah\":\"$tj_kesehatan(int)\",\"fix\":\"@salary_type[0,1]\",\"tj_masa_kontrak\":\"$tj_masa_kontrak(int)\",\"kompensasi\":\"$kompensasi(int)\",\"asuransi_kecelakaan\":\"$asuransi_kecelakaan(int)\",\"asuransi_kesehatan\":\"$asuransi_kesehatan(int)\",\"tj_service_charge\":\"$tj_service_charge(int)\",\"position\":\"$posisi\",\"variable\":\"@salary_type[0,0]\",\"daily\":\"@salary_type[0,2]\",\"kategori_tunjangan_tidak_tetap\":\"$cb_tunjangan_tidak_tetap(arrayin)\",\"komponen_project\":\"$komponen_project\",\"unit_name\":\"$unit\",\"tj_kerajinan\":\"$tj_kerajinan(int)\",\"tunjangan_premium\":\"$tunjangan_premium(int)\",\"tunjangan_tetap\":\"$tunjangan_tetap_periode(arraydb)\",\"tunjangan_sewa_service_motor\":\"$tj_sewa_service(int)\",\"division\":\"$divisi\",\"kinerja\":\"$tj_kerja(int)\"},\"callz\":\"\",\"dbmode\":\"insert\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 225371
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
INSERT INTO master_salary (
    division,
    unit_name,
    employee_type,
    workday_type,
    position,
    branch,
    salary_type_id,
    tunjangan_supervisor,	
    tunjangan_jabatan,
    skill_allowance,
    grading_allowance,
    montly_allowance,
    performance_allowance,
    position_allowance,
    bbm,
    komunikasi,
    transportasi,
    productivity,
    khusus,
    sewa_laptop,
    makan_meal,
    career_allowence,
    tunjangan_premium,
    kinerja,
    tunjangan_parkir,
    bonus,
    spesial_threatment,
    variable,
    fix,
    daily,
    manajemen_fee,
    insentif_non_upah,
    lembur_non_upah,
    tj_kesehatan_non_upah,
    monthly_comission_non_upah,
    shift_allowance_non_upah,
    thr,
    jip_type,
    created_date,
    created_by,
    approval,
    id_user
)
VALUES (
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?
);
```

---
#### Komponen: `load_visible` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 211684
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)
- **Detail:**
```json
{
  "param2": "red",
  "param1": "Tunjangan"
}
```

---
##### Route ID: 211685
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Supervisor`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_spv -> true

---
##### Route ID: 211686
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_spv -> false

---
##### Route ID: 211687
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Jabatan`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_jabatan -> true

---
##### Route ID: 211688
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_jabatan -> false

---
##### Route ID: 211689
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Skill Allowance`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $skill_allowance -> true

---
##### Route ID: 211690
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $skill_allowance -> false

---
##### Route ID: 211691
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Grading Allowance`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $grading_allowance -> true

---
##### Route ID: 211692
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $grading_allowance -> false

---
##### Route ID: 211693
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Montly Allowance`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $montly_allowance -> true

---
##### Route ID: 211694
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $montly_allowance -> false

---
##### Route ID: 211695
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Performance Allowance`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $performance_allowance -> true

---
##### Route ID: 211696
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $performance_allowance -> false

---
##### Route ID: 211697
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Position Allowance`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $position_allowance -> true

---
##### Route ID: 211698
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $position_allowance -> false

---
##### Route ID: 211699
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Bensin"`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_bensin -> true

---
##### Route ID: 211700
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_bensin -> false

---
##### Route ID: 211701
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Komunikasi`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $komunikasi -> true

---
##### Route ID: 211702
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $komunikasi -> false

---
##### Route ID: 211703
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Transportasi`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $transportasi -> true

---
##### Route ID: 211704
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $transportasi -> false

---
##### Route ID: 211705
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Productivity`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $productivity -> true

---
##### Route ID: 211706
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $productivity -> false

---
##### Route ID: 211707
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Khusus`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_khusus -> true

---
##### Route ID: 211708
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_khusus -> false

---
##### Route ID: 211709
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Sewa Laptop`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $sewa_laptop -> true

---
##### Route ID: 211710
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $sewa_laptop -> false

---
##### Route ID: 211711
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Uang Makan`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_makan -> true

---
##### Route ID: 211712
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_makan -> false

---
##### Route ID: 211713
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Career Allowance`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $career_allowance -> true

---
##### Route ID: 211714
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $career_allowance -> false

---
##### Route ID: 211715
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Premium`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_premium -> true

---
##### Route ID: 211716
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_premium -> false

---
##### Route ID: 211717
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Kerja`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_kerja -> true

---
##### Route ID: 211718
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_kerja -> false

---
##### Route ID: 211719
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Parkir`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_parkir -> true

---
##### Route ID: 211720
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_parkir -> false

---
##### Route ID: 211721
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Kehadiran`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_hadir -> true

---
##### Route ID: 211722
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_hadir -> false

---
##### Route ID: 211723
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Tugas Harian`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_tugas_harian -> true

---
##### Route ID: 211724
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_tugas_harian -> false

---
##### Route ID: 211725
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Operasional`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_operasional -> true

---
##### Route ID: 211726
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_operasional -> false

---
##### Route ID: 211727
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Sewa dan Service Motor`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_sewa_service -> true

---
##### Route ID: 211728
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_sewa_service -> false

---
##### Route ID: 211729
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Akomodasi`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_akomodasi -> true

---
##### Route ID: 211730
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_akomodasi -> false

---
##### Route ID: 211731
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Project`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_project -> true

---
##### Route ID: 211732
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_project -> false

---
##### Route ID: 211733
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Project Allowance`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $project_allowance -> true

---
##### Route ID: 211734
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $project_allowance -> false

---
##### Route ID: 211735
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Cuti`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_cuti -> true

---
##### Route ID: 211736
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_cuti -> false

---
##### Route ID: 211737
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Kerajinan`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_kerajinan -> true

---
##### Route ID: 211738
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_kerajinan -> false

---
##### Route ID: 211739
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Service Charge`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_service_charge -> true

---
##### Route ID: 211740
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_service_charge -> false

---
##### Route ID: 211741
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Surveyor Bengkel`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_surveyor_bengkel -> true

---
##### Route ID: 211742
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_surveyor_bengkel -> false

---
##### Route ID: 211743
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Tempat Tinggal`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_tempat_tinggal -> true

---
##### Route ID: 211744
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_tempat_tinggal -> false

---
##### Route ID: 211745
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `$cb_tunjangan_tetap`

---
##### Route ID: 212042
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 212043
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Masa Kontrak`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_masa_kontrak -> true

---
##### Route ID: 212044
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_masa_kontrak -> false

---
##### Route ID: 224010
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_tunjangan_tetap contain Tunjangan Bensin dan Parkir`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_bensin_parkir -> true

---
##### Route ID: 224012
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_bensin_parkir -> false

---
#### Komponen: `load_combo_tjgn_beda_periode` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 211746
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT field_deskripsi FROM allowance_categories
WHERE field_name IN @?
```

---
##### Route ID: 211747
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $tunjangan_tetap_periode -> @tunjangan_beda_periode

---
---

## FORM: `upload_client_v2`

### 1. Validasi & Mandatori Field (Input Components)
- **`cbl_tunjangan_tetap`** (Tipe: `combolist`)
- **`cbl_tunjangan_tidak_tetap`** (Tipe: `combolist`)

### 2. Business Logic, Queries & Actions
#### Komponen: `btn_upload` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 155582
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
#### Komponen: `btn_proses` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 155583
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Form Baru (`upload_client_dialog_v2`)

---
##### Route ID: 155584
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cbl_tunjangan_tetap  []`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Pilih list Kategori Tunjangan Tetap dan Tidak Tetap terlebih dahulu`

---
##### Route ID: 155585
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 155586
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $tunjangan_beda_preiode  []`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** @beda_tunjangan -> 0

---
##### Route ID: 155587
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 155588
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $tunjangan_beda_preiode  ["Tunjangan Beda Periode"]`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** @beda_tunjangan -> 1

---
##### Route ID: 155589
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 1 FROM master_salary 
WHERE 
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 LIMIT 1
```

---
##### Route ID: 155590
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@beda_tunjangan`

---
##### Route ID: 155591
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)
- **Detail:**
```json
{
  "param2": "red",
  "param1": "Jika kategori diwajibkan isi"
}
```

---
##### Route ID: 155592
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
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `$komponen_upah(arrayin)`

---
#### Komponen: `btn_template` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 155593
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT division AS "Division",unit_name AS "Unit",position AS "Position",employee_type AS "Employee Type",branch AS "Branch",workday_type AS "Works Days",jip_type AS "BPJS TK Type",manajemen_fee AS "Manajemen Fee",
variable,fix,daily,allowance,submitted_user,cl_first_transaction,cl_retention,pay_later_first_transaction,achivement,
tunjangan_supervisor,tunjangan_jabatan,skill_allowance,grading_allowance,montly_allowance,performance_allowance,
position_allowance,bbm,komunikasi,transportasi,productivity,khusus,sewa_laptop,makan_meal,jabatan,career_allowence,
tunjangan_premium,kinerja,tunjangan_parkir,tj_kehadiran,tj_tugas_harian,spesial_threatment AS "biaya_training",bonus,productivity_non_upah,
insentif_non_upah,lembur_non_upah,tj_kesehatan_non_upah,perdiem_non_upah,performance_pay_non_upah,leader_board_non_upah,monthly_comission_non_upah,
sop_reward_non_upah,kompensasi_hln_non_upah,motorcycle_collector_non_upah,birthday_gift__non_upah,shift_allowance_non_upah,
bonus_non_upah,thr,kompensasi,bpjs_perusahaan AS "bpjs_kesehatan_perusahaan",jip_perusahaan,jkm_perusahaan,jkk_perusahaan,jamsostek_perusahaan AS "jht_perusahaan",
bpjs_employee AS "bpjs_kesehatan_employee",jip_employee,jht_employee,bsu AS "bpu",status_asuransi,
asuransi_kesehatan,asuransi_kecelakaan
FROM master_salary
WHERE 1=2
```

---
##### Route ID: 155594
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** @result -> @template

---
##### Route ID: 155595
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `ReportAction` (`export`)
- **Detail:**
```json
{
  "param3": "@{\"cells\":{\"A5\":\"\"},\"template\":\"true\",\"sheetrename\":\"Template Upload Client\",\"file\":\"/home/generator/payroll/storage/TemplateClient.xlsx\"}",
  "param2": "Template Client.xlsx",
  "param1": "@result"
}
```

---
##### Route ID: 155596
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@template",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"0\",\"sqlwhere\":\"\",\"param\":{\"parameter2\":\"\",\"parameter1\":\"\"}},\"tbl\":\"none\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{},\"callz\":\"\",\"dbmode\":\"query\",\"sql\":\"\"}",
  "param1": ""
}`

---
#### Komponen: `navfile` (Tipe: navresult)
Komponen ini memicu aliran logika berikut:

##### Route ID: 155606
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+RESPONSECODE  1`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $fname -> @+RESULT (json)

---
##### Route ID: 155607
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
##### Route ID: 155608
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  123`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Success`

---
##### Route ID: 155609
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
  "param1": "77"
}
```

---
##### Route ID: 155610
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
##### Route ID: 155611
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  123`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Data Kosong `

---
##### Route ID: 155612
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
UPDATE employee_payroll_data 
SET bpjs_perusahaan =
CASE
		
		WHEN ( bpjs_perusahaan = 1 AND payroll_mode = "MODE1" ) THEN
		( SELECT ROUND( variable * ( 4 / 100 ) ) ) 
		WHEN ( bpjs_perusahaan = 1 AND payroll_mode = "MODE2" ) THEN
		( SELECT ROUND( fix * ( 4 / 100 ) ) ) 
		WHEN ( bpjs_perusahaan = 1 AND payroll_mode = "MODE3" ) THEN
		( SELECT ROUND( daily * ( 4 / 100 ) ) ) 
		WHEN ( bpjs_perusahaan = 1 AND payroll_mode = "MODE4" ) THEN
		( SELECT ROUND( allowance * ( 4 / 100 ) ) ) ELSE 0 
	END,
	jip_perusahaan =
CASE
		
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE1" AND jip_type = "FIX" AND variable < 9559600 ) THEN
		( SELECT ROUND( variable * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE2" AND jip_type = "FIX" AND fix < 9559600 ) THEN
		( SELECT ROUND( fix * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE3" AND jip_type = "FIX" AND daily < 9559600 ) THEN
		( SELECT ROUND( daily * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE4" AND jip_type = "FIX" AND allowance < 9559600 ) THEN
		( SELECT ROUND( allowance * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE1" AND jip_type = "FIX" AND variable > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE2" AND jip_type = "FIX" AND fix > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE3" AND jip_type = "FIX" AND daily > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE4" AND jip_type = "FIX" AND allowance > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE1" AND jip_type = "Variable" AND variable < 9559600 ) THEN
		( SELECT ROUND( ( variable * ( absen / total_works ) ) * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE2" AND jip_type = "Variable" AND fix < 9559600 ) THEN
		( SELECT ROUND( fix * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE3" AND jip_type = "Variable" AND daily < 9559600 ) THEN
		( SELECT ROUND( ( daily * absen ) * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE4" AND jip_type = "Variable" AND allowance < 9559600 ) THEN
		( SELECT ROUND( ( allowance * achivement ) * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE1" AND jip_type = "Variable" AND variable > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE2" AND jip_type = "Variable" AND fix > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE3" AND jip_type = "Variable" AND daily > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE4" AND jip_type = "Variable" AND allowance > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 2 / 100 ) ) ) ELSE 0 
	END,
	jkm_perusahaan =
CASE
		
		WHEN ( jkm_perusahaan = 1 AND payroll_mode = "MODE1" AND jip_type = "FIX") THEN
		( SELECT ROUND( variable * ( 0.3 / 100 ) ) ) 
		WHEN ( jkm_perusahaan = 1 AND payroll_mode = "MODE2" AND jip_type = "FIX") THEN
		( SELECT ROUND( fix * ( 0.3 / 100 ) ) ) 
		WHEN ( jkm_perusahaan = 1 AND payroll_mode = "MODE3" AND jip_type = "FIX") THEN
		( SELECT ROUND( daily * ( 0.3 / 100 ) ) ) 
		WHEN ( jkm_perusahaan = 1 AND payroll_mode = "MODE4" AND jip_type = "FIX") THEN
		( SELECT ROUND( allowance * ( 0.3 / 100 ) ) )
		WHEN ( jkm_perusahaan = 1 AND payroll_mode = "MODE1" AND jip_type = "Variable") THEN
		( SELECT ROUND( ( variable * ( absen / total_works ) ) * ( 0.3 / 100 ) ) ) 
		WHEN ( jkm_perusahaan = 1 AND payroll_mode = "MODE2" AND jip_type = "Variable") THEN
		( SELECT ROUND( fix * ( 0.3 / 100 ) ) ) 
		WHEN ( jkm_perusahaan = 1 AND payroll_mode = "MODE3" AND jip_type = "Variable") THEN
		( SELECT ROUND( ( daily * absen ) * ( 0.3 / 100 ) ) ) 
		WHEN ( jkm_perusahaan = 1 AND payroll_mode = "MODE4" AND jip_type = "Variable") THEN
		( SELECT ROUND( ( allowance * achivement ) * ( 0.3 / 100 ) ) ) ELSE 0 
	END,
	jkk_perusahaan =
CASE
		
		WHEN ( jkk_perusahaan = 1 AND payroll_mode = "MODE1" AND jip_type = "FIX") THEN
		( SELECT ROUND( variable * ( 0.24 / 100 ) ) ) 
		WHEN ( jkk_perusahaan = 1 AND payroll_mode = "MODE2" AND jip_type = "FIX") THEN
		( SELECT ROUND( fix * ( 0.24 / 100 ) ) ) 
		WHEN ( jkk_perusahaan = 1 AND payroll_mode = "MODE3" AND jip_type = "FIX") THEN
		( SELECT ROUND( daily * ( 0.24 / 100 ) ) ) 
		WHEN ( jkk_perusahaan = 1 AND payroll_mode = "MODE4" AND jip_type = "FIX") THEN
		( SELECT ROUND( allowance * ( 0.24 / 100 ) ) ) 
		WHEN ( jkk_perusahaan = 1 AND payroll_mode = "MODE1" AND jip_type = "Variable") THEN
		( SELECT ROUND( ( variable * ( absen / total_works ) ) * ( 0.24 / 100 ) ) )
		WHEN ( jkk_perusahaan = 1 AND payroll_mode = "MODE2" AND jip_type = "Variable") THEN
		( SELECT ROUND( fix * ( 0.24 / 100 ) ) ) 
		WHEN ( jkk_perusahaan = 1 AND payroll_mode = "MODE3" AND jip_type = "Variable") THEN
		( SELECT ROUND( ( daily * absen ) * ( 0.24 / 100 ) ) ) 
		WHEN ( jkk_perusahaan = 1 AND payroll_mode = "MODE4" AND jip_type = "Variable") THEN
		( SELECT ROUND( ( allowance * achivement ) * ( 0.24 / 100 ) ) ) ELSE 0 
	END,
	jamsostek_perusahaan = 
CASE
		
		WHEN ( jamsostek_perusahaan = 1 AND payroll_mode = "MODE1" AND jip_type = "FIX") THEN
		( SELECT ROUND( variable * ( 3.7 / 100 ) ) ) 
		WHEN ( jamsostek_perusahaan = 1 AND payroll_mode = "MODE2" AND jip_type = "FIX") THEN
		( SELECT ROUND( fix * ( 3.7 / 100 ) ) ) 
		WHEN ( jamsostek_perusahaan = 1 AND payroll_mode = "MODE3" AND jip_type = "FIX") THEN
		( SELECT ROUND( daily * ( 3.7 / 100 ) ) ) 
		WHEN ( jamsostek_perusahaan = 1 AND payroll_mode = "MODE4" AND jip_type = "FIX") THEN
		( SELECT ROUND( allowance * ( 3.7 / 100 ) ) )
		WHEN ( jamsostek_perusahaan = 1 AND payroll_mode = "MODE1" AND jip_type = "Variable") THEN
		( SELECT ROUND(( variable * (absen / total_works)) * ( 3.7 / 100 ) ) ) 
		WHEN ( jamsostek_perusahaan = 1 AND payroll_mode = "MODE2" AND jip_type = "Variable") THEN
		( SELECT ROUND( fix * ( 3.7 / 100 ) ) ) 
		WHEN ( jamsostek_perusahaan = 1 AND payroll_mode = "MODE3" AND jip_type = "Variable") THEN
		( SELECT ROUND( ( daily * absen ) * ( 3.7 / 100 ) ) ) 
		WHEN ( jamsostek_perusahaan = 1 AND payroll_mode = "MODE4" AND jip_type = "Variable") THEN
		( SELECT ROUND( ( allowance * achivement ) * ( 3.7 / 100 ) ) ) ELSE 0 
	END,
	bpjs_employee = 
CASE
		
		WHEN ( bpjs_employee = 1 AND payroll_mode = "MODE1" ) THEN
		( SELECT ROUND( variable * ( 1 / 100 ) ) ) 
		WHEN ( bpjs_employee = 1 AND payroll_mode = "MODE2" ) THEN
		( SELECT ROUND( fix * ( 1 / 100 ) ) ) 
		WHEN ( bpjs_employee = 1 AND payroll_mode = "MODE3" ) THEN
		( SELECT ROUND( daily * ( 1 / 100 ) ) ) 
		WHEN ( bpjs_employee = 1 AND payroll_mode = "MODE4" ) THEN
		( SELECT ROUND( allowance * ( 1 / 100 ) ) ) ELSE 0 
	END,
	jip_employee =
CASE
		
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE1" AND jip_type = "FIX" AND variable < 9559600 ) THEN
		( SELECT ROUND( variable * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE2" AND jip_type = "FIX" AND fix < 9559600 ) THEN
		( SELECT ROUND( fix * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE3" AND jip_type = "FIX" AND daily < 9559600 ) THEN
		( SELECT ROUND( daily * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE4" AND jip_type = "FIX" AND allowance < 9559600 ) THEN
		( SELECT ROUND( allowance * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE1" AND jip_type = "FIX" AND variable > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE2" AND jip_type = "FIX" AND fix > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE3" AND jip_type = "FIX" AND daily > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE4" AND jip_type = "FIX" AND allowance > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE1" AND jip_type = "Variable" AND variable < 9559600 ) THEN
		( SELECT ROUND( ( variable * ( absen / total_works ) ) * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE2" AND jip_type = "Variable" AND fix < 9559600 ) THEN
		( SELECT ROUND( fix * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE3" AND jip_type = "Variable" AND daily < 9559600 ) THEN
		( SELECT ROUND( ( daily * absen ) * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE4" AND jip_type = "Variable" AND allowance < 9559600 ) THEN
		( SELECT ROUND( ( allowance * achivement ) * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE1" AND jip_type = "Variable" AND variable > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE2" AND jip_type = "Variable" AND fix > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE3" AND jip_type = "Variable" AND daily > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE4" AND jip_type = "Variable" AND allowance > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 1 / 100 ) ) ) ELSE 0 
	END,
	jht_employee =
CASE
		
		WHEN ( jht_employee = 1 AND payroll_mode = "MODE1" AND jip_type = "FIX") THEN
		( SELECT ROUND( variable * ( 2 / 100 ) ) ) 
		WHEN ( jht_employee = 1 AND payroll_mode = "MODE2" AND jip_type = "FIX") THEN
		( SELECT ROUND( fix * ( 2 / 100 ) ) ) 
		WHEN ( jht_employee = 1 AND payroll_mode = "MODE3" AND jip_type = "FIX") THEN
		( SELECT ROUND( daily * ( 2 / 100 ) ) ) 
		WHEN ( jht_employee = 1 AND payroll_mode = "MODE4" AND jip_type = "FIX") THEN
		( SELECT ROUND( allowance * ( 2 / 100 ) ) ) 
		WHEN ( jht_employee = 1 AND payroll_mode = "MODE1" AND jip_type = "Variable") THEN
		( SELECT ROUND( ( variable * ( absen / total_works ) ) * ( 2 / 100 ) ) ) 
		WHEN ( jht_employee = 1 AND payroll_mode = "MODE2" AND jip_type = "Variable") THEN
		( SELECT ROUND( fix * ( 2 / 100 ) ) ) 
		WHEN ( jht_employee = 1 AND payroll_mode = "MODE3" AND jip_type = "Variable") THEN
		( SELECT ROUND( ( daily * absen ) * ( 2 / 100 ) ) ) 
		WHEN ( jht_employee = 1 AND payroll_mode = "MODE4" AND jip_type = "Variable") THEN
		( SELECT ROUND( ( allowance * achivement ) * ( 2 / 100 ) ) ) ELSE 0 
	END  
	WHERE
	id = ?
```

---
##### Route ID: 155613
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 155614
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  data_regex`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
#### Komponen: `load_combo` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 155619
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT field_deskripsi FROM master_allowance 
WHERE is_base_salary !=1;
```

---
##### Route ID: 155620
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbl_tunjangan_tetap -> @all_allowance

---
##### Route ID: 155621
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbl_tunjangan_tidak_tetap -> @all_allowance

---
##### Route ID: 155622
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT kode,komponen_upah FROM master_upah_tk
```

---
##### Route ID: 155623
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $komponen_upah -> @upah_tk

---
##### Route ID: 172812
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $komponen_lembur -> @upah_tk

---
##### Route ID: 182985
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT id,salary_type FROM master_salary_type LIMIT 4
```

---
##### Route ID: 182986
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $salary_type -> @salary_type

---
#### Komponen: `load_combo2` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 155631
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT allowance_type FROM master_type_fixed_allowance
WHERE allowance_type IN @? AND allowance_type !='Jabatan'
UNION
SELECT allowance_type FROM master_type_not_fixed_allowance
WHERE allowance_type IN @? AND allowance_type !='Jabatan'
```

---
##### Route ID: 155632
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $tunjangan_tetap_periode -> @tunjangan_beda_periode

---
---

## FORM: `add_client_v2`

### 1. Validasi & Mandatori Field (Input Components)
- _Tidak ada field mandatori yang didefinisikan secara eksplisit di komponen._

### 2. Business Logic, Queries & Actions
#### Komponen: `save_spv` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 157182
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Are You Sure ?`

---
##### Route ID: 157183
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $division  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Division] can't be empty!`

---
##### Route ID: 157184
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $unit  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Unit Name] can't be empty!`

---
##### Route ID: 157185
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $position  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Position] can't be empty!`

---
##### Route ID: 157186
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $employee_type  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Employee Type] can't be empty!`

---
##### Route ID: 157187
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $branch  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Branch] can't be empty!`

---
##### Route ID: 157188
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $periode_start  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Periode Start] can't be empty!`

---
##### Route ID: 157189
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $salary_type  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Salary Type] can't be empty!`

---
##### Route ID: 157190
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @jip_type[0,0] = 1`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Jika BPJS TK Type "None" khusus untuk yang tidak memiliki bpjs`

---
##### Route ID: 157191
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_jip_type[0,0]  1`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Jika BPJS nya tidak ada seharusnya BPJS TK Type diisi "None"`

---
##### Route ID: 157192
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_methode_pajak  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Methode Pajak] can't be empty!`

---
##### Route ID: 157193
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 157194
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 157195
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 157196
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 157197
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 157198
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 157199
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 157200
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
##### Route ID: 157201
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
##### Route ID: 157202
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
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 157203
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT division,unit_name,position,employee_type,branch,periode_start,periode_end FROM master_salary
WHERE division = ? AND unit_name = ? AND position = ? AND employee_type = ? AND branch = ?
```

---
##### Route ID: 157204
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @view(rows)  1`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Data already exists!`

---
##### Route ID: 157205
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $periode_end  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Periode End] can't be empty!`

---
##### Route ID: 157206
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 157207
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
##### Route ID: 157208
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $periode_start > $periode_end`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Tanggal Akhir Terlalu kecil`

---
##### Route ID: 157209
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $bpjs_ketenagakerjaan = 1`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Komponen Upah BPJS TK] can't be empty!`

---
##### Route ID: 157210
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 157211
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 1 WHERE (? = "" OR ? = "None") AND (? = 1 OR ? = 1)
```

---
##### Route ID: 157212
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 1 WHERE (? = "Variable" OR ? = "FIX")  AND (? = 0 AND ? = 0)
```

---
#### Komponen: `load` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 157213
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT total FROM master_works_day WHERE id = 1
```

---
##### Route ID: 157214
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $total_work_day1 -> !total

---
##### Route ID: 157215
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT total FROM master_works_day WHERE id = 2
```

---
##### Route ID: 157216
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $total_work_day2 -> @total_25[0,0]

---
#### Komponen: `load_combo` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 157217
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT type FROM master_works_day WHERE type!= "not assigned" GROUP BY type
```

---
##### Route ID: 157218
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $works_days -> @work_day

---
##### Route ID: 157219
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT division FROM employee_update_data 
WHERE status_employee IN ('ACTIVE','BLOCK','FRAUD','TERMINATE') OR 
#(resign_date > DATE_SUB(CURDATE(), INTERVAL 3 MONTH))
#(resign_date is NULL OR YEAR(resign_date) = YEAR(CURDATE())) 
(resign_date is Null OR resign_date >= '2023-12-01')
GROUP BY division
ORDER BY division ASC
```

---
##### Route ID: 157220
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $division -> @division

---
##### Route ID: 157221
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT position FROM employee_update_data 
WHERE division = ? AND unit_name = ? AND (status_employee IN ('ACTIVE','BLOCK','FRAUD','TERMINATE') OR 
#(resign_date > DATE_SUB(CURDATE(), INTERVAL 3 MONTH))) 
(resign_date is Null OR resign_date >= '2023-12-01'))
GROUP BY position
ORDER BY position ASC
```

---
##### Route ID: 157222
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $position -> @position

---
##### Route ID: 157223
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT employee_type FROM employee_update_data 
WHERE division = ? AND unit_name = ? AND position = ? AND (status_employee IN ('ACTIVE','BLOCK','FRAUD','TERMINATE') OR 
#(resign_date > DATE_SUB(CURDATE(), INTERVAL 3 MONTH)))
#(resign_date is NULL OR YEAR(resign_date) = YEAR(CURDATE()))) GROUP BY employee_type
(resign_date is Null OR resign_date >= '2023-12-01'))	
GROUP BY employee_type
ORDER BY employee_type ASC
```

---
##### Route ID: 157224
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $employee_type -> @employee_type

---
##### Route ID: 157225
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT id,salary_type  FROM master_salary_type GROUP BY salary_type 
LIMIT 4
```

---
##### Route ID: 157226
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $salary_type -> @salary_type

---
##### Route ID: 157227
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT unit_name FROM employee_update_data WHERE division = ? AND
(status_employee IN ('ACTIVE','BLOCK','FRAUD','TERMINATE') OR 
#(resign_date > DATE_SUB(CURDATE(), INTERVAL 3 MONTH)))
#(resign_date is NULL OR YEAR(resign_date) = YEAR(CURDATE()))) 
(resign_Date is Null OR resign_date >= '2023-12-01')) 
GROUP BY unit_name
ORDER BY unit_name ASC
```

---
##### Route ID: 157228
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $unit -> @unit_name

---
##### Route ID: 157229
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT nominal FROM master_umk 
WHERE area = ? AND ? <= 2
```

---
##### Route ID: 157230
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $nominal -> @set_umk[0,0]

---
##### Route ID: 157231
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT branch FROM employee_update_data
WHERE division = ? AND unit_name = ?
AND position = ? AND employee_type = ? 
AND (status_employee IN ('ACTIVE','BLOCK','FRAUD','TERMINATE') OR 
#(resign_date > DATE_SUB(CURDATE(), INTERVAL 3 MONTH)))
#(resign_date is NULL OR YEAR(resign_date) = YEAR(CURDATE()))) 
(resign_date is Null OR resign_date >= '2023-12-01'))
GROUP BY branch
ORDER BY branch ASC
```

---
##### Route ID: 157232
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $branch -> @branch

---
##### Route ID: 157233
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT kode,komponen_upah FROM master_upah_tk
```

---
##### Route ID: 157234
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $komponen_upah -> @upah_tk

---
##### Route ID: 157237
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT allowance_type FROM master_type_fixed_allowance
WHERE allowance_type !="jabatan"
UNION
SELECT allowance_type FROM master_type_not_fixed_allowance
WHERE allowance_type !="jabatan"
```

---
##### Route ID: 157238
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbl_tunjangan_tetap -> @all_allowance

---
##### Route ID: 157239
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbl_tunjangan_tidak_tetap -> @all_allowance

---
##### Route ID: 172811
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $komponen_lembur -> @upah_tk

---
#### Komponen: `res` (Tipe: navresult)
Komponen ini memicu aliran logika berikut:

##### Route ID: 157245
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  save_salary_staff`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@insert",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"0\",\"sqlwhere\":\"\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"master_salary\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"tunjangan_parkir\":\"$tunjangan_parkir\",\"achivement\":\"$achivment\",\"tj_lain\":\"$tj_lain\",\"career_allowence\":\"$career_allowance\",\"fix\":\"$nominal_fix\",\"pay_later_first_transaction\":\"$nominal_pay\",\"variable\":\"$nominal_variabel\",\"persen_bpjs_kesehatan\":\"$cb_persen_bpjs_kesehatan\",\"approval\":\"REQUEST\",\"sewa_laptop\":\"$sewa_laptop\",\"daily\":\"$nominal_daily\",\"branch\":\"$branch\",\"unit_name\":\"$unit\",\"jabatan\":\"$jabatan\",\"bpjs_ketenagakerjaan\":\"$bpjs_ketenagakerjaan\",\"kompensasi\":\"$kompensasi\",\"methode_pajak\":\"$cb_methode_pajak\",\"insentif_non_upah\":\"$insentif\",\"tunjangan_beda_periode\":\"@beda_tunjangan\",\"salary_type_id\":\"$salary_type\",\"tunjangan_operasional\":\"$tj_operasional\",\"position_allowance\":\"$position_allowance\",\"bonus\":\"$bonus\",\"makan_meal\":\"$makan_meal\",\"tunjangan_jabatan\":\"$tunjangan_jabatan\",\"grading_allowance\":\"$grading_allowance\",\"tunjangan_tetap\":\"$tunjangan_tetap_periode (arraydb)\",\"bbm\":\"$bbm\",\"shift_allowance_non_upah\":\"$shift_allowance\",\"id_user\":\"@+SESSION-ID\",\"bonus_non_upah\":\"$bonus_non_upah\",\"komunikasi\":\"$komunikasi\",\"kategori_tunjangan_tetap\":\"$cbl_tunjangan_tetap(arrayin)\",\"monthly_comission_non_upah\":\"$monthly_commision\",\"cl_retention\":\"$nominal_retention\",\"workday_type\":\"$works_days\",\"rapelan\":\"$rapel\",\"division\":\"$division\",\"komponen_lembur\":\"$komponen_lembur(arraydb)\",\"spesial_threatment\":\"$biaya_jasa_training\",\"created_date\":\"@+NOW()\",\"performance_allowance\":\"$performance_allowance\",\"tunjangan_supervisor\":\"$tunjangan_spv\",\"total_works\":\"0\",\"tj_tugas_harian\":\"$tj_tugas_harian\",\"kategori_tunjangan_tidak_tetap\":\"$cbl_tunjangan_tidak_tetap(arrayin)\",\"periode_payroll\":\"$periode (trim)\",\"submitted_user\":\"$nominal_submiteduser\",\"upah_tk\":\"$komponen_upah(arraydb)\",\"tunjangan_akomodasi\":\"$tj_akomodasi\",\"manajemen_fee\":\"$manajemen_fee\",\"tj_kehadiran\":\"$tj_hadir\",\"position\":\"$position\",\"tunjangan_sewa_service_motor\":\"$tj_sewa_service\",\"created_by\":\"@+SESSION-FULL_NAME\",\"tj_kesehatan_non_upah\":\"$tj_kesehatan\",\"productivity_non_upah\":\"$productivity_non_tetap\",\"tunjangan_premium\":\"$tunjangan_premium\",\"lembur_non_upah\":\"$lembur\",\"cl_first_transaction\":\"$nominal_firsttransaction\",\"performance_pay_non_upah\":\"$performance_pay\",\"transportasi\":\"$transportasi\",\"tunjangan_project\":\"$tj_project(trim)\",\"thr\":\"$thr\",\"jip_type\":\"$jip_type\",\"kinerja\":\"$kinerja\",\"allowance\":\"$nominal_allowance\",\"skill_allowance\":\"$skill_allowance\",\"productivity\":\"$productivity\",\"montly_allowance\":\"$montly_allowance\",\"khusus\":\"$khusus\",\"komponen_project\":\"$cb_komponen_project\",\"employee_type\":\"$employee_type\",\"status_asuransi\":\"$asuransi\"},\"callz\":\"\",\"dbmode\":\"insert\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 157246
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
  "param1": "11"
}
```

---
##### Route ID: 157247
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
##### Route ID: 157258
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT MAX(id) FROM master_salary
```

---
##### Route ID: 157259
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@history_client",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"0\",\"sqlwhere\":\"\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"history_salary\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"tunjangan_parkir\":\"$tunjangan_parkir\",\"achivement\":\"$achivment\",\"tj_lain\":\"$tj_lain\",\"career_allowence\":\"$career_allowance\",\"fix\":\"$nominal_fix\",\"pay_later_first_transaction\":\"$nominal_pay\",\"variable\":\"$nominal_variabel\",\"persen_bpjs_kesehatan\":\"$cb_persen_bpjs_kesehatan\",\"sewa_laptop\":\"$sewa_laptop\",\"daily\":\"$nominal_daily\",\"branch\":\"$branch\",\"unit_name\":\"$unit\",\"jabatan\":\"$jabatan\",\"bpjs_ketenagakerjaan\":\"$bpjs_ketenagakerjaan\",\"methode_pajak\":\"$cb_methode_pajak\",\"kompensasi\":\"$kompensasi\",\"insentif_non_upah\":\"$insentif\",\"salary_type_id\":\"$salary_type\",\"tunjangan_operasional\":\"$tj_operasional\",\"position_allowance\":\"$position_allowance\",\"bonus\":\"$bonus\",\"status\":\"ADD\",\"makan_meal\":\"$makan_meal\",\"tunjangan_jabatan\":\"$tunjangan_jabatan\",\"grading_allowance\":\"$grading_allowance\",\"tunjangan_tetap\":\"$tunjangan_tetap_periode (arraydb)\",\"bbm\":\"$bbm\",\"shift_allowance_non_upah\":\"$shift_allowance\",\"bonus_non_upah\":\"$bonus_non_upah\",\"komunikasi\":\"$komunikasi\",\"id_parent\":\"@tinggi[0,0]\",\"kategori_tunjangan_tetap\":\"$cbl_tunjangan_tetap(arrayin)\",\"bpjs_kesehatan\":\"$bpjs_kesehatan\",\"monthly_comission_non_upah\":\"$monthly_commision\",\"cl_retention\":\"$nominal_retention\",\"rapelan\":\"$rapel\",\"division\":\"$division\",\"spesial_threatment\":\"$biaya_jasa_training\",\"created_date\":\"@+NOW()\",\"performance_allowance\":\"$performance_allowance\",\"tunjangan_supervisor\":\"$tunjangan_spv\",\"tj_tugas_harian\":\"$tj_tugas_harian\",\"kategori_tunjangan_tidak_tetap\":\"$cbl_tunjangan_tidak_tetap(arrayin)\",\"periode_payroll\":\"$periode (trim)\",\"submitted_user\":\"$nominal_submiteduser\",\"upah_tk\":\"$komponen_upah(arraydb)\",\"tunjangan_akomodasi\":\"$tj_akomodasi\",\"periode_start\":\"$periode_start (fdatedb)\",\"manajemen_fee\":\"$manajemen_fee\",\"tj_kehadiran\":\"$tj_hadir\",\"position\":\"$position\",\"tunjangan_sewa_service_motor\":\"$tj_sewa_service\",\"periode_end\":\"$periode_end (fdatedb)\",\"created_by\":\"@+SESSION-FULL_NAME\",\"tj_kesehatan_non_upah\":\"$tj_kesehatan\",\"methode_payroll\":\"$cb_methode_pajak\",\"productivity_non_upah\":\"$productivity_non_tetap\",\"tunjangan_premium\":\"$tunjangan_premium\",\"lembur_non_upah\":\"$lembur\",\"cl_first_transaction\":\"$nominal_firsttransaction\",\"performance_pay_non_upah\":\"$performance_pay\",\"workday_type_id\":\"$works_days\",\"transportasi\":\"$transportasi\",\"tunjangan_project\":\"$tj_project\",\"thr\":\"$thr\",\"jip_type\":\"$jip_type\",\"kinerja\":\"$kinerja\",\"allowance\":\"$nominal_allowance\",\"skill_allowance\":\"$skill_allowance\",\"productivity\":\"$productivity\",\"montly_allowance\":\"$montly_allowance\",\"khusus\":\"$khusus\",\"komponen_project\":\"$cb_komponen_project\",\"employee_type\":\"$employee_type\",\"status_asuransi\":\"$asuransi\"},\"callz\":\"\",\"dbmode\":\"insert\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 157261
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
##### Route ID: 157262
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `DataAction` (`broadcastglobal`)
- **Detail:**
```json
{
  "param2": "@[]",
  "param1": "pop_up"
}
```

---
##### Route ID: 157266
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $tunjangan_beda_preiode  []`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** @beda_tunjangan -> 0

---
##### Route ID: 157267
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $tunjangan_beda_preiode  ["Tunjangan Beda Periode"]`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** @beda_tunjangan -> 1

---
##### Route ID: 225369
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@insert(error)`

---
#### Komponen: `load_visible` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 157271
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)
- **Detail:**
```json
{
  "param2": "red",
  "param1": "Special Treatment"
}
```

---
##### Route ID: 157272
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_special_treatment contain Biaya Jasa Training`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $biaya_jasa_training -> true

---
##### Route ID: 157273
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 157274
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
- **Parameter:** $biaya_jasa_training -> false

---
##### Route ID: 157275
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_special_treatment contain Bonus`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $bonus -> true

---
##### Route ID: 157276
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $bonus -> false

---
##### Route ID: 157277
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)
- **Detail:**
```json
{
  "param2": "red",
  "param1": "Tunjangan"
}
```

---
##### Route ID: 157278
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjanga_tetap contain Tunjangan Supervisor`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_spv -> true

---
##### Route ID: 157279
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_spv -> false

---
##### Route ID: 157280
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjanga_tetap contain Tunjangan Jabatan`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_jabatan -> true

---
##### Route ID: 157281
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_jabatan -> false

---
##### Route ID: 157282
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjanga_tetap contain Skill Allowance`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $skill_allowance -> true

---
##### Route ID: 157283
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $skill_allowance -> false

---
##### Route ID: 157284
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjanga_tetap contain Grading Allowance`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $grading_allowance -> true

---
##### Route ID: 157285
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $grading_allowance -> false

---
##### Route ID: 157286
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjanga_tetap contain Montly Allowance`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $montly_allowance -> true

---
##### Route ID: 157287
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjanga_tetap contain Performance Allowance`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $performance_allowance -> true

---
##### Route ID: 157288
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $montly_allowance -> false

---
##### Route ID: 157289
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $performance_allowance -> false

---
##### Route ID: 157290
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjanga_tetap contain Position Allowance`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $position_allowance -> true

---
##### Route ID: 157291
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $position_allowance -> false

---
##### Route ID: 157292
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $bbm -> false

---
##### Route ID: 157293
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjangan_tidak_tetap contain BBM`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $bbm -> true

---
##### Route ID: 157294
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjangan_tidak_tetap contain Komunikasi`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $komunikasi -> true

---
##### Route ID: 157295
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $komunikasi -> false

---
##### Route ID: 157296
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjangan_tidak_tetap contain Transportasi`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $transportasi -> true

---
##### Route ID: 157297
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $transportasi -> false

---
##### Route ID: 157298
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjangan_tidak_tetap contain Productivity`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $productivity -> true

---
##### Route ID: 157299
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $productivity -> false

---
##### Route ID: 157300
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjangan_tidak_tetap contain Khusus`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $khusus -> true

---
##### Route ID: 157301
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $khusus -> false

---
##### Route ID: 157302
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjangan_tidak_tetap contain Sewa Laptop`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $sewa_laptop -> true

---
##### Route ID: 157303
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $sewa_laptop -> false

---
##### Route ID: 157304
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjangan_tidak_tetap contain Makan Meal`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $makan_meal -> true

---
##### Route ID: 157305
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $makan_meal -> false

---
##### Route ID: 157306
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjangan_tidak_tetap contain 'Jabatan'`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $jabatan -> true

---
##### Route ID: 157307
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @set_jabatan[0,0]  1`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $jabatan -> false

---
##### Route ID: 157308
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjangan_tidak_tetap contain Career Allowance`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $career_allowance -> true

---
##### Route ID: 157309
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $career_allowance -> false

---
##### Route ID: 157310
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjangan_tidak_tetap contain Tunjangan Premium`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_premium -> true

---
##### Route ID: 157311
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_premium -> false

---
##### Route ID: 157312
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjangan_tidak_tetap contain Kinerja`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $kinerja -> true

---
##### Route ID: 157313
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $kinerja -> false

---
##### Route ID: 157314
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjangan_tidak_tetap contain Tunjangan Parkir`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_parkir -> true

---
##### Route ID: 157315
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tunjangan_parkir -> false

---
##### Route ID: 157316
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)
- **Detail:**
```json
{
  "param2": "red",
  "param1": "button"
}
```

---
##### Route ID: 157317
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  SPV`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $save_spv -> true

---
##### Route ID: 157318
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Staff`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $save_staff -> true

---
##### Route ID: 157319
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)
- **Detail:**
```json
{
  "param2": "red",
  "param1": "achivment"
}
```

---
##### Route ID: 157320
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjangan_tidak_tetap contain Tunjangan Kehadiran`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_hadir -> true

---
##### Route ID: 157321
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_hadir -> false

---
##### Route ID: 157322
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjangan_tidak_tetap contain Tunjangan Tugas Harian`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_tugas_harian -> true

---
##### Route ID: 157323
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_tugas_harian -> false

---
##### Route ID: 157324
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjangan_tidak_tetap contain Tunjangan Operasional`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_operasional -> true

---
##### Route ID: 157325
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_operasional -> false

---
##### Route ID: 157326
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjangan_tidak_tetap contain Tunjangan Sewa dan Service Motor`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_sewa_service -> true

---
##### Route ID: 157327
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_sewa_service -> false

---
##### Route ID: 157328
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjangan_tidak_tetap contain Tunjangan Akomodasi`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_akomodasi -> true

---
##### Route ID: 157329
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_akomodasi -> false

---
##### Route ID: 157330
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $set_tunjangan_tidak_tetap contain Tunjangan Project`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_project -> true

---
##### Route ID: 157331
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $tj_project -> false

---
##### Route ID: 157332
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 1 WHERE ? NOT LIKE "%'Jabatan'%" AND ? NOT LIKE "%'Jabatan'%"
```

---
#### Komponen: `save_staff` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 157333
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Are You Sure ?`

---
##### Route ID: 157334
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $division  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Division] can't be empty!`

---
##### Route ID: 157335
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 157336
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $unit  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Unit Name] can't be empty!`

---
##### Route ID: 157337
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 157338
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $position  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Position] can't be empty!`

---
##### Route ID: 157339
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 157340
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $employee_type  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Employee Type] can't be empty!`

---
##### Route ID: 157341
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 157342
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $branch  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Branch] can't be empty!`

---
##### Route ID: 157343
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 157344
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $periode_start  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Periode] can't be empty!`

---
##### Route ID: 157345
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 157346
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $salary_type  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Salary Type] can't be empty!`

---
##### Route ID: 157347
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 157348
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $salary_type <> 3`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Work Day] can't be empty!`

---
##### Route ID: 157349
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
##### Route ID: 157350
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 157351
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_methode_pajak  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Methode Pajak] can't be empty!`

---
##### Route ID: 157352
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
##### Route ID: 157353
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT division,unit_name,position,employee_type,branch,periode_start, periode_end FROM master_salary
WHERE division = ? AND unit_name = ? AND position = ? AND employee_type = ? AND branch = ?
```

---
##### Route ID: 157354
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_data_already_exist[0,0] <> `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@cek_data_already_exist[0,0]`

---
##### Route ID: 157355
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $bpjs_ketenagakerjaan = 1`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Komponen Upah BPJS TK] can't be empty!`

---
##### Route ID: 157356
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 157357
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 1 WHERE (? = "" OR ? = "None") AND (? = 1 OR ? = 1)
```

---
##### Route ID: 157358
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @jip_type[0,0] = 1`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Jika BPJS nya ada, wajib pilih BPJS TK Type antara Variable ataupun Fix`

---
##### Route ID: 157359
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 1 WHERE (? = "Variable" OR ? = "FIX")  AND (? = 0 AND ? = 0)
```

---
##### Route ID: 157360
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_jip_type[0,0]  1`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Jika BPJS nya tidak ada seharusnya BPJS TK Type diisi "None"`

---
##### Route ID: 157361
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
##### Route ID: 157362
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT
	concat('Division 	: ',?, 
', Unit Name : ', ?, 
', Position 	: ', ?,
', Branch  	: ', ?, 
', Employee Type 	: ', ?,
' Already Exist, Created by ',created_by) 
FROM
	master_salary
WHERE division = ? 
	AND unit_name = ?
	AND position = ? 
	AND branch = ? 
	AND employee_type = ?
```

---
##### Route ID: 172043
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cb_komponen_project  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `[Komponen Project] can't be empty!`

---
##### Route ID: 172044
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 195872
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
#### Komponen: `load_visible2` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 157363
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $works_days  5+2`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $total_work_day -> true

---
##### Route ID: 157364
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
- **Parameter:** $total_work_day -> 21

---
##### Route ID: 157365
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
##### Route ID: 157366
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $works_days  6+1`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $total_work_day -> true

---
##### Route ID: 157367
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
- **Parameter:** $total_work_day -> 25

---
##### Route ID: 157368
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
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 157369
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT nominal FROM master_umk 
WHERE area = ?
```

---
##### Route ID: 157370
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
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $nominal1 -> @set_umk

---
##### Route ID: 157371
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@set_umk`

---
##### Route ID: 157372
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $works_days  not assigned`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $total_work_day -> true

---
##### Route ID: 157373
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
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $total_work_day -> 

---
##### Route ID: 157374
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
#### Komponen: `load_date` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 157376
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $periode_start -> @+NOW (fdateview)

---
##### Route ID: 157377
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $periode_end -> @+NOW (fdateview)

---
#### Komponen: `plus` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 157378
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `EvaluateAction` (``)
- **Detail:**
```json
{
  "paramc": "",
  "paramb": "0.25",
  "parama": "$set",
  "evaluate": "A+B",
  "paramh": "",
  "paramg": "",
  "paramf": "",
  "parame": "",
  "result": "@jml",
  "paramd": ""
}
```

---
##### Route ID: 157379
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $set -> @jml

---
##### Route ID: 157380
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $cb_manajemen_fee -> $set

---
#### Komponen: `min` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 157381
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `EvaluateAction` (``)
- **Detail:**
```json
{
  "paramc": "",
  "paramb": "0.25",
  "parama": "$set",
  "evaluate": "A-B",
  "paramh": "",
  "paramg": "",
  "paramf": "",
  "parame": "",
  "result": "@jml",
  "paramd": ""
}
```

---
##### Route ID: 157382
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @jml >= 0`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $set -> @jml

---
##### Route ID: 157383
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $cb_manajemen_fee -> $set

---
#### Komponen: `load_combo2` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 157388
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT id,allowance_type FROM master_type_fixed_allowance
```

---
##### Route ID: 157389
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbl_tunjangan_tetap -> @fixed_allowance

---
##### Route ID: 157390
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT allowance_type FROM master_type_not_fixed_allowance
```

---
##### Route ID: 157391
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbl_tunjangan_tidak_tetap -> @not_fix_allowance

---
##### Route ID: 157392
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT allowance_type FROM master_type_fixed_allowance
WHERE allowance_type !="jabatan"
UNION
SELECT allowance_type FROM master_type_not_fixed_allowance
WHERE allowance_type !="jabatan"
```

---
##### Route ID: 157393
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbl_tunjangan_tetap -> @all_allowance

---
##### Route ID: 157394
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT allowance_type FROM master_type_fixed_allowance
WHERE allowance_type IN @? AND allowance_type !='Jabatan'
UNION
SELECT allowance_type FROM master_type_not_fixed_allowance
WHERE allowance_type IN @? AND allowance_type !='Jabatan'
```

---
##### Route ID: 157395
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $tunjangan_tetap_periode -> @tunjangan_beda_periode

---
##### Route ID: 174539
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $cb_persen_bpjs_kesehatan -> ["Perusahaan 4% dan Karyawan 1%"]

---
---

## FORM: `upload_update_client_v4`

### 1. Validasi & Mandatori Field (Input Components)
- **`cbl_tunjangan_tidak_tetap`** (Tipe: `combolist`)
- **`cbl_tunjangan_tetap`** (Tipe: `combolist`)

### 2. Business Logic, Queries & Actions
#### Komponen: `btn_upload` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212821
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
##### Route ID: 212822
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
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `$btn_upload`

---
#### Komponen: `btn_proses` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212823
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Form Baru (`upload_update_client_dialog_v4`)

---
##### Route ID: 212824
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $cbl_tunjangan_tetap  []`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Pilih list Kategori Tunjangan Tetap dan Tidak Tetap terlebih dahulu`

---
##### Route ID: 212825
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 212826
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $tunjangan_beda_preiode  []`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** @beda_tunjangan -> 0

---
##### Route ID: 212827
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 212828
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $tunjangan_beda_preiode  ["Tunjangan Beda Periode"]`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** @beda_tunjangan -> 1

---
##### Route ID: 212829
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 1 FROM master_salary 
WHERE 
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 LIMIT 1
```

---
##### Route ID: 212830
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@beda_tunjangan`

---
##### Route ID: 212831
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)
- **Detail:**
```json
{
  "param2": "red",
  "param1": "Jika kategori diwajibkan isi"
}
```

---
##### Route ID: 212832
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
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `$komponen_upah(arrayin)`

---
#### Komponen: `btn_template` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212833
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT division AS "Division",unit_name AS "Unit",position AS "Position",employee_type AS "Employee Type",branch AS "Branch",workday_type AS "Works Days",jip_type AS "BPJS TK Type",manajemen_fee AS "Manajemen Fee",
variable,fix,daily,allowance,submitted_user,cl_first_transaction,cl_retention,pay_later_first_transaction,achivement,
tunjangan_supervisor,tunjangan_jabatan,skill_allowance,grading_allowance,montly_allowance,performance_allowance,
position_allowance,bbm,komunikasi,transportasi,productivity,khusus,sewa_laptop,makan_meal,jabatan,career_allowence,
tunjangan_premium,kinerja,tunjangan_parkir,tj_kehadiran,tj_tugas_harian,spesial_threatment AS "biaya_training",bonus,productivity_non_upah,
insentif_non_upah,lembur_non_upah,tj_kesehatan_non_upah,perdiem_non_upah,performance_pay_non_upah,leader_board_non_upah,monthly_comission_non_upah,
sop_reward_non_upah,kompensasi_hln_non_upah,motorcycle_collector_non_upah,birthday_gift__non_upah,shift_allowance_non_upah,
bonus_non_upah,thr,kompensasi,bpjs_perusahaan AS "bpjs_kesehatan_perusahaan",jip_perusahaan,jkm_perusahaan,jkk_perusahaan,jamsostek_perusahaan AS "jht_perusahaan",
bpjs_employee AS "bpjs_kesehatan_employee",jip_employee,jht_employee,bsu AS "bpu",status_asuransi,
asuransi_kesehatan,asuransi_kecelakaan
FROM master_salary
WHERE 1=2
```

---
##### Route ID: 212834
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** @result -> @template

---
##### Route ID: 212835
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `ReportAction` (`export`)
- **Detail:**
```json
{
  "param3": "",
  "param2": "Template Update Client.xls",
  "param1": "@result"
}
```

---
##### Route ID: 212836
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT division AS 'Division',
unit_name AS 'Unit',
position AS 'Position',
branch AS 'Branch',
employee_type AS 'Employee Type',
tunjangan_supervisor AS 'Tunjangan Supervisor',
tunjangan_jabatan AS 'Tunjangan Jabatan',
skill_allowance AS 'Skill Allowance',
grading_allowance AS 'Grading Allowance',
montly_allowance AS 'Monthly Allowance',
performance_allowance AS 'Performance Allowance',
position_allowance AS 'Position Allowance',
bbm AS 'Tunjangan Bensin',
komunikasi AS 'Tunjangan Komunikasi',
transportasi AS 'Tunjangan Transportasi',
productivity AS 'Tunjangan Productivity',
khusus AS 'Tunjangan Khusus',
sewa_laptop AS 'Sewa Laptop',
makan_meal AS 'Tunjangan Uang Makan',
career_allowence AS 'Career Allowance',
tunjangan_premium AS 'Tunjangan Premium',
kinerja AS 'Tunjangan Kerja',
tunjangan_parkir AS 'Tunjangan Parkir',
tj_kehadiran AS 'Tunjangan Kehadiran',
tj_tugas_harian AS 'Tunjangan Tugas Harian',
tunjangan_operasional AS 'Tunjangan Operasional',
tunjangan_sewa_service_motor AS 'Tunjangan Sewa dan Service Motor',
tunjangan_akomodasi AS 'Tunjangan Akomodasi',
tunjangan_project AS 'Tunjangan Project',
spesial_threatment AS 'Biaya Jasa Training',
bonus AS 'Bonus',
insentif_non_upah AS 'Insentif',
lembur_non_upah AS 'Lembur',
tj_kesehatan_non_upah AS 'Tunjangan Kesehatan',
performance_pay_non_upah AS 'Performance Pay',
monthly_comission_non_upah AS 'Monthly Commission',
shift_allowance_non_upah AS 'Shift Allowance',
thr AS 'THR',
kompensasi AS 'Kompensasi',
project_allowance AS 'Project Allowance',
tj_cuti AS 'Tunjangan Cuti',
tj_kerajinan AS 'Tunjangan Kerajinan',
tj_masa_kontrak AS 'Tunjangan Masa Kontrak',
tj_service_charge AS 'Tunjangan Service Charge',
tj_surveyor_bengkel AS 'Tunjangan Surveyor Bengkel',
tj_tempat_tinggal AS 'Tunjangan Tempat Tinggal',
tj_bensin_parkir AS 'Tunjangan Bensin dan Parkir'
FROM master_salary WHERE id iN @?
```

---
#### Komponen: `navfile` (Tipe: navresult)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212846
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+RESPONSECODE  1`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $fname -> @+RESULT (json)

---
##### Route ID: 212847
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
##### Route ID: 212848
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  123`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Success`

---
##### Route ID: 212849
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
  "param1": "77"
}
```

---
##### Route ID: 212850
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
##### Route ID: 212851
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  123`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Data Kosong `

---
##### Route ID: 212852
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
UPDATE employee_payroll_data 
SET bpjs_perusahaan =
CASE
		
		WHEN ( bpjs_perusahaan = 1 AND payroll_mode = "MODE1" ) THEN
		( SELECT ROUND( variable * ( 4 / 100 ) ) ) 
		WHEN ( bpjs_perusahaan = 1 AND payroll_mode = "MODE2" ) THEN
		( SELECT ROUND( fix * ( 4 / 100 ) ) ) 
		WHEN ( bpjs_perusahaan = 1 AND payroll_mode = "MODE3" ) THEN
		( SELECT ROUND( daily * ( 4 / 100 ) ) ) 
		WHEN ( bpjs_perusahaan = 1 AND payroll_mode = "MODE4" ) THEN
		( SELECT ROUND( allowance * ( 4 / 100 ) ) ) ELSE 0 
	END,
	jip_perusahaan =
CASE
		
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE1" AND jip_type = "FIX" AND variable < 9559600 ) THEN
		( SELECT ROUND( variable * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE2" AND jip_type = "FIX" AND fix < 9559600 ) THEN
		( SELECT ROUND( fix * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE3" AND jip_type = "FIX" AND daily < 9559600 ) THEN
		( SELECT ROUND( daily * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE4" AND jip_type = "FIX" AND allowance < 9559600 ) THEN
		( SELECT ROUND( allowance * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE1" AND jip_type = "FIX" AND variable > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE2" AND jip_type = "FIX" AND fix > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE3" AND jip_type = "FIX" AND daily > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE4" AND jip_type = "FIX" AND allowance > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE1" AND jip_type = "Variable" AND variable < 9559600 ) THEN
		( SELECT ROUND( ( variable * ( absen / total_works ) ) * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE2" AND jip_type = "Variable" AND fix < 9559600 ) THEN
		( SELECT ROUND( fix * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE3" AND jip_type = "Variable" AND daily < 9559600 ) THEN
		( SELECT ROUND( ( daily * absen ) * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE4" AND jip_type = "Variable" AND allowance < 9559600 ) THEN
		( SELECT ROUND( ( allowance * achivement ) * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE1" AND jip_type = "Variable" AND variable > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE2" AND jip_type = "Variable" AND fix > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE3" AND jip_type = "Variable" AND daily > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 2 / 100 ) ) ) 
		WHEN ( jip_perusahaan = 1 AND payroll_mode = "MODE4" AND jip_type = "Variable" AND allowance > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 2 / 100 ) ) ) ELSE 0 
	END,
	jkm_perusahaan =
CASE
		
		WHEN ( jkm_perusahaan = 1 AND payroll_mode = "MODE1" AND jip_type = "FIX") THEN
		( SELECT ROUND( variable * ( 0.3 / 100 ) ) ) 
		WHEN ( jkm_perusahaan = 1 AND payroll_mode = "MODE2" AND jip_type = "FIX") THEN
		( SELECT ROUND( fix * ( 0.3 / 100 ) ) ) 
		WHEN ( jkm_perusahaan = 1 AND payroll_mode = "MODE3" AND jip_type = "FIX") THEN
		( SELECT ROUND( daily * ( 0.3 / 100 ) ) ) 
		WHEN ( jkm_perusahaan = 1 AND payroll_mode = "MODE4" AND jip_type = "FIX") THEN
		( SELECT ROUND( allowance * ( 0.3 / 100 ) ) )
		WHEN ( jkm_perusahaan = 1 AND payroll_mode = "MODE1" AND jip_type = "Variable") THEN
		( SELECT ROUND( ( variable * ( absen / total_works ) ) * ( 0.3 / 100 ) ) ) 
		WHEN ( jkm_perusahaan = 1 AND payroll_mode = "MODE2" AND jip_type = "Variable") THEN
		( SELECT ROUND( fix * ( 0.3 / 100 ) ) ) 
		WHEN ( jkm_perusahaan = 1 AND payroll_mode = "MODE3" AND jip_type = "Variable") THEN
		( SELECT ROUND( ( daily * absen ) * ( 0.3 / 100 ) ) ) 
		WHEN ( jkm_perusahaan = 1 AND payroll_mode = "MODE4" AND jip_type = "Variable") THEN
		( SELECT ROUND( ( allowance * achivement ) * ( 0.3 / 100 ) ) ) ELSE 0 
	END,
	jkk_perusahaan =
CASE
		
		WHEN ( jkk_perusahaan = 1 AND payroll_mode = "MODE1" AND jip_type = "FIX") THEN
		( SELECT ROUND( variable * ( 0.24 / 100 ) ) ) 
		WHEN ( jkk_perusahaan = 1 AND payroll_mode = "MODE2" AND jip_type = "FIX") THEN
		( SELECT ROUND( fix * ( 0.24 / 100 ) ) ) 
		WHEN ( jkk_perusahaan = 1 AND payroll_mode = "MODE3" AND jip_type = "FIX") THEN
		( SELECT ROUND( daily * ( 0.24 / 100 ) ) ) 
		WHEN ( jkk_perusahaan = 1 AND payroll_mode = "MODE4" AND jip_type = "FIX") THEN
		( SELECT ROUND( allowance * ( 0.24 / 100 ) ) ) 
		WHEN ( jkk_perusahaan = 1 AND payroll_mode = "MODE1" AND jip_type = "Variable") THEN
		( SELECT ROUND( ( variable * ( absen / total_works ) ) * ( 0.24 / 100 ) ) )
		WHEN ( jkk_perusahaan = 1 AND payroll_mode = "MODE2" AND jip_type = "Variable") THEN
		( SELECT ROUND( fix * ( 0.24 / 100 ) ) ) 
		WHEN ( jkk_perusahaan = 1 AND payroll_mode = "MODE3" AND jip_type = "Variable") THEN
		( SELECT ROUND( ( daily * absen ) * ( 0.24 / 100 ) ) ) 
		WHEN ( jkk_perusahaan = 1 AND payroll_mode = "MODE4" AND jip_type = "Variable") THEN
		( SELECT ROUND( ( allowance * achivement ) * ( 0.24 / 100 ) ) ) ELSE 0 
	END,
	jamsostek_perusahaan = 
CASE
		
		WHEN ( jamsostek_perusahaan = 1 AND payroll_mode = "MODE1" AND jip_type = "FIX") THEN
		( SELECT ROUND( variable * ( 3.7 / 100 ) ) ) 
		WHEN ( jamsostek_perusahaan = 1 AND payroll_mode = "MODE2" AND jip_type = "FIX") THEN
		( SELECT ROUND( fix * ( 3.7 / 100 ) ) ) 
		WHEN ( jamsostek_perusahaan = 1 AND payroll_mode = "MODE3" AND jip_type = "FIX") THEN
		( SELECT ROUND( daily * ( 3.7 / 100 ) ) ) 
		WHEN ( jamsostek_perusahaan = 1 AND payroll_mode = "MODE4" AND jip_type = "FIX") THEN
		( SELECT ROUND( allowance * ( 3.7 / 100 ) ) )
		WHEN ( jamsostek_perusahaan = 1 AND payroll_mode = "MODE1" AND jip_type = "Variable") THEN
		( SELECT ROUND(( variable * (absen / total_works)) * ( 3.7 / 100 ) ) ) 
		WHEN ( jamsostek_perusahaan = 1 AND payroll_mode = "MODE2" AND jip_type = "Variable") THEN
		( SELECT ROUND( fix * ( 3.7 / 100 ) ) ) 
		WHEN ( jamsostek_perusahaan = 1 AND payroll_mode = "MODE3" AND jip_type = "Variable") THEN
		( SELECT ROUND( ( daily * absen ) * ( 3.7 / 100 ) ) ) 
		WHEN ( jamsostek_perusahaan = 1 AND payroll_mode = "MODE4" AND jip_type = "Variable") THEN
		( SELECT ROUND( ( allowance * achivement ) * ( 3.7 / 100 ) ) ) ELSE 0 
	END,
	bpjs_employee = 
CASE
		
		WHEN ( bpjs_employee = 1 AND payroll_mode = "MODE1" ) THEN
		( SELECT ROUND( variable * ( 1 / 100 ) ) ) 
		WHEN ( bpjs_employee = 1 AND payroll_mode = "MODE2" ) THEN
		( SELECT ROUND( fix * ( 1 / 100 ) ) ) 
		WHEN ( bpjs_employee = 1 AND payroll_mode = "MODE3" ) THEN
		( SELECT ROUND( daily * ( 1 / 100 ) ) ) 
		WHEN ( bpjs_employee = 1 AND payroll_mode = "MODE4" ) THEN
		( SELECT ROUND( allowance * ( 1 / 100 ) ) ) ELSE 0 
	END,
	jip_employee =
CASE
		
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE1" AND jip_type = "FIX" AND variable < 9559600 ) THEN
		( SELECT ROUND( variable * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE2" AND jip_type = "FIX" AND fix < 9559600 ) THEN
		( SELECT ROUND( fix * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE3" AND jip_type = "FIX" AND daily < 9559600 ) THEN
		( SELECT ROUND( daily * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE4" AND jip_type = "FIX" AND allowance < 9559600 ) THEN
		( SELECT ROUND( allowance * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE1" AND jip_type = "FIX" AND variable > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE2" AND jip_type = "FIX" AND fix > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE3" AND jip_type = "FIX" AND daily > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE4" AND jip_type = "FIX" AND allowance > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE1" AND jip_type = "Variable" AND variable < 9559600 ) THEN
		( SELECT ROUND( ( variable * ( absen / total_works ) ) * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE2" AND jip_type = "Variable" AND fix < 9559600 ) THEN
		( SELECT ROUND( fix * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE3" AND jip_type = "Variable" AND daily < 9559600 ) THEN
		( SELECT ROUND( ( daily * absen ) * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE4" AND jip_type = "Variable" AND allowance < 9559600 ) THEN
		( SELECT ROUND( ( allowance * achivement ) * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE1" AND jip_type = "Variable" AND variable > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE2" AND jip_type = "Variable" AND fix > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE3" AND jip_type = "Variable" AND daily > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 1 / 100 ) ) ) 
		WHEN ( jip_employee = 1 AND payroll_mode = "MODE4" AND jip_type = "Variable" AND allowance > 9559600 ) THEN
		( SELECT ROUND( 9559600 * ( 1 / 100 ) ) ) ELSE 0 
	END,
	jht_employee =
CASE
		
		WHEN ( jht_employee = 1 AND payroll_mode = "MODE1" AND jip_type = "FIX") THEN
		( SELECT ROUND( variable * ( 2 / 100 ) ) ) 
		WHEN ( jht_employee = 1 AND payroll_mode = "MODE2" AND jip_type = "FIX") THEN
		( SELECT ROUND( fix * ( 2 / 100 ) ) ) 
		WHEN ( jht_employee = 1 AND payroll_mode = "MODE3" AND jip_type = "FIX") THEN
		( SELECT ROUND( daily * ( 2 / 100 ) ) ) 
		WHEN ( jht_employee = 1 AND payroll_mode = "MODE4" AND jip_type = "FIX") THEN
		( SELECT ROUND( allowance * ( 2 / 100 ) ) ) 
		WHEN ( jht_employee = 1 AND payroll_mode = "MODE1" AND jip_type = "Variable") THEN
		( SELECT ROUND( ( variable * ( absen / total_works ) ) * ( 2 / 100 ) ) ) 
		WHEN ( jht_employee = 1 AND payroll_mode = "MODE2" AND jip_type = "Variable") THEN
		( SELECT ROUND( fix * ( 2 / 100 ) ) ) 
		WHEN ( jht_employee = 1 AND payroll_mode = "MODE3" AND jip_type = "Variable") THEN
		( SELECT ROUND( ( daily * absen ) * ( 2 / 100 ) ) ) 
		WHEN ( jht_employee = 1 AND payroll_mode = "MODE4" AND jip_type = "Variable") THEN
		( SELECT ROUND( ( allowance * achivement ) * ( 2 / 100 ) ) ) ELSE 0 
	END  
	WHERE
	id = ?
```

---
##### Route ID: 212853
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 212854
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  data_regex`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
#### Komponen: `load_combo` (Tipe: label)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212856
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT field_deskripsi FROM master_allowance
```

---
##### Route ID: 212857
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbl_tunjangan_tetap -> @all_allowance

---
##### Route ID: 212858
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbl_tunjangan_tidak_tetap -> @all_allowance

---
##### Route ID: 212859
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT kode,komponen_upah FROM master_upah_tk
```

---
##### Route ID: 212860
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $komponen_upah1 -> @upah_tk

---
##### Route ID: 212861
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
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@all_allowance[0,0]`

---
##### Route ID: 212862
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
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $test -> @upah_tk (nset)

---
#### Komponen: `load_combo2` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212864
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT field_deskripsi FROM master_allowance 
WHERE field_deskripsi IN @?
```

---
##### Route ID: 212865
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $tunjangan_tetap_periode -> @tunjangan_beda_periode

---
#### Komponen: `load_visible` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212880
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $update_kategori  ["  Kategori Tunjangan"]`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $layout_kategori -> true

---
##### Route ID: 212881
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $layout_kategori -> false

---
##### Route ID: 212882
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $update_bpjs  ["  Kategori BPJS"]`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $layout_bpjs -> true

---
##### Route ID: 212883
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $layout_bpjs -> false

---
##### Route ID: 212884
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $update_gaji  ["  Salary Type"]`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $layout_gaji -> true

---
##### Route ID: 212885
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT kode,komponen_upah FROM master_upah_tk
```

---
##### Route ID: 212886
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $komponen_upah -> @upah_tk

---
##### Route ID: 212887
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT field_deskripsi FROM master_allowance
```

---
##### Route ID: 212888
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbl_tunjangan_tetap -> @all_allowance

---
##### Route ID: 212889
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
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbl_tunjangan_tidak_tetap -> @all_allowance

---
##### Route ID: 212890
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT id,salary_type FROM master_salary_type LIMIT 3
```

---
##### Route ID: 212891
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $salary_type -> @salary_type

---
##### Route ID: 212892
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $layout_gaji -> false

---
##### Route ID: 212893
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $update_manfee  ["  Manajemen Fee"]`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $layout_manajemen_fee -> true

---
##### Route ID: 212894
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $layout_manajemen_fee -> false

---
##### Route ID: 212895
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $update_komponen_project  ["  Komponen Project"]`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $layout_komponen_project -> true

---
##### Route ID: 212896
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $layout_komponen_project -> false

---
##### Route ID: 212897
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $update_metode_pajak  ["  Metode Pajak"]`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $layout_metode_pajak -> true

---
##### Route ID: 212898
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $layout_metode_pajak -> false

---
##### Route ID: 212899
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $update_asuransi  ["  Asuransi"]`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $layout_asuransi -> true

---
##### Route ID: 212900
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $layout_asuransi -> false

---
##### Route ID: 212901
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $update_bpu  ["  BPU"]`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $layout_bpu -> true

---
##### Route ID: 212902
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $layout_bpu -> false

---
##### Route ID: 212903
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $update_nominal  ["  Nominal Tunjangan"]`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $layout_nominal -> true

---
##### Route ID: 212904
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $layout_nominal -> false

---
##### Route ID: 212905
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $nominal_variable -> false

---
##### Route ID: 212906
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $nominal_fix -> false

---
##### Route ID: 212907
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $nominal_daily -> false

---
##### Route ID: 212908
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $allowance -> false

---
##### Route ID: 212909
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
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $salary_type -> Variable

---
##### Route ID: 212910
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT REGEXP_REPLACE(CONCAT(?,',',?,',',?,',',?,',',?,',',?,',',?,',',?,',',?,' '), '["\\[\\]\']', '');
```

---
##### Route ID: 212911
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $komponen_lembur -> @upah_tk

---
##### Route ID: 212912
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $update_workday_type  ["  Work Day"]`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $layout_workday_type -> true

---
##### Route ID: 212913
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $layout_workday_type -> false

---
##### Route ID: 212914
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $update_lembur  ["  Komponen Lembur"]`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $layout_lembur -> true

---
##### Route ID: 212915
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $komponen_lembur -> @upah_tk

---
##### Route ID: 212916
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $layout_lembur -> false

---
##### Route ID: 212917
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT kode,komponen_upah FROM master_upah_tk
```

---
#### Komponen: `load` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212961
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
  "param1": "id",
  "param10": "",
  "param9": "",
  "param8": "",
  "param7": "",
  "param6": ""
}
```

---
##### Route ID: 212962
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $id -> @id(arraydb)

---
##### Route ID: 212963
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT division as Division,
unit_name AS 'Unit Name',
position AS Position,
branch AS Branch,
position AS Position,
workday_type AS 'Work Day',
(CASE WHEN salary_type_id=1 THEN 'Variable'
WHEN salary_type_id=2 THEN 'FIX'
WHEN salary_type_id=3 THEN 'Daily'
WHEN salary_type_id=4 THEN 'Achievment'
END) AS 'Salary Type',
IF(salary_type_id=4,allowance,'') AS 'Allowance',
IF(salary_type_id=4,achivement,'') AS 'Achivment',
format_uang(CASE WHEN salary_type_id=1 THEN variable
WHEN salary_type_id=2 THEN fix
WHEN salary_type_id=3 THEN daily
WHEN salary_type_id=4 THEN achivement*allowance
ELSE 0 END) AS 'Gaji Pokok',
manajemen_fee AS 'Manajemen Fee', 
komponen_project AS 'Komponen Project',
methode_pajak AS 'Metode Pajak',
REGEXP_REPLACE(kategori_tunjangan_tetap, '[\\[\\]()\\"'']', '') AS 'Kategori Tunjangan Tetap',
REGEXP_REPLACE(kategori_tunjangan_tidak_tetap, '[\\[\\]()\\"'']', '') AS 'Kategori Tunjangan Tidak Tetap',
jip_type AS 'BPJS TK Type',
IF(bpjs_ketenagakerjaan=1,'YES','NO') AS 'BPJS Ketenagakerjaan',
IF(bpjs_kesehatan=1,'YES','NO') AS 'BPJS Kesehatan',
-- (SELECT GROUP_CONCAT(komponen_upah) FROM master_upah_tk WHERE kode IN (a.upah_tk)) AS 'Komponen Upah BPJS TK',
CASE WHEN status_asuransi=1 THEN 'Perusahaan'
WHEN status_asuransi=2 THEN 'Karyawan' ELSE '' END AS 'Asuransi ditanggung oleh',
IF(asuransi_kecelakaan=0,'',format_uang(asuransi_kecelakaan)) AS 'Asuransi Kecelakaan',
IF(asuransi_kesehatan=0,'',format_uang(asuransi_kesehatan)) AS 'Asuransi Kesehatan',
IF(bsu=0,'',format_uang(bsu)) AS 'BPU JKK',
IF(bpu_jkm=0,'',format_uang(bpu_jkm)) AS 'BPU JKM',
IF(bpu_jht=0,'',format_uang(bpu_jht)) AS 'BPU JHT',
IF(tunjangan_supervisor=0,'',format_uang(tunjangan_supervisor)) AS 'Tunjangan Supervisor',
IF(tunjangan_jabatan=0,'',format_uang(tunjangan_jabatan)) AS 'Tunjangan Jabatan',
IF(skill_allowance=0,'',format_uang(skill_allowance)) AS 'Skill Allowance',
IF(grading_allowance=0,'',format_uang(grading_allowance)) AS 'Grading Allowance',
IF(montly_allowance=0,'',format_uang(montly_allowance)) AS 'Montly Allowance',
IF(performance_allowance=0,'',format_uang(performance_allowance)) AS 'Performance Allowance',
IF(position_allowance=0,'',format_uang(position_allowance)) AS 'Position Allowance',
IF(bbm=0,'',format_uang(bbm)) AS BBM,
IF(komunikasi=0,'',format_uang(komunikasi)) AS Komunikasi,
IF(transportasi=0,'',format_uang(transportasi)) AS Transportasi,
IF(productivity=0,'',format_uang(productivity)) AS Productivity,
IF(khusus=0,'',format_uang(khusus)) AS 'Tunjangan Khusus',
IF(sewa_laptop=0,'',format_uang(sewa_laptop)) AS 'Sewa Laptop',
IF(makan_meal=0,'',format_uang(makan_meal)) AS 'Makan Meal',
IF(career_allowence=0,'',format_uang(career_allowence)) AS 'Career Allowance',
IF(tunjangan_premium=0,'',format_uang(tunjangan_premium)) AS 'Tunjangan Premium',
IF(kinerja=0,'',format_uang(kinerja)) AS Kinerja,
IF(tunjangan_parkir=0,'',format_uang(tunjangan_parkir)) AS 'Tunjangan Parkir',
IF(tj_kehadiran=0,'',format_uang(tj_kehadiran)) AS 'Tunjangan Kehadiran',
IF(tj_tugas_harian=0,'',format_uang(tj_tugas_harian)) AS 'Tunjangan Tugas Harian',
IF(tunjangan_operasional=0,'',format_uang(tunjangan_operasional)) AS 'Tunjangan Operasional',
IF(tunjangan_sewa_service_motor=0,'',format_uang(tunjangan_sewa_service_motor)) AS 'Tunjangan Sewa Service Motor',
IF(tunjangan_akomodasi=0,'',format_uang(tunjangan_akomodasi)) AS 'Tunjangan Akomodasi',
IF(tunjangan_project=0,'',format_uang(tunjangan_project)) AS 'Tunjangan Project',
IF(spesial_threatment=0,'',format_uang(spesial_threatment)) AS 'Biaya Jasa Training',  
IF(bonus=0,'',format_uang(bonus)) AS Bonus,
IF(insentif_non_upah=0,'',format_uang(insentif_non_upah)) AS Insentif,
IF(lembur_non_upah=0,'',format_uang(lembur_non_upah)) AS Lembur,
IF(productivity_non_upah=0,'',format_uang(productivity_non_upah)) AS Productivity,
IF(tj_kesehatan_non_upah=0,'',format_uang(tj_kesehatan_non_upah)) AS 'Tunjangan Kesehatan',
IF(performance_pay_non_upah=0,'',format_uang(performance_pay_non_upah)) AS 'Performance Pay',
IF(bonus_non_upah=0,'',format_uang(bonus_non_upah)) AS 'Bonus Non Upah',
IF(monthly_comission_non_upah=0,'',format_uang(monthly_comission_non_upah)) AS 'Montly Comission',
IF(shift_allowance_non_upah=0,'',format_uang(shift_allowance_non_upah)) AS 'Shift Allowance',
IF(project_allowance=0,'',format_uang(project_allowance)) AS 'Project Allowance',
IF(tj_kerajinan=0,'',format_uang(tj_kerajinan)) AS 'Tunjangan Kerjinan',
IF(tj_masa_kontrak=0,'',format_uang(tj_masa_kontrak)) AS 'Tunjangan Masa Kontrak',
IF(tj_service_charge=0,'',format_uang(tj_service_charge)) AS 'Tunjangan Service Charge',
IF(tj_surveyor_bengkel=0,'',format_uang(tj_surveyor_bengkel)) AS 'Tunjangan Surveyor Bengkel',
IF(tj_tempat_tinggal=0,'',format_uang(tj_tempat_tinggal)) AS 'Tunjangan Tempat Tinggal'
FROM master_salary a
WHERE id in @?
```

---
##### Route ID: 212964
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`smartgrid`)
- **Parameter:** $grid_data -> @data_client

---
##### Route ID: 212965
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 1 WHERE MONTH(NOW())=1
```

---
##### Route ID: 212966
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @awal_tahun[0,0]  1`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $update_komponen_project -> true

---
##### Route ID: 212967
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 1 
FROM employee_update_data eu 
JOIN master_salary ms 
ON ms.division = eu.division 
and ms.position = eu.position
and ms.unit_name = eu.unit_name 
and ms.employee_type = eu.employee_type
and ms.branch = eu.branch
JOIN buka_tutup_project bt ON bt.periode = CONCAT(DATE_FORMAT(eu.join_date,"%Y"),MONTH(eu.join_date))
WHERE bt.deskripsi='unlock' AND ms.id IN @?
LIMIT 1
```

---
##### Route ID: 212968
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $update_metode_pajak -> true

---
#### Komponen: `res` (Tipe: navresult)
Komponen ini memicu aliran logika berikut:

##### Route ID: 212974
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  update_data`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT GROUP_CONCAT(
    CASE 
        WHEN  data <> '[]'  THEN REGEXP_REPLACE(data, '["\\[\\]\']', '') 
        ELSE NULL 
    END
    SEPARATOR ', '
) AS result
FROM (
    SELECT ? AS data
    UNION ALL
    SELECT ?
    UNION ALL
    SELECT ?
    UNION ALL
    SELECT ?
    UNION ALL
    SELECT ?
   UNION ALL
    SELECT ?
    UNION ALL
    SELECT ?
    UNION ALL
    SELECT ?
    UNION ALL
    SELECT ?
    UNION ALL
    SELECT ?
    UNION ALL
    SELECT ?
  
) AS temp_table;
```

---
##### Route ID: 212975
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@update_komponen_gaji",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{\"0\":\"$id\"},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"2\",\"sqlwhere\":\"id IN @?\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"master_salary\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"achivement\":\"$achievment\",\"fix\":\"$nominal_fix\",\"variable\":\"$nominal_variable\",\"allowance\":\"$nominal_allowance\",\"daily\":\"$nominal_daily\",\"salary_type_id\":\"$salary_type\"},\"callz\":\"\",\"dbmode\":\"update\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 212976
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
UPDATE master_salary SET 
variable=IF(variable='',0,variable),
fix=IF(fix='',0,fix),
daily=IF(daily='',0,daily),
allowance=IF(allowance='',0,allowance)
WHERE id IN @?
```

---
##### Route ID: 212977
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_data_update[0,0] contain Manajemen Fee`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@update_manfee",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{\"0\":\"$id\"},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"2\",\"sqlwhere\":\"id in @?\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"master_salary\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"manajemen_fee\":\"$manajemen_fee\"},\"callz\":\"\",\"dbmode\":\"update\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 212978
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_data_update[0,0] contain Komponen Project`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@update_komponen_project",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{\"0\":\"$id\"},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"2\",\"sqlwhere\":\"id in @?\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"master_salary\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"komponen_project\":\"$komponen_project\"},\"callz\":\"\",\"dbmode\":\"update\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 212979
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_data_update[0,0] contain Metode Pajak`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@update_metode_pajak",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{\"0\":\"$id\"},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"2\",\"sqlwhere\":\"id in @?\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"master_salary\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"methode_pajak\":\"$metode_pajak\"},\"callz\":\"\",\"dbmode\":\"update\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 212980
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_data_update[0,0] contain Kategori BPJS`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@update_kategori_bpjs",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{\"0\":\"$id\"},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"2\",\"sqlwhere\":\"id in @?\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"master_salary\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"upah_tk\":\"$komponen_upah(arrayin)\",\"persen_bpjs_kesehatan\":\"$cb_persen_bpjs_kesehatan\",\"jip_type\":\"$jip_type\",\"komponen_lembur\":\"$komponen_lembur(arrayin)\",\"bpjs_ketenagakerjaan\":\"$bpjs_ketenagakerjaan\"},\"callz\":\"\",\"dbmode\":\"update\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 212981
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_data_update[0,0] contain Asuransi`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@update_komponen_asuransi",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{\"0\":\"$id\"},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"2\",\"sqlwhere\":\"id in @?\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"master_salary\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"status_asuransi\":\"$asuransi\"},\"callz\":\"\",\"dbmode\":\"update\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 212982
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_data_update[0,0] contain BPU`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@update_komponen_bpu",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{\"0\":\"$id\"},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"2\",\"sqlwhere\":\"id in @?\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"master_salary\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{},\"callz\":\"\",\"dbmode\":\"update\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 212983
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_data_update[0,0] contain Kategori Tunjangan`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@update_kategori_tunjangan",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{\"0\":\"$id\"},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"2\",\"sqlwhere\":\"id in @?\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"master_salary\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"kategori_tunjangan_tidak_tetap\":\"$cbl_tunjangan_tidak_tetap(arrayin)\",\"kategori_tunjangan_tetap\":\"$cbl_tunjangan_tetap(arrayin)\"},\"callz\":\"\",\"dbmode\":\"update\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 212984
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
##### Route ID: 212985
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
  "param1": "11"
}
```

---
##### Route ID: 212986
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
##### Route ID: 212987
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_data_update[0,0] contain Salary Type`

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)

---
##### Route ID: 212988
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
UPDATE payroll_buffer pb LEFT JOIN master_salary ms 
ON pb.division = ms.division 
AND pb.unit_name=ms.unit_name
AND pb.position=ms.position
AND pb.branch=ms.branch
AND pb.employee_type = ms.employee_type 
SET pb.manajemen_fee = ms.manajemen_fee
WHERE
pb.approval LIKE "%NEW%" AND ms.id in @?
```

---
##### Route ID: 212989
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
UPDATE payroll_buffer pb LEFT JOIN master_salary ms 
ON pb.division = ms.division 
AND pb.unit_name=ms.unit_name
AND pb.position=ms.position
AND pb.branch=ms.branch
AND pb.employee_type = ms.employee_type 
SET pb.methode_pajak = ms.methode_pajak
WHERE
pb.approval LIKE "%NEW%" AND ms.id in @?
```

---
##### Route ID: 212990
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
UPDATE payroll_buffer pb LEFT JOIN master_salary ms 
ON pb.division = ms.division 
AND pb.unit_name=ms.unit_name
AND pb.position=ms.position
AND pb.branch=ms.branch
AND pb.employee_type = ms.employee_type 
SET pb.upah_tk = ms.upah_tk,
pb.bpjs_kesehatan = ms.bpjs_kesehatan,
pb.bpjs_ketenagakerjaan = ms.bpjs_ketenagakerjaan,
pb.persen_bpjs_kesehatan = ms.persen_bpjs_kesehatan,
pb.jip_type = ms.jip_type
WHERE
pb.approval LIKE "%NEW%" AND ms.id in @?
```

---
##### Route ID: 212991
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
UPDATE payroll_buffer pb LEFT JOIN master_salary ms 
ON pb.division = ms.division 
AND pb.unit_name=ms.unit_name
AND pb.position=ms.position
AND pb.branch=ms.branch
AND pb.employee_type = ms.employee_type 
SET pb.status_asuransi = ms.status_asuransi,
pb.asuransi_kesehatan = ms.asuransi_kesehatan,
pb.asuransi_kecelakaan = ms.asuransi_kecelakaan
WHERE
pb.approval LIKE "%NEW%" AND ms.id in @?
```

---
##### Route ID: 212992
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
UPDATE payroll_buffer pb LEFT JOIN master_salary ms 
ON pb.division = ms.division 
AND pb.unit_name=ms.unit_name
AND pb.position=ms.position
AND pb.branch=ms.branch
AND pb.employee_type = ms.employee_type 
SET pb.bsu = ms.bsu,
pb.bpu_jkm = ms.bpu_jkm,
pb.bpu_jht = ms.bpu_jht
WHERE
pb.approval LIKE "%NEW%" AND ms.id in @?
```

---
##### Route ID: 212993
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
UPDATE payroll_buffer pb LEFT JOIN master_salary ms 
ON pb.division = ms.division 
AND pb.unit_name=ms.unit_name
AND pb.position=ms.position
AND pb.branch=ms.branch
AND pb.employee_type = ms.employee_type 
SET pb.kategori_tunjangan_tetap = ms.kategori_tunjangan_tetap,
pb.kategori_tunjangan_tidak_tetap = ms.kategori_tunjangan_tidak_tetap
WHERE
pb.approval LIKE "%NEW%" AND ms.id in @?
```

---
##### Route ID: 212994
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
UPDATE payroll_buffer pb LEFT JOIN master_salary ms 
ON pb.division = ms.division 
AND pb.unit_name=ms.unit_name
AND pb.position=ms.position
AND pb.branch=ms.branch
AND pb.employee_type = ms.employee_type 
SET pb.payroll_mode = (select salary_mode from master_salary_type where id = ms.salary_type_id),
pb.variable=ms.variable,
pb.fix=ms.fix,
pb.daily=ms.daily,
pb.allowance=ms.allowance,
pb.achivement=ms.achivement
WHERE
pb.approval LIKE "%NEW%" AND ms.id in @?
```

---
##### Route ID: 212995
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
UPDATE payroll_buffer pb LEFT JOIN master_salary ms 
ON pb.division = ms.division 
AND pb.unit_name=ms.unit_name
AND pb.position=ms.position
AND pb.branch=ms.branch
AND pb.employee_type = ms.employee_type 
SET pb.komponen_project = ms.komponen_project
WHERE
pb.approval LIKE "%NEW%" AND ms.id in @?
```

---
##### Route ID: 212996
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+REQUESTCODE  update_data`

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)

---
##### Route ID: 212997
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 1 FROM master_salary WHERE ? LIKE '%Nominal Tunjangan%' LIMIT 1
```

---
##### Route ID: 212998
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 212999
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_update_nominal[0,0] = 1`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Form Baru (`upload_update_client_dialog_v4`)

---
##### Route ID: 213000
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 213001
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 213002
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
UPDATE master_salary SET keterangan=CONCAT('Update pada',?),update_date=NOW(),approval='REQUEST' 
WHERE id in @?
```

---
##### Route ID: 213003
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
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@keterangan[0,0]`

---
##### Route ID: 213004
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 213005
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_data_update[0,0] contain Work Day`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@update_work_day",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{\"0\":\"$id\"},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"2\",\"sqlwhere\":\"id in @?\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"master_salary\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"workday_type\":\"$work_day\"},\"callz\":\"\",\"dbmode\":\"update\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 213006
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_data_update[0,0] contain Komponen Lembur`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@update_kategori_lembur",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{\"0\":\"$id\"},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"2\",\"sqlwhere\":\"id in @?\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"master_salary\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"komponen_lembur\":\"$komponen_lembur(arrayin)\"},\"callz\":\"\",\"dbmode\":\"update\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 213007
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 213008
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 213936
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
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@update_manfee(error)`

---
#### Komponen: `update_upload` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 213009
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT REGEXP_REPLACE(CONCAT(?,',',?,',',?,',',?,',',?,',',?,',',?,',',?,',',?,',',?,' '), '["\\[\\]\']', '');
```

---
##### Route ID: 213010
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_data_update[0,0] = ,,,,,,,, `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Pilih komponen Payroll terlebih dahulu`

---
##### Route ID: 213011
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Perubahan ini setelah diapprove akan berpengaruh pada data employee yang berada dibuket proses payroll, apakah Anda Yakin ingin update data ?`

---
##### Route ID: 213012
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
---

## FORM: `log_history_master_client`

### 1. Validasi & Mandatori Field (Input Components)
- _Tidak ada field mandatori yang didefinisikan secara eksplisit di komponen._

### 2. Business Logic, Queries & Actions
#### Komponen: `load` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 163724
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT DISTINCT 
hs.id AS "ID",
hs.division AS "Division",
hs.unit_name AS "Unit",
hs.position AS "Position",
hs.branch AS "Branch",
hs.employee_type AS "Employee Type",
hs.created_date AS "Created Date",
hs.created_by AS "Created By",hs.`status` AS "Status" 
from history_salary hs LEFT JOIN users u ON u.full_name=hs.created_by 
 WHERE (hs.created_by = ? OR u.leader = ?) @?
 ORDER BY hs.created_date DESC
 limit 100
```

---
##### Route ID: 163725
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`smartgrid`)
- **Parameter:** $grid -> @data_histori_master

---
##### Route ID: 163726
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Staff`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 163727
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  SPV`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 163728
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT DISTINCT 
hs.id AS "ID",
hs.division AS "Division",
hs.unit_name AS "Unit",
hs.position AS "Position",
hs.branch AS "Branch",
hs.employee_type AS "Employee Type",
hs.created_date AS "Created Date",
hs.created_by AS "Created By",
hs.`status` AS "Status" from history_salary hs 
LEFT JOIN users u ON u.full_name=hs.created_by 
 WHERE hs.created_by = ? @?
 ORDER BY hs.created_date DESC
 LIMIT 100
```

---
##### Route ID: 163729
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`smartgrid`)
- **Parameter:** $grid -> @data_histori_master

---
##### Route ID: 163730
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
  "param2": "AND (hs.division LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 163731
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%'",
  "param3": "$search",
  "param2": "OR hs.unit_name LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 163732
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%'",
  "param3": "$search",
  "param2": "OR hs.position LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 163733
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%'",
  "param3": "$search",
  "param2": "OR hs.employee_type LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 163734
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%'",
  "param3": "$search",
  "param2": "OR hs.branch LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 163735
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%'",
  "param3": "$search",
  "param2": "OR hs.total_works LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 163736
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%'",
  "param3": "$search",
  "param2": "OR hs.jip_type LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 163737
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "%')",
  "param3": "$search",
  "param2": " OR monthname(STR_TO_DATE(hs.periode_payroll,'%m')) LIKE '%",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 163738
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
  "param2": "AND hs.division = '",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 163739
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
  "param2": "AND hs.unit_name = '",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 163740
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
  "param2": "AND hs.position = '",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 163741
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
  "param2": "AND hs.branch = '",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 163742
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
  "param2": "AND hs.periode = '",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 163743
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $periode_start <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "''",
  "param3": "$periode_start (fdatedb)",
  "param2": "AND date(hs.periode_start) ='",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 163744
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $periode_end <> `

**Aksi (Action):**
- **Tipe Eksekusi:** `StringManipulationAction` (`concat`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "''",
  "param3": "$periode_end (fdatedb)",
  "param2": " AND date(hs.periode_end) ='",
  "param1": "@search",
  "param9": "",
  "param8": "",
  "param7": "",
  "result": "@search",
  "param6": ""
}
```

---
##### Route ID: 177118
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Manajer`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT DISTINCT 
hs.id AS "ID",
hs.division AS "Division",
hs.unit_name AS "Unit",
hs.position AS "Position",
hs.branch AS "Branch",
hs.employee_type AS "Employee Type",
hs.created_date AS "Created Date",
hs.created_by AS "Created By",
hs.`status` AS "Status" from history_salary hs 
LEFT JOIN users u ON u.full_name=hs.created_by 
 WHERE 1=1 @?
 ORDER BY hs.created_date DESC
 LIMIT 100
```

---
##### Route ID: 177119
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`smartgrid`)
- **Parameter:** $grid -> @data_histori_master

---
##### Route ID: 227540
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
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@search`

---
#### Komponen: `search` (Tipe: text)
Komponen ini memicu aliran logika berikut:

##### Route ID: 163759
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

##### Route ID: 163760
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

##### Route ID: 163761
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT DISTINCT hs.division FROM history_salary hs
LEFT JOIN users u ON u.id = hs.created_by
WHERE (hs.created_by = ? OR u.leader = ?) ORDER BY hs.division ASC
```

---
##### Route ID: 163762
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbx_division -> @division

---
##### Route ID: 163763
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT DISTINCT unit_name FROM history_salary
WHERE division = ? ORDER BY unit_name ASC
```

---
##### Route ID: 163764
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbx_unit -> @unit_name

---
##### Route ID: 163765
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT DISTINCT position FROM history_salary WHERE division = ? AND unit_name = ?
ORDER BY position ASC
```

---
##### Route ID: 163766
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbx_position -> @position

---
##### Route ID: 163767
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT DISTINCT branch FROM history_salary WHERE division = ? AND unit_name = ?
AND position = ?  ORDER BY branch ASC
```

---
##### Route ID: 163768
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbx_branch -> @branch

---
##### Route ID: 177133
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Manajer`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 177134
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  spv`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 177135
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT DISTINCT hs.division FROM history_salary hs
ORDER BY hs.division ASC
```

---
##### Route ID: 177136
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbx_division -> @division

---
##### Route ID: 177137
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT DISTINCT unit_name FROM history_salary
WHERE division = ? ORDER BY unit_name ASC
```

---
##### Route ID: 177138
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbx_unit -> @unit_name

---
##### Route ID: 177139
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT DISTINCT position FROM history_salary WHERE division = ? AND unit_name = ?
ORDER BY position ASC
```

---
##### Route ID: 177140
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbx_position -> @position

---
##### Route ID: 177141
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT DISTINCT branch FROM history_salary WHERE division = ? AND unit_name = ?
AND position = ?  ORDER BY branch ASC
```

---
##### Route ID: 177142
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $cbx_branch -> @branch

---
---

## FORM: `upload_client_dialog_v2`

### 1. Validasi & Mandatori Field (Input Components)
- _Tidak ada field mandatori yang didefinisikan secara eksplisit di komponen._

### 2. Business Logic, Queries & Actions
#### Komponen: `simpan` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 156823
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
##### Route ID: 156824
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** @CURDATE -> @+NOW

---
##### Route ID: 156825
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
##### Route ID: 156826
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 156827
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
##### Route ID: 156828
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

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
##### Route ID: 156829
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
  "param1": "@dt [\"header\"]"
}
```

---
##### Route ID: 156831
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
##### Route ID: 156832
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setenable`)
- **Parameter:** $simpan -> false

---
##### Route ID: 156833
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $info -> Memproses.......

---
##### Route ID: 156834
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
- **Parameter:** $simpan -> true

---
##### Route ID: 156835
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
##### Route ID: 156839
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 156843
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Staff`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@x",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"0\",\"sqlwhere\":\"\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"master_salary\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"tunjangan_parkir\":\"@currns[0,\\\"Tunjangan Parkir\\\"]\",\"achivement\":\"@currns[0,\\\"Achivement\\\"]\",\"career_allowence\":\"@currns[0,\\\"Career Allowence\\\"]\",\"fix\":\"@currns[0,\\\"Fix\\\"]\",\"pay_later_first_transaction\":\"@currns[0,\\\"Pay Later First Transaction\\\"]\",\"kompensasi_hln_non_upah\":\"@currns[0,\\\"Kompensasi HLN\\\"]\",\"sop_reward_non_upah\":\"@currns[0,\\\"SOP Reward\\\"]\",\"variable\":\"@currns[0,\\\"Variable\\\"]\",\"approval\":\"REQUEST\",\"jamsostek_perusahaan\":\"@currns[0,\\\"JHT Perusahaan\\\"]\",\"sewa_laptop\":\"@currns[0,\\\"Sewa Laptop\\\"]\",\"birthday_gift__non_upah\":\"@currns[0,\\\"Birthday Gift\\\"]\",\"daily\":\"@currns[0,\\\"Daily\\\"]\",\"jht_employee\":\"@currns[0,\\\"JHT Employee\\\"]\",\"branch\":\"@currns[0,\\\"Branch\\\"]\",\"unit_name\":\"@currns[0,\\\"Unit\\\"]\",\"jabatan\":\"@currns[0,\\\"Jabatan\\\"]\",\"perdiem_non_upah\":\"@currns[0,\\\"Perdiem\\\"]\",\"kompensasi\":\"@currns[0,\\\"Kompensasi\\\"]\",\"insentif_non_upah\":\"@currns[0,\\\"Insentif\\\"]\",\"position_allowance\":\"@currns[0,\\\"Position Allowance\\\"]\",\"bonus\":\"@currns[0,\\\"Bonus\\\"]\",\"makan_meal\":\"@currns[0,\\\"Makan Meal\\\"]\",\"tunjangan_jabatan\":\"@currns[0,\\\"Tunjangan Jabatan\\\"]\",\"grading_allowance\":\"@currns[0,\\\"Grading Allowance\\\"]\",\"bbm\":\"@currns[0,\\\"BBM\\\"]\",\"shift_allowance_non_upah\":\"@currns[0,\\\"Shift Allowance\\\"]\",\"id_user\":\"@+SESSION-ID\",\"bonus_non_upah\":\"@currns[0,\\\"Bonus Non Upah\\\"]\",\"komunikasi\":\"@currns[0,\\\"Komunikasi\\\"]\",\"jkk_perusahaan\":\"@currns[0,\\\"JKK Perusahaan\\\"]\",\"bpjs_employee\":\"@currns[0,\\\"BPJS Kesehatan Employee\\\"]\",\"monthly_comission_non_upah\":\"@currns[0,\\\"Monthly Commision\\\"]\",\"leader_board_non_upah\":\"@currns[0,\\\"Leaderboard\\\"]\",\"cl_retention\":\"@currns[0,\\\"CL Retention\\\"]\",\"jip_employee\":\"@currns[0,\\\"JIP Employee\\\"]\",\"asuransi_kecelakaan\":\"@currns[0,\\\"Asuransi Kecelakaan\\\"]\",\"workday_type\":\"@currns[0,\\\"Works Days\\\"]\",\"division\":\"@currns[0,\\\"Division\\\"]\",\"spesial_threatment\":\"@currns[0,\\\"Biaya Jasa Training\\\"]\",\"created_date\":\"@+NOW()\",\"performance_allowance\":\"@currns[0,\\\"Performance Allowance\\\"]\",\"bsu\":\"@currns[0,\\\"BPU\\\"]\",\"tunjangan_supervisor\":\"@currns[0,\\\"Tunjangan Supervisor\\\"]\",\"total_works\":\"0\",\"tj_tugas_harian\":\"@currns[0,\\\"Tunjangan Tugas Harian\\\"]\",\"submitted_user\":\"@currns[0,\\\"Submitted User\\\"]\",\"asuransi_kesehatan\":\"@currns[0,\\\"Asuransi Kesehatan\\\"]\",\"manajemen_fee\":\"@currns[0,\\\"Manajemen Fee\\\"]\",\"tj_kehadiran\":\"@currns[0,\\\"Tunjangan Kehadiran\\\"]\",\"position\":\"@currns[0,\\\"Position\\\"]\",\"created_by\":\"@+SESSION-FULL_NAME\",\"tj_kesehatan_non_upah\":\"@currns[0,\\\"TJ Kesehatan\\\"]\",\"jip_perusahaan\":\"@currns[0,\\\"JIP Perusahaan\\\"]\",\"productivity_non_upah\":\"@currns[0,\\\"Productivity\\\"]\",\"tunjangan_premium\":\"@currns[0,\\\"Tunjangan Premium\\\"]\",\"lembur_non_upah\":\"@currns[0,\\\"Lembur\\\"]\",\"cl_first_transaction\":\"@currns[0,\\\"CL First Transaction\\\"]\",\"performance_pay_non_upah\":\"@currns[0,\\\"Performance Pay\\\"]\",\"transportasi\":\"@currns[0,\\\"Transportasi\\\"]\",\"thr\":\"@currns[0,\\\"THR\\\"]\",\"jip_type\":\"@currns[0,\\\"Bpjs TK Type\\\"]\",\"motorcycle_collector_non_upah\":\"@currns[0,\\\"Motorcycle Collector\\\"]\",\"kinerja\":\"@currns[0,\\\"Kinerja\\\"]\",\"allowance\":\"@currns[0,\\\"Allowance\\\"]\",\"skill_allowance\":\"@currns[0,\\\"Skill Allowance\\\"]\",\"productivity\":\"@currns[0,\\\"Productivity\\\"]\",\"montly_allowance\":\"@currns[0,\\\"Montly Allowance\\\"]\",\"khusus\":\"@currns[0,\\\"Khusus\\\"]\",\"employee_type\":\"@currns[0,\\\"Employee Type\\\"]\",\"jkm_perusahaan\":\"@currns[0,\\\"JKM Perusahaan\\\"]\",\"status_asuransi\":\"@currns[0,\\\"Status Asuransi\\\"]\",\"bpjs_perusahaan\":\"@currns[0,\\\"BPJS Kesehatan Perusahaan\\\"]\"},\"callz\":\"\",\"dbmode\":\"insert\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 156844
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @currns[0,"Division"]  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Division can't be empty`

---
##### Route ID: 156845
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
##### Route ID: 156846
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 156847
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @currns [0,"Unit"]  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Unit can't be empty`

---
##### Route ID: 156848
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
##### Route ID: 156849
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 156850
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @currns [0,"Employee Type"]  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Employee Type can't be empty`

---
##### Route ID: 156851
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
##### Route ID: 156852
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 156853
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @currns [0,"Position"]  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Position can't be empty`

---
##### Route ID: 156854
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
##### Route ID: 156855
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 156856
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @currns [0,"Branch"]  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Branch can't be empty`

---
##### Route ID: 156857
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
##### Route ID: 156858
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 156859
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
- **Tipe Eksekusi:** `FormAction` (`closeform`)
- **Detail:**
```json
{
  "param1": ""
}
```

---
##### Route ID: 156860
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
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 156861
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_duplikat[0,0] = 1`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@cek_data_already_exist[0,0]`

---
##### Route ID: 156862
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
##### Route ID: 156863
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
##### Route ID: 156865
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 156869
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
UPDATE master_salary SET 
  variable= IF(variable!= " " OR variable = "-",REPLACE(variable, '.', ''),0),
  fix= IF(fix!= " " OR fix = "-",REPLACE(fix, '.', ''),0),
  daily= IF(daily!= " " OR daily = "-",REPLACE(daily, '.', ''),0),
  bpjs_perusahaan= IF(bpjs_perusahaan!= " ",bpjs_perusahaan,0),
  jip_perusahaan= IF(jip_perusahaan!= " ",jip_perusahaan,0),
  jkm_perusahaan= IF(jkm_perusahaan!= " ",jkm_perusahaan,0),
  jkk_perusahaan= IF(jkk_perusahaan!= " ",jkk_perusahaan,0),
  jamsostek_perusahaan= IF(jamsostek_perusahaan!= " ",jamsostek_perusahaan,0),
  bpjs_employee= IF(bpjs_employee!= " ",bpjs_employee,0),
  jip_employee= IF(jip_employee!= " ",jip_employee,0),
  jht_employee= IF(jht_employee!= " ",jht_employee,0),
  rapelan= IF(rapelan is Null," ",rapelan),
  tj_lain= IF(tj_lain is Null,0,tj_lain),
  tunjangan_project = COALESCE(NULLIF(TRIM(tunjangan_project), ''), 0),
  tunjangan_supervisor = COALESCE(NULLIF(TRIM(tunjangan_supervisor), ''), 0),
  tunjangan_jabatan = COALESCE(NULLIF(TRIM(tunjangan_jabatan), ''), 0),
  skill_allowance = COALESCE(NULLIF(TRIM(skill_allowance), ''), 0),
  grading_allowance = COALESCE(NULLIF(TRIM(grading_allowance), ''), 0),
  montly_allowance = COALESCE(NULLIF(TRIM(montly_allowance), ''), 0),
  performance_allowance = COALESCE(NULLIF(TRIM(performance_allowance), ''), 0),
  position_allowance = COALESCE(NULLIF(TRIM(position_allowance), ''), 0),
  bbm = COALESCE(NULLIF(TRIM(bbm), ''), 0),
  komunikasi = COALESCE(NULLIF(TRIM(komunikasi), ''), 0),
  transportasi = COALESCE(NULLIF(TRIM(transportasi), ''), 0),
  productivity = COALESCE(NULLIF(TRIM(productivity), ''), 0),
  khusus = COALESCE(NULLIF(TRIM(khusus), ''), 0),
  sewa_laptop = COALESCE(NULLIF(TRIM(sewa_laptop), ''), 0),
  makan_meal = COALESCE(NULLIF(TRIM(makan_meal), ''), 0),
  career_allowence = COALESCE(NULLIF(TRIM(career_allowence), ''), 0),
  tunjangan_premium = COALESCE(NULLIF(TRIM(tunjangan_premium), ''), 0),
  kinerja = COALESCE(NULLIF(TRIM(kinerja), ''), 0),
  tunjangan_parkir = COALESCE(NULLIF(TRIM(tunjangan_parkir), ''), 0),
  bonus = COALESCE(NULLIF(TRIM(bonus), ''), 0),
  spesial_threatment = COALESCE(NULLIF(TRIM(spesial_threatment), ''), 0),
  insentif_non_upah = COALESCE(NULLIF(TRIM(insentif_non_upah), ''), 0),
  lembur_non_upah = COALESCE(NULLIF(TRIM(lembur_non_upah), ''), 0),
  tj_kesehatan_non_upah = COALESCE(NULLIF(TRIM(tj_kesehatan_non_upah), ''), 0),
  performance_pay_non_upah = COALESCE(NULLIF(TRIM(performance_pay_non_upah), ''), 0),
  monthly_comission_non_upah = COALESCE(NULLIF(TRIM(monthly_comission_non_upah), ''), 0),
  shift_allowance_non_upah = COALESCE(NULLIF(TRIM(shift_allowance_non_upah), ''), 0),
  tj_kehadiran = COALESCE(NULLIF(TRIM(tj_kehadiran), ''), 0),
  tj_tugas_harian = COALESCE(NULLIF(TRIM(tj_tugas_harian), ''), 0),
  kompensasi = COALESCE(NULLIF(TRIM(kompensasi), ''), 0),
  tunjangan_operasional = COALESCE(NULLIF(TRIM(tunjangan_operasional), ''), 0),
  tunjangan_sewa_service_motor = COALESCE(NULLIF(TRIM(tunjangan_sewa_service_motor), ''), 0),
  tunjangan_akomodasi = COALESCE(NULLIF(TRIM(tunjangan_akomodasi), ''), 0) WHERE created_by = ? AND pic IS NULL
```

---
##### Route ID: 156871
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
UPDATE master_salary SET salary_type_id = CASE 
WHEN variable !=0 AND variable!='' THEN 1
WHEN fix !=0 AND fix!='' THEN 2
WHEN daily !=0 AND daily!='' THEN 3
WHEN allowance !=0 AND allowance !='' THEN 4
WHEN submitted_user !=0 AND submitted_user!='' THEN 5
WHEN cl_first_transaction !=0 AND cl_first_transaction!='' THEN 6
WHEN cl_retention !=0 AND cl_retention!='' THEN 7
WHEN pay_later_first_transaction !=0 AND pay_later_first_transaction!='' THEN 8 END
WHERE DATE_FORMAT(created_date,'%Y-%m-%d')= DATE_FORMAT(NOW(),'%Y-%m-%d') AND approval='REQUEST'
```

---
##### Route ID: 156872
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
- **Tipe Eksekusi:** `SystemAction` (`loop`)
- **Detail:**
```json
{
  "param1": "@dt (rows)"
}
```

---
##### Route ID: 156873
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
- **Tipe Eksekusi:** `DataAction` (`setresult`)
- **Detail:**
```json
{
  "param2": "",
  "param1": "123"
}
```

---
##### Route ID: 156876
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT IF((? REGEXP '^[1-9][0-9]*$' AND ? !='') 
		  OR (? REGEXP '^[1-9][0-9]*$' AND ? !='') 
		  OR (? REGEXP '^[1-9][0-9]*$' AND ? !='')
		  OR (? REGEXP '^[1-9][0-9]*$' AND ? !='')
		 OR (? REGEXP '^[1-9][0-9]*$' AND ? !='')
		 OR (? REGEXP '^[1-9][0-9]*$' AND ? !='')
		 OR (? REGEXP '^[1-9][0-9]*$' AND ? !='')
		 OR (? REGEXP '^[1-9][0-9]*$' AND ? !='')
		 OR (? REGEXP '^[1-9][0-9]*$' AND ? !=''),'',1)
```

---
##### Route ID: 156877
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @gajipokok[0,0] = 1`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Form Baru (`dialog_salary_empty`)

---
##### Route ID: 156878
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 1 FROM master_salary WHERE division = ? AND unit_name = ? AND
employee_type = ? AND position = ? AND branch = ?
```

---
##### Route ID: 156879
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @currns [0,"Bpjs TK Type"]  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `BPJS TK Type can't be empty`

---
##### Route ID: 156880
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
##### Route ID: 156881
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 156882
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT ? !=" " AND ? !=" " AND ? !=" " AND ? !=" " AND ? !=" "
   AND ? !=" " AND ? = 1 AND ? = 1
```

---
##### Route ID: 156883
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 1 WHERE ? = '' AND (? != '' OR ? != '')
```

---
##### Route ID: 156884
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_status_asuransi[0,0]  1`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Status Asuransi can't be empty !`

---
##### Route ID: 156885
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
##### Route ID: 156886
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 156887
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT IF(?=1,"1","0"),IF(?=1,"1","0")
```

---
##### Route ID: 156888
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Staff`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@x",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"0\",\"sqlwhere\":\"\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"master_salary\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"tunjangan_parkir\":\"@currns[0,\\\"Tunjangan Parkir\\\"]\",\"achivement\":\"@currns[0,\\\"Achivement\\\"]\",\"bpu_jkm\":\"@currns[0,\\\"BPU JKM\\\"](int)\",\"career_allowence\":\"@currns[0,\\\"Career Allowence\\\"]\",\"fix\":\"@currns[0,\\\"Fix\\\"]\",\"pay_later_first_transaction\":\"@currns[0,\\\"Pay Later First Transaction\\\"]\",\"kompensasi_hln_non_upah\":\"@currns[0,\\\"Kompensasi HLN\\\"]\",\"sop_reward_non_upah\":\"@currns[0,\\\"SOP Reward\\\"]\",\"variable\":\"@currns[0,\\\"Variable\\\"]\",\"persen_bpjs_kesehatan\":\"@persen_bpjs[0,0]\",\"approval\":\"REQUEST\",\"sewa_laptop\":\"@currns[0,\\\"Sewa Laptop\\\"]\",\"birthday_gift__non_upah\":\"@currns[0,\\\"Birthday Gift\\\"]\",\"daily\":\"@currns[0,\\\"Daily\\\"]\",\"branch\":\"@currns[0,\\\"Branch\\\"]\",\"unit_name\":\"@currns[0,\\\"Unit\\\"]\",\"jabatan\":\"@currns[0,\\\"Jabatan\\\"]\",\"bpjs_ketenagakerjaan\":\"@bpjs_ketenagakerjaan\",\"perdiem_non_upah\":\"@currns[0,\\\"Perdiem\\\"]\",\"methode_pajak\":\"@currns[0,\\\"Methode Pajak\\\"]\",\"kompensasi\":\"@currns[0,\\\"Kompensasi\\\"]\",\"insentif_non_upah\":\"@currns[0,\\\"Insentif\\\"]\",\"tunjangan_beda_periode\":\"$beda_periode\",\"tunjangan_operasional\":\"@currns[0,\\\"Tunjangan Operasional\\\"]\",\"position_allowance\":\"@currns[0,\\\"Position Allowance\\\"]\",\"bonus\":\"@currns[0,\\\"Bonus\\\"]\",\"makan_meal\":\"@currns[0,\\\"Makan Meal\\\"]\",\"tunjangan_jabatan\":\"@currns[0,\\\"Tunjangan Jabatan\\\"]\",\"grading_allowance\":\"@currns[0,\\\"Grading Allowance\\\"]\",\"tunjangan_tetap\":\"$list_tunjangan_beda_periode\",\"bbm\":\"@currns[0,\\\"BBM\\\"]\",\"shift_allowance_non_upah\":\"@currns[0,\\\"Shift Allowance\\\"]\",\"id_user\":\"@+SESSION-ID\",\"bonus_non_upah\":\"@currns[0,\\\"Bonus Non Upah\\\"]\",\"komunikasi\":\"@currns[0,\\\"Komunikasi\\\"]\",\"kategori_tunjangan_tetap\":\"$tunjangan_tetap\",\"bpjs_kesehatan\":\"@bpjs_kesehatan\",\"monthly_comission_non_upah\":\"@currns[0,\\\"Monthly Commision\\\"]\",\"leader_board_non_upah\":\"@currns[0,\\\"Leaderboard\\\"]\",\"bpu_jht\":\"@currns[0,\\\"BPU JHT\\\"](int)\",\"cl_retention\":\"@currns[0,\\\"CL Retention\\\"]\",\"asuransi_kecelakaan\":\"@currns[0,\\\"Asuransi Kecelakaan\\\"]\",\"workday_type\":\"@currns[0,\\\"Works Days\\\"]\",\"division\":\"@currns[0,\\\"Division\\\"]\",\"spesial_threatment\":\"@currns[0,\\\"Biaya Jasa Training\\\"]\",\"created_date\":\"@+NOW()\",\"performance_allowance\":\"@currns[0,\\\"Performance Allowance\\\"]\",\"bsu\":\"@currns[0,\\\"BPU JKK\\\"](int)\",\"tunjangan_supervisor\":\"@currns[0,\\\"Tunjangan Supervisor\\\"]\",\"total_works\":\"0\",\"tj_tugas_harian\":\"@currns[0,\\\"Tunjangan Tugas Harian\\\"]\",\"kategori_tunjangan_tidak_tetap\":\"$tunjangan_tidak_tetap\",\"submitted_user\":\"@currns[0,\\\"Submitted User\\\"]\",\"asuransi_kesehatan\":\"@currns[0,\\\"Asuransi Kesehatan\\\"]\",\"upah_tk\":\"$komponen_upah\",\"tunjangan_akomodasi\":\"@currns[0,\\\"Tunjangan Akomodasi\\\"]\",\"manajemen_fee\":\"@currns[0,\\\"Manajemen Fee\\\"]\",\"tj_kehadiran\":\"@currns[0,\\\"Tunjangan Kehadiran\\\"]\",\"position\":\"@currns[0,\\\"Position\\\"]\",\"tunjangan_sewa_service_motor\":\"@currns[0,\\\"Tunjangan Sewa dan Service Motor\\\"]\",\"created_by\":\"@+SESSION-FULL_NAME\",\"tj_kesehatan_non_upah\":\"@currns[0,\\\"Tunjangan Kesehatan\\\"]\",\"productivity_non_upah\":\"@currns[0,\\\"Productivity\\\"]\",\"tunjangan_premium\":\"@currns[0,\\\"Tunjangan Premium\\\"]\",\"lembur_non_upah\":\"@currns[0,\\\"Lembur\\\"]\",\"cl_first_transaction\":\"@currns[0,\\\"CL First Transaction\\\"]\",\"performance_pay_non_upah\":\"@currns[0,\\\"Performance Pay\\\"]\",\"transportasi\":\"@currns[0,\\\"Transportasi\\\"]\",\"tunjangan_project\":\"@currns[0,\\\"Tunjangan Project\\\"](int)\",\"thr\":\"@currns[0,\\\"THR\\\"]\",\"jip_type\":\"@currns[0,\\\"Bpjs TK Type\\\"]\",\"motorcycle_collector_non_upah\":\"@currns[0,\\\"Motorcycle Collector\\\"]\",\"kinerja\":\"@currns[0,\\\"Kinerja\\\"]\",\"allowance\":\"@currns[0,\\\"Allowance\\\"]\",\"skill_allowance\":\"@currns[0,\\\"Skill Allowance\\\"]\",\"productivity\":\"@currns[0,\\\"Productivity\\\"]\",\"montly_allowance\":\"@currns[0,\\\"Montly Allowance\\\"]\",\"khusus\":\"@currns[0,\\\"Khusus\\\"]\",\"komponen_project\":\"$komponen_project\",\"employee_type\":\"@currns[0,\\\"Employee Type\\\"]\",\"status_asuransi\":\"@currns[0,\\\"Status Asuransi\\\"]\"},\"callz\":\"\",\"dbmode\":\"insert\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 156890
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT MAX(id) FROM master_salary
```

---
##### Route ID: 156891
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@history_client",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"0\",\"sqlwhere\":\"\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"history_salary\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"employee_type\":\"@currns[0,\\\"Employee Type\\\"]\",\"tj_kehadiran\":\"@currns[0,\\\"Tunjangan Kehadiran\\\"](int)\",\"id_parent\":\"@tinggi[0,0]\",\"sewa_laptop\":\"@currns[0,\\\"Sewa Laptop\\\"](int)\",\"grading_allowance\":\"@currns[0,\\\"Grading Allowance\\\"](int)\",\"project_allowance\":\"@currns[0,\\\"Project Allowance\\\"](int)\",\"branch\":\"@currns[0,\\\"Branch\\\"]\",\"khusus\":\"@currns[0,\\\"Tunjangan Khusus\\\"](int)\",\"performance_allowance\":\"@currns[0,\\\"Performance Allowance\\\"](int)\",\"spesial_threatment\":\"@currns[0,\\\"Biaya Jasa Training\\\"](int)\",\"tj_cuti\":\"@currns[0,\\\"Tunjangan Cuti\\\"](int)\",\"lembur_non_upah\":\"@currns[0,\\\"Lembur\\\"](int)\",\"kategori_tunjangan_tetap\":\"$tunjangan_tetap\",\"methode_payroll\":\"@currns[0,\\\"Methode Pajak\\\"]\",\"tunjangan_project\":\"@currns[0,\\\"Tunjangan Project\\\"](int)\",\"tunjangan_jabatan\":\"@currns[0,\\\"Tunjangan Jabatan\\\"](int)\",\"upah_tk\":\"$komponen_upah\",\"created_date\":\"@+NOW()\",\"bpjs_ketenagakerjaan\":\"@currns[0,\\\"BPJS Ketenagakerjaan\\\"]\",\"created_by\":\"@+SESSION-FULL_NAME\",\"salary_type_id\":\"$salary_type\",\"workday_type_id\":\"@currns[0,\\\"Works Days\\\"]\",\"jip_type\":\"@currns[0,\\\"Bpjs TK Type\\\"]\",\"tj_tugas_harian\":\"@currns[0,\\\"Tunjangan Tugas Harian\\\"](int)\",\"tunjangan_supervisor\":\"@currns[0,\\\"Tunjangan Supervisor\\\"](int)\",\"monthly_comission_non_upah\":\"@currns[0,\\\"Monthly Commision\\\"](int)\",\"manajemen_fee\":\"@currns[0,\\\"Manajemen Fee\\\"]\",\"shift_allowance_non_upah\":\"@currns[0,\\\"Shift Allowance\\\"](int)\",\"tj_tempat_tinggal\":\"@currns[0,\\\"Tunjangan Tempat Tinggal\\\"](int)\",\"status_asuransi\":\"@currns[0,\\\"Status Asuransi\\\"]\",\"montly_allowance\":\"@currns[0,\\\"Montly Allowance\\\"](int)\",\"tj_bensin_parkir\":\"@currns[0,\\\"Tunjangan Bensin dan Parkir\\\"](int)\",\"transportasi\":\"@currns[0,\\\"Tunjangan Transportasi\\\"](int)\",\"thr\":\"@currns[0,\\\"THR\\\"](int)\",\"komunikasi\":\"@currns[0,\\\"Tunjangan Komunikasi\\\"](int)\",\"productivity\":\"@currns[0,\\\"Tunjangan Productivity\\\"](int)\",\"career_allowence\":\"@currns[0,\\\"Career Allowence\\\"](int)\",\"allowance\":\"@currns[0,\\\"Allowance\\\"](int)\",\"tunjangan_operasional\":\"@currns[0,\\\"Tunjangan Operasional\\\"](int)\",\"bbm\":\"@currns[0,\\\"Tunjangan Bensin\\\"](int)\",\"skill_allowance\":\"@currns[0,\\\"Skill Allowance\\\"](int)\",\"position_allowance\":\"@currns[0,\\\"Position Allowance\\\"](int)\",\"tj_surveyor_bengkel\":\"@currns[0,\\\"Tunjangan Surveyor Bengkel\\\"](int)\",\"tunjangan_akomodasi\":\"@currns[0,\\\"Tunjangan Akomodasi\\\"](int)\",\"insentif_non_upah\":\"@currns[0,\\\"Insentif\\\"](int)\",\"status\":\"UPLOAD\",\"makan_meal\":\"@currns[0,\\\"Tunjangan Uang Makanl\\\"](int)\",\"tunjangan_parkir\":\"@currns[0,\\\"Tunjangan Parkir\\\"](int)\",\"bonus\":\"@currns[0,\\\"Bonus\\\"](int)\",\"tj_kesehatan_non_upah\":\"@currns[0,\\\"Tunjangan Kesehatan\\\"](int)\",\"fix\":\"@currns[0,\\\"Fix\\\"](int)\",\"tj_masa_kontrak\":\"@currns[0,\\\"Tunjangan Masa Kontrak (Karyawan Layoff)\\\"](int)\",\"kompensasi\":\"@currns[0,\\\"Kompensasi\\\"](int)\",\"total_works\":\"0\",\"tj_service_charge\":\"@currns[0,\\\"Tunjangan Service Charge\\\"](int)\",\"position\":\"@currns[0,\\\"Position\\\"]\",\"variable\":\"@currns[0,\\\"Variable\\\"](int)\",\"performance_pay_non_upah\":\"@currns[0,\\\"Performance Pay\\\"](int)\",\"daily\":\"@currns[0,\\\"Daily\\\"](int)\",\"kategori_tunjangan_tidak_tetap\":\"$tunjangan_tidak_tetap\",\"unit_name\":\"@currns[0,\\\"Unit\\\"]\",\"tj_kerajinan\":\"@currns[0,\\\"Tunjangan Kerajinan\\\"](int)\",\"tunjangan_premium\":\"@currns[0,\\\"Tunjangan Premium\\\"](int)\",\"tunjangan_sewa_service_motor\":\"@currns[0,\\\"Tunjangan Sewa dan Service Motor\\\"](int)\",\"division\":\"@currns[0,\\\"Division\\\"]\",\"kinerja\":\"@currns[0,\\\"Tunjangan Kerja\\\"](int)\"},\"callz\":\"\",\"dbmode\":\"insert\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 156894
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
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@X(error)`

---
##### Route ID: 156895
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 1 FROM master_employee_new 
WHERE Division = ? AND Unit = ? AND Position = ? AND Branch = ?
```

---
##### Route ID: 156896
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_data[0,0]  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Data Client tidak ada, cek kembali division,unit,position,dan branch`

---
##### Route ID: 156897
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
##### Route ID: 156898
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 156899
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 1 FROM master_salary 
WHERE 
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 OR
? !=0 LIMIT 1
```

---
##### Route ID: 156900
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $tunjangan_tetap  ('')`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Pilih list Kategori Tunjangan Tetap dan Tidak Tetap terlebih dahulu`

---
##### Route ID: 156901
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
##### Route ID: 156902
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 156903
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT CASE 
WHEN ?=1 AND ?!='[]' THEN ?
ELSE (IF(? =1 AND ?='[]','["Perusahaan 4% dan Karyawan 1%"]',null)) END
```

---
##### Route ID: 156904
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
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@persen_bpjs[0,0]`

---
##### Route ID: 156905
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
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 156906
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $komponen_upah  ('')`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Komponen Upah BPJS TK can't be empty!`

---
##### Route ID: 156907
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
##### Route ID: 156908
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 156909
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @currns [0,"BPJS Kesehatan"]  1`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Select BPJS percentage first`

---
##### Route ID: 156910
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
##### Route ID: 156911
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 156912
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT
	concat('Division 	: ',?, 
', Unit Name : ', ?, 
', Position 	: ', ?,
', Branch  	: ', ?, 
', Employee Type 	: ', ?,
' Already Exist, Created by ',created_by) 
FROM
	master_salary
WHERE division = ? 
	AND unit_name = ?
	AND position = ? 
	AND branch = ? 
	AND employee_type = ?
```

---
##### Route ID: 156913
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @currns [0,"BPJS Kesehatan"]  `

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** @bpjs_kesehatan -> 0

---
##### Route ID: 156914
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @currns [0,"BPJS Ketenagakerjaan"]  `

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** @bpjs_ketenagakerjaan -> 0

---
##### Route ID: 156915
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @currns [0,"Methode Pajak"]  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Methode Pajak invalid`

---
##### Route ID: 156916
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
##### Route ID: 156917
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 156918
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 1 WHERE ? != 'Variable' AND ? != 'Fix' AND ? != 'None'
```

---
##### Route ID: 156919
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_jip_type[0,0]  1`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Terdapat data yang bpjs tk typenya tidak valid`

---
##### Route ID: 156920
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
##### Route ID: 156921
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 156922
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 1 WHERE ? !='Net' AND ?!='Gross'
```

---
##### Route ID: 156923
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** @bpjs_ketenagakerjaan -> @currns[0,"BPJS Ketenagakerjaan"]

---
##### Route ID: 156924
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** @bpjs_kesehatan -> @currns[0,"BPJS Kesehatan"]

---
##### Route ID: 168432
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 1 FROM master_salary WHERE ?!=0 AND ?!='' AND (? =1 OR ?=1) LIMIT 1
```

---
##### Route ID: 168435
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 168436
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_payroll_mode_daily[0,0]  1`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Data tidak valid karena salary type daily tidak memenuhi kondisi bpjs`

---
##### Route ID: 168437
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
##### Route ID: 168438
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 172787
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $komponen_project  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Komponen Project can't be empty !`

---
##### Route ID: 172788
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
##### Route ID: 172789
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
##### Route ID: 174534
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $persen_bpjs_kesehatan  []`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Select BPJS percentage first`

---
##### Route ID: 174535
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT CASE 
WHEN  ?!='[]' THEN ?
ELSE (IF(?='[]','["Perusahaan 4% dan Karyawan 1%"]',null)) END
```

---
##### Route ID: 174536
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Staff`

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@x",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"0\",\"sqlwhere\":\"\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"master_salary\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"employee_type\":\"@currns[0,\\\"Employee Type\\\"]\",\"tj_kehadiran\":\"@currns[0,\\\"Tunjangan Kehadiran\\\"]\",\"persen_bpjs_kesehatan\":\"@persen_bpjs[0,0]\",\"sewa_laptop\":\"@currns[0,\\\"Sewa Laptop\\\"]\",\"grading_allowance\":\"@currns[0,\\\"Grading Allowance\\\"]\",\"project_allowance\":\"@currns[0,\\\"Project Allowance\\\"](int)\",\"branch\":\"@currns[0,\\\"Branch\\\"]\",\"khusus\":\"@currns[0,\\\"Tunjangan Khusus\\\"]\",\"performance_allowance\":\"@currns[0,\\\"Performance Allowance\\\"]\",\"spesial_threatment\":\"@currns[0,\\\"Biaya Jasa Training\\\"]\",\"tj_cuti\":\"@currns[0,\\\"Tunjangan Cuti\\\"](int)\",\"lembur_non_upah\":\"@currns[0,\\\"Lembur\\\"]\",\"kategori_tunjangan_tetap\":\"$tunjangan_tetap\",\"tunjangan_project\":\"@currns[0,\\\"Tunjangan Project\\\"](int)\",\"tunjangan_jabatan\":\"@currns[0,\\\"Tunjangan Jabatan\\\"]\",\"upah_tk\":\"$komponen_upah\",\"created_date\":\"@+NOW()\",\"bpjs_ketenagakerjaan\":\"@bpjs_ketenagakerjaan\",\"created_by\":\"@+SESSION-FULL_NAME\",\"salary_type_id\":\"$salary_type\",\"jip_type\":\"@currns[0,\\\"Bpjs TK Type\\\"]\",\"tj_tugas_harian\":\"@currns[0,\\\"Tunjangan Tugas Harian\\\"]\",\"tunjangan_supervisor\":\"@currns[0,\\\"Tunjangan Supervisor\\\"]\",\"workday_type\":\"@currns[0,\\\"Works Days\\\"]\",\"tunjangan_beda_periode\":\"$beda_periode\",\"monthly_comission_non_upah\":\"@currns[0,\\\"Monthly Commision\\\"]\",\"manajemen_fee\":\"@currns[0,\\\"Manajemen Fee\\\"]\",\"shift_allowance_non_upah\":\"@currns[0,\\\"Shift Allowance\\\"]\",\"tj_tempat_tinggal\":\"@currns[0,\\\"Tunjangan Tempat Tinggal\\\"](int)\",\"status_asuransi\":\"@currns[0,\\\"Status Asuransi\\\"]\",\"montly_allowance\":\"@currns[0,\\\"Montly Allowance\\\"]\",\"tj_bensin_parkir\":\"@currns[0,\\\"Tunjangan Bensin dan Parkir\\\"](int)\",\"id_user\":\"@+SESSION-ID\",\"transportasi\":\"@currns[0,\\\"Tunjangan Transportasi\\\"]\",\"thr\":\"@currns[0,\\\"THR\\\"]\",\"komunikasi\":\"@currns[0,\\\"Tunjangan Komunikasi\\\"]\",\"productivity\":\"@currns[0,\\\"Tunjangan Productivity\\\"]\",\"career_allowence\":\"@currns[0,\\\"Career Allowence\\\"]\",\"tunjangan_operasional\":\"@currns[0,\\\"Tunjangan Operasional\\\"]\",\"bbm\":\"@currns[0,\\\"Tunjangan Bensin\\\"]\",\"skill_allowance\":\"@currns[0,\\\"Skill Allowance\\\"]\",\"position_allowance\":\"@currns[0,\\\"Position Allowance\\\"]\",\"tj_surveyor_bengkel\":\"@currns[0,\\\"Tunjangan Surveyor Bengkel\\\"](int)\",\"tunjangan_akomodasi\":\"@currns[0,\\\"Tunjangan Akomodasi\\\"]\",\"insentif_non_upah\":\"@currns[0,\\\"Insentif\\\"]\",\"methode_pajak\":\"@currns[0,\\\"Methode Pajak\\\"]\",\"approval\":\"REQUEST\",\"makan_meal\":\"@currns[0,\\\"Tunjangan Uang Makan\\\"]\",\"tunjangan_parkir\":\"@currns[0,\\\"Tunjangan Parkir\\\"]\",\"bonus\":\"@currns[0,\\\"Bonus\\\"]\",\"tj_kesehatan_non_upah\":\"@currns[0,\\\"Tunjangan Kesehatan\\\"]\",\"fix\":\"@currns[0,\\\"Fix\\\"]\",\"tj_masa_kontrak\":\"@currns[0,\\\"Tunjangan Masa Kontrak\\\"](int)\",\"kompensasi\":\"@currns[0,\\\"Kompensasi\\\"]\",\"total_works\":\"0\",\"tj_service_charge\":\"@currns[0,\\\"Tunjangan Service Charge\\\"](int)\",\"position\":\"@currns[0,\\\"Position\\\"]\",\"variable\":\"@currns[0,\\\"Variable\\\"]\",\"performance_pay_non_upah\":\"@currns[0,\\\"Performance Pay\\\"]\",\"daily\":\"@currns[0,\\\"Daily\\\"]\",\"kategori_tunjangan_tidak_tetap\":\"$tunjangan_tidak_tetap\",\"komponen_project\":\"$komponen_project\",\"unit_name\":\"@currns[0,\\\"Unit\\\"]\",\"tj_kerajinan\":\"@currns[0,\\\"Tunjangan Kerajinan\\\"](int)\",\"tunjangan_premium\":\"@currns[0,\\\"Tunjangan Premium\\\"]\",\"tunjangan_tetap\":\"$list_tunjangan_beda_periode\",\"tunjangan_sewa_service_motor\":\"@currns[0,\\\"Tunjangan Sewa dan Service Motor\\\"]\",\"division\":\"@currns[0,\\\"Division\\\"]\",\"kinerja\":\"@currns[0,\\\"Tunjangan Kerja\\\"]\"},\"callz\":\"\",\"dbmode\":\"insert\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 182989
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF $salary_type  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Salary Type can't be empty !`

---
##### Route ID: 182990
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
##### Route ID: 182991
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
##### Route ID: 226802
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
##### Route ID: 227137
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @validasi_karakter[0,0] <> `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@validasi_karakter[0,0]`

---
##### Route ID: 227138
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
##### Route ID: 227139
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 227140
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 'Terdapat kesalahan pada upload master client, nominal yang diinput harus berupa angka' AS validasi
FROM master_salary
WHERE 
    REPLACE(CONCAT(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?),' ','') REGEXP '[^0-9. ]'
LIMIT 1;
```

---
#### Komponen: `load` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 156925
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `DefinitionAction` (`arg`)
- **Detail:**
```json
{
  "param5": "@beda_periode",
  "param4": "@tunjangan_tidak_tetap",
  "param3": "@tunjangan_tetap",
  "param2": "@filename",
  "param1": "@fname",
  "param10": "@komponen_lembur",
  "param9": "@komponen_project",
  "param8": "@komponen_upah_tk",
  "param7": "@persen_bpjs",
  "param6": "@list_beda_periode"
}
```

---
##### Route ID: 156926
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $lbldata -> @fname

---
##### Route ID: 156927
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
##### Route ID: 156928
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $ll -> @filename

---
##### Route ID: 156929
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $beda_periode -> @beda_periode

---
##### Route ID: 156930
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
- **Parameter:** $list_tunjangan_beda_periode -> @list_beda_periode

---
##### Route ID: 156931
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $tunjangan_tetap -> @tunjangan_tetap

---
##### Route ID: 156932
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $tunjangan_tidak_tetap -> @tunjangan_tidak_tetap

---
##### Route ID: 156933
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $persen_bpjs_kesehatan -> @persen_bpjs

---
##### Route ID: 156934
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $komponen_upah -> @komponen_upah_tk

---
##### Route ID: 172783
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $komponen_project -> @komponen_project

---
##### Route ID: 172813
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $komponen_lembur -> @komponen_lembur

---
##### Route ID: 182987
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `DefinitionAction` (`arg`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "",
  "param3": "",
  "param2": "",
  "param1": "@salary_type",
  "param10": "",
  "param9": "",
  "param8": "",
  "param7": "",
  "param6": ""
}
```

---
##### Route ID: 182988
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $salary_type -> @salary_type

---
---

## FORM: `upload_update_client_dialog_v4`

### 1. Validasi & Mandatori Field (Input Components)
- _Tidak ada field mandatori yang didefinisikan secara eksplisit di komponen._

### 2. Business Logic, Queries & Actions
#### Komponen: `simpan` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 213028
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
##### Route ID: 213029
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** @CURDATE -> @+NOW

---
##### Route ID: 213030
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
##### Route ID: 213031
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 213032
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
##### Route ID: 213033
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

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
##### Route ID: 213034
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
  "param1": "@dt [\"header\"]"
}
```

---
##### Route ID: 213035
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
##### Route ID: 213036
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setenable`)
- **Parameter:** $simpan -> false

---
##### Route ID: 213037
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setdata`)
- **Parameter:** $info -> Memproses.......

---
##### Route ID: 213038
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
- **Parameter:** $simpan -> true

---
##### Route ID: 213039
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
##### Route ID: 213040
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 213041
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @currns[0,"Division"]  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Division can't be empty`

---
##### Route ID: 213042
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
##### Route ID: 213043
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 213044
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @currns [0,"Unit"]  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Unit can't be empty`

---
##### Route ID: 213045
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
##### Route ID: 213046
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 213047
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @currns [0,"Employee Type"]  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Employee Type can't be empty`

---
##### Route ID: 213048
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
##### Route ID: 213049
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 213050
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @currns [0,"Position"]  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Position can't be empty`

---
##### Route ID: 213051
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
##### Route ID: 213052
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 213053
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @currns [0,"Branch"]  `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Branch can't be empty`

---
##### Route ID: 213054
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
##### Route ID: 213055
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 213056
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_data[0,0] <> 1`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Data tidak ada disistem`

---
##### Route ID: 213057
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
##### Route ID: 213058
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 213059
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
- **Tipe Eksekusi:** `SystemAction` (`loop`)
- **Detail:**
```json
{
  "param1": "@dt (rows)"
}
```

---
##### Route ID: 213060
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
- **Tipe Eksekusi:** `DataAction` (`setresult`)
- **Detail:**
```json
{
  "param2": "",
  "param1": "123"
}
```

---
##### Route ID: 213061
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
##### Route ID: 213062
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT CONCAT('Update kategori tunjangan terlebih dahulu pada ( ',division,' ',position,' ',branch,' ',employee_type,' ) karena belum sesuai dengan nominal yang ingin diupload')
FROM master_salary 
WHERE 
    ((? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Supervisor%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Supervisor%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Jabatan%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Jabatan%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Skill Allowance%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Skill Allowance%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Grading Allowance%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Grading Allowance%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Monthly Allowance%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Monthly Allowance%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Performance Allowance%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Performance Allowance%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Position Allowance%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Position Allowance%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Bensin%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Bensin%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Komunikasi%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Komunikasi%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Transportasi%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Transportasi%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Productivity%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Productivity%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Khusus%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Khusus%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Sewa Laptop%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Sewa Laptop%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Uang Makan%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Uang Makan%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Career Allowance%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Career Allowance%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Premium%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Premium%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Kerja%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Kerja%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Parkir%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Parkir%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Kehadiran%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Kehadiran%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Tugas Harian%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Tugas Harian%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Operasional%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Operasional%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Sewa dan Service Motor%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Sewa dan Service Motor%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Akomodasi%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Akomodasi%') OR
    (? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Project%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Project%') OR
	(? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Project Allowance%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Project Allowance%') OR
	(? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Cuti%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Cuti%') OR
	(? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Kerajinan%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Kerajinan%') OR
	(? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Masa Kontrak%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Masa Kontrak%') OR
	(? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Service Charge%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Service Charge%') OR
	(? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Surveyor Bengkel%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Surveyor Bengkel%') OR
	(? > 0 AND kategori_tunjangan_tetap NOT LIKE '%Tunjangan Tempat Tinggal%' AND kategori_tunjangan_tidak_tetap NOT LIKE '%Tunjangan Tempat Tinggal%')
	)
	AND division=? AND unit_name = ? AND position = ? AND branch = ? AND employee_type = ?
LIMIT 1
```

---
##### Route ID: 213063
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT ? !=" " AND ? !=" " AND ? !=" " AND ? !=" " AND ? !=" "
   AND ? !=" " AND ? = 1 AND ? = 1
```

---
##### Route ID: 213064
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT IF(?=1,"1","0"),IF(?=1,"1","0")
```

---
##### Route ID: 213065
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@update_nominal",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{\"5\":\"$id\",\"4\":\"@currns[0,\\\"Employee Type\\\"]\",\"3\":\"@currns[0,\\\"Branch\\\"]\",\"2\":\"@currns[0,\\\"Position\\\"]\",\"1\":\"@currns[0,\\\"Unit\\\"]\",\"0\":\"@currns[0,\\\"Division\\\"]\"},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"2\",\"sqlwhere\":\"division = ? AND unit_name = ? AND position = ? AND branch = ? AND employee_type = ? AND id IN @?\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"master_salary\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"tj_kehadiran\":\"@currns[0,\\\"Tunjangan Kehadiran\\\"](int)\",\"sewa_laptop\":\"@currns[0,\\\"Sewa Laptop\\\"](int)\",\"grading_allowance\":\"@currns[0,\\\"Grading Allowance\\\"](int)\",\"project_allowance\":\"@currns[0,\\\"Project Allowance\\\"](int)\",\"khusus\":\"@currns[0,\\\"Tunjangan Khusus\\\"](int)\",\"performance_allowance\":\"@currns[0,\\\"Performance Allowance\\\"](int)\",\"spesial_threatment\":\"@currns[0,\\\"Biaya Jasa Training\\\"](int)\",\"tj_cuti\":\"@currns[0,\\\"Tunjangan Cuti\\\"](int)\",\"lembur_non_upah\":\"@currns[0,\\\"Lembur\\\"](int)\",\"tunjangan_project\":\"@currns[0,\\\"Tunjangan Project\\\"](int)\",\"tunjangan_jabatan\":\"@currns[0,\\\"Tunjangan Jabatan\\\"](int)\",\"tunjangan_supervisor\":\"@currns[0,\\\"Tunjangan Supervisor\\\"](int)\",\"tj_tugas_harian\":\"@currns[0,\\\"Tunjangan Tugas Harian\\\"](int)\",\"monthly_comission_non_upah\":\"@currns[0,\\\"Monthly Commission\\\"](int)\",\"shift_allowance_non_upah\":\"@currns[0,\\\"Shift Allowance\\\"](int)\",\"tj_tempat_tinggal\":\"@currns[0,\\\"Tunjangan Tempat Tinggal\\\"](int)\",\"montly_allowance\":\"@currns[0,\\\"Monthly Allowance\\\"](int)\",\"tj_bensin_parkir\":\"@currns[0,\\\"Tunjangan Bensin dan Parkir\\\"](int)\",\"transportasi\":\"@currns[0,\\\"Tunjangan Transportasi\\\"](int)\",\"thr\":\"@currns[0,\\\"THR\\\"](int)\",\"komunikasi\":\"@currns[0,\\\"Tunjangan Komunikasi\\\"](int)\",\"career_allowence\":\"@currns[0,\\\"Career Allowance\\\"](int)\",\"productivity\":\"@currns[0,\\\"Tunjangan Productivity\\\"](int)\",\"tunjangan_operasional\":\"@currns[0,\\\"Tunjangan Operasional\\\"](int)\",\"bbm\":\"@currns[0,\\\"Tunjangan Bensin\\\"](int)\",\"skill_allowance\":\"@currns[0,\\\"Skill Allowance\\\"](int)\",\"position_allowance\":\"@currns[0,\\\"Position Allowance\\\"](int)\",\"tj_surveyor_bengkel\":\"@currns[0,\\\"Tunjangan Surveyor Bengkel\\\"](int)\",\"insentif_non_upah\":\"@currns[0,\\\"Insentif\\\"](int)\",\"tunjangan_akomodasi\":\"@currns[0,\\\"Tunjangan Akomodasi\\\"](int)\",\"makan_meal\":\"@currns[0,\\\"Tunjangan Uang Makan\\\"](int)\",\"tunjangan_parkir\":\"@currns[0,\\\"Tunjangan Parkir\\\"](int)\",\"bonus\":\"@currns[0,\\\"Bonus\\\"](int)\",\"tj_kesehatan_non_upah\":\"@currns[0,\\\"Tunjangan Kesehatan\\\"](int)\",\"tj_masa_kontrak\":\"@currns[0,\\\"Tunjangan Masa Kontrak\\\"](int)\",\"kompensasi\":\"@currns[0,\\\"Kompensasi\\\"](int)\",\"tj_service_charge\":\"@currns[0,\\\"Tunjangan Service Charge\\\"](int)\",\"performance_pay_non_upah\":\"@currns[0,\\\"Performance Pay\\\"](int)\",\"tj_kerajinan\":\"@currns[0,\\\"Tunjangan Kerajinan\\\"](int)\",\"tunjangan_premium\":\"@currns[0,\\\"Tunjangan Premium\\\"](int)\",\"tunjangan_sewa_service_motor\":\"@currns[0,\\\"Tunjangan Sewa dan Service Motor\\\"](int)\",\"kinerja\":\"@currns[0,\\\"Tunjangan Kerja\\\"](int)\"},\"callz\":\"\",\"dbmode\":\"update\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 213066
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT MAX(id) FROM master_salary
```

---
##### Route ID: 213067
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **Aksi Raw:** `{
  "result": "@history_client",
  "param4": "",
  "param3": "",
  "param2": "{\"paramid\":\"webaction-gen-1\",\"argswhere\":{},\"conn\":\"payroll\",\"where\":{\"paramargs\":{\"parameter2\":\"\",\"parameter1\":\"\"},\"logic\":\"0\",\"type\":\"0\",\"sqlwhere\":\"\",\"param\":{\"parameter2\":\"id\",\"parameter1\":\"id\"}},\"tbl\":\"history_salary\",\"fields\":\"[]\",\"orderby\":{\"customs\":\"\",\"conditionorders\":\"0\",\"orderbys\":\"[]\"},\"fhide\":\"[\\\"\\\"]\",\"args\":{\"employee_type\":\"@currns[0,\\\"Employee Type\\\"]\",\"tj_kehadiran\":\"@currns[0,\\\"Tunjangan Kehadiran\\\"](int)\",\"id_parent\":\"@tinggi[0,0]\",\"sewa_laptop\":\"@currns[0,\\\"Sewa Laptop\\\"](int)\",\"grading_allowance\":\"@currns[0,\\\"Grading Allowance\\\"](int)\",\"branch\":\"@currns[0,\\\"Branch\\\"]\",\"khusus\":\"@currns[0,\\\"Tunjangan Khusus\\\"](int)\",\"performance_allowance\":\"@currns[0,\\\"Performance Allowance\\\"](int)\",\"spesial_threatment\":\"@currns[0,\\\"Biaya Jasa Training\\\"](int)\",\"tj_cuti\":\"@currns[0,\\\"Tunjangan Cuti\\\"](int)\",\"lembur_non_upah\":\"@currns[0,\\\"Lembur\\\"](int)\",\"tunjangan_jabatan\":\"@currns[0,\\\"Tunjangan Jabatan\\\"](int)\",\"created_date\":\"@+NOW()\",\"created_by\":\"@+SESSION-FULL_NAME\",\"workday_type_id\":\"@currns[0,\\\"Works Days\\\"]\",\"tj_tugas_harian\":\"@currns[0,\\\"Tunjangan Tugas Harian\\\"](int)\",\"tunjangan_supervisor\":\"@currns[0,\\\"Tunjangan Supervisor\\\"](int)\",\"monthly_comission_non_upah\":\"@currns[0,\\\"Monthly Commision\\\"](int)\",\"shift_allowance_non_upah\":\"@currns[0,\\\"Shift Allowance\\\"](int)\",\"tj_tempat_tinggal\":\"@currns[0,\\\"Tunjangan Tempat Tinggal\\\"](int)\",\"montly_allowance\":\"@currns[0,\\\"Montly Allowance\\\"](int)\",\"tj_bensin_parkir\":\"@currns[0,\\\"Tunjangan Bensin dan Parkir\\\"](int)\",\"transportasi\":\"@currns[0,\\\"Tunjangan Transportasi\\\"](int)\",\"komunikasi\":\"@currns[0,\\\"Tunjangan Komunikasi\\\"](int)\",\"productivity\":\"@currns[0,\\\"Tunjangan Productivity\\\"](int)\",\"career_allowence\":\"@currns[0,\\\"Career Allowence\\\"](int)\",\"tunjangan_operasional\":\"@currns[0,\\\"Tunjangan Operasional\\\"](int)\",\"bbm\":\"@currns[0,\\\"Tunjangan Bensin\\\"](int)\",\"skill_allowance\":\"@currns[0,\\\"Skill Allowance\\\"](int)\",\"position_allowance\":\"@currns[0,\\\"Position Allowance\\\"](int)\",\"tj_surveyor_bengkel\":\"@currns[0,\\\"Tunjangan Surveyor Bengkel\\\"](int)\",\"tunjangan_akomodasi\":\"@currns[0,\\\"Tunjangan Akomodasi\\\"](int)\",\"insentif_non_upah\":\"@currns[0,\\\"Insentif\\\"](int)\",\"status\":\"UPDATE\",\"makan_meal\":\"@currns[0,\\\"Tunjangan Uang Makan\\\"](int)\",\"tunjangan_parkir\":\"@currns[0,\\\"Tunjangan Parkir\\\"](int)\",\"bonus\":\"@currns[0,\\\"Bonus\\\"](int)\",\"tj_kesehatan_non_upah\":\"@currns[0,\\\"Tunjangan Kesehatan\\\"](int)\",\"tj_masa_kontrak\":\"@currns[0,\\\"Tunjangan Masa Kontrak\\\"](int)\",\"tj_service_charge\":\"@currns[0,\\\"Tunjangan Service Charge\\\"](int)\",\"position\":\"@currns[0,\\\"Position\\\"]\",\"performance_pay_non_upah\":\"@currns[0,\\\"Performance Pay\\\"](int)\",\"unit_name\":\"@currns[0,\\\"Unit\\\"]\",\"tj_kerajinan\":\"@currns[0,\\\"Tunjangan Kerajinan\\\"](int)\",\"tunjangan_premium\":\"@currns[0,\\\"Tunjangan Premium\\\"](int)\",\"tunjangan_sewa_service_motor\":\"@currns[0,\\\"Tunjangan Sewa dan Service Motor\\\"](int)\",\"division\":\"@currns[0,\\\"Division\\\"]\",\"kinerja\":\"@currns[0,\\\"Tunjangan Kerja\\\"](int)\"},\"callz\":\"\",\"dbmode\":\"insert\",\"sql\":\"\"}",
  "param1": ""
}`

---
##### Route ID: 213068
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
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 1 FROM master_salary
WHERE division = ? AND unit_name = ? AND position = ? 
AND branch = ? AND employee_type = ?
```

---
##### Route ID: 213069
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
SELECT 1 FROM master_salary 
WHERE( 
COALESCE(?,0) + COALESCE(?,0) + COALESCE(?,0) + COALESCE(?,0) + COALESCE(?,0) +
COALESCE(?,0) + COALESCE(?,0) + COALESCE(?,0) + COALESCE(?,0) + COALESCE(?,0) +
COALESCE(?,0) + COALESCE(?,0) + COALESCE(?,0) + COALESCE(?,0) + COALESCE(?,0) +
COALESCE(?,0) + COALESCE(?,0) + COALESCE(?,0) + COALESCE(?,0) + COALESCE(?,0) +
COALESCE(?,0) + COALESCE(?,0) + COALESCE(?,0) + COALESCE(?,0) + COALESCE(?,0) +
COALESCE(?,0) + COALESCE(?,0) + COALESCE(?,0) + COALESCE(?,0) + COALESCE(?,0) +
COALESCE(?,0) + COALESCE(?,0) + COALESCE(?,0)
) > 0 AND kategori_tunjangan_tetap="('')" AND kategori_tunjangan_tidak_tetap="('')"
AND division=? AND unit_name=? AND position= ? AND branch=? AND employee_type=?
LIMIT 1
```

---
##### Route ID: 213070
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_kategori_tunjangan[0,0]  1`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `Update list Kategori Tunjangan Tetap dan Tidak Tetap terlebih dahulu`

---
##### Route ID: 213071
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
##### Route ID: 213072
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 213073
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 213074
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Eksekusi Query Database
- **SQL Query:**
```sql
UPDATE payroll_buffer pb
LEFT JOIN master_salary ms ON 
pb.master_salary_id = ms.id
SET 
    pb.tunjangan_supervisor = IF(ms.tunjangan_supervisor != " ", REPLACE(ms.tunjangan_supervisor, '.', ''), 0),
    pb.tunjangan_jabatan = IF(ms.tunjangan_jabatan != " ", REPLACE(ms.tunjangan_jabatan, '.', ''), 0),
    pb.skill_allowance = IF(ms.skill_allowance != " ", REPLACE(ms.skill_allowance, '.', ''), 0),
    pb.grading_allowance = IF(ms.grading_allowance != " ", REPLACE(ms.grading_allowance, '.', ''), 0),
    pb.montly_allowance = IF(ms.montly_allowance != " ", REPLACE(ms.montly_allowance, '.', ''), 0),
    pb.performance_allowance = IF(ms.performance_allowance != " ", REPLACE(ms.performance_allowance, '.', ''), 0),
    pb.position_allowance = IF(ms.position_allowance != " ", REPLACE(ms.position_allowance, '.', ''), 0),
    pb.bbm = IF(ms.bbm != " ", REPLACE(ms.bbm, '.', ''), 0),
    pb.komunikasi = IF(ms.komunikasi != " ", REPLACE(ms.komunikasi, '.', ''), 0),
    pb.transportasi = IF(ms.transportasi != " ", REPLACE(ms.transportasi, '.', ''), 0),
    pb.productivity = IF(ms.productivity != " ", REPLACE(ms.productivity, '.', ''), 0),
    pb.khusus = IF(ms.khusus != " ", REPLACE(ms.khusus, '.', ''), 0),
    pb.sewa_laptop = IF(ms.sewa_laptop != " ", REPLACE(ms.sewa_laptop, '.', ''), 0),
    pb.makan_meal = IF(ms.makan_meal != " ", REPLACE(ms.makan_meal, '.', ''), 0),
    pb.career_allowence = IF(ms.career_allowence != " ", REPLACE(ms.career_allowence, '.', ''), 0),
    pb.tunjangan_premium = IF(ms.tunjangan_premium != " ", REPLACE(ms.tunjangan_premium, '.', ''), 0),
    pb.kinerja = IF(ms.kinerja != " ", REPLACE(ms.kinerja, '.', ''), 0),
    pb.tunjangan_parkir = IF(ms.tunjangan_parkir != " ", REPLACE(ms.tunjangan_parkir, '.', ''), 0),
    pb.tj_kehadiran = IF(ms.tj_kehadiran != '', REPLACE(ms.tj_kehadiran, '.', ''), 0),
    pb.tj_tugas_harian = IF(ms.tj_tugas_harian != '', REPLACE(ms.tj_tugas_harian, '.', ''), 0),
    pb.tunjangan_operasional = IF(ms.tunjangan_operasional != '', REPLACE(ms.tunjangan_operasional, '.', ''), 0),
    pb.tunjangan_sewa_service_motor = IF(ms.tunjangan_sewa_service_motor != '', REPLACE(ms.tunjangan_sewa_service_motor, '.', ''), 0),
    pb.tunjangan_akomodasi = IF(ms.tunjangan_akomodasi != '', REPLACE(ms.tunjangan_akomodasi, '.', ''), 0),
	pb.tunjangan_project = IF(ms.tunjangan_project != '', REPLACE(ms.tunjangan_project, '.', ''), 0),
    pb.spesial_threatment = IF(ms.spesial_threatment != " ", REPLACE(ms.spesial_threatment, '.', ''), 0),		
	pb.bonus = IF(ms.bonus != " ", REPLACE(ms.bonus, '.', ''), 0),
    pb.insentif_non_upah = IF(ms.insentif_non_upah != '', REPLACE(ms.insentif_non_upah, '.', ''), 0),
    pb.lembur_non_upah = IF(ms.lembur_non_upah != '', REPLACE(ms.lembur_non_upah, '.', ''), 0),
    pb.tj_kesehatan_non_upah = IF(ms.tj_kesehatan_non_upah != '', REPLACE(ms.tj_kesehatan_non_upah, '.', ''), 0),
    pb.performance_pay_non_upah = IF(ms.performance_pay_non_upah != '', REPLACE(ms.performance_pay_non_upah, '.', ''), 0),
    pb.monthly_comission_non_upah = IF(ms.monthly_comission_non_upah != '', REPLACE(ms.monthly_comission_non_upah, '.', ''), 0),
    pb.shift_allowance_non_upah = IF(ms.shift_allowance_non_upah != '', REPLACE(ms.shift_allowance_non_upah, '.', ''), 0),
	pb.thr = IF(ms.thr != '', REPLACE(ms.thr, '.', ''), 0),
	pb.kompensasi = IF(ms.kompensasi != '', REPLACE(ms.kompensasi, '.', ''), 0),
	pb.project_allowance = IF(ms.project_allowance != '', REPLACE(ms.project_allowance, '.', ''), 0),
	pb.tj_cuti = IF(ms.tj_cuti != '', REPLACE(ms.tj_cuti, '.', ''), 0),
	pb.tj_kerajinan = IF(ms.tj_kerajinan != '', REPLACE(ms.tj_kerajinan, '.', ''), 0),
	pb.tj_masa_kontrak = IF(ms.tj_masa_kontrak != '', REPLACE(ms.tj_masa_kontrak, '.', ''), 0),
	pb.tj_service_charge = IF(ms.tj_service_charge != '', REPLACE(ms.tj_service_charge, '.', ''), 0),
	pb.tj_surveyor_bengkel = IF(ms.tj_surveyor_bengkel != '', REPLACE(ms.tj_surveyor_bengkel, '.', ''), 0),
	pb.tj_tempat_tinggal = IF(ms.tj_tempat_tinggal != '', REPLACE(ms.tj_tempat_tinggal, '.', ''), 0),
	pb.tj_bensin_parkir = IF(ms.tj_bensin_parkir != '', REPLACE(ms.tj_bensin_parkir, '.', ''), 0)
		
WHERE 
    pb.approval LIKE "%NEW%" AND pb.master_salary_id IN @?
```

---
##### Route ID: 213075
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 213076
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @cek_detail_list_kategori[0,0] <> `

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@cek_detail_list_kategori[0,0]`

---
##### Route ID: 213077
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
##### Route ID: 213078
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `SystemAction` (`break`)

---
##### Route ID: 213079
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
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@update_nominal(error)`

---
##### Route ID: 213935
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
#### Komponen: `load` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 213080
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `DefinitionAction` (`arg`)
- **Detail:**
```json
{
  "param5": "",
  "param4": "",
  "param3": "@id",
  "param2": "@filename",
  "param1": "@fname",
  "param10": "",
  "param9": "",
  "param8": "",
  "param7": "",
  "param6": ""
}
```

---
##### Route ID: 213081
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $lbldata -> @fname

---
##### Route ID: 213082
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
##### Route ID: 213083
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $ll -> @filename

---
##### Route ID: 213084
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
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $tunjangan_tetap -> @tunjangan_tetap

---
##### Route ID: 213085
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
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $tunjangan_tidak_tetap -> @tunjangan_tidak_tetap

---
##### Route ID: 226218
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`settext`)
- **Parameter:** $id -> @id

---
---

## FORM: `dialog_salary_empty`

### 1. Validasi & Mandatori Field (Input Components)
- _Tidak ada field mandatori yang didefinisikan secara eksplisit di komponen._

### 2. Business Logic, Queries & Actions
#### Komponen: `btn_ok` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 163906
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
