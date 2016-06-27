package com.lgb.function.admin.score.service;

import com.google.common.base.Optional;
import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.major.Major;
import com.lgb.function.admin.score.Score;
import com.lgb.function.admin.score.repository.ScoreRepositoryI;
import com.lgb.function.support.log.LogContent;
import com.lgb.function.support.log.repository.LogRepositoryI;
import com.lgb.function.support.log.service.LogServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService implements ScoreServiceI{

    @Autowired
    private ScoreRepositoryI scoreRepository;

    @Autowired
    private LogRepositoryI logRepository;

    @Override
    public List<Department> departments() {
        return scoreRepository.selectDepartments();
    }

    @Override
    public List<Major> majors(int departmentId) {
        return scoreRepository.selectMajors(departmentId);
    }

    @Override
    public List<Score> select4Page(Score score) {
        return scoreRepository.select4Page(score);
    }

    @Override
    public List<Course> courses(int majorId) {
        return scoreRepository.selectCourses(majorId);
    }

    @Override
    public Score seleciById(int studentCourseId) {
        return scoreRepository.seleciById(studentCourseId);
    }

    @Override
    public Boolean edit(Score score, String logUser) {
        Score exitScore = scoreRepository.seleciById(score.getStudentCourseId());

        Optional<Score> optional = Optional.fromNullable(exitScore);
        if(optional.isPresent()) {
            Boolean tmp = scoreRepository.update(score);

            if(tmp) {
                LogContent logContent = new LogContent(logUser, "编辑成绩" + score.getStuName(), 1, 4);
                logRepository.insertLog(logContent);
            }

            return tmp;
        }

        return  false;
    }
}
