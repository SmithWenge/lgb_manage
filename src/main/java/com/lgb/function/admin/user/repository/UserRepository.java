package com.lgb.function.admin.user.repository;

import com.lgb.function.admin.login.AdminUser;
import com.lgb.function.support.dictionary.impl.DefaultDictionaryManager;
import com.lgb.function.support.utils.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository implements UserRepositoryI {
    @Autowired
    private RepositoryUtils<AdminUser> repositoryUtils;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Page<AdminUser> query4Page(Pageable pageable) {
        String sql = "SELECT adminId, adminLoginName, adminName, adminIsLock, adminRole, adminEmail, adminIsChanged, adminIsReturn FROM lgb_adminUser WHERE deleteFlag = 0 ORDER BY adminId";

        return repositoryUtils.select4Page(sql, pageable, null, new Query4PageRowMapper());
    }

    @Override
    public boolean insert(AdminUser adminUser) {
        String sql = "INSERT INTO lgb_adminUser (adminName, adminLoginName, adminLoginPass, adminIsLock, adminRole, adminEmail) VALUES (?, ?, ?, ?, ?, ?)";
        Object[] args = {
                adminUser.getAdminName(),
                adminUser.getAdminLoginName(),
                adminUser.getAdminLoginPass(),
                adminUser.getAdminIsLock(),
                adminUser.getAdminRole(),
                adminUser.getAdminEmail()
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }

    @Override
    public AdminUser select(int adminId) {
        String sql = "SELECT adminId, adminLoginName, adminName, adminIsLock, adminRole, adminEmail, adminIsChanged, adminIsReturn FROM lgb_adminUser WHERE deleteFlag = 0 AND adminId = ?";
        Object[] args = {
                adminId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new Query4PageRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(AdminUser adminUser) {
        String sql = "UPDATE lgb_adminUser SET adminName = ?, adminLoginName = ?, adminIsLock = ?, adminRole = ?, adminEmail = ?, adminIsChanged = ?, adminIsReturn = ? WHERE adminId = ?";
        Object[] args = {
                adminUser.getAdminName(),
                adminUser.getAdminLoginName(),
                adminUser.getAdminIsLock(),
                adminUser.getAdminRole(),
                adminUser.getAdminEmail(),
                adminUser.getAdminIsChanged(),
                adminUser.getAdminIsReturn(),
                adminUser.getAdminId()
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }

    @Override
    public boolean delete(int adminId) {
        String sql = "UPDATE lgb_adminUser SET deleteFlag = 1 WHERE adminId = ?";
        Object[] args = {
                adminId
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }


    private class Query4PageRowMapper implements RowMapper<AdminUser> {

        @Override
        public AdminUser mapRow(ResultSet resultSet, int i) throws SQLException {
            AdminUser adminUser = new AdminUser();

            adminUser.setAdminId(resultSet.getInt("adminId"));
            adminUser.setAdminName(resultSet.getString("adminName"));
            int adminIsLock = resultSet.getInt("adminIsLock");
            adminUser.setAdminIsLock(adminIsLock);
            int adminRole = resultSet.getInt("adminRole");
            adminUser.setAdminRole(adminRole);
            adminUser.setAdminEmail(resultSet.getString("adminEmail"));
            int adminIsChanged = resultSet.getInt("adminIsChanged");
            adminUser.setAdminIsChanged(adminIsChanged);
            int adminIsReturn = resultSet.getInt("adminIsReturn");
            adminUser.setAdminIsReturn(adminIsReturn);
            adminUser.setAdminLoginName(resultSet.getString("adminLoginName"));

            return adminUser;
        }
    }
}
