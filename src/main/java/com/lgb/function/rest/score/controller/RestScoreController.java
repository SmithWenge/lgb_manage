package com.lgb.function.rest.score.controller;

import com.lgb.function.rest.score.RestStudentScoreInfo;
import com.lgb.function.rest.score.service.RestScoreServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/score")
public class RestScoreController {

    @Autowired
    private RestScoreServiceI restScoreService;

    @RequestMapping(value = "/{userCardNum}", method = RequestMethod.GET)
    public List<RestStudentScoreInfo> getStuAllScore(@PathVariable("userCardNum") String userCardNum) {
        return restScoreService.selectStuScore(userCardNum);
    }
}