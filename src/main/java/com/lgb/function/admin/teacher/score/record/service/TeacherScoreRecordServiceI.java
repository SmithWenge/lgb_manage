package com.lgb.function.admin.teacher.score.record.service;

import com.lgb.function.admin.teacher.score.record.TeacherScoreRecord;

import java.util.List;

public interface TeacherScoreRecordServiceI {
    TeacherScoreRecord select(TeacherScoreRecord teacherScoreRecord);
    List<TeacherScoreRecord> courses(int teacherId);
    List<TeacherScoreRecord> selectScores(TeacherScoreRecord teacherScoreRecord);
    TeacherScoreRecord seleciById(int studentCourseId);
    Boolean edit (TeacherScoreRecord score);
    boolean save(List<TeacherScoreRecord> scores);
}
