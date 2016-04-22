package com.lgb.function.admin.count.detail.service;

import com.lgb.function.admin.count.detail.repository.CountDetailRepositoryI;
import com.lgb.function.admin.student.StudentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CountDetailService implements CountDetailServiceI {
    @Autowired
    private CountDetailRepositoryI detailRepository;

    @Override
    public List<StudentUser> yearStuEduStart(String year) {
        return detailRepository.selectStuYearStart(year);
    }
}
