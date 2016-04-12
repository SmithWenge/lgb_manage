package com.lgb.function.support.student.card.service;

import com.lgb.arc.utils.DateUtils;
import com.lgb.function.support.student.StudentNowCourseInfo;
import com.lgb.function.support.student.card.repository.StudentCardRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCardService implements StudentCardServiceI {
    @Autowired
    private StudentCardRepositoryI studentCardRepository;

    @Override
    public List<StudentNowCourseInfo> nowDayCourseInfo(String studentCardNum) {
        int week = DateUtils.getNowWeek();

        return studentCardRepository.selectCourses(studentCardNum, week);
    }

    @Override
    public StudentNowCourseInfo student(String studentCardNum) {
        return studentCardRepository.selectStudent(studentCardNum);
    }
}
