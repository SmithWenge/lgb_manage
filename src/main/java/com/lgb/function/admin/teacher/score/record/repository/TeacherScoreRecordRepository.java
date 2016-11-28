package com.lgb.function.admin.teacher.score.record.repository;

import com.lgb.function.admin.teacher.score.record.TeacherScoreRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherScoreRecordRepository implements TeacherScoreRecordRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public TeacherScoreRecord select(TeacherScoreRecord teacherScoreRecord) {
        String sql = "select teacherId from lgb_teacher where teacherName = ? and teacherCardNum = ? and deleteFlag = 0";
        Object[] args = {
                teacherScoreRecord.getTeacherName(),
                teacherScoreRecord.getTeacherCardNum()
        };
        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectRpwMapper());
        }catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<TeacherScoreRecord> selectCourses(int teacherId) {
        String sql = "SELECT DISTINCT TC.courseId, courseName FROM lgb_teachercourse TC LEFT JOIN lgb_course C ON TC.courseId = C.courseId WHERE C.deleteFlag = 0 AND TC.teacherId = ?";
        Object[] args = {
                teacherId
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectCoursesRowMapper());
        } catch (Exception e) {
            return new ArrayList<TeacherScoreRecord>();
        }
    }

    @Override
    public List<TeacherScoreRecord> selectScores(TeacherScoreRecord teacherScoreRecord) {
        StringBuilder sql = new StringBuilder("SELECT S.stuCardNum, S.stuName, C.courseName, SC.stuScore ,SC.studentCourseId FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId WHERE C.deleteFlag = 0");

        List<Object> list = new ArrayList<>();
        if (teacherScoreRecord.getCourseId() > 0) {
            sql.append(" AND C.courseId = ?");
            list.add(teacherScoreRecord.getCourseId());
        }

        if(teacherScoreRecord.getStudentCardNum() != null) {
            sql.append(" AND S.stuCardNum = ?");
            list.add(teacherScoreRecord.getStudentCardNum());
        }

        Object[] args = list.toArray();

        try {
            return jdbcTemplate.query(sql.toString(), args, new Select4PageRowMapper());
        }catch (Exception e) {
            return new ArrayList<TeacherScoreRecord>();
        }
    }

    @Override
    public TeacherScoreRecord seleciById(int studentCourseId) {
        String sql = "SELECT S.stuCardNum, S.stuName, C.courseName, SC.stuScore, SC.studentCourseId FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId WHERE C.deleteFlag = 0 AND SC.studentCourseId = ?";
        Object[] args = {
                studentCourseId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectByIdRowMapper());
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public int update(TeacherScoreRecord score) {
        String sql = "UPDATE lgb_studentcourse SET stuScore = ? WHERE studentCourseId =? ";
        Object[] args = {
                score.getStuScore(),
                score.getStudentCourseId()
        };

        return jdbcTemplate.update(sql, args);
    }

    @Override
    public int selectStudentCourseId(String studentCardNum, String courseName) {
        String sql = "SELECT SC.studentCourseId FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId WHERE C.deleteFlag = 0 AND S.stuCardNum = ? AND C.courseNum = ?";
        Object[] args = {
                studentCardNum,
                courseName
        };
        try{
            return jdbcTemplate.queryForObject(sql, args, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getInt("studentCourseId");
                }
            });
        }catch (Exception e) {
            return 0;
        }

    }

    private class SelectByIdRowMapper implements  RowMapper<TeacherScoreRecord> {

        @Override
        public TeacherScoreRecord mapRow(ResultSet resultSet, int i) throws SQLException {
            TeacherScoreRecord score = new TeacherScoreRecord();
            score.setStudentCardNum(resultSet.getString("stuCardNum"));
            score.setStuName(resultSet.getString("stuName"));
            score.setCourseName(resultSet.getString("courseName"));
            score.setStuScore(resultSet.getString("stuScore"));
            score.setStudentCourseId(resultSet.getInt("studentCourseId"));

            return score;
        }
    }

    private class Select4PageRowMapper implements RowMapper<TeacherScoreRecord> {

        @Override
        public TeacherScoreRecord mapRow(ResultSet resultSet, int i) throws SQLException {
            TeacherScoreRecord score = new TeacherScoreRecord();
            score.setStudentCardNum(resultSet.getString("stuCardNum"));
            score.setStuName(resultSet.getString("stuName"));
            score.setCourseName(resultSet.getString("courseName"));
            score.setStuScore(resultSet.getString("stuScore"));
            score.setStudentCourseId(resultSet.getInt("studentCourseId"));

            return score;
        }
    }

    private class SelectCoursesRowMapper implements RowMapper<TeacherScoreRecord> {

        @Override
        public TeacherScoreRecord mapRow(ResultSet resultSet, int i) throws SQLException {
            TeacherScoreRecord teacherScoreRecord = new TeacherScoreRecord();
            teacherScoreRecord.setCourseId(resultSet.getInt("courseId"));
            teacherScoreRecord.setCourseName(resultSet.getString("courseName"));
            return teacherScoreRecord;
        }
    }

    private class SelectRpwMapper implements RowMapper<TeacherScoreRecord> {

        @Override
        public TeacherScoreRecord mapRow(ResultSet resultSet, int i) throws SQLException {
            TeacherScoreRecord teacherScoreRecord = new TeacherScoreRecord();
            teacherScoreRecord.setTeacherId(resultSet.getInt("teacherId"));

            return teacherScoreRecord;
        }
    }
}
