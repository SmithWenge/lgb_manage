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

        AdminUser returnUser = jdbcTemplate.queryForObject(sql, args, new SelectUniqueRowMapper());

        return returnUser != null ? returnUser : null;
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
}
