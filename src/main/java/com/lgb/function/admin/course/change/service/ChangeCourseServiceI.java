package com.lgb.function.admin.course.change.service;

import com.lgb.function.admin.course.change.ChangeCourse;

import java.util.List;

public interface ChangeCourseServiceI {
    List<ChangeCourse> queryCouldChangeCourse(ChangeCourse changeCourse);
    List<ChangeCourse> queryHasChangeCourse(ChangeCourse changeCourse);
    ChangeCourse getTurnCourse(int studentCourseId);
    ChangeCourse getStudentInfo(int studentCourseId);
    List<ChangeCourse> getOtherCourses(int studentId);
    boolean addNewTurnCourse(ChangeCourse changeCourse);
    ChangeCourse newTurnCourseInfo(int courseId);
}
