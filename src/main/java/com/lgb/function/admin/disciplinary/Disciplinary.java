package com.lgb.function.admin.disciplinary;

import com.lgb.arc.Entry;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Disciplinary extends Entry {
    private int stuId;
    private int disciplinaryId;
    private String stuName;
    private String stuCardNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date disciTime;
    private int disciCount;
    private String disciCause;
    private String stuTelOne;
    private int disciSum;

    public int getDisciSum() {
        return disciSum;
    }

    public void setDisciSum(int disciSum) {
        this.disciSum = disciSum;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuTelOne() {
        return stuTelOne;
    }

    public void setStuTelOne(String stuTelOne) {
        this.stuTelOne = stuTelOne;
    }

    public int getDisciplinaryId() {
        return disciplinaryId;
    }

    public void setDisciplinaryId(int disciplinaryId) {
        this.disciplinaryId = disciplinaryId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuCardNum() {
        return stuCardNum;
    }

    public void setStuCardNum(String stuCardNum) {
        this.stuCardNum = stuCardNum;
    }

    public Date getDisciTime() {
        return disciTime;
    }

    public void setDisciTime(Date disciTime) {
        this.disciTime = disciTime;
    }

    public int getDisciCount() {
        return disciCount;
    }

    public void setDisciCount(int disciCount) {
        this.disciCount = disciCount;
    }

    public String getDisciCause() {
        return disciCause;
    }

    public void setDisciCause(String disciCause) {
        this.disciCause = disciCause;
    }
}
