package com.lgb.function.admin.course.change.repository;

import com.lgb.arc.utils.CommonUtils;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.course.change.ChangeCourse;
import com.lgb.function.admin.course.time.CourseTime;
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
        String sql = "SELECT SC.actualTuition, C.courseName, C.courseId, C.courseYears, C.courseRoom, C.teacherOneName, C.teacherTwoName, M.majorName, D.departmentName, C.courseLimitNum, C.courseGraLimitNum, C.courseEnrollmentNum, C.courseStuNum, C.courseTuition FROM lgb_studentcourse SC LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_department D ON C.departmentId = D.departmentId LEFT JOIN lgb_major M ON C.majorId = M.majorId WHERE C.deleteFlag = 0 AND SC.studentCourseId = ?";
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
        String sql = "SELECT SC.studentId, S.stuCardNum, S.stuType, S.stuName FROM lgb_studentcourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId WHERE SC.studentCourseId = ?";
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
            changeCourse.setStuType(resultSet.getInt("stuType"));
            changeCourse.setStuName(resultSet.getString("stuName"));

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
        String sql = "SELECT courseName, courseId FROM lgb_course WHERE deleteFlag = 0 AND courseId NOT IN (SELECT courseId FROM lgb_studentcourse WHERE deleteFlag != 1 AND studentId = ?)";
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

    /**
     * 添加新的换课记录
     *
     * @param changeCourse
     * @return
     */
    @Override
    public boolean insertNewChangeCourseRecord(ChangeCourse changeCourse) {
        String sql = "INSERT INTO lgb_changecourse (courseId, studentId, operationUser, financeFlag, oldCourseActualTuition, oldCourseId, studentCourseId, finance) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {
                changeCourse.getCourseId(),
                changeCourse.getStudentId(),
                changeCourse.getOperationUser(),
                changeCourse.getFinanceFlag(),
                changeCourse.getFinanceFlag(),
                changeCourse.getOldCourseActualTuition(),
                changeCourse.getOldCourseId(),
                changeCourse.getStudentCourseId(),
                changeCourse.getFinance()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 更新学生的选课状态的换课
     *
     * @param changeCourse
     */
    @Override
    public void updateStudentCourse(ChangeCourse changeCourse) {
        String sql = "UPDATE lgb_studentcourse SET deleteFlag = 2, courseId = ? WHERE studentCourseId = ?";
        Object[] args = {
                changeCourse.getCourseId(),
                changeCourse.getStudentCourseId()
        };

        try {
            jdbcTemplate.update(sql, args);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * 添加新的学生选课记录
     *
     * @param changeCourse
     */
//    @Override
//    public void insertNewStudentCourse(ChangeCourse changeCourse) {
//        String sql = "INSERT INTO lgb_studentcourse (studentId, courseId, courseDiscount, signUpComeForm, signUpUser) VALUES (?, ?, ?, ?, ?)";
//        Object[] args = {
//                changeCourse.getStudentId(),
//                changeCourse.getCourseId(),
//                changeCourse.getStuType(),
//                ConstantFields.SIGN_UP_COME_FROM_TURN_COURSE,
//                changeCourse.getOperationUser()
//        };
//
//        try {
//            jdbcTemplate.update(sql, args);
//        } catch (Exception e) {
//            return;
//        }
//    }

    /**
     * 查询课程上课时间
     *
     * @param courseId
     * @return
     */
    public List<CourseTime> selectTime(int courseId) {
        String sql = "SELECT timeWeek, timeSpecific FROM lgb_courseTime WHERE courseId = ?";
        Object[] args = {
                courseId
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectTimeRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<CourseTime>();
        }
    }

    private class SelectTimeRowMapper implements RowMapper<CourseTime> {

        @Override
        public CourseTime mapRow(ResultSet rs, int rowNum) throws SQLException {
            CourseTime time = new CourseTime();

            time.setTimeWeek(rs.getInt("timeWeek"));
            String specific = rs.getString("timeSpecific");
            time.setTimeSpecificInt(CommonUtils.convertTimeSpecific(specific));

            return time;
        }
    }

    /**
     * 更换课程时候查询新的选中课程的信息
     *
     * @param courseId
     * @return
     */
    @Override
    public ChangeCourse select4NewTurnCourseInfo(int courseId) {
        String sql = "SELECT C.courseName, C.courseId, C.courseYears, C.courseRoom, C.teacherOneName, C.teacherTwoName, M.majorName, D.departmentName, C.courseLimitNum, C.courseGraLimitNum, C.courseEnrollmentNum, C.courseStuNum, C.courseTuition FROM lgb_course C LEFT JOIN lgb_department D ON C.departmentId = D.departmentId LEFT JOIN lgb_major M ON C.majorId = M.majorId WHERE C.deleteFlag = 0 AND C.courseId = ?";
        Object[] args = {
                courseId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new Select4NewTurnCourseInfoRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    private class Select4NewTurnCourseInfoRowMapper implements RowMapper<ChangeCourse> {

        @Override
        public ChangeCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
            ChangeCourse changeCourse = new ChangeCourse();

            changeCourse.setCourseName(rs.getString("courseName"));
            changeCourse.setCourseYears(rs.getInt("courseYears"));
            changeCourse.setCourseRoom(rs.getInt("courseRoom"));
            changeCourse.setTeacherOneName(rs.getString("teacherOneName"));
            changeCourse.setTeacherTwoName(rs.getString("teacherTwoName"));
            changeCourse.setMajorName(rs.getString("majorName"));
            changeCourse.setDepartmentName(rs.getString("departmentName"));
            changeCourse.setCourseLimitNum(rs.getInt("courseLimitNum"));
            changeCourse.setCourseGraLimitNum(rs.getInt("courseGraLimitNum"));
            changeCourse.setCourseEnrollmentNum(rs.getInt("courseEnrollmentNum"));
            changeCourse.setCourseStuNum(rs.getInt("courseStuNum"));
            changeCourse.setCourseTuition(rs.getInt("courseTuition"));
            changeCourse.setCourseId(rs.getInt("courseId"));

            return changeCourse;
        }
    }
}
