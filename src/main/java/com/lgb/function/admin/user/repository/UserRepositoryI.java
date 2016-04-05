package com.lgb.function.admin.user.repository;

import com.lgb.function.admin.login.AdminUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryI {
    Page<AdminUser> query4Page(Pageable pageable);
    boolean insert(AdminUser adminUser);
    AdminUser select(int adminId);
    boolean update(AdminUser adminUser);
    boolean delete(int adminId);
}
