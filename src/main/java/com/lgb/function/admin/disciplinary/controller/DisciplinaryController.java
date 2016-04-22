package com.lgb.function.admin.disciplinary.controller;

import com.google.common.base.Optional;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.disciplinary.Disciplinary;
import com.lgb.function.admin.disciplinary.service.DisciplinaryServiceI;
import com.lgb.function.admin.login.AdminUser;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("admin/disciplinary")
public class DisciplinaryController {
    private static final Logger LOG = LoggerFactory.getLogger(DisciplinaryController.class);
    @Autowired
    private DisciplinaryServiceI disciplinaryService;

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public ModelAndView query4Page (@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE)
                                        Pageable pageable, Disciplinary disciplinary, HttpSession session) {
//        Disciplinary searchDisciplinary = (Disciplinary) session.getAttribute(ConstantFields.SESSION_STU_SEARCH_KEY);
//
//        Optional<Disciplinary> optional = Optional.fromNullable(searchDisciplinary);
//        if (optional.isPresent()) {
//            disciplinary = searchDisciplinary;
//        }
        ModelAndView mav = new ModelAndView("admin/disciplinary/list");
        Page<Disciplinary> page = disciplinaryService.query4page(disciplinary, pageable);

        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;

    }
    @RequestMapping(value = "/pageSearch")
    public ModelAndView queryForCard(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable,
                                     Disciplinary disciplinary, HttpSession session) {
        session.setAttribute(ConstantFields.SESSION_STU_SEARCH_KEY, disciplinary);
        ModelAndView mav = new ModelAndView("admin/disciplinary/list");
        Page<Disciplinary> page = disciplinaryService.query4page(disciplinary, pageable);

        mav.addObject(ConstantFields.PAGE_KEY, page);
        return mav;
    }
    @RequestMapping(value = "/CountSearch")
    public ModelAndView queryForCount(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable,
                                      Disciplinary disciplinary, HttpSession session) {
        ModelAndView mav = new ModelAndView("admin/disciplinary/list");
        Page<Disciplinary> page = disciplinaryService.query4Count(disciplinary, pageable);

        mav.addObject(ConstantFields.PAGE_KEY, page);
        return mav;
    }
    @RequestMapping(value = "/moreInfo/{stuId}", method = RequestMethod.GET)
    public ModelAndView queryMore(@PathVariable("stuId") int stuId) {
        List<Disciplinary> disciplinary = disciplinaryService.queryMore(stuId);
        if (disciplinary != null){
            ModelAndView mav = new ModelAndView("admin/disciplinary/more");
            mav.addObject(ConstantFields.DISCIPLINARY_INFO_KEY,disciplinary);
            return mav;
        }

        return new ModelAndView("redirect:/admin/disciplinary/page.action");
    }

    @RequestMapping(value = "/routeAdd", method = RequestMethod.GET)
    public String routeAddStudent() {
        return "admin/disciplinary/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(Disciplinary disciplinary, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser)session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminName();

        if (disciplinaryService.addDis(disciplinary, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} add new disciplinary user {}.", logUser, disciplinary.getStuName());

            redirectAttributes.addFlashAttribute(ConstantFields.ADD_SUCCESS_KEY, ConstantFields.ADD_SUCCESS_MESSAGE);
            return "redirect:/admin/disciplinary/page.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.ADD_FAILURE_KEY, ConstantFields.ADD_FAILURE_MESSAGE);
        return "redirect:/admin/disciplinary/routeAdd.action";
    }

    @RequestMapping(value = "/cardNum", method = RequestMethod.POST)
    @ResponseBody
    public boolean nameExist(Disciplinary disciplinary) {
        if (disciplinaryService.existCardNum(disciplinary)) {
            return false;
        } else return true;
    }
}