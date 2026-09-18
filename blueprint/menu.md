# Blueprint Rewrite Spring Boot: Modul `menu`

Dokumen ini berisi kumpulan query, logic, parameter, dan mandatori yang diekstrak langsung dari _low-code engine_ secara dinamis beserta form turunannya. Gunakan dokumen ini sebagai referensi utama saat melakukan rewrite ke Spring Boot (Controller, Service, dan Repository).

---

## FORM: `menu`

### 1. Validasi & Mandatori Field (Input Components)
- _Tidak ada field mandatori yang didefinisikan secara eksplisit di komponen._

### 2. Business Logic, Queries & Actions
#### Komponen: `load` (Tipe: navload)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161352
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
- **Parameter:** $menu_report -> false

---
##### Route ID: 161353
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION = Staff`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $master_bank -> true

---
##### Route ID: 161354
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$pop_mess"
}
```

---
##### Route ID: 161355
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
- **Parameter:** $btn_master_umk_staff -> true

---
##### Route ID: 161356
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
- **Parameter:** $master_client_v2_staff -> true

---
##### Route ID: 161357
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
- **Parameter:** $btn_proses_payroll_v2_staff -> true

---
##### Route ID: 161358
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  SPV`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_master_umk -> true

---
##### Route ID: 161359
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
- **Parameter:** $master_client_v2 -> true

---
##### Route ID: 161360
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
- **Parameter:** $btn_proses_payroll_v2 -> true

---
##### Route ID: 161369
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
- **Parameter:** $master_pic_project -> true

---
##### Route ID: 161371
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
- **Parameter:** $master_pic_uk -> true

---
##### Route ID: 161372
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Staff`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $master_client_staff -> true

---
##### Route ID: 161373
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Staff`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_proses_payroll_staff -> true

---
##### Route ID: 161374
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  SPV`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $master_client -> true

---
##### Route ID: 161375
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  SPV`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_proses_payroll -> true

---
##### Route ID: 171024
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_buku_tutup_kunci -> true

---
##### Route ID: 173055
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_rekap_payroll -> true

---
##### Route ID: 173524
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  PIC BPJS`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 173529
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_bpjs -> true

---
##### Route ID: 173530
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $user_management -> false

---
##### Route ID: 173531
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_payroll -> false

---
##### Route ID: 173532
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_master -> false

---
##### Route ID: 173533
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_pph21 -> false

---
##### Route ID: 173534
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_spt -> false

---
##### Route ID: 173535
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_lembur -> false

---
##### Route ID: 173536
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_pinjaman -> false

---
##### Route ID: 173537
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_uk -> false

---
##### Route ID: 173538
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_report -> true

---
##### Route ID: 173539
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
- **Parameter:** $menu_pph21 -> false

---
##### Route ID: 177174
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Manajer`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 177175
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $user_management -> false

---
##### Route ID: 177176
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $master_pic_project -> false

---
##### Route ID: 177181
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
- **Parameter:** $btn_payroll -> false

---
##### Route ID: 177258
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_lembur -> false

---
##### Route ID: 177259
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_pinjaman -> false

---
##### Route ID: 177260
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_uk -> false

---
##### Route ID: 177261
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_buku_tutup_kunci -> false

---
##### Route ID: 177286
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-PRIVILAGE  Admin`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 177287
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $user_management -> true

---
##### Route ID: 177288
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_master -> false

---
##### Route ID: 177289
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_payroll -> false

---
##### Route ID: 177290
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_pph21 -> false

---
##### Route ID: 177291
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_spt -> false

---
##### Route ID: 177292
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_report -> false

---
##### Route ID: 177293
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_buku_tutup_kunci -> false

---
##### Route ID: 179116
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_bca -> false

---
##### Route ID: 180637
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  SPV(PAYROLL SERVICE)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_master_pic_project_service -> true

---
##### Route ID: 181631
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_bca -> false

---
##### Route ID: 181632
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
- **Parameter:** $menu_bukti_potong -> false

---
##### Route ID: 181633
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_bca -> false

