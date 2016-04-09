package com.lgb.function.admin.course.service;

import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.course.CourseSite;
import com.lgb.function.admin.course.time.CourseTime;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.major.Major;
import com.lgb.function.admin.student.StudentUser;
import com.lgb.function.admin.teacher.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseServiceI {
    List<Department> departments();
    List<Major> majors(int departmentId);
    boolean courseNum(String courseNum);
    boolean courseName(String courseName);
    List<Teacher> teachers(int departmentId);
    boolean add(Course course, String logUser);
    Page<Course> select4Page(int department, Pageable pageable);
    Course select(int courseId);
    List<CourseTime> courseTimes(int courseId);
    boolean edit(Course course, String logUser);
    boolean delete(int courseId, String logUser);
    List<CourseSite> courseSiteNum(int courseId);
    List<StudentUser> courseStudent(int courseId);
    Course selectName(int courseId);
    boolean makeLeader(Course course, String logUser);
}