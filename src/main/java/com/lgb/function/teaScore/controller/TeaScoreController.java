package com.lgb.function.teaScore.controller;

import com.google.common.base.Optional;
import com.lgb.arc.excel.into.ExcelConverter;
import com.lgb.arc.excel.into.ScoreModelExcelMapper;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.teaScore.ScoreModel;
import com.lgb.function.teaScore.service.TeaScoreServiceI;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/teaScore")
public class TeaScoreController {
    private static final Logger LOG = LoggerFactory.getLogger(TeaScoreController.class);
    public static final String UPLOAD_EXCEL_FILE_NAME ="targetFile";

    @Autowired
    private TeaScoreServiceI teaScoreService;

    @RequestMapping(value = "routerLogin")
    public String routerLogin() {
        return "teaScore/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("teacherIdScore");

        return "redirect:/teaScore/routerLogin.action";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(ScoreModel scoreModel,HttpSession session) {
        ScoreModel score = teaScoreService.select(scoreModel);

        Optional<ScoreModel> optional = Optional.fromNullable(score);
        if(optional.isPresent()) {
            session.setAttribute(ConstantFields.SESSION_TEACHER_SCORE_KEY,scoreModel);
            session.setAttribute("teacherIdScore", score);

            return "redirect:/teaScore/routerHelp.action";
        }
        return "redirect:/teaScore/routerLogin.action";

    }

    @RequestMapping(value = "routerHelp")
    public ModelAndView routerHelp(HttpSession session) {
        ModelAndView mav = new ModelAndView("teaScore/help");
        ScoreModel idModel = (ScoreModel)session.getAttribute("teacherIdScore");
        List<ScoreModel> courses = teaScoreService.courses(idModel.getTeacherId());
        mav.addObject("courses", courses);

        ScoreModel teacherModel = (ScoreModel)session.getAttribute(ConstantFields.SESSION_TEACHER_SCORE_KEY);
        mav.addObject("teacher",teacherModel);

        return mav;
    }

    @RequestMapping(value = "routerList",method = RequestMethod.GET)
    public ModelAndView routerList(HttpSession session) {
        ScoreModel idModel = (ScoreModel)session.getAttribute("teacherIdScore");
        ModelAndView mav = new ModelAndView("teaScore/list");
        List<ScoreModel> courses = teaScoreService.courses(idModel.getTeacherId());
        mav.addObject("courses", courses);

        ScoreModel teacherModel = (ScoreModel)session.getAttribute(ConstantFields.SESSION_TEACHER_SCORE_KEY);
        mav.addObject("teacher",teacherModel);

        ScoreModel searchModel = (ScoreModel)session.getAttribute("searchScore");
        Optional<ScoreModel> optional = Optional.fromNullable(searchModel);
        if(optional.isPresent()) {
            List<ScoreModel> scoreModels = teaScoreService.selectScores(searchModel);
            mav.addObject("scoreModels", scoreModels);

            return mav;
        }

        return mav;
    }

    @RequestMapping(value = "Search",method = RequestMethod.POST)
    public ModelAndView Search(HttpSession session,ScoreModel scoreModel) {
        Optional<ScoreModel> optional = Optional.fromNullable(scoreModel);
        if(optional.isPresent()) {
            session.setAttribute("searchScore", scoreModel);
        }

        ModelAndView mav = new ModelAndView("teaScore/list");
        ScoreModel idModel = (ScoreModel)session.getAttribute("teacherIdScore");
        List<ScoreModel> courses = teaScoreService.courses(idModel.getTeacherId());
        mav.addObject("courses", courses);

        ScoreModel teacherModel = (ScoreModel)session.getAttribute(ConstantFields.SESSION_TEACHER_SCORE_KEY);
        mav.addObject("teacher",teacherModel);

        ScoreModel searchScore = (ScoreModel)session.getAttribute("searchScore");
        List<ScoreModel> scoreModels = teaScoreService.selectScores(searchScore);
        mav.addObject("scoreModels",scoreModels);

        return mav;
    }

    @RequestMapping(value = "routeEdit/{studentCourseId}",method = RequestMethod.GET)
    public ModelAndView routeEdit(@PathVariable("studentCourseId") int studentCourseId, HttpSession session) {
        ScoreModel score = teaScoreService.seleciById(studentCourseId);
        Optional<ScoreModel> optional = Optional.fromNullable(score);
        if(optional.isPresent()) {
            ModelAndView mav = new ModelAndView("teaScore/edit");
            mav.addObject("score",score);

            ScoreModel teacherModel = (ScoreModel)session.getAttribute(ConstantFields.SESSION_TEACHER_SCORE_KEY);
            mav.addObject("teacher",teacherModel);

            return mav;
        }
        return new ModelAndView("teaScore/list");
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String scoreEdit(HttpSession session, ScoreModel score, RedirectAttributes redirectAttributes) {

        if(teaScoreService.edit(score)) {

            redirectAttributes.addFlashAttribute(ConstantFields.EDIT_SUCCESS_KEY, ConstantFields.EDIT_SUCCESS_MESSAGE);

            return "redirect:/teaScore/routerList.action";
        }
        redirectAttributes.addFlashAttribute(ConstantFields.EDIT_FAILURE_KEY, ConstantFields.EDIT_FAILURE_MESSAGE);

        return "redirect:/teaScore/routeEdit/" + score.getStudentCourseId() + ".action";
    }

    @RequestMapping(value = "routerImport",method = RequestMethod.GET)
    public ModelAndView routerImport(HttpSession session) {
        ScoreModel scoreModel = (ScoreModel)session.getAttribute(ConstantFields.SESSION_TEACHER_SCORE_KEY);
        Optional<ScoreModel> optional = Optional.fromNullable(scoreModel);
        if(optional.isPresent()) {
            ModelAndView mav = new ModelAndView("teaScore/import");
            ScoreModel teacherModel = (ScoreModel)session.getAttribute(ConstantFields.SESSION_TEACHER_SCORE_KEY);
            mav.addObject("teacher", teacherModel);

            return mav;
        }
        return new ModelAndView("teaScore/login");
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String downloadTemplate(HttpServletResponse response, HttpSession session) {
        if (null == session.getAttribute(ConstantFields.SESSION_TEACHER_SCORE_KEY)) return "redirect:/teaScore/routerLogin.action";

        String templatePath = session.getServletContext().getRealPath("/") + "WEB-INF/data/template/score.xls";
        try {
            File file = new File(templatePath);

            response.setContentType("application/x-excel");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            response.setHeader("Content-Length", String.valueOf(file.length()));

            int length = 0;
            byte[] buffer = new byte[1024];

            FileInputStream fis = new FileInputStream(file);
            OutputStream os = response.getOutputStream();

            while (-1 != (length = fis.read(buffer, 0, buffer.length))) {
                os.write(buffer, 0, length);
            }

            os.flush();
            os.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private File save(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String targetPath = request.getSession().getServletContext().getRealPath("/WEB-INF/data/export/");
        String sourceFileName = file.getOriginalFilename();

        String dateString = new DateTime().toString("MM-dd-yyyy-HH-mm-ss-SSS");
        String prefixName = sourceFileName.substring(0, sourceFileName.indexOf("."));
        String subName = sourceFileName.substring(sourceFileName.lastIndexOf("."));
        String newName = prefixName + "-" + dateString + subName;

        File targetFile = new File(targetPath, newName);
        request.getSession().setAttribute(UPLOAD_EXCEL_FILE_NAME, targetFile.getName());

        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        try {
            file.transferTo(targetFile);

            return targetFile;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String add(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        File importFile = save(file, request);

        if (null == importFile) return "redirect:/teaScore/routerImport.action";

        ExcelConverter<ScoreModel> converter = new ExcelConverter<ScoreModel>();
        List<ScoreModel> scoreModels = converter.readFromExcel(importFile, 1, new ScoreModelExcelMapper());

        if (teaScoreService.save(scoreModels)) {
            return "redirect:/teaScore/routerList.action";
        }

        return "redirect:/teaScore/routerImport.action";
    }
}
