package com.lgb.function.admin.room.manage.service;

import com.lgb.function.admin.room.info.RoomInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomManageServiceI {
    Page<RoomInfo> query4Page(RoomInfo roomInfo, Pageable pageable);
    RoomInfo select(int roomId);
    boolean edit(RoomInfo roomInfo, String logUser);
    boolean delete(int roomId, String logUser);
}
