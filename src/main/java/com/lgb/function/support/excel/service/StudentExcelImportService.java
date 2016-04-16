package com.lgb.function.support.excel.service;

import com.lgb.function.admin.student.StudentUser;
import com.lgb.function.support.excel.repository.StudentExcelImportRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentExcelImportService implements StudentExcelImportServiceI {

    @Autowired
    private StudentExcelImportRepositoryI studentExcelImportRepository;

    @Override
    public boolean save(List<StudentUser> users) {
        int sum = 0;

        for (StudentUser user : users) {
            sum += studentExcelImportRepository.insert(user);
        }

        return users.size() == sum;
    }
}
