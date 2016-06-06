package com.lgb.function.rest.score.service.impl;

import com.lgb.function.rest.score.RestStudentScoreInfo;
import com.lgb.function.rest.score.repository.RestScoreRepositoryI;
import com.lgb.function.rest.score.service.RestScoreServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestScoreService implements RestScoreServiceI{

    @Autowired
    private RestScoreRepositoryI restScoreRepository;

    @Override
    public List<RestStudentScoreInfo> selectStuScore(String userCardNum) {
        return restScoreRepository.selectStuScore(userCardNum);
    }
}
