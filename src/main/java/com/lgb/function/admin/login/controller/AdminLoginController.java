package com.lgb.function.admin.login.controller;

import com.google.common.base.Optional;
import com.lgb.function.admin.login.AdminUser;
import com.lgb.function.admin.login.service.AdminLoginServiceI;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminLoginController.class);

    @Autowired
    private AdminLoginServiceI adminLoginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(AdminUser adminUser) {
        AdminUser loginUser = adminLoginService.login(adminUser);
        ModelAndView mav = new ModelAndView();

        Optional<AdminUser> optional = Optional.fromNullable(loginUser);
        if (optional.isPresent()) {
            mav.addObject("loginUser", loginUser);
            mav.setViewName("index");

            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] {} login system at {} .", loginUser.getAdminLoginName(), DateTime.now());
        } else {
            mav.setViewName("redirect:/admin/routeLogin");
        }

        return mav;
    }

    @RequestMapping(value = "/routeLogin", method = RequestMethod.GET)
    public String routeLogin() {
        return "admin/login/adminLogin";
    }
}
