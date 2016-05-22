package com.lgb.function.rest.course.controller;

import com.lgb.function.rest.course.RestNowStudentCourseInfo;
import com.lgb.function.rest.course.service.RestCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class RestCourseController {

    @Autowired
    private RestCourseService restCourseService;

    @RequestMapping(value = "/{userCardNum}", method = RequestMethod.GET)
    public List<RestNowStudentCourseInfo> getNowDayCourse(@PathVariable("userCardNum") String userCardNum) {
        return restCourseService.nowDayStudentCourse(userCardNum);
    }
}