---
##### Route ID: 181642
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  SPV (Kertas Kerja Jasa)`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 181644
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_payroll -> false

---
##### Route ID: 181645
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_spt -> false

---
##### Route ID: 181646
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_lembur -> false

---
##### Route ID: 181647
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_pinjaman -> false

---
##### Route ID: 181648
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_uk -> false

---
##### Route ID: 181649
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_report -> true

---
##### Route ID: 181650
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_buku_tutup_kunci -> false

---
##### Route ID: 181689
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_master_umk -> false

---
##### Route ID: 181690
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_master_ter -> false

---
##### Route ID: 181691
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_master_umk_staff -> false

---
##### Route ID: 181692
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_master_ptkp -> false

---
##### Route ID: 181693
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_master_pkp -> false

---
##### Route ID: 181694
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $master_client_v2 -> false

---
##### Route ID: 181695
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $master_pic_uk -> false

---
##### Route ID: 181696
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $master_signed -> false

---
##### Route ID: 181697
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $master_libur -> false

---
##### Route ID: 181698
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $master_libur_kk_jasa -> true

---
##### Route ID: 181699
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $master_client_v2_staff -> false

---
##### Route ID: 181859
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_master_employee -> false

---
##### Route ID: 181860
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_master_employee_kertas_kerja -> true

---
##### Route ID: 181913
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $master_unit_penempatan -> true

---
##### Route ID: 181914
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $master_posisi -> true

---
##### Route ID: 182096
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_retrieve_kk_jasa -> true

---
##### Route ID: 182097
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_bca -> false

---
##### Route ID: 182613
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_kk_jasa -> true

---
##### Route ID: 182614
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_bpjs -> false

---
##### Route ID: 182615
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
- **Parameter:** $btn_report_pph21 -> false

---
##### Route ID: 182616
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_ebupot -> false

---
##### Route ID: 182617
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_ebupot_a1 -> false

---
##### Route ID: 182618
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_ebupot_uk -> false

---
##### Route ID: 182619
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_perubahan_upah -> false

---
##### Route ID: 182620
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_daftar_tenaga_kerja_masuk -> false

---
##### Route ID: 182621
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_iuran_bpjs -> false

---
##### Route ID: 182622
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_daftar_tenaga_kerja_keluar -> false

---
##### Route ID: 183065
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_payroll_sales -> false

---
##### Route ID: 183066
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_payroll_sales -> false

---
##### Route ID: 183067
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_payroll_sales -> false

---
##### Route ID: 185026
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@+SESSION-POSITION`

---
##### Route ID: 185027
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION = Staff`

**Aksi (Action):**
- **Tipe Eksekusi:** `` (``)

---
##### Route ID: 185028
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
- **Parameter:** $user_management -> false

---
##### Route ID: 185029
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Staff (Sales)`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 185030
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_payroll_sales -> true

---
##### Route ID: 185031
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_payroll -> false

