package com.lgb.function.admin.department.repository;

import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.login.AdminUser;
import com.lgb.function.support.utils.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentRepository implements DepartmentRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils<Department> repositoryUtils;

    @Override
    public List<AdminUser> departmentAdmins() {
        String sql = "SELECT adminId, adminName FROM lgb_adminUser WHERE deleteFlag = 0 AND adminRole = 6";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new DepartmentAdminsRowMapper());
        } catch (Exception e) {
            return new ArrayList<AdminUser>();
        }
    }

    @Override
    public int selectName(Department department) {
        String sql = "SELECT COUNT(departmentId) as num FROM lgb_department WHERE deleteFlag = 0 AND departmentName = ?";
        Object[] args = {
                department.getDepartmentName()
        };

        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, args);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public boolean insert(Department department) {
        String sql = "INSERT INTO lgb_department (departmentName, departmentStartDate, departmentStopDate, adminId) VALUES (?, ?, ?, ?)";
        Object[] args = {
                department.getDepartmentName(),
                department.getDepartmentStartDate(),
                department.getDepartmentStopDate(),
                department.getAdminId()
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }

    @Override
    public Page<Department> select4Page(Pageable pageable) {
        String sql = "SELECT departmentId, departmentName, departmentStartDate, departmentStopDate FROM lgb_department WHERE deleteFlag = 0 ORDER BY departmentId";
        Object[] args = {

        };

        return repositoryUtils.select4Page(sql, pageable, args, new Select4PageRowMapper());
    }

    @Override
    public Department select(int departmentId) {
        String sql = "SELECT departmentId, departmentName, departmentStartDate, departmentStopDate, adminId FROM lgb_department WHERE deleteFlag = 0 AND departmentId = ?";
        Object[] args = {
                departmentId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(Department department) {
        String sql = "UPDATE lgb_department SET departmentName = ?, departmentStartDate = ?, departmentStopDate = ?, adminId = ? WHERE departmentId =? AND deleteFlag = 0";
        Object[] args = {
                department.getDepartmentName(),
                department.getDepartmentStartDate(),
                department.getDepartmentStopDate(),
                department.getAdminId(),
                department.getDepartmentId()
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }

    @Override
    public boolean delete(int departmentId) {
        String sql = "UPDATE lgb_department SET deleteFlag = 1 WHERE departmentId = ?";
        Object[] args = {
                departmentId
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }

    private class DepartmentAdminsRowMapper implements RowMapper<AdminUser> {

        @Override
        public AdminUser mapRow(ResultSet resultSet, int i) throws SQLException {
            AdminUser adminUser = new AdminUser();

            adminUser.setAdminId(resultSet.getInt("adminId"));
            adminUser.setAdminName(resultSet.getString("adminName"));

            return adminUser;
        }
    }

    private class Select4PageRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
            Department department = new Department();

            department.setDepartmentId(rs.getInt("departmentId"));
            department.setDepartmentName(rs.getString("departmentName"));
            department.setDepartmentStartDate(rs.getDate("departmentStartDate"));
            department.setDepartmentStopDate(rs.getDate("departmentStopDate"));

            return department;
        }
    }

    private class SelectRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
            Department department = new Department();

            department.setDepartmentId(rs.getInt("departmentId"));
            department.setDepartmentName(rs.getString("departmentName"));
            department.setDepartmentStartDate(rs.getTimestamp("departmentStartDate"));
            department.setDepartmentStopDate(rs.getTimestamp("departmentStopDate"));
            department.setAdminId(rs.getInt("adminId"));

            return department;
        }
    }

    @Override
    public int courseNum(int departmentId) {
        String sql = "SELECT COUNT(courseId) AS num FROM lgb_course WHERE departmentId = ?";
        Object[] args = {
                departmentId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, Integer.class);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int studentNum(int departmentId) {
        String sql = "SELECT COUNT(studentCourseId) AS num FROM lgb_studentCourse WHERE courseId IN (SELECT courseId FROM lgb_course WHERE departmentId = ?)";
        Object[] args = {
                departmentId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, Integer.class);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
