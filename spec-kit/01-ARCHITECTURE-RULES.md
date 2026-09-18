# 🚀 PAYPRO V.6 (BACKEND) - MASTER ARCHITECTURE & CODING RULES

Dokumen ini adalah **panduan baku & blueprint arsitektur terlengkap** untuk backend developer dan AI dalam membangun, merawat, dan mengembangkan modul di PayPro agar siap komersial (**B2B SaaS Ready**).

---

## 🏛️ 1. CORE ARCHITECTURE PRINCIPLES
1. **MODULAR MONOLITH:** Seluruh sistem berjalan dalam SATU aplikasi Spring Boot dan SATU database PostgreSQL. Kode dipisahkan secara domain-driven dalam package `com.payroll.modules.<domain>`.
2. **DILARANG MENGGUNAKAN TABEL BUFFER/TEMP:** Dilarang membuat proses `INSERT` ke tabel tampungan sementara. Untuk file upload massal atau integrasi external, lempar payload ke **RabbitMQ** dan proses via `@RabbitListener` di background.
3. **DYNAMIC COMPONENTS BERBASIS JSONB:** Dilarang membuat kolom statis untuk tunjangan/potongan di tabel Payroll. Gunakan satu kolom `dynamic_components` (JSONB) di PostgreSQL.
4. **NO HARDCODED ROLE/POSITION MAPPING:** Dilarang keras melakukan string matching seperti `if (pos.contains("IT"))`. User dihubungkan langsung ke `role_id` pada tabel `roles` dan `role_permissions`.

---

## 🏢 2. MULTI-TENANCY & DATA ISOLATION (B2B SaaS ISOLATION)
Untuk mendukung ratusan perusahaan klien yang berbeda dalam satu database:
1. **Master Company (`companies`):**
   * Entitas `Company` (`com.payroll.modules.company.Company`) mencatat seluruh perusahaan yang berlangganan PayPro.
   * Default Tenant ID: `1` (*PT Danamas Insan Kreasi Andalan*).
2. **Kolom Wajib `company_id`:**
   * Setiap entitas data bisnis (`User`, `Employee`, `PayrollTransaction`, `MasterClient`, `MasterUmk`, dll.) WAJIB memiliki kolom `company_id` (BIGINT / Long).
3. **Injeksi JWT & Filter Scoping:**
   * `JwtTokenProvider` wajib menyertakan claim `"companyId"` saat user login.
   * Setiap query pencarian, update, dan delete wajib memfilter `WHERE company_id = :companyId`.

---

## 🔐 3. DYNAMIC RBAC & METHOD SECURITY
1. **Format Penamaan Permission:**
   * **Menu View:** `MENU_<MODULE_NAME>` (contoh: `MENU_MASTER_UMK`, `MENU_PAYROLL_PROSES`, `MENU_REPORT_BPJS`)
   * **Action CRUD:** `<MODULE>_<ACTION>` (contoh: `UMK_CREATE`, `PAYROLL_APPROVE`, `PAYROLL_REJECT`, `EMPLOYEE_EXPORT`)
2. **Proteksi Method Security:**
   * Seluruh Controller wajib menggunakan `@PreAuthorize("hasAuthority('...')")`.
   * Security Config diaktifkan dengan `@EnableMethodSecurity(prePostEnabled = true)`.
3. **Seeding Otomatis:**
   * Setiap modul/menu baru wajib didaftarkan di method `RoleService.seedDefaultRoles()` agar otomatis tercatat di `menu_permissions`.

---

## 👥 4. UPLINER APPROVAL & WORKFLOW ENGINE (STATE MACHINE)
1. **Siklus Status Transaksi:**
   $$\text{DRAFT} \longrightarrow \text{REQUEST\_APPROVAL} \longrightarrow \text{APPROVED} \longrightarrow \text{PROCESSED} \longrightarrow \text{PAID}$$
   *(Jika ditolak: status beralih ke `REJECTED` dengan field `reason_reject` wajib tercatat)*.
2. **Automatic Downliner Scoping:**
   * Backend secara otomatis memfilter antrean approval berdasarkan relasi `master_upliner` (`nik_upliner = currentUser.nik`).
   * SPV otomatis hanya menerima data yang diajukan oleh bawahan langsungnya.
3. **Dedicated Workflow Controller:**
   * `com.payroll.modules.payroll.PayrollApprovalController` menangani endpoint `/api/v1/payroll/approval/*` (inbox, request, approve, reject).

---

## 🌐 5. NETWORK & STABILITY STANDARDS
1. **Anti-Logout Trap:**
   * Endpoint data unmapped atau kegagalan internal dilarang mengembalikan kode status yang memicu pembersihan token global di frontend.
2. **Audit Logging:**
   * Setiap perubahan data penting wajib mencatat `entity_name`, `entity_id`, `action_type`, `old_values`, `new_values`, `created_by`, dan `created_at` ke tabel `audit_logs`.

---

## 📋 6. END-TO-END DEVELOPER CHECKLIST (BACKEND)
Saat menambahkan modul/fitur baru, ikuti urutan berikut:
- [ ] 1. Buat **Entity** dengan `company_id` dan timestamps audit.
- [ ] 2. Buat **Repository** yang menyertakan filter `company_id`.
- [ ] 3. Daftarkan permission keys di `RoleService.java`.
- [ ] 4. Buat **Service** untuk mengelola business logic & state transitions.
- [ ] 5. Buat **Controller** dengan proteksi `@PreAuthorize`.
- [ ] 6. Pastikan `mvn clean compile` sukses tanpa error.
