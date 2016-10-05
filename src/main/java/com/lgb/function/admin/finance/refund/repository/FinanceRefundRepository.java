package com.lgb.function.admin.finance.refund.repository;

import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.refund.RefundStudentCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FinanceRefundRepository implements FinanceRefundRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 通过学员号查询所有的选择课程
     *
     * @param stuCardNum
     * @return
     */
    @Override
    public List<Finance> select4StudentFinance(String stuCardNum) {
        String sql = "SELECT SC.studentCourseId, SC.tuitionFlag, SC.studentId, SC.financeTime, SC.financeUser, SC.actualTuition, C.courseName, C.courseId, SC.billFlag, SC.billNumber, S.stuName, C.courseTuition FROM lgb_studentCourse SC LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_student S ON SC.studentId = S.stuId WHERE SC.tuitionFlag = 1 AND C.deleteFlag = 0 AND SC.deleteFlag = 0 AND S.deleteFlag = 0 AND S.stuCardNum = ?";
        Object[] args = {
                stuCardNum
        };

        try {
            return jdbcTemplate.query(sql, args, new Select4StudentFinanceRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private class Select4StudentFinanceRowMapper implements RowMapper<Finance> {

        @Override
        public Finance mapRow(ResultSet rs, int rowNum) throws SQLException {
            Finance finance = new Finance();

            finance.setStudentCourseId(rs.getInt("studentCourseId"));
            finance.setTuitionFlag(rs.getInt("tuitionFlag"));
            finance.setFinanceTime(rs.getTimestamp("financeTime"));
            finance.setFinanceUser(rs.getString("financeUser"));
            finance.setActualTuition(rs.getInt("actualTuition"));
            finance.setCourseName(rs.getString("courseName"));
            finance.setCourseId(rs.getInt("courseId"));
            finance.setBillFlag(rs.getInt("billFlag"));
            finance.setBillNumber(rs.getString("billNumber"));
            finance.setTuitionFlag(rs.getInt("tuitionFlag"));
            finance.setStuName(rs.getString("stuName"));
            finance.setCourseTuition(rs.getInt("courseTuition"));
            finance.setStudentId(rs.getInt("studentId"));

            return finance;
        }
    }

    /**
     * 查询这门课程的退课情况
     *
     * @param studentCourseId
     * @return
     */
    @Override
    public Finance select4RefundInfo(int studentCourseId) {
        String sql = "SELECT SC.studentCourseId, SC.tuitionFlag, SC.financeTime, SC.studentId, SC.financeUser, SC.actualTuition, C.courseName, C.courseId, SC.billFlag, SC.billNumber, S.stuName, C.courseTuition FROM lgb_studentCourse SC LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_student S ON SC.studentId = S.stuId WHERE SC.tuitionFlag = 1 AND C.deleteFlag = 0 AND SC.deleteFlag = 0 AND S.deleteFlag = 0 AND SC.studentCourseId = ?";
        Object[] args = {
                studentCourseId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new Select4StudentFinanceRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 通过选择课程的ID查询学生的学员号
     *
     * @param studentCourseId
     * @return
     */
    @Override
    public String selectStudentCardNumByStuCourseId(int studentCourseId) {
        String sql = "SELECT S.stuCardNum FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId WHERE SC.deleteFlag != 1 AND SC.studentCourseId = ?";
        Object[] args = {
                studentCourseId
        };

        return jdbcTemplate.queryForObject(sql, args, String.class);
    }

    /**
     * 添加新的退款纪录
     *
     * @param refundStudentCourse
     * @return
     */
    @Override
    public boolean insertRefund(RefundStudentCourse refundStudentCourse) {
        String sql = "INSERT INTO lgb_refundStudentCourse (studentCourseId, refundMoney, studentId, courseId, refundTuitionFlag, refundUser) VALUES (?, ?, ?, ?, ?, ?)";
        Object[] args = {
                refundStudentCourse.getStudentCourseId(),
                refundStudentCourse.getRefundMoney(),
                refundStudentCourse.getStudentId(),
                refundStudentCourse.getCourseId(),
                refundStudentCourse.getRefundTuitionFlag(),
                refundStudentCourse.getRefundUser()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 更新选课的状态为退课状态
     *
     * @param refundStudentCourse
     * @return
     */
    @Override
    public boolean updateStudentCourse(RefundStudentCourse refundStudentCourse) {
        String sql = "UPDATE lgb_studentCourse SET deleteFlag = 2 WHERE studentCourseId = ? AND deleteFlag = 0";
        Object[] args = {
                refundStudentCourse.getStudentCourseId()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 查看所有退课情况
     *
     * @return
     */
    @Override
    public List<RefundStudentCourse> select4OldRefund() {
        String sql = "SELECT RSC.studentId, RSC.courseId, RSC.refundMoney, RSC.refundTuitionFlag, RSC.refundTime, RSC.refundUser, S.stuName, C.courseName FROM lgb_refundStudentCourse RSC LEFT JOIN lgb_student S ON RSC.studentId = S.stuId LEFT JOIN lgb_course C ON RSC.courseId = C.courseId";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new Select4OldRefundRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private class Select4OldRefundRowMapper implements RowMapper<RefundStudentCourse> {

        @Override
        public RefundStudentCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
            RefundStudentCourse course = new RefundStudentCourse();

            course.setStudentId(rs.getInt("studentId"));
            course.setCourseId(rs.getInt("courseId"));
            course.setRefundMoney(rs.getInt("refundMoney"));
            course.setRefundTuitionFlag(rs.getInt("refundTuitionFlag"));
            course.setRefundTime(rs.getTimestamp("refundTime"));
            course.setRefundUser(rs.getString("refundUser"));
            course.setStudentName(rs.getString("stuName"));
            course.setCourseName(rs.getString("courseName"));

            return course;
        }
    }
}
