package com.lgb.function.admin.room.info.service;

import com.lgb.function.admin.room.info.RoomInfo;

import java.util.List;

public interface RoomInfoServiceI {
    RoomInfo detail(int roomId);
    List<RoomInfo> roomCourse(int roomId);
}
