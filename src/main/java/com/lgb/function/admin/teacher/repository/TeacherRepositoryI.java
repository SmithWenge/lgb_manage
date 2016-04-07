package com.lgb.function.admin.teacher.repository;

import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.teacher.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherRepositoryI {
    int selectCardNum(Teacher teacher);
    List<Department> selectDepartments();
    boolean insert(Teacher teacher);
    void insert(String subjectName, int teacherId);
    Page<Teacher> select4Page(Teacher teacher, Pageable pageable);
    int selectId(String teacherCardNum);
    Teacher selectCard(int teacherId);
    boolean selectIdAndCard(Teacher teacher);
    boolean updateCard(Teacher teacher);
    boolean update(Teacher teacher);
    Teacher select(int teacherId);
    boolean delete(Teacher teacher);
}