package com.lgb.function.admin.login.controller;

import com.google.common.base.Optional;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.support.utils.MailUtils;
import com.lgb.arc.utils.PasswordUtils;
import com.lgb.function.admin.login.AdminUser;
import com.lgb.function.admin.login.service.AdminLoginServiceI;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminLoginController.class);

    @Autowired
    private AdminLoginServiceI adminLoginService;
    @Autowired
    private MailUtils mailUtils;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(AdminUser adminUser, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);

        Optional<AdminUser> optionalUser = Optional.fromNullable(user);

        if (optionalUser.isPresent()) {
            return new ModelAndView("admin/home/index");
        }

        AdminUser loginUser = adminLoginService.login(adminUser);
        ModelAndView mav = new ModelAndView();

        Optional<AdminUser> optional = Optional.fromNullable(loginUser);
        if (optional.isPresent()) {
            mav.addObject("loginUser", loginUser);
            mav.setViewName("admin/home/index");
            session.setAttribute(ConstantFields.SESSION_ADMIN_KEY, loginUser);

            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] {} login system at {} .", loginUser.getAdminLoginName(), DateTime.now());
        } else {
            mav.setViewName("redirect:/admin/routeLogin.action");
        }

        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(HttpSession session) {
        AdminUser loginUser = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);

        Optional<AdminUser> optional = Optional.fromNullable(loginUser);

        if (optional.isPresent()) {
            return "admin/home/index";
        }

        return "redirect:/admin/routeLogin.action";
    }

    @RequestMapping(value = "/routeLogin", method = RequestMethod.GET)
    public String routeLogin() {
        return "admin/login/adminLogin";
    }

    @RequestMapping(value = "/resetPass", method = RequestMethod.POST)
    public String resetPassword(AdminUser adminUser) {
        AdminUser mailUser = adminLoginService.isExistAdminUser(adminUser);

        Optional<AdminUser> optional = Optional.fromNullable(mailUser);
        if (optional.isPresent()) {
            String randomPass = RandomStringUtils.randomAlphanumeric(10);
            mailUser.setAdminLoginPass(PasswordUtils.encrypt(randomPass));

            if (adminLoginService.newPassword(mailUser)) {
                mailUtils.mailTo(mailUser.getAdminEmail(), "重置密码", "新的密码是:" + randomPass);
                if (LOG.isInfoEnabled())
                    LOG.info("[LGB MANAGE] {} reset password with mail {}.", mailUser.getAdminName(), mailUser.getAdminEmail());
            }
        }

        return "redirect:/admin/routeLogin.action";
    }

    @RequestMapping(value = "/routePass", method = RequestMethod.GET)
    public String routePassword() {
        return "admin/login/adminPassword";
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public ModelAndView password(AdminUser adminUser, HttpSession session) {
        ModelAndView mav = new ModelAndView("redirect:/admin/routeLogin.action");

        AdminUser newUser = adminLoginService.resetPassword(adminUser);
        Optional<AdminUser> optional = Optional.fromNullable(newUser);

        if (optional.isPresent()) {
            session.removeAttribute(ConstantFields.SESSION_ADMIN_KEY);
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] {} reset password.", newUser.getAdminName());

            return mav;
        }

        return new ModelAndView("redirect:/admin/routePass.action");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute(ConstantFields.SESSION_ADMIN_KEY);

        return "redirect:/admin/routeLogin.action";
    }
}
