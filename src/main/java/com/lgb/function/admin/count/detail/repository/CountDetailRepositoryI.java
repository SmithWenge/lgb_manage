package com.lgb.function.admin.count.detail.repository;

import com.lgb.function.admin.student.StudentUser;

import java.util.List;

public interface CountDetailRepositoryI {
    List<StudentUser> selectStuYearStart(String year);
}
