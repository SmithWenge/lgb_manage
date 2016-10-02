package com.lgb.function.admin.setting.repository;

import com.lgb.arc.utils.ConstantFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SettingRepository implements SettingRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String select4NowSettingColor() {
        String sql = "SELECT background_color_setting FROM lgb_config";

        try {
            return jdbcTemplate.queryForObject(sql, String.class);
        } catch (Exception e) {
            return ConstantFields.DEFAULT_BACKGROUND_COLOR_SETTING;
        }
    }

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
}
