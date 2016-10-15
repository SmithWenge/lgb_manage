package com.lgb.function.support.student.card.repository;

import com.lgb.function.support.student.StudentNowCourseInfo;
import com.lgb.function.support.student.card.StudentCheck;

import java.util.List;

public interface StudentCardRepositoryI {
    List<StudentNowCourseInfo> selectCourses(String studentCardNum, int week);
    StudentNowCourseInfo selectStudent(String studentCardNum);
    void addStudentCheck(StudentCheck check);
    boolean hasCarded(String studentCardNum, int year, int month, int day);
}
