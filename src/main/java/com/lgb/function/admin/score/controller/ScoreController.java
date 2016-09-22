package com.lgb.function.admin.score.controller;

import com.google.common.base.Optional;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.login.AdminUser;
import com.lgb.function.admin.major.Major;
import com.lgb.function.admin.score.Score;
import com.lgb.function.admin.score.service.ScoreService;
import com.lgb.function.admin.score.service.ScoreServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/score")
public class ScoreController {
    private static final Logger LOG = LoggerFactory.getLogger(ScoreController.class);

    @Autowired
    private ScoreServiceI scoreService;

    @RequestMapping("/routerList")
    public ModelAndView routerScore(HttpSession session) {
        ModelAndView mav = new ModelAndView("admin/score/scoreList");
        List<Department> departments = scoreService.departments();
        mav.addObject("departments",departments);

        Score sessionScore = (Score)session.getAttribute(ConstantFields.SESSION_SCORE_SEARCH_KEY);
        Optional<Score> optional = Optional.fromNullable(sessionScore);
        if(optional.isPresent()) {
            List<Score> scores = scoreService.select4Page(sessionScore);
            mav.addObject("scores", scores);

            return mav;
        }

        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/majors/{departmentId}", method = RequestMethod.POST)
    public Map<String, List<Major>> majors(@PathVariable("departmentId") int departmentId) {
        Map<String, List<Major>> map = new HashMap<>();
        map.put("majors", scoreService.majors(departmentId));

        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/courses/{majorId}", method = RequestMethod.POST)
    public Map<String, List<Course>> courses(@PathVariable("majorId") int majorId) {
        Map<String, List<Course>> map = new HashMap<>();
        map.put("courses", scoreService.courses(majorId));

        return map;
    }

    @RequestMapping(value = "/pageSearch",method = RequestMethod.POST)
    public ModelAndView selecePage(Score score,HttpSession session) {
        Optional<Score> optional = Optional.fromNullable(score);
        if(optional.isPresent()) {
            session.setAttribute(ConstantFields.SESSION_SCORE_SEARCH_KEY,score);
        }

        ModelAndView mav = new ModelAndView("admin/score/scoreList");
        List<Department> departments = scoreService.departments();
        mav.addObject("departments",departments);

        Score sessionScore = (Score)session.getAttribute(ConstantFields.SESSION_SCORE_SEARCH_KEY);
        List<Score> scores = scoreService.select4Page(sessionScore);
        mav.addObject("scores", scores);
        return mav;
    }

    @RequestMapping(value = "routeEdit/{studentCourseId}",method = RequestMethod.GET)
    public ModelAndView routeEdit(@PathVariable("studentCourseId") int studentCourseId, HttpSession session) {
        Score score = scoreService.seleciById(studentCourseId);
        Optional<Score> optional = Optional.fromNullable(score);
        if(optional.isPresent()) {
            ModelAndView mav = new ModelAndView("admin/score/edit");
            mav.addObject("score",score);

            return mav;
        }
        return new ModelAndView("admin/score/scoreList");
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String scoreEdit(HttpSession session, Score score, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if(scoreService.edit(score,logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} edit score {}.", logUser, score.getStuName());

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.EDIT_SUCCESS_MESSAGE);

            return "redirect:/admin/score/routerList.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.EDIT_FAILURE_MESSAGE);
        return "redirect:/admin/score/routeEdit/" + score.getStudentCourseId() + ".action";

    }
}
