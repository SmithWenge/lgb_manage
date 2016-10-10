package com.lgb.function.admin.course.change.controller;

import com.google.common.base.Optional;
import com.lgb.function.admin.course.change.ChangeCourse;
import com.lgb.function.admin.course.change.service.ChangeCourseServiceI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/course/change")
public class ChangeCourseController {
    private static final Logger LOG = Logger.getLogger(ChangeCourseController.class);

    @Autowired
    private ChangeCourseServiceI changeCourseService;

    /**
     * 路由到查看个人课程页面
     *
     * @return
     */
    @RequestMapping("/route")
    public String routeChangeCourseQuery() {
        return "admin/course/change/query";
    }

    /**
     * 查询学员可更换的课程列表
     */
    @RequestMapping("/query")
    public ModelAndView queryChangeCourse(ChangeCourse changeCourse) {
        List<ChangeCourse> couldCourses = changeCourseService.queryCouldChangeCourse(changeCourse);
        List<ChangeCourse> hasCourses = changeCourseService.queryHasChangeCourse(changeCourse);

        ModelAndView mav = new ModelAndView("admin/course/change/list");

        mav.addObject("couldCourses", couldCourses);
        mav.addObject("hasCourses", hasCourses);

        return mav;
    }

    /**
     * 路由到换课页面
     */
    @RequestMapping("/route/turn/{studentCourseId}")
    public ModelAndView routeTurnCourse(@PathVariable int studentCourseId) {
        ChangeCourse student = changeCourseService.getStudentInfo(studentCourseId);
        Optional<ChangeCourse> optionalStudent = Optional.fromNullable(student);

        if (!optionalStudent.isPresent()) {
            return new ModelAndView("redirect:/admin/course/change/route.action");
        } else {
            ChangeCourse changeCourse = changeCourseService.getTurnCourse(studentCourseId);
            Optional<ChangeCourse> optional = Optional.fromNullable(changeCourse);


            if (optional.isPresent()) {
                List<ChangeCourse> otherCourses = changeCourseService.getOtherCourses(student.getStudentId());
                ModelAndView mav = new ModelAndView("admin/course/change/turn");

                mav.addObject("changeCourse", changeCourse);
                mav.addObject("student", student);
                mav.addObject("otherCourses", otherCourses);

                return mav;
            }

            return new ModelAndView("redirect:/admin/course/change/query.action?stuCardNum=" + student.getStuCardNum());
        }
    }
}
