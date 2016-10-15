package com.lgb.function.support.student.card;

import java.sql.Timestamp;

/**
 * 学员考勤表Bean
 */
public class StudentCheck {
    private int checkId;
    private Timestamp checkTime;
    private String checkUserName;
    private String checkCardNum;
    private int checkFlag;

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public void setCheckTime(Timestamp checkTime) {
        this.checkTime = checkTime;
    }

    public void setCheckUserName(String checkUserName) {
        this.checkUserName = checkUserName;
    }

    public void setCheckCardNum(String checkCardNum) {
        this.checkCardNum = checkCardNum;
    }

    public int getCheckId() {

        return checkId;
    }

    public Timestamp getCheckTime() {
        return checkTime;
    }

    public String getCheckUserName() {
        return checkUserName;
    }

    public String getCheckCardNum() {
        return checkCardNum;
    }

    public void setCheckFlag(int checkFlag) {
        this.checkFlag = checkFlag;
    }

    public int getCheckFlag() {

        return checkFlag;
    }
}
