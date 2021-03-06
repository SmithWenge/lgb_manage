package com.lgb.function.admin.finance.service;

import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.major.Major;
import com.lgb.function.admin.setting.LGBConfig;
import com.lgb.function.admin.student.StudentUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FinanceServiceI {
    Page<Finance> selectUnFinance4Page(Finance finance, Pageable pageable);
    List<Department> departments();
    List<Major> majors(int departmentId);
    List<Course> courses(int courseId);
    Finance select(int studentCourseId);
    List<Finance> selectFinanceCourse(int studentCourseId);
    boolean edit(Finance finance, String logUser);
    Page<Finance> selectFinance4Page(Finance finance, Pageable pageable);
    Page<Finance> selectTwoDayFinance4Page(Finance finance, Pageable pageable);
    List<Finance> selectUnFinanceByCard(Finance finance);
    List<StudentUser> unpaymentStudentUser(Course course);
    List<Course> getAllCourses();
    List<StudentUser> paymentStudentUser(Course course);
    boolean delete(int studentCourseId, String logUser);
    LGBConfig nowConfig();
}
