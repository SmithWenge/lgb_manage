package com.lgb.function.admin.finance.refund.controller;

import com.google.common.base.Optional;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.refund.RefundStudentCourse;
import com.lgb.function.admin.finance.refund.service.FinanceRefundServiceI;
import com.lgb.function.admin.login.AdminUser;
import com.lgb.function.admin.setting.LGBConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/finance/refund")
public class FinanceRefundController {
    private static final Logger LOG = LoggerFactory.getLogger(FinanceRefundController.class);

    @Autowired
    private FinanceRefundServiceI financeRefundService;

    /**
     * 路由到退款根据账号查询页面
     *
     * @return
     */
    @RequestMapping("/route")
    public String routeRefund() {
        return "admin/finance/refund/refund";
    }

    /**
     * 根据学员卡号查询这个学员的所有选择课程
     *
     * @param stuCardNum
     * @return
     */
    @RequestMapping("/query")
    public ModelAndView queryRefundCourse(@RequestParam String stuCardNum) {
        List<Finance> finances = financeRefundService.getStudentFinance(stuCardNum);

        ModelAndView mav = new ModelAndView("admin/finance/refund/listRefund");
        mav.addObject("finances", finances);

        return mav;
    }

    /**
     * 路由到具体的退款页面
     */
    @RequestMapping("/routeRefund/{studentCourseId}")
    public ModelAndView route2Refund(@PathVariable int studentCourseId, RedirectAttributes redirectAttributes) {
        Finance finance = financeRefundService.queryRefundInfo(studentCourseId);

        Optional<Finance> optional = Optional.fromNullable(finance);
        if (optional.isPresent()) {
            ModelAndView mav = new ModelAndView("admin/finance/refund/refundInfo");
            LGBConfig config = financeRefundService.nowConfig();

            mav.addObject("finance", finance);
            mav.addObject("config", config);

            return mav;
        } else {
            String stuCardNum = financeRefundService.getStudentCardNum(studentCourseId);

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.ROUTE_REFUND_SUCCESS_MESSAGE);
            return new ModelAndView("redirect:/admin/finance/refund/query.action?stuCardNum=" + stuCardNum);
        }
    }

    /**
     * 添加新的退课纪录
     */
    @RequestMapping(value = "/newRefund", method = RequestMethod.POST)
    public String addNewRefund(RefundStudentCourse refundStudentCourse, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);

        String operationUser = user.getAdminName();
        refundStudentCourse.setRefundUser(operationUser);

        if (financeRefundService.updateRefund(refundStudentCourse)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} refund student course studentCourse's ID {}.", operationUser, refundStudentCourse.getStudentCourseId());

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.REFUND_SUCCESS_MESSAGE);
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.REFUND_FAILURE_MESSAGE);
        }

        String stuCardNum = financeRefundService.getStudentCardNum(refundStudentCourse.getStudentCourseId());

        return "redirect:/admin/finance/refund/query.action?stuCardNum=" + stuCardNum;
    }

    /**
     * 查看退款纪录
     */
    @RequestMapping("/oldRefund")
    public ModelAndView listOldRefund() {
        List<RefundStudentCourse> courses = financeRefundService.queryOldRefund();

        ModelAndView mav = new ModelAndView("admin/finance/refund/oldRefundList");

        mav.addObject("courses", courses);

        return mav;
    }
}
