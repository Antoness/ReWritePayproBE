package com.payroll.modules.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MasterPicUkService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private HistoryMfeeUkRepository historyMfeeUkRepository;

    public Page<MasterPicUkResponseDTO> searchMasterPicUk(MasterPicUkRequest request, int page, int size, String fullname, String role) {
        StringBuilder sql = new StringBuilder();
        StringBuilder countSql = new StringBuilder();
        List<Object> params = new ArrayList<>();

        if ("SPV".equalsIgnoreCase(role)) {
            sql.append("SELECT ms.id, ms.division, ms.unit_name, ms.position, ms.branch, ms.employee_type, ms.mode_uk, ms.mfee_uk, ms.approval_mfee_uk ");
            sql.append("FROM master_salary ms WHERE 1=1 ");
            
            countSql.append("SELECT count(ms.id) FROM master_salary ms WHERE 1=1 ");
        } else {
            sql.append("SELECT ms.id, ms.division, ms.unit_name, ms.position, ms.branch, ms.employee_type, ms.mode_uk, ms.mfee_uk, ms.approval_mfee_uk ");
            sql.append("FROM master_salary ms WHERE 1=1 ");

            countSql.append("SELECT count(ms.id) FROM master_salary ms WHERE 1=1 ");
        }

        if (request.getSearch() != null && !request.getSearch().trim().isEmpty()) {
            String searchPattern = "%" + request.getSearch().toLowerCase() + "%";
            String searchClause = " AND (LOWER(ms.division) LIKE ? OR LOWER(ms.unit_name) LIKE ? OR LOWER(ms.position) LIKE ? OR LOWER(ms.branch) LIKE ?) ";
            sql.append(searchClause);
            countSql.append(searchClause);
            params.add(searchPattern);
            params.add(searchPattern);
            params.add(searchPattern);
            params.add(searchPattern);
        }

        if (request.getDivision() != null && !request.getDivision().trim().isEmpty()) {
            sql.append(" AND ms.division = ? ");
            countSql.append(" AND ms.division = ? ");
            params.add(request.getDivision());
        }
        if (request.getUnitName() != null && !request.getUnitName().trim().isEmpty()) {
            sql.append(" AND ms.unit_name = ? ");
            countSql.append(" AND ms.unit_name = ? ");
            params.add(request.getUnitName());
        }
        if (request.getPosition() != null && !request.getPosition().trim().isEmpty()) {
            sql.append(" AND ms.position = ? ");
            countSql.append(" AND ms.position = ? ");
            params.add(request.getPosition());
        }
        if (request.getBranch() != null && !request.getBranch().trim().isEmpty()) {
            sql.append(" AND ms.branch = ? ");
            countSql.append(" AND ms.branch = ? ");
            params.add(request.getBranch());
        }
        if (request.getEmployeeType() != null && !request.getEmployeeType().trim().isEmpty()) {
            sql.append(" AND ms.employee_type = ? ");
            countSql.append(" AND ms.employee_type = ? ");
            params.add(request.getEmployeeType());
        }
        if (request.getModeUk() != null && !request.getModeUk().trim().isEmpty()) {
            sql.append(" AND ms.mode_uk = ? ");
            countSql.append(" AND ms.mode_uk = ? ");
            params.add(request.getModeUk());
        }

        Long totalElements = jdbcTemplate.queryForObject(countSql.toString(), Long.class, params.toArray());

        sql.append(" ORDER BY ms.id DESC LIMIT ? OFFSET ?");
        List<Object> pageParams = new ArrayList<>(params);
        pageParams.add(size);
        pageParams.add(page * size);

        List<MasterPicUkResponseDTO> content = jdbcTemplate.query(sql.toString(), pageParams.toArray(), new RowMapper<MasterPicUkResponseDTO>() {
            @Override
            public MasterPicUkResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasterPicUkResponseDTO dto = new MasterPicUkResponseDTO();
                dto.setId(rs.getLong("id"));
                dto.setDivision(rs.getString("division"));
                dto.setUnit(rs.getString("unit_name"));
                dto.setPosition(rs.getString("position"));
                dto.setBranch(rs.getString("branch"));
                dto.setEmployeeType(rs.getString("employee_type"));
                dto.setModeUk(rs.getString("mode_uk"));
                
                Double mfee = rs.getDouble("mfee_uk");
                if (!rs.wasNull()) {
                    dto.setMfee(mfee);
                }
                
                dto.setApproval(rs.getString("approval_mfee_uk"));
                return dto;
            }
        });

        return new PageImpl<>(content, PageRequest.of(page, size), totalElements != null ? totalElements : 0);
    }

    public Page<HistoryMfeeUkResponseDTO> getHistoryLog(MasterPicUkRequest request, int page, int size, String fullname, String role) {
        StringBuilder sql = new StringBuilder();
        StringBuilder countSql = new StringBuilder();
        List<Object> params = new ArrayList<>();

        String selectFields = "SELECT ms.division, ms.unit_name, ms.position, ms.branch, ms.employee_type, ms.mode_uk, h.mfee, h.mfee_uk_old, TO_CHAR(h.created_date, 'DD-MM-YYYY HH24:MI:SS') as created_date, h.created_by, ms.approval_mfee_uk as approval ";
        
        if ("SPV".equalsIgnoreCase(role)) {
            sql.append(selectFields);
            sql.append("FROM history_mfee_uk h JOIN master_salary ms ON h.master_salary_id = ms.id ");
            sql.append("WHERE 1=1 ");
            
            countSql.append("SELECT count(h.id) ");
            countSql.append("FROM history_mfee_uk h JOIN master_salary ms ON h.master_salary_id = ms.id ");
            countSql.append("WHERE 1=1 ");
        } else {
            sql.append(selectFields);
            sql.append("FROM history_mfee_uk h JOIN master_salary ms ON h.master_salary_id = ms.id WHERE 1=1 ");

            countSql.append("SELECT count(h.id) FROM history_mfee_uk h JOIN master_salary ms ON h.master_salary_id = ms.id WHERE 1=1 ");
        }

        if (request.getSearch() != null && !request.getSearch().trim().isEmpty()) {
            String searchPattern = "%" + request.getSearch().toLowerCase() + "%";
            String searchClause = " AND (LOWER(ms.division) LIKE ? OR LOWER(ms.unit_name) LIKE ? OR LOWER(ms.position) LIKE ? OR LOWER(ms.branch) LIKE ?) ";
            sql.append(searchClause);
            countSql.append(searchClause);
            params.add(searchPattern);
            params.add(searchPattern);
            params.add(searchPattern);
            params.add(searchPattern);
        }

        if (request.getDivision() != null && !request.getDivision().trim().isEmpty()) {
            sql.append(" AND ms.division = ? ");
            countSql.append(" AND ms.division = ? ");
            params.add(request.getDivision());
        }
        if (request.getUnitName() != null && !request.getUnitName().trim().isEmpty()) {
            sql.append(" AND ms.unit_name = ? ");
            countSql.append(" AND ms.unit_name = ? ");
            params.add(request.getUnitName());
        }
        if (request.getPosition() != null && !request.getPosition().trim().isEmpty()) {
            sql.append(" AND ms.position = ? ");
            countSql.append(" AND ms.position = ? ");
            params.add(request.getPosition());
        }
        if (request.getBranch() != null && !request.getBranch().trim().isEmpty()) {
            sql.append(" AND ms.branch = ? ");
            countSql.append(" AND ms.branch = ? ");
            params.add(request.getBranch());
        }
        if (request.getEmployeeType() != null && !request.getEmployeeType().trim().isEmpty()) {
            sql.append(" AND ms.employee_type = ? ");
            countSql.append(" AND ms.employee_type = ? ");
            params.add(request.getEmployeeType());
        }

        Long totalElements = jdbcTemplate.queryForObject(countSql.toString(), Long.class, params.toArray());

        sql.append(" ORDER BY h.created_date DESC LIMIT ? OFFSET ?");
        List<Object> pageParams = new ArrayList<>(params);
        pageParams.add(size);
        pageParams.add(page * size);

        List<HistoryMfeeUkResponseDTO> content = jdbcTemplate.query(sql.toString(), pageParams.toArray(), new RowMapper<HistoryMfeeUkResponseDTO>() {
            @Override
            public HistoryMfeeUkResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                HistoryMfeeUkResponseDTO dto = new HistoryMfeeUkResponseDTO();
                dto.setDivision(rs.getString("division"));
                dto.setUnit(rs.getString("unit_name"));
                dto.setPosition(rs.getString("position"));
                dto.setBranch(rs.getString("branch"));
                dto.setEmployeeType(rs.getString("employee_type"));
                dto.setModeUk(rs.getString("mode_uk"));
                
                Double mfee = rs.getDouble("mfee");
                if (!rs.wasNull()) dto.setMfee(mfee);
                
                Double mfeeOld = rs.getDouble("mfee_uk_old");
                if (!rs.wasNull()) dto.setMfeeUkOld(mfeeOld);
                
                dto.setCreatedDate(rs.getString("created_date"));
                dto.setCreatedBy(rs.getString("created_by"));
                dto.setApproval(rs.getString("approval"));
                return dto;
            }
        });

        return new PageImpl<>(content, PageRequest.of(page, size), totalElements != null ? totalElements : 0);
    }

    @Transactional
    public void assignModeUk(MasterPicUkRequest request) {
        if (request.getIds() == null || request.getIds().isEmpty()) return;
        
        String inSql = String.join(",", java.util.Collections.nCopies(request.getIds().size(), "?"));
        String sql = "UPDATE master_salary SET mode_uk = ? WHERE id IN (" + inSql + ")";
        
        List<Object> params = new ArrayList<>();
        params.add(request.getAssignModeUk());
        params.addAll(request.getIds());
        
        jdbcTemplate.update(sql, params.toArray());
    }

    @Transactional
    public void requestUpdateMfee(MasterPicUkRequest request, String username) {
        if (request.getIds() == null || request.getIds().isEmpty()) return;

        String inSql = String.join(",", java.util.Collections.nCopies(request.getIds().size(), "?"));

        // Step 1: Copy current mfee_uk to mfee_uk_old
        String updateOldSql = "UPDATE master_salary SET mfee_uk_old = mfee_uk WHERE id IN (" + inSql + ")";
        jdbcTemplate.update(updateOldSql, request.getIds().toArray());

        // Step 2: Insert into history_mfee_uk
        String insertHistorySql = "INSERT INTO history_mfee_uk (master_salary_id, mfee, mfee_uk_old, created_by, created_date, approval) " +
                                  "SELECT id, ?, mfee_uk_old, ?, ?, 'Waiting Approval' FROM master_salary WHERE id IN (" + inSql + ")";
        List<Object> historyParams = new ArrayList<>();
        historyParams.add(request.getUpdateMfee());
        historyParams.add(username);
        historyParams.add(new Date());
        historyParams.addAll(request.getIds());
        jdbcTemplate.update(insertHistorySql, historyParams.toArray());

        // Step 3: Update master_salary to Waiting Approval
        String updateSql = "UPDATE master_salary SET mfee_uk = ?, approval_mfee_uk = 'Waiting Approval' WHERE id IN (" + inSql + ")";
        List<Object> updateParams = new ArrayList<>();
        updateParams.add(request.getUpdateMfee());
        updateParams.addAll(request.getIds());
        jdbcTemplate.update(updateSql, updateParams.toArray());
    }

    @Transactional
    public void approveMfee(MasterPicUkRequest request) {
        if (request.getIds() == null || request.getIds().isEmpty()) return;
        
        String inSql = String.join(",", java.util.Collections.nCopies(request.getIds().size(), "?"));
        String sql = "UPDATE master_salary SET mfee_uk_old = null, approval_mfee_uk = 'Approved' WHERE id IN (" + inSql + ")";
        
        jdbcTemplate.update(sql, request.getIds().toArray());
    }

    @Transactional
    public void rejectMfee(MasterPicUkRequest request) {
        if (request.getIds() == null || request.getIds().isEmpty()) return;
        
        String inSql = String.join(",", java.util.Collections.nCopies(request.getIds().size(), "?"));
        // Revert to old value and set status to REJECT
        String sql = "UPDATE master_salary SET mfee_uk = COALESCE(mfee_uk_old, mfee_uk), approval_mfee_uk = 'REJECT' WHERE id IN (" + inSql + ")";
        
        jdbcTemplate.update(sql, request.getIds().toArray());
    }
}
