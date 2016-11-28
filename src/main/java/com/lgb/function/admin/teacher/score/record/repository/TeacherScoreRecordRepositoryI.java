package com.lgb.function.admin.teacher.score.record.repository;

import com.lgb.function.admin.teacher.score.record.TeacherScoreRecord;

import java.util.List;

public interface TeacherScoreRecordRepositoryI {
    TeacherScoreRecord select(TeacherScoreRecord teacherScoreRecord);
    List<TeacherScoreRecord> selectCourses(int teacherId);
    List<TeacherScoreRecord> selectScores(TeacherScoreRecord teacherScoreRecord);
    TeacherScoreRecord seleciById(int studentCourseId);
    int update (TeacherScoreRecord score);
    int selectStudentCourseId(String studentCardNum, String courseName);
}
