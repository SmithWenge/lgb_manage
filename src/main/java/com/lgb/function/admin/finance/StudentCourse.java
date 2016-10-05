package com.lgb.function.admin.finance;

public class StudentCourse {
    private int studentCourseId;
    private int studentId;
    private int courseId;

    public void setStudentCourseId(int studentCourseId) {
        this.studentCourseId = studentCourseId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentCourseId() {

        return studentCourseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }
}
