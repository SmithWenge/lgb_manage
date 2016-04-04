package com.lgb.function.admin.login.repository;

import com.lgb.function.admin.login.AdminUser;

public interface AdminLoginRepositoryI {
    AdminUser selectUnique(AdminUser adminUser);
}
