package com.lgb.function.admin.count.detail.repository;

import com.lgb.function.admin.student.StudentUser;

import java.util.List;

public interface CountDetailRepositoryI {
    List<StudentUser> selectStuYearStart(String year);
    List<StudentUser> selectStuGender(int value);
    List<StudentUser> selectStuEducational(int value);
    List<StudentUser> selectStuOldWorkPlaceType(int value);
    List<StudentUser> selectStuOldWorkType(int value);
    List<StudentUser> selectStuPolitical(int value);
    List<StudentUser> selectStuPreferential(int value);
    List<StudentUser> selectStuType(int value);
    List<StudentUser> selectStuBirthday(String value);
}
