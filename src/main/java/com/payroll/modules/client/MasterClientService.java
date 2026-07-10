package com.payroll.modules.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MasterClientService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private void appendSearchFilters(StringBuilder sql, MasterClientSearchRequest request, List<Object> params) {
        if (request.getSearch() != null && !request.getSearch().trim().isEmpty()) {
            String search = "%" + request.getSearch().trim() + "%";
            sql.append(" AND (ms.division LIKE ? OR ms.unit_name LIKE ? OR ms.position LIKE ? OR ms.branch LIKE ? OR ms.employee_type LIKE ? OR ms.approval LIKE ? OR monthname(STR_TO_DATE(ms.periode_payroll,'%m')) LIKE ?) ");
            for (int i = 0; i < 7; i++) params.add(search);
        }
        if (request.getDivision() != null && !request.getDivision().trim().isEmpty()) {
            sql.append(" AND ms.division = ? ");
            params.add(request.getDivision());
        }
        if (request.getUnitName() != null && !request.getUnitName().trim().isEmpty()) {
            sql.append(" AND ms.unit_name = ? ");
            params.add(request.getUnitName());
        }
        if (request.getPosition() != null && !request.getPosition().trim().isEmpty()) {
            sql.append(" AND ms.position = ? ");
            params.add(request.getPosition());
        }
        if (request.getBranch() != null && !request.getBranch().trim().isEmpty()) {
            sql.append(" AND ms.branch = ? ");
            params.add(request.getBranch());
        }
        if (request.getStatus() != null && !request.getStatus().trim().isEmpty()) {
            sql.append(" AND ms.approval = ? ");
            params.add(request.getStatus());
        }
    }

    public List<MasterClientResponseDTO> getStaffList(Long userId, String fullname, MasterClientSearchRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT ms.id, ms.division AS Division, ms.unit_name AS Unit, ms.position AS Position, ms.branch AS Branch, ");
        sql.append("ms.employee_type AS Employee_Type, DATE_FORMAT(ms.created_date,'%d/%m/%Y') AS Created_Date, DATE_FORMAT(ms.update_date,'%d/%m/%Y') AS Update_Date, ");
        sql.append("ms.created_by AS Created_By, ms.approval AS Status ");
        sql.append("FROM master_salary ms ");
        sql.append("LEFT JOIN users u ON u.id = ms.id_user ");
        sql.append("LEFT JOIN master_pic mp ON mp.master_salary_id = ms.id ");
        sql.append("WHERE ((ms.id_user = ? AND ms.approval = 'REQUEST') ");
        sql.append("OR (mp.user = ? AND ms.approval = 'APPROVED') ");
        sql.append("OR (ms.pic = ? AND ms.approval = 'APPROVED')) ");
        sql.append("AND (ms.approval != 'DONE') ");

        List<Object> params = new ArrayList<>();
        params.add(userId);
        params.add(fullname);
        params.add(fullname);

        appendSearchFilters(sql, request, params);

        sql.append(" ORDER BY ms.created_date DESC");

        return jdbcTemplate.query(sql.toString(), params.toArray(), new StaffRowMapper());
    }

    public List<MasterClientResponseDTO> getStaffExportByFilter(Long userId, String fullname, String uplinerName, MasterClientSearchRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ROW_NUMBER() OVER (ORDER BY ms.division, ms.unit_name, ms.position, ms.branch, ms.employee_type ASC) AS No, ");
        sql.append("ms.division AS Division, ms.unit_name AS Unit, ms.position AS Position, ms.branch AS Branch, UPPER(ms.employee_type) AS Employee_Type ");
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
        
        sql.append("AND (ms.id_user = ? OR mp.user = ? OR (ms.created_by = ? AND ms.approval = 'APPROVED')) ");
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
        sql.append("SELECT @NO := CAST(@NO + 1 AS UNSIGNED) AS No, ");
        sql.append("ms.division AS Division, ms.unit_name AS Unit, ms.position AS Position, ms.branch AS Branch, UPPER(ms.employee_type) AS Employee_Type ");
        sql.append("FROM master_salary ms LEFT JOIN users u ON u.id = ms.id_user, (SELECT @NO := 0) AS nomor ");
        sql.append("WHERE ms.id IN (");
        
        String inSql = ids.stream().map(id -> "?").collect(Collectors.joining(","));
        sql.append(inSql).append(") ");
        sql.append("ORDER BY ms.division, ms.unit_name, ms.position, ms.branch, ms.employee_type ASC");

        return jdbcTemplate.query(sql.toString(), ids.toArray(), new ExportRowMapper());
    }

    public List<MasterClientResponseDTO> getSpvList(MasterClientSearchRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT ms.id, ms.division AS Division, ms.unit_name AS Unit, ms.position AS Position, ms.branch AS Branch, ");
        sql.append("ms.employee_type AS Employee_Type, DATE_FORMAT(ms.created_date,'%d/%m/%Y') AS Created_Date, DATE_FORMAT(ms.update_date,'%d/%m/%Y') AS Update_Date, ");
        sql.append("ms.created_by AS Created_By, ms.approval AS Status, IF(ms.approval='REQUEST', ms.keterangan, '') AS Keterangan ");
        sql.append("FROM master_salary ms ");
        sql.append("WHERE (ms.approval = 'REQUEST' OR ms.approval = 'APPROVED' OR ms.approval = 'PROCESSED') ");

        List<Object> params = new ArrayList<>();
        appendSearchFilters(sql, request, params);

        sql.append(" ORDER BY ms.created_date DESC");

        return jdbcTemplate.query(sql.toString(), params.toArray(), new SpvRowMapper());
    }

    public List<MasterClientResponseDTO> getSpvExportByFilter(String fullname, MasterClientSearchRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT @NO := CAST(@NO + 1 AS UNSIGNED) AS No, ms.division AS Division, ms.unit_name AS Unit, ms.position AS Position, ms.branch AS Branch, UPPER(ms.employee_type) AS Employee_Type ");
        sql.append("FROM master_salary ms ");
        sql.append("LEFT JOIN users u ON u.id = ms.id_user ");
        sql.append("LEFT JOIN master_upliner mup ON u.nik = mup.nik, (SELECT @NO := 0) AS nomor ");
        
        List<Object> params = new ArrayList<>();
        if (request.getDivision() != null && !request.getDivision().isEmpty()) {
            sql.append("WHERE ms.division = ? ");
            params.add(request.getDivision());
        } else {
            sql.append("WHERE 1=1 ");
        }

        sql.append("AND (mup.nama_upliner = ? OR u.leader = ?) ");
        sql.append("AND (ms.approval IN ('REQUEST', 'APPROVED', 'PROCESSED')) ");
        
        params.add(fullname);
        params.add(fullname);

        appendSearchFilters(sql, request, params);

        sql.append(" GROUP BY ms.id");

        return jdbcTemplate.query(sql.toString(), params.toArray(), new ExportRowMapper());
    }

    public List<MasterClientResponseDTO> getSpvExportByChecklist(List<Long> ids) {
        if (ids == null || ids.isEmpty()) return new ArrayList<>();
        
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT @NO := CAST(@NO + 1 AS UNSIGNED) AS No, ");
        sql.append("ms.division AS Division, ms.unit_name AS Unit, ms.position AS Position, ms.branch AS Branch, UPPER(ms.employee_type) AS Employee_Type ");
        sql.append("FROM master_salary ms LEFT JOIN users u ON u.id = ms.id_user, (SELECT @NO := 0) AS nomor ");
        sql.append("WHERE ms.id IN (");
        
        String inSql = ids.stream().map(id -> "?").collect(Collectors.joining(","));
        sql.append(inSql).append(") ");
        sql.append("ORDER BY ms.division, ms.unit_name, ms.position, ms.branch, ms.employee_type ASC");

        return jdbcTemplate.query(sql.toString(), ids.toArray(), new ExportRowMapper());
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
            return dto;
        }
    }
}