package com.lgb.function.admin.user.controller;

import com.google.common.base.Optional;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.login.AdminUser;
import com.lgb.function.admin.user.service.UserServiceI;
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
@RequestMapping("/admin/user")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceI userService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView listUser(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable) {
        Page<AdminUser> page = userService.list(pageable);

        ModelAndView mav = new ModelAndView("admin/user/list");
        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }

    @RequestMapping(value = "/routeEdit/{adminId}", method = RequestMethod.GET)
    public ModelAndView routeEditUser(@PathVariable("adminId") int adminId) {
        AdminUser adminUser = userService.select(adminId);

        Optional<AdminUser> optional = Optional.fromNullable(adminUser);
        if (optional.isPresent()) {
            ModelAndView mav = new ModelAndView("admin/user/edit");
            mav.addObject(ConstantFields.EDIT_OBJECT_KEY, adminUser);

            return mav;
        }
        return new ModelAndView("redirect:/admin/user/page.action");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(AdminUser adminUser, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (userService.edit(adminUser, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} update admin user {}.", logUser, adminUser.getAdminLoginName());

            redirectAttributes.addFlashAttribute(ConstantFields.EDIT_SUCCESS_KEY, ConstantFields.EDIT_SUCCESS_MESSAGE);

            return "redirect:/admin/user/page.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.EDIT_FAILURE_KEY, ConstantFields.EDIT_FAILURE_MESSAGE);
        return "redirect:/admin/user/routeEdit/" + adminUser.getAdminId() + ".action";
    }

    @RequestMapping(value = "/delete/{adminId}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("adminId") int adminId, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (userService.delete(adminId, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} delete admin user's ID {}.", logUser, adminId);

            redirectAttributes.addFlashAttribute(ConstantFields.DELETE_SUCCESS_KEY, ConstantFields.DELETE_SUCCESS_MESSAGE);

            return "redirect:/admin/user/page.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.DELETE_FAILURE_KEY, ConstantFields.DELETE_FAILURE_MESSAGE);

        return "redirect:/admin/user/page.action";
    }

    @RequestMapping(value = "/routeAdd", method = RequestMethod.GET)
    public String routeAddUser() {
        return "admin/user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(AdminUser adminUser, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (userService.add(adminUser, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} add new admin user {}.", logUser, adminUser.getAdminLoginName());

            redirectAttributes.addFlashAttribute(ConstantFields.ADD_SUCCESS_KEY, ConstantFields.ADD_SUCCESS_MESSAGE);
            return "redirect:/admin/user/page.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.ADD_FAILURE_KEY, ConstantFields.ADD_FAILURE_MESSAGE);
        return "redirect:/admin/user/routeAdd.action";
    }
}
