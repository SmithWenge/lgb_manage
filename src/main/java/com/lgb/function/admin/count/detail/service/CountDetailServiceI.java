package com.lgb.function.admin.count.detail.service;

import com.lgb.function.admin.student.StudentUser;

import java.util.List;

public interface CountDetailServiceI {
    List<StudentUser> yearStuEduStart(String value);
    List<StudentUser> stuGender(String value);
    List<StudentUser> stuEducational(String value);
    List<StudentUser> stuOldWorkPlaceType(String value);
    List<StudentUser> stuOldWorkType(String value);
    List<StudentUser> stuPolitical(String value);
    List<StudentUser> stuPreferential(String value);
    List<StudentUser> stuType(String value);
    List<StudentUser> yearStuBirthday(String value);
    List<StudentUser> stuLevel(String key);
}
