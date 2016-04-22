package com.lgb.function.admin.room.manage.repository;

import com.google.common.base.Optional;
import com.lgb.function.admin.room.info.RoomInfo;
import com.lgb.function.support.utils.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomManageRepository implements RoomManageRepositoryI {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils<RoomInfo> repositoryUtils;

    @Override
    public Page<RoomInfo> select4Page(RoomInfo roomInfo, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT roomId, roomName, roomFloor, roomCapacity FROM lgb_room WHERE deleteFlag = 0");

        List<Object> list = new ArrayList<>();
        Optional<RoomInfo> optional = Optional.fromNullable(roomInfo);

        if (optional.isPresent()) {
            if (roomInfo.getRoomFloor() > 0) {
                sql.append(" AND roomFloor = ?");
                list.add(roomInfo.getRoomFloor());
            }

            if (roomInfo.getRoomCapacity() > 0) {
                sql.append(" AND roomCapacity >= ?");
                list.add(roomInfo.getRoomCapacity());
            }
        }

        Object[] args = list.toArray();

        sql.append(" ORDER BY roomId");

        return repositoryUtils.select4Page(sql.toString(), pageable, args, new Select4PageRowMapper());
    }

    private class Select4PageRowMapper implements RowMapper<RoomInfo> {

        @Override
        public RoomInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            RoomInfo roomInfo = new RoomInfo();

            roomInfo.setRoomId(rs.getInt("roomId"));
            roomInfo.setRoomName(rs.getString("roomName"));
            roomInfo.setRoomFloor(rs.getInt("roomFloor"));
            roomInfo.setRoomCapacity(rs.getInt("roomCapacity"));

            return roomInfo;
        }
    }

    @Override
    public RoomInfo select(int roomId) {
        String sql = "SELECT roomId, roomName, roomFloor, roomCapacity FROM lgb_room WHERE deleteFlag = 0 AND roomId = ?";
        Object[] args = {
                roomId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new Select4PageRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(RoomInfo roomInfo) {
        String sql = "UPDATE lgb_room SET roomName = ?, roomCapacity = ?, roomFloor = ? WHERE deleteFlag = 0 AND roomId = ?";
        Object[] args = {
                roomInfo.getRoomName(),
                roomInfo.getRoomCapacity(),
                roomInfo.getRoomFloor(),
                roomInfo.getRoomId()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int roomId) {
        String sql = "UPDATE lgb_room SET deleteFlag = 1 WHERE deleteFlag = 0 AND roomId = ?";
        Object[] args = {
                roomId
        };

        try {
            return jdbcTemplate.update(sql, args) == 1 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
