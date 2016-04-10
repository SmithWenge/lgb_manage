package com.lgb.function.admin.count.controller;

import com.lgb.function.admin.count.model.JsonModel;
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
    public Map<String, List<JsonModel>> yearStuEduStartMap() {
        Map<String, List<JsonModel>> map = new HashMap<>();
        map.put("yearStuEduStart", countService.queryNumOfStuEduStart());
        map.put("stuGender",countService.queryNumOfGender());
        map.put("stuEducational",countService.queryNumOfEducational());
        map.put("stuOldWorkPlaceType",countService.queryNumOfOldPlaceType());
        map.put("stuOldWorkType",countService.queryNumOfOldWorkType());
        map.put("stuPolitical",countService.queryNumOfStuPolitical());
        map.put("stuPreferential",countService.queryNumOfStuPreferential());
        map.put("stuType",countService.queryNumOfStuType());
        map.put("yearOfStuBirthday",countService.queryNumOfStuBirthday());

        return map;
    }

}
