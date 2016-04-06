package com.lgb.function.admin.major.controller;

import com.google.common.base.Optional;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.login.AdminUser;
import com.lgb.function.admin.major.Major;
import com.lgb.function.admin.major.service.MajorServiceI;
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
@RequestMapping("/admin/major")
public class MajorController {
    @Autowired
    private MajorServiceI majorService;

    private static final Logger LOG = LoggerFactory.getLogger(MajorController.class);

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView listDepartment(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable) {
        Page<Major> page = majorService.list(pageable);

        ModelAndView mav = new ModelAndView("admin/major/list");
        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }

    @RequestMapping(value = "/routeAdd", method = RequestMethod.GET)
    public ModelAndView routeMajorAdd() {
        ModelAndView mav = new ModelAndView("admin/major/add");

        mav.addObject("departments", majorService.departments());

        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String majorAdd(Major major, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (majorService.add(major, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} add new major {}.", logUser, major.getMajorName());

            redirectAttributes.addFlashAttribute(ConstantFields.ADD_SUCCESS_KEY, ConstantFields.ADD_SUCCESS_MESSAGE);
            return "redirect:/admin/major/page.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.ADD_FAILURE_KEY, ConstantFields.ADD_FAILURE_MESSAGE);
        return "redirect:/admin/major/routeAdd.action";
    }

    @RequestMapping(value = "/majorName", method = RequestMethod.POST)
    @ResponseBody
    public boolean nameExist(Major major) {
        if (majorService.exist(major)) return true;
        else return false;
    }

    @RequestMapping(value = "/routeEdit/{majorId}", method = RequestMethod.GET)
    public ModelAndView routeEditMajor(@PathVariable("majorId") int majorId) {
        Major major = majorService.select(majorId);

        Optional<Major> optional = Optional.fromNullable(major);
        if (optional.isPresent()) {
            ModelAndView mav = new ModelAndView("admin/major/edit");
            mav.addObject(ConstantFields.EDIT_OBJECT_KEY, major);
            mav.addObject("departments", majorService.departments());

            return mav;
        }

        return new ModelAndView("redirect:/admin/major/page.action");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editMajor(Major major, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (majorService.edit(major, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} edit major {}.", logUser, major.getMajorName());

            redirectAttributes.addFlashAttribute(ConstantFields.EDIT_SUCCESS_KEY, ConstantFields.EDIT_SUCCESS_MESSAGE);

            return "redirect:/admin/major/page.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.EDIT_FAILURE_KEY, ConstantFields.EDIT_FAILURE_MESSAGE);
        return "redirect:/admin/major/routeEdit/" + major.getMajorId() + ".action";
    }

    @RequestMapping(value = "/delete/{majorId}", method = RequestMethod.GET)
    public String deleteMajor(@PathVariable("majorId") int majorId, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (majorService.delete(majorId, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} delete major's ID {}.", logUser, majorId);

            redirectAttributes.addFlashAttribute(ConstantFields.DELETE_SUCCESS_KEY, ConstantFields.DELETE_SUCCESS_MESSAGE);

            return "redirect:/admin/major/page.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.DELETE_FAILURE_KEY, ConstantFields.DELETE_FAILURE_MESSAGE);

        return "redirect:/admin/major/page.action";
    }
}
