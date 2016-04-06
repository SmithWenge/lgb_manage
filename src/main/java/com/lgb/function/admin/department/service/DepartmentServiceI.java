package com.lgb.function.admin.department.service;

import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.login.AdminUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentServiceI {
    List<AdminUser> listDepartmentAdmins();
    boolean exist(Department department);
    boolean add(Department department, String logUser);
    Page<Department> list(Pageable pageable);
    Department select(int departmentId);
    boolean edit(Department department, String logUser);
    boolean delete(int departmentId, String logUser);
}
