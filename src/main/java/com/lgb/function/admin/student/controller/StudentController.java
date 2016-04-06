package com.lgb.function.admin.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/student")
public class StudentController {
    @RequestMapping(value = "/routeAdd", method = RequestMethod.GET)
    public String routeAddStu() {return "admin/student/add";}
}
