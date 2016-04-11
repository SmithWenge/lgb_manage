package com.lgb.function.admin.room;

import com.lgb.arc.Entry;

public class Room extends Entry {
    private int roomId;
    private int courseId;
    private int timeWeek;
    private String timeSpecific;

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomId() {

        return roomId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setTimeWeek(int timeWeek) {
        this.timeWeek = timeWeek;
    }

    public void setTimeSpecific(String timeSpecific) {
        this.timeSpecific = timeSpecific;
    }

    public int getCourseId() {

        return courseId;
    }

    public int getTimeWeek() {
        return timeWeek;
    }

    public String getTimeSpecific() {
        return timeSpecific;
    }
}
