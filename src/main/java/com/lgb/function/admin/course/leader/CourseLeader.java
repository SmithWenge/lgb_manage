package com.lgb.function.admin.course.leader;

import com.lgb.arc.Entry;

import java.util.Date;

public class CourseLeader extends Entry {
    private String cardNum;
    private String leaderName;
    private int classRoom;
    private String departmentName;
    private String majorName;
    private String courseName;
    private int gender;
    private Date birthday;
    private String telOne;
    private String telTwo;
    private int siteNum;

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public void setClassRoom(int classRoom) {
        this.classRoom = classRoom;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setTelOne(String telOne) {
        this.telOne = telOne;
    }

    public void setTelTwo(String telTwo) {
        this.telTwo = telTwo;
    }

    public void setSiteNum(int siteNum) {
        this.siteNum = siteNum;
    }

    public String getCardNum() {

        return cardNum;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public int getClassRoom() {
        return classRoom;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getMajorName() {
        return majorName;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getGender() {
        return gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getTelOne() {
        return telOne;
    }

    public String getTelTwo() {
        return telTwo;
    }

    public int getSiteNum() {
        return siteNum;
    }
}
