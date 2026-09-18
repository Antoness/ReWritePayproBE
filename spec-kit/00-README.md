# 📦 PAYPRO V.6 (BACKEND) - MASTER SPEC-KIT

Dokumen ini adalah panduan spesifikasi arsitektur, database, dan logika bisnis untuk **Backend PayPro V.6 (Spring Boot & PostgreSQL)**.

---

## 📂 Struktur Dokumen Spec-Kit Backend

| File | Deskripsi & Cakupan |
|---|---|
| [01-ARCHITECTURE-RULES.md](file:///Users/pt-dika/Documents/Documents%20-%20MacBook%20Air%20PT-DIKA%20(2)%20-%201/PAYROLL/PAYPRO%20NEW/backend/spec-kit/01-ARCHITECTURE-RULES.md) | Blueprint arsitektur backend, modular monolith, multi-tenancy `company_id`, dynamic RBAC, RabbitMQ, dan audit logs. |
| [02-DATABASE-SCHEMA.md](file:///Users/pt-dika/Documents/Documents%20-%20MacBook%20Air%20PT-DIKA%20(2)%20-%201/PAYROLL/PAYPRO%20NEW/backend/spec-kit/02-DATABASE-SCHEMA.md) | Kamus data, definisi tabel PostgreSQL, ERD relasi, JSONB schemas, dan indexing. |
| [03-BUSINESS-LOGIC.md](file:///Users/pt-dika/Documents/Documents%20-%20MacBook%20Air%20PT-DIKA%20(2)%20-%201/PAYROLL/PAYPRO%20NEW/backend/spec-kit/03-BUSINESS-LOGIC.md) | Rumus resmi kalkulasi Payroll, PPh 21 TER (PP 58/2023), Uang Kompensasi PKWT (PP 35/2021), BPJS, dan Lembur. |
| [04-API-CONTRACTS.md](file:///Users/pt-dika/Documents/Documents%20-%20MacBook%20Air%20PT-DIKA%20(2)%20-%201/PAYROLL/PAYPRO%20NEW/backend/spec-kit/04-API-CONTRACTS.md) | Standar REST API, format Request/Response JSON, otentikasi JWT, pagination, dan error codes. |

---

## 💡 Panduan untuk AI & Developer
Saat menambahkan modul / endpoint baru di backend:
1. Pastikan entitas memiliki kolom `company_id` untuk isolasi multi-tenant.
2. Gunakan method security `@PreAuthorize("hasAuthority('...')")`.
3. Simpan komponen tunjangan/potongan dinamis pada kolom JSONB `dynamic_components`.
4. Jalankan `mvn clean compile` untuk memverifikasi tidak ada error sintaks.
