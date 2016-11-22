package com.lgb.function.admin.department;

import com.lgb.arc.Entry;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;

public class Department extends Entry {
    private int departmentId;
    private String departmentName;
    private int departmentCourseNum;
    private int departmentStuNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String departmentStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String departmentStopDate;
    private int adminId;
    private String adminName;
    private int courseNum;
    private int studentNum;

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public int getCourseNum() {

        return courseNum;
    }

    public int getStudentNum() {
        return studentNum;
    }

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

    public void setDepartmentStartDate(String departmentStartDate) {
        this.departmentStartDate = departmentStartDate;
    }

    public void setDepartmentStopDate(String departmentStopDate) {
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

    public String getDepartmentStartDate() {
        return departmentStartDate;
    }

    public String getDepartmentStopDate() {
        return departmentStopDate;
    }
}
