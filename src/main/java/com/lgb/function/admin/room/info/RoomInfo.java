package com.lgb.function.admin.room.info;

public class RoomInfo {
    private int roomId;
    private String roomName;
    private int roomFloor;
    private int roomCapacity;
    private int timeWeek;
    private String timeSpecific;
    private int courseRoom;
    private int courseId;
    private String courseName;

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {

        return courseName;
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

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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

    public int getCourseId() {
        return courseId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setRoomFloor(int roomFloor) {
        this.roomFloor = roomFloor;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public void setRoomRemark(String roomRemark) {
        this.roomRemark = roomRemark;
    }

    public String getRoomRemark() {

        return roomRemark;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getRoomFloor() {
        return roomFloor;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    private String roomRemark;
}
