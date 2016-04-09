package com.lgb.function.admin.count.controller;

import com.lgb.function.admin.count.service.CountServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CountController {
    @Autowired
    private CountServiceI countServiceI;



}
