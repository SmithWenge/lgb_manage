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
    public List<StuGender> queryNumOfGender() {
        String sql = "select stuGender, count(stuId) as num from lgb_student group by stuGender";
        return jdbcTemplate.query(sql,new StuGenderRowMapper());
    }

    private class StuGenderRowMapper implements RowMapper<StuGender> {
        private IDictionaryManager dictionaryManager = DefaultDictionaryManager.getInstance();

        @Override
        public StuGender mapRow(ResultSet resultSet, int i) throws SQLException {

            StuGender stuGender = new StuGender();
            stuGender.setStuGender(dictionaryManager.dictionary(resultSet.getInt("stuGender"), "gender").getItemValue());
            stuGender.setNum(resultSet.getInt("num"));
            return stuGender;
        }
    }

    @Override
    public List<StuEducational> queryNumOfEducational() {
        String sql = "select stuEducational,count(stuId) as num from lgb_student group by stuEducational";
        return jdbcTemplate.query(sql,new StuEducationalRowMapper());
    }

    private class StuEducationalRowMapper implements RowMapper<StuEducational> {
        private IDictionaryManager dictionaryManager = DefaultDictionaryManager.getInstance();
        @Override
        public StuEducational mapRow(ResultSet resultSet, int i) throws SQLException {
            StuEducational stuEducational = new StuEducational();
            stuEducational.setStuEduCational(dictionaryManager.dictionary(resultSet.getInt("stuEducational"), "educational").getItemValue());
            stuEducational.setNum(resultSet.getInt("num"));
            return stuEducational;
        }
    }

    @Override
    public List<StuOldWorkPlaceType> queryNumOfOldPlaceType() {
        String sql = "select stuOldWorkPlaceType,count(stuId) as num from lgb_student group by stuOldWorkPlaceType";
        return jdbcTemplate.query(sql,new StuOldWorkPlaceTypeRowMapper());
    }

    private class StuOldWorkPlaceTypeRowMapper implements RowMapper<StuOldWorkPlaceType> {
        private IDictionaryManager dictionaryManager = DefaultDictionaryManager.getInstance();
        @Override
        public StuOldWorkPlaceType mapRow(ResultSet resultSet, int i) throws SQLException {
            StuOldWorkPlaceType stuOldWorkPlaceType = new StuOldWorkPlaceType();
            stuOldWorkPlaceType.setStuOldWorkPlaceType(dictionaryManager.dictionary(resultSet.getInt("stuOldWorkPlaceType"), "stuOldWorkPlaceType").getItemValue());
            stuOldWorkPlaceType.setNum(resultSet.getInt("num"));
            return stuOldWorkPlaceType;
        }
    }

    @Override
    public List<StuOldWorkType> queryNumOfOldWorkType() {
        String sql = "select stuOldWorkType,count(stuId) as num from lgb_student group by stuOldWorkType";
        return jdbcTemplate.query(sql,new StuOldWorkTypeRowMapper());
    }
    private class StuOldWorkTypeRowMapper implements RowMapper<StuOldWorkType> {
        private IDictionaryManager dictionaryManager = DefaultDictionaryManager.getInstance();
        @Override
        public StuOldWorkType mapRow(ResultSet resultSet, int i) throws SQLException {
            StuOldWorkType stuOldWorkType = new StuOldWorkType();
            stuOldWorkType.setStuOldWorkType(dictionaryManager.dictionary(resultSet.getInt("stuOldWorkType"), "stuOldWorkType").getItemValue());
            stuOldWorkType.setNum(resultSet.getInt("num"));
            return  stuOldWorkType;
        }
    }

    @Override
    public List<StuPolitical> queryNumOfStuPolitical() {
        String sql = "select stuPolitical,count(stuId) as num from lgb_student group by stuPolitical";
        return jdbcTemplate.query(sql, new StuPoliticalRowMapper());
    }
    private class StuPoliticalRowMapper implements RowMapper<StuPolitical> {
        private IDictionaryManager dictionaryManager = DefaultDictionaryManager.getInstance();
        @Override
        public StuPolitical mapRow(ResultSet resultSet, int i) throws SQLException {
            StuPolitical stuPolitical = new StuPolitical();
            stuPolitical.setStuPolitical(dictionaryManager.dictionary(resultSet.getInt("stuPolitical"), "stuPolitical").getItemValue());
            return stuPolitical;
        }
    }
    @Override
    public List<StuPreferential> queryNumOfStuPreferential() {
        String sql = "select stuPreferential,count(stuId) as num from lgb_student group by stuPreferential";
        return jdbcTemplate.query(sql,new StuPreferentialRowMapper());
    }

    private class StuPreferentialRowMapper implements RowMapper<StuPreferential> {
        private IDictionaryManager dictionaryManager = DefaultDictionaryManager.getInstance();
        @Override
        public StuPreferential mapRow(ResultSet resultSet, int i) throws SQLException {
            StuPreferential stuPreferential = new StuPreferential();
            stuPreferential.setStuPreferential(dictionaryManager.dictionary(resultSet.getInt("stuPreferential"), "courseDiscount").getItemValue());
            return stuPreferential;
        }
    }

    @Override
    public List<StuType> queryNumOfStuType() {
        String sql = "select stuType,count(stuId) as num from lgb_student group by stuType";
        return jdbcTemplate.query(sql,new StuTypeRowMapper());
    }

    private class StuTypeRowMapper implements RowMapper<StuType> {
        private IDictionaryManager dictionaryManager = DefaultDictionaryManager.getInstance();
        @Override
        public StuType mapRow(ResultSet resultSet, int i) throws SQLException {
            StuType stuType = new StuType();
            stuType.setStuType(dictionaryManager.dictionary(resultSet.getInt("stuType"), "stuType").getItemValue());
            stuType.setNum(resultSet.getInt("num"));
            return stuType;
        }
    }

    @Override
    public List<YearStuBirthday> queryNumOfStuBirthday() {
        String sql = "select YEAR(stuBirthday) AS yearOfStuBirthday,count(stuId) as num from lgb_student group by YEAR(stuBirthday)";
        return jdbcTemplate.query(sql, new YearOfStuBirthdayRowMapper());
    }

    private class YearOfStuBirthdayRowMapper implements RowMapper<YearStuBirthday> {
        @Override
        public YearStuBirthday mapRow(ResultSet resultSet, int i) throws SQLException {
            YearStuBirthday yearStuBirthday = new YearStuBirthday();
            yearStuBirthday.setYearStuBirthday(resultSet.getString("yearStuBirthday"));
            yearStuBirthday.setNum(resultSet.getInt("num"));
            return yearStuBirthday;
        }
    }

    @Override
    public InfoCount queryNumOfStudent() {
        String sql = "select count(stuId) as num from lgb_student";
        return jdbcTemplate.queryForObject(sql,new StuInfoCountRowMapper());
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
    public InfoCount querySumOfActualTuition() {
        String sql = "select sum(actualTuition) AS sumActualTuition from lgb_studentcourse group by studentCourseId,studentId";
        return jdbcTemplate.queryForObject(sql,new TuitionRowMapper());
    }

    private class TuitionRowMapper implements RowMapper<InfoCount> {
        @Override
        public InfoCount mapRow(ResultSet resultSet, int i) throws SQLException {
            InfoCount infoCount = new InfoCount();
            infoCount.setActualTuition(resultSet.getInt("sumActualTuition"));
            return infoCount;
        }
    }

    @Override
    public List<YearStuEduStart> queryNumOfStuEduStart() {
        String sql = "SELECT YEAR(studentStartDate) AS yearStuEduStart, COUNT(stuId) AS num FROM lgb_student GROUP BY YEAR(studentStartDate)";
        return jdbcTemplate.query(sql, new QueryNumOfStuEduStartRowMapper());
    }

    private class QueryNumOfStuEduStartRowMapper implements RowMapper<YearStuEduStart> {

        @Override
        public YearStuEduStart mapRow(ResultSet rs, int rowNum) throws SQLException {
            YearStuEduStart yearStuEduStart = new YearStuEduStart();

            yearStuEduStart.setName(rs.getString("yearStuEduStart"));
            yearStuEduStart.setValue(rs.getInt("num"));

            return yearStuEduStart;
        }
    }
}
