package com.lgb.function.admin.finance.refund;

import java.sql.Timestamp;

public class RefundStudentCourse {
    private int refundId;
    private int studentCourseId;
    private int courseId;
    private int studentId;
    private int refundMoney;
    private int refundTuitionFlag;
    private String refundUser;
    private Timestamp refundTime;
    private String courseName;
    private String studentName;

    public void setRefundId(int refundId) {
        this.refundId = refundId;
    }

    public void setStudentCourseId(int studentCourseId) {
        this.studentCourseId = studentCourseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setRefundMoney(int refundMoney) {
        this.refundMoney = refundMoney;
    }

    public void setRefundTuitionFlag(int refundTuitionFlag) {
        this.refundTuitionFlag = refundTuitionFlag;
    }

    public int getRefundId() {

        return refundId;
    }

    public int getStudentCourseId() {
        return studentCourseId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getRefundMoney() {
        return refundMoney;
    }

    public int getRefundTuitionFlag() {
        return refundTuitionFlag;
    }

    public void setRefundUser(String refundUser) {
        this.refundUser = refundUser;
    }

    public String getRefundUser() {

        return refundUser;
    }

    public void setRefundTime(Timestamp refundTime) {
        this.refundTime = refundTime;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Timestamp getRefundTime() {

        return refundTime;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getStudentName() {
        return studentName;
    }
}
