package com.lgb.function.admin.finance.query.controller;

import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.query.service.FinanceQueryServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/finance/query")
public class FinanceQueryController {
    @Autowired
    private FinanceQueryServiceI financeQueryService;

    /**
     * 查询课程缴费情况
     */
    @RequestMapping("/all")
    public ModelAndView allFinance(Course course) {
        List<Finance> finances = financeQueryService.allPayFinance(course);
        List<Course> courses = financeQueryService.getAllCourses();

        ModelAndView mav = new ModelAndView("admin/finance/query/allPay");
        mav.addObject("finances", finances);
        mav.addObject("courses", courses);

        return mav;
    }
}
