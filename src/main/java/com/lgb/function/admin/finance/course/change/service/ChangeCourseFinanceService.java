package com.lgb.function.admin.finance.course.change.service;

import com.google.common.base.Optional;
import com.lgb.function.admin.course.change.ChangeCourse;
import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.course.change.repository.ChangeCourseFinanceRepositoryI;
import com.lgb.function.support.log.LogContent;
import com.lgb.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChangeCourseFinanceService implements ChangeCourseFinanceServiceI {
    @Autowired
    private ChangeCourseFinanceRepositoryI changeCourseFinanceRepository;
    @Autowired
    private LogRepositoryI logRepository;

    @Override
    public List<ChangeCourse> changeCourseFinances() {
        return changeCourseFinanceRepository.select4ChangeCourseFinances();
    }

    @Override
    public boolean tuition(int changeCourseId, String logUser) {
        if (changeCourseFinanceRepository.update4Tuition(changeCourseId)) {
            LogContent logContent = new LogContent(logUser, "换课缴费换课Id为" + changeCourseId, 1, 4);
            logRepository.insertLog(logContent);

            return true;
        }

        return false;
    }
}
