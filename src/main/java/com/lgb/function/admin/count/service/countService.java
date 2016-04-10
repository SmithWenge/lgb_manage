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
    public List<StuGender> queryNumOfGender() {
        return countRepository.queryNumOfGender();
    }

    @Override
    public List<StuEducational> queryNumOfEducational() {
        return countRepository.queryNumOfEducational();
    }

    @Override
    public List<StuOldWorkPlaceType> queryNumOfOldPlaceType() {
        return countRepository.queryNumOfOldPlaceType();
    }

    @Override
    public List<StuOldWorkType> queryNumOfOldWorkType() {
        return countRepository.queryNumOfOldWorkType();
    }

    @Override
    public List<StuPolitical> queryNumOfStuPolitical() {
        return countRepository.queryNumOfStuPolitical();
    }

    @Override
    public List<StuPreferential> queryNumOfStuPreferential() {
        return countRepository.queryNumOfStuPreferential();
    }

    @Override
    public List<StuType> queryNumOfStuType() {
        return countRepository.queryNumOfStuType();
    }

    @Override
    public List<YearStuBirthday> queryNumOfStuBirthday() {
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
    public List<YearStuEduStart> queryNumOfStuEduStart() {
        return countRepository.queryNumOfStuEduStart();
    }
}
