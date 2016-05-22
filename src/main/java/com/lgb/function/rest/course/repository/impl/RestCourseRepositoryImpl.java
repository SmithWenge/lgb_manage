package com.lgb.function.rest.course.repository.impl;

import com.lgb.arc.utils.CommonUtils;
import com.lgb.function.support.dictionary.IDictionaryManager;
import com.lgb.function.support.dictionary.impl.DefaultDictionaryManager;
import com.lgb.function.rest.course.RestNowStudentCourseInfo;
import com.lgb.function.rest.course.repository.RestCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RestCourseRepositoryImpl implements RestCourseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<RestNowStudentCourseInfo> selectNowDay(int week, String userCardNum) {
        String sql = "SELECT CT.courseRoom, CT.timeSpecific, CT.timeWeek, CT.courseId, C.courseName, D.departmentName, M.majorName FROM lgb_courseTime CT LEFT JOIN lgb_course C ON CT.courseId = C.courseId LEFT JOIN lgb_department D ON C.departmentId = D.departmentId LEFT JOIN lgb_major M ON M.majorId = C.majorId WHERE timeWeek = ? AND CT.courseId IN (SELECT SC.courseId FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId WHERE C.deleteFlag = 0 AND S.deleteFlag = 0 AND S.stuCardNum = ?)";
        Object[] args = {
                week,
                userCardNum
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectNowDayRowMapper());
        } catch (Exception e) {
            return new ArrayList<RestNowStudentCourseInfo>();
        }
    }

    private class SelectNowDayRowMapper implements RowMapper<RestNowStudentCourseInfo> {
        IDictionaryManager manager = DefaultDictionaryManager.getInstance();

        @Override
        public RestNowStudentCourseInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            RestNowStudentCourseInfo restNowStudentCourseInfo = new RestNowStudentCourseInfo();

            restNowStudentCourseInfo.setCourseName(rs.getString("courseName"));
            restNowStudentCourseInfo.setDepartmentName(rs.getString("departmentName"));
            restNowStudentCourseInfo.setMajorName(rs.getString("majorName"));
            restNowStudentCourseInfo.setCourseId(rs.getInt("courseId"));
            restNowStudentCourseInfo.setCourseTime(manager.dictionary(CommonUtils.convertTimeSpecific(rs.getString("timeSpecific")), "timeSpecific").getItemValue());
            restNowStudentCourseInfo.setCourseRoom(manager.dictionary(rs.getInt("courseRoom"), "courseRoom").getItemValue());

            return restNowStudentCourseInfo;
        }
    }

    public RestNowStudentCourseInfo selectStudent(String studentCardNum) {
        String sql = "SELECT stuName, stuId, stuCardNum FROM lgb_student WHERE deleteFlag = 0 AND stuCardNum = ?";
        Object[] args = {
                studentCardNum
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectRestNowStudentCourseInfoRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    private class SelectRestNowStudentCourseInfoRowMapper implements RowMapper<RestNowStudentCourseInfo> {

        @Override
        public RestNowStudentCourseInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            RestNowStudentCourseInfo info = new RestNowStudentCourseInfo();

            info.setStudentName(rs.getString("stuName"));
            info.setStudentCardNum(rs.getString("stuCardNum"));

            return info;
        }
    }
}
