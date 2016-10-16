package com.lgb.function.admin.student.card.controller;

import com.lgb.function.admin.count.model.JsonModel;
import com.lgb.function.admin.student.card.service.StudentCardCountServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学员考勤情况统计分析
 */
@Controller
@RequestMapping("/admin/student/count")
public class StudentCardCountController {
    @Autowired
    private StudentCardCountServiceI studentCardCountService;

    @RequestMapping("/index")
    public String index() {
        return "admin/student/card/report";
    }

    @ResponseBody
    @RequestMapping("/data")
    public Map<String, List<JsonModel>> data() {
        Map<String, List<JsonModel>> map = new HashMap<String, List<JsonModel>>();

        map.put("studentCardReport", studentCardCountService.queryNumOfStudentCard());

        return map;
    }
}
