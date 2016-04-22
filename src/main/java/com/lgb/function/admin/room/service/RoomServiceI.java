package com.lgb.function.admin.room.service;

import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.room.Room;

import java.util.List;

public interface RoomServiceI {
    List<Room> allRooms();
    List<Room> allTimes();
    List<Course> roomCourse(Room room);
}
