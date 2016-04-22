package com.lgb.function.admin.room.controller;

import com.google.common.base.Optional;
import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.room.Room;
import com.lgb.function.admin.room.service.RoomServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/time/course/{timeWeek}/{timeSpecific}/{roomId}", method = RequestMethod.GET)
    public ModelAndView redirectTimeCourse(@PathVariable("timeWeek") int timeWeek,
                                            @PathVariable("timeSpecific") String timeSpecific,
                                            @PathVariable("roomId") int roomId) {
        ModelAndView mav = new ModelAndView("admin/room/courseInfo");

        Room room = new Room();
        room.setTimeWeek(timeWeek);
        room.setTimeSpecific(timeSpecific);
        room.setRoomId(roomId);

        List<Course> courses = roomService.roomCourse(room);

        if (courses.size() > 0) {
            mav.addObject("courses", courses);

            return mav;
        }

        return new ModelAndView("redirect:/admin/room/routeRoom.action");
    }
}
