package com.lgb.function.admin.finance.print.controller;

import com.google.common.base.Optional;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.print.service.printServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/finance")
public class printController {

    @Autowired
    private printServiceI printService;

    @RequestMapping(value = "/routePrint/{studentCourseId}", method = RequestMethod.GET)
    public ModelAndView routeEditFinance(@PathVariable("studentCourseId") int studentCourseId) {
        Finance finance = printService.selectById(studentCourseId);
        ModelAndView mav = new ModelAndView("admin/finance/financePrint");
        mav.addObject("finance", finance);
        return mav;
    }
}

