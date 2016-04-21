package com.lgb.function.admin.sign.repository;

import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.course.time.CourseTime;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.major.Major;
import com.lgb.function.admin.sign.StudentCourse;
import com.lgb.function.admin.student.StudentUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentLoginRepositoryI {
    Page<Course> query4Page(Course course, Pageable pageable);
    StudentUser selectUnique(StudentUser studentUser);
    Course queryForCourse(int courseId);
    boolean signUp(StudentCourse studentCourse);
    Page<Course> querySign4Page(Course course, Pageable pageable);
    List<CourseTime> queryForCourseTime(int courseId);
    StudentCourse signUpVer(StudentCourse studentCourse);
    boolean delete(StudentCourse studentCourse);
    List<Department> selectDepartments();
    List<Major> selectMajors(int departmentId);
}