package com.lgb.function.admin.student.controller;

import com.google.common.base.Optional;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.student.StudentUser;
import com.lgb.function.admin.student.service.StudentServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/student")
public class StudentController {
    private static final Logger LOG = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentServiceI studentService;

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public ModelAndView ListUser(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable){
        Page<StudentUser> page = studentService.list(pageable);

        ModelAndView mav = new ModelAndView("admin/student/list");
        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }

    @RequestMapping(value = "/routeEdit/{adminId}", method = RequestMethod.GET)
    public ModelAndView routeEditUser(@PathVariable("adminId") int stuId) {
        StudentUser studentUser = studentService.select(stuId);

        Optional<StudentUser> optional = Optional.fromNullable(studentUser);
        if (optional.isPresent()) {
            ModelAndView mav = new ModelAndView("admin/student/edit");
            mav.addObject(ConstantFields.EDIT_OBJECT_KEY, studentUser);

            return mav;
        }
        return new ModelAndView("redirect:/admin/student/page.action");
    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editStudent(StudentUser studentUser, HttpSession session, RedirectAttributes redirectAttributes) {
        StudentUser user = (StudentUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getStuName();

        if (studentService.edit(studentUser, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} update admin student {}.", logUser, studentUser.getStuName());

            redirectAttributes.addFlashAttribute(ConstantFields.EDIT_SUCCESS_KEY, ConstantFields.EDIT_SUCCESS_MESSAGE);

            return "redirect:/admin/student/page.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.EDIT_FAILURE_KEY, ConstantFields.EDIT_FAILURE_MESSAGE);
        return "redirect:/admin/student/routeEdit/" + studentUser.getStuID() + ".action";
    }

    @RequestMapping(value = "/delete/{stuId}", method = RequestMethod.GET)
    public String deleteStudnet(@PathVariable("stuId") int stuId, HttpSession session, RedirectAttributes redirectAttributes) {
        StudentUser user = (StudentUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getStuName();

        if (studentService.delete(stuId, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} delete admin student's ID {}.", logUser, stuId);

            redirectAttributes.addFlashAttribute(ConstantFields.DELETE_SUCCESS_KEY, ConstantFields.DELETE_SUCCESS_MESSAGE);

            return "redirect:/admin/student/page.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.DELETE_FAILURE_KEY, ConstantFields.DELETE_FAILURE_MESSAGE);

        return "redirect:/admin/user/page.action";
    }

    @RequestMapping(value = "/routeAdd", method = RequestMethod.GET)
    public String routeAddStudent() {
        return "admin/student/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStudent(StudentUser studentUser, HttpSession session, RedirectAttributes redirectAttributes) {
        StudentUser user = (StudentUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getStuName();

        if (studentService.add(studentUser,  logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} add new admin user {}.", logUser, studentUser.getStuName());

            redirectAttributes.addFlashAttribute(ConstantFields.ADD_SUCCESS_KEY, ConstantFields.ADD_SUCCESS_MESSAGE);
            return "redirect:/admin/student/page.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.ADD_FAILURE_KEY, ConstantFields.ADD_FAILURE_MESSAGE);
        return "redirect:/admin/student/routeAdd.action";
    }
}