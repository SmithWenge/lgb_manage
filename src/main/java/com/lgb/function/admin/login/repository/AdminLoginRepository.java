package com.lgb.function.admin.login.repository;

import com.lgb.function.admin.login.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AdminLoginRepository implements AdminLoginRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AdminUser selectUnique(AdminUser adminUser) {
        String sql = "SELECT adminName, adminLoginName, adminRole FROM lgb_adminUser WHERE deleteFlag = 0 AND adminRole = 1 AND adminIsLock = 0 AND adminLoginName = ? AND adminLoginPass = ?";
        Object[] args = {
                adminUser.getAdminLoginName(),
                adminUser.getAdminLoginPass()
        };

        AdminUser returnUser = null;

        try {
            returnUser = jdbcTemplate.queryForObject(sql, args, new SelectUniqueRowMapper());
        } catch (Exception e) {
            return null;
        }

        return returnUser != null ? returnUser : null;
    }

    @Override
    public AdminUser selectUniqueEmail(AdminUser adminUser) {
        String sql = "SELECT adminLoginName, adminEmail, adminName FROM lgb_adminUser WHERE adminLoginName = ? AND adminEmail = ? AND deleteFlag = 0 AND adminIsLock = 0";
        Object[] args = {
                adminUser.getAdminLoginName(),
                adminUser.getAdminEmail()
        };

        AdminUser emailUser = jdbcTemplate.queryForObject(sql, args, new SelectUniqueEmailRowMapper());

        return emailUser != null ? emailUser : null;
    }

    @Override
    public boolean updatePassword(AdminUser adminUser) {
        String sql = "UPDATE lgb_adminUser SET adminLoginPass = ? WHERE adminLoginName = ? AND adminEmail = ? AND adminIsLock = 0 AND deleteFlag = 0";
        Object[] args = {
                adminUser.getAdminLoginPass(),
                adminUser.getAdminLoginName(),
                adminUser.getAdminEmail()
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }

    private class SelectUniqueRowMapper implements RowMapper<AdminUser> {

        @Override
        public AdminUser mapRow(ResultSet resultSet, int i) throws SQLException {
            AdminUser adminUser = new AdminUser();

            adminUser.setAdminLoginName(resultSet.getString("adminLoginName"));
            adminUser.setAdminName(resultSet.getString("adminName"));
            adminUser.setAdminRole(resultSet.getInt("adminRole"));

            return adminUser;
        }
    }

    private class SelectUniqueEmailRowMapper implements RowMapper<AdminUser> {

        @Override
        public AdminUser mapRow(ResultSet resultSet, int i) throws SQLException {
            AdminUser adminUser = new AdminUser();

            adminUser.setAdminLoginName(resultSet.getString("adminLoginName"));
            adminUser.setAdminName(resultSet.getString("adminName"));
            adminUser.setAdminEmail(resultSet.getString("adminEmail"));

            return adminUser;
        }
    }
}
