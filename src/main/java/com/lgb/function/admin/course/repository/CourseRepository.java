package com.lgb.function.admin.course.repository;

import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.course.CourseSite;
import com.lgb.function.admin.course.time.CourseTime;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.major.Major;
import com.lgb.function.admin.student.StudentUser;
import com.lgb.function.admin.teacher.Teacher;
import com.lgb.function.support.utils.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository implements CourseRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils<Course> repositoryUtils;

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
    public int selectExist(String courseNum) {
        String sql = "SELECT COUNT(courseId) as NUM FROM lgb_course WHERE deleteFlag = 0 AND courseNum = ?";
        Object[] args = {
                courseNum
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, Integer.class);
        } catch (Exception e) {
            return 1;
        }
    }

    @Override
    public int selectCourseName(String courseName) {
        String sql = "SELECT COUNT(courseId) as NUM FROM lgb_course WHERE deleteFlag = 0 AND courseName = ?";
        Object[] args = {
                courseName
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, Integer.class);
        } catch (Exception e) {
            return 1;
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

    @Override
    public List<Teacher> selectTeachers(int departmentId) {
        String sql = "SELECT teacherId, teacherName FROM lgb_teacher WHERE deleteFlag = 0 AND departmentId = ?";
        Object[] args = {
                departmentId
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectTeachersRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private class SelectTeachersRowMapper implements RowMapper<Teacher> {

        @Override
        public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
            Teacher teacher = new Teacher();

            teacher.setTeacherId(rs.getInt("teacherId"));
            teacher.setTeacherName(rs.getString("teacherName"));

            return teacher;
        }
    }

    @Override
    public int insert(Course course) {
        String sql = "INSERT INTO lgb_course (departmentId, majorId, courseNum, courseName, courseEnrollmentNum, courseTeacherOne, courseTeacherTwo, courseTuition, courseLimitNum, courseYears, courseGraLimitNum, courseSumFlag, courseRemark, courseRoom, teacherOneName, teacherTwoName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {
                course.getDepartmentId(),
                course.getMajorId(),
                course.getCourseNum(),
                course.getCourseName(),
                course.getCourseEnrollmentNum(),
                course.getCourseTeacherOne(),
                course.getCourseTeacherTwo(),
                course.getCourseTuition(),
                course.getCourseLimitNum(),
                course.getCourseYears(),
                course.getCourseGraLimitNum(),
                course.getCourseSumFlag(),
                course.getCourseRemark(),
                course.getCourseRoom(),
                course.getTeacherOneName(),
                course.getTeacherTwoName()
        };

        return jdbcTemplate.update(sql, args);
    }

    @Override
    public Page<Course> select4Page(int departmentId, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT courseId, majorName, courseTuition, courseNum, courseName, teacherOneName, teacherTwoName, courseRoom, courseEnrollmentNum, courseLimitNum FROM lgb_course C LEFT JOIN lgb_major M ON C.majorId = M.majorId WHERE C.deleteFlag = 0");

        List<Object> list = new ArrayList<>();
        if (departmentId > 0) {
            sql.append(" AND C.departmentId = ?");
            list.add(departmentId);
        }

        sql.append(" ORDER BY courseId DESC");
        Object[] args = list.toArray();

        return repositoryUtils.select4Page(sql.toString(), pageable, args, new Select4PageRowMapper());
    }

    private class Select4PageRowMapper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();

            course.setCourseId(rs.getInt("courseId"));
            course.setCourseName(rs.getString("courseName"));
            course.setTeacherOneName(rs.getString("teacherOneName"));
            course.setTeacherTwoName(rs.getString("teacherTwoName"));
            course.setCourseRoom(rs.getInt("courseRoom"));
            course.setCourseEnrollmentNum(rs.getInt("courseEnrollmentNum"));
            course.setMajorName(rs.getString("majorName"));
            course.setCourseNum(rs.getString("courseNum"));
            course.setCourseTuition(rs.getInt("courseTuition"));
            course.setCourseLimitNum(rs.getInt("courseLimitNum"));
            course.setCourseStuNum(courseStuNum(course.getCourseId()));

            return course;
        }
    }

    private int courseStuNum(int courseId) {
        String sql = "SELECT COUNT(studentCourseId) AS NUM FROM lgb_studentCourse WHERE courseId = ?";
        Object[] args = {
                courseId
        };

        return jdbcTemplate.queryForObject(sql, args, Integer.class);
    }

    @Override
    public Course selectByCourseNum(String courseNum) {
        String sql = "SELECT courseId FROM lgb_course WHERE deleteFlag = 0 AND courseNum = ?";
        Object[] args = {
                courseNum
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectByCourseNumRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    private class SelectByCourseNumRowMapper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();

            course.setCourseId(rs.getInt("courseId"));

            return course;
        }
    }

    @Override
    public void insertTeacherCourse(int courseId, int courseTeacherOne) {
        String sql = "INSERT INTO lgb_teacherCourse (teacherId, courseId) VALUES (?, ?)";
        Object[] args = {
                courseId,
                courseTeacherOne
        };

        jdbcTemplate.update(sql, args);
    }

    public Teacher selectTeacher(int teacherId) {
        String sql = "SELECT teacherId, teacherName FROM lgb_teacher WHERE teacherId = ?";
        Object[] args = {
                teacherId
        };

        return jdbcTemplate.queryForObject(sql, args, new SelectTeacherRowMapper());
    }

    private class SelectTeacherRowMapper implements RowMapper<Teacher> {

        @Override
        public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
            Teacher teacher = new Teacher();

            teacher.setTeacherId(rs.getInt("teacherId"));
            teacher.setTeacherName(rs.getString("teacherName"));

            return teacher;
        }
    }

    @Override
    public Course select(int courseId) {
        String sql = "SELECT courseId, C.departmentId, C.majorId, M.majorName, courseNum, courseName, courseEnrollmentNum, courseTeacherOne,courseTeacherTwo, courseTuition, courseLimitNum, courseYears, courseGraLimitNum, courseSumFlag, courseRemark, courseRoom, teacherOneName, teacherTwoName FROM lgb_course C LEFT JOIN lgb_major M ON C.majorId = M.majorId WHERE C.deleteFlag = 0 AND courseId = ?";
        Object[] args = {
                courseId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    private class SelectRowMapper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();

            course.setCourseId(rs.getInt("courseId"));
            course.setDepartmentId(rs.getInt("departmentId"));
            course.setMajorId(rs.getInt("majorId"));
            course.setMajorName(rs.getString("majorName"));
            course.setCourseName(rs.getString("courseName"));
            course.setCourseEnrollmentNum(rs.getInt("courseEnrollmentNum"));
            course.setCourseTeacherOne(rs.getInt("courseTeacherOne"));
            course.setCourseTeacherTwo(rs.getInt("courseTeacherTwo"));
            course.setCourseTuition(rs.getInt("courseTuition"));
            course.setCourseLimitNum(rs.getInt("courseLimitNum"));
            course.setCourseYears(rs.getInt("courseYears"));
            course.setCourseGraLimitNum(rs.getInt("courseGraLimitNum"));
            course.setCourseSumFlag(rs.getInt("courseSumFlag"));
            course.setCourseRemark(rs.getString("courseRemark"));
            course.setCourseRoom(rs.getInt("courseRoom"));
            course.setTeacherTwoName(rs.getString("teacherTwoName"));
            course.setTeacherOneName(rs.getString("teacherOneName"));
            course.setCourseNum(rs.getString("courseNum"));

            return course;
        }
    }

    @Override
    public List<CourseTime> selectTimes(int courseId) {
        String sql = "SELECT timeWeek, timeSpecific FROM lgb_courseTime WHERE courseId = ?";
        Object[] args = {
                courseId
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectTimesRowMapper());
        } catch (Exception e) {
            return new ArrayList<CourseTime>();
        }
    }

    @Override
    public int update(Course course) {
        String sql = "UPDATE lgb_course SET departmentId = ?, majorId = ?, courseNum = ?, courseName = ?, courseEnrollmentNum = ?, courseTeacherOne = ?, courseTeacherTwo = ?, courseTuition = ?, courseLimitNum = ?, courseYears = ?, courseGraLimitNum = ?, courseSumFlag =?, courseRemark = ?, courseRoom = ?, teacherOneName = ?, teacherTwoName = ? WHERE deleteFlag = 0 AND courseId = ?";
        Object[] args = {
                course.getDepartmentId(),
                course.getMajorId(),
                course.getCourseNum(),
                course.getCourseName(),
                course.getCourseEnrollmentNum(),
                course.getCourseTeacherOne(),
                course.getCourseTeacherTwo(),
                course.getCourseTuition(),
                course.getCourseLimitNum(),
                course.getCourseYears(),
                course.getCourseGraLimitNum(),
                course.getCourseSumFlag(),
                course.getCourseRemark(),
                course.getCourseRoom(),
                course.getTeacherOneName(),
                course.getTeacherTwoName(),
                course.getCourseId()
        };

        try {
            return jdbcTemplate.update(sql, args);
        } catch (Exception e) {
            return 0;
        }
    }

    private class SelectTimesRowMapper implements RowMapper<CourseTime> {

        @Override
        public CourseTime mapRow(ResultSet rs, int rowNum) throws SQLException {
            CourseTime courseTime = new CourseTime();

            courseTime.setTimeWeek(rs.getInt("timeWeek"));
            courseTime.setTimeSpecific(rs.getString("timeSpecific"));

            return courseTime;
        }
    }

    @Override
    public boolean deleteTimes(int courseId) {
        String sql = "DELETE FROM lgb_courseTime WHERE courseId = ?";
        Object[] args = {
                courseId
        };

        try {
            return jdbcTemplate.update(sql, args) > 0 ? true : false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(int courseId) {
        String sql = "UPDATE lgb_course SET deleteFlag = 1 WHERE courseId = ?";
        Object[] args = {
                courseId
        };

        try {
            return jdbcTemplate.update(sql, args) == 1 ? true : false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Course selectById(int courseId) {
        String sql = "SELECT courseId, courseNum FROM lgb_course WHERE deleteFlag = 0 AND courseId = ?";
        Object[] args = {
                courseId
        };

        return jdbcTemplate.queryForObject(sql, args, new SelectByIdRowMapper());
    }

    private class SelectByIdRowMapper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();

            course.setCourseId(rs.getInt("courseId"));
            course.setCourseNum(rs.getString("courseNum"));

            return course;
        }
    }

    @Override
    public List<CourseSite> selectSiteNum(int courseId) {
        String sql = "SELECT S.stuName, C.courseRoom, D.departmentName, M.majorName, C.courseName, S.stuBirthday, S.stuId, STUSITE.siteNum FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_major M ON C.majorId = M.majorId LEFT JOIN lgb_department D ON M.departmentId = D.departmentId LEFT JOIN (SELECT TMP.studentId, TMP.stuBirthday, TMP.courseId, (@num:=@num + 1) AS siteNum FROM (SELECT SC.studentId, S.stuBirthday, SC.courseId FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId WHERE SC.courseId = ? ORDER BY S.stuBirthday ASC) AS TMP, (SELECT @num:=0) AS IT) AS STUSITE ON STUSITE.studentId = S.stuId";
        Object[] args = {
                courseId
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectSiteNumRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<CourseSite>();
        }
    }

    private class SelectSiteNumRowMapper implements RowMapper<CourseSite> {

        @Override
        public CourseSite mapRow(ResultSet rs, int rowNum) throws SQLException {
            CourseSite site = new CourseSite();

            site.setStuName(rs.getString("stuName"));
            site.setCourseRoom(rs.getString("courseRoom"));
            site.setDepartmentName(rs.getString("departmentName"));
            site.setMajorName(rs.getString("majorName"));
            site.setCourseRoom(rs.getString("courseName"));
            site.setSiteNum(rs.getInt("siteNum"));
            site.setStuBirthday(rs.getDate("stuBirthday"));
            site.setCourseName(rs.getString("courseName"));

            return site;
        }
    }

    @Override
    public List<StudentUser> selectStudents(int courseId) {
        String sql = "SELECT S.stuId, S.stuName FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId WHERE SC.courseId = ?";
        Object[] args = {
                courseId
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectStudentsRowMapper());
        } catch (Exception e) {
            return new ArrayList<StudentUser>();
        }
    }

    private class SelectStudentsRowMapper implements RowMapper<StudentUser> {

        @Override
        public StudentUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            StudentUser studentUser = new StudentUser();

            studentUser.setStuId(rs.getInt("stuId"));
            studentUser.setStuName(rs.getString("stuName"));

            return studentUser;
        }
    }

    @Override
    public Course selectName(int courseId) {
        String sql = "SELECT courseId, courseName FROM lgb_course WHERE courseId = ?";
        Object[] args = {
                courseId
        };
        return jdbcTemplate.queryForObject(sql, args, new SelectNameRowMaper());
    }

    private class SelectNameRowMaper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();

            course.setCourseId(rs.getInt("courseId"));
            course.setCourseName(rs.getString("courseName"));

            return course;
        }
    }

    @Override
    public boolean updateLeader(Course course) {
        String sql = "UPDATE lgb_course SET courseMaster = ? WHERE deleteFlag = 0 AND courseId = ?";
        Object[] args = {
                course.getCourseMaster(),
                course.getCourseId()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
