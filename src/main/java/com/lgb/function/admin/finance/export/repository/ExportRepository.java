package com.lgb.function.admin.finance.export.repository;

import com.google.common.base.Optional;
import com.lgb.function.admin.finance.Finance;
import com.lgb.function.support.utils.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExportRepository implements ExportRepositoryI{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Finance> exportFinance(Finance finance) {
        StringBuilder sql = new StringBuilder("SELECT SC.studentCourseId, S.stuCardNum, S.stuName, SC.signUpComeFrom, D.departmentName, M.majorName, C.courseName, C.courseTuition, SC.courseDiscount, SC.signUpUser, SC.financeUser, SC.financeTime, SC.actualTuition  FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_department D ON C.departmentId = D.departmentId LEFT JOIN lgb_major M ON C.majorId = M.majorId WHERE SC.tuitionFlag = 1");

        List<Object> list = new ArrayList<>();
        if (finance.getExcelMonth().equals("")) {
            sql.append(" AND YEAR(SC.financeTime) = ?");
            list.add(finance.getExcelYear());
        } else {
            sql.append(" AND YEAR(SC.financeTime) = ? AND MONTH(SC.financeTime) = ?");
            list.add(finance.getExcelYear());
            list.add(finance.getExcelMonth());
        }

        Object[] args = list.toArray(

        );
        sql.append(" ORDER BY SC.financeTime DESC");
        return jdbcTemplate.query(sql.toString(),args,new ExportRowMapper());
    }

    private class ExportRowMapper implements RowMapper<Finance> {

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
