package com.lgb.function.admin.student.photo.controller;

import com.lgb.function.admin.student.photo.service.StudentPhotoServiceI;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * 这个一个测试用的controller和student/photo.jsp连接测试拍照功能,可以删除
 */
@Controller
@RequestMapping("/admin/photo")
public class StudentPhotoController {
    @Autowired
    private StudentPhotoServiceI photoService;
    @Autowired
    ServletContext context;

    @RequestMapping("/route")
    public String routeTakePhoto() {
        return "admin/student/photo";
    }

    @RequestMapping("/route/{name}")
    public void photo(HttpServletResponse response, HttpServletRequest request, @PathVariable("name") String name) throws IOException {
        response.setContentType("image/jpg");
        InputStream in = context.getResourceAsStream("/WEB-INF/photo/" + name + ".jpg");

        IOUtils.copy(in, response.getOutputStream());

        in.close();
    }
}
