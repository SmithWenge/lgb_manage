package com.lgb.function.admin.student.repository;

import com.google.common.base.Optional;
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

            if (studentUser.getStuGender() != null) {
                sql.append(" AND stuGender = ?");
                list.add(studentUser.getStuGender());
            }

            if (studentUser.getStuBirthday() != null) {
                sql.append(" AND stuBirthday = ?");
                list.add(studentUser.getStuBirthday());
            }

            if (studentUser.getStuOldWorkPlaceType() != null) {
                sql.append(" AND stuOldWorkPlaceType = ?");
                list.add(studentUser.getStuOldWorkPlaceType());
            }

            if (studentUser.getStuType() != null) {
                sql.append(" AND stuType = ?");
                list.add(studentUser.getStuType());
            }

            if (studentUser.getStuOldWorkType() != null) {
                sql.append(" AND stuOldWorkType = ?");
                list.add(studentUser.getStuOldWorkType());
            }

            if (studentUser.getStuEducational() != null) {
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


        }

        Object[] args = list.toArray();

        sql.append(" ORDER BY stuId DESC");

        return repositoryUtils.select4Page(sql.toString(), pageable, args, new Query4PageRowMapper());
    }
    @Override
    public boolean insert(StudentUser studentUser) {
        String sql = "INSERT INTO lgb_student (stuId, stuCardNum, stuName, stuGender, stuTelOne, stuTelTwo, stuType, stuIdentifiedType, stuIdentifiedNum, stuOldWorkPlaceType, stuOldWorkPlaceName, stuPolitical, stuOldWorkType, stuNationality, stuBirthday, stuLastEightNum, stuCheck, stuHealth, stuLocation, stuEducational, stuLevel, stuSpeciality, stuPreferential, stuDependentsTel, stuDependentsDesc, stuRemarkOne, stuRemarkTwo, studentStartDate  ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                studentUser.getStudentStartDate()
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }

    @Override
    public StudentUser select(int stuId) {
        String sql = "SELECT stuId, stuCardNum, stuName, stuGender, stuTelOne, stuTelTwo, stuType, stuIdentifiedType, stuIdentifiedNum, stuOldWorkPlaceType, stuOldWorkPlaceName, stuPolitical, stuOldWorkType, stuNationality, stuBirthday, stuLastEightNum, stuCheck, stuHealth, stuLocation, stuEducational, stuLevel, stuSpeciality, stuPreferential, stuDependentsTel, stuDependentsDesc, stuRemarkOne, stuRemarkTwo, studentStartDate FROM lgb_student WHERE deleteFlag = 0 AND stuId = ?";
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
    public boolean update(StudentUser studentUser) {
        String sql = "UPDATE lgb_adminUser SET stuId =  ?, stuCardNum =  ?, stuName =  ?, stuGender =  ?, stuTelOne =  ?, stuTelTwo =  ?, stuType =  ?, stuIdentifiedType =  ?, stuIdentifiedNum =  ?, stuOldWorkPlaceType =  ?,stuOldWorkPlaceName =  ?, stuPolitical =  ?, stuOldWorkType =  ?, stuNationality =  ?, stuBirthday =  ?, stuLastEightNum =  ?, stuCheck =  ?, stuHealth =  ?, stuLocation =  ?, stuEducational =  ?, stuLevel =  ?, stuSpeciality =  ?, stuPreferential =  ?, stuDependentsTel =  ?, stuDependentsDesc =  ?, stuRemarkOne =  ?, stuRemarkTwo =  ?\n" +
                "\n ,studentStartDate = ? WHERE stuId = ?";
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
                studentUser.getStudentStartDate()

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
            studentUser.setStuGender(resultSet.getString("stuGender"));
            studentUser.setStuBirthday(resultSet.getDate("stuBirthday"));
            studentUser.setStuCardNum(resultSet.getString("stuCardNum"));
            studentUser.setStuTelOne(resultSet.getString("stuTelOne"));
            studentUser.setStuTelTwo(resultSet.getString("stuTelTwo"));



            return studentUser;
        }
    }

    private class Query4RowMapper implements RowMapper<StudentUser> {

        @Override
        public StudentUser mapRow(ResultSet resultSet, int i) throws SQLException {
            StudentUser studentUser = new StudentUser();
            studentUser.setStuId(resultSet.getInt("stuId"));
            studentUser.setStuName(resultSet.getString("stuName"));
            studentUser.setStuGender(resultSet.getString("stuGender"));
            studentUser.setStuTelOne(resultSet.getString("stuTelOne"));
            studentUser.setStuTelTwo(resultSet.getString("stuTelTwo"));
            studentUser.setStuIdentifiedType(resultSet.getInt("stuIdentifiedType"));
            studentUser.setStuCardNum(resultSet.getString("stuCardNum"));
            studentUser.setStuOldWorkPlaceType(resultSet.getString("stuOldWorkPlaceType"));
            studentUser.setStuType(resultSet.getString("stuType"));
            studentUser.setStuHealth(resultSet.getString("stuHealth"));
            studentUser.setStuLevel(resultSet.getString("stuLevel"));
            studentUser.setStuSpeciality(resultSet.getString("stuSpeciality"));
            studentUser.setStuPreferential(resultSet.getString("stuPreferential"));
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
            studentUser.setStuOldWorkType(resultSet.getString("stuOldWorkType"));
            studentUser.setStuEducational(resultSet.getString("stuEducational"));
            studentUser.setStuIdentifiedNum(resultSet.getString("stuIdentifiedNum"));
            studentUser.setStudentStartDate(resultSet.getDate("studentStartDate"));

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
}
