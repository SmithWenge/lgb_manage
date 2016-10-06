package com.lgb.function.admin.setting.service;

import com.lgb.function.admin.setting.LGBConfig;
import com.lgb.function.admin.setting.repository.SettingRepositoryI;
import com.lgb.function.support.log.LogContent;
import com.lgb.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingService implements SettingServiceI {
    @Autowired
    private SettingRepositoryI settingRepository;
    @Autowired
    private LogRepositoryI logRepository;

    @Override
    public String nowSettingColor() {
        return settingRepository.select4NowSettingColor();
    }

    @Override
    public boolean config(String configColor, String logUser) {
        boolean tmp = settingRepository.updateConfig(configColor);

        if (tmp) {
            LogContent logContent = new LogContent(logUser, "修改背景颜色为" + configColor, 1, 3);
            logRepository.insertLog(logContent);
        }

        return tmp;
    }

    @Override
    public LGBConfig basicConfig(int defaultConfigId) {
        return settingRepository.selectBasicConfig(defaultConfigId);
    }

    @Override
    public boolean updateBasicConfig(LGBConfig config, String logUser) {
        boolean tmp = settingRepository.updateBasicConfig(config);

        if (tmp) {
            LogContent logContent = new LogContent(logUser, "修改基本配置", 1, 3);
            logRepository.insertLog(logContent);
        }

        return tmp;
    }
}
