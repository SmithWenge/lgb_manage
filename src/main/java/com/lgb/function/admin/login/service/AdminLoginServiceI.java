package com.lgb.function.admin.login.service;

import com.lgb.function.admin.login.AdminUser;

public interface AdminLoginServiceI {
    AdminUser login(AdminUser adminUser);
    AdminUser isExistAdminUser(AdminUser adminUser);
    boolean newPassword(AdminUser mailUser);

    AdminUser resetPassword(AdminUser adminUser);
}
