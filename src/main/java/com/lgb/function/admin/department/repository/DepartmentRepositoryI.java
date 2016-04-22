package com.lgb.function.admin.department.repository;

import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.login.AdminUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentRepositoryI {
    List<AdminUser> departmentAdmins();
    int selectName(Department department);
    boolean insert(Department department);
    Page<Department> select4Page(Pageable pageable);
    Department select(int departmentId);
    boolean update(Department department);
    boolean delete(int departmentId);
    int courseNum(int departmentId);
    int studentNum(int departmentId);
}
