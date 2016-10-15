package com.lgb.function.support.student.card.repository;

import com.lgb.arc.utils.CommonUtils;
import com.lgb.function.support.dictionary.IDictionaryManager;
import com.lgb.function.support.dictionary.impl.DefaultDictionaryManager;
import com.lgb.function.support.student.StudentNowCourseInfo;
import com.lgb.function.support.student.card.StudentCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentCardRepository implements StudentCardRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<StudentNowCourseInfo> selectCourses(String studentCardNum, int week) {
        String sql = "SELECT CT.courseRoom, CT.timeSpecific, CT.timeWeek, CT.courseId, C.courseName, D.departmentName, M.majorName FROM lgb_courseTime CT LEFT JOIN lgb_course C ON CT.courseId = C.courseId LEFT JOIN lgb_department D ON C.departmentId = D.departmentId LEFT JOIN lgb_major M ON M.majorId = C.majorId WHERE timeWeek = ? AND CT.courseId IN (SELECT SC.courseId FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId WHERE C.deleteFlag = 0 AND S.deleteFlag = 0 AND S.stuCardNum = ?)";
        Object[] args = {
                week,
                studentCardNum
        };

        try {
            return jdbcTemplate.query(sql, args, new StudentCourseRowMapper());
        } catch (Exception e) {
            return new ArrayList<StudentNowCourseInfo>();
        }
    }

    private class StudentCourseRowMapper implements RowMapper<StudentNowCourseInfo> {
        IDictionaryManager manager = DefaultDictionaryManager.getInstance();

        @Override
        public StudentNowCourseInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            StudentNowCourseInfo studentNowCourseInfo = new StudentNowCourseInfo();

            studentNowCourseInfo.setCourseName(rs.getString("courseName"));
            studentNowCourseInfo.setDepartmentName(rs.getString("departmentName"));
            studentNowCourseInfo.setMajorName(rs.getString("majorName"));
            studentNowCourseInfo.setCourseId(rs.getInt("courseId"));
            studentNowCourseInfo.setCourseTime(manager.dictionary(CommonUtils.convertTimeSpecific(rs.getString("timeSpecific")), "timeSpecific").getItemValue());
            studentNowCourseInfo.setCourseRoom(manager.dictionary(rs.getInt("courseRoom"), "courseRoom").getItemValue());

            return studentNowCourseInfo;
        }
    }

    public StudentNowCourseInfo selectStudent(String studentCardNum) {
        String sql = "SELECT stuName, stuId, stuCardNum FROM lgb_student WHERE deleteFlag = 0 AND stuCardNum = ?";
        Object[] args = {
                studentCardNum
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectStudentRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    private class SelectStudentRowMapper implements RowMapper<StudentNowCourseInfo> {

        @Override
        public StudentNowCourseInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            StudentNowCourseInfo info = new StudentNowCourseInfo();

            info.setStudentName(rs.getString("stuName"));
            info.setStudentCardNum(rs.getString("stuCardNum"));

            return info;
        }
    }

    /**
     * 添加学员考勤纪录
     *
     * @param check
     */
    @Override
    public void addStudentCheck(StudentCheck check) {
        String sql = "INSERT INTO lgb_studentcheck (checkUserName, checkCardNum, checkFlag) VALUES (?, ?, ?)";
        Object[] args = {
                check.getCheckUserName(),
                check.getCheckCardNum(),
                check.getCheckFlag()
        };

        try {
            jdbcTemplate.update(sql, args);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * 查询学员今天是否打卡
     *
     * @param studentCardNum
     * @param year
     * @param month
     * @param day
     * @return
     */
    @Override
    public boolean hasCarded(String studentCardNum, int year, int month, int day) {
        String sql = "SELECT count(checkId) as num FROM lgb_studentcheck WHERE checkCardNum = ? AND YEAR(checkTime) = ? AND MONTH(checkTime) = ? AND DAY(checkTime) = ?";
        Object[] args = {
                studentCardNum,
                year,
                month,
                day
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, Integer.class) >= 1;
        } catch (Exception e) {
            return false;
        }
    }
}