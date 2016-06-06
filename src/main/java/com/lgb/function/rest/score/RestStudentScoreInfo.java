package com.lgb.function.rest.score;

public class RestStudentScoreInfo {
    private String studentCardNum;
    private String stuName;
    private String stuScore;
    private String courseName;

    public String getStudentCardNum() {
        return studentCardNum;
    }

    public void setStudentCardNum(String studentCardNum) {
        this.studentCardNum = studentCardNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuScore() {
        return stuScore;
    }

    public void setStuScore(String stuScore) {
        this.stuScore = stuScore;
    }
}
