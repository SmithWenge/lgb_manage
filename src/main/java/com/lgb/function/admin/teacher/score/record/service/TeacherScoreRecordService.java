package com.lgb.function.admin.teacher.score.record.service;

import com.lgb.function.admin.teacher.score.record.TeacherScoreRecord;
import com.lgb.function.admin.teacher.score.record.repository.TeacherScoreRecordRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherScoreRecordService implements TeacherScoreRecordServiceI {
    @Autowired
    private TeacherScoreRecordRepositoryI teaScoreRepository;

    @Override
    public TeacherScoreRecord select(TeacherScoreRecord teacherScoreRecord) {
        return teaScoreRepository.select(teacherScoreRecord);
    }

    @Override
    public List<TeacherScoreRecord> courses(int teacherId) {
        return teaScoreRepository.selectCourses(teacherId);
    }

    @Override
    public List<TeacherScoreRecord> selectScores(TeacherScoreRecord teacherScoreRecord) {
        return teaScoreRepository.selectScores(teacherScoreRecord);
    }

    @Override
    public TeacherScoreRecord seleciById(int studentCourseId) {
        return teaScoreRepository.seleciById(studentCourseId);
    }

    @Override
    public Boolean edit(TeacherScoreRecord score) {
        return teaScoreRepository.update(score) == 1 ? true:false;
    }

    @Transactional
    @Override
    public boolean save(List<TeacherScoreRecord> scores) {
        int sum = 0;

        for (TeacherScoreRecord user : scores) {
            int studentCourseId = teaScoreRepository.selectStudentCourseId(user.getStudentCardNum(), user.getCourseName());
            user.setStudentCourseId(studentCourseId);
            sum += teaScoreRepository.update(user);
        }

        return scores.size() == sum;
    }
}
