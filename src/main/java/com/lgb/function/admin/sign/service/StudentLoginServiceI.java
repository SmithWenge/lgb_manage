package com.lgb.function.admin.sign.service;

import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.major.Major;
import com.lgb.function.admin.sign.StudentCourse;
import com.lgb.function.admin.student.StudentUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentLoginServiceI {
    Page<Course> list(Course course,Pageable pageable);
    StudentUser login(StudentUser studentUser);
    Course moreCourseInfo(int courseId);
    boolean add(StudentCourse studentCourse);
    Page<Course> querySign4Page(Course course,Pageable pageable);
    boolean delete(StudentCourse studentCourse);
    List<Department> departments();
    List<Major> majors(int departmentId);
}