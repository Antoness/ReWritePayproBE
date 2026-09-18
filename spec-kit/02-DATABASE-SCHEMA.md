# 🗄️ 02 - DATABASE SCHEMA & DATA DICTIONARY

Dokumen ini mendokumentasikan skema database PostgreSQL, kamus data, relasi tabel, dan struktur JSONB.

---

## 1. Core Multi-Tenant & Auth Tables
- `companies`: Master perusahaan/tenant (`id`, `company_name`, `code`, `is_active`, `created_at`).
- `users`: Data pengguna login (`id`, `company_id`, `nik`, `username`, `password`, `email`, `role_id`, `is_active`).
- `roles` & `role_permissions`: Manajemen hak akses dinamis.
- `master_upliner`: Struktur hierarki approval (`id`, `company_id`, `nik_bawahan`, `nik_upliner`, `level`).

---

## 2. Core Master Tables
- `master_employee`: Data master karyawan & kontrak (`id`, `company_id`, `nik`, `name`, `employee_type`, `division`, `unit_name`, `position`, `branch`, `ptkp_status`, `join_date`, `resign_date`, `status`).
- `master_umk`: Standar upah minimum per kota/cabang.
- `master_client`: Master konfigurasi klien & formula hitungan.
- `master_ter`: Tabel tarif efektif rata-rata (TER PPh 21 Kategori A, B, C).

---

## 3. Transactional Tables
- `payroll_transactions`: Transaksi penggajian bulanan (`id`, `company_id`, `periode_bulan`, `periode_tahun`, `nik`, `gaji_pokok`, `total_tunjangan`, `total_potongan`, `thp`, `status`, `dynamic_components` JSONB).
- `kompensasi_transactions`: Transaksi kompensasi PKWT & cadangan UK.
- `pph_transactions`: Hasil kalkulasi PPh 21 bulanan dan tahunan.
- `audit_logs`: Rekam jejak audit keamanan (`id`, `company_id`, `entity_name`, `entity_id`, `action_type`, `old_values`, `new_values`, `created_by`, `created_at`).
