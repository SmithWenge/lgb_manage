package com.lgb.function.support.student.card.service;

import com.lgb.function.support.student.StudentNowCourseInfo;

import java.util.List;

public interface StudentCardServiceI {
    List<StudentNowCourseInfo> nowDayCourseInfo(String studentCardNum);
    StudentNowCourseInfo student(String studentCardNum);
}
