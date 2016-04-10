package com.lgb.function.admin.finance.repository;

import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.major.Major;
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
    boolean update(Finance finance);
}
