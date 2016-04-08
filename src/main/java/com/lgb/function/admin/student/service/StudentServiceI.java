package com.lgb.function.admin.student.service;

import com.lgb.function.admin.login.AdminUser;
import com.lgb.function.admin.student.StudentUser;
import com.lgb.function.admin.teacher.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentServiceI {
    Page<StudentUser> list(StudentUser studentUser,Pageable pageable);
    boolean add(StudentUser studentUser, String logUser);
    StudentUser select(int stuId);
    boolean edit(StudentUser studentUser, String logUser);
    boolean delete(int stuId, String logUser);
    StudentUser selectCard(int stuID);
    boolean turnCard(StudentUser studentUser, String logUser);
    boolean existCardNum(StudentUser studentUser);
    List<StudentUser> exportAllStu();
}
