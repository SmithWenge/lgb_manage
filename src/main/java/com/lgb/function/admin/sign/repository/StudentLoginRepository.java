package com.lgb.function.admin.sign.repository;


import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.course.time.CourseTime;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.major.Major;
import com.lgb.function.admin.sign.StudentCourse;
import com.lgb.function.admin.student.StudentUser;
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
public class StudentLoginRepository implements StudentLoginRepositoryI {
    @Autowired
    private RepositoryUtils<Course> courseRepositoryUtils;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Page<Course> query4Page(Course course, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT \n" +
                "    courseId,\n" +
                "    C.departmentId,\n" +
                "    C.majorId,\n" +
                "    M.majorName,\n" +
                "    D.departmentName,\n" +
                "    courseNum,\n" +
                "    courseName,\n" +
                "    courseLimitNum\n" +
                "FROM\n" +
                "    lgb_course C\n" +
                "        LEFT JOIN\n" +
                "    lgb_department D ON C.departmentId = D.departmentId\n" +
                "        LEFT JOIN\n" +
                "    lgb_major M ON C.majorId = M.majorId\n" +
                "WHERE\n" +
                "    C.deleteFlag = 0\n" +
                "        AND courseId IN (SELECT \n" +
                "            courseId\n" +
                "        FROM\n" +
                "            lgb_course C\n" +
                "        WHERE\n" +
                "            C.courseId NOT IN (SELECT \n" +
                "                    courseId\n" +
                "                FROM\n" +
                "                    lgb_studentCourse SC\n" +
                "                WHERE\n" +
                "                    SC.studentId = ?))\n" +
                "        AND D.departmentStartDate < NOW()\n" +
                "        AND D.departmentStopDate > NOW()\n" +
                "        AND C.courseId IN (SELECT \n" +
                "            C.courseId\n" +
                "        FROM\n" +
                "            lgb_course C\n" +
                "                LEFT JOIN\n" +
                "            (SELECT \n" +
                "                SC.courseId, COUNT(SC.studentCourseId) AS NUM\n" +
                "            FROM\n" +
                "                lgb_studentCourse SC\n" +
                "            GROUP BY SC.courseId) AS TMP ON TMP.courseId = C.courseId\n" +
                "        WHERE\n" +
                "            C.deleteFlag = 0 AND NUM < courseLimitNum)");

        List<Object> list = new ArrayList<>();
        if (course.getStudentId() > 0) {
            list.add(course.getStudentId());
        }

        if (course.getDepartmentId() > 0) {
            sql.append(" AND D.departmentId = ?");
            list.add(course.getDepartmentId());
        }

        if (course.getMajorId() > 0) {
            sql.append(" AND C.majorId = ?");
            list.add(course.getMajorId());
        }
        sql.append(" ORDER BY courseId DESC");

