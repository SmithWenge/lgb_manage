package com.lgb.function.admin.course.change.controller;

import com.google.common.base.Optional;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.course.change.ChangeCourse;
import com.lgb.function.admin.course.change.service.ChangeCourseServiceI;
import com.lgb.function.admin.login.AdminUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/course/change")
public class ChangeCourseController {
    private static final Logger LOG = LoggerFactory.getLogger(ChangeCourseController.class);

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
                mav.addObject("studentCourseId", studentCourseId);

                return mav;
            }

            return new ModelAndView("redirect:/admin/course/change/query.action?stuCardNum=" + student.getStuCardNum());
        }
    }

    /**
     * 换课的具体操作
     */
    @RequestMapping(value = "/turn", method = RequestMethod.POST)
    public String turnCourse(ChangeCourse changeCourse, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminName();

        changeCourse.setOperationUser(logUser);

        boolean isTurn = changeCourseService.addNewTurnCourse(changeCourse);

        if (isTurn) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} add new turn course old course is is {} and new course id is {}.", logUser, String.valueOf(changeCourse.getOldCourseId()), String.valueOf(changeCourse.getCourseId()));

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.ADD_SUCCESS_MESSAGE);

            return "redirect:/admin/course/change/query.action?stuCardNum=" + changeCourse.getStuCardNum();
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.ADD_FAILURE_MESSAGE);
        return "redirect:/admin/course/change/route/turn/" + changeCourse.getStudentCourseId() + ".action";
    }
}
