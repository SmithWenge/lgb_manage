package com.lgb.function.teaScore.repository;

import com.lgb.function.teaScore.ScoreModel;

import java.util.List;

public interface TeaScoreRepositoryI {

    ScoreModel select(ScoreModel scoreModel);
    List<ScoreModel> selectCourses(int teacherId);
    List<ScoreModel> selectScores(ScoreModel scoreModel);
    ScoreModel seleciById(int studentCourseId);
    int update (ScoreModel score);
    int selectStudentCourseId(String studentCardNum, String courseName);
}