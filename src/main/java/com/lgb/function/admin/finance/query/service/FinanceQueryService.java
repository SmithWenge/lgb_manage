package com.lgb.function.admin.finance.query.service;

import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.query.repository.FinanceQueryRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceQueryService implements FinanceQueryServiceI {
    @Autowired
    private FinanceQueryRepositoryI financeQueryRepository;

    @Override
    public List<Finance> allPayFinance(Course course) {
        return financeQueryRepository.select4AllPayFinance(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return financeQueryRepository.selectUndeleteCourses();
    }
}
