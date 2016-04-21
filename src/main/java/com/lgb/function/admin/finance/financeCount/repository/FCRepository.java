package com.lgb.function.admin.finance.financeCount.repository;

import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.financeCount.model.InfoCount;
import com.lgb.function.admin.finance.financeCount.model.JsonModel;
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
        String sql = "select m,sum(case when month(financeTime)=m then  1 else 0 end) as number from lgb_studentcourse lgb,(select 1 m union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9 union all select 10 union all select 11 union all select 12) mon where ? = year(financeTime) group by m ORDER BY m ASC";
        Object[] args = {finance.getCountFinanceYear()};
        return jdbcTemplate.query(sql,args,new MonthSunFinanceRowMapper());
    }

    private class MonthSunFinanceRowMapper implements RowMapper<JsonModel> {

        @Override
        public JsonModel mapRow(ResultSet resultSet, int i) throws SQLException {
            JsonModel jsonModel = new JsonModel();
            jsonModel.setValue(resultSet.getInt("number"));
            jsonModel.setName(resultSet.getString("m"));
            return jsonModel;
        }
    }

}
