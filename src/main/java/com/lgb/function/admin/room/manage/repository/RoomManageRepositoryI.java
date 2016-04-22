package com.lgb.function.admin.room.manage.repository;

import com.lgb.function.admin.room.info.RoomInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomManageRepositoryI {
    Page<RoomInfo> select4Page(RoomInfo roomInfo, Pageable pageable);
    RoomInfo select(int roomId);
    boolean update(RoomInfo roomInfo);
    boolean delete(int roomId);
}