        Object[] args = list.toArray();
        try {
            return courseRepositoryUtils.select4Page(sql.toString(), pageable, args, new Query4PageRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Page<Course> querySign4Page(Course course, Pageable pageable) {
        String sql = ("SELECT \n" +
                "    lgb_studentCourse.courseId,\n" +
                "\tlgb_course.courseNum,\n" +
                "    lgb_course.courseName,\n" +
                "    lgb_major.majorName,\n" +
                "    lgb_department.departmentName,\n" +
                "    lgb_course.courseLimitNum\n" +
                "FROM\n" +
                "    lgb_course\n" +
                "        LEFT JOIN\n" +
                "    lgb_major ON lgb_course.majorId = lgb_major.majorId\n" +
                "        LEFT JOIN\n" +
                "    lgb_department ON lgb_course.departmentId = lgb_department.departmentId\n" +
                "\t\tleft join\n" +
                "\tlgb_studentCourse on lgb_course.courseId = lgb_studentCourse.courseId\n" +
                "WHERE\n" +
                "    lgb_course.deleteFlag = 0 AND lgb_studentCourse.studentId = ?  ORDER BY courseId DESC\n" +
                "\n");

        Object[] args = {
                course.getStudentId()
        };
        try {
            return courseRepositoryUtils.select4Page(sql, pageable, args, new Query4PageRowMapper());
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public StudentUser selectUnique(StudentUser studentUser) {
        String sql = "SELECT stuCardNum, stuId, stuName FROM lgb_manage.lgb_student WHERE deleteFlag = 0 AND stuCardNum = ? ";
        Object[] args = {
                studentUser.getStuCardNum(),
        };

        StudentUser returnUser = null;

        try {
            returnUser = jdbcTemplate.queryForObject(sql, args, new SelectUniqueRowMapper());
        } catch (Exception e) {
            return null;
        }

        return returnUser != null ? returnUser : null;
    }

    @Override
    public Course queryForCourse(int courseId) {
        String sql = "SELECT courseId, majorName, departmentName, courseTuition, courseNum, courseName, teacherOneName, teacherTwoName, courseRoom, courseLimitNum, courseStuNum FROM lgb_course C  LEFT JOIN lgb_major M ON C.majorId = M.majorId LEFT JOIN lgb_department D ON C.departmentId = D.departmentId WHERE C.deleteFlag = 0 and courseId = ?";
        Object[] args = {
                courseId
        };
        try {
            return jdbcTemplate.queryForObject(sql, args, new courseRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<CourseTime> queryForCourseTime(int courseId) {
        String sql = "SELECT \n" +
                "    lgb_course.courseId,\n" +
                "    lgb_courseTime.timeWeek,\n" +
                "    lgb_courseTime.timeSpecific\n" +
                "FROM\n" +
                "    lgb_course\n" +
                "        LEFT JOIN\n" +
                "    lgb_courseTime ON lgb_course.courseId = lgb_courseTime.courseId\n" +
                "WHERE\n" +
                "    lgb_course.deleteFlag = 0\n" +
                "        AND lgb_course.courseId = ?";
        Object[] args = {
                courseId
        };
        try {
            return jdbcTemplate.query(sql, args, new courseTimeRowMapper());

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<CourseTime>();
        }
    }


    @Override
    public boolean signUp(StudentCourse studentCourse) {
        String sql = "INSERT INTO lgb_studentCourse (courseId, studentId) VALUES (?, ?)";
        Object[] args = {
                studentCourse.getCourseId(),
                studentCourse.getStudentId()
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;

    }
    @Override
    public StudentCourse signUpVer(StudentCourse studentCourse) {
        String sql = "SELECT studentId,courseId FROM lgb_studentCourse WHERE studentId = ? AND courseId = ?";
        Object[] args = {
                studentCourse.getStudentId(),
                studentCourse.getCourseId()
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SignUpVerRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean delete(StudentCourse studentCourse) {
        String sql = "DELETE FROM lgb_studentCourse  WHERE studentId = ? AND courseId = ? ";
        Object[] args = {
                studentCourse.getStudentId(),
                studentCourse.getCourseId()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1 ? true : false;
        } catch (Exception e) {
            return false;
        }

    }
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

    private class SelectMajorsRowMapper implements RowMapper<Major> {

        @Override
        public Major mapRow(ResultSet rs, int rowNum) throws SQLException {
            Major major = new Major();

            major.setMajorId(rs.getInt("majorId"));
            major.setMajorName(rs.getString("majorName"));

            return major;
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



    private class SelectUniqueRowMapper implements RowMapper<StudentUser> {

        @Override
        public StudentUser mapRow(ResultSet resultSet, int i) throws SQLException {
            StudentUser studentUser = new StudentUser();

            studentUser.setStuId(resultSet.getInt("stuId"));
            studentUser.setStuCardNum(resultSet.getString("stuCardNum"));
            studentUser.setStuName(resultSet.getString("stuName"));

            return studentUser;
        }
    }

    private class Query4PageRowMapper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet resultSet, int i) throws SQLException {
            Course course = new Course();

            course.setCourseId(resultSet.getInt("courseId"));
            course.setDepartmentName(resultSet.getString("departmentName"));
            course.setMajorName(resultSet.getString("majorName"));
            course.setCourseNum(resultSet.getString("courseNum"));
            course.setCourseName(resultSet.getString("courseName"));
            course.setCourseLimitNum(resultSet.getInt("courseLimitNum"));

            return course;
        }
    }

    private class courseRowMapper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet resultSet, int i) throws SQLException {
            Course course = new Course();

            course.setCourseId(resultSet.getInt("courseId"));
            course.setCourseName(resultSet.getString("courseName"));
            course.setCourseNum(resultSet.getString("courseNum"));
            course.setDepartmentName(resultSet.getString("departmentName"));
            course.setMajorName(resultSet.getString("majorName"));
            course.setCourseLimitNum(resultSet.getInt("courseLimitNum"));
            course.setTeacherOneName(resultSet.getString("teacherOneName"));
            course.setTeacherTwoName(resultSet.getString("teacherTwoName"));
            course.setCourseTuition(resultSet.getInt("courseTuition"));
            course.setCourseRoom(resultSet.getInt("courseRoom"));
            course.setCourseStuNum(courseStuNum(course.getCourseId()));
            course.setTimes(queryForCourseTime(course.getCourseId()));

            return course;
        }
    }

    private class courseTimeRowMapper implements RowMapper<CourseTime> {

        @Override
        public CourseTime mapRow(ResultSet resultSet, int i) throws SQLException {
            CourseTime course = new CourseTime();

            course.setTimeWeek(resultSet.getInt("timeWeek"));
            String specific = resultSet.getString("timeSpecific");
            course.setTimeSpecificInt(convertTimeSpecific(specific));
            course.setCourseId(resultSet.getInt("courseId"));

            return course;
        }
    }

    private class SignUpVerRowMapper implements RowMapper<StudentCourse> {

        @Override
        public StudentCourse mapRow(ResultSet resultSet, int i) throws SQLException {
            StudentCourse course = new StudentCourse();

            course.setCourseId(resultSet.getInt("courseId"));
            course.setStudentId(resultSet.getInt("studentId"));

            return course;
        }
    }

    private int convertTimeSpecific(String specific) {
        if (specific.equals("a")) {
            return 1;
        } else if (specific.equals("b")) {
            return 2;
        } else if (specific.equals("c")) {
            return 3;
        } else if (specific.equals("d")) {
            return 4;
        } else if (specific.equals("e")) {
            return 5;
        }

        return 0;
    }
    private int courseStuNum(int courseId) {
        String sql = "SELECT COUNT(studentCourseId) AS NUM FROM lgb_studentCourse WHERE courseId = ?";
        Object[] args = {
                courseId
        };

        return jdbcTemplate.queryForObject(sql, args, Integer.class);
    }
}
