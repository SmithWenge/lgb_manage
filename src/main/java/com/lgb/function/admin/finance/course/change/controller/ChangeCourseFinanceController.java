package com.lgb.function.admin.finance.course.change.controller;

import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.course.change.ChangeCourse;
import com.lgb.function.admin.finance.course.change.service.ChangeCourseFinanceServiceI;
import com.lgb.function.admin.login.AdminUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/finance/course/change")
public class ChangeCourseFinanceController {
    private static final Logger LOG = LoggerFactory.getLogger(ChangeCourseFinanceController.class);

    @Autowired
    private ChangeCourseFinanceServiceI changeCourseFinanceService;

    /**
     * 查询所有的换课学生未操作费用数据
     *
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView changeCourseFinanceList() {
        List<ChangeCourse> finances = changeCourseFinanceService.changeCourseFinances();

        ModelAndView mav = new ModelAndView("admin/finance/course/change/list");
        mav.addObject("finances", finances);

        return mav;
    }

    /**
     * 收取/退还换课费用
     */
    @RequestMapping("/{changeCourseId}/tuition")
    public String changeCourseTuition(@PathVariable int changeCourseId, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (changeCourseFinanceService.tuition(changeCourseId, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] tuition change course id is {} and operation user is {}.", logUser, changeCourseId, logUser);

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.CHANGE_COURSE_TUITION_SUCCESS_MESSAGE);

            return "redirect:/admin/finance/course/change/list.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.CHANGE_COURSE_TUITION_FAILURE_MESSAGE);
        return "redirect:/admin/finance/course/change/list.action";
    }
}
