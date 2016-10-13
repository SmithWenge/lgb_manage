package com.lgb.function.admin.finance.course.change.repository;

import com.lgb.function.admin.course.change.ChangeCourse;

import java.util.List;

public interface ChangeCourseFinanceRepositoryI {
    List<ChangeCourse> select4ChangeCourseFinances();
    boolean update4Tuition(int changeCourseId);
}
