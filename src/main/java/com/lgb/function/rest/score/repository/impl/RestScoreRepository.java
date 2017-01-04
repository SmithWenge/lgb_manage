package com.lgb.function.rest.score.repository.impl;

import com.lgb.function.rest.score.RestStudentScoreInfo;
import com.lgb.function.rest.score.repository.RestScoreRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RestScoreRepository implements RestScoreRepositoryI{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<RestStudentScoreInfo> selectStuScore(String userCardNum) {
        String sql = "SELECT S.stuCardNum, S.stuName, C.courseName, SC.stuScore FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId WHERE C.deleteFlag = 0 AND S.stuCardNum = ? AND SC.stuScore >= 0";
        Object[] args = {
                userCardNum
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectStuScoreRowMapper());
        } catch (Exception e) {
            return new ArrayList<RestStudentScoreInfo>();
        }
    }

    private class SelectStuScoreRowMapper implements RowMapper<RestStudentScoreInfo> {

        @Override
        public RestStudentScoreInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            RestStudentScoreInfo restStudentScoreInfo = new RestStudentScoreInfo();
            restStudentScoreInfo.setStuName(resultSet.getString("stuName"));
            restStudentScoreInfo.setCourseName(resultSet.getString("courseName"));
            restStudentScoreInfo.setStudentCardNum(resultSet.getString("stuCardNum"));
            restStudentScoreInfo.setStuScore(resultSet.getString("stuScore"));

            return restStudentScoreInfo;
        }
    }
}
