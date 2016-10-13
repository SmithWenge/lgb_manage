package com.lgb.function.admin.finance.course.change.service;

import com.lgb.function.admin.course.change.ChangeCourse;

import java.util.List;

public interface ChangeCourseFinanceServiceI {
    List<ChangeCourse> changeCourseFinances();
    boolean tuition(int changeCourseId, String logUser);
}
