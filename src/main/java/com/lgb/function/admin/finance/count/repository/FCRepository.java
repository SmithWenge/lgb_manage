package com.lgb.function.admin.finance.count.repository;

import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.count.model.InfoCount;
import com.lgb.function.admin.finance.count.model.JsonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FCRepository implements FCRepositoryI{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public InfoCount querySumOfActualTuition() {
        try{
            String sql = "select SUM(actualTuition) AS sumActualTuition from lgb_studentCourse";
            return jdbcTemplate.queryForObject(sql,new TuitionRowMapper());
        } catch (Exception e) {
            InfoCount infoCount = new InfoCount();
            infoCount.setSumActualTuition(0);
            return infoCount;
        }

    }
    private class TuitionRowMapper implements RowMapper<InfoCount> {
        @Override
        public InfoCount mapRow(ResultSet resultSet, int i) throws SQLException {
            InfoCount infoCount = new InfoCount();
            infoCount.setSumActualTuition(resultSet.getInt("sumActualTuition"));
            return infoCount;
        }
    }

    @Override
    public InfoCount queryDaySumActualTuition() {
        try {
            String sql = "select SUM(actualTuition) AS daySumActualTuition from lgb_studentCourse WHERE DATE_FORMAT(financeTime,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d')";
            return jdbcTemplate.queryForObject(sql, new QueryDaySumActualTuitionRowMapper());
        }catch (Exception e){
            InfoCount infoCount = new InfoCount();
            infoCount.setDaySumActualTuition(0);
            return infoCount;
        }
    }

    private class QueryDaySumActualTuitionRowMapper implements RowMapper<InfoCount> {

        @Override
        public InfoCount mapRow(ResultSet resultSet, int i) throws SQLException {
            InfoCount infoCount = new InfoCount();
            infoCount.setDaySumActualTuition(resultSet.getInt("daySumActualTuition"));
            return  infoCount;
        }
    }

    @Override
    public List<JsonModel> queryMonthSumFinance(Finance finance) {
        String sql = "SELECT SUM(actualTuition) AS total, DATE_FORMAT(financeTime, '%Y-%m') AS groupMonth FROM lgb_studentCourse WHERE YEAR(financeTime) = ? GROUP BY groupMonth;";
        Object[] args = {
                finance.getCountFinanceYear()
        };

        return jdbcTemplate.query(sql,args,new MonthSunFinanceRowMapper());
    }

    private class MonthSunFinanceRowMapper implements RowMapper<JsonModel> {

        @Override
        public JsonModel mapRow(ResultSet resultSet, int i) throws SQLException {
            JsonModel jsonModel = new JsonModel();

            jsonModel.setValue(resultSet.getInt("total"));
            jsonModel.setName(resultSet.getString("groupMonth"));

            return jsonModel;
        }
    }

}
