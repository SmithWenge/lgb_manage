package com.lgb.function.admin.sign;

import com.lgb.arc.Entry;

public class StudentCourse extends Entry {
    private int studentCourseId;
    private int studentId;
    private int courseId;
    private int courseDiscount;
    private String actualTuition;

    public int getStudentCourseId() {
        return studentCourseId;
    }

    public void setStudentCourseId(int studentCourseId) {
        this.studentCourseId = studentCourseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseDiscount() {
        return courseDiscount;
    }

    public void setCourseDiscount(int courseDiscount) {
        this.courseDiscount = courseDiscount;
    }

    public String getActualTuition() {
        return actualTuition;
    }

    public void setActualTuition(String actualTuition) {
        this.actualTuition = actualTuition;
    }
}
