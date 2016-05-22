package com.lgb.function.rest.course.service;

import com.lgb.function.rest.course.RestNowStudentCourseInfo;

import java.util.List;

public interface RestCourseService {
    List<RestNowStudentCourseInfo> nowDayStudentCourse(String userCardNum);
}
