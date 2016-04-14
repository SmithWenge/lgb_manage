package com.lgb.function.support.student.card.controller;

import com.lgb.function.support.student.StudentNowCourseInfo;
import com.lgb.function.support.student.card.service.StudentCardServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student/card")
public class StudentCardController {

    @Autowired
    private StudentCardServiceI studentCardService;

    @RequestMapping("/routeIndex")
    public String test() {
        return "student/card/index";
    }

    @ResponseBody
    @RequestMapping(value = "/read", method = RequestMethod.POST)
    public Map<String, List<StudentNowCourseInfo>> testTwo(@RequestBody StudentNowCourseInfo studentNowCourseInfo) {
        String studentCardNum =studentNowCourseInfo.getStudentCardNum().trim();
        Map<String, List<StudentNowCourseInfo>> map = new HashMap<>();
//        TODO 添加数据验证,学生卡号
        List<StudentNowCourseInfo> infos = studentCardService.nowDayCourseInfo(studentCardNum);

        map.put("infos", infos);
        map.put("student", Arrays.asList(studentCardService.student(studentCardNum)));

        return map;
    }
}
