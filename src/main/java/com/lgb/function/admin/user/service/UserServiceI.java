package com.lgb.function.admin.user.service;

import com.lgb.function.admin.login.AdminUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserServiceI {
    Page<AdminUser> list(Pageable pageable);
    boolean add(AdminUser adminUser, String logUser);
    AdminUser select(int adminId);
    boolean edit(AdminUser adminUser, String logUser);
    boolean delete(int adminId, String logUser);
}
