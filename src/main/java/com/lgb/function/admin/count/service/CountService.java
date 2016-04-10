package com.lgb.function.admin.count.service;

import com.lgb.function.admin.count.model.*;
import com.lgb.function.admin.count.repository.CountRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountService implements CountServiceI {
    @Autowired
    private CountRepositoryI countRepository;
    @Override
    public List<JsonModel> queryNumOfGender() {
        return countRepository.queryNumOfGender();
    }

    @Override
    public List<JsonModel> queryNumOfEducational() {
        return countRepository.queryNumOfEducational();
    }

    @Override
    public List<JsonModel> queryNumOfOldPlaceType() {
        return countRepository.queryNumOfOldPlaceType();
    }

    @Override
    public List<JsonModel> queryNumOfOldWorkType() {
        return countRepository.queryNumOfOldWorkType();
    }

    @Override
    public List<JsonModel> queryNumOfStuPolitical() {
        return countRepository.queryNumOfStuPolitical();
    }

    @Override
    public List<JsonModel> queryNumOfStuPreferential() {
        return countRepository.queryNumOfStuPreferential();
    }

    @Override
    public List<JsonModel> queryNumOfStuType() {
        return countRepository.queryNumOfStuType();
    }

    @Override
    public List<JsonModel> queryNumOfStuBirthday() {
        return countRepository.queryNumOfStuBirthday();
    }

    @Override
    public InfoCount queryNumOfStudent() {
        return countRepository.queryNumOfStudent();
    }

    @Override
    public InfoCount queryNumOfTeacher() {
        return countRepository.queryNumOfTeacher();
    }

    @Override
    public InfoCount queryNumOfCourse() {
        return countRepository.queryNumOfCourse();
    }

    @Override
    public InfoCount querySumOfActualTuition() {
        return countRepository.querySumOfActualTuition();
    }

    @Override
    public List<JsonModel> queryNumOfStuEduStart() {
        return countRepository.queryNumOfStuEduStart();
    }
}
