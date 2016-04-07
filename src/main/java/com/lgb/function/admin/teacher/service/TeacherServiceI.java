package com.lgb.function.admin.teacher.service;

import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.teacher.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherServiceI {
    boolean existCardNum(Teacher teacher);
    List<Department> departments();
    boolean add(Teacher teacher, String logUser);
    Page<Teacher> query4Page(Teacher teacher, Pageable pageable);
    Teacher selectCard(int teacherId);
    boolean turnCard(Teacher teacher, String logUser);
    boolean edit(Teacher teacher, String logUser);
    Teacher select(int teacherId);
    boolean delete(Teacher teacher, String logUser);
}