---
##### Route ID: 185032
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @payroll  true`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 185034
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_pph21 -> false

---
##### Route ID: 185036
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_report -> true

---
##### Route ID: 185037
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_master -> false

---
##### Route ID: 185038
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_payroll_sales -> true

---
##### Route ID: 185039
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_lembur -> false

---
##### Route ID: 185040
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_pinjaman -> false

---
##### Route ID: 185041
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_uk -> false

---
##### Route ID: 185042
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_buku_tutup_kunci -> false

---
##### Route ID: 185045
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
- **Parameter:** $menu_report -> true

---
##### Route ID: 185047
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_bca -> false

---
##### Route ID: 185048
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_bpjs -> false

---
##### Route ID: 185049
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_pph21 -> false

---
##### Route ID: 185050
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_ebupot -> false

---
##### Route ID: 185051
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_ebupot_a1 -> false

---
##### Route ID: 185054
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_ebupot_uk -> false

---
##### Route ID: 185055
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_perubahan_upah -> false

---
##### Route ID: 185056
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $report_1721_sales -> true

---
##### Route ID: 185057
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_iuran_bpjs -> false

---
##### Route ID: 185058
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_daftar_tenaga_kerja_masuk -> false

---
##### Route ID: 185059
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_daftar_tenaga_kerja_keluar -> false

---
##### Route ID: 185111
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_spt_pkwt -> false

---
##### Route ID: 185112
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_spt_bulanan -> false

---
##### Route ID: 185113
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_spt_mitra -> false

---
##### Route ID: 185114
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_spt_final -> false

---
##### Route ID: 185244
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_spt_mitra_sales -> true

---
##### Route ID: 186692
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_bca -> true

---
##### Route ID: 186705
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_pph21 -> false

---
##### Route ID: 186706
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_ebupot -> false

---
##### Route ID: 186707
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_ebupot_a1 -> false

---
##### Route ID: 186708
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_ebupot_uk -> false

---
##### Route ID: 186709
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_perubahan_upah -> false

---
##### Route ID: 186710
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_iuran_bpjs -> false

---
##### Route ID: 186711
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_daftar_tenaga_kerja_masuk -> false

---
##### Route ID: 186712
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_daftar_tenaga_kerja_keluar -> false

---
##### Route ID: 191189
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $user_management -> false

---
##### Route ID: 196039
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_bukti_potong -> false

---
##### Route ID: 196794
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
- **Parameter:** $menu_bukti_potong -> false

---
##### Route ID: 197300
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
- **Parameter:** $menu_bukti_potong -> false

---
##### Route ID: 197301
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
- **Parameter:** $report_1721_sales -> false

---
##### Route ID: 197303
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
- **Parameter:** $btn_report_ebupot_pph26 -> false

---
##### Route ID: 197304
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 197307
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  SPV (Sales)`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_buku_tutup_kunci -> true

---
##### Route ID: 197308
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
- **Parameter:** $menu_bukti_potong -> false

---
##### Route ID: 197309
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
- **Parameter:** $btn_report_ebupot_pph26 -> false

---
##### Route ID: 197440
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $bukti_potong_a1 -> false

---
##### Route ID: 197441
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $bukti_potong_bp21_uk -> false

---
##### Route ID: 197442
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $bukti_potong_bp26 -> false

---
##### Route ID: 197443
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $bukti_potong_bp21_uk -> false

---
##### Route ID: 198529
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_bukti_potong -> false

---
##### Route ID: 198530
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_ebupot_pph26 -> false

---
##### Route ID: 198953
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_bukti_potong -> false

---
##### Route ID: 199392
**Kondisi (Expression):**
- Raw: `{
  "result": "@payroll",
  "flag": "",
  "args": {},
  "code": "",
  "class": "",
  "id": "110"
}`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $master_hold_staff -> true

---
##### Route ID: 200997
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  SPV(PAYROLL SERVICE)`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 200998
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_master_service -> true

---
##### Route ID: 200999
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $user_management -> false

---
##### Route ID: 201000
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_master -> false

---
##### Route ID: 201001
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_payroll -> false

---
##### Route ID: 201002
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_bca -> false

---
##### Route ID: 201003
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_pph21 -> false

---
##### Route ID: 201004
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_bukti_potong -> false

---
##### Route ID: 201005
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_lembur -> false

---
##### Route ID: 201006
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_pinjaman -> false

---
##### Route ID: 201007
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_uk -> false

---
##### Route ID: 201008
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_report -> false

---
##### Route ID: 204022
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_payroll_service -> true

---
##### Route ID: 204326
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_buku_tutup_kunci -> true

---
##### Route ID: 204338
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_ebupot_pph26 -> false

---
##### Route ID: 204339
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  PIC BPJS(PAYROLL SERVICE)`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 204340
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 204341
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $user_management -> false

---
##### Route ID: 204342
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_payroll -> false

---
##### Route ID: 204343
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_master -> false

---
##### Route ID: 204344
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_pph21 -> false

---
##### Route ID: 204345
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_spt -> false

---
##### Route ID: 204346
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_lembur -> false

---
##### Route ID: 204347
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_pinjaman -> false

---
##### Route ID: 204348
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_uk -> false

---
##### Route ID: 204349
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_report -> true

---
##### Route ID: 204350
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_bca -> false

