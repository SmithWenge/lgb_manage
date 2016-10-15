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
     public Map<String, List<StudentUser>> detailCountYearStuEduStart(@RequestBody CountDetail countDetail) {
        Map<String, List<StudentUser>> map = new HashMap<>();

        List<StudentUser> studentUsers = countDetailService.yearStuEduStart(countDetail.getKey());

        map.put("students", studentUsers);

        return map;
    }

    @ResponseBody
    @RequestMapping("/stuGender")
    public Map<String, List<StudentUser>> detailCountStuGender(@RequestBody CountDetail countDetail) {
        Map<String, List<StudentUser>> map = new HashMap<>();

        List<StudentUser> studentUsers = countDetailService.stuGender(countDetail.getKey());

        map.put("students", studentUsers);

        return map;
    }

    @ResponseBody
    @RequestMapping("/stuEducational")
    public Map<String, List<StudentUser>> detailCountStuEducational(@RequestBody CountDetail countDetail) {
        Map<String, List<StudentUser>> map = new HashMap<>();

        List<StudentUser> studentUsers = countDetailService.stuEducational(countDetail.getKey());

        map.put("students", studentUsers);

        return map;
    }

    @ResponseBody
    @RequestMapping("/stuOldWorkPlaceType")
    public Map<String, List<StudentUser>> detailCountStuOldWorkPlaceType(@RequestBody CountDetail countDetail) {
        Map<String, List<StudentUser>> map = new HashMap<>();

        List<StudentUser> studentUsers = countDetailService.stuOldWorkPlaceType(countDetail.getKey());

        map.put("students", studentUsers);

        return map;
    }

    @ResponseBody
    @RequestMapping("/stuOldWorkType")
    public Map<String, List<StudentUser>> detailCountStuOldWorkType(@RequestBody CountDetail countDetail) {
        Map<String, List<StudentUser>> map = new HashMap<>();

        List<StudentUser> studentUsers = countDetailService.stuOldWorkType(countDetail.getKey());

        map.put("students", studentUsers);

        return map;
    }

    @ResponseBody
    @RequestMapping("/stuPolitical")
    public Map<String, List<StudentUser>> detailCountStuPolitical(@RequestBody CountDetail countDetail) {
        Map<String, List<StudentUser>> map = new HashMap<>();

        List<StudentUser> studentUsers = countDetailService.stuPolitical(countDetail.getKey());

        map.put("students", studentUsers);

        return map;
    }

    @ResponseBody
    @RequestMapping("/stuPreferential")
    public Map<String, List<StudentUser>> detailCountStuPreferential(@RequestBody CountDetail countDetail) {
        Map<String, List<StudentUser>> map = new HashMap<>();

        List<StudentUser> studentUsers = countDetailService.stuPreferential(countDetail.getKey());

        map.put("students", studentUsers);

        return map;
    }

    @ResponseBody
    @RequestMapping("/stuType")
    public Map<String, List<StudentUser>> detailCountStuType(@RequestBody CountDetail countDetail) {
        Map<String, List<StudentUser>> map = new HashMap<>();

        List<StudentUser> studentUsers = countDetailService.stuType(countDetail.getKey());

        map.put("students", studentUsers);

        return map;
    }

    @ResponseBody
    @RequestMapping("/yearStuBirthday")
    public Map<String, List<StudentUser>> detailCountYearStuBirthday(@RequestBody CountDetail countDetail) {
        Map<String, List<StudentUser>> map = new HashMap<>();

        List<StudentUser> studentUsers = countDetailService.yearStuBirthday(countDetail.getKey());

        map.put("students", studentUsers);

        return map;
    }

    /**
     * 人员级别统计分析
     */
    @ResponseBody
    @RequestMapping("/stuLevel")
    public Map<String, List<StudentUser>> detailStuLevel(@RequestBody CountDetail countDetail) {
        Map<String, List<StudentUser>> map = new HashMap<>();

        List<StudentUser> studentUsers = countDetailService.stuLevel(countDetail.getKey());

        map.put("students", studentUsers);

        return map;
    }
}
