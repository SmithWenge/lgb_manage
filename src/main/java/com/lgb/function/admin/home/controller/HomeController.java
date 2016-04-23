package com.lgb.function.admin.home.controller;

import com.google.common.base.Optional;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.course.service.CourseServiceI;
import com.lgb.function.admin.department.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/home")
public class HomeController {

    @Autowired
    private CourseServiceI courseService;

    @RequestMapping("/index")
    public ModelAndView index(Course course, @PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable, HttpSession session) {
        session.removeAttribute(ConstantFields.SESSION_COURSE_SEARCH_KEY);
        ModelAndView mav = new ModelAndView("admin/home/index");

        List<Department> departments = courseService.departments();
        mav.addObject("departments", departments);

        Page<Course> courses = courseService.select4Page(course, pageable);
        mav.addObject(ConstantFields.PAGE_KEY, courses);

        return mav;
    }


    @RequestMapping("/page")
    public ModelAndView listPage(Course course, @PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable, HttpSession session) {
        Course searchObj = (Course) session.getAttribute(ConstantFields.SESSION_COURSE_SEARCH_KEY);
        Optional<Course> optional = Optional.fromNullable(searchObj);

        if (optional.isPresent()) {
            course = searchObj;
        }

        ModelAndView mav = new ModelAndView("admin/home/index");
        List<Department> departments = courseService.departments();
        mav.addObject("departments", departments);

        Page<Course> courses = courseService.select4Page(course, pageable);
        mav.addObject(ConstantFields.PAGE_KEY, courses);

        return mav;
    }

    @RequestMapping("/indexSearch")
    public ModelAndView searchPage(Course course, @PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable, HttpSession session) {
        Optional<Course> optional = Optional.fromNullable(course);

        if (optional.isPresent()) {
            session.setAttribute(ConstantFields.SESSION_COURSE_SEARCH_KEY, course);
        }

        ModelAndView mav = new ModelAndView("admin/home/index");
        List<Department> departments = courseService.departments();
        mav.addObject("departments", departments);

        Page<Course> courses = courseService.select4Page(course, pageable);
        mav.addObject(ConstantFields.PAGE_KEY, courses);

        return mav;
    }
}
