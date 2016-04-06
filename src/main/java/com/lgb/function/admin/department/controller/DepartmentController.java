package com.lgb.function.admin.department.controller;

import com.google.common.base.Optional;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.department.service.DepartmentServiceI;
import com.lgb.function.admin.login.AdminUser;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/department")
public class DepartmentController {
    private static final Logger LOG = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentServiceI departmentService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView listDepartment(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable) {
        Page<Department> page = departmentService.list(pageable);

        ModelAndView mav = new ModelAndView("admin/department/list");
        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }


    @RequestMapping(value = "/routeAdd", method = RequestMethod.GET)
    public ModelAndView routeAdd() {
        ModelAndView mav = new ModelAndView("admin/department/add");

        mav.addObject("departmentAdmins", departmentService.listDepartmentAdmins());

        return mav;
    }

    @RequestMapping(value = "/departmentName", method = RequestMethod.POST)
    @ResponseBody
    public boolean nameExist(Department department) {
        if (departmentService.exist(department)) return true;
        else return false;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Department department, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (departmentService.add(department, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} add new department {}.", logUser, department.getDepartmentName());

            redirectAttributes.addFlashAttribute(ConstantFields.ADD_SUCCESS_KEY, ConstantFields.ADD_SUCCESS_MESSAGE);
            return "redirect:/admin/department/page.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.ADD_FAILURE_KEY, ConstantFields.ADD_FAILURE_MESSAGE);
        return "redirect:/admin/department/routeAdd.action";
    }

    @RequestMapping(value = "/routeEdit/{departmentId}", method = RequestMethod.GET)
    public ModelAndView routeEditDepartment(@PathVariable("departmentId") int departmentId) {
        Department department = departmentService.select(departmentId);

        Optional<Department> optional = Optional.fromNullable(department);
        if (optional.isPresent()) {
            ModelAndView mav = new ModelAndView("admin/department/edit");
            mav.addObject(ConstantFields.EDIT_OBJECT_KEY, department);
            mav.addObject("departmentAdmins", departmentService.listDepartmentAdmins());

            return mav;
        }
        return new ModelAndView("redirect:/admin/department/page.action");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editDepartment(Department department, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (departmentService.edit(department, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} edit department {}.", logUser, department.getDepartmentName());

            redirectAttributes.addFlashAttribute(ConstantFields.EDIT_SUCCESS_KEY, ConstantFields.EDIT_SUCCESS_MESSAGE);

            return "redirect:/admin/department/page.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.EDIT_FAILURE_KEY, ConstantFields.EDIT_FAILURE_MESSAGE);
        return "redirect:/admin/department/routeEdit/" + department.getDepartmentId() + ".action";
    }

    @RequestMapping(value = "/delete/{departmentId}", method = RequestMethod.GET)
    public String deleteDepartment(@PathVariable("departmentId") int departmentId, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (departmentService.delete(departmentId, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} delete department's ID {}.", logUser, departmentId);

            redirectAttributes.addFlashAttribute(ConstantFields.DELETE_SUCCESS_KEY, ConstantFields.DELETE_SUCCESS_MESSAGE);

            return "redirect:/admin/department/page.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.DELETE_FAILURE_KEY, ConstantFields.DELETE_FAILURE_MESSAGE);

        return "redirect:/admin/department/page.action";
    }
}
