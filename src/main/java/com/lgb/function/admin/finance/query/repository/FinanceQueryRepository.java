package com.lgb.function.admin.finance.query.repository;

import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.finance.Finance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FinanceQueryRepository implements FinanceQueryRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Finance> select4AllPayFinance(Course course) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT studentCourseId, SC.studentId, S.stuName, SC.courseId, financeTime, financeUser, billFlag, billNumber, SC.tuitionFlag, C.courseName, SC.actualTuition, C.courseTuition FROM lgb_studentCourse SC LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_student S ON S.stuId = SC.studentId WHERE SC.deleteFlag = 0 AND C.deleteFlag = 0 AND SC.tuitionFlag = 1");

        List<Object> arguments = new ArrayList<>();

        int year = course.getFinanceYear();
        if (year > 0) {
            arguments.add(year);
            sqlBuilder.append(" AND YEAR(SC.financeTime) = ?");
        }

        int courseId = course.getCourseId();
        if (courseId > 0) {
            arguments.add(courseId);
            sqlBuilder.append(" AND SC.courseId = ?");
        }

        int billFlag = course.getBillFlag();

        if (billFlag < 0) {
            arguments.add(billFlag);
            sqlBuilder.append(" AND SC.billFlag = ?");
        }

        try {
            return jdbcTemplate.query(sqlBuilder.toString(), arguments.toArray(), new Select4AllPayFianceRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private class Select4AllPayFianceRowMapper implements RowMapper<Finance> {

        @Override
        public Finance mapRow(ResultSet rs, int rowNum) throws SQLException {
            Finance finance = new Finance();

            finance.setStudentCourseId(rs.getInt("studentCourseId"));
            finance.setStudentId(rs.getInt("studentId"));
            finance.setCourseId(rs.getInt("courseId"));
            finance.setFinanceTime(rs.getTimestamp("financeTime"));
            finance.setFinanceUser(rs.getString("financeUser"));
            finance.setBillFlag(rs.getInt("billFlag"));
            finance.setBillNumber(rs.getString("billNumber"));
            finance.setTuitionFlag(rs.getInt("tuitionFlag"));
            finance.setCourseName(rs.getString("courseName"));
            finance.setStuName(rs.getString("stuName"));
            finance.setActualTuition(rs.getInt("actualTuition"));
            finance.setCourseTuition(rs.getInt("courseTuition"));

            return finance;
        }
    }

    /**
     * 获得所有未删除的课程
     *
     * @return
     */
    @Override
    public List<Course> selectUndeleteCourses() {
        String sql = "SELECT courseId, courseName FROM lgb_course WHERE deleteFlag = 0";

        try {
            return jdbcTemplate.query(sql, new Object[] {}, new SelectUndeleteCoursesRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private class SelectUndeleteCoursesRowMapper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();

            course.setCourseId(rs.getInt("courseId"));
            course.setCourseName(rs.getString("courseName"));

            return course;
        }
    }
}