---
##### Route ID: 204351
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_payroll_sales -> false

---
##### Route ID: 204352
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_pph21 -> false

---
##### Route ID: 204353
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_ebupot -> false

---
##### Route ID: 204354
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_ebupot_a1 -> false

---
##### Route ID: 204355
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_ebupot_uk -> false

---
##### Route ID: 204356
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_perubahan_upah -> false

---
##### Route ID: 204357
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_iuran_bpjs -> false

---
##### Route ID: 204358
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_daftar_tenaga_kerja_masuk -> false

---
##### Route ID: 204359
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_daftar_tenaga_kerja_keluar -> false

---
##### Route ID: 204360
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_bukti_potong -> false

---
##### Route ID: 204361
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_report_ebupot_pph26 -> false

---
##### Route ID: 204362
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_bpjs -> true

---
##### Route ID: 210627
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Staff`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 210628
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  SPV`

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 210629
**Kondisi (Expression):**
- Raw: `{
  "result": "@payroll",
  "flag": "",
  "args": {},
  "code": "",
  "class": "",
  "id": "110"
}`

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $master_hold -> true

---
##### Route ID: 217016
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_pph21_service -> true

---
##### Route ID: 217801
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_report_pph_service -> true

---
##### Route ID: 223654
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_master_upah_kertas_kerja -> true

---
##### Route ID: 223930
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_pre_payment -> false

---
##### Route ID: 227047
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_pre_payment -> true

---
##### Route ID: 228351
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
- **Parameter:** $dvi_btn_dashboard -> true

---
##### Route ID: 228377
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
- **Parameter:** $dvi_btn_dashboard -> true

---
##### Route ID: 228384
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $btn_dashboard_sales -> true

---
##### Route ID: 228473
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`calllogic`)
- **Detail:**
```json
{
  "param1": "$btn_dashboard_sales"
}
```

---
##### Route ID: 228538
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_denda_potongan -> true

---
##### Route ID: 228954
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $master_salary_employee -> true

---
##### Route ID: 228955
**Kondisi (Expression):** _Tidak ada (Langsung Eksekusi)_

**Aksi (Action):**
- **Tipe Eksekusi:** Set Data UI (`setvisible`)
- **Parameter:** $menu_uk_op -> true

---
#### Komponen: `user` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161376
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_user\"}"
}
```

---
#### Komponen: `btn_master_umk` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161377
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_umk\"}"
}
```

---
#### Komponen: `btn_master_employee` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161378
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_employee\"}"
}
```

---
#### Komponen: `btn_master_bca` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161379
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_bca\"}"
}
```

---
#### Komponen: `master_client` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161380
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_client\"}"
}
```

---
#### Komponen: `btn_employee` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161381
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "master_employee",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"employee\"}"
}
```

---
#### Komponen: `btn_view_employee` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161382
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "view_employee",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"view_employee\"}"
}
```

---
#### Komponen: `btn_kk_jasa` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161383
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"kk_jasa_bca_new\"}"
}
```

---
#### Komponen: `btn_kk_lembur` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161384
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"kk_lembur_bca_1\"}"
}
```

---
#### Komponen: `btn_kk_insentif` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161385
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_bca_insentive\"}"
}
```

---
#### Komponen: `btn_list` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161386
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"list_pph21\"}"
}
```

---
#### Komponen: `btn_report` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161387
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "report",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report\"}"
}
```

---
#### Komponen: `btn_report_insentif` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161388
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"tl_report_insentif\"}"
}
```

---
#### Komponen: `btn_report_lembur` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161389
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"tl_report_lembur\"}"
}
```

---
#### Komponen: `btn_master_salary` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161391
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_salary\"}"
}
```

---
#### Komponen: `master_libur` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161392
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_libur\"}"
}
```

---
#### Komponen: `btn_report_ebupot` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161393
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_ebupot1721\"}"
}
```

---
#### Komponen: `btn_report_bpjs` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161394
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  PIC BPJS`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_bpjs\"}"
}
```

---
##### Route ID: 206754
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  PIC BPJS(PAYROLL SERVICE)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_bpjs_service\"}"
}
```

