package com.payroll.modules.client;

import lombok.Data;
import java.util.List;

@Data
public class MasterSalaryRequestDTO {
    private String division;
    private String unitName;
    private String position;
    private String employeeType;
    private String branch;
    private String salaryType;
    private Double nominal;
    private String workDays;
    private String bpjsTkType;
    private Double manajemenFee;
    private String metodePajak;
    private String komponenProject;
    
    private String persenBpjsKesehatan;
    private String bpjsKetenagakerjaan;
    private String komponenUpah;
    private String komponenLembur;
    
    private Double biayaJasa;
    private Double training;
    private Double bonus;
    
    private List<String> tunjanganTetap;
    private List<String> tunjanganTidakTetap;
    private Boolean tunjanganBedaPeriode;
    private String tunjanganDetails; // JSON string from frontend
    
    private String ditanggungOleh;
    private Double asuransiKesehatan;
    private Double asuransiKecelakaan;
    
    private Double insentif;
    private Double lembur;
    private Double tunjanganKesehatan;
    private Double performancePay;
    private Double monthlyCommission;
    private Double shiftAllowance;
    private Double thr;
    private Double kompensasi;
}
