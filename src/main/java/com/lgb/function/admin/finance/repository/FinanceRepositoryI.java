package com.lgb.function.admin.finance.repository;

import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.StudentCourse;
import com.lgb.function.admin.major.Major;
import com.lgb.function.admin.student.StudentUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FinanceRepositoryI {
    Page<Finance> selectUnFinance4Page(Finance finance, Pageable pageable);
    List<Department> selectDepartments();
    List<Major> selectMajors(int departmentId);
    List<Course> selectCourses(int majorId);
    Finance select(int studentCourseId);
    List<Finance> selectFinanceCourse(int studentCourseId);
    Finance selectById(int studentCourseId);
    boolean update(Finance finance, String logUser);
    Page<Finance> selectFinance4Page(Finance finance, Pageable pageable);
    Page<Finance> selectTwoDayFinance4Page(Finance finance, Pageable pageable);
    List<Finance> selectUnFinanceByCard(Finance finance);
    List<StudentUser> select4UnpaymentStudent(Course course);
    List<Course> selectUndeleteCourses();
    List<StudentUser> select4paymentStudent(Course course);
    StudentCourse selectByStudentCourseId(int studentCourseId);
    boolean delete(int studentCourseId);
}
