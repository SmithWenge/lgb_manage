package com.lgb.function.admin.finance.service;

import com.google.common.base.Optional;
import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.repository.FinanceRepositoryI;
import com.lgb.function.admin.major.Major;
import com.lgb.function.support.log.LogContent;
import com.lgb.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FinanceService implements FinanceServiceI {
    @Autowired
    private FinanceRepositoryI financeRepository;
    @Autowired
    private LogRepositoryI logRepository;

    @Override
    public Page<Finance> selectUnFinance4Page(Finance finance, Pageable pageable) {
        return financeRepository.selectUnFinance4Page(finance, pageable);
    }

    @Override
    public List<Department> departments() {
        return financeRepository.selectDepartments();
    }

    @Override
    public List<Major> majors(int departmentId) {
        return financeRepository.selectMajors(departmentId);
    }

    @Override
    public List<Course> courses(int majorId) {
        return financeRepository.selectCourses(majorId);
    }

    @Override
    public Finance select(int studentCourseId) {
        return financeRepository.select(studentCourseId);
    }

    @Override
    public List<Finance> selectFinanceCourse(int studentCourseId) {
        return financeRepository.selectFinanceCourse(studentCourseId);
    }

    @Override
    public boolean edit(Finance finance, String logUser) {
        Finance existObj = financeRepository.selectById(finance.getStudentCourseId());

        Optional<Finance> optional = Optional.fromNullable(existObj);

        if (optional.isPresent()) {
            boolean tmp = financeRepository.update(finance, logUser);

            if (tmp) {
                LogContent logContent = new LogContent(logUser, "收费" + finance.getStudentCourseId() + " : " + finance.getStudentCourseId(), 1, 4);
                logRepository.insertLog(logContent);
            }

            return tmp;
        }

        return false;
    }

    @Override
    public Page<Finance> selectFinance4Page(Finance finance, Pageable pageable) {
        return financeRepository.selectFinance4Page(finance, pageable);
    }

    @Override
    public Page<Finance> selectTwoDayFinance4Page(Finance finance, Pageable pageable) {
        return financeRepository.selectTwoDayFinance4Page(finance,pageable);
    }

}
