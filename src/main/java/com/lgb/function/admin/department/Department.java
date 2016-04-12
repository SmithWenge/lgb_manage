package com.lgb.function.admin.department;

import com.lgb.arc.Entry;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Department extends Entry {
    private int departmentId;
    private String departmentName;
    private int departmentCourseNum;
    private int departmentStuNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date departmentStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date departmentStopDate;
    private int adminId;
    private String adminName;

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public int getAdminId() {

        return adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setDepartmentCourseNum(int departmentCourseNum) {
        this.departmentCourseNum = departmentCourseNum;
    }

    public void setDepartmentStuNum(int departmentStuNum) {
        this.departmentStuNum = departmentStuNum;
    }

    public void setDepartmentStartDate(Date departmentStartDate) {
        this.departmentStartDate = departmentStartDate;
    }

    public void setDepartmentStopDate(Date departmentStopDate) {
        this.departmentStopDate = departmentStopDate;
    }

    public int getDepartmentId() {

        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public int getDepartmentCourseNum() {
        return departmentCourseNum;
    }

    public int getDepartmentStuNum() {
        return departmentStuNum;
    }

    public Date getDepartmentStartDate() {
        return departmentStartDate;
    }

    public Date getDepartmentStopDate() {
        return departmentStopDate;
    }
}
