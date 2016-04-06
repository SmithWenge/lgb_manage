package com.lgb.function.admin.major.service;

import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.major.Major;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MajorServiceI {
    List<Department> departments();
    boolean exist(Major major);
    boolean add(Major major, String logUser);
    Page<Major> list(Pageable pageable);
    Major select(int majorId);
    boolean edit(Major major, String logUser);
    boolean delete(int majorId, String logUser);
}
