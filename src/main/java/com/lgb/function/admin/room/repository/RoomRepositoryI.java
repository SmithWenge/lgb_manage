package com.lgb.function.admin.room.repository;

import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.room.Room;

import java.util.List;

public interface RoomRepositoryI {
    List<Room> selectAll();
    List<Room> selectAllTime();
    List<Course> selectRoomCourse(Room room);
}
