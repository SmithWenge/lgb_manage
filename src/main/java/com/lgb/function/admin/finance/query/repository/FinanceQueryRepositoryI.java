package com.lgb.function.admin.finance.query.repository;

import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.finance.Finance;

import java.util.List;

public interface FinanceQueryRepositoryI {
    List<Finance> select4AllPayFinance(Course course);
    List<Course> selectUndeleteCourses();
}
