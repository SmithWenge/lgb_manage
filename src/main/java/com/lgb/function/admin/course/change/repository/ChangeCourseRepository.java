package com.lgb.function.admin.course.change.repository;

import com.lgb.function.admin.course.change.ChangeCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ChangeCourseRepository implements ChangeCourseRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查看这个学院的所有正在上课的已缴费课程
     *
     * @param changeCourse
     * @return
     */
    @Override
    public List<ChangeCourse> select4CouldChangeCourse(ChangeCourse changeCourse) {
        String sql = "SELECT SC.studentCourseId, SC.actualTuition, SC.tuitionFlag, SC.billFlag, SC.signUpTime, C.courseName, S.stuCardNum, S.stuName, S.stuId, C.courseId FROM lgb_studentcourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId WHERE S.stuCardNum = ? AND SC.tuitionFlag = 1 AND SC.deleteFlag = 0 AND C.deleteFlag = 0";
        Object[] args = {
                changeCourse.getStuCardNum()
        };

        try {
            return jdbcTemplate.query(sql, args, new Select4CouldChangeCourseRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private class Select4CouldChangeCourseRowMapper implements RowMapper<ChangeCourse> {

        @Override
        public ChangeCourse mapRow(ResultSet resultSet, int i) throws SQLException {
            ChangeCourse changeCourse = new ChangeCourse();

            changeCourse.setActualTuition(resultSet.getInt("actualTuition"));
            changeCourse.setTuitionFlag(resultSet.getInt("tuitionFlag"));
            changeCourse.setBillFlag(resultSet.getInt("billFlag"));
            changeCourse.setSignUpTime(resultSet.getTimestamp("signUpTime"));
            changeCourse.setCourseName(resultSet.getString("courseName"));
            changeCourse.setStuCardNum(resultSet.getString("stuCardNum"));
            changeCourse.setStuName(resultSet.getString("stuName"));
            changeCourse.setStudentId(resultSet.getInt("stuId"));
            changeCourse.setCourseId(resultSet.getInt("courseId"));
            changeCourse.setStudentCourseId(resultSet.getInt("studentCourseId"));

            return changeCourse;
        }
    }

    /**
     * 查看已经更换的课程
     */
    public List<ChangeCourse> select4HasChangedCourse(String stuCardNum) {
        String sql = "SELECT CC.studentId, CC.courseId, S.stuName, s.stuCardNum, C.courseName, CC.changeTime, CC.oldCourseId FROM lgb_changecourse CC LEFT JOIN lgb_student S ON CC.studentId = S.stuId LEFT JOIN lgb_course C ON CC.courseId = C.courseId WHERE S.stuCardNum = ?";
        Object[] args = {
                stuCardNum
        };

        try {
            return jdbcTemplate.query(sql, args, new Select4HasChangedCourseRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private class Select4HasChangedCourseRowMapper implements RowMapper<ChangeCourse> {

        @Override
        public ChangeCourse mapRow(ResultSet resultSet, int i) throws SQLException {
            ChangeCourse changeCourse = new ChangeCourse();

            changeCourse.setStudentId(resultSet.getInt("studentId"));
            changeCourse.setCourseId(resultSet.getInt("courseId"));
            changeCourse.setStuName(resultSet.getString("stuName"));
            changeCourse.setStuCardNum(resultSet.getString("stuCardNum"));
            changeCourse.setCourseName(resultSet.getString("courseName"));
            changeCourse.setChangeTime(resultSet.getTimestamp("changeTime"));
            changeCourse.setOldCourseId(resultSet.getInt("oldCourseId"));

            return changeCourse;
        }
    }

    /**
     * 查询更换课程之前的课程名
     *
     * @param oldCourseId
     * @return
     */
    @Override
    public String select4OldCourse(int oldCourseId) {
        String sql = "SELECT courseName FROM lgb_course WHERE courseId = ?";
        Object[] args = {
                oldCourseId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, String.class);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 查看要更换课程的详细信息
     *
     * @param studentCourseId
     * @return
     */
    @Override
    public ChangeCourse select4TurnCourse(int studentCourseId) {
        String sql = "SELECT SC.actualTuition, C.courseName, C.courseId, C.courseYears, C.courseRoom, C.teacherOneName, C.teacherTwoName, M.majorName, D.departmentName, C.courseLimitNum, C.courseGraLimitNum, C.courseEnrollmentNum, C.courseStuNum, C.courseTuition FROM lgb_studentcourse SC LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_department D ON C.departmentId = D.departmentId LEFT JOIN lgb_major M ON C.majorId = M.majorId LEFT JOIN lgb_coursetime CT ON C.courseId = CT.courseId WHERE C.deleteFlag = 0 AND SC.studentCourseId = ?";
        Object[] args = {
                studentCourseId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new Select4TurnCourseRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    private class Select4TurnCourseRowMapper implements RowMapper<ChangeCourse> {

        @Override
        public ChangeCourse mapRow(ResultSet resultSet, int i) throws SQLException {
            ChangeCourse changeCourse = new ChangeCourse();

            changeCourse.setCourseName(resultSet.getString("courseName"));
            changeCourse.setCourseYears(resultSet.getInt("courseYears"));
            changeCourse.setCourseRoom(resultSet.getInt("courseRoom"));
            changeCourse.setTeacherOneName(resultSet.getString("teacherOneName"));
            changeCourse.setTeacherTwoName(resultSet.getString("teacherTwoName"));
            changeCourse.setMajorName(resultSet.getString("majorName"));
            changeCourse.setDepartmentName(resultSet.getString("departmentName"));
            changeCourse.setCourseLimitNum(resultSet.getInt("courseLimitNum"));
            changeCourse.setCourseGraLimitNum(resultSet.getInt("courseGraLimitNum"));
            changeCourse.setCourseEnrollmentNum(resultSet.getInt("courseEnrollmentNum"));
            changeCourse.setCourseStuNum(resultSet.getInt("courseStuNum"));
            changeCourse.setCourseTuition(resultSet.getInt("courseTuition"));
            changeCourse.setCourseId(resultSet.getInt("courseId"));
            changeCourse.setActualTuition(resultSet.getInt("actualTuition"));

            return changeCourse;
        }
    }

    /**
     * 查询学生Id
     *
     * @param studentCourseId
     * @return
     */
    @Override
    public int selectStudentId(int studentCourseId) {
        String sql = "SELECT studentId FROM lgb_studentcourse WHERE studentCourseId = ?";
        Object[] args = {
                studentCourseId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, Integer.class);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 查看这个更改课程学员的基本信息
     *
     * @param studentCourseId
     * @return
     */
    @Override
    public ChangeCourse select4StudentInfo(int studentCourseId) {
        String sql = "SELECT SC.studentId, S.stuCardNum FROM lgb_studentcourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId WHERE SC.studentCourseId = ?";
        Object[] args = {
                studentCourseId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new Select4StudentInfoRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    private class Select4StudentInfoRowMapper implements RowMapper<ChangeCourse> {

        @Override
        public ChangeCourse mapRow(ResultSet resultSet, int i) throws SQLException {
            ChangeCourse changeCourse = new ChangeCourse();

            changeCourse.setStudentId(resultSet.getInt("studentId"));
            changeCourse.setStuCardNum(resultSet.getString("stuCardNum"));

            return changeCourse;
        }
    }

    /**
     * 查看这个学员的可以选择的其他课程
     *
     * @param studentId
     * @return
     */
    @Override
    public List<ChangeCourse> select4OtherCourses(int studentId) {
        String sql = "SELECT courseName, courseId FROM lgb_course WHERE deleteFlag = 0 AND courseId NOT IN (SELECT courseId FROM lgb_studentcourse WHERE deleteFlag = 0 OR deleteFlag = 2 AND studentId = ?)";
        Object[] args = {
                studentId
        };

        try {
            return jdbcTemplate.query(sql, args, new Select4OtherCoursesRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private class Select4OtherCoursesRowMapper implements RowMapper<ChangeCourse> {

        @Override
        public ChangeCourse mapRow(ResultSet resultSet, int i) throws SQLException {
            ChangeCourse changeCourse = new ChangeCourse();

            changeCourse.setCourseId(resultSet.getInt("courseId"));
            changeCourse.setCourseName(resultSet.getString("courseName"));

            return changeCourse;
        }
    }
}