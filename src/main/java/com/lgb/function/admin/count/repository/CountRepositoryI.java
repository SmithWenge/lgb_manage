package com.lgb.function.admin.count.repository;

import com.lgb.function.admin.count.model.*;

import java.util.List;

public interface CountRepositoryI {
    List<JsonModel> queryNumOfGender();
    List<JsonModel> queryNumOfEducational();
    List<JsonModel> queryNumOfOldPlaceType();
    List<JsonModel> queryNumOfOldWorkType();
    List<JsonModel> queryNumOfStuPolitical();
    List<JsonModel> queryNumOfStuPreferential();
    List<JsonModel> queryNumOfStuType();
    List<JsonModel> queryNumOfStuBirthday();
    InfoCount queryNumOfStudent();
    InfoCount queryNumOfTeacher();
    InfoCount queryNumOfCourse();
    List<JsonModel> queryNumOfStuEduStart();
}
