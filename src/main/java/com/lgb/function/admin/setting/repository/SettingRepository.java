package com.lgb.function.admin.setting.repository;

import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.setting.LGBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class SettingRepository implements SettingRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 选择现在颜色
     *
     * @return
     */
    @Override
    public String select4NowSettingColor() {
        String sql = "SELECT background_color_setting FROM lgb_config";

        try {
            return jdbcTemplate.queryForObject(sql, String.class);
        } catch (Exception e) {
            return ConstantFields.DEFAULT_BACKGROUND_COLOR_SETTING;
        }
    }

    /**
     * 更新工作背景色
     *
     * @param configColor
     * @return
     */
    @Override
    public boolean updateConfig(String configColor) {
        String sql = "UPDATE lgb_config SET background_color_setting = ? WHERE configId = ?";
        Object[] args = {
                configColor,
                ConstantFields.DEFAULT_CONFIG_ID
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 查看现在的基本配置
     *
     * @param defaultConfigId
     * @return
     */
    @Override
    public LGBConfig selectBasicConfig(int defaultConfigId) {
        String sql = "SELECT studentCourseLimit, financeMessage, refundMessage FROM lgb_config WHERE configId = ?";
        Object[] args = {
                defaultConfigId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectBasicConfigRowMapper());
        } catch (Exception e) {
            return new LGBConfig();
        }
    }

    private class SelectBasicConfigRowMapper implements RowMapper<LGBConfig> {

        @Override
        public LGBConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
            LGBConfig config = new LGBConfig();

            config.setStudentCourseLimit(rs.getInt("studentCourseLimit"));
            config.setFinanceMessage(rs.getString("financeMessage"));
            config.setRefundMessage(rs.getString("refundMessage"));

            return config;
        }
    }

    /**
     * 更新基本配置
     *
     * @param config
     * @return
     */
    @Override
    public boolean updateBasicConfig(LGBConfig config) {
        String sql = "UPDATE lgb_config SET studentCourseLimit = ?, financeMessage = ?, refundMessage = ? WHERE configId = ?";
        Object[] args = {
                config.getStudentCourseLimit(),
                config.getFinanceMessage(),
                config.getRefundMessage(),
                ConstantFields.DEFAULT_CONFIG_ID
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            return false;
        }
    }
}
