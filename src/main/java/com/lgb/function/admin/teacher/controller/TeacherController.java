package com.lgb.function.admin.teacher.controller;

import com.google.common.base.Optional;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.login.AdminUser;
import com.lgb.function.admin.teacher.Teacher;
import com.lgb.function.admin.teacher.service.TeacherServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

@Controller
@RequestMapping("/admin/teacher")
public class TeacherController {
    private static final Logger LOG = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherServiceI teacherService;

    @RequestMapping("/page")
    public ModelAndView listTeacher(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE)
                                            Pageable pageable, Teacher teacher, HttpSession session) {
        Teacher searchTeacher = (Teacher) session.getAttribute(ConstantFields.SESSION_TEACHER_SEARCH_KEY);

        Optional<Teacher> optional = Optional.fromNullable(searchTeacher);
        if (optional.isPresent()) {
            teacher = searchTeacher;
        }

        ModelAndView mav = new ModelAndView("admin/teacher/list");
        Page<Teacher> page = teacherService.query4Page(teacher, pageable);

        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }

    @RequestMapping(value = "/pageSearch", method = RequestMethod.POST)
    public ModelAndView searchTeacher(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE)
                                                  Pageable pageable, Teacher teacher, HttpSession session) {
        session.setAttribute(ConstantFields.SESSION_TEACHER_SEARCH_KEY, teacher);

        ModelAndView mav = new ModelAndView("admin/teacher/list");
        Page<Teacher> page = teacherService.query4Page(teacher, pageable);

        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }

    @RequestMapping(value = "/routePage", method = RequestMethod.GET)
    public ModelAndView showFirstPage(HttpSession session) {
        session.removeAttribute(ConstantFields.SESSION_TEACHER_SEARCH_KEY);

        ModelAndView mav = new ModelAndView("admin/teacher/list");

        Page<Teacher> contents = teacherService.query4Page(new Teacher(), new PageRequest(0, ConstantFields.DEFAULT_PAGE_SIZE));
        mav.addObject(ConstantFields.PAGE_KEY, contents);
        mav.addObject("departments", teacherService.departments());
        return mav;
    }

    @RequestMapping("/routeAdd")
    public ModelAndView routeAdd() {
        ModelAndView mav = new ModelAndView("admin/teacher/add");

        mav.addObject("departments", teacherService.departments());

        return mav;
    }

    @RequestMapping(value = "/cardNum", method = RequestMethod.POST)
    @ResponseBody
    public boolean nameExist(Teacher teacher) {
        if (teacherService.existCardNum(teacher)) return true;
        else return false;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String teacherAdd(Teacher teacher, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (teacherService.add(teacher, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} add new teacher {}.", logUser, teacher.getTeacherName());

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.ADD_SUCCESS_MESSAGE);
            return "redirect:/admin/teacher/routePage.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.ADD_FAILURE_MESSAGE);
        return "redirect:/admin/teacher/routeAdd.action";
    }

    @RequestMapping(value = "/routeTurnCard/{teacherId}")
    public ModelAndView routeTurnCard(@PathVariable("teacherId") int teacherId) {
        Teacher teacher = teacherService.selectCard(teacherId);

        Optional<Teacher> optional = Optional.fromNullable(teacher);
        if (optional.isPresent()) {
            ModelAndView mav = new ModelAndView("admin/teacher/turnCard");

            mav.addObject("teacher", teacher);

            return mav;
        }

        return new ModelAndView("redirect:/admin/teacher/routePage.action");
    }

    @RequestMapping(value = "/turnCard", method = RequestMethod.POST)
    public String turnCard(Teacher teacher, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (teacherService.turnCard(teacher, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} turn {}'s card.", logUser, teacher.getTeacherName());

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.TURN_CARD_SUCCESS_MESSAGE + teacher.getTeacherName());

            return "redirect:/admin/teacher/page.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.TURN_CARD_FAILURE_MESSAGE + teacher.getTeacherName());
        return "redirect:/admin/teacher/routeTurnCard/" + teacher.getTeacherId() + ".action";
    }

    @RequestMapping(value = "/routeEdit/{teacherId}", method = RequestMethod.GET)
    public ModelAndView routeEditTeacher(@PathVariable("teacherId") int teacherId) {
        Teacher teacher = teacherService.select(teacherId);

        Optional<Teacher> optional = Optional.fromNullable(teacher);
        if (optional.isPresent()) {
            ModelAndView mav = new ModelAndView("admin/teacher/edit");
            mav.addObject(ConstantFields.EDIT_OBJECT_KEY, teacher);
            mav.addObject("departments", teacherService.departments());

            return mav;
        }
        return new ModelAndView("redirect:/admin/teacher/page.action");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editDepartment(Teacher teacher, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (teacherService.edit(teacher, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} edit department {}.", logUser, teacher.getTeacherName());

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.EDIT_SUCCESS_MESSAGE);

            return "redirect:/admin/teacher/page.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.EDIT_FAILURE_MESSAGE);
        return "redirect:/admin/teacher/routeEdit/" + teacher.getTeacherId() + ".action";
    }

    @RequestMapping(value = "/routeDelete/{teacherId}", method = RequestMethod.GET)
    public ModelAndView routeDeleteTeacher(@PathVariable("teacherId") int teacherId) {
        Teacher teacher = teacherService.selectCard(teacherId);

        Optional<Teacher> optional = Optional.fromNullable(teacher);
        if (optional.isPresent()) {
            ModelAndView mav = new ModelAndView("admin/teacher/delete");
            mav.addObject(ConstantFields.DELETE_OBJECT_KEY, teacher);

            return mav;
        }
        return new ModelAndView("redirect:/admin/teacher/page.action");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteDepartment(Teacher teacher, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (teacherService.delete(teacher, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} delete teacher {}.", logUser, teacher.getTeacherName());

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.DELETE_SUCCESS_MESSAGE);

            return "redirect:/admin/teacher/page.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.DELETE_FAILURE_MESSAGE);

        return "redirect:/admin/teacher/routeDelete/" + teacher.getTeacherId() + ".action";
    }
}
