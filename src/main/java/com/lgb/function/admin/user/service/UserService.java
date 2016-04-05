package com.lgb.function.admin.user.service;

import com.google.common.base.Optional;
import com.lgb.arc.utils.PasswordUtils;
import com.lgb.function.admin.login.AdminUser;
import com.lgb.function.admin.user.repository.UserRepositoryI;
import com.lgb.function.support.log.LogContent;
import com.lgb.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements UserServiceI {
    @Autowired
    private UserRepositoryI userRepository;
    @Autowired
    private LogRepositoryI logRepository;

    @Override
    public Page<AdminUser> list(Pageable pageable) {
        return userRepository.query4Page(pageable);
    }

    @Override
    public boolean add(AdminUser adminUser, String logUser) {
        adminUser.setAdminLoginPass(PasswordUtils.encrypt(adminUser.getAdminLoginPass()));
        boolean tmp = userRepository.insert(adminUser);

        if (tmp) {
            LogContent logContent = new LogContent(logUser, "添加用户" + adminUser.getAdminLoginName(), 1, 3);
            logRepository.insertLog(logContent);
        }
        return tmp;
    }

    @Override
    public AdminUser select(int adminId) {
        return userRepository.select(adminId);
    }

    @Override
    public boolean edit(AdminUser adminUser, String logUser) {
        AdminUser user = userRepository.select(adminUser.getAdminId());

        Optional<AdminUser> optional = Optional.fromNullable(user);

        if (optional.isPresent()) {
            boolean tmp = userRepository.update(adminUser);

            if (tmp) {
                LogContent logContent = new LogContent(logUser, "编辑用户" + adminUser.getAdminLoginName(), 1, 4);
                logRepository.insertLog(logContent);
            }

            return tmp;
        }

        return false;
    }

    @Override
    public boolean delete(int adminId, String logUser) {
        AdminUser user = userRepository.select(adminId);

        Optional<AdminUser> optional = Optional.fromNullable(user);

        if (optional.isPresent()) {
            boolean tmp = userRepository.delete(adminId);

            if (tmp) {
                LogContent logContent = new LogContent(logUser, "删除用户ID为" + adminId, 1, 2);
                logRepository.insertLog(logContent);
            }

            return tmp;
        }

        return false;
    }
}
