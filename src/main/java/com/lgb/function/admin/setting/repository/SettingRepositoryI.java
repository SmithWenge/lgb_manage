package com.lgb.function.admin.setting.repository;

import com.lgb.function.admin.setting.LGBConfig;

public interface SettingRepositoryI {
    String select4NowSettingColor();
    boolean updateConfig(String configColor);
    LGBConfig selectBasicConfig(int defaultConfigId);
    boolean updateBasicConfig(LGBConfig config);
}
