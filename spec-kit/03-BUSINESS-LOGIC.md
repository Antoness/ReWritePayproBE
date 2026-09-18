# 🧮 03 - BUSINESS LOGIC & CALCULATION RULES

Dokumen ini berisi rumus baku dan logika kalkulasi payroll, pajak, dan tunjangan sesuai regulasi ketenagakerjaan Indonesia untuk implementasi backend Service.

---

## 1. PPh 21 TER (Peraturan Pemerintah PP 58/2023 & PMK 168/2023)
- **Kategori TER A:** PTKP TK/0 (54 jt), TK/1 (58.5 jt), K/0 (58.5 jt).
- **Kategori TER B:** PTKP TK/2 (63 jt), TK/3 (67.5 jt), K/1 (63 jt), K/2 (67.5 jt).
- **Kategori TER C:** PTKP K/3 (72 jt).
- **Rumus Bulanan (Januari - November):**
  $$\text{PPh 21} = \text{Penghasilan Bruto Sebulan} \times \text{Tarif TER}$$
- **Rumus Masa Pajak Terakhir (Desember / Resign):**
  $$\text{PPh 21 Pasal 17} = (\text{Penghasilan Neto Setahun} - \text{PTKP}) \times \text{Tarif Progresif Pasal 17} - \sum \text{PPh 21 TER Terbayar}$$

---

## 2. Uang Kompensasi PKWT (PP 35/2021)
- **Syarat:** Pekerja PKWT dengan masa kerja minimal 1 bulan terus menerus.
- **Rumus:**
  $$\text{Uang Kompensasi} = \frac{\text{Masa Kerja (Bulan)}}{12} \times 1 \text{ Bulan Upah Pokok + Tunjangan Tetap}$$

---

## 3. BPJS Ketenagakerjaan & Kesehatan
- **BPJS Kesehatan:** 4% Ditanggung Pemberi Kerja (maksimal batas upah Rp 12.000.000), 1% Ditanggung Pekerja.
- **BPJS Ketenagakerjaan:**
  - JKK: 0.24% - 1.74% (Pemberi Kerja)
  - JKM: 0.30% (Pemberi Kerja)
  - JHT: 3.7% (Pemberi Kerja), 2.0% (Pekerja)
  - JP: 2.0% (Pemberi Kerja), 1.0% (Pekerja) — dengan batas upah maksimal tahunan.
