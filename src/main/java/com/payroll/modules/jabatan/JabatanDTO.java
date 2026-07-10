package com.payroll.modules.jabatan;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class JabatanDTO {
    private Long id;

    @NotBlank(message = "Kode jabatan tidak boleh kosong")
    private String kodeJabatan;

    @NotBlank(message = "Nama jabatan tidak boleh kosong")
    private String namaJabatan;

    private String deskripsi;

    @NotNull(message = "Gaji pokok tidak boleh kosong")
    @Positive(message = "Gaji pokok harus bernilai positif")
    private Double gajiPokok;
}
