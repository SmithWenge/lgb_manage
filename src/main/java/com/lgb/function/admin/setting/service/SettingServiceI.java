package com.lgb.function.admin.setting.service;

public interface SettingServiceI {
    String nowSettingColor();
    boolean config(String configColor, String logUser);
}
