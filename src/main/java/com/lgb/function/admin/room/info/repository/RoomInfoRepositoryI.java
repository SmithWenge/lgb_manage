package com.lgb.function.admin.room.info.repository;

import com.lgb.function.admin.room.info.RoomInfo;

import java.util.List;

public interface RoomInfoRepositoryI {
    RoomInfo select(int roomId);
    List<RoomInfo> selectRoomCourses(int roomId);
}
