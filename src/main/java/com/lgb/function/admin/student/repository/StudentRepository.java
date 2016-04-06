package com.lgb.function.admin.student.repository;

import com.lgb.function.admin.student.StudentUser;
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

/**
 * Created by Samuel on 16/4/6.
 */
@Repository
public class StudentRepository implements StudentRepositoryI {
    @Autowired
    private RepositoryUtils<StudentUser> repositoryUtils;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Page<StudentUser> query4Page(Pageable pageable) {
        String sql = "SELECT stuID, stuCardNum,stuName,stuGender,stuTelOne,stuTelTwo FROM lgb_adminUser WHERE deleteFlag = 0 ORDER BY stuID";

        return repositoryUtils.select4Page(sql, pageable, null, new Query4PageRowMapper());
    }
    @Override
    public boolean insert(StudentUser studentUser) {
        String sql = "INSERT INTO lgb_student (stuId, stuCardNum, stuName, stuGender, stuTelOne, stuTelTwo, stuType, stuIdentifiedType, stuIdentifiedNum, stuOldWorkPlaceType, stuOldWorkPlaceName, stuPolitical, stuOldWorkType, stuNationality, stuBirthday, stuLastEightNum, stuCheck, stuHealth, stuLocation, stuEducational, stuLevel, stuSpeciality, stuPreferential, stuDependentsTel, stuDependentsDesc, stuRemarkOne, stuRemarkTwo   ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {
                studentUser.getStuID(),
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
                studentUser.getStuRemarkTwo()
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }

    @Override
    public StudentUser select(int stuID) {
        String sql = "SELECT stuId, stuName, stuGender, stuCardNum, stuOldWorkPlaceType, stuType, stuBirthday, stuOldWorkType, stuEducational, stuIdentifiedNum, FROM lgb_student WHERE deleteFlag = 0 AND stuId = ?";
        Object[] args = {
                stuID
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new Query4PageRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(StudentUser studentUser) {
        String sql = "UPDATE lgb_adminUser SET stuId =  ?, stuCardNum =  ?, stuName =  ?, stuGender =  ?, stuTelOne =  ?, stuTelTwo =  ?, stuType =  ?, stuIdentifiedType =  ?, stuIdentifiedNum =  ?, stuOldWorkPlaceType =  ?,stuOldWorkPlaceName =  ?, stuPolitical =  ?, stuOldWorkType =  ?, stuNationality =  ?, stuBirthday =  ?, stuLastEightNum =  ?, stuCheck =  ?, stuHealth =  ?, stuLocation =  ?, stuEducational =  ?, stuLevel =  ?, stuSpeciality =  ?, stuPreferential =  ?, stuDependentsTel =  ?, stuDependentsDesc =  ?, stuRemarkOne =  ?, stuRemarkTwo =  ?\n" +
                "\n WHERE stuId = ?";
        Object[] args = {
                studentUser.getStuID(),
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
                studentUser.getStuRemarkTwo()
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

            studentUser.setStuID(resultSet.getInt("stuID"));
            studentUser.setStuCardNum(resultSet.getString("stuCardNum"));
            studentUser.setStuName(resultSet.getString("stuName"));
            studentUser.setStuGender(resultSet.getString("stuGender"));
            studentUser.setStuTelOne(resultSet.getString("stuTelOne"));
            studentUser.setStuTelTwo(resultSet.getString("stuTelTwo"));



            return studentUser;
        }
    }
}
