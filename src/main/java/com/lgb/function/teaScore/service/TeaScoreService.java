package com.lgb.function.teaScore.service;

import com.lgb.function.teaScore.ScoreModel;
import com.lgb.function.teaScore.repository.TeaScoreRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeaScoreService implements TeaScoreServiceI{

    @Autowired
    private TeaScoreRepositoryI teaScoreRepository;

    @Override
    public ScoreModel select(ScoreModel scoreModel) {
        return teaScoreRepository.select(scoreModel);
    }

    @Override
    public List<ScoreModel> courses(int teacherId) {
        return teaScoreRepository.selectCourses(teacherId);
    }

    @Override
    public List<ScoreModel> selectScores(ScoreModel scoreModel) {
        return teaScoreRepository.selectScores(scoreModel);
    }

    @Override
    public ScoreModel seleciById(int studentCourseId) {
        return teaScoreRepository.seleciById(studentCourseId);
    }

    @Override
    public Boolean edit(ScoreModel score) {
        return teaScoreRepository.update(score) == 1 ? true:false;
    }

    @Override
    public boolean save(List<ScoreModel> scores) {
        int sum = 0;

        for (ScoreModel user : scores) {
            int studentCourseId = teaScoreRepository.selectStudentCourseId(user.getStudentCardNum(), user.getCourseName());
            user.setStudentCourseId(studentCourseId);
            sum += teaScoreRepository.update(user);
        }

        return scores.size() == sum;
    }
}
