package com.lgb.function.admin.course.controller;

import com.google.common.base.Optional;
import com.lgb.arc.exception.BatchRollbackException;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.course.CourseSite;
import com.lgb.function.admin.course.service.CourseServiceI;
import com.lgb.function.admin.course.time.CourseTime;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.login.AdminUser;
import com.lgb.function.admin.major.Major;
import com.lgb.function.admin.student.StudentUser;
import com.lgb.function.admin.teacher.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/course")
public class CourseController {
    private static final Logger LOG = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseServiceI courseService;

    @RequestMapping("/routePage")
    public ModelAndView listFirstCourse(Course course, @PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable, HttpSession session) {
        session.removeAttribute(ConstantFields.SESSION_COURSE_SEARCH_KEY);

        ModelAndView mav = new ModelAndView("admin/course/list");

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

        ModelAndView mav = new ModelAndView("admin/course/list");
        List<Department> departments = courseService.departments();
        mav.addObject("departments", departments);

        Page<Course> courses = courseService.select4Page(course, pageable);
        mav.addObject(ConstantFields.PAGE_KEY, courses);

        return mav;
    }

    @RequestMapping("/pageSearch")
    public ModelAndView searchPage(Course course, @PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable, HttpSession session) {
        Optional<Course> optional = Optional.fromNullable(course);

        if (optional.isPresent()) {
            session.setAttribute(ConstantFields.SESSION_COURSE_SEARCH_KEY, course);
        }

        ModelAndView mav = new ModelAndView("admin/course/list");
        List<Department> departments = courseService.departments();
        mav.addObject("departments", departments);

        Page<Course> courses = courseService.select4Page(course, pageable);
        mav.addObject(ConstantFields.PAGE_KEY, courses);

        return mav;
    }

