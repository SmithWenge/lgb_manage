package com.lgb.function.admin.login.service;

import com.google.common.base.Optional;
import com.lgb.function.admin.login.AdminUser;
import com.lgb.function.admin.login.repository.AdminLoginRepositoryI;
import com.lgb.function.support.log.LogContent;
import com.lgb.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminLoginService implements AdminLoginServiceI {
    @Autowired
    private AdminLoginRepositoryI adminLoginRepository;
    @Autowired
    private LogRepositoryI logRepository;

    public AdminUser login(AdminUser adminUser) {
        AdminUser loginUser = adminLoginRepository.selectUnique(adminUser);
        Optional<AdminUser> optional = Optional.fromNullable(loginUser);

        if (optional.isPresent()) {
            LogContent logContent = new LogContent(adminUser.getAdminLoginName(), "登陆系统", 1, 5);
            logRepository.insertLog(logContent);
        }

        return loginUser;
    }

    @Override
    public AdminUser isExistAdminUser(AdminUser adminUser) {
        AdminUser mailUser = adminLoginRepository.selectUniqueEmail(adminUser);
        Optional<AdminUser> optional = Optional.fromNullable(mailUser);

        if (optional.isPresent()) {
            LogContent logContent = new LogContent(adminUser.getAdminLoginName(), "更改密码", 1, 4);
            logRepository.insertLog(logContent);
        }

        return mailUser;
    }

    @Override
    public boolean newPassword(AdminUser mailUser) {
        return adminLoginRepository.updatePassword(mailUser);
    }
}
