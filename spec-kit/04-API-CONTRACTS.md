# 🔌 04 - API CONTRACTS & SPECIFICATIONS

Dokumen ini mendefinisikan standar endpoint RESTful API, otentikasi JWT, pagination, dan daftar rute seluruh modul PayPro V.6.

---

## 1. Global API Standards
- **Base URL:** `/api/v1/`
- **Auth Header:** `Authorization: Bearer <JWT_TOKEN>`
- **Standard Response Envelope:**
```json
{
  "success": true,
  "message": "Operasi berhasil",
  "data": { ... },
  "timestamp": "2026-09-17T10:00:00Z"
}
```

- **Standard Pagination Response:**
```json
{
  "content": [ ... ],
  "page": 0,
  "size": 10,
  "totalElements": 150,
  "totalPages": 15,
  "last": false
}
```

---

## 2. Pemetaan Modul & Endpoint PayPro V.6

### 🔐 1. Auth & Multi-Tenant
- `POST /api/v1/auth/login` : Login user (mengembalikan JWT token dengan claim `companyId`).
- `GET /api/v1/auth/me` : Profile user login dan permissions.
- `GET/POST /api/v1/companies` : Master multi-tenant tenant companies.

### 👥 2. User & Role Management
- `GET/POST/PUT/DELETE /api/v1/users` : Kelola user, NIK, dan password.
- `GET/POST/PUT/DELETE /api/v1/roles` : Role permission dinamis.
- `GET/POST /api/v1/upliner` : Hierarki approval upliner-downliner.

### 📋 3. Master Data
- `GET/POST/PUT/DELETE /api/v1/master-employees` : Data master karyawan, kontrak, & status.
- `GET/POST/PUT/DELETE /api/v1/master-clients` : Konfigurasi client billing.
- `GET/POST/PUT/DELETE /api/v1/master-posisi` : Master jabatan/posisi.
- `GET/POST/PUT/DELETE /api/v1/master-unit-kerja` : Unit kerja penempatan.
- `GET/POST/PUT/DELETE /api/v1/master-umk` : Upah Minimum Kota/Kabupaten.
- `GET/POST/PUT/DELETE /api/v1/master-ptkp` & `master-pkp` : Batasan pajak PTKP & PKP.
- `GET/POST/PUT/DELETE /api/v1/master-ter` : Tabel tarif efektif rata-rata TER.
- `GET/POST/PUT/DELETE /api/v1/master-libur` : Kalender hari libur nasional & cuti bersama.

### 💰 4. Kertas Kerja & Payroll
- `POST /api/v1/kertas-kerja/retrieve` : Penarikan data absensi & kertas kerja jasa.
- `POST /api/v1/payroll/process` : Eksekusi proses kalkulasi payroll bulanan.
- `GET /api/v1/payroll/approval/inbox` : Daftar antrean approval SPV/Manager.
- `POST /api/v1/payroll/approval/approve` : Konfirmasi approval transaksi payroll.
- `POST /api/v1/payroll/approval/reject` : Penolakan payroll dengan catatan reject.

### 💵 5. Kompensasi PKWT & Cadangan
- `POST /api/v1/kompensasi/upload` : Upload data kompensasi PKWT awal.
- `POST /api/v1/kompensasi/upload-calculated` : Upload hasil kalkulasi kompensasi.
- `POST /api/v1/kompensasi/upload-cadangan` : Upload pencadangan UK bulanan.
- `GET /api/v1/kompensasi/slip` : Data slip kompensasi & status approval slip.

### 📊 6. PPh 21, Bukti Potong & Reporting
- `GET /api/v1/pph21/summary` : Ringkasan kalkulasi PPh 21 bulanan.
- `GET /api/v1/pph21/slip-gaji` : Data slip gaji elektronik karyawan.
- `GET /api/v1/bukti-potong/a1` : Bukti Potong Formulir 1721-A1 tahunan.
- `GET /api/v1/bukti-potong/pasal21` : Bukti Potong PPh Pasal 21 tidak final / final.
- `GET /api/v1/reports/pph` & `reports/bpjs` : Laporan rekapitulasi pajak & BPJS.
