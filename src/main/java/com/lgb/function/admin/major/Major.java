package com.lgb.function.admin.major;

import com.lgb.arc.Entry;

public class Major extends Entry {
    private int majorId;
    private int departmentId;
    private String majorName;
    private String departmentName;

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {

        return departmentName;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public int getMajorId() {

        return majorId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getMajorName() {
        return majorName;
    }
}
