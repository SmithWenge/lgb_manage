package com.lgb.function.teaScore.service;


import com.lgb.function.teaScore.ScoreModel;

import java.util.List;

public interface TeaScoreServiceI {

    ScoreModel select(ScoreModel scoreModel);
    List<ScoreModel> courses(int teacherId);
    List<ScoreModel> selectScores(ScoreModel scoreModel);
    ScoreModel seleciById(int studentCourseId);
    Boolean edit (ScoreModel score);
    boolean save(List<ScoreModel> scores);
}
