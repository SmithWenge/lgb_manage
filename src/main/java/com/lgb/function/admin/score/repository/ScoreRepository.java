package com.lgb.function.admin.score.repository;

import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.major.Major;
import com.lgb.function.admin.score.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScoreRepository implements ScoreRepositoryI{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Department> selectDepartments() {
        String sql = "SELECT departmentId, departmentName FROM lgb_department WHERE deleteFlag = 0";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new SelectDepartmentsRowMapper());
        } catch (Exception e) {
            return new ArrayList<Department>();
        }
    }

    private class SelectDepartmentsRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
            Department department = new Department();

            department.setDepartmentId(rs.getInt("departmentId"));
            department.setDepartmentName(rs.getString("departmentName"));

            return department;
        }
    }

    @Override
    public List<Major> selectMajors(int departmentId) {
        String sql = "SELECT majorId, majorName FROM lgb_major WHERE deleteFlag = 0 AND departmentId = ?";
        Object[] args = {
                departmentId
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectMajorsRowMapper());
        } catch (Exception e) {
            return new ArrayList<Major>();
        }
    }

    @Override
    public List<Score> select4Page(Score score) {
        StringBuilder sql = new StringBuilder("SELECT S.stuCardNum, S.stuName, C.courseName, SC.stuScore ,SC.studentCourseId FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId WHERE C.deleteFlag = 0");

        List<Object> list = new ArrayList<>();
        if (score.getDepartmentId() > 0) {
            sql.append(" AND C.departmentId = ?");
            list.add(score.getDepartmentId());
        }

        if (score.getMajorId() > 0) {
            sql.append(" AND C.majorId = ?");
            list.add(score.getMajorId());
        }

        if (score.getCourseId() > 0) {
            sql.append(" AND C.courseId = ?");
            list.add(score.getCourseId());
        }

        if(score.getStudentCardNum() != null) {
            sql.append(" AND S.stuCardNum = ?");
            list.add(score.getStudentCardNum());
        }

        Object[] args = list.toArray();

        try {
            return jdbcTemplate.query(sql.toString(), args, new Select4PageRowMapper());
        }catch (Exception e) {
            return new ArrayList<Score>();
        }
    }

    @Override
    public List<Course> selectCourses(int majorId) {
        String sql = "SELECT courseId, courseName FROM lgb_course WHERE deleteFlag = 0 AND majorId = ?";
        Object[] args = {
                majorId
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectCoursesRowMapper());
        } catch (Exception e) {
            return new ArrayList<Course>();
        }
    }

    @Override
    public Score seleciById(int studentCourseId) {
        String sql = "SELECT S.stuCardNum, S.stuName, C.courseName, SC.stuScore, SC.studentCourseId FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId WHERE C.deleteFlag = 0 AND SC.studentCourseId = ?";
        Object[] args = {
                studentCourseId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SeleciByIdRowMapper());
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean update(Score score) {
        String sql = "UPDATE lgb_studentcourse SET stuScore = ? WHERE studentCourseId =? ";
        Object[] args = {
                score.getStuScore(),
                score.getStudentCourseId()
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }

    private class SeleciByIdRowMapper implements  RowMapper<Score> {

        @Override
        public Score mapRow(ResultSet resultSet, int i) throws SQLException {
            Score score = new Score();
            score.setStudentCardNum(resultSet.getString("stuCardNum"));
            score.setStuName(resultSet.getString("stuName"));
            score.setCourseName(resultSet.getString("courseName"));
            score.setStuScore(resultSet.getString("stuScore"));
            score.setStudentCourseId(resultSet.getInt("studentCourseId"));

            return score;
        }
    }

    private class SelectCoursesRowMapper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet resultSet, int i) throws SQLException {
            Course course = new Course();
            course.setCourseName(resultSet.getString("courseName"));
            course.setCourseId(resultSet.getInt("courseId"));

            return course;
        }
    }

    private class Select4PageRowMapper implements RowMapper<Score> {

        @Override
        public Score mapRow(ResultSet resultSet, int i) throws SQLException {
            Score score = new Score();
            score.setStudentCardNum(resultSet.getString("stuCardNum"));
            score.setStuName(resultSet.getString("stuName"));
            score.setCourseName(resultSet.getString("courseName"));
            score.setStuScore(resultSet.getString("stuScore"));
            score.setStudentCourseId(resultSet.getInt("studentCourseId"));

            return score;
        }
    }
    private class SelectMajorsRowMapper implements RowMapper<Major> {

        @Override
        public Major mapRow(ResultSet rs, int rowNum) throws SQLException {
            Major major = new Major();

            major.setMajorId(rs.getInt("majorId"));
            major.setMajorName(rs.getString("majorName"));

            return major;
        }
    }


}
