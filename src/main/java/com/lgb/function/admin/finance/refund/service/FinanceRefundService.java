package com.lgb.function.admin.finance.refund.service;

import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.refund.RefundStudentCourse;
import com.lgb.function.admin.finance.refund.repository.FinanceRefundRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceRefundService implements FinanceRefundServiceI {
    @Autowired
    private FinanceRefundRepositoryI financeRefundRepository;

    @Override
    public List<Finance> getStudentFinance(String stuCardNum) {
        return financeRefundRepository.select4StudentFinance(stuCardNum);
    }

    @Override
    public Finance queryRefundInfo(int studentCourseId) {
        return financeRefundRepository.select4RefundInfo(studentCourseId);
    }

    @Override
    public String getStudentCardNum(int studentCourseId) {
        return financeRefundRepository.selectStudentCardNumByStuCourseId(studentCourseId);
    }

    @Override
    public boolean updateRefund(RefundStudentCourse refundStudentCourse) {
        if (financeRefundRepository.insertRefund(refundStudentCourse)) {
            return financeRefundRepository.updateStudentCourse(refundStudentCourse);
        }

        return false;
    }

    @Override
    public List<RefundStudentCourse> queryOldRefund() {
        return financeRefundRepository.select4OldRefund();
    }
}
