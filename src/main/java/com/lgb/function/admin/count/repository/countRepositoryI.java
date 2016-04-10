package com.lgb.function.admin.count.repository;

import com.lgb.function.admin.count.model.*;

import java.util.List;

public interface CountRepositoryI {
    List<StuGender> queryNumOfGender();
    List<StuEducational> queryNumOfEducational();
    List<StuOldWorkPlaceType> queryNumOfOldPlaceType();
    List<StuOldWorkType> queryNumOfOldWorkType();
    List<StuPolitical> queryNumOfStuPolitical();
    List<StuPreferential> queryNumOfStuPreferential();
    List<StuType> queryNumOfStuType();
    List<YearStuBirthday> queryNumOfStuBirthday();
    InfoCount queryNumOfStudent();
    InfoCount queryNumOfTeacher();
    InfoCount queryNumOfCourse();
    InfoCount querySumOfActualTuition();
    List<YearStuEduStart> queryNumOfStuEduStart();
}
