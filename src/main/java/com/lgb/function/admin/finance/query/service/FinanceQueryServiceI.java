package com.lgb.function.admin.finance.query.service;

import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.finance.Finance;

import java.util.List;

public interface FinanceQueryServiceI {
    List<Finance> allPayFinance(Course course);
    List<Course> getAllCourses();
}
