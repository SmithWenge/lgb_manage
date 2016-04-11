package com.lgb.function.admin.count.model;

public class InfoCount {
    private int numOfStudent;
    private int numOfTeacher;
    private int numOfCourse;
    private int sumActualTuition;
    private int daySumActualTuition;

    public int getDaySumActualTuition() {
        return daySumActualTuition;
    }

    public void setDaySumActualTuition(int daySumActualTuition) {
        this.daySumActualTuition = daySumActualTuition;
    }

    public int getSumActualTuition() {
        return sumActualTuition;
    }

    public void setSumActualTuition(int sumActualTuition) {
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
