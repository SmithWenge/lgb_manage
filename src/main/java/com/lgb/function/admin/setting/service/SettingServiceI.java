package com.lgb.function.admin.setting.service;

import com.lgb.function.admin.setting.LGBConfig;

public interface SettingServiceI {
    String nowSettingColor();
    boolean config(String configColor, String logUser);
    LGBConfig basicConfig(int defaultConfigId);
    boolean updateBasicConfig(LGBConfig config, String logUser);
}
