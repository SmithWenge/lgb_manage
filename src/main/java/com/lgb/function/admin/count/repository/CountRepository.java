package com.lgb.function.admin.count.repository;

import com.lgb.function.admin.count.model.*;
import com.lgb.function.support.dictionary.IDictionaryManager;
import com.lgb.function.support.dictionary.impl.DefaultDictionaryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CountRepository implements CountRepositoryI {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<JsonModel> queryNumOfGender() {
        String sql = "select stuGender, count(stuId) as num from lgb_student group by stuGender";
        return jdbcTemplate.query(sql,new StuGenderRowMapper());
    }

    private class StuGenderRowMapper implements RowMapper<JsonModel> {
        private IDictionaryManager dictionaryManager = DefaultDictionaryManager.getInstance();

        @Override
        public JsonModel mapRow(ResultSet resultSet, int i) throws SQLException {

            JsonModel stuGender = new JsonModel();
            stuGender.setName(dictionaryManager.dictionary(resultSet.getInt("stuGender"), "gender").getItemValue());
            stuGender.setValue(resultSet.getInt("num"));
            return stuGender;
        }
    }

    @Override
    public List<JsonModel> queryNumOfEducational() {
        String sql = "select stuEducational,count(stuId) as num from lgb_student group by stuEducational";
        return jdbcTemplate.query(sql,new StuEducationalRowMapper());
    }

    private class StuEducationalRowMapper implements RowMapper<JsonModel> {
        private IDictionaryManager dictionaryManager = DefaultDictionaryManager.getInstance();
        @Override
        public JsonModel mapRow(ResultSet resultSet, int i) throws SQLException {
            JsonModel stuEducational = new JsonModel();
            stuEducational.setName(dictionaryManager.dictionary(resultSet.getInt("stuEducational"), "educational").getItemValue());
            stuEducational.setValue(resultSet.getInt("num"));
            return stuEducational;
        }
    }

    @Override
    public List<JsonModel> queryNumOfOldPlaceType() {
        String sql = "select stuOldWorkPlaceType,count(stuId) as num from lgb_student group by stuOldWorkPlaceType";
        return jdbcTemplate.query(sql,new StuOldWorkPlaceTypeRowMapper());
    }

    private class StuOldWorkPlaceTypeRowMapper implements RowMapper<JsonModel> {
        private IDictionaryManager dictionaryManager = DefaultDictionaryManager.getInstance();
        @Override
        public JsonModel mapRow(ResultSet resultSet, int i) throws SQLException {
            JsonModel stuOldWorkPlaceType = new JsonModel();
            stuOldWorkPlaceType.setName(dictionaryManager.dictionary(resultSet.getInt("stuOldWorkPlaceType"), "stuOldWorkPlaceType").getItemValue());
            stuOldWorkPlaceType.setValue(resultSet.getInt("num"));
            return stuOldWorkPlaceType;
        }
    }

    @Override
    public List<JsonModel> queryNumOfOldWorkType() {
        String sql = "select stuOldWorkType,count(stuId) as num from lgb_student group by stuOldWorkType";
        return jdbcTemplate.query(sql,new StuOldWorkTypeRowMapper());
    }
    private class StuOldWorkTypeRowMapper implements RowMapper<JsonModel> {
        private IDictionaryManager dictionaryManager = DefaultDictionaryManager.getInstance();
        @Override
        public JsonModel mapRow(ResultSet resultSet, int i) throws SQLException {
            JsonModel stuOldWorkType = new JsonModel();
            stuOldWorkType.setName(dictionaryManager.dictionary(resultSet.getInt("stuOldWorkType"), "stuOldWorkType").getItemValue());
            stuOldWorkType.setValue(resultSet.getInt("num"));
            return  stuOldWorkType;
        }
    }

    @Override
    public List<JsonModel> queryNumOfStuPolitical() {
        String sql = "select stuPolitical,count(stuId) as num from lgb_student group by stuPolitical";
        return jdbcTemplate.query(sql, new StuPoliticalRowMapper());
    }
    private class StuPoliticalRowMapper implements RowMapper<JsonModel> {
        private IDictionaryManager dictionaryManager = DefaultDictionaryManager.getInstance();
        @Override
        public JsonModel mapRow(ResultSet resultSet, int i) throws SQLException {
            JsonModel stuPolitical = new JsonModel();
            stuPolitical.setValue(resultSet.getInt("num"));
            stuPolitical.setName(dictionaryManager.dictionary(resultSet.getInt("stuPolitical"), "stuPolitical").getItemValue());
            return stuPolitical;
        }
    }
    @Override
    public List<JsonModel> queryNumOfStuPreferential() {
        String sql = "select stuPreferential,count(stuId) as num from lgb_student group by stuPreferential";
        return jdbcTemplate.query(sql,new StuPreferentialRowMapper());
    }

    private class StuPreferentialRowMapper implements RowMapper<JsonModel> {
        private IDictionaryManager dictionaryManager = DefaultDictionaryManager.getInstance();
        @Override
        public JsonModel mapRow(ResultSet resultSet, int i) throws SQLException {
            JsonModel stuPreferential = new JsonModel();
            stuPreferential.setName(dictionaryManager.dictionary(resultSet.getInt("stuPreferential"), "courseDiscount").getItemValue());
            stuPreferential.setValue(resultSet.getInt("num"));
            return stuPreferential;
        }
    }

    @Override
    public List<JsonModel> queryNumOfStuType() {
        String sql = "select stuType,count(stuId) as num from lgb_student group by stuType";
        return jdbcTemplate.query(sql,new StuTypeRowMapper());
    }

    private class StuTypeRowMapper implements RowMapper<JsonModel> {
        private IDictionaryManager dictionaryManager = DefaultDictionaryManager.getInstance();
        @Override
        public JsonModel mapRow(ResultSet resultSet, int i) throws SQLException {
            JsonModel stuType = new JsonModel();
            stuType.setName(dictionaryManager.dictionary(resultSet.getInt("stuType"), "stuType").getItemValue());
            stuType.setValue(resultSet.getInt("num"));
            return stuType;
        }
    }

    @Override
    public List<JsonModel> queryNumOfStuBirthday() {
        String sql = "select YEAR(stuBirthday) AS yearOfStuBirthday,count(stuId) as num from lgb_student group by YEAR(stuBirthday)";
        return jdbcTemplate.query(sql, new YearOfStuBirthdayRowMapper());
    }

    private class YearOfStuBirthdayRowMapper implements RowMapper<JsonModel> {
        @Override
        public JsonModel mapRow(ResultSet resultSet, int i) throws SQLException {
            JsonModel yearStuBirthday = new JsonModel();
            yearStuBirthday.setName(resultSet.getString("yearOfStuBirthday"));
            yearStuBirthday.setValue(resultSet.getInt("num"));
            return yearStuBirthday;
        }
    }

    @Override
    public InfoCount queryNumOfStudent() {
        String sql = "select count(stuId) as num from lgb_student";
        return jdbcTemplate.queryForObject(sql, new StuInfoCountRowMapper());
    }

    private class StuInfoCountRowMapper implements RowMapper<InfoCount> {
        @Override
        public InfoCount mapRow(ResultSet resultSet, int i) throws SQLException {
            InfoCount infoCount = new InfoCount();
            infoCount.setNumOfStudent(resultSet.getInt("num"));
            return infoCount;
        }
    }

    @Override
    public InfoCount queryNumOfTeacher() {
        String sql = "select count(teacherId) as num from lgb_teacher";
        return jdbcTemplate.queryForObject(sql,new TeaInfoCountRowMapper());
    }

    private class TeaInfoCountRowMapper implements RowMapper<InfoCount> {
        @Override
        public InfoCount mapRow(ResultSet resultSet, int i) throws SQLException {
            InfoCount infoCount = new InfoCount();
            infoCount.setNumOfTeacher(resultSet.getInt("num"));
            return infoCount;
        }
    }

    @Override
    public InfoCount queryNumOfCourse() {
        String sql = "select count(courseId) as num from lgb_course";
        return jdbcTemplate.queryForObject(sql,new CouInfoCountRowMapper());
    }

    private class CouInfoCountRowMapper implements RowMapper<InfoCount> {

        @Override
        public InfoCount mapRow(ResultSet resultSet, int i) throws SQLException {
            InfoCount infoCount = new InfoCount();
            infoCount.setNumOfCourse(resultSet.getInt("num"));
            return infoCount;
        }
    }

    @Override
    public List<JsonModel> queryNumOfStuEduStart() {

        String sql = "SELECT YEAR(studentStartDate) AS yearStuEduStart, COUNT(stuId) AS num FROM lgb_student GROUP BY YEAR(studentStartDate)";
        return jdbcTemplate.query(sql, new QueryNumOfStuEduStartRowMapper());
    }

    private class QueryNumOfStuEduStartRowMapper implements RowMapper<JsonModel> {

        @Override
        public JsonModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            JsonModel yearStuEduStart = new JsonModel();
            yearStuEduStart.setName(rs.getString("yearStuEduStart"));
            yearStuEduStart.setValue(rs.getInt("num"));
            return yearStuEduStart;
        }
    }

    /**
     * 查询学员级别统计分类
     *
     * @return
     */
    @Override
    public List<JsonModel> queryNumOfStuLevel() {
        String sql = "SELECT stuLevel, COUNT(stuId) AS num FROM lgb_student GROUP BY stuLevel";
        return jdbcTemplate.query(sql, new QueryNumOfStuLevelRowMapper());
    }

    private class QueryNumOfStuLevelRowMapper implements RowMapper<JsonModel> {

        @Override
        public JsonModel mapRow(ResultSet resultSet, int i) throws SQLException {
            JsonModel stuLevel = new JsonModel();

            stuLevel.setName(resultSet.getString("stuLevel"));
            stuLevel.setValue(resultSet.getInt("num"));

            return stuLevel;
        }
    }

}
