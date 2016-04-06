package com.lgb.function.admin.major.repository;

import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.major.Major;
import com.lgb.function.support.utils.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MajorRepository implements MajorRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils<Major> repositoryUtils;

    @Override
    public List<Department> selectDepartments() {
        String sql = "SELECT departmentId, departmentName FROM lgb_department WHERE deleteFlag = 0";
        Object[] args = {};

        return jdbcTemplate.query(sql, args, new SelectDepartmentsRowMapper());
    }

    private class SelectDepartmentsRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
            Department department = new Department();

            department.setDepartmentId(rs.getInt("departmentId"));
            department.setDepartmentName(rs.getString("departmentName"));

            return department;
        }
    }

    @Override
    public int selectName(Major major) {
        String sql = "SELECT COUNT(majorId) as num FROM lgb_major WHERE deleteFlag = 0 AND majorName = ?";
        Object[] args = {
                major.getMajorName()
        };

        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, args);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public boolean insert(Major major) {
        String sql = "INSERT INTO lgb_major (departmentId, majorName) VALUES (?, ?)";
        Object[] args = {
                major.getDepartmentId(),
                major.getMajorName()
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }

    @Override
    public Page<Major> select4Page(Pageable pageable) {
        String sql = "SELECT majorId, majorName, departmentName FROM lgb_major M LEFT JOIN lgb_department D ON M.departmentId = D.departmentId WHERE M.deleteFlag = 0 ORDER BY majorId";
        Object[] args = {

        };

        return repositoryUtils.select4Page(sql, pageable, args, new Select4PageRowMapper());
    }

    @Override
    public Major select(int majorId) {
        String sql = "SELECT majorId, departmentId, majorName FROM lgb_major WHERE deleteFlag = 0 AND majorId = ?";
        Object[] args = {
                majorId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(Major major) {
        String sql = "UPDATE lgb_major SET departmentId = ?, majorName = ? WHERE majorId = ?";
        Object[] args = {
                major.getDepartmentId(),
                major.getMajorName(),
                major.getMajorId()
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }

    @Override
    public boolean delete(int majorId) {
        String sql = "UPDATE lgb_major SET deleteFlag = 1 WHERE majorId = ?";
        Object[] args = {
                majorId
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }

    private class Select4PageRowMapper implements RowMapper<Major> {

        @Override
        public Major mapRow(ResultSet rs, int rowNum) throws SQLException {
            Major major = new Major();

            major.setMajorId(rs.getInt("majorId"));
            major.setMajorName(rs.getString("majorName"));
            major.setDepartmentName(rs.getString("departmentName"));

            return major;
        }
    }

    private class SelectRowMapper implements RowMapper<Major> {

        @Override
        public Major mapRow(ResultSet rs, int rowNum) throws SQLException {
            Major major = new Major();

            major.setMajorId(rs.getInt("majorId"));
            major.setMajorName(rs.getString("majorName"));
            major.setDepartmentId(rs.getInt("departmentId"));

            return major;
        }
    }
}
