package com.lgb.function.admin.course;

import java.util.Date;

public class CourseSite {
    private String courseName;
    private String courseRoom;
    private int siteNum;
    private String stuName;
    private Date stuBirthday;
    private String departmentName;
    private String majorName;

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseRoom(String courseRoom) {
        this.courseRoom = courseRoom;
    }

    public void setSiteNum(int siteNum) {
        this.siteNum = siteNum;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public void setStuBirthday(Date stuBirthday) {
        this.stuBirthday = stuBirthday;
    }

    public String getCourseName() {

        return courseName;
    }

    public String getCourseRoom() {
        return courseRoom;
    }

    public int getSiteNum() {
        return siteNum;
    }

    public String getStuName() {
        return stuName;
    }

    public Date getStuBirthday() {
        return stuBirthday;
    }
}
