package com.lgb.function.admin.finance.print.repository;

import com.lgb.function.admin.finance.Finance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class printRepository implements printRepositoryI{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public Finance selectById(int studentCourseId) {
        String sql = "SELECT SC.billNumber,SC.studentCourseId,S.stuName,D.departmentName,M.majorName,C.courseName,SC.actualTuition,SC.financeTime FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_department D ON C.departmentId = D.departmentId LEFT JOIN lgb_major M ON C.majorId = M.majorId WHERE SC.tuitionFlag = 1 AND SC.billFlag = 0 AND SC.studentCourseId = ?";
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

    private class SelectByIdRowMapper implements RowMapper<Finance> {

        @Override
        public Finance mapRow(ResultSet resultSet, int i) throws SQLException {
            Finance finance = new Finance();
            finance.setBillNumber(resultSet.getString("billNumber"));
            finance.setStudentCourseId(resultSet.getInt("studentCourseId"));
            finance.setStuName(resultSet.getString("stuName"));
            finance.setDepartmentName(resultSet.getString("departmentName"));
            finance.setMajorName(resultSet.getString("majorName"));
            finance.setCourseName(resultSet.getString("courseName"));
            finance.setActualTuition(resultSet.getInt("actualTuition"));
            finance.setFinanceTime(resultSet.getTimestamp("financeTime"));
            return finance;
        }
    }
}
