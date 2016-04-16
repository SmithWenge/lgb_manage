package com.lgb.function.support.excel.service;

import com.lgb.function.admin.student.StudentUser;

import java.util.List;

public interface StudentExcelImportServiceI {
    boolean save(List<StudentUser> users);
}
