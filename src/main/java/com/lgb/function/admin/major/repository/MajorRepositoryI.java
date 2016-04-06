package com.lgb.function.admin.major.repository;

import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.major.Major;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MajorRepositoryI {
    List<Department> selectDepartments();
    int selectName(Major major);
    boolean insert(Major major);
    Page<Major> select4Page(Pageable pageable);
    Major select(int majorId);
    boolean update(Major major);
    boolean delete(int majorId);
}
