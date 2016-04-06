package com.lgb.function.admin.student.repository;

import com.lgb.function.admin.student.StudentUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface StudentRepositoryI {
    Page<StudentUser> query4Page(Pageable pageable);
    boolean insert(StudentUser studentUser);
    StudentUser select(int stuID);
    boolean update(StudentUser studentUser);
    boolean delete(int stuId);
}
