# 🤖 BACKEND AI CODING INSTRUCTIONS (PAYPRO V.6)

> [!IMPORTANT]
> **MANDATORY SPEC-KIT FIRST POLICY:**
> Sebelum membuat atau mengubah kode backend, AI **WAJIB** membaca dan mematuhi dokumen di `spec-kit/` sebagai acuan utama.

---

## 📌 Checklist Wajib Backend Developer / AI:
1. **Multi-Tenancy:** Pastikan kolom `company_id` ada di entitas dan disertakan pada setiap query JPA/Native.
2. **Dynamic Components:** Tunjangan/potongan tidak boleh dibuat kolom statis baru; gunakan JSONB `dynamic_components`.
3. **No Buffer / Temp Tables:** File upload massal wajib lewat RabbitMQ.
4. **Security RBAC:** Controller wajib memakai `@PreAuthorize("hasAuthority('...')")`.
5. **Audit Logs:** Mutasi penting wajib dicatat ke tabel `audit_logs`.
6. **Compile Verification:** Wajib verifikasi dengan `mvn clean compile`.
