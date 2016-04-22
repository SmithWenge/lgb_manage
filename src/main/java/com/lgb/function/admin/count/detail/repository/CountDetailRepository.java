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
    public List<StudentUser> selectStuYearStart(String year) {
        String sql = "SELECT stuId, stuCardNum,stuName,stuGender,stuBirthday,stuTelOne,stuTelTwo FROM lgb_student WHERE deleteFlag = 0 AND YEAR(studentStartDate) = ?";
        Object[] args = {
                year
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
