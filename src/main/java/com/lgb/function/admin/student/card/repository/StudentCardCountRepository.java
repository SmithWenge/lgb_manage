package com.lgb.function.admin.student.card.repository;

import com.lgb.arc.utils.DateUtils;
import com.lgb.function.admin.count.model.JsonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentCardCountRepository implements StudentCardCountRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 当前年分月统计数据
     *
     * @return
     */
    @Override
    public List<JsonModel> select4NumOfStudentCard() {
        String sql = "SELECT MONTH(checkTime) AS monthNum, count(checkId) AS num FROM lgb_studentcheck WHERE YEAR(checkTime) = ? GROUP BY MONTH(checkTime)";
        Object[] args = {
                DateUtils.nowYear()
        };

        return jdbcTemplate.query(sql, args, new Select4NumOfStudentCardRowMapper());
    }

    private class Select4NumOfStudentCardRowMapper implements RowMapper<JsonModel> {

        @Override
        public JsonModel mapRow(ResultSet rs, int rowNum) throws SQLException {

            JsonModel jsonModel = new JsonModel();

            jsonModel.setName(String.valueOf(rs.getInt("monthNum") + "月"));
            jsonModel.setValue(rs.getInt("num"));

            return jsonModel;
        }
    }
}
