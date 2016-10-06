package com.lgb.function.admin.setting.controller;

import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.login.AdminUser;
import com.lgb.function.admin.setting.LGBConfig;
import com.lgb.function.admin.setting.service.SettingServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/setting")
public class SettingController {
    private static final Logger LOG = LoggerFactory.getLogger(SettingController.class);

    @Autowired
    private SettingServiceI settingService;

    /**
     * 路由到颜色配置页面
     *
     * @return
     */
    @RequestMapping("/route")
    public ModelAndView routeSetting() {
        String nowColorConfig = settingService.nowSettingColor();

        ModelAndView mav = new ModelAndView("admin/setting/config");

        mav.addObject("color", nowColorConfig);

        return mav;
    }

    /**
     * 颜色配置
     *
     * @param configColor
     * @param session
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/config")
    public String newConfig(@RequestParam("configColor") String configColor, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (settingService.config(configColor, logUser)) {
            session.setAttribute(ConstantFields.SESSION_BG_COLOR, configColor);

            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} config new background color {}.", logUser, configColor);

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.CONFIG_SUCCESS_MESSAGE);
        }

        return "redirect:/admin/setting/route.action";
    }

    /**
     * 路由到基本配置页面
     */
    @RequestMapping("/routeBasic")
    public ModelAndView routeBasic() {
        LGBConfig config = settingService.basicConfig(ConstantFields.DEFAULT_CONFIG_ID);

        ModelAndView mav = new ModelAndView("admin/setting/basicConfig");

        mav.addObject("config", config);

        return mav;
    }

    /**
     * 更新配置信息
     */
    @RequestMapping("/basicConfig")
    public String updateBasicConfig(LGBConfig config, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (settingService.updateBasicConfig(config, logUser)) {

            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} update basic config.", logUser);

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.CONFIG_SUCCESS_MESSAGE);
        }

        return "redirect:/admin/setting/routeBasic.action";
    }
}