    @RequestMapping("/routeAdd")
    public ModelAndView routeCourseAdd() {
        ModelAndView mav = new ModelAndView("admin/course/add");
        mav.addObject("departments", courseService.departments());

        return mav;
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCourse(Course course, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (courseService.add(course, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} add new course {}.", logUser, course.getCourseName());

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.ADD_SUCCESS_MESSAGE);
            return "redirect:/admin/course/routePage.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.ADD_FAILURE_MESSAGE);
        return "redirect:/admin/course/routeAdd.action";
    }

    @ResponseBody
    @RequestMapping(value = "/majors/{departmentId}", method = RequestMethod.POST)
    public Map<String, List<Major>> majors(@PathVariable("departmentId") int departmentId) {
        Map<String, List<Major>> map = new HashMap<>();
        map.put("majors", courseService.majors(departmentId));

        return map;
    }

    @RequestMapping(value = "/courseNum", method = RequestMethod.POST)
    @ResponseBody
    public boolean courseNumExist(Course course) {
        if (courseService.courseNum(course.getCourseNum())) return true;
        else return false;
    }

    @RequestMapping(value = "/courseName", method = RequestMethod.POST)
    @ResponseBody
    public boolean courseNameExist(Course course) {
        if (courseService.courseName(course.getCourseName())) return true;
        else return false;
    }

    @ResponseBody
    @RequestMapping(value = "/teachers/{departmentId}", method = RequestMethod.POST)
    public Map<String, List<Teacher>> teachers(@PathVariable("departmentId") int departmentId) {
        Map<String, List<Teacher>> map = new HashMap<>();
        map.put("teachers", courseService.teachers(departmentId));

        return map;
    }

    @RequestMapping(value = "/routeEdit/{courseId}")
    public ModelAndView routeEdit(@PathVariable("courseId") int courseId) {
        ModelAndView mav = new ModelAndView("admin/course/edit");

        List<Department> departments = courseService.departments();
        mav.addObject("departments", departments);

        Course course = courseService.select(courseId);
        List<CourseTime> courseTimes = courseService.courseTimes(courseId);

        mav.addObject("course", course);
        mav.addObject("courseTimes", courseTimes);

        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editDepartment(Course course, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (courseService.edit(course, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} edit Course Id is  {}.", logUser, course.getCourseId());

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.EDIT_SUCCESS_MESSAGE);

            return "redirect:/admin/course/routePage.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.EDIT_FAILURE_MESSAGE);
        return "redirect:/admin/course/routeEdit/" + course.getCourseId() + ".action";
    }

    @RequestMapping(value = "/delete/{courseId}", method = RequestMethod.GET)
    public String deleteDepartment(@PathVariable("courseId") int courseId, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (courseService.delete(courseId, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} delete course's ID {}.", logUser, courseId);

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.DELETE_SUCCESS_MESSAGE);

            return "redirect:/admin/course/routePage.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.DELETE_FAILURE_MESSAGE);

        return "redirect:/admin/course/routePage.action";
    }

    @RequestMapping(value = "/siteNum/{courseId}")
    public ModelAndView siteNum(@PathVariable("courseId") int courseId) {
        List<CourseSite> courseSites = courseService.courseSiteNum(courseId);

        ModelAndView mav = new ModelAndView("admin/course/site");
        mav.addObject("courseSites", courseSites);

        return mav;
    }

    @RequestMapping(value = "/route/make/leader/{courseId}")
    public ModelAndView routeMakeLeader(@PathVariable("courseId") int courseId, RedirectAttributes redirectAttributes) {
        List<StudentUser> studentUsers = courseService.courseStudent(courseId);
        Course course = courseService.selectName(courseId);

        if (studentUsers.size() > 0) {
            ModelAndView mav = new ModelAndView("admin/course/route/make/leader");

            mav.addObject("studentUsers", studentUsers);
            mav.addObject("course", course);

            redirectAttributes.addFlashAttribute(ConstantFields.EDIT_SUCCESS_KEY, "添加班长成功");

            return mav;
        }

        redirectAttributes.addFlashAttribute(ConstantFields.EDIT_FAILURE_KEY, "没有人员选择这个课程");

        return new ModelAndView("redirect:/admin/course/routePage.action");
    }

    @RequestMapping(value = "/make/leader", method = RequestMethod.POST)
    public String makeLeader(Course course, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (courseService.makeLeader(course, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} edit Course's ID {} add leader's ID is  {}.", logUser, course.getCourseId(), course.getCourseMaster());

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.EDIT_SUCCESS_MESSAGE);

            return "redirect:/admin/course/routePage.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.EDIT_FAILURE_MESSAGE);
        return "redirect:/admin/course/route/make/leader/" + course.getCourseId() + ".action";
    }

    @RequestMapping(value = "/upgrade/{courseId}")
    public String courseUpgrade(@PathVariable("courseId") int courseId, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (courseService.courseUpgrade(courseId, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} upgrade Course's ID {}.", logUser, courseId);

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.EDIT_SUCCESS_MESSAGE);

            return "redirect:/admin/course/routePage.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.EDIT_FAILURE_MESSAGE);
        return "redirect:/admin/course/routePage.action";
    }

    @RequestMapping(value = "/batch/upgrade")
    public String batchUpgrade(@RequestParam("batchId") String batchIds, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        try {
            if (courseService.batchUpgrade(batchIds, logUser)) {
                if (LOG.isInfoEnabled())
                    LOG.info("[LGB MANAGE] [OK] {} upgrade Course's IDs {}.", logUser, batchIds);

                redirectAttributes.addFlashAttribute(ConstantFields.EDIT_SUCCESS_KEY, ConstantFields.EDIT_SUCCESS_MESSAGE);

                return "redirect:/admin/course/routePage.action";
            }
        } catch (BatchRollbackException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.EDIT_FAILURE_MESSAGE);
            return "redirect:/admin/course/routePage.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.EDIT_FAILURE_MESSAGE);
        return "redirect:/admin/course/routePage.action";
    }

    @RequestMapping(value = "/batch/graduate")
    public String batchGraduate(@RequestParam("batchId") String batchIds, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        try {
            if (courseService.batchGraduate(batchIds, logUser)) {
                if (LOG.isInfoEnabled())
                    LOG.info("[LGB MANAGE] [OK] {} graduate Course's IDs {}.", logUser, batchIds);

                redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.DELETE_SUCCESS_MESSAGE);

                return "redirect:/admin/course/routePage.action";
            }
        } catch (BatchRollbackException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.DELETE_FAILURE_MESSAGE);
            return "redirect:/admin/course/routePage.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.DELETE_FAILURE_MESSAGE);
        return "redirect:/admin/course/routePage.action";
    }
}
