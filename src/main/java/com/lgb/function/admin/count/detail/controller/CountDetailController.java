package com.lgb.function.admin.count.detail.controller;

import com.lgb.function.admin.count.detail.CountDetail;
import com.lgb.function.admin.count.detail.service.CountDetailServiceI;
import com.lgb.function.admin.student.StudentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/count/detail")
public class CountDetailController {
    @Autowired
    private CountDetailServiceI countDetailService;

    @ResponseBody
    @RequestMapping("/yearStuEduStart")
    public Map<String, List<StudentUser>> detailCount(@RequestBody CountDetail countDetail) {
        Map<String, List<StudentUser>> map = new HashMap<>();

        List<StudentUser> studentUsers = countDetailService.yearStuEduStart(countDetail.getKey());

        map.put("students", studentUsers);

        return map;
    }
}