---
##### Route ID: 211494
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):** _Tidak ada aksi eksplisit_

---
##### Route ID: 211495
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
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_bpjs\"}"
}
```

---
#### Komponen: `btn_special_case` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161395
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"special_case\"}"
}
```

---
#### Komponen: `btn_master_ptkp` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161396
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_ptkp\"}"
}
```

---
#### Komponen: `btn_simulation` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161397
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"simulasi\"}"
}
```

---
#### Komponen: `btn_master_pkp` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161398
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_pkp\"}"
}
```

---
#### Komponen: `btn_payroll_old` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161399
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_payroll\"}"
}
```

---
#### Komponen: `btn_proses_payroll` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161400
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"employee\"}"
}
```

---
#### Komponen: `btn_proses_pph` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161401
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"view_employee\"}"
}
```

---
#### Komponen: `btn_master_umk_staff` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161417
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_umk\"}"
}
```

---
#### Komponen: `master_client_staff` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161418
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_client\"}"
}
```

---
#### Komponen: `btn_proses_payroll_staff` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161419
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"employee\"}"
}
```

---
##### Route ID: 206591
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"employee\"}"
}
```

---
#### Komponen: `btn_change_password` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161420
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"change_password\"}"
}
```

---
#### Komponen: `master_signed` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161421
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_signed\"}"
}
```

---
#### Komponen: `btn_pinjaman` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161422
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"pinjaman\"}"
}
```

---
#### Komponen: `btn_report_spt_pkwt` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161423
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_spt\"}"
}
```

---
#### Komponen: `btn_report_spt_mitra` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161424
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_spt_mitra\"}"
}
```

---
#### Komponen: `btn_report_perubahan_upah` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161425
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_perubahan_upah\"}"
}
```

---
#### Komponen: `btn_report_iuran_bpjs` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161426
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_iuran_bpjs_tenaga_kerja\"}"
}
```

---
#### Komponen: `btn_report_daftar_tenaga_kerja_masuk` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161427
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_daftar_tenaga_kerja_masuk\"}"
}
```

---
#### Komponen: `btn_lembur` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161428
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"lembur\"}"
}
```

---
#### Komponen: `btn_cek_pph` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161429
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"compare_pph21\"}"
}
```

---
#### Komponen: `btn_report_daftar_tenaga_kerja_keluar` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161430
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_daftar_tenaga_kerja_keluar\"}"
}
```

---
#### Komponen: `btn_uang_kompensasi` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161431
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"view_uang_kompensasi_1\"}"
}
```

---
##### Route ID: 208730
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-FULL_NAME  Putri Ramadhan`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"view_uang_kompensasi_1\"}"
}
```

---
#### Komponen: `btn_master_ter` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161432
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_ter\"}"
}
```

---
#### Komponen: `master_pic_project` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161433
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_pic_project\"}"
}
```

---
#### Komponen: `btn_master_em` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161434
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_employee_new\"}"
}
```

---
#### Komponen: `master_pic_uk` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161435
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_pic_uk\"}"
}
```

---
#### Komponen: `btn_proses_payroll_v2` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161436
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"employee_v2\"}"
}
```

---
##### Route ID: 218756
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"employee_v4\"}"
}
```

---
#### Komponen: `btn_proses_payroll_v2_staff` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161437
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"employee_v2\"}"
}
```

---
##### Route ID: 214642
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"employee_v4\"}"
}
```

---
#### Komponen: `btn_proses_pph_v2` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161438
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"view_employee_v2\"}"
}
```

---
##### Route ID: 217802
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"view_employee_v4\"}"
}
```

---
#### Komponen: `master_client_v2` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 207420
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_client_v2\"}"
}
```

---
##### Route ID: 219109
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_client_v4\"}"
}
```

---
#### Komponen: `master_client_v2_staff` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 207421
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_client_v2\"}"
}
```

---
##### Route ID: 212524
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_client_v4\"}"
}
```

---
#### Komponen: `btn_list_v2` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161441
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"list_pph21_v2_byrumus\"}"
}
```

---
#### Komponen: `btn_payroll` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 161442
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_payroll_new\"}"
}
```

