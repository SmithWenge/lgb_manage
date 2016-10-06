package com.lgb.function.admin.finance.refund.repository;

import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.refund.RefundStudentCourse;
import com.lgb.function.admin.setting.LGBConfig;

import java.util.List;

public interface FinanceRefundRepositoryI {
    List<Finance> select4StudentFinance(String stuCardNum);
    Finance select4RefundInfo(int studentCourseId);
    String selectStudentCardNumByStuCourseId(int studentCourseId);
    boolean insertRefund(RefundStudentCourse refundStudentCourse);
    boolean updateStudentCourse(RefundStudentCourse refundStudentCourse);
    List<RefundStudentCourse> select4OldRefund();
    LGBConfig select4NowConfig();
}
