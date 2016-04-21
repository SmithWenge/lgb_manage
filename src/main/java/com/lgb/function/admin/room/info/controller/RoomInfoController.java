package com.lgb.function.admin.room.info.controller;

import com.google.common.base.Optional;
import com.lgb.function.admin.room.info.RoomInfo;
import com.lgb.function.admin.room.info.service.RoomInfoServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/room/info")
public class RoomInfoController {
    @Autowired
    private RoomInfoServiceI roomInfoService;

    @RequestMapping("/detail/{roomId}")
    public ModelAndView detailRoom(@PathVariable("roomId") int roomId) {
        ModelAndView mav = new ModelAndView("admin/room/info");

        RoomInfo info = roomInfoService.detail(roomId);
        Optional<RoomInfo> optional = Optional.fromNullable(info);
        if (optional.isPresent()) {
            mav.addObject("room", info);

            return mav;
        }

        return new ModelAndView("redirect:/admin/room/routeRoom.action");
    }

    @ResponseBody
    @RequestMapping("/courses/{roomId}")
    public Map<String, List<RoomInfo>> roomCourses(@PathVariable("roomId") int roomId) {
        Map<String, List<RoomInfo>> map = new HashMap<>();
        map.put("courses", roomInfoService.roomCourse(roomId));

        return map;
    }
}
