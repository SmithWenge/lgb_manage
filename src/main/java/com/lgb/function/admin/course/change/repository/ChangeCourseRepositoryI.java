package com.lgb.function.admin.course.change.repository;

import com.lgb.function.admin.course.change.ChangeCourse;
import com.lgb.function.admin.course.time.CourseTime;

import java.util.List;

public interface ChangeCourseRepositoryI {
    List<ChangeCourse> select4CouldChangeCourse(ChangeCourse changeCourse);
    List<ChangeCourse> select4HasChangedCourse(String stuCardNum);
    String select4OldCourse(int oldCourseId);
    ChangeCourse select4TurnCourse(int studentCourseId);
    int selectStudentId(int studentCourseId);
    ChangeCourse select4StudentInfo(int studentCourseId);
    List<ChangeCourse> select4OtherCourses(int studentId);
    boolean insertNewChangeCourseRecord(ChangeCourse changeCourse);
    void updateStudentCourse(ChangeCourse changeCourse);
    List<CourseTime> selectTime(int courseId);
    ChangeCourse select4NewTurnCourseInfo(int courseId);
//    void insertNewStudentCourse(ChangeCourse changeCourse);
}
