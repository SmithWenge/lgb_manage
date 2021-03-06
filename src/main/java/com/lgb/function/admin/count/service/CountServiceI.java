package com.lgb.function.admin.count.service;

import com.lgb.function.admin.count.model.*;

import java.util.List;

public interface CountServiceI {

    List<JsonModel> queryNumOfGender();
    List<JsonModel> queryNumOfEducational();
    List<JsonModel> queryNumOfOldPlaceType();
    List<JsonModel> queryNumOfOldWorkType();
    List<JsonModel> queryNumOfStuPolitical();
    List<JsonModel> queryNumOfStuPreferential();
    List<JsonModel> queryNumOfStuType();
    List<JsonModel> queryNumOfStuBirthday();
    List<JsonModel> queryNumOfStuEduStart();
    InfoCount queryNumOfStudent();
    InfoCount queryNumOfTeacher();
    InfoCount queryNumOfCourse();
    List<JsonModel> queryNumOfStuLevel();
}
