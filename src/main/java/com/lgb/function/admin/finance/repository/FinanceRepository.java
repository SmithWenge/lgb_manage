package com.lgb.function.admin.finance.repository;

import com.google.common.base.Optional;
import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.major.Major;
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
import java.util.Date;
import java.util.List;

@Repository
public class FinanceRepository implements FinanceRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils<Finance> repositoryUtils;

    public Page<Finance> selectUnFinance4Page(Finance finance, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT SC.studentCourseId, S.stuCardNum, S.stuName, SC.signUpComeFrom, D.departmentName, M.majorName, C.courseName, C.courseTuition, S.stuPreferential, SC.signUpUser, DATE_FORMAT(SC.signUpTime, '%Y-%m-%d') AS signUpDate  FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_department D ON C.departmentId = D.departmentId LEFT JOIN lgb_major M ON C.majorId = M.majorId WHERE SC.tuitionFlag = 0 AND C.deleteFlag = 0");

        List<Object> list = new ArrayList<>();
        Optional<Finance> optional = Optional.fromNullable(finance);

        if (optional.isPresent()) {
            if (finance.getCourseDiscount() > 0 ) {
                sql.append(" AND S.stuPreferential = ?");
                list.add(finance.getCourseDiscount());
            }

            if (finance.getSignUpComeFrom() > 0) {
                sql.append(" AND SC.signUpComeFrom = ?");
                list.add(finance.getSignUpComeFrom());
            }

            if (finance.getDepartmentId() > 0) {
                sql.append(" AND D.departmentId = ?");
                list.add(finance.getDepartmentId());
            }

            if (finance.getMajorId() > 0) {
                sql.append(" AND M.majorId = ?");
                list.add(finance.getMajorId());
            }

            if (finance.getCourseId() > 0) {
                sql.append(" AND C.courseId = ?");
                list.add(finance.getCourseId());
            }
        }

        Object[] args = list.toArray();
        sql.append(" ORDER BY SC.studentCourseId");

        return repositoryUtils.select4Page(sql.toString(), pageable, args, new SelectUnFinance4PageRowMapper());
    }

    private class SelectUnFinance4PageRowMapper implements RowMapper<Finance> {

        @Override
        public Finance mapRow(ResultSet rs, int rowNum) throws SQLException {
            Finance finance = new Finance();

            finance.setStudentCourseId(rs.getInt("studentCourseId"));
            finance.setCardNum(rs.getString("stuCardNum"));
            finance.setStuName(rs.getString("stuName"));
            finance.setSignUpComeFrom(rs.getInt("signUpComeFrom"));
            finance.setDepartmentName(rs.getString("departmentName"));
            finance.setMajorName(rs.getString("majorName"));
            finance.setCourseName(rs.getString("courseName"));
            finance.setCourseTuition(rs.getInt("courseTuition"));
            finance.setCourseDiscount(rs.getInt("stuPreferential"));
            finance.setSignUpUser(rs.getString("signUpUser"));
            finance.setSignUpDate(rs.getDate("signUpDate"));

            return finance;
        }
    }

    @Override
    public Page<Finance> selectFinance4Page(Finance finance, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT SC.studentCourseId, S.stuCardNum, S.stuName, SC.signUpComeFrom, D.departmentName, M.majorName, C.courseName, C.courseTuition, SC.courseDiscount, SC.signUpUser, SC.financeUser, SC.financeTime, SC.actualTuition  FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_department D ON C.departmentId = D.departmentId LEFT JOIN lgb_major M ON C.majorId = M.majorId WHERE SC.tuitionFlag = 1");

        List<Object> list = new ArrayList<>();
        Optional<Date> optional = Optional.fromNullable(finance.getQueryFinanceDate());

        if (optional.isPresent()) {
            sql.append(" AND DATE_FORMAT(SC.financeTime,'%Y-%m-%d') = DATE_FORMAT(?,'%Y-%m-%d')");
            list.add(finance.getQueryFinanceDate());
        }

        Object[] args = list.toArray();
        sql.append(" ORDER BY SC.financeTime DESC");
        return repositoryUtils.select4Page(sql.toString(), pageable, args, new SelectFinance4PageRowMapper());

    }

    @Override
    public Page<Finance> selectTwoDayFinance4Page(Finance finance, Pageable pageable) {
        String sql = "SELECT SC.studentCourseId, S.stuCardNum, S.stuName, SC.signUpComeFrom, D.departmentName, M.majorName, C.courseName, C.courseTuition, SC.courseDiscount, SC.signUpUser, SC.financeUser, SC.financeTime, SC.actualTuition  FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_department D ON C.departmentId = D.departmentId LEFT JOIN lgb_major M ON C.majorId = M.majorId WHERE SC.tuitionFlag = 1 AND DATE_FORMAT(SC.financeTime,'%Y-%m-%d') between DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d')";
        Object[] args = {
                finance.getQueryFinanceDateOne(),
                finance.getQueryFinanceDateTwo()
        };
        return repositoryUtils.select4Page(sql, pageable, args, new SelectFinance4PageRowMapper());
    }

    private class SelectFinance4PageRowMapper implements RowMapper<Finance> {

        @Override
        public Finance mapRow(ResultSet rs, int rowNum) throws SQLException {
            Finance finance = new Finance();

            finance.setStudentCourseId(rs.getInt("studentCourseId"));
            finance.setCardNum(rs.getString("stuCardNum"));
            finance.setStuName(rs.getString("stuName"));
            finance.setSignUpComeFrom(rs.getInt("signUpComeFrom"));
            finance.setDepartmentName(rs.getString("departmentName"));
            finance.setMajorName(rs.getString("majorName"));
            finance.setCourseName(rs.getString("courseName"));
            finance.setCourseTuition(rs.getInt("courseTuition"));
            finance.setCourseDiscount(rs.getInt("courseDiscount"));
            finance.setFinanceUser(rs.getString("financeUser"));
            finance.setFinanceTime(rs.getTimestamp("financeTime"));
            finance.setActualTuition(rs.getInt("actualTuition"));

            return finance;
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

    private class SelectCoursesRowMapper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();

            course.setCourseId(rs.getInt("courseId"));
            course.setCourseName(rs.getString("courseName"));

            return course;
        }
    }

    @Override
    public Finance select(int studentCourseId) {
        String sql = "SELECT SC.studentCourseId, S.stuName, S.stuGender, S.stuTelOne, S.stuTelTwo, S.stuBirthday, SC.signUpComeFrom, D.departmentName, M.majorName, C.courseName, C.courseTuition, S.stuPreferential FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_department D ON C.departmentId = D.departmentId LEFT JOIN lgb_major M ON C.majorId = M.majorId WHERE SC.tuitionFlag = 0 AND C.deleteFlag = 0 AND SC.studentCourseId = ?";
        Object[] args = {
                studentCourseId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private class SelectRowMapper implements RowMapper<Finance> {

        @Override
        public Finance mapRow(ResultSet rs, int rowNum) throws SQLException {
            Finance finance = new Finance();

            finance.setStudentCourseId(rs.getInt("studentCourseId"));
            finance.setStuName(rs.getString("stuName"));
            finance.setStuGender(rs.getInt("stuGender"));
            finance.setTelOne(rs.getString("stuTelOne"));
            finance.setTelTwo(rs.getString("stuTelTwo"));
            finance.setStuBirthday(rs.getDate("stuBirthday"));
            finance.setSignUpComeFrom(rs.getInt("signUpComeFrom"));
            finance.setDepartmentName(rs.getString("departmentName"));
            finance.setMajorName(rs.getString("majorName"));
            finance.setCourseName(rs.getString("courseName"));
            finance.setCourseTuition(rs.getInt("courseTuition"));
            finance.setCourseDiscount(rs.getInt("stuPreferential"));

            return finance;
        }
    }

    @Override
    public List<Finance> selectFinanceCourse(int studentCourseId) {
        String sql = "SELECT S.stuName, SC.actualTuition, D.departmentName, M.majorName, C.courseName, C.courseTuition, SC.courseDiscount, SC.financeTime FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_department D ON C.departmentId = D.departmentId LEFT JOIN lgb_major M ON C.majorId = M.majorId WHERE SC.tuitionFlag = 1 AND SC.studentId IN (SELECT studentId FROM lgb_studentCourse WHERE studentCourseId = ?)";
        Object[] args = {
                studentCourseId
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectFinanceCourse());
        } catch (Exception e) {
            return new ArrayList<Finance>();
        }
    }

    private class SelectFinanceCourse implements RowMapper<Finance> {

        @Override
        public Finance mapRow(ResultSet rs, int rowNum) throws SQLException {
            Finance finance = new Finance();

            finance.setStuName(rs.getString("stuName"));
            finance.setActualTuition(rs.getInt("actualTuition"));
            finance.setDepartmentName(rs.getString("departmentName"));
            finance.setMajorName(rs.getString("majorName"));
            finance.setCourseName(rs.getString("courseName"));
            finance.setCourseTuition(rs.getInt("courseTuition"));
            finance.setCourseDiscount(rs.getInt("courseDiscount"));
            finance.setFinanceTime(rs.getTimestamp("financeTime"));

            return finance;
        }
    }

    @Override
    public Finance selectById(int studentCourseId) {
        String sql = "SELECT studentCourseId FROM lgb_studentCourse WHERE studentCourseId = ?";
        Object[] args = {
                studentCourseId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectByIdRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    private class SelectByIdRowMapper implements RowMapper<Finance> {

        @Override
        public Finance mapRow(ResultSet rs, int rowNum) throws SQLException {
            Finance finance = new Finance();

            finance.setStudentCourseId(rs.getInt("studentCourseId"));

            return finance;
        }
    }

    @Override
    public boolean update(Finance finance, String logUser) {
        String sql = "UPDATE lgb_studentCourse SET actualTuition = ?, tuitionFlag = 1, financeTime = NOW(), financeUser = ?, courseDiscount = ? WHERE studentCourseId = ?";
        Object[] args = {
                finance.getActualTuition(),
                logUser,
                finance.getCourseDiscount(),
                finance.getStudentCourseId()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
