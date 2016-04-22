package com.lgb.function.admin.room.manage.service;

import com.google.common.base.Optional;
import com.lgb.function.admin.login.AdminUser;
import com.lgb.function.admin.room.info.RoomInfo;
import com.lgb.function.admin.room.manage.repository.RoomManageRepositoryI;
import com.lgb.function.support.log.LogContent;
import com.lgb.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoomManageService implements RoomManageServiceI {

    @Autowired
    private RoomManageRepositoryI roomManageRepository;
    @Autowired
    private LogRepositoryI logRepository;

    @Override
    public Page<RoomInfo> query4Page(RoomInfo roomInfo, Pageable pageable) {
        return roomManageRepository.select4Page(roomInfo, pageable);
    }

    @Override
    public RoomInfo select(int roomId) {
        return roomManageRepository.select(roomId);
    }

    @Override
    public boolean edit(RoomInfo roomInfo, String logUser) {
        RoomInfo info = roomManageRepository.select(roomInfo.getRoomId());

        Optional<RoomInfo> optional = Optional.fromNullable(info);

        if (optional.isPresent()) {
            boolean tmp = roomManageRepository.update(roomInfo);

            if (tmp) {
                LogContent logContent = new LogContent(logUser, "编辑教室" + roomInfo.getRoomName(), 1, 4);
                logRepository.insertLog(logContent);
            }

            return tmp;
        }

        return false;
    }

    @Override
    public boolean delete(int roomId, String logUser) {
        RoomInfo info = roomManageRepository.select(roomId);

        Optional<RoomInfo> optional = Optional.fromNullable(info);

        if (optional.isPresent()) {
            boolean tmp = roomManageRepository.delete(roomId);

            if (tmp) {
                LogContent logContent = new LogContent(logUser, "删除教室ID为" + roomId, 1, 2);
                logRepository.insertLog(logContent);
            }

            return tmp;
        }

        return false;
    }
}
