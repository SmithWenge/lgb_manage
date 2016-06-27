package com.lgb.function.admin.score.repository;

import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.major.Major;
import com.lgb.function.admin.score.Score;

import java.util.List;

public interface ScoreRepositoryI {

    List<Department> selectDepartments();
    List<Major> selectMajors(int departmentId);
    List<Score> select4Page(Score score);
    List<Course> selectCourses(int majorId);
    Score seleciById(int studentCourseId);
    Boolean update(Score score);
}
