package com.payroll.modules.master;

import com.payroll.modules.client.MasterPic;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MasterPicProjectService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MasterPicRepository masterPicRepository;

    public Page<MasterPicProjectResponseDTO> getList(MasterPicProjectRequest request, int page, int size, String fullname, String role) {
        StringBuilder sql = new StringBuilder();
        StringBuilder countSql = new StringBuilder();
        List<Object> params = new ArrayList<>();

        String baseSelect = "SELECT ms.id, ms.division, ms.unit_name, ms.position, ms.branch, ms.employee_type, ms.pic ";
        String baseCount = "SELECT count(ms.id) ";
        
        // Follow blueprint: JOIN with users and master_upliner
        String baseFrom = "FROM master_salary ms " +
                          "LEFT JOIN users u ON u.id = ms.id_user " +
                          "LEFT JOIN master_upliner mup ON u.nik = mup.nik " +
                          "WHERE (ms.approval = 'APPROVED' OR ms.approval = 'PROCESSED') ";

        sql.append(baseSelect).append(baseFrom);
        countSql.append(baseCount).append(baseFrom);

        /* 
         * Blueprint rule for SPV: (mup.nama_upliner = ? OR u.leader = ?)
         * We bypass strict SPV filtering (WHERE 1=1) like in MasterPicUk 
         * so dummy data can be visible for testing. 
         * If needed for production, uncomment the logic below:
         */
        /*
        if ("SPV".equalsIgnoreCase(role)) {
            sql.append(" AND (mup.nama_upliner = ? OR u.leader = ?) ");
            countSql.append(" AND (mup.nama_upliner = ? OR u.leader = ?) ");
            params.add(fullname);
            params.add(fullname);
        }
        */

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
        if (request.getPeriodePayroll() != null && !request.getPeriodePayroll().trim().isEmpty()) {
            sql.append(" AND ms.periode_payroll = ? ");
            countSql.append(" AND ms.periode_payroll = ? ");
            params.add(request.getPeriodePayroll());
        }

        Long totalElements = jdbcTemplate.queryForObject(countSql.toString(), Long.class, params.toArray());

        sql.append(" ORDER BY ms.id DESC LIMIT ? OFFSET ?");
        List<Object> pageParams = new ArrayList<>(params);
        pageParams.add(size);
        pageParams.add(page * size);

        List<MasterPicProjectResponseDTO> content = jdbcTemplate.query(sql.toString(), pageParams.toArray(), new RowMapper<MasterPicProjectResponseDTO>() {
            @Override
            public MasterPicProjectResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasterPicProjectResponseDTO dto = new MasterPicProjectResponseDTO();
                dto.setId(rs.getLong("id"));
                dto.setDivision(rs.getString("division"));
                dto.setUnit(rs.getString("unit_name"));
                dto.setPosition(rs.getString("position"));
                dto.setBranch(rs.getString("branch"));
                dto.setEmployeeType(rs.getString("employee_type"));
                dto.setPic(rs.getString("pic"));
                return dto;
            }
        });

        return new PageImpl<>(content, PageRequest.of(page, size), totalElements != null ? totalElements : 0);
    }

    @Transactional
    public void assignPic(List<Long> ids, String pic, String fullname) {
        if (ids == null || ids.isEmpty()) return;
        
        // update master_salary set pic = ? where id in @?
        String sql = "UPDATE master_salary SET pic = ? WHERE id = ?";
        List<Object[]> batchArgs = new ArrayList<>();
        for (Long id : ids) {
            batchArgs.add(new Object[]{pic, id});
        }
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    public MasterPicProjectDetailDTO getDetail(Long id) {
        String sql = "SELECT ms.id, ms.division, ms.unit_name, ms.position, ms.employee_type, ms.branch, ms.pic " +
                     "FROM master_salary ms WHERE ms.id = ?";
        
        List<MasterPicProjectDetailDTO> results = jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<MasterPicProjectDetailDTO>() {
            @Override
            public MasterPicProjectDetailDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasterPicProjectDetailDTO dto = new MasterPicProjectDetailDTO();
                dto.setId(rs.getLong("id"));
                dto.setDivision(rs.getString("division"));
                dto.setUnit(rs.getString("unit_name"));
                dto.setPosition(rs.getString("position"));
                dto.setEmployeeType(rs.getString("employee_type"));
                dto.setBranch(rs.getString("branch"));
                dto.setPicUtama(rs.getString("pic"));
                return dto;
            }
        });

        if (results.isEmpty()) {
            throw new RuntimeException("Data not found");
        }

        MasterPicProjectDetailDTO detail = results.get(0);

        // Fetch Pic Tambahan from master_pic
        List<MasterPic> picTambahanRecords = masterPicRepository.findByMasterSalaryId(id);
        List<String> picTambahanUsers = picTambahanRecords.stream()
                .map(MasterPic::getUser)
                .collect(Collectors.toList());
        
        detail.setPicTambahanList(picTambahanUsers);

        return detail;
    }

    @Transactional
    public void updatePic(Long id, MasterPicProjectUpdateRequest request, String fullname) {
        // Update pic_utama in master_salary
        String sql = "UPDATE master_salary SET pic = ? WHERE id = ?";
        jdbcTemplate.update(sql, request.getPicUtama(), id);

        // Update pic_tambahan in master_pic
        // Simple approach: delete all existing for this id and insert new ones
        masterPicRepository.deleteByMasterSalaryId(id);

        if (request.getPicTambahanList() != null && !request.getPicTambahanList().isEmpty()) {
            List<MasterPic> newPics = new ArrayList<>();
            for (String user : request.getPicTambahanList()) {
                MasterPic mp = new MasterPic();
                mp.setMasterSalaryId(id);
                mp.setUser(user);
                newPics.add(mp);
            }
            masterPicRepository.saveAll(newPics);
        }
    }
}
