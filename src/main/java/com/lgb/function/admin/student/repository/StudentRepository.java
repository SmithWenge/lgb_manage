package com.lgb.function.admin.student.repository;

import com.google.common.base.Optional;
import com.lgb.arc.utils.EncryptAnDecrypt;
import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.student.StudentUser;
import com.lgb.function.admin.teacher.Teacher;
import com.lgb.function.support.dictionary.impl.DefaultDictionaryManager;
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
public class StudentRepository implements StudentRepositoryI {
    @Autowired
    private RepositoryUtils<StudentUser> repositoryUtils;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Page<StudentUser> query4Page(StudentUser studentUser,Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT stuId, stuCardNum,stuName,stuGender,stuBirthday,stuTelOne,stuTelTwo FROM lgb_student WHERE deleteFlag = 0");
        List<Object> list = new ArrayList<Object>();

        Optional<StudentUser> optional = Optional.fromNullable(studentUser);
        if (optional.isPresent()) {
            if (studentUser.getStuName() != null && studentUser.getStuName().length() > 0) {
                sql.append(" AND stuName = ?");
                list.add(studentUser.getStuName());
            }

            if (studentUser.getStuCardNum() != null&& studentUser.getStuCardNum().length() > 0) {
                sql.append(" AND stuCardNum = ?");
                list.add(studentUser.getStuCardNum());
            }

            if (studentUser.getStuGender() > 0) {
                sql.append(" AND stuGender = ?");
                list.add(studentUser.getStuGender());
            }

            if (studentUser.getStuBirthday() != null) {
                sql.append(" AND stuBirthday = ?");
                list.add(studentUser.getStuBirthday());
            }

            if (studentUser.getStuOldWorkPlaceType() > 0) {
                sql.append(" AND stuOldWorkPlaceType = ?");
                list.add(studentUser.getStuOldWorkPlaceType());
            }

            if (studentUser.getStuType() > 0) {
                sql.append(" AND stuType = ?");
                list.add(studentUser.getStuType());
            }

            if (studentUser.getStuOldWorkType() > 0) {
                sql.append(" AND stuOldWorkType = ?");
                list.add(studentUser.getStuOldWorkType());
            }

            if (studentUser.getStuEducational() > 0) {
                sql.append(" AND stuEducational = ?");
                list.add(studentUser.getStuEducational());
            }

            if (studentUser.getStuIdentifiedNum() != null && studentUser.getStuIdentifiedNum().length() > 0) {
                sql.append(" AND stuIdentifiedNum = ?");
                list.add(studentUser.getStuIdentifiedNum());
            }

            if (studentUser.getStudentStartDate() != null) {
                sql.append(" AND studentStartDate = ?");
                list.add(studentUser.getStudentStartDate());
            }
            if (studentUser.getStudentStartDate() != null) {
                sql.append(" AND studentStartDate = ?");
                list.add(studentUser.getStudentStartDate());
            }
            if (studentUser.getStuTelOne() != null && studentUser.getStuTelOne().length() > 0) {
                sql.append(" AND stuTelOne = ?");
                list.add(studentUser.getStuTelOne());
            }
            if (studentUser.getStuNationality() != null && studentUser.getStuNationality().length() > 0) {
                sql.append(" AND stuNationality = ?");
                list.add(studentUser.getStuNationality());
            }
            if (studentUser.getStuLevel() != null && studentUser.getStuLevel().length() > 0) {
                sql.append(" AND stuLevel = ?");
                list.add(studentUser.getStuLevel());
            }
            if (studentUser.getStuPreferential() > 0) {
                sql.append(" AND stuPreferential = ?");
                list.add(studentUser.getStuPreferential());
            }
            if (studentUser.getStuDependentsTel() != null && studentUser.getStuDependentsTel().length() > 0) {
                sql.append(" AND stuDependentsTel = ?");
                list.add(studentUser.getStuDependentsTel());
            }
            if (studentUser.getStuOldWorkPlaceName() != null && studentUser.getStuOldWorkPlaceName().length() > 0) {
                sql.append(" AND stuOldWorkPlaceName = ?");
                list.add(studentUser.getStuOldWorkPlaceName());
            }
            if (studentUser.getStuHealth() != null && studentUser.getStuHealth().length() > 0) {
                sql.append(" AND stuHealth = ?");
                list.add(studentUser.getStuHealth());
            }
            if (studentUser.getStuDependentsDesc() != null && studentUser.getStuDependentsDesc().length() > 0) {
                sql.append(" AND stuDependentsDesc = ?");
                list.add(studentUser.getStuDependentsDesc());
            }
        }

        Object[] args = list.toArray();

        sql.append(" ORDER BY stuId DESC");
        try {
            return repositoryUtils.select4Page(sql.toString(), pageable, args, new Query4PageRowMapper());
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public boolean insert(StudentUser studentUser) {
        String sql = "INSERT INTO lgb_student (stuId, stuCardNum, stuName, stuGender, stuTelOne, stuTelTwo, stuType, stuIdentifiedType, stuIdentifiedNum, stuOldWorkPlaceType, stuOldWorkPlaceName, stuPolitical, stuOldWorkType, stuNationality, stuBirthday, stuLastEightNum, stuCheck, stuHealth, stuLocation, stuEducational, stuLevel, stuSpeciality, stuPreferential, stuDependentsTel, stuDependentsDesc, stuRemarkOne, stuRemarkTwo, studentStartDate, stuPicture) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {
                studentUser.getStuId(),
                studentUser.getStuCardNum(),
                studentUser.getStuName(),
                studentUser.getStuGender(),
                studentUser.getStuTelOne(),
                studentUser.getStuTelTwo(),
                studentUser.getStuType(),
                studentUser.getStuIdentifiedType(),
                studentUser.getStuIdentifiedNum(),
                studentUser.getStuOldWorkPlaceType(),
                studentUser.getStuOldWorkPlaceName(),
                studentUser.getStuPolitical(),
                studentUser.getStuOldWorkType(),
                studentUser.getStuNationality(),
                studentUser.getStuBirthday(),
                studentUser.getStuLastEightNum(),
                studentUser.getStuCheck(),
                studentUser.getStuHealth(),
                studentUser.getStuLocation(),
                studentUser.getStuEducational(),
                studentUser.getStuLevel(),
                studentUser.getStuSpeciality(),
                studentUser.getStuPreferential(),
                studentUser.getStuDependentsTel(),
                studentUser.getStuDependentsDesc(),
                studentUser.getStuRemarkOne(),
                studentUser.getStuRemarkTwo(),
                studentUser.getStudentStartDate(),
                studentUser.getStuPicture()
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }

    @Override
    public StudentUser select(int stuId) {
        String sql = "SELECT stuId, stuCardNum, stuName, stuGender, stuTelOne, stuTelTwo, stuType, stuIdentifiedType, stuIdentifiedNum, stuOldWorkPlaceType, stuOldWorkPlaceName, stuPolitical, stuOldWorkType, stuNationality, stuBirthday, stuLastEightNum, stuCheck, stuHealth, stuLocation, stuEducational, stuLevel, stuSpeciality, stuPreferential, stuDependentsTel, stuDependentsDesc, stuRemarkOne, stuRemarkTwo, studentStartDate, stuPicture FROM lgb_student WHERE deleteFlag = 0 AND stuId = ?";
        Object[] args = {
                stuId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new Query4RowMapper());
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public List<StudentUser> selectForExport() {
        String sql = "SELECT stuCardNum, stuName, stuGender, stuBirthday, stuTelOne, stuTelTwo, stuOldWorkPlaceName, stuLocation FROM lgb_student WHERE deleteFlag = 0";
        Object[] args = {};


        return jdbcTemplate.query(sql, new exportRowMapper());


    }

    @Override
    public boolean update(StudentUser studentUser) {
        String sql = "UPDATE lgb_student SET stuId = ?, stuCardNum = ?, stuName = ?, stuGender = ?, stuTelOne = ?, stuTelTwo = ?, stuType = ?, stuIdentifiedType = ?, stuIdentifiedNum = ?, stuOldWorkPlaceType = ?,stuOldWorkPlaceName = ?, stuPolitical = ?, stuOldWorkType = ?, stuNationality = ?, stuBirthday = ?, stuLastEightNum = ?, stuCheck = ?, stuHealth = ?, stuLocation = ?, stuEducational = ?, stuLevel = ?, stuSpeciality = ?, stuPreferential = ?, stuDependentsTel = ?, stuDependentsDesc = ?, stuRemarkOne = ?, stuRemarkTwo = ?, studentStartDate = ? WHERE deleteFlag = 0 AND stuId = ?";
        Object[] args = {
                studentUser.getStuId(),
                studentUser.getStuCardNum(),
                studentUser.getStuName(),
                studentUser.getStuGender(),
                studentUser.getStuTelOne(),
                studentUser.getStuTelTwo(),
                studentUser.getStuType(),
                studentUser.getStuIdentifiedType(),
                studentUser.getStuIdentifiedNum(),
                studentUser.getStuOldWorkPlaceType(),
                studentUser.getStuOldWorkPlaceName(),
                studentUser.getStuPolitical(),
                studentUser.getStuOldWorkType(),
                studentUser.getStuNationality(),
                studentUser.getStuBirthday(),
                studentUser.getStuLastEightNum(),
                studentUser.getStuCheck(),
                studentUser.getStuHealth(),
                studentUser.getStuLocation(),
                studentUser.getStuEducational(),
                studentUser.getStuLevel(),
                studentUser.getStuSpeciality(),
                studentUser.getStuPreferential(),
                studentUser.getStuDependentsTel(),
                studentUser.getStuDependentsDesc(),
                studentUser.getStuRemarkOne(),
                studentUser.getStuRemarkTwo(),
                studentUser.getStudentStartDate(),
                studentUser.getStuId()
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }

    @Override
    public boolean delete(int stuId) {
        String sql = "UPDATE lgb_student SET deleteFlag = 1 WHERE stuId = ?";
        Object[] args = {
                stuId
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }

    private class Query4PageRowMapper implements RowMapper<StudentUser> {

        @Override
        public StudentUser mapRow(ResultSet resultSet, int i) throws SQLException {
            StudentUser studentUser = new StudentUser();

            studentUser.setStuId(resultSet.getInt("stuId"));
            studentUser.setStuName(resultSet.getString("stuName"));
            studentUser.setStuGender(resultSet.getInt("stuGender"));
            studentUser.setStuBirthday(resultSet.getDate("stuBirthday"));
            studentUser.setStuCardNum(resultSet.getString("stuCardNum"));
            studentUser.setStuTelOne(EncryptAnDecrypt.decrypt(resultSet.getString("stuTelOne")));
            String stuTelTwo = resultSet.getString("stuTelTwo");
            Optional<String> optional = Optional.fromNullable(stuTelTwo);
            if (optional.isPresent()) {
                studentUser.setStuTelTwo(EncryptAnDecrypt.decrypt(stuTelTwo));
            } else {
                studentUser.setStuTelTwo("");
            }

            return studentUser;
        }
    }

    private class exportRowMapper implements RowMapper<StudentUser> {

        @Override
        public StudentUser mapRow(ResultSet resultSet, int i) throws SQLException {
            StudentUser studentUser = new StudentUser();

            studentUser.setStuCardNum(resultSet.getString("stuCardNum"));
            studentUser.setStuName(resultSet.getString("stuName"));
            studentUser.setStuGender(resultSet.getInt("stuGender"));
            studentUser.setStuBirthday(resultSet.getDate("stuBirthday"));
            studentUser.setStuTelOne(EncryptAnDecrypt.decrypt(resultSet.getString("stuTelOne")));
            String stuTelTwo = resultSet.getString("stuTelTwo");
            Optional<String> optional = Optional.fromNullable(stuTelTwo);
            if (optional.isPresent()) {
                studentUser.setStuTelTwo(EncryptAnDecrypt.decrypt(stuTelTwo));
            } else {
                studentUser.setStuTelTwo("");
            }
            studentUser.setStuOldWorkPlaceName(resultSet.getString("stuOldWorkPlaceName"));
            studentUser.setStuLocation(resultSet.getString("stuLocation"));

            return studentUser;
        }
    }

    private class Query4RowMapper implements RowMapper<StudentUser> {

        @Override
        public StudentUser mapRow(ResultSet resultSet, int i) throws SQLException {
            StudentUser studentUser = new StudentUser();
            studentUser.setStuId(resultSet.getInt("stuId"));
            studentUser.setStuName(resultSet.getString("stuName"));
            studentUser.setStuGender(resultSet.getInt("stuGender"));
            studentUser.setStuTelOne(EncryptAnDecrypt.decrypt(resultSet.getString("stuTelOne")));

            String stuTelTwo = resultSet.getString("stuTelTwo");
            Optional<String> optional = Optional.fromNullable(stuTelTwo);
            if (optional.isPresent()) {
                studentUser.setStuTelTwo(EncryptAnDecrypt.decrypt(stuTelTwo));
            } else {
                studentUser.setStuTelTwo("");
            }
            studentUser.setStuIdentifiedType(resultSet.getInt("stuIdentifiedType"));
            studentUser.setStuCardNum(resultSet.getString("stuCardNum"));
            studentUser.setStuOldWorkPlaceType(resultSet.getInt("stuOldWorkPlaceType"));
            studentUser.setStuType(resultSet.getInt("stuType"));
            studentUser.setStuHealth(resultSet.getString("stuHealth"));
            studentUser.setStuLevel(resultSet.getString("stuLevel"));
            studentUser.setStuSpeciality(resultSet.getString("stuSpeciality"));
            studentUser.setStuPreferential(resultSet.getInt("stuPreferential"));
            studentUser.setStuDependentsTel(resultSet.getString("stuDependentsTel"));
            studentUser.setStuDependentsDesc(resultSet.getString("stuDependentsDesc"));
            studentUser.setStuRemarkOne(resultSet.getString("stuRemarkOne"));
            studentUser.setStuRemarkTwo(resultSet.getString("stuRemarkTwo"));
            studentUser.setStuLocation(resultSet.getString("stuLocation"));
            studentUser.setStuNationality(resultSet.getString("stuNationality"));
            studentUser.setStuPolitical(resultSet.getString("stuPolitical"));
            studentUser.setStuOldWorkPlaceName(resultSet.getString("stuOldWorkPlaceName"));
            studentUser.setStuBirthday(resultSet.getDate("stuBirthday"));
            studentUser.setStuLastEightNum(resultSet.getString("stuLastEightNum"));
            studentUser.setStuCheck(resultSet.getString("stuCheck"));
            studentUser.setStuOldWorkType(resultSet.getInt("stuOldWorkType"));
            studentUser.setStuEducational(resultSet.getInt("stuEducational"));
            studentUser.setStuIdentifiedNum(resultSet.getString("stuIdentifiedNum"));
            studentUser.setStudentStartDate(resultSet.getDate("studentStartDate"));
            studentUser.setStuPicture(resultSet.getString("stuPicture"));

            return studentUser;
        }
    }

    @Override
    public StudentUser selectCard(int stuId) {
        String sql = "SELECT stuId, stuName FROM lgb_student WHERE deleteFlag = 0 AND stuId = ?";
        Object[] args = {
                stuId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectCardRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    private class SelectCardRowMapper implements RowMapper<StudentUser> {

        @Override
        public StudentUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            StudentUser studentUser = new StudentUser();

            studentUser.setStuId(rs.getInt("stuId"));
            studentUser.setStuName(rs.getString("stuName"));

            return studentUser;
        }
    }

    @Override
    public boolean selectIdAndCard(StudentUser studentUser) {
        String sql = "SELECT COUNT(stuId) as NUM FROM lgb_student WHERE deleteFlag = 0 AND stuId = ? AND stuCardNum = ?";
        Object[] args = {
                studentUser.getStuId(),
                studentUser.getStuCardNum()
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, Integer.class) == 1 ? true : false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateCard(StudentUser studentUser) {
        String sql = "UPDATE lgb_student SET stuCardNum = ? WHERE stuId = ?";
        Object[] args = {
                studentUser.getStuCardNumNew(),
                studentUser.getStuId()
        };


        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }

    @Override
    public int selectCardNum(StudentUser studentUser) {
        String sql = "SELECT COUNT(stuId) as num FROM lgb_student WHERE deleteFlag = 0 AND stuCardNum = ?";
        Object[] args = {
                studentUser.getStuCardNum()
        };

        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, args);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<Course> select4Courses(int stuId) {
        String sql = "SELECT C.courseName, D.departmentName, SC.tuitionFlag FROM lgb_studentCourse SC LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_department D ON C.departmentId = D.departmentId WHERE SC.studentId = ?";
        Object[] args = {
                stuId
        };

        try {
            return jdbcTemplate.query(sql, args, new Select4CoursesRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private class Select4CoursesRowMapper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();

            course.setCourseName(rs.getString("courseName"));
            course.setDepartmentName(rs.getString("departmentName"));
            course.setTuitionFlag(rs.getInt("tuitionFlag"));

            return course;
        }
    }
}
