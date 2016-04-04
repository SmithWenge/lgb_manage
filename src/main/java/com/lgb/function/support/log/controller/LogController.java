package com.lgb.function.support.log.controller;

import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.support.log.LogContent;
import com.lgb.function.support.log.service.LogServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/log")
public class LogController {
    @Autowired
    private LogServiceI logService;

    @ResponseBody
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Map<String, Page<LogContent>> showLog(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE)
                                                         Pageable pageable, LogContent logContent) {
        Map<String, Page<LogContent>> map = new HashMap<>();
        Page<LogContent> page = logService.query4Page(logContent, pageable);

        map.put(ConstantFields.AJAX_PAGE_KEY, page);

        return map;
    }

    @RequestMapping(value = "/routePage", method = RequestMethod.GET)
    public ModelAndView showFirstPage() {
        ModelAndView mav = new ModelAndView("log/logPage");

        Page<LogContent> contents = logService.query4Page(new LogContent(-1), new PageRequest(0, ConstantFields.DEFAULT_PAGE_SIZE));
        mav.addObject(ConstantFields.AJAX_PAGE_KEY, contents);

        return mav;
    }
}
