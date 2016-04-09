package com.lgb.function.admin.course.time;

import com.lgb.arc.Entry;

public class CourseTime extends Entry{
    private int courseTimeId;
    private int timeWeek;
    private String timeSpecific;
    private int courseRoom;
    private int courseId;
    private int timeSpecificInt;

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseId() {

        return courseId;
    }

    public CourseTime(int timeWeek, String timeSpecific, int courseRoom, int courseId) {
        this.timeWeek = timeWeek;
        this.timeSpecific = timeSpecific;
        this.courseRoom = courseRoom;
        this.courseId = courseId;
    }

    public void setTimeSpecificInt(int timeSpecificInt) {
        this.timeSpecificInt = timeSpecificInt;
    }

    public int getTimeSpecificInt() {

        return timeSpecificInt;
    }

    public CourseTime() {
    }

    public void setCourseTimeId(int courseTimeId) {
        this.courseTimeId = courseTimeId;
    }

    public void setTimeWeek(int timeWeek) {
        this.timeWeek = timeWeek;
    }

    public void setTimeSpecific(String timeSpecific) {
        this.timeSpecific = timeSpecific;
    }

    public void setCourseRoom(int courseRoom) {
        this.courseRoom = courseRoom;
    }

    public int getCourseTimeId() {

        return courseTimeId;
    }

    public int getTimeWeek() {
        return timeWeek;
    }

    public String getTimeSpecific() {
        return timeSpecific;
    }

    public int getCourseRoom() {
        return courseRoom;
    }
}
