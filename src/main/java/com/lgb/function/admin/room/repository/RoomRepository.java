package com.lgb.function.admin.room.repository;

import com.lgb.arc.utils.CommonUtils;
import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.course.time.CourseTime;
import com.lgb.function.admin.room.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomRepository implements RoomRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Room> selectAll() {
        String sql = "SELECT DISTINCT courseRoom FROM lgb_courseTime WHERE courseId IN (SELECT courseId FROM lgb_course WHERE deleteFlag = 0)";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new SelectAllRowMapper());
        } catch (Exception e) {
            return new ArrayList<Room>();
        }
    }

    private class SelectAllRowMapper implements RowMapper<Room> {

        @Override
        public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
            Room room = new Room();

            room.setRoomId(rs.getInt("courseRoom"));

            return room;
        }
    }

    public List<Room> selectAllTime() {
        String sql = "SELECT courseId, courseRoom, timeWeek, timeSpecific FROM lgb_courseTime WHERE courseRoom IN (SELECT DISTINCT courseRoom FROM lgb_courseTime WHERE courseId IN (SELECT courseId FROM lgb_course WHERE deleteFlag = 0))";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new SelectAllTimeRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Room>();
        }
    }

    private class SelectAllTimeRowMapper implements RowMapper<Room> {

        @Override
        public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
            Room room = new Room();

            room.setCourseId(rs.getInt("courseId"));
            room.setRoomId(rs.getInt("courseRoom"));
            room.setTimeSpecific(rs.getString("timeSpecific"));
            room.setTimeWeek(rs.getInt("timeWeek"));

            return room;
        }
    }

    @Override
    public List<Course> selectRoomCourse(Room room) {
        String sql = "SELECT D.departmentName, M.majorName, C.courseNum, C.courseName, C.courseTuition, C.courseYears, C.courseEnrollmentNum, C.courseGraLimitNum, C.courseId, C.courseSumFlag, C.teacherOneName, C.teacherTwoName, AU.adminName FROM lgb_course C LEFT JOIN lgb_department D ON C.departmentId = D.departmentId LEFT JOIN lgb_major M ON C.majorId = M.majorId LEFT JOIN lgb_adminUser AU ON D.adminId = AU.adminId WHERE AU.adminRole = 6 AND C.courseId IN (SELECT courseId FROM lgb_courseTime WHERE timeWeek = ? AND timeSpecific = ? AND courseRoom = ?)";
        Object[] args = {
                room.getTimeWeek(),
                room.getTimeSpecific(),
                room.getRoomId()
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectRoomRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private class SelectRoomRowMapper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();

            course.setDepartmentName(rs.getString("departmentName"));
            course.setMajorName(rs.getString("majorName"));
            course.setCourseNum(rs.getString("courseNum"));
            course.setCourseName(rs.getString("courseName"));
            course.setCourseTuition(rs.getInt("courseTuition"));
            course.setCourseYears(rs.getInt("courseYears"));
            course.setCourseEnrollmentNum(rs.getInt("courseEnrollmentNum"));
            course.setCourseGraLimitNum(rs.getInt("courseGraLimitNum"));
            course.setCourseId(rs.getInt("courseId"));
            course.setCourseSumFlag(rs.getInt("courseSumFlag"));
            course.setTeacherOneName(rs.getString("teacherOneName"));
            course.setTeacherTwoName(rs.getString("teacherTwoName"));
            course.setAdminName(rs.getString("adminName"));

            int courseId = course.getCourseId();
            course.setTimes(selectTime(courseId));
            course.setCourseStuNum(courseStuNum(courseId));

            return course;
        }
    }

    private List<CourseTime> selectTime(int courseId) {
        String sql = "SELECT timeWeek, timeSpecific, courseRoom FROM lgb_courseTime WHERE courseId = ?";
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
            time.setCourseRoom(rs.getInt("courseRoom"));

            return time;
        }
    }

    private int courseStuNum(int courseId) {
        String sql = "SELECT COUNT(studentCourseId) AS NUM FROM lgb_studentCourse WHERE courseId = ?";
        Object[] args = {
                courseId
        };

        return jdbcTemplate.queryForObject(sql, args, Integer.class);
    }
}
