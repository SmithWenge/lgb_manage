package com.lgb.function.admin.course.repository;

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

public interface CourseRepositoryI {
    List<Department> selectDepartments();
    List<Major> selectMajors(int departmentId);
    int selectExist(String courseNum);
    int selectCourseName(String courseName);
    List<Teacher> selectTeachers(int departmentId);
    int insert(Course course);
    Page<Course> select4Page(Course course, Pageable pageable);
    Course selectByCourseNum(String courseNum);
    void insertTeacherCourse(int courseId, int courseTeacherOne);
    Teacher selectTeacher(int teacherId);
    Course select(int courseId);
    List<CourseTime> selectTimes(int courseId);
    int update(Course course);
    boolean deleteTimes(int courseId);
    boolean delete(int courseId);
    Course selectById(int courseId);
    List<CourseSite> selectSiteNum(int courseId);
    List<StudentUser> selectStudents(int courseId);
    Course selectName(int courseId);
    boolean updateLeader(Course course);
    boolean canUpdateCourseGrade(int courseId);
    int updateUpgradeCourse(int courseId);
    int deleteGraduateCourse(int courseId);
}