---
##### Route ID: 219035
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_payroll_new_v3\"}"
}
```

---
#### Komponen: `btn_report_pph21` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 168465
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_pph\"}"
}
```

---
#### Komponen: `btn_report_spt_bulanan` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 169639
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_spt_bulanan\"}"
}
```

---
#### Komponen: `btn_report_spt_final` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 170737
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_spt_final\"}"
}
```

---
#### Komponen: `btn_buku_tutup_kunci` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 171025
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION <> SPV(PAYROLL SERVICE)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"buka_tutup_kunci\"}"
}
```

---
##### Route ID: 197765
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  SPV (Sales)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"buka_tutup_kunci_sales\"}"
}
```

---
##### Route ID: 204325
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  Staff(PAYROLL SERVICE)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"buka_tutup_kunci_service\"}"
}
```

---
##### Route ID: 226279
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** Menampilkan Pesan / Dialog (Validasi UI)
- **Pesan:** `@+SESSION-POSITION`

---
#### Komponen: `btn_report_ebupot_a1` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 171444
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_ebupot1721a1\"}"
}
```

---
#### Komponen: `btn_report_ebupot_uk` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 176249
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_ebupotfinal\"}"
}
```

---
#### Komponen: `btn_summary` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 180498
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"rekap_bruto_pph\"}"
}
```

---
#### Komponen: `master_libur_kk_jasa` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 181688
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_libur_kk_jasa\"}"
}
```

---
#### Komponen: `btn_master_employee_kertas_kerja` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 181858
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_employee_kertas_kerja\"}"
}
```

---
#### Komponen: `master_unit_penempatan` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 181915
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_unit_penempatan\"}"
}
```

---
#### Komponen: `master_posisi` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 181953
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_posisi\"}"
}
```

---
#### Komponen: `btn_retrieve_kk_jasa` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 182108
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"retrieve_kertas_kerja_jasa_v2\"}"
}
```

---
#### Komponen: `btn_report_kk_jasa` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 182612
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_kk_jasa\"}"
}
```

---
#### Komponen: `btn_retrieve_sales` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 182744
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"retrieve_sales\"}"
}
```

---
#### Komponen: `btn_proses_payroll_sales` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 183069
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"proses_payroll_sales\"}"
}
```

---
#### Komponen: `btn_proses_pph_sales` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 184557
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"proses_pph_sales\"}"
}
```

---
#### Komponen: `btn_list_sales` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 184699
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"list_pph21_sales\"}"
}
```

---
##### Route ID: 187471
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"list_pph21_sales_v2\"}"
}
```

---
#### Komponen: `report_1721_sales` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 185060
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_1721_sales\"}"
}
```

---
#### Komponen: `btn_report_spt_mitra_sales` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 185116
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_spt_mitra_sales\"}"
}
```

---
#### Komponen: `bpjs` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 186704
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"menu_bpjs\"}"
}
```

---
##### Route ID: 204440
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION  PIC BPJS(PAYROLL SERVICE)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"menu_bpjs_service\"}"
}
```

---
#### Komponen: `btn_summary_sales` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 187192
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"rekap_bruto_pph_sales\"}"
}
```

---
#### Komponen: `btn_uk_cadangan` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 194578
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"view_uk_cadangan\"}"
}
```

---
#### Komponen: `bukti_potong_a1` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 195777
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_bpa1\"}"
}
```

---
#### Komponen: `bukti_potong_bp21` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 195778
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`iif`)
- Logika: `IF @+SESSION-POSITION = Staff (Sales)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_bukti_potong21_sales\"}"
}
```

---
##### Route ID: 197565
**Kondisi (Expression):**
- Tipe: `BooleanExpression` (`else`)

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_bukti_potong21\"}"
}
```

---
#### Komponen: `bukti_potong_bp21_uk` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 195779
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_bukti_potong21_uk\"}"
}
```

---
#### Komponen: `btn_report_ebupot_pph26` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 196655
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_ebupot26\"}"
}
```

---
#### Komponen: `bukti_potong_bp26` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 196779
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_bukti_potong26\"}"
}
```

---
#### Komponen: `btn_retrieve_data_uk` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 198450
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"retrieve_uang_kompensasi\"}"
}
```

---
#### Komponen: `master_bank` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 199262
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_bank\"}"
}
```

---
#### Komponen: `master_hold` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 199391
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_hold\"}"
}
```

---
#### Komponen: `btn_master_employee_service` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 201009
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_employee_payroll_service\"}"
}
```

---
#### Komponen: `btn_master_client_service` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 202284
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_client_service\"}"
}
```

---
#### Komponen: `btn_master_pic_project_service` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 203706
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_pic_project_service\"}"
}
```

---
#### Komponen: `btn_retrieve_service` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 204259
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_payroll_service\"}"
}
```

---
#### Komponen: `btn_slip_gaji` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 205008
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"slip_gaji_payroll\"}"
}
```

---
#### Komponen: `btn_slip_gaji_uk` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 205358
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"slip_gaji_payroll_uk\"}"
}
```

---
#### Komponen: `btn_proses_payroll` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 209252
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"proses_payroll_service\"}"
}
```

---
#### Komponen: `master_hold_staff` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 210631
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_hold\"}"
}
```

---
#### Komponen: `btn_proses_pph` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 216639
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"view_employee_service\"}"
}
```

---
#### Komponen: `btn_list_service` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 217508
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"list_pph21_service\"}"
}
```

---
#### Komponen: `btn_simulasi_pph_service` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 217632
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"simulasi_service\"}"
}
```

---
#### Komponen: `btn_summary_pph_service` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 217676
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"rekap_bruto_pph_service\"}"
}
```

---
#### Komponen: `btn_report_bukti_potong_pph21_service` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 217881
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_ebupot1721_service\"}"
}
```

---
#### Komponen: `btn_report_bukti_potong_pph21a1_service` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 217943
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_ebupot1721a1_service\"}"
}
```

---
#### Komponen: `btn_report_bukti_potong_pph26_service` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 217994
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"report_ebupot26_service\"}"
}
```

---
#### Komponen: `btn_get_data_mis` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 219606
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"get_data_sales\"}"
}
```

---
#### Komponen: `btn_validas_data_mis` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 219620
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"validasi_data_mis\"}"
}
```

---
#### Komponen: `btn_retrieve_pre_payment` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 222765
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"retrieve_pre_payment\"}"
}
```

---
#### Komponen: `btn_pre_payment` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 222769
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"pre_payment\"}"
}
```

---
#### Komponen: `btn_master_upah_kertas_kerja` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 223692
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_upah_kertas_kerja\"}"
}
```

---
#### Komponen: `monitoring_master_client` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 225260
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"monitoring_master_client\"}"
}
```

---
#### Komponen: `btn_list_export_leadersales` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 227030
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"list_export_leaders_sales\"}"
}
```

---
#### Komponen: `btn_blast_email_sales` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 227431
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"blast_email_sales\"}"
}
```

---
#### Komponen: `btn_dashboard_sales` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 228472
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"dashboard_sales\"}"
}
```

---
#### Komponen: `btn_denda_potongan` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 228539
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"denda_potongan_sales\"}"
}
```

---
#### Komponen: `retrieve_uk_ops` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 228959
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"retrieve_uang_kompensasi_ops\"}"
}
```

---
#### Komponen: `master_salary_employee` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 228953
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_salary_employee\"}"
}
```

---
#### Komponen: `btn_uk_ops` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 229293
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"view_uang_kompensasi_ops\"}"
}
```

---
#### Komponen: `master_salary_history` (Tipe: button)
Komponen ini memicu aliran logika berikut:

##### Route ID: 229303
**Kondisi (Expression):**
- Logika: `Selalu Jalan (True)`

**Aksi (Action):**
- **Tipe Eksekusi:** `FormAction` (`showwindows`)
- **Detail:**
```json
{
  "param4": "",
  "param3": "",
  "param2": "$kanan",
  "param1": "{\"args\":{},\"formname\":\"master_salary_history\"}"
}
```

---
