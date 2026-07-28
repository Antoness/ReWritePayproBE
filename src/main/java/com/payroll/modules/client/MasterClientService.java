package com.payroll.modules.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.Map;

@Service
public class MasterClientService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MasterSalaryRepository masterSalaryRepository;
    
    @Autowired
    private HistoryMasterSalaryRepository historyMasterSalaryRepository;


    private void appendSearchFilters(StringBuilder sql, MasterClientSearchRequest request, List<Object> params) {
        if (request.getSearch() != null && !request.getSearch().trim().isEmpty()) {
            String search = "%" + request.getSearch().trim() + "%";
            sql.append(" AND (ms.division LIKE ? OR ms.unit_name LIKE ? OR ms.position LIKE ? OR ms.branch LIKE ? OR ms.employee_type LIKE ? OR ms.approval LIKE ? OR ms.periode_payroll LIKE ?) ");
            for (int i = 0; i < 7; i++) params.add(search);
        }
        if (request.getDivision() != null && !request.getDivision().trim().isEmpty()) {
            sql.append(" AND UPPER(ms.division) = UPPER(?) ");
            params.add(request.getDivision());
        }
        if (request.getUnitName() != null && !request.getUnitName().trim().isEmpty()) {
            sql.append(" AND UPPER(ms.unit_name) = UPPER(?) ");
            params.add(request.getUnitName());
        }
        if (request.getPosition() != null && !request.getPosition().trim().isEmpty()) {
            sql.append(" AND UPPER(ms.position) = UPPER(?) ");
            params.add(request.getPosition());
        }
        if (request.getBranch() != null && !request.getBranch().trim().isEmpty()) {
            sql.append(" AND UPPER(ms.branch) = UPPER(?) ");
            params.add(request.getBranch());
        }
        if (request.getStatus() != null && !request.getStatus().trim().isEmpty()) {
            sql.append(" AND UPPER(ms.approval) = UPPER(?) ");
            params.add(request.getStatus());
        }
        if (request.getEmployeeType() != null && !request.getEmployeeType().trim().isEmpty()) {
            sql.append(" AND UPPER(ms.employee_type) = UPPER(?) ");
            params.add(request.getEmployeeType());
        }
    }

    public Long getUserIdByFullname(String fullname) {
        try {
            return jdbcTemplate.queryForObject("SELECT id FROM users WHERE full_name = ? LIMIT 1", Long.class, fullname);
        } catch (Exception e) {
            return null;
        }
    }

    public Page<MasterClientResponseDTO> getStaffList(Long userId, String fullname, MasterClientSearchRequest request, int page, int size) {
        StringBuilder sql = new StringBuilder();
        StringBuilder countSql = new StringBuilder();

        String selectFields = "SELECT DISTINCT ms.id, ms.created_date, ms.division AS Division, ms.unit_name AS Unit, ms.position AS Position, ms.branch AS Branch, ms.employee_type AS Employee_Type, TO_CHAR(ms.created_date, 'DD/MM/YYYY') AS Created_Date, TO_CHAR(ms.update_date, 'DD/MM/YYYY') AS Update_Date, ms.created_by AS Created_By, ms.approval AS Status, ms.gaji, ms.bpjs_kesehatan, ms.bp_jamsostek, ms.bpjs_pensiun, ms.asuransi_kesehatan, ms.asuransi_kecelakaan, ms.tunjangan ";
        String fromWhere = "FROM master_salary ms " +
                           "LEFT JOIN users u ON u.id = ms.id_user " +
                           "LEFT JOIN master_pic mp ON mp.master_salary_id = ms.id " +
                           "WHERE ((ms.id_user = ? AND ms.approval = 'REQUEST') " +
                           "OR (mp.\"user\" = ? AND ms.approval = 'APPROVED') " +
                           "OR (ms.pic = ? AND ms.approval = 'APPROVED')) " +
                           "AND (ms.approval != 'DONE') ";
        
        sql.append(selectFields).append(fromWhere);
        countSql.append("SELECT COUNT(DISTINCT ms.id) ").append(fromWhere);

        List<Object> params = new ArrayList<>();
        params.add(userId);
        params.add(fullname);
        params.add(fullname);

        List<Object> countParams = new ArrayList<>(params);
        appendSearchFilters(countSql, request, countParams);
        appendSearchFilters(sql, request, params);

        sql.append(" ORDER BY ms.created_date DESC");
        
        Long totalElements = jdbcTemplate.queryForObject(countSql.toString(), Long.class, countParams.toArray());
        if (totalElements == null) totalElements = 0L;

        sql.append(" LIMIT ? OFFSET ?");
        params.add(size);
        params.add(page * size);

        List<MasterClientResponseDTO> content = jdbcTemplate.query(sql.toString(), params.toArray(), new StaffRowMapper());
        
        return new org.springframework.data.domain.PageImpl<>(content, org.springframework.data.domain.PageRequest.of(page, size), totalElements);
    }

    public List<MasterClientResponseDTO> getStaffExportByFilter(Long userId, String fullname, String uplinerName, MasterClientSearchRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ROW_NUMBER() OVER (ORDER BY ms.division, ms.unit_name, ms.position, ms.branch, ms.employee_type ASC) AS No, ");
        sql.append("ms.division AS Division, ms.unit_name AS Unit, ms.position AS Position, ms.branch AS Branch, UPPER(ms.employee_type) AS Employee_Type, ms.gaji, ms.bpjs_kesehatan, ms.bp_jamsostek, ms.bpjs_pensiun, ms.asuransi_kesehatan, ms.asuransi_kecelakaan, ms.tunjangan ");
        sql.append("FROM master_salary ms ");
        sql.append("LEFT JOIN master_pic mp ON mp.master_salary_id = ms.id ");
        sql.append("LEFT JOIN users u ON u.id = ms.id_user ");
        
        List<Object> params = new ArrayList<>();
        if (request.getDivision() != null && !request.getDivision().isEmpty()) {
            sql.append("WHERE ms.division = ? ");
            params.add(request.getDivision());
        } else {
            sql.append("WHERE 1=1 ");
        }
        
        sql.append("AND (ms.id_user = ? OR mp.\"user\" = ? OR (ms.created_by = ? AND ms.approval = 'APPROVED')) ");
        sql.append("AND (ms.approval != 'DONE') ");
        
        params.add(userId);
        params.add(fullname);
        params.add(uplinerName);

        appendSearchFilters(sql, request, params);

        sql.append(" GROUP BY ms.id ORDER BY ms.division, ms.unit_name, ms.position, ms.branch, ms.employee_type ASC");

        return jdbcTemplate.query(sql.toString(), params.toArray(), new ExportRowMapper());
    }

    public List<MasterClientResponseDTO> getStaffExportByChecklist(List<Long> ids) {
        if (ids == null || ids.isEmpty()) return new ArrayList<>();
        
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ROW_NUMBER() OVER (ORDER BY ms.division, ms.unit_name, ms.position, ms.branch, ms.employee_type ASC) AS No, ");
        sql.append("ms.division AS Division, ms.unit_name AS Unit, ms.position AS Position, ms.branch AS Branch, UPPER(ms.employee_type) AS Employee_Type, ms.gaji, ms.bpjs_kesehatan, ms.bp_jamsostek, ms.bpjs_pensiun, ms.asuransi_kesehatan, ms.asuransi_kecelakaan, ms.tunjangan ");
        sql.append("FROM master_salary ms LEFT JOIN users u ON u.id = ms.id_user ");
        sql.append("WHERE ms.id IN (");
        
        String inSql = ids.stream().map(id -> "?").collect(Collectors.joining(","));
        sql.append(inSql).append(") ");
        sql.append("ORDER BY ms.division, ms.unit_name, ms.position, ms.branch, ms.employee_type ASC");

        return jdbcTemplate.query(sql.toString(), ids.toArray(), new ExportRowMapper());
    }

    public Page<MasterClientResponseDTO> getSpvList(String fullname, MasterClientSearchRequest request, int page, int size) {
        StringBuilder sql = new StringBuilder();
        StringBuilder countSql = new StringBuilder();
        
        String selectFields = "SELECT DISTINCT ms.id, ms.created_date, ms.division AS Division, ms.unit_name AS Unit, ms.position AS Position, ms.branch AS Branch, ms.employee_type AS Employee_Type, TO_CHAR(ms.created_date, 'DD/MM/YYYY') AS Created_Date, TO_CHAR(ms.update_date, 'DD/MM/YYYY') AS Update_Date, ms.created_by AS Created_By, ms.approval AS Status, CASE WHEN ms.approval='REQUEST' THEN ms.keterangan ELSE '' END AS Keterangan, ms.gaji, ms.bpjs_kesehatan, ms.bp_jamsostek, ms.bpjs_pensiun, ms.asuransi_kesehatan, ms.asuransi_kecelakaan, CAST(ms.tunjangan AS NUMERIC) AS tunjangan ";
        String fromWhere = "FROM master_salary ms " +
                           "LEFT JOIN users u ON u.id = ms.id_user " +
                           "LEFT JOIN master_pic mp ON mp.master_salary_id = ms.id " +
                           "LEFT JOIN users uc ON uc.full_name = ms.created_by " +
                           "LEFT JOIN users up ON up.full_name = mp.\"user\" " +
                           "LEFT JOIN master_upliner mup ON u.nik = mup.nik OR uc.nik = mup.nik OR up.nik = mup.nik " +
                           "WHERE (mup.nama_upliner = ? OR u.leader = ? OR uc.leader = ? OR up.leader = ?) " +
                           "AND (ms.approval = 'REQUEST' OR ms.approval = 'APPROVED' OR ms.approval = 'PROCESSED') ";
        
        sql.append(selectFields).append(fromWhere);
        countSql.append("SELECT COUNT(DISTINCT ms.id) ").append(fromWhere);

        List<Object> params = new ArrayList<>();
        params.add(fullname);
        params.add(fullname);
        params.add(fullname);
        params.add(fullname);
        
        List<Object> countParams = new ArrayList<>(params);
        appendSearchFilters(countSql, request, countParams);
        appendSearchFilters(sql, request, params);

        sql.append(" ORDER BY ms.created_date DESC");

        Long totalElements = jdbcTemplate.queryForObject(countSql.toString(), Long.class, countParams.toArray());
        if (totalElements == null) totalElements = 0L;

        sql.append(" LIMIT ? OFFSET ?");
        params.add(size);
        params.add(page * size);

        List<MasterClientResponseDTO> content = jdbcTemplate.query(sql.toString(), params.toArray(), new SpvRowMapper());
        
        return new org.springframework.data.domain.PageImpl<>(content, org.springframework.data.domain.PageRequest.of(page, size), totalElements);
    }

    public List<MasterClientResponseDTO> getSpvExportByFilter(String fullname, MasterClientSearchRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ROW_NUMBER() OVER (ORDER BY ms.division, ms.unit_name, ms.position, ms.branch, ms.employee_type ASC) AS No, ms.division AS Division, ms.unit_name AS Unit, ms.position AS Position, ms.branch AS Branch, UPPER(ms.employee_type) AS Employee_Type, ms.gaji, ms.bpjs_kesehatan, ms.bp_jamsostek, ms.bpjs_pensiun, ms.asuransi_kesehatan, ms.asuransi_kecelakaan, ms.tunjangan ");
        sql.append("FROM master_salary ms ");
        sql.append("LEFT JOIN users u ON u.id = ms.id_user ");
        sql.append("LEFT JOIN master_pic mp ON mp.master_salary_id = ms.id ");
        sql.append("LEFT JOIN users uc ON uc.full_name = ms.created_by ");
        sql.append("LEFT JOIN users up ON up.full_name = mp.\"user\" ");
        sql.append("LEFT JOIN master_upliner mup ON u.nik = mup.nik OR uc.nik = mup.nik OR up.nik = mup.nik ");
        
        List<Object> params = new ArrayList<>();
        if (request.getDivision() != null && !request.getDivision().isEmpty()) {
            sql.append("WHERE ms.division = ? ");
            params.add(request.getDivision());
        } else {
            sql.append("WHERE 1=1 ");
        }

        sql.append("AND (mup.nama_upliner = ? OR u.leader = ? OR uc.leader = ? OR up.leader = ?) ");
        sql.append("AND (ms.approval IN ('REQUEST', 'APPROVED', 'PROCESSED')) ");
        
        params.add(fullname);
        params.add(fullname);
        params.add(fullname);
        params.add(fullname);

        appendSearchFilters(sql, request, params);

        sql.append(" GROUP BY ms.id");

        return jdbcTemplate.query(sql.toString(), params.toArray(), new ExportRowMapper());
    }

    public List<MasterClientResponseDTO> getSpvExportByChecklist(List<Long> ids) {
        if (ids == null || ids.isEmpty()) return new ArrayList<>();
        
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ROW_NUMBER() OVER (ORDER BY ms.division, ms.unit_name, ms.position, ms.branch, ms.employee_type ASC) AS No, ");
        sql.append("ms.division AS Division, ms.unit_name AS Unit, ms.position AS Position, ms.branch AS Branch, UPPER(ms.employee_type) AS Employee_Type, ms.gaji, ms.bpjs_kesehatan, ms.bp_jamsostek, ms.bpjs_pensiun, ms.asuransi_kesehatan, ms.asuransi_kecelakaan, ms.tunjangan ");
        sql.append("FROM master_salary ms LEFT JOIN users u ON u.id = ms.id_user ");
        sql.append("WHERE ms.id IN (");
        
        String inSql = ids.stream().map(id -> "?").collect(Collectors.joining(","));
        sql.append(inSql).append(") ");
        sql.append("ORDER BY ms.division, ms.unit_name, ms.position, ms.branch, ms.employee_type ASC");

        return jdbcTemplate.query(sql.toString(), ids.toArray(), new ExportRowMapper());
    }

    public List<Map<String, Object>> getUpdatePreviewByChecklist(List<Long> ids) {
        if (ids == null || ids.isEmpty()) return new ArrayList<>();
        
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, ");
        sql.append("division AS \"Division\", unit_name AS \"Unit Name\", position AS \"Position\", branch AS \"Branch\", ");
        sql.append("work_days AS \"Work Day\", salary_type AS \"Salary Type\", nominal AS \"Allowance\", performance_pay AS \"Achivment\", ");
        sql.append("gaji AS \"Gaji Pokok\", manajemen_fee AS \"Manajemen Fee\", komponen_project AS \"Komponen Project\", ");
        sql.append("metode_pajak AS \"Metode Pajak\", bpjs_tk_type AS \"BPJS TK Type\", bpjs_ketenagakerjaan AS \"BPJS Ketenagakerjaan\", ");
        sql.append("bpjs_kesehatan AS \"BPJS Kesehatan\", ditanggung_oleh AS \"Asuransi ditanggung oleh\", ");
        sql.append("asuransi_kecelakaan AS \"Asuransi Kecelakaan\", asuransi_kesehatan AS \"Asuransi Kesehatan\", ");
        sql.append("tunjangan_tetap AS \"Kategori Tunjangan Tetap\", tunjangan_tidak_tetap AS \"Kategori Tunjangan Tidak Tetap\", ");
        sql.append("biaya_jasa AS \"Biaya Jasa Training\", bonus AS \"Bonus\", insentif AS \"Insentif\", lembur AS \"Lembur\", ");
        sql.append("shift_allowance AS \"Shift Allowance\", thr AS \"THR\", tunjangan_kesehatan AS \"Tunjangan Kesehatan\", ");
        sql.append("created_date "); // we fetch created_date for 'karyawan baru' logic in frontend
        sql.append("FROM master_salary ");
        sql.append("WHERE id IN (");
        
        String inSql = ids.stream().map(id -> "?").collect(Collectors.joining(","));
        sql.append(inSql).append(") ");

        return jdbcTemplate.queryForList(sql.toString(), ids.toArray());
    }

    private static class StaffRowMapper implements RowMapper<MasterClientResponseDTO> {
        @Override
        public MasterClientResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            MasterClientResponseDTO dto = new MasterClientResponseDTO();
            dto.setId(rs.getLong("id"));
            dto.setDivision(rs.getString("Division"));
            dto.setUnit(rs.getString("Unit"));
            dto.setPosition(rs.getString("Position"));
            dto.setBranch(rs.getString("Branch"));
            dto.setEmployeeType(rs.getString("Employee_Type"));
            dto.setCreatedDate(rs.getString("Created_Date"));
            dto.setUpdateDate(rs.getString("Update_Date"));
            dto.setCreatedBy(rs.getString("Created_By"));
            dto.setStatus(rs.getString("Status"));
            dto.setGaji(rs.getDouble("gaji"));
            dto.setBpjsKesehatan(rs.getString("bpjs_kesehatan"));
            dto.setBpJamsostek(rs.getString("bp_jamsostek"));
            dto.setBpjsPensiun(rs.getString("bpjs_pensiun"));
            dto.setAsuransiKesehatan(rs.getString("asuransi_kesehatan"));
            dto.setAsuransiKecelakaan(rs.getString("asuransi_kecelakaan"));
            dto.setTunjangan(rs.getDouble("tunjangan"));
            return dto;
        }
    }

    private static class SpvRowMapper implements RowMapper<MasterClientResponseDTO> {
        @Override
        public MasterClientResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            MasterClientResponseDTO dto = new MasterClientResponseDTO();
            dto.setId(rs.getLong("id"));
            dto.setDivision(rs.getString("Division"));
            dto.setUnit(rs.getString("Unit"));
            dto.setPosition(rs.getString("Position"));
            dto.setBranch(rs.getString("Branch"));
            dto.setEmployeeType(rs.getString("Employee_Type"));
            dto.setCreatedDate(rs.getString("Created_Date"));
            dto.setUpdateDate(rs.getString("Update_Date"));
            dto.setCreatedBy(rs.getString("Created_By"));
            dto.setStatus(rs.getString("Status"));
            dto.setKeterangan(rs.getString("Keterangan"));
            dto.setGaji(rs.getDouble("gaji"));
            dto.setBpjsKesehatan(rs.getString("bpjs_kesehatan"));
            dto.setBpJamsostek(rs.getString("bp_jamsostek"));
            dto.setBpjsPensiun(rs.getString("bpjs_pensiun"));
            dto.setAsuransiKesehatan(rs.getString("asuransi_kesehatan"));
            dto.setAsuransiKecelakaan(rs.getString("asuransi_kecelakaan"));
            dto.setTunjangan(rs.getDouble("tunjangan"));
            return dto;
        }
    }

    private static class ExportRowMapper implements RowMapper<MasterClientResponseDTO> {
        @Override
        public MasterClientResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            MasterClientResponseDTO dto = new MasterClientResponseDTO();
            dto.setNo(rs.getLong("No"));
            dto.setDivision(rs.getString("Division"));
            dto.setUnit(rs.getString("Unit"));
            dto.setPosition(rs.getString("Position"));
            dto.setBranch(rs.getString("Branch"));
            dto.setEmployeeType(rs.getString("Employee_Type"));
            dto.setGaji(rs.getDouble("gaji"));
            dto.setBpjsKesehatan(rs.getString("bpjs_kesehatan"));
            dto.setBpJamsostek(rs.getString("bp_jamsostek"));
            dto.setBpjsPensiun(rs.getString("bpjs_pensiun"));
            dto.setAsuransiKesehatan(rs.getString("asuransi_kesehatan"));
            dto.setAsuransiKecelakaan(rs.getString("asuransi_kecelakaan"));
            dto.setTunjangan(rs.getDouble("tunjangan"));
            return dto;
        }
    }
    public Page<MasterClientResponseDTO> getHistoryLog(String role, String fullname, MasterClientSearchRequest request, int page, int size) {
        StringBuilder sql = new StringBuilder();
        StringBuilder countSql = new StringBuilder();

        String selectFields = "SELECT al.log_id AS id, TO_CHAR(al.created_at, 'YYYY-MM-DD HH24:MI:SS.US') AS created_date, " +
                              "al.new_values->>'division' AS Division, al.new_values->>'unit_name' AS Unit, al.new_values->>'position' AS Position, " +
                              "al.new_values->>'branch' AS Branch, UPPER(al.new_values->>'employee_type') AS Employee_Type, " +
                              "TO_CHAR(al.created_at, 'DD/MM/YYYY') AS Created_Date, TO_CHAR(al.created_at, 'DD/MM/YYYY') AS Update_Date, " +
                              "al.created_by AS Created_By, al.new_values->>'approval' AS Status, " +
                              "CAST(al.new_values->>'gaji' AS NUMERIC) AS gaji, " +
                              "al.new_values->>'bpjs_kesehatan' AS bpjs_kesehatan, al.new_values->>'bp_jamsostek' AS bp_jamsostek, " +
                              "al.new_values->>'bpjs_pensiun' AS bpjs_pensiun, al.new_values->>'asuransi_kesehatan' AS asuransi_kesehatan, " +
                              "al.new_values->>'asuransi_kecelakaan' AS asuransi_kecelakaan, CAST(al.new_values->>'tunjangan' AS NUMERIC) AS tunjangan, " +
                              "CASE WHEN al.new_values->>'approval'='REQUEST' THEN al.new_values->>'keterangan' ELSE '' END AS Keterangan ";
        
        String fromWhere = "FROM audit_logs al LEFT JOIN users u ON u.full_name = al.created_by ";
        fromWhere += "WHERE al.entity_name = 'MasterClient' ";

        List<Object> params = new ArrayList<>();
        
        // Role based filters
        if ("STAFF".equalsIgnoreCase(role)) {
            fromWhere += "AND (al.created_by = ? OR u.leader = ?) ";
            params.add(fullname);
            params.add(fullname);
        } else if ("SPV".equalsIgnoreCase(role)) {
            fromWhere += "AND al.created_by = ? ";
            params.add(fullname);
        } else if ("MANAGER".equalsIgnoreCase(role)) {
            // Manager sees all, 1=1 is implicit since we just don't add conditions
        }

        // Search Filters
        if (request.getSearch() != null && !request.getSearch().trim().isEmpty()) {
            String search = "%" + request.getSearch().trim() + "%";
            fromWhere += "AND (al.new_values->>'division' ILIKE ? OR al.new_values->>'unit_name' ILIKE ? OR al.new_values->>'position' ILIKE ? " +
                         "OR al.new_values->>'employee_type' ILIKE ? OR al.new_values->>'branch' ILIKE ? OR al.new_values->>'approval' ILIKE ?) ";
            for (int i = 0; i < 6; i++) params.add(search);
        }
        if (request.getDivision() != null && !request.getDivision().trim().isEmpty()) {
            fromWhere += "AND UPPER(al.new_values->>'division') = UPPER(?) ";
            params.add(request.getDivision());
        }
        if (request.getUnitName() != null && !request.getUnitName().trim().isEmpty()) {
            fromWhere += "AND UPPER(al.new_values->>'unit_name') = UPPER(?) ";
            params.add(request.getUnitName());
        }
        if (request.getPosition() != null && !request.getPosition().trim().isEmpty()) {
            fromWhere += "AND UPPER(al.new_values->>'position') = UPPER(?) ";
            params.add(request.getPosition());
        }
        if (request.getBranch() != null && !request.getBranch().trim().isEmpty()) {
            fromWhere += "AND UPPER(al.new_values->>'branch') = UPPER(?) ";
            params.add(request.getBranch());
        }

        sql.append(selectFields).append(fromWhere);
        countSql.append("SELECT COUNT(DISTINCT al.log_id) ").append(fromWhere);

        sql.append(" ORDER BY al.created_at DESC");

        Long totalElements = jdbcTemplate.queryForObject(countSql.toString(), Long.class, params.toArray());
        if (totalElements == null) totalElements = 0L;

        sql.append(" LIMIT ? OFFSET ?");
        params.add(size);
        params.add(page * size);

        List<MasterClientResponseDTO> content = jdbcTemplate.query(sql.toString(), params.toArray(), new SpvRowMapper());
        
        return new org.springframework.data.domain.PageImpl<>(content, org.springframework.data.domain.PageRequest.of(page, size), totalElements);
    }
    public MasterSalary addClient(MasterSalaryRequestDTO req, String createdBy) {
        Optional<MasterSalary> existing = masterSalaryRepository.findByDivisionIgnoreCaseAndUnitNameIgnoreCaseAndPositionIgnoreCaseAndBranchIgnoreCaseAndEmployeeTypeIgnoreCase(
                req.getDivision(), req.getUnitName(), req.getPosition(), req.getBranch(), req.getEmployeeType()
        );

        if (existing.isPresent()) {
            throw new IllegalArgumentException(String.format("Division : %s, Unit Name : %s, Position : %s, Branch : %s, Employee Type : %s Already Exist, Created by %s",
                    req.getDivision(), req.getUnitName(), req.getPosition(), req.getBranch(), req.getEmployeeType(), existing.get().getCreatedBy()));
        }


        // Fetch id_user if missing
        Long idUser = null;
        try {
            idUser = jdbcTemplate.queryForObject("SELECT id FROM users WHERE full_name = ? LIMIT 1", Long.class, createdBy);
        } catch (Exception e) {
            // ignore
        }

        MasterSalary ms = new MasterSalary();
        ms.setIdUser(idUser);
        ms.setDivision(req.getDivision());
        ms.setUnitName(req.getUnitName());
        ms.setPosition(req.getPosition());
        ms.setBranch(req.getBranch());
        ms.setEmployeeType(req.getEmployeeType());
        ms.setCreatedBy(createdBy);
        ms.setApproval("REQUEST");
        
        ms.setSalaryType(req.getSalaryType());
        ms.setNominal(req.getNominal());
        ms.setWorkDays(req.getWorkDays());
        ms.setBpjsTkType(req.getBpjsTkType());
        ms.setManajemenFee(req.getManajemenFee());
        ms.setMetodePajak(req.getMetodePajak());
        ms.setKomponenProject(req.getKomponenProject());
        ms.setPersenBpjsKesehatan(req.getPersenBpjsKesehatan());
        ms.setBpjsKetenagakerjaan(req.getBpjsKetenagakerjaan());
        ms.setKomponenUpah(req.getKomponenUpah());
        ms.setKomponenLembur(req.getKomponenLembur());
        ms.setBiayaJasa(req.getBiayaJasa());
        ms.setTraining(req.getTraining());
        ms.setBonus(req.getBonus());
        
        if (req.getTunjanganTetap() != null) ms.setTunjanganTetap(String.join(",", req.getTunjanganTetap()));
        if (req.getTunjanganTidakTetap() != null) ms.setTunjanganTidakTetap(String.join(",", req.getTunjanganTidakTetap()));
        ms.setTunjanganBedaPeriode(req.getTunjanganBedaPeriode());
        ms.setTunjanganDetails(req.getTunjanganDetails());
        ms.setDitanggungOleh(req.getDitanggungOleh());
        ms.setAsuransiKesehatan(req.getAsuransiKesehatan() != null ? String.valueOf(req.getAsuransiKesehatan()) : null);
        ms.setAsuransiKecelakaan(req.getAsuransiKecelakaan() != null ? String.valueOf(req.getAsuransiKecelakaan()) : null);
        ms.setInsentif(req.getInsentif());
        ms.setLembur(req.getLembur());
        ms.setTunjanganKesehatan(req.getTunjanganKesehatan());
        ms.setPerformancePay(req.getPerformancePay());
        ms.setMonthlyCommission(req.getMonthlyCommission());
        ms.setShiftAllowance(req.getShiftAllowance());
        ms.setThr(req.getThr());
        ms.setKompensasi(req.getKompensasi());

        MasterSalary saved = masterSalaryRepository.save(ms);

        // Construct new_values JSON for audit_logs
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            java.util.Map<String, Object> values = new java.util.HashMap<>();
            values.put("division", saved.getDivision());
            values.put("unit_name", saved.getUnitName());
            values.put("position", saved.getPosition());
            values.put("branch", saved.getBranch());
            values.put("employee_type", saved.getEmployeeType());
            values.put("approval", saved.getApproval());
            values.put("gaji", saved.getNominal());
            values.put("bpjs_kesehatan", saved.getPersenBpjsKesehatan());
            values.put("bp_jamsostek", saved.getBpjsTkType());
            values.put("bpjs_pensiun", "");
            values.put("asuransi_kesehatan", saved.getAsuransiKesehatan());
            values.put("asuransi_kecelakaan", saved.getAsuransiKecelakaan());
            values.put("tunjangan", 0); // Need to calculate total tunjangan if required, else 0
            
            String newValuesJson = mapper.writeValueAsString(values);
            
            String insertAudit = "INSERT INTO audit_logs (entity_name, entity_id, action_type, new_values, created_by, created_at) " +
                                 "VALUES ('MasterClient', ?, 'ADD', CAST(? AS JSONB), ?, CURRENT_TIMESTAMP)";
            jdbcTemplate.update(insertAudit, String.valueOf(saved.getId()), newValuesJson, createdBy);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return saved;
    }

    public MasterSalary getClientById(Long id) {
        return masterSalaryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Data not found with id: " + id));
    }

    @org.springframework.transaction.annotation.Transactional
    public MasterSalary updateClient(Long id, MasterSalaryRequestDTO req, String fullname) {
        MasterSalary ms = getClientById(id);

        // Track history before update
        HistoryMasterSalary history = new HistoryMasterSalary();
        history.setMasterSalaryId(ms.getId());
        history.setAction("UPDATE");
        history.setDivision(ms.getDivision());
        history.setUnitName(ms.getUnitName());
        history.setPosition(ms.getPosition());
        history.setBranch(ms.getBranch());
        history.setEmployeeType(ms.getEmployeeType());
        history.setCreatedBy(fullname);
        history.setDetails("Updated by " + fullname);
        historyMasterSalaryRepository.save(history);

        ms.setDivision(req.getDivision());
        ms.setUnitName(req.getUnitName());
        ms.setPosition(req.getPosition());
        ms.setBranch(req.getBranch());
        ms.setEmployeeType(req.getEmployeeType());
        ms.setApproval("REQUEST"); // reset approval status when data changed
        
        ms.setSalaryType(req.getSalaryType());
        ms.setNominal(req.getNominal());
        ms.setWorkDays(req.getWorkDays());
        ms.setBpjsTkType(req.getBpjsTkType());
        ms.setManajemenFee(req.getManajemenFee());
        ms.setMetodePajak(req.getMetodePajak());
        ms.setKomponenProject(req.getKomponenProject());
        ms.setPersenBpjsKesehatan(req.getPersenBpjsKesehatan());
        ms.setBpjsKetenagakerjaan(req.getBpjsKetenagakerjaan());
        ms.setKomponenUpah(req.getKomponenUpah());
        ms.setKomponenLembur(req.getKomponenLembur());
        ms.setBiayaJasa(req.getBiayaJasa());
        ms.setTraining(req.getTraining());
        ms.setBonus(req.getBonus());
        
        if (req.getTunjanganTetap() != null) ms.setTunjanganTetap(String.join(",", req.getTunjanganTetap()));
        if (req.getTunjanganTidakTetap() != null) ms.setTunjanganTidakTetap(String.join(",", req.getTunjanganTidakTetap()));
        ms.setTunjanganBedaPeriode(req.getTunjanganBedaPeriode());
        ms.setTunjanganDetails(req.getTunjanganDetails());
        ms.setDitanggungOleh(req.getDitanggungOleh());
        ms.setAsuransiKesehatan(req.getAsuransiKesehatan() != null ? String.valueOf(req.getAsuransiKesehatan()) : null);
        ms.setAsuransiKecelakaan(req.getAsuransiKecelakaan() != null ? String.valueOf(req.getAsuransiKecelakaan()) : null);
        ms.setInsentif(req.getInsentif());
        ms.setLembur(req.getLembur());
        ms.setTunjanganKesehatan(req.getTunjanganKesehatan());
        ms.setPerformancePay(req.getPerformancePay());
        ms.setMonthlyCommission(req.getMonthlyCommission());
        ms.setShiftAllowance(req.getShiftAllowance());
        ms.setThr(req.getThr());
        ms.setKompensasi(req.getKompensasi());

        MasterSalary saved = masterSalaryRepository.save(ms);

        // log audit
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            java.util.Map<String, Object> values = new java.util.HashMap<>();
            values.put("division", saved.getDivision());
            values.put("unit_name", saved.getUnitName());
            values.put("approval", saved.getApproval());
            String newValuesJson = mapper.writeValueAsString(values);
            
            String insertAudit = "INSERT INTO audit_logs (entity_name, entity_id, action_type, new_values, created_by, created_at) " +
                                 "VALUES ('MasterClient', ?, 'UPDATE', CAST(? AS JSONB), ?, CURRENT_TIMESTAMP)";
            jdbcTemplate.update(insertAudit, String.valueOf(saved.getId()), newValuesJson, fullname);
        } catch (Exception e) {}

        return saved;
    }

    @org.springframework.transaction.annotation.Transactional
    public void deleteClient(Long id, String fullname) {
        // Find existing record
        Optional<MasterSalary> opt = masterSalaryRepository.findById(id);
        if (opt.isEmpty()) throw new IllegalArgumentException("Data not found");
        
        MasterSalary ms = opt.get();
        
        // Backup to HistoryMasterSalary
        HistoryMasterSalary history = new HistoryMasterSalary();
        history.setMasterSalaryId(ms.getId());
        history.setAction("DELETE");
        history.setDivision(ms.getDivision());
        history.setUnitName(ms.getUnitName());
        history.setPosition(ms.getPosition());
        history.setBranch(ms.getBranch());
        history.setEmployeeType(ms.getEmployeeType());
        history.setCreatedBy(fullname);
        history.setDetails("Deleted by " + fullname);
        historyMasterSalaryRepository.save(history);

        // Delete from MasterSalary
        masterSalaryRepository.deleteById(id);
    }

    @org.springframework.transaction.annotation.Transactional
    public void updateKomponenUpah(List<Long> ids, String upahTk) {
        if (ids == null || ids.isEmpty()) return;
        String sql = "UPDATE master_salary SET upah_tk = ? WHERE id = ?";
        List<Object[]> batchArgs = new ArrayList<>();
        for (Long id : ids) {
            batchArgs.add(new Object[]{upahTk, id});
        }
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    @org.springframework.transaction.annotation.Transactional
    public void updateStatus(List<Long> ids, String status, String keterangan) {
        if (ids == null || ids.isEmpty()) return;
        String sql = "UPDATE master_salary SET approval = ?, keterangan = ? WHERE id = ?";
        List<Object[]> batchArgs = new ArrayList<>();
        for (Long id : ids) {
            batchArgs.add(new Object[]{status, keterangan, id});
        }
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }
}
