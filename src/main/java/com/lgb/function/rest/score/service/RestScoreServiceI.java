package com.lgb.function.rest.score.service;

import com.lgb.function.rest.score.RestStudentScoreInfo;

import java.util.List;


public interface RestScoreServiceI {

    List<RestStudentScoreInfo> selectStuScore(String userCardNum);
}
