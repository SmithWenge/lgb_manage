package com.lgb.function.rest.course.service.impl;

import com.lgb.arc.utils.DateUtils;
import com.lgb.function.rest.course.RestNowStudentCourseInfo;
import com.lgb.function.rest.course.repository.RestCourseRepository;
import com.lgb.function.rest.course.service.RestCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestCourseServiceImpl implements RestCourseService {

    @Autowired
    private RestCourseRepository restCourseRepository;

    @Override
    public List<RestNowStudentCourseInfo> nowDayStudentCourse(String userCardNum) {
        int week = DateUtils.getNowWeek();

        List<RestNowStudentCourseInfo> studentCourseInfos = restCourseRepository.selectNowDay(week, userCardNum);

        for (RestNowStudentCourseInfo sci : studentCourseInfos) {
            sci.setStudentCardNum(userCardNum);
            sci.setStudentName(restCourseRepository.selectStudent(userCardNum).getStudentName());
        }

        return studentCourseInfos;
    }
}
