package com.lgb.function.admin.course.change.service;

import com.lgb.function.admin.course.change.ChangeCourse;
import com.lgb.function.admin.course.change.repository.ChangeCourseRepositoryI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChangeCourseService implements ChangeCourseServiceI {
    private static final Logger LOG = Logger.getLogger(ChangeCourseService.class);

    @Autowired
    private ChangeCourseRepositoryI changeCourseRepository;

    @Override
    public List<ChangeCourse> queryCouldChangeCourse(ChangeCourse changeCourse) {
        return changeCourseRepository.select4CouldChangeCourse(changeCourse);
    }

    @Override
    public List<ChangeCourse> queryHasChangeCourse(ChangeCourse changeCourse) {
        List<ChangeCourse> hasCourses = changeCourseRepository.select4HasChangedCourse(changeCourse.getStuCardNum());

        for (ChangeCourse course : hasCourses) {
            course.setOldCourseName(changeCourseRepository.select4OldCourse(course.getOldCourseId()));
        }

        return hasCourses;
    }

    @Override
    public ChangeCourse getTurnCourse(int studentCourseId) {
        ChangeCourse changeCourse = changeCourseRepository.select4TurnCourse(studentCourseId);
        changeCourse.setCourseTimes(changeCourseRepository.selectTime(changeCourse.getCourseId()));

        return changeCourse;
    }

    @Override
    public ChangeCourse getStudentInfo(int studentCourseId) {
        return changeCourseRepository.select4StudentInfo(studentCourseId);
    }

    @Override
    public List<ChangeCourse> getOtherCourses(int studentId) {
        return changeCourseRepository.select4OtherCourses(studentId);
    }

    @Transactional
    @Override
    public boolean addNewTurnCourse(ChangeCourse changeCourse) {
        changeCourseRepository.updateStudentCourse(changeCourse);

        return changeCourseRepository.insertNewChangeCourseRecord(changeCourse);
    }

    @Override
    public ChangeCourse newTurnCourseInfo(int courseId) {
        ChangeCourse changeCourse = changeCourseRepository.select4NewTurnCourseInfo(courseId);
        changeCourse.setCourseTimes(changeCourseRepository.selectTime(courseId));

        return changeCourse;
    }
}
