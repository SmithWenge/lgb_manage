package com.lgb.function.admin.room.info.repository;

import com.lgb.function.admin.room.Room;
import com.lgb.function.admin.room.info.RoomInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomInfoRepository implements RoomInfoRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public RoomInfo select(int roomId) {
        String sql = "SELECT roomId, roomName, roomFloor, roomCapacity, roomRemark FROM lgb_room WHERE deleteFlag = 0 AND roomId = ?";
        Object[] args = {
                roomId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    private class SelectRowMapper implements RowMapper<RoomInfo> {

        @Override
        public RoomInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            RoomInfo info = new RoomInfo();

            info.setRoomId(rs.getInt("roomId"));
            info.setRoomName(rs.getString("roomName"));
            info.setRoomFloor(rs.getInt("roomFloor"));
            info.setRoomCapacity(rs.getInt("roomCapacity"));
            info.setRoomRemark(rs.getString("roomRemark"));

            return info;
        }
    }

    @Override
    public List<RoomInfo> selectRoomCourses(int roomId) {
        String sql = "SELECT CT.courseId, timeWeek, timeSpecific, CT.courseRoom, C.courseName FROM lgb_courseTime CT LEFT JOIN lgb_course C ON CT.courseId = C.courseId WHERE CT.courseRoom = ?";
        Object[] args = {
                roomId
        };

        try {
            return jdbcTemplate.query(sql, args, new RoomCourseRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<RoomInfo>();
        }
    }

    private class RoomCourseRowMapper implements RowMapper<RoomInfo> {

        @Override
        public RoomInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            RoomInfo roomInfo = new RoomInfo();

            roomInfo.setCourseId(rs.getInt("courseId"));
            roomInfo.setTimeWeek(rs.getInt("timeWeek"));
            roomInfo.setTimeSpecific(rs.getString("timeSpecific"));
            roomInfo.setCourseRoom(rs.getInt("courseRoom"));
            roomInfo.setCourseName(rs.getString("courseName"));

            return roomInfo;
        }
    }
}
