package com.lgb.function.admin.room.manage.controller;

import com.google.common.base.Optional;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.login.AdminUser;
import com.lgb.function.admin.room.info.RoomInfo;
import com.lgb.function.admin.room.manage.service.RoomManageServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/room/manage")
public class RoomManageController {

    private static final Logger LOG = LoggerFactory.getLogger(RoomManageController.class);

    @Autowired
    private RoomManageServiceI roomManageService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView showRoom(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE)
                                        Pageable pageable, RoomInfo roomInfo, HttpSession session) {
        RoomInfo searchRoomInfo = (RoomInfo) session.getAttribute(ConstantFields.SESSION_ROOM_SEARCH_KEY);

        Optional<RoomInfo> optional = Optional.fromNullable(searchRoomInfo);
        if (optional.isPresent()) {
            roomInfo = searchRoomInfo;
        }

        ModelAndView mav = new ModelAndView("admin/room/manage/list");
        Page<RoomInfo> page = roomManageService.query4Page(roomInfo, pageable);

        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }

    @RequestMapping(value = "/pageSearch", method = RequestMethod.POST)
    public ModelAndView searchRoom(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable,
                                   RoomInfo roomInfo, HttpSession session) {
        session.setAttribute(ConstantFields.SESSION_ROOM_SEARCH_KEY, roomInfo);

        ModelAndView mav = new ModelAndView("admin/room/manage/list");
        Page<RoomInfo> page = roomManageService.query4Page(roomInfo, pageable);

        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }

    @RequestMapping(value = "/routePage", method = RequestMethod.GET)
    public ModelAndView showFirstPage(HttpSession session) {
        session.removeAttribute(ConstantFields.SESSION_ROOM_SEARCH_KEY);

        ModelAndView mav = new ModelAndView("admin/room/manage/list");

        Page<RoomInfo> users = roomManageService.query4Page(new RoomInfo(), new PageRequest(0, ConstantFields.DEFAULT_PAGE_SIZE));
        mav.addObject(ConstantFields.PAGE_KEY, users);

        return mav;
    }

    @RequestMapping(value = "/routeEdit/{roomId}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("roomId") int roomId) {
        RoomInfo roomInfo = roomManageService.select(roomId);

        Optional<RoomInfo> optional = Optional.fromNullable(roomInfo);
        if (optional.isPresent()) {
            ModelAndView mav = new ModelAndView("admin/room/manage/edit");
            mav.addObject(ConstantFields.EDIT_OBJECT_KEY, roomInfo);

            return mav;
        }

        return new ModelAndView("redirect:/admin/room/manage/routePage.action");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(RoomInfo roomInfo, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (roomManageService.edit(roomInfo, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} update room {}.", logUser, roomInfo.getRoomName());

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.EDIT_SUCCESS_MESSAGE);

            return "redirect:/admin/room/manage/routePage.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.EDIT_FAILURE_MESSAGE);
        return "redirect:/admin/room/manage/routeEdit/" + roomInfo.getRoomId() + ".action";
    }

    @RequestMapping(value = "/delete/{roomId}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("roomId") int roomId, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (roomManageService.delete(roomId, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} delete room's ID {}.", logUser, roomId);

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.DELETE_SUCCESS_MESSAGE);

            return "redirect:/admin/room/manage/routePage.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.DELETE_FAILURE_MESSAGE);

        return "redirect:/admin/room/manage/routePage.action";
    }
}
