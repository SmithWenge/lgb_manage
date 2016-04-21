package com.lgb.function.admin.room.info.service;

import com.lgb.function.admin.room.info.RoomInfo;
import com.lgb.function.admin.room.info.repository.RoomInfoRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoomInfoService implements RoomInfoServiceI {
    @Autowired
    private RoomInfoRepositoryI roomInfoRepository;

    @Override
    public RoomInfo detail(int roomId) {
        return roomInfoRepository.select(roomId);
    }

    @Override
    public List<RoomInfo> roomCourse(int roomId) {
        return roomInfoRepository.selectRoomCourses(roomId);
    }


}
