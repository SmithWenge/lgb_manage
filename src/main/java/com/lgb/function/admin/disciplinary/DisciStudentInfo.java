package com.lgb.function.admin.disciplinary;

import com.lgb.arc.Entry;

public class DisciStudentInfo extends Entry {
    private int disciplinaryId;
    private int stuId;
    private String stuName;
    private String stuCardNum;

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
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
}
