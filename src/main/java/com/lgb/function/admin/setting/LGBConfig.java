package com.lgb.function.admin.setting;

public class LGBConfig {
    private int configId;
    private String backgroundColorSetting;
    private int studentCourseLimit;
    private String financeMessage;
    private String refundMessage;

    public void setConfigId(int configId) {
        this.configId = configId;
    }

    public void setBackgroundColorSetting(String backgroundColorSetting) {
        this.backgroundColorSetting = backgroundColorSetting;
    }

    public void setStudentCourseLimit(int studentCourseLimit) {
        this.studentCourseLimit = studentCourseLimit;
    }

    public void setFinanceMessage(String financeMessage) {
        this.financeMessage = financeMessage;
    }

    public void setRefundMessage(String refundMessage) {
        this.refundMessage = refundMessage;
    }

    public int getConfigId() {

        return configId;
    }

    public String getBackgroundColorSetting() {
        return backgroundColorSetting;
    }

    public int getStudentCourseLimit() {
        return studentCourseLimit;
    }

    public String getFinanceMessage() {
        return financeMessage;
    }

    public String getRefundMessage() {
        return refundMessage;
    }
}
