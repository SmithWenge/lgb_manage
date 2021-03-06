package com.lgb.function.admin.finance.repository;

import com.google.common.base.Optional;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.StudentCourse;
import com.lgb.function.admin.major.Major;
import com.lgb.function.admin.setting.LGBConfig;
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
import java.util.Date;
import java.util.List;

@Repository
public class FinanceRepository implements FinanceRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils<Finance> repositoryUtils;

    public Page<Finance> selectUnFinance4Page(Finance finance, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT SC.studentCourseId, S.stuCardNum, S.stuName, SC.signUpComeFrom, D.departmentName, M.majorName, C.courseName, C.courseTuition, S.stuPreferential, SC.signUpUser, DATE_FORMAT(SC.signUpTime, '%Y-%m-%d') AS signUpDate  FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_department D ON C.departmentId = D.departmentId LEFT JOIN lgb_major M ON C.majorId = M.majorId WHERE SC.tuitionFlag = 0 AND C.deleteFlag = 0 AND SC.deleteFlag = 0");

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
        StringBuilder sql = new StringBuilder("SELECT SC.studentCourseId, S.stuCardNum, S.stuName, SC.signUpComeFrom, D.departmentName, M.majorName, C.courseName, C.courseTuition, SC.courseDiscount, SC.signUpUser, SC.financeUser, SC.financeTime, SC.actualTuition  FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_department D ON C.departmentId = D.departmentId LEFT JOIN lgb_major M ON C.majorId = M.majorId WHERE SC.tuitionFlag = 1 AND SC.deleteFlag = 0");

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
        StringBuilder sql = new StringBuilder("SELECT SC.studentCourseId, S.stuCardNum, S.stuName, SC.signUpComeFrom, D.departmentName, M.majorName, C.courseName, C.courseTuition, SC.courseDiscount, SC.signUpUser, SC.financeUser, SC.financeTime, SC.actualTuition  FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_department D ON C.departmentId = D.departmentId LEFT JOIN lgb_major M ON C.majorId = M.majorId WHERE SC.tuitionFlag = 1 AND SC.deleteFlag = 0");

        List<Object> list = new ArrayList<>();
        Date dateOne = finance.getQueryFinanceDateOne();
        Date dateTwo = finance.getQueryFinanceDateTwo();
        if (dateOne != null) {
            list.add(dateOne);
            sql.append(" AND DATE_FORMAT(SC.financeTime,'%Y-%m-%d') >= ?");
        }
        if (dateTwo != null) {
            list.add(dateTwo);
            sql.append(" AND DATE_FORMAT(SC.financeTime,'%Y-%m-%d') <= ?");
        }

        Object[] args = list.toArray();

        return repositoryUtils.select4Page(sql.toString(), pageable, args, new SelectFinance4PageRowMapper());
    }

    @Override
    public List<Finance> selectUnFinanceByCard(Finance finance) {
        String sql = "SELECT SC.studentCourseId, S.stuCardNum, S.stuName, SC.signUpComeFrom, D.departmentName, M.majorName, C.courseName, C.courseTuition, S.stuPreferential, SC.signUpUser, DATE_FORMAT(SC.signUpTime, '%Y-%m-%d') AS signUpDate  FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_department D ON C.departmentId = D.departmentId LEFT JOIN lgb_major M ON C.majorId = M.majorId WHERE SC.tuitionFlag = 0 AND C.deleteFlag = 0 AND S.stuCardNum = ? AND SC.deleteFlag = 0";
        Object[] args = {
                finance.getStuCardNum()
        };

        return jdbcTemplate.query(sql, args, new SelectUnFinance4PageRowMapper());
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
        String sql = "SELECT SC.studentCourseId, S.stuName, S.stuGender, S.stuTelOne, S.stuTelTwo, S.stuBirthday, SC.signUpComeFrom, D.departmentName, M.majorName, C.courseName, C.courseTuition, S.stuPreferential FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_department D ON C.departmentId = D.departmentId LEFT JOIN lgb_major M ON C.majorId = M.majorId WHERE SC.tuitionFlag = 0 AND C.deleteFlag = 0 AND SC.studentCourseId = ? AND SC.deleteFlag = 0";
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
        String sql = "SELECT S.stuName, SC.actualTuition, D.departmentName, M.majorName, C.courseName, C.courseTuition, SC.courseDiscount, SC.financeTime FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_department D ON C.departmentId = D.departmentId LEFT JOIN lgb_major M ON C.majorId = M.majorId WHERE SC.tuitionFlag = 1 AND SC.deleteFlag = 0 AND SC.studentId IN (SELECT studentId FROM lgb_studentCourse WHERE studentCourseId = ? AND SC.deleteFlag = 0)";
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
        String sql = "UPDATE lgb_studentCourse SET actualTuition = ?, tuitionFlag = 1, financeTime = NOW(), financeUser = ?, courseDiscount = ?, billNumber = ? WHERE studentCourseId = ? AND deleteFlag = 0";
        Object[] args = {
                finance.getActualTuition(),
                logUser,
                finance.getCourseDiscount(),
                finance.getBillNumber(),
                finance.getStudentCourseId()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查询所有未缴费学员的信息
     *
     * @return
     */
    @Override
    public List<StudentUser> select4UnpaymentStudent(Course course) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT S.stuId, S.stuName, S.stuLevel, S.stuTelOne, S.stuCardNum, SC.tuitionFlag, C.courseName, SC.studentCourseId FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId WHERE tuitionFlag = 0 AND C.deleteFlag = 0 AND SC.deleteFlag = 0");

        List<Object> arguments = new ArrayList<>();
        if (course.getCourseId() > 0 ) {
            sqlBuilder.append(" AND C.courseId = ?");
            arguments.add(course.getCourseId());
        }
        try {
            return jdbcTemplate.query(sqlBuilder.toString(), arguments.toArray(), new Select4UnpaymentStudentRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private class Select4UnpaymentStudentRowMapper implements RowMapper<StudentUser> {

        @Override
        public StudentUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            StudentUser studentUser = new StudentUser();

            studentUser.setStuId(rs.getInt("stuId"));
            studentUser.setStuName(rs.getString("stuName"));
            studentUser.setStuCardNum(rs.getString("stuCardNum"));
            studentUser.setStuTelOne(rs.getString("stuTelOne"));
            studentUser.setTuitionFlag(rs.getInt("tuitionFlag"));
            studentUser.setCourseName(rs.getString("courseName"));
            studentUser.setStudentCourseId(rs.getInt("studentCourseId"));

            return studentUser;
        }
    }

    /**
     * 查看所有未删除课程,用户选择查询未缴费的课程
     *
     * @return
     */
    @Override
    public List<Course> selectUndeleteCourses() {
        String sql = "SELECT courseId, courseName FROM lgb_course WHERE deleteFlag = 0";

        try {
            return jdbcTemplate.query(sql, new Object[] {}, new SelectUndeleteCoursesRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * 查询已付费的学员信息
     *
     * @param course
     * @return
     */
    @Override
    public List<StudentUser> select4paymentStudent(Course course) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT S.stuId, S.stuName, S.stuLevel, S.stuTelOne, S.stuCardNum, SC.tuitionFlag, C.courseName, SC.studentCourseId FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId WHERE tuitionFlag = 1 AND C.deleteFlag = 0 AND SC.deleteFlag = 0");

        List<Object> arguments = new ArrayList<>();
        if (course.getCourseId() > 0 ) {
            sqlBuilder.append(" AND C.courseId = ?");
            arguments.add(course.getCourseId());
        }
        try {
            return jdbcTemplate.query(sqlBuilder.toString(), arguments.toArray(), new Select4UnpaymentStudentRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private class SelectUndeleteCoursesRowMapper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();

            course.setCourseId(rs.getInt("courseId"));
            course.setCourseName(rs.getString("courseName"));

            return course;
        }
    }

    @Override
    public StudentCourse selectByStudentCourseId(int studentCourseId) {
        String sql = "SELECT studentCourseId FROM lgb_studentCourse WHERE deleteFlag = 0 AND studentCourseId = ?";
        Object[] args = {
                studentCourseId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectByStudentCourseIdRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    private class SelectByStudentCourseIdRowMapper implements RowMapper<StudentCourse> {

        @Override
        public StudentCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
            StudentCourse studentCourse = new StudentCourse();

            studentCourse.setStudentCourseId(rs.getInt("studentCourseId"));

            return studentCourse;
        }
    }

    @Override
    public boolean delete(int studentCourseId) {
        String sql = "UPDATE lgb_studentCourse SET deleteFlag = 1 WHERE studentCourseId = ? AND deleteFlag = 0";
        Object[] args = {
                studentCourseId
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 查询当前收费规则
     *
     * @return
     */
    @Override
    public LGBConfig select4NowConfig() {
        String sql = "SELECT financeMessage FROM lgb_config WHERE configId = ?";
        Object[] args = {
                ConstantFields.DEFAULT_CONFIG_ID
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new Select4NowConfigRowMapper());
        } catch (Exception e) {
            LGBConfig config = new LGBConfig();
            config.setFinanceMessage("还没有定义规则");

            return config;
        }
    }

    private class Select4NowConfigRowMapper implements RowMapper<LGBConfig> {

        @Override
        public LGBConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
            LGBConfig config = new LGBConfig();

            config.setFinanceMessage(rs.getString("financeMessage"));

            return config;
        }
    }
}
