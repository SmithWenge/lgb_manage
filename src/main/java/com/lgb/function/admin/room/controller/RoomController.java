package com.lgb.function.admin.room.controller;

import com.lgb.function.admin.room.Room;
import com.lgb.function.admin.room.service.RoomServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/room")
public class RoomController {

    @Autowired
    private RoomServiceI roomService;

    @RequestMapping("/routeRoom")
    public String routeRoom() {
        return "admin/room/list";
    }

    @ResponseBody
    @RequestMapping("/index")
    public Map<String, List<Room>> index() {
        Map<String, List<Room>> map = new HashMap<>();
        map.put("rooms", roomService.allRooms());

        return map;
    }

    @ResponseBody
    @RequestMapping("/time")
    public Map<String, List<Room>> time() {
        Map<String, List<Room>> map = new HashMap<>();
        map.put("times", roomService.allTimes());

        return map;
    }

    @RequestMapping("/routeTime")
    public String routeTime() {
        return "admin/room/time";
    }
}
