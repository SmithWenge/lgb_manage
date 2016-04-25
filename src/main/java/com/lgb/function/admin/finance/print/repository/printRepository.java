package com.lgb.function.admin.finance.print.repository;

import com.lgb.function.admin.finance.Finance;
import com.lgb.function.support.utils.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class printRepository implements printRepositoryI{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils<Finance> repositoryUtils;

    @Override
    public Finance selectById(int studentCourseId) {
        String sql = "SELECT SC.billNumber,S.stuCardNum,S.stuName,D.departmentName,M.majorName,C.courseName,SC.actualTuition,SC.financeTime FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_department D ON C.departmentId = D.departmentId LEFT JOIN lgb_major M ON C.majorId = M.majorId WHERE SC.tuitionFlag = 1 AND SC.billFlag = 0 AND SC.studentCourseId = ?";
        Object[] args = {
                studentCourseId
        };
        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectByIdRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<Finance> selectPrintAll(Finance finance, Pageable pageable) {
        String sql = "SELECT SC.studentCourseId, S.stuCardNum, S.stuName, SC.signUpComeFrom, D.departmentName, M.majorName, C.courseName, C.courseTuition, SC.courseDiscount, SC.signUpUser, SC.financeUser, SC.financeTime, SC.actualTuition  FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_department D ON C.departmentId = D.departmentId LEFT JOIN lgb_major M ON C.majorId = M.majorId WHERE SC.tuitionFlag = 1 AND S.stuCardNum = ? ORDER BY SC.financeTime DESC";
        Object[] args = {
                finance.getStuCardNum()
        };
        return repositoryUtils.select4Page(sql, pageable, args, new SelectPrintAllRowMapper());

    }

    private class SelectByIdRowMapper implements RowMapper<Finance> {

        @Override
        public Finance mapRow(ResultSet resultSet, int i) throws SQLException {
            Finance finance = new Finance();
            finance.setBillNumber(resultSet.getString("billNumber"));
            finance.setStuCardNum(resultSet.getString("stuCardNum"));
            finance.setStuName(resultSet.getString("stuName"));
            finance.setDepartmentName(resultSet.getString("departmentName"));
            finance.setMajorName(resultSet.getString("majorName"));
            finance.setCourseName(resultSet.getString("courseName"));
            finance.setActualTuition(resultSet.getInt("actualTuition"));
            finance.setFinanceTime(resultSet.getTimestamp("financeTime"));
            return finance;
        }
    }

    private class SelectPrintAllRowMapper implements RowMapper<Finance> {

        @Override
        public Finance mapRow(ResultSet rs, int rowNum) throws SQLException {
            Finance finance = new Finance();

            finance.setStudentCourseId(rs.getInt("studentCourseId"));
            finance.setCardNum(rs.getString("stuCardNum"));
            finance.setStuName(rs.getString("stuName"));
            finance.setSignUpComeFrom(rs.getInt("signUpComeFrom"));
            finance.setDepartmentName(rs.getString("departmentName"));
            finance.setMajorName(rs.getString("majorName"));
            finance.setCourseName(rs.getString("courseName"));
            finance.setCourseTuition(rs.getInt("courseTuition"));
            finance.setCourseDiscount(rs.getInt("courseDiscount"));
            finance.setFinanceUser(rs.getString("financeUser"));
            finance.setFinanceTime(rs.getTimestamp("financeTime"));
            finance.setActualTuition(rs.getInt("actualTuition"));

            return finance;
        }
    }
}
