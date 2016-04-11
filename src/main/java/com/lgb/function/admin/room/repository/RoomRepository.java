package com.lgb.function.admin.room.repository;

import com.lgb.function.admin.room.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomRepository implements RoomRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Room> selectAll() {
        String sql = "SELECT DISTINCT courseRoom FROM lgb_courseTime WHERE courseId IN (SELECT courseId FROM lgb_course WHERE deleteFlag = 0)";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new SelectAllRowMapper());
        } catch (Exception e) {
            return new ArrayList<Room>();
        }
    }

    private class SelectAllRowMapper implements RowMapper<Room> {

        @Override
        public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
            Room room = new Room();

            room.setRoomId(rs.getInt("courseRoom"));

            return room;
        }
    }

    public List<Room> selectAllTime() {
        String sql = "SELECT courseId, courseRoom, timeWeek, timeSpecific FROM lgb_courseTime WHERE courseRoom IN (SELECT DISTINCT courseRoom FROM lgb_courseTime WHERE courseId IN (SELECT courseId FROM lgb_course WHERE deleteFlag = 0))";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new SelectAllTimeRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Room>();
        }
    }

    private class SelectAllTimeRowMapper implements RowMapper<Room> {

        @Override
        public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
            Room room = new Room();

            room.setCourseId(rs.getInt("courseId"));
            room.setRoomId(rs.getInt("courseRoom"));
            room.setTimeSpecific(rs.getString("timeSpecific"));
            room.setTimeWeek(rs.getInt("timeWeek"));

            return room;
        }
    }
}
