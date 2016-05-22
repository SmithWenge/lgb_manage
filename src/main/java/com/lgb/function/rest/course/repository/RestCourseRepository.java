package com.lgb.function.rest.course.repository;

import com.lgb.function.rest.course.RestNowStudentCourseInfo;

import java.util.List;

public interface RestCourseRepository {
    List<RestNowStudentCourseInfo> selectNowDay(int week, String userCardNum);
    RestNowStudentCourseInfo selectStudent(String studentCardNum);
}
