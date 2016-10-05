package com.lgb.function.admin.finance.refund.service;

import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.refund.RefundStudentCourse;

import java.util.List;

public interface FinanceRefundServiceI {
    List<Finance> getStudentFinance(String stuCardNum);
    Finance queryRefundInfo(int studentCourseId);
    String getStudentCardNum(int studentCourseId);
    boolean updateRefund(RefundStudentCourse refundStudentCourse);
    List<RefundStudentCourse> queryOldRefund();
}
