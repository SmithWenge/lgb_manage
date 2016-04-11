package com.lgb.function.admin.room.service;

import com.lgb.function.admin.room.Room;
import com.lgb.function.admin.room.repository.RoomRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements RoomServiceI {
    @Autowired
    private RoomRepositoryI roomRepository;

    @Override
    public List<Room> allRooms() {
        return roomRepository.selectAll();
    }

    @Override
    public List<Room> allTimes() {
        return roomRepository.selectAllTime();
    }
}
