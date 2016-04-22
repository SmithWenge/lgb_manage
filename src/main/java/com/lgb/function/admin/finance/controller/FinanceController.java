package com.lgb.function.admin.finance.controller;

import com.google.common.base.Optional;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.count.model.InfoCount;
import com.lgb.function.admin.finance.count.model.JsonModel;
import com.lgb.function.admin.finance.count.service.FCServiceI;
import com.lgb.function.admin.finance.service.FinanceServiceI;
import com.lgb.function.admin.login.AdminUser;
import com.lgb.function.admin.major.Major;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/admin/finance")
public class FinanceController {
    private static final Logger LOG = LoggerFactory.getLogger(FinanceController.class);

    @Autowired
    private FinanceServiceI financeService;
    @Autowired
    private FCServiceI fcService;

    @RequestMapping(value = "/page")
    public ModelAndView showLog(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE)
                                        Pageable pageable, Finance finance, HttpSession session) {
        Finance searchFinance = (Finance) session.getAttribute(ConstantFields.SESSION_FINANCE_SEARCH_KEY);

        Optional<Finance> optional = Optional.fromNullable(searchFinance);
        if (optional.isPresent()) {
            finance = searchFinance;
        }

        ModelAndView mav = new ModelAndView("admin/finance/list");
        Page<Finance> page = financeService.selectUnFinance4Page(finance, pageable);
        mav.addObject(ConstantFields.PAGE_KEY, page);

        List<Department> departments = financeService.departments();
        mav.addObject("departments", departments);

        return mav;
    }

    @RequestMapping(value = "/pageSearch")
    public ModelAndView searchLog(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable,
                                  Finance finance, HttpSession session) {
        session.setAttribute(ConstantFields.SESSION_FINANCE_SEARCH_KEY, finance);

        ModelAndView mav = new ModelAndView("admin/finance/list");
        Page<Finance> page = financeService.selectUnFinance4Page(finance, pageable);
        mav.addObject(ConstantFields.PAGE_KEY, page);

        List<Department> departments = financeService.departments();
        mav.addObject("departments", departments);

        return mav;
    }

    @RequestMapping(value = "/routePage", method = RequestMethod.GET)
    public ModelAndView showFirstPage(HttpSession session) {
        session.removeAttribute(ConstantFields.SESSION_FINANCE_SEARCH_KEY);

        ModelAndView mav = new ModelAndView("admin/finance/list");

        Page<Finance> contents = financeService.selectUnFinance4Page(new Finance(), new PageRequest(0, ConstantFields.DEFAULT_PAGE_SIZE));
        mav.addObject(ConstantFields.PAGE_KEY, contents);

        List<Department> departments = financeService.departments();
        mav.addObject("departments", departments);

        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/majors/{departmentId}", method = RequestMethod.POST)
    public Map<String, List<Major>> majors(@PathVariable("departmentId") int departmentId) {
        Map<String, List<Major>> map = new HashMap<>();
        map.put("majors", financeService.majors(departmentId));

        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/courses/{majorId}", method = RequestMethod.POST)
    public Map<String, List<Course>> course(@PathVariable("majorId") int majorId) {
        Map<String, List<Course>> map = new HashMap<>();
        map.put("courses", financeService.courses(majorId));

        return map;
    }

    @RequestMapping(value = "/routeEdit/{studentCourseId}", method = RequestMethod.GET)
    public ModelAndView routeEditFinance(@PathVariable("studentCourseId") int studentCourseId) {
        Finance finance = financeService.select(studentCourseId);

        Optional<Finance> optional = Optional.fromNullable(finance);
        if (optional.isPresent()) {
            List<Finance> finances = financeService.selectFinanceCourse(studentCourseId);

            ModelAndView mav = new ModelAndView("admin/finance/edit");
            mav.addObject(ConstantFields.EDIT_OBJECT_KEY, finance);
            mav.addObject("finances", finances);

            return mav;
        }
        return new ModelAndView("redirect:/admin/department/page.action");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editFinance(Finance finance, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (financeService.edit(finance, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} edit finance with course name is {} and studentCourseId is {}.", logUser, finance.getCourseName(), finance.getStudentCourseId());

            redirectAttributes.addFlashAttribute(ConstantFields.EDIT_SUCCESS_KEY, ConstantFields.EDIT_SUCCESS_MESSAGE);

            return "redirect:/admin/finance/routePage.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.EDIT_FAILURE_KEY, ConstantFields.EDIT_FAILURE_MESSAGE);
        return "redirect:/admin/finance/routeEdit/" + finance.getStudentCourseId() + ".action";
    }

    @RequestMapping(value = "/routeCount", method = RequestMethod.GET)
    public ModelAndView showCountPage() {
        ModelAndView mav = new ModelAndView("admin/finance/financedList");

        Page<Finance> contents = financeService.selectFinance4Page(new Finance(), new PageRequest(0, ConstantFields.DEFAULT_PAGE_SIZE));
        mav.addObject(ConstantFields.PAGE_KEY, contents);

        InfoCount infoCount = new InfoCount();
        infoCount.setDaySumActualTuition(fcService.queryDaySumActualTuition().getDaySumActualTuition());
        infoCount.setSumActualTuition(fcService.querySumOfActualTuition().getSumActualTuition());
        mav.addObject("infoCount", infoCount);

        return mav;
    }

    @RequestMapping(value = "/countPage")
    public ModelAndView showCountLog(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE)
                                Pageable pageable, Finance finance) {

        ModelAndView mav = new ModelAndView("admin/finance/financedList");
        Page<Finance> page = financeService.selectFinance4Page(finance, pageable);
        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }
    @RequestMapping(value = "/routeDayCount",method = RequestMethod.POST)
     public ModelAndView showDayCount(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable,
                                      Finance finance) {
        ModelAndView mav = new ModelAndView("admin/finance/financedList");

        Page<Finance> contents = financeService.selectFinance4Page(finance,pageable);
        mav.addObject(ConstantFields.PAGE_KEY, contents);

        return mav;
     }

    @RequestMapping(value = "/routeEcharts",method = RequestMethod.GET)
    public String showFinanceCount(Finance finance) {
        return "admin/finance/financeCount";
    }

    @ResponseBody
    @RequestMapping(value = "/echarts", method = RequestMethod.POST)
    public Map<String, List<JsonModel>> echarts(@RequestBody Finance finance) {
        List<JsonModel> jsonModels = fcService.queryMonthSumFinance(finance);
        Map<String, List<JsonModel>> map = new HashMap<>();
        map.put("financeCount", jsonModels);

        return map;
    }
}
