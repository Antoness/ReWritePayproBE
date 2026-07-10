package com.payroll.modules.employee;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

@Service
public class HrisSyncService {

    private final JdbcTemplate jdbcTemplate;
    private final RestTemplate restTemplate;

    @Value("${hris.api.employee.data}")
    private String dataUrl;

    @Value("${hris.api.employee.contract}")
    private String contractUrl;

    @Value("${hris.api.key}")
    private String apiKey;

    @Value("${hris.api.auth}")
    private String apiAuth;

    public HrisSyncService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.restTemplate = new RestTemplate();
    }

    public void syncHrisData(String username, String labelTanggalUpdate) {
        try {
            // 1. Fetch API Data
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-API-KEY", apiKey);
            headers.set("Authorization", apiAuth);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            System.out.println("Fetching data from HRIS...");
            ResponseEntity<String> dataResponse = restTemplate.exchange(dataUrl, HttpMethod.GET, entity, String.class);
            String dataJson = dataResponse.getBody();
            if(dataJson == null) dataJson = "{}";

            ResponseEntity<String> contractResponse = restTemplate.exchange(contractUrl, HttpMethod.GET, entity, String.class);
            String contractJson = contractResponse.getBody();
            if(contractJson == null) contractJson = "{}";

            // 2. Insert into audit_logs (so we can get Last Update date)
            System.out.println("Inserting to audit_logs for Last Update tracking...");
            jdbcTemplate.update("INSERT INTO audit_logs (action_type, created_by, created_at) VALUES ('SYNC_HRIS', ?, NOW())", username);
            
            // Insert to staging tables if they exist (optional, wrapping in try-catch to avoid crash)
            try {
                System.out.println("Inserting JSON to staging tables...");
                jdbcTemplate.update("INSERT INTO table_json (data_json, get_date, get_by) VALUES (?, NOW(), ?)", dataJson, username);
                jdbcTemplate.update("INSERT INTO table_json_uk (data_json, get_date, get_by) VALUES (?, NOW(), ?)", contractJson, username);
            } catch (Exception e) {
                System.out.println("Warning: staging tables not found, skipping raw json insert: " + e.getMessage());
            }

            // 3. Execute massive queries
            System.out.println("Executing sync SQL scripts...");
            
            jdbcTemplate.execute("TRUNCATE table master_employee_new");
            jdbcTemplate.execute("TRUNCATE table master_contract_employee");

            String insertMasterEmployeeNew = "INSERT INTO master_employee_new (\n" +
                "Address_By_ID, Vendor_Contract_Start_Date, Religion, Education, Bank_Branch, Bank_Account, Bank_Name, \n" +
                "Employee_ID, NIK, Name, Place_Of_Birth, Date_Of_Birth, ID_Number, KK_Number, Marital_Status, Total_Of_Child, \n" +
                "Company_Name, Gender, Last_Position, Batch_Payroll, Salary, NPWP_Number, NPWP_Address, BPJS_Kesehatan, \n" +
                "BP_Jamsostek, BPJS_Pensiun, Asuransi_Kesehatan, Asuransi_Kecelakaan, Branch, Division, Departement, Unit, \n" +
                "Project_Name, Employee_Type, Employee_Type_Group, Position, Product, Product2, Channel, Group_Type, Level, \n" +
                "Sales_Mode, Status, Sign_Date, Join_Date, Join_Date1, Join_Date2, Efective_Date, Position_Date, Movement_Date, \n" +
                "Contract_Start_Date, Contract_End_Date, Contract_Start_Date2, Contract_End_Date2, Contract_Start_Date3, \n" +
                "Contract_End_Date3, Resign_Date, Resign_Date1, Resign_Date2, Resign_Reason, Termination_Reason, \n" +
                "Termination_Reason_Date, Termination_Rules, Termination_Notes, SM_Code, SM_Name, Regno_ID, Data_Type, \n" +
                "Placement_Unit, Posisi_Client, Lokasi_Kerja_Client, Payment_Branch_Code, Payment_RCC, SLID, Standarisasi, \n" +
                "TKAD_Code, TKAD_Name, Nationality, number_of_contract, Created_Date, Created_by, id_json)\n" +
                "SELECT\n" +
                "T.*, NOW(), 'ADMIN' AS CREATE_BY, ID\n" +
                "FROM table_json, \n" +
                "JSON_TABLE(JSON_EXTRACT(data_json, '$.data[*]'), '$[*]' COLUMNS (\n" +
                "  `Address_By_ID` VARCHAR(255) PATH '$.Address_By_ID',\n" +
                "  `Vendor_Contract_Start_Date` DATE PATH '$.Vendor_Contract_Start_Date',\n" +
                "  `Religion` VARCHAR(25) PATH '$.Religion',\n" +
                "  `Education` VARCHAR(25) PATH '$.Education',\n" +
                "  `Bank_Branch` VARCHAR(100) PATH '$.Bank_Branch',\n" +
                "  `Bank_Account` VARCHAR(100) PATH '$.Bank_Account',\n" +
                "  `Bank_Name` VARCHAR(100) PATH '$.Bank_Name',\n" +
                "  `Employee_ID` BIGINT PATH '$.Employee_ID',\n" +
                "  `NIK` VARCHAR(12) PATH '$.NIK',\n" +
                "  `Name` VARCHAR(50) PATH '$.Name',\n" +
                "  `Place_Of_Birth` VARCHAR(30) PATH '$.Place_Of_Birth',\n" +
                "  `Date_Of_Birth` DATE PATH '$.Date_Of_Birth',\n" +
                "  `ID_Number` VARCHAR(50) PATH '$.ID_Number',\n" +
                "  `KK_Number` VARCHAR(20) PATH '$.KK_Number',\n" +
                "  `Marital_Status` VARCHAR(10) path '$.Marital_Status',\n" +
                "  `Total_Of_Child` INT PATH '$.Total_Of_Child',\n" +
                "  `Company_Name` VARCHAR(50) PATH '$.Company_Name',\n" +
                "  `Gender` VARCHAR(20) PATH '$.Gender',\n" +
                "  `Last_Position` VARCHAR(50) PATH '$.Last_Position',\n" +
                "  `Batch_Payroll` VARCHAR(2) PATH '$.Batch_Payroll',\n" +
                "  `Salary` INT PATH '$.Salary',\n" +
                "  `NPWP_Number` VARCHAR(30) PATH '$.NPWP_Number',\n" +
                "  `NPWP_Address` VARCHAR(255) PATH '$.NPWP_Address',\n" +
                "  `BPJS_Kesehatan` VARCHAR(20) PATH '$.BPJS_Kesehatan',\n" +
                "  `BP_Jamsostek` VARCHAR(20) PATH '$.BP_Jamsostek',\n" +
                "  `BPJS_Pensiun` VARCHAR(20) PATH '$.BPJS_Pensiun',\n" +
                "  `Asuransi_Kesehatan` VARCHAR(20) PATH '$.Asuransi_Kesehatan',\n" +
                "  `Asuransi_Kecelakaan` VARCHAR(20) PATH '$.Asuransi_Kecelakaan',\n" +
                "  `Branch` VARCHAR(255) PATH '$.Branch',\n" +
                "  `Division` VARCHAR(255) PATH '$.Division',\n" +
                "  `Departement` VARCHAR(255) PATH '$.Departement',\n" +
                "  `Unit` VARCHAR(255) PATH '$.Unit',\n" +
                "  `Project_Name` VARCHAR(30) PATH '$.Project_Name',\n" +
                "  `Employee_Type` VARCHAR(30) PATH '$.Employee_Type',\n" +
                "  `Employee_Type_Group` VARCHAR(30) PATH '$.Employee_Type_Group',\n" +
                "  `Position` VARCHAR(50) PATH '$.Position',\n" +
                "  `Product` VARCHAR(30) PATH '$.Product',\n" +
                "  `Product2` VARCHAR(30) PATH '$.Product2',\n" +
                "  `Channel` VARCHAR(30) PATH '$.Channel',\n" +
                "  `Group_Type` VARCHAR(30) PATH '$.Group_Type',\n" +
                "  `Level` VARCHAR(30) PATH '$.Level',\n" +
                "  `Sales_Mode` VARCHAR(30) PATH '$.Sales_Mode',\n" +
                "  `Status` VARCHAR(30) PATH '$.Status',\n" +
                "  `Sign_Date` DATE PATH '$.Sign_Date',\n" +
                "  `Join_Date` DATE PATH '$.Join_Date',\n" +
                "  `Join_Date1` DATE PATH '$.Join_Date1',\n" +
                "  `Join_Date2` DATE PATH '$.Join_Date2',\n" +
                "  `Efective_Date` DATE PATH '$.Efective_Date',\n" +
                "  `Position_Date` DATE PATH '$.Postion_Date',\n" +
                "  `Movement_Date` DATE PATH '$.Movement_Date',\n" +
                "  `Contract_Start_Date` DATE PATH '$.Contract_Start_Date',\n" +
                "  `Contract_End_Date` DATE PATH '$.Contract_End_Date',\n" +
                "  `Contract_Start_Date2` DATE PATH '$.Contract_Start_Date2',\n" +
                "  `Contract_End_Date2` DATE PATH '$.Contract_End_Date2',\n" +
                "  `Contract_Start_Date3` DATE PATH '$.Contract_Start_Date3',\n" +
                "  `Contract_End_Date3` DATE PATH '$.Contract_End_Date3',\n" +
                "  `Resign_Date` DATE PATH '$.Resign_Date',\n" +
                "  `Resign_Date1` DATE PATH '$.Resign_Date1',\n" +
                "  `Resign_Date2` DATE PATH '$.Resign_Date2',\n" +
                "  `Resign_Reason` TEXT PATH '$.Resign_Reason',\n" +
                "  `Termination_Reason` VARCHAR(255) PATH '$.Termintaion_Date',\n" +
                "  `Termination_Reason_Date` DATE PATH '$.Termination_Reason_Date',\n" +
                "  `Termination_Rules` VARCHAR(255) PATH '$.Termination_Rules',\n" +
                "  `Termination_Notes` TEXT PATH '$.Termination_Notes',\n" +
                "  `SM_Code` VARCHAR(12) PATH '$.SM_Code',\n" +
                "  `SM_Name` VARCHAR(50) PATH '$.SM_Name',\n" +
                "  `Regno_ID` VARCHAR(100) PATH '$.Regno_ID',\n" +
                "  `Data_Type` VARCHAR(20) PATH '$.Data_Type',\n" +
                "  `Placement_Unit` VARCHAR(200) PATH '$.Placement_Unit',\n" +
                "  `Posisi_Client` VARCHAR(200) PATH '$.Posisi_Client',\n" +
                "  `Lokasi_Kerja_Client` VARCHAR(200) PATH '$.Lokasi_Kerja_Client',\n" +
                "  `Payment_Branch_Code` VARCHAR(100) PATH '$.Payment_Branch_Code',\n" +
                "  `Payment_RCC` VARCHAR(100) PATH '$.Payment_RCC',\n" +
                "  `SLID` VARCHAR(100) PATH '$.SLID',\n" +
                "  `Standarisasi` VARCHAR(100) PATH '$.Standarisasi',\n" +
                "  `TKAD_Code` VARCHAR(100) PATH '$.TKAD_Code',\n" +
                "  `TKAD_Name` VARCHAR(100) PATH '$.TKAD_Name',\n" +
                "  `Nationality` VARCHAR(5) PATH '$.Nationality',\n" +
                "  `Number_Of_Contract` VARCHAR(4) PATH '$.Number_Of_Contract'\n" +
                ")) AS T\n" +
                "WHERE ID = (SELECT MAX(ID) FROM table_json)\n" +
                "GROUP BY `Division`, `Unit`, `Position`, `Branch`, `Employee_Type`";
            
            jdbcTemplate.execute(insertMasterEmployeeNew);

            String insertContract = "INSERT INTO master_contract_employee (\n" +
                "    Employee_id_user, NIK_Contract, Name_Contract, Contract_Start, Contract_End, \n" +
                "    Number_Of_Contract, created_date, created_by\n" +
                ")\n" +
                "SELECT emp.Employee_ID, c.NIK_Contract, c.Name_Contract, c.Contract_Start, c.Contract_End, c.kontrak_ke, NOW(), 'ADMIN'\n" +
                "FROM table_json_uk j, \n" +
                "JSON_TABLE(JSON_EXTRACT(j.data_json, '$.data[*]'), '$[*]' COLUMNS (\n" +
                "    Employee_ID VARCHAR(100) PATH '$.Employee_Id',\n" +
                "    contracts JSON PATH '$.contracts'\n" +
                ")) AS emp,\n" +
                "JSON_TABLE(emp.contracts, '$[*]' COLUMNS (\n" +
                "    NIK_Contract VARCHAR(100) PATH '$.NIK_Contract',\n" +
                "    Name_Contract VARCHAR(100) PATH '$.Name_Contract',\n" +
                "    Contract_Start DATE PATH '$.Contract_Start',\n" +
                "    Contract_End DATE PATH '$.Contract_End',\n" +
                "    kontrak_ke VARCHAR(10) PATH '$.kontrak_ke'\n" +
                ")) AS c\n" +
                "WHERE j.ID = (SELECT MAX(ID) FROM table_json_uk)";
            jdbcTemplate.execute(insertContract);

            jdbcTemplate.execute("TRUNCATE table table_json");
            jdbcTemplate.execute("TRUNCATE table table_json_uk");

            jdbcTemplate.update("DELETE FROM history_employee WHERE DATE_FORMAT(NOW(), '%m-%d') = ?", labelTanggalUpdate);

            String insertHistory = "INSERT INTO history_employee (nik, name, employee_type, division, unit_name, position,\n" +
                "branch, created_date, created_by, join_date, resign_date, status_employee, marital_status, total_of_child,\n" +
                "gender, keterangan)\n" +
                "SELECT NIK, Name, Employee_Type, Division, Unit, Position, Branch, NOW(), ?, Join_Date, Resign_Date, Status,\n" +
                "Marital_Status, Total_Of_Child, Gender, 'Kunci Status PTKP'\n" +
                "FROM master_employee_new m WHERE (DATE_FORMAT(NOW(), '%m-%d') = ? OR \n" +
                "(NOT EXISTS (SELECT l.nik, l.join_date from history_employee l WHERE m.nik = l.nik and m.join_date = l.join_date))) \n" +
                "AND m.Status NOT IN ('BATAL JOIN', 'PENDING', 'INACTIVE') AND (m.Resign_Date is NULL OR YEAR(m.Resign_Date) = YEAR(CURDATE()))\n" +
                "AND (m.Marital_Status is not null OR m.Gender is not null)";
            
            jdbcTemplate.update(insertHistory, username, labelTanggalUpdate);

            jdbcTemplate.execute("TRUNCATE table employee_update_data");

            String insertEmployeeUpdate = "insert into employee_update_data (id_json, nik, `name`, id_number, employee_type, division, position, branch, join_date,\n" +
                "marital_status, total_of_child, unit_name, department, npwp, resign_date, status_employee,\n" +
                "gender, norek, kode_bank, cabang_bank, religion, education, nik_address, vendor_contract_start_date,\n" +
                "npwp_address, date_of_birth, contract_start_date, contract_end_date, level, nationality, place_of_birth, status, work_location, number_of_contract)\n" +
                "select n.id_json, n.nik, n.`name`, n.ID_Number, n.employee_type, n.division, n.position, n.branch, n.join_date,\n" +
                "n.marital_status, n.total_of_child, n.Unit, n.Departement, n.NPWP_Number, n.Resign_Date, n.Status,\n" +
                "n.Gender, n.Bank_Account, n.Bank_Name, n.Bank_Branch, n.Religion, n.Education, n.Address_By_ID, n.Vendor_Contract_Start_Date,\n" +
                "n.NPWP_Address, n.Date_Of_Birth, n.Contract_Start_Date, n.Contract_End_Date, n.Level, n.Nationality, n.Place_of_birth,\n" +
                "IF(n.Nationality='WNA','NEW',NULL), n.Lokasi_Kerja_Client, n.number_of_contract\n" +
                "from master_employee_new n\n" +
                "WHERE NOT EXISTS (SELECT l.nik, l.join_date from employee_update_data l WHERE n.nik = l.nik and n.join_date = l.join_date) AND \n" +
                "n.Status NOT IN ('BATAL JOIN', 'PENDING', 'INACTIVE') \n" +
                "AND ((n.Resign_Date is Null OR YEAR(n.Resign_Date) >= YEAR(CURDATE()) - 2) \n" +
                "OR YEAR(n.Contract_End_Date) BETWEEN YEAR(CURDATE()) - 1 AND YEAR(CURDATE()))\n" +
                "AND n.Employee_Type!='PKWTT'\n" +
                "AND n.Employee_Type is not null\n" +
                "AND n.nik is not null\n" +
                "AND n.ID_Number is not null";
            jdbcTemplate.execute(insertEmployeeUpdate);

            // Update queries
            jdbcTemplate.execute("UPDATE employee_update_data AS eud\n" +
                "LEFT JOIN history_employee AS he ON eud.NIK = he.NIK\n" +
                "SET eud.marital_status = he.marital_status, eud.total_of_child = he.total_of_child\n" +
                "WHERE eud.NIK = he.NIK");

            jdbcTemplate.execute("UPDATE employee_update_data eud\n" +
                "JOIN (\n" +
                "   SELECT nik, name, dub, bpjs_kesehatan, bpjs_keluarga, premi_asuransi, asuransi_kesehatan, asuransi_kecelakaan, bpu_jkm, bpu_jht, bsu, created_date\n" +
                "    FROM history_bpjs\n" +
                "    WHERE (nik, name, created_date) IN (\n" +
                "        SELECT nik, name, MAX(created_date)\n" +
                "        FROM history_bpjs\n" +
                "        GROUP BY nik, name\n" +
                "    )\n" +
                ") hb ON eud.nik = hb.nik COLLATE utf8mb4_general_ci AND eud.name = hb.name COLLATE utf8mb4_general_ci\n" +
                "SET eud.dub = hb.dub, \n" +
                "eud.bpjs_kesehatan = hb.bpjs_kesehatan,\n" +
                "eud.bpjs_keluarga = hb.bpjs_keluarga,\n" +
                "eud.premi_asuransi = hb.premi_asuransi,\n" +
                "eud.asuransi_kesehatan = hb.asuransi_kesehatan,\n" +
                "eud.asuransi_kecelakaan = hb.asuransi_kecelakaan,\n" +
                "eud.bpu_jkm = hb.bpu_jkm,\n" +
                "eud.bpu_jht = hb.bpu_jht,\n" +
                "eud.bsu = hb.bsu");

            jdbcTemplate.execute("UPDATE employee_update_data AS eud\n" +
                "JOIN data_izin_wna AS he ON eud.NIK = he.NIK\n" +
                "SET eud.kode_negara = he.kode_negara, \n" +
                "    eud.passport_number = he.passport_number,\n" +
                "    eud.tgl_izin_kerja = he.tgl_terdaftar,\n" +
                "    eud.kitas_number = he.kitas_number,\n" +
                "    eud.status = 'APPROVED'");

            jdbcTemplate.execute("UPDATE employee_update_data e\n" +
                "JOIN (\n" +
                "    SELECT *\n" +
                "    FROM (\n" +
                "        SELECT \n" +
                "            h.*,\n" +
                "            ROW_NUMBER() OVER (\n" +
                "                PARTITION BY h.id_number, h.nik\n" +
                "                ORDER BY h.created_date DESC\n" +
                "            ) AS rn\n" +
                "        FROM history_methode_pajak h\n" +
                "        WHERE h.status = 'APPROVED'\n" +
                "    ) x\n" +
                "   WHERE rn = 1\n" +
                ") h\n" +
                "  ON e.id_number = h.id_number COLLATE utf8mb4_general_ci\n" +
                " AND e.nik = h.nik COLLATE utf8mb4_general_ci\n" +
                "SET\n" +
                "    e.methode_pajak   = h.methode_pajak,\n" +
                "    e.komponen_pajak = h.komponen_pajak,\n" +
                "    e.status_pajak = h.status,\n" +
                "    e.created_by_pajak = h.created_by,\n" +
                "    e.created_date_pajak = h.created_date");

            jdbcTemplate.execute("update employee_update_data\n" +
                "set created_by_pajak = NULL, status_pajak = NULL, created_date_pajak = NULL\n" +
                "where MONTH(CURDATE()) = 1 AND DAY(CURDATE()) = 15");

            jdbcTemplate.execute("DELETE eu\n" +
                "FROM employee_update_data eu\n" +
                "JOIN history_nik_dika hnd \n" +
                "  ON eu.nik = hnd.old_nik\n" +
                "  AND eu.name = hnd.name\n" +
                "  AND eu.id_number = hnd.id_number\n" +
                "  AND eu.date_of_birth = hnd.date_of_birth");

            jdbcTemplate.execute("UPDATE payroll_buffer pb \n" +
                "JOIN employee_update_data eu\n" +
                "    ON eu.division = pb.division\n" +
                "    AND eu.unit_name = pb.unit_name\n" +
                "    AND eu.position = pb.position\n" +
                "    AND eu.branch = pb.branch\n" +
                "    AND eu.employee_type = pb.employee_type\n" +
                "JOIN buka_tutup_project bt\n" +
                "    ON bt.periode = CONCAT(pb.year_payroll, pb.month_payroll)\n" +
                "SET \n" +
                "    pb.norek = IF(pb.norek IS NULL OR pb.norek = '', eu.norek, pb.norek),\n" +
                "    pb.kode_bank = IF(pb.kode_bank IS NULL OR pb.kode_bank = '', eu.kode_bank, pb.kode_bank),\n" +
                "    pb.cabang_bank = IF(pb.cabang_bank IS NULL OR pb.cabang_bank = '', eu.cabang_bank, pb.cabang_bank),\n" +
                "    pb.update_date = NOW()\n" +
                "WHERE\n" +
                "    pb.year_payroll >= 2026\n" +
                "    AND bt.deskripsi = 'unlock'\n" +
                "    AND (\n" +
                "        pb.norek IS NULL OR pb.norek = '' \n" +
                "        OR pb.kode_bank IS NULL OR pb.kode_bank = ''\n" +
                "        OR pb.cabang_bank IS NULL OR pb.cabang_bank = ''\n" +
                "    )");

            jdbcTemplate.execute("UPDATE payroll p\n" +
                "JOIN employee_update_data eu\n" +
                "    ON eu.division = p.division\n" +
                "    AND eu.unit_name = p.unit_name\n" +
                "    AND eu.position = p.position\n" +
                "    AND eu.branch = p.branch\n" +
                "    AND eu.employee_type = p.employee_type\n" +
                "JOIN buka_tutup_project bt\n" +
                "    ON bt.periode = CONCAT(p.year_payroll, p.month_payroll)\n" +
                "SET \n" +
                "    p.norek = IF(p.norek IS NULL OR p.norek = '', eu.norek, p.norek),\n" +
                "    p.kode_bank = IF(p.kode_bank IS NULL OR p.kode_bank = '', eu.kode_bank, p.kode_bank),\n" +
                "    p.cabang_bank = IF(p.cabang_bank IS NULL OR p.cabang_bank = '', eu.cabang_bank, p.cabang_bank),\n" +
                "    p.update_date = NOW()\n" +
                "WHERE\n" +
                "    p.year_payroll >= 2026\n" +
                "    AND (p.approval_slip_gaji != 'APPROVED' OR p.approval_slip_gaji IS NULL)\n" +
                "    AND bt.deskripsi = 'unlock'\n" +
                "    AND (\n" +
                "        p.norek IS NULL OR p.norek = '' \n" +
                "        OR p.kode_bank IS NULL OR p.kode_bank = ''\n" +
                "        OR p.cabang_bank IS NULL OR p.cabang_bank = ''\n" +
                "    )");

            jdbcTemplate.execute("UPDATE payroll_detail pd\n" +
                "JOIN employee_update_data eu\n" +
                "    ON eu.division = pd.division\n" +
                "    AND eu.unit_name = pd.unit_name\n" +
                "    AND eu.position = pd.position\n" +
                "    AND eu.branch = pd.branch\n" +
                "    AND eu.employee_type = pd.employee_type\n" +
                "JOIN buka_tutup_project bt\n" +
                "    ON bt.periode = CONCAT(pd.year_payroll, pd.month_payroll)\n" +
                "SET \n" +
                "    pd.norek = IF(pd.norek IS NULL OR pd.norek = '', eu.norek, pd.norek),\n" +
                "    pd.kode_bank = IF(pd.kode_bank IS NULL OR pd.kode_bank = '', eu.kode_bank, pd.kode_bank),\n" +
                "    pd.cabang_bank = IF(pd.cabang_bank IS NULL OR pd.cabang_bank = '', eu.cabang_bank, pd.cabang_bank),\n" +
                "    pd.update_date = NOW()\n" +
                "WHERE\n" +
                "    pd.year_payroll >= 2026\n" +
                "    AND pd.approval_slip_gaji != 'APPROVED'\n" +
                "    AND bt.deskripsi = 'unlock'\n" +
                "    AND (\n" +
                "        pd.norek IS NULL OR pd.norek = '' \n" +
                "        OR pd.kode_bank IS NULL OR pd.kode_bank = ''\n" +
                "        OR pd.cabang_bank IS NULL OR pd.cabang_bank = ''\n" +
                "    )");

            jdbcTemplate.execute("UPDATE master_hold mh\n" +
                "JOIN employee_update_data eu\n" +
                "    ON eu.division = mh.division\n" +
                "    AND eu.unit_name = mh.unit_name\n" +
                "    AND eu.position = mh.position\n" +
                "    AND eu.branch = mh.branch\n" +
                "    AND eu.employee_type = mh.employee_type\n" +
                "JOIN buka_tutup_project bt\n" +
                "    ON bt.periode = CONCAT(mh.year_payroll, mh.month_payroll)\n" +
                "SET \n" +
                "    mh.bank_account_number = IF(mh.bank_account_number IS NULL OR mh.bank_account_number = '', eu.norek, mh.bank_account_number),\n" +
                "    mh.bank_code = IF(mh.bank_code IS NULL OR mh.bank_code = '', eu.kode_bank, mh.bank_code),\n" +
                "    mh.bank_branch = IF(mh.bank_branch IS NULL OR mh.bank_branch = '', eu.cabang_bank, mh.bank_branch)\n" +
                "WHERE\n" +
                "    mh.year_payroll >= 2026 \n" +
                "    AND bt.deskripsi = 'unlock'\n" +
                "    AND (\n" +
                "        mh.bank_account_number IS NULL OR mh.bank_account_number = '' \n" +
                "        OR mh.bank_code IS NULL OR mh.bank_code = ''\n" +
                "        OR mh.bank_branch IS NULL OR mh.bank_branch = ''\n" +
                "    )");

            System.out.println("HRIS Sync Complete!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("HRIS Sync failed", e);
        }
    }
}
