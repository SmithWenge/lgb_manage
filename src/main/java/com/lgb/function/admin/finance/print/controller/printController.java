package com.lgb.function.admin.finance.print.controller;

import com.google.common.base.Optional;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.print.service.printServiceI;
import com.lgb.function.admin.finance.service.FinanceServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    @Autowired
    private FinanceServiceI financeService;

    @RequestMapping(value = "/routePrint/{studentCourseId}", method = RequestMethod.GET)
    public ModelAndView routePrintBill(@PathVariable("studentCourseId") int studentCourseId) {
        Finance finance = printService.selectById(studentCourseId);
        ModelAndView mav = new ModelAndView("admin/finance/financePrint");
        mav.addObject("finance", finance);
        return mav;
    }

    @RequestMapping(value = "/routePrintBill", method = RequestMethod.POST)
    public ModelAndView routePrintBill(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE)
                                          Pageable pageable,Finance finance) {
        ModelAndView mav = new ModelAndView("admin/finance/printList");
        Page<Finance> page = printService.selectPrintAll(finance,pageable);
        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }

    @RequestMapping(value = "/printPage")
    public ModelAndView showCountLog(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE)
                                     Pageable pageable, Finance finance) {

        ModelAndView mav = new ModelAndView("admin/finance/printList");
        Page<Finance> page = financeService.selectFinance4Page(finance, pageable);
        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }


}

