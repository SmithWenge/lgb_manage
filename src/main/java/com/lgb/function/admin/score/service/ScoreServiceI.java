package com.lgb.function.admin.score.service;

import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.major.Major;
import com.lgb.function.admin.score.Score;

import java.util.List;

public interface ScoreServiceI {
    List<Department> departments();
    List<Major> majors(int departmentId);
    List<Score> select4Page(Score score);
    List<Course> courses(int majorId);
    Score seleciById(int studentCourseId);
    Boolean edit (Score score, String logUser);
}
