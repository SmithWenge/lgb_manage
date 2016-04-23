package com.lgb.function.admin.sign.controller;

import com.google.common.base.Optional;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.major.Major;
import com.lgb.function.admin.sign.StudentCourse;
import com.lgb.function.admin.student.StudentUser;
import com.lgb.function.admin.sign.service.StudentLoginServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/offline")
public class StudentLoginController {

    @Autowired
    private StudentLoginServiceI studentLoginService;

    @RequestMapping("/sign")
    public String index() {
        return "stu/downSign/sign";
    }

    @RequestMapping(value = "/downSign")
    public ModelAndView sign(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE)
                             Pageable pageable, StudentUser studentUser, Course course, HttpSession session, RedirectAttributes redirectAttributes) {
        StudentUser loginUser = studentLoginService.login(studentUser);
        if (loginUser == null) {
            redirectAttributes.addFlashAttribute(ConstantFields.DELETE_STU_FAILURE_KEY, ConstantFields.DELETE_STU_FAILURE_MESSAGE);
            return new ModelAndView("redirect:/admin/offline/sign.action");
        }
        session.setAttribute(ConstantFields.STU_CARD_NUM,loginUser.getStuCardNum());
        ModelAndView mav = new ModelAndView();


        if (loginUser != null) {
            Optional<Course> courseOptional = Optional.fromNullable(course);
            if (!courseOptional.isPresent()) {
                course = new Course();
            }
            course.setStudentId(loginUser.getStuId());
            session.setAttribute(ConstantFields.SESSION_STU_ID_KEY,loginUser.getStuId());

            Page<Course> page = studentLoginService.list(course, pageable);
            mav.addObject(ConstantFields.PAGE_KEY, page);
            mav.addObject(ConstantFields.SESSION_STU_KEY, loginUser);
            mav.setViewName("stu/downSign/sign");
//            session.setAttribute(ConstantFields.SESSION_STU_KEY, loginUser);
            List<Department> departments = studentLoginService.departments();
            mav.addObject("departments", departments);
        }
        else {
            mav.setViewName("redirect:/admin/offline/sign.action");
        }
        return mav;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView stuLogin(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE)
                                 Pageable pageable, StudentUser studentUser, Course course, HttpSession session) {

        StudentUser loginUser = studentLoginService.login(studentUser);
        if (loginUser == null) {
            return new ModelAndView("redirect:/admin/offline/routeLogin.action");
        }
        ModelAndView mav = new ModelAndView();
        Optional<Course> courseOptional = Optional.fromNullable(course);
        if (!courseOptional.isPresent()) {
            course = new Course();
        }
        course.setStudentId(loginUser.getStuId());
        session.setAttribute(ConstantFields.SESSION_STU_ID_KEY,loginUser.getStuId());

        if (loginUser != null) {
            Page<Course> page = studentLoginService.list(course, pageable);
            mav.addObject(ConstantFields.PAGE_KEY, page);
            mav.addObject(ConstantFields.SESSION_STU_KEY, loginUser);
            mav.setViewName("stu/login/list");
            session.setAttribute(ConstantFields.SESSION_STU_KEY, loginUser);
            List<Department> departments = studentLoginService.departments();
            mav.addObject("departments", departments);
        }
        else {
            mav.setViewName("redirect:/admin/offline/routeLogin.action");
        }
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginGet(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE)
                                 Pageable pageable, Course course, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute(ConstantFields.SESSION_STU_ID_KEY) != null) {
            StudentUser user = (StudentUser) session.getAttribute(ConstantFields.SESSION_STU_KEY);
            if (user != null) {
                course.setStudentId((Integer) session.getAttribute(ConstantFields.SESSION_STU_ID_KEY));
                List<Department> departments = studentLoginService.departments();
                mav.addObject("departments", departments);

                Page<Course> page = studentLoginService.list(course, pageable);
                mav.addObject(ConstantFields.PAGE_KEY, page);
                mav.setViewName("stu/login/list");
            }
            else {
                mav.setViewName("redirect:/admin/offline/routeLogin.action");
            }
        } else {
            mav.setViewName("redirect:/admin/offline/routeLogin.action");
        }
        return mav;
    }

    @RequestMapping("/pageSearch")
    public ModelAndView searchPage(Course course, @PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable, HttpSession session) {
        Optional<Course> optional = Optional.fromNullable(course);

        if (optional.isPresent()) {
            course.setStudentId((Integer)session.getAttribute(ConstantFields.SESSION_STU_ID_KEY));
        }

        ModelAndView mav = new ModelAndView("stu/login/list");
        List<Department> departments = studentLoginService.departments();
        mav.addObject("departments", departments);

        Page<Course> courses = studentLoginService.list(course, pageable);
        mav.addObject(ConstantFields.PAGE_KEY, courses);

        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/majors/{departmentId}", method = RequestMethod.POST)
    public Map<String, List<Major>> majors(@PathVariable("departmentId") int departmentId) {
        Map<String, List<Major>> map = new HashMap<>();
        map.put("majors", studentLoginService.majors(departmentId));

        return map;
    }

    @RequestMapping(value = "/routeLogin", method = RequestMethod.GET)
    public String routeLogin(HttpSession session) {
        if (session.getAttribute(ConstantFields.SESSION_STU_KEY) != null){
            ModelAndView mav = new ModelAndView();
            mav.setViewName("redirect:/admin/offline/login.action");
            return null;
        }else {
            return "stu/login/stuLogin";
        }
    }

    @RequestMapping(value = "/querySign",method = RequestMethod.GET)
    public ModelAndView querySign(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE)
                                  Pageable pageable, StudentUser studentUser, Course course, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        course.setStudentId((Integer) session.getAttribute(ConstantFields.SESSION_STU_ID_KEY));
        if (studentUser != null) {
            Page<Course> page = studentLoginService.querySign4Page(course, pageable);
            mav.addObject(ConstantFields.PAGE_KEY, page);
            mav.setViewName("stu/login/querySign");
        }
        else {
            mav.setViewName("redirect:/admin/offline/login.action");
        }
        return mav;
    }

    @RequestMapping(value = "/queryDownSign",method = RequestMethod.GET)
    public ModelAndView queryDownSign(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE)
                                      Pageable pageable, StudentUser studentUser, Course course, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute(ConstantFields.SESSION_STU_ID_KEY) == null){
            mav.setViewName("redirect:/admin/offline/downSign.action");
            return mav;
        }
        course.setStudentId((Integer)session.getAttribute(ConstantFields.SESSION_STU_ID_KEY));
        if (studentUser != null) {
            Page<Course> page = studentLoginService.querySign4Page(course, pageable);
            mav.addObject(ConstantFields.PAGE_KEY, page);
            String stuCardNum = (String)session.getAttribute(ConstantFields.STU_CARD_NUM);
            mav.addObject(ConstantFields.STU_CARD_NUM,stuCardNum);
            mav.setViewName("stu/downSign/querySign");
        }
        else {
            mav.setViewName("redirect:/admin/offline/downSign.action");
        }
        return mav;
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute(ConstantFields.SESSION_STU_KEY);

        return "redirect:/admin/offline/routeLogin.action";
    }

    @RequestMapping(value = "/courseInfo/{courseId}", method = RequestMethod.GET)
    public ModelAndView moreCourseInfo(@PathVariable("courseId") int courseId ) {
        Course course = studentLoginService.moreCourseInfo(courseId);
        if (course != null ){
            ModelAndView mav = new ModelAndView("stu/login/moreInfo");
            mav.addObject(ConstantFields.COURSE_INFO_KEY, course);
            return mav;
        }
        return new ModelAndView("redirect:/admin/offline/login.action");
    }
    @RequestMapping(value = "/courseInfoSign/{courseId}", method = RequestMethod.GET)
    public ModelAndView moreCourseInfoSign(@PathVariable("courseId") int courseId ) {
        Course course = studentLoginService.moreCourseInfo(courseId);
        if (course != null){
            ModelAndView mav = new ModelAndView("stu/login/moreInfoSign");
            mav.addObject(ConstantFields.COURSE_INFO_KEY, course);
            return mav;
        }
        return new ModelAndView("redirect:/admin/offline/login.action");
    }

    @RequestMapping(value = "/downCourseInfo/{courseId}", method = RequestMethod.GET)
    public ModelAndView moreCourseInfoDown(@PathVariable("courseId") int courseId ,HttpSession session) {
        Course course = studentLoginService.moreCourseInfo(courseId);
        if (course != null ){
            ModelAndView mav = new ModelAndView("stu/downSign/moreInfo");
            mav.addObject(ConstantFields.COURSE_INFO_KEY, course);
            String stuCardNum = (String)session.getAttribute(ConstantFields.STU_CARD_NUM);
            mav.addObject(ConstantFields.STU_CARD_NUM,stuCardNum);
            return mav;
        }
        return new ModelAndView("redirect:/admin/offline/downSign.action");
    }

    @RequestMapping(value = "/downCourseInfoSign/{courseId}", method = RequestMethod.GET)
    public ModelAndView moreCourseInfoDownSign(@PathVariable("courseId") int courseId ,HttpSession session) {
        Course course = studentLoginService.moreCourseInfo(courseId);
        if (course != null){
            ModelAndView mav = new ModelAndView("stu/downSign/moreInfoSign");
            mav.addObject(ConstantFields.COURSE_INFO_KEY, course);
            String stuCardNum = (String)session.getAttribute(ConstantFields.STU_CARD_NUM);
            mav.addObject(ConstantFields.STU_CARD_NUM,stuCardNum);
            return mav;
        }
        return new ModelAndView("redirect:/admin/offline/downSign.action");
    }

    @RequestMapping(value = "/signUp/{courseId}", method = RequestMethod.GET)
    public String signUp(@PathVariable("courseId") int courseId,StudentCourse studentCourse, HttpSession session,
                         RedirectAttributes redirectAttributes) {
        Integer studentId = (Integer)session.getAttribute(ConstantFields.SESSION_STU_ID_KEY);

        studentCourse.setStudentId(studentId);
        studentCourse.setCourseId(courseId);
        if (studentLoginService.add(studentCourse) == true && session.getAttribute(ConstantFields.SESSION_STU_KEY) != null) {

            redirectAttributes.addFlashAttribute(ConstantFields.ADD_SUCCESS_KEY, ConstantFields.ADD_SUCCESS_MESSAGE);
            return "redirect:/admin/offline/login.action";
        } else if (session.getAttribute(ConstantFields.SESSION_STU_KEY) == null){
            redirectAttributes.addFlashAttribute(ConstantFields.ADD_SUCCESS_KEY, ConstantFields.ADD_SUCCESS_MESSAGE);
            return "redirect:/admin/offline/downSign.action?stuCardNum="+session.getAttribute(ConstantFields.STU_CARD_NUM);
        }
        redirectAttributes.addFlashAttribute(ConstantFields.ADD_FAILURE_KEY, ConstantFields.ADD_FAILURE_MESSAGE);
        return "redirect:/admin/offline/routeSignUp.action";
    }

    @RequestMapping(value = "/routeSignUp", method = RequestMethod.GET)
    public String routeAddStudent() {
        return "stu/login/moreInfo";
    }

    @RequestMapping(value = "/delete/{courseId}", method = RequestMethod.GET)
    public String delete(@PathVariable("courseId") int courseId,StudentCourse studentCourse, HttpSession session,
                         RedirectAttributes redirectAttributes) {
        Integer studentId = (Integer)session.getAttribute(ConstantFields.SESSION_STU_ID_KEY);
        studentCourse.setStudentId(studentId);
        studentCourse.setCourseId(courseId);
        if (studentLoginService.delete(studentCourse) == true  && session.getAttribute(ConstantFields.SESSION_STU_KEY) != null) {
            redirectAttributes.addFlashAttribute(ConstantFields.DELETE_SUCCESS_KEY, ConstantFields.DELETE_SUCCESS_MESSAGE);
            return "redirect:/admin/offline/login.action";
        } else if (session.getAttribute(ConstantFields.SESSION_STU_KEY) == null){
            redirectAttributes.addFlashAttribute(ConstantFields.DELETE_SUCCESS_KEY, ConstantFields.DELETE_SUCCESS_MESSAGE);
            return "redirect:/admin/offline/downSign.action?stuCardNum="+session.getAttribute(ConstantFields.STU_CARD_NUM);
        }

        redirectAttributes.addFlashAttribute(ConstantFields.DELETE_FAILURE_KEY, ConstantFields.DELETE_FAILURE_MESSAGE);
        return "redirect:/admin/offline/login.action";

    }
}
