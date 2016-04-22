package com.lgb.function.admin.count.detail.repository;

import com.lgb.function.admin.student.StudentUser;
import com.lgb.function.support.dictionary.impl.DefaultDictionaryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CountDetailRepository implements CountDetailRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<StudentUser> selectStuYearStart(String value) {
        String sql = "SELECT stuId, stuCardNum,stuName,stuGender,stuBirthday,stuTelOne,stuTelTwo FROM lgb_student WHERE deleteFlag = 0 AND YEAR(studentStartDate) = ?";
        Object[] args = {
                value
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<StudentUser> selectStuGender(int value) {
        String sql = "SELECT stuId, stuCardNum,stuName,stuGender,stuBirthday,stuTelOne,stuTelTwo FROM lgb_student WHERE deleteFlag = 0 AND stuGender = ?";
        Object[] args = {
                value
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<StudentUser> selectStuEducational(int value) {
        String sql = "SELECT stuId, stuCardNum,stuName,stuGender,stuBirthday,stuTelOne,stuTelTwo FROM lgb_student WHERE deleteFlag = 0 AND stuEducational = ?";
        Object[] args = {
                value
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<StudentUser> selectStuOldWorkPlaceType(int value) {
        String sql = "SELECT stuId, stuCardNum,stuName,stuGender,stuBirthday,stuTelOne,stuTelTwo FROM lgb_student WHERE deleteFlag = 0 AND stuOldWorkPlaceType = ?";
        Object[] args = {
                value
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<StudentUser> selectStuOldWorkType(int value) {
        String sql = "SELECT stuId, stuCardNum,stuName,stuGender,stuBirthday,stuTelOne,stuTelTwo FROM lgb_student WHERE deleteFlag = 0 AND stuOldWorkType = ?";
        Object[] args = {
                value
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<StudentUser> selectStuPolitical(int value) {
        String sql = "SELECT stuId, stuCardNum,stuName,stuGender,stuBirthday,stuTelOne,stuTelTwo FROM lgb_student WHERE deleteFlag = 0 AND stuPolitical = ?";
        Object[] args = {
                value
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<StudentUser> selectStuPreferential(int value) {
        String sql = "SELECT stuId, stuCardNum,stuName,stuGender,stuBirthday,stuTelOne,stuTelTwo FROM lgb_student WHERE deleteFlag = 0 AND stuPreferential = ?";
        Object[] args = {
                value
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<StudentUser> selectStuType(int value) {
        String sql = "SELECT stuId, stuCardNum,stuName,stuGender,stuBirthday,stuTelOne,stuTelTwo FROM lgb_student WHERE deleteFlag = 0 AND stuType = ?";
        Object[] args = {
                value
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<StudentUser> selectStuBirthday(String value) {
        String sql = "SELECT stuId, stuCardNum,stuName,stuGender,stuBirthday,stuTelOne,stuTelTwo FROM lgb_student WHERE deleteFlag = 0 AND YEAR(StuBirthday) = ?";
        Object[] args = {
                value
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private class SelectRowMapper implements RowMapper<StudentUser> {
        DefaultDictionaryManager manager = DefaultDictionaryManager.getInstance();

        @Override
        public StudentUser mapRow(ResultSet resultSet, int i) throws SQLException {
            StudentUser studentUser = new StudentUser();

            studentUser.setStuId(resultSet.getInt("stuId"));
            studentUser.setStuName(resultSet.getString("stuName"));
            studentUser.setStuGenderValue(manager.dictionary(resultSet.getInt("stuGender"), "gender").getItemValue());
            studentUser.setStuBirthday(resultSet.getDate("stuBirthday"));
            studentUser.setStuCardNum(resultSet.getString("stuCardNum"));
            studentUser.setStuTelOne(resultSet.getString("stuTelOne"));
            studentUser.setStuTelTwo(resultSet.getString("stuTelTwo"));

            return studentUser;
        }
    }
}
