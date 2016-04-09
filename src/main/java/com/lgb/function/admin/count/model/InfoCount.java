package com.lgb.function.admin.count.model;

public class InfoCount {
    private int numOfStudent;
    private int numOfTeacher;
    private int numOfCourse;
    private int sumActualTuition;

    public int getActualTuition() {
        return sumActualTuition;
    }

    public void setActualTuition(int sumActualTuition) {
        this.sumActualTuition = sumActualTuition;
    }

    public int getNumOfStudent() {
        return numOfStudent;
    }

    public void setNumOfStudent(int numOfStudent) {
        this.numOfStudent = numOfStudent;
    }

    public int getNumOfTeacher() {
        return numOfTeacher;
    }

    public void setNumOfTeacher(int numOfTeacher) {
        this.numOfTeacher = numOfTeacher;
    }

    public int getNumOfCourse() {
        return numOfCourse;
    }

    public void setNumOfCourse(int numOfCourse) {
        this.numOfCourse = numOfCourse;
    }

}
