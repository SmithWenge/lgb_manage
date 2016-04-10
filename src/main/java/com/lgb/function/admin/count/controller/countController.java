package com.lgb.function.admin.count.controller;

import com.lgb.function.admin.count.model.YearStuEduStart;
import com.lgb.function.admin.count.service.CountServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/count")
public class CountController {
    @Autowired
    private CountServiceI countService;

    @RequestMapping(value = "/index")
    public String index() {
        return "admin/count/all";
    }

    @ResponseBody
    @RequestMapping("/all")
    public Map<String, List<YearStuEduStart>> yearStuEduStartMap() {
        Map<String, List<YearStuEduStart>> map = new HashMap<>();
        map.put("yearStuEduStart", countService.queryNumOfStuEduStart());

        return map;
    }

}
