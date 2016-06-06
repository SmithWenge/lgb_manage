package com.lgb.function.rest.score.repository;

import com.lgb.function.rest.score.RestStudentScoreInfo;

import java.util.List;

public interface RestScoreRepositoryI {
    List<RestStudentScoreInfo> selectStuScore(String userCardNum);
}
