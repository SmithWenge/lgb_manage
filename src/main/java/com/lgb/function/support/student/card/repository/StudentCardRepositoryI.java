package com.lgb.function.support.student.card.repository;

import com.lgb.function.support.student.StudentNowCourseInfo;

import java.util.List;

public interface StudentCardRepositoryI {
    List<StudentNowCourseInfo> selectCourses(String studentCardNum, int week);
    StudentNowCourseInfo selectStudent(String studentCardNum);
}
