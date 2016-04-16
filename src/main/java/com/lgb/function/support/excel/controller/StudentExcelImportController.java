package com.lgb.function.support.excel.controller;

import com.lgb.arc.excel.into.ExcelConverter;
import com.lgb.arc.excel.into.StudentExcelMapper;
import com.lgb.function.admin.student.StudentUser;
import com.lgb.function.support.excel.service.StudentExcelImportServiceI;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/admin/excel")
public class StudentExcelImportController {
    public static final String UPLOAD_EXCEL_FILE_NAME ="targetFile";

    @Autowired
    private StudentExcelImportServiceI studentExcelImportService;

    @RequestMapping("/routeImport")
    public String routeImport() {
        return "admin/student/import";
    }

    @RequestMapping(value = "/addExcel", method = RequestMethod.POST)
    public String add(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        File importFile = save(file, request);

        if (null == importFile) return "redirect:/admin/excel/routeImport.action";

        ExcelConverter<StudentUser> converter = new ExcelConverter<StudentUser>();
        List<StudentUser> studentUsers = converter.readFromExcel(importFile, 1, new StudentExcelMapper());

        if (studentExcelImportService.save(studentUsers)) {
            return "redirect:/admin/student/page.action";
        }

        return "redirect:/admin/excel/routeImport.action";
    }

    @RequestMapping("/template")
    public String downloadTemplate(HttpServletResponse response, HttpSession session) {
        String templatePath = session.getServletContext().getRealPath("/") + "WEB-INF/data/template/学员导入.xls";
        try {
            File file = new File(templatePath);

            String name = URLEncoder.encode(file.getName(), "utf-8");

            response.setContentType("application/x-excel");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + name);
            response.setHeader("Content-Length", String.valueOf(file.length()));

            int length = 0;
            byte[] buffer = new byte[1024];

            FileInputStream fis = new FileInputStream(file);
            OutputStream os = response.getOutputStream();

            while (-1 != (length = fis.read(buffer, 0, buffer.length))) {
                os.write(buffer, 0, length);
            }

            os.flush();
            os.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private File save(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String targetPath = request.getSession().getServletContext().getRealPath("/WEB-INF/data/excel/");
        String sourceFileName = file.getOriginalFilename();

        String dateString = new DateTime().toString("MM-dd-yyyy-HH-mm-ss-SSS");
        String prefixName = sourceFileName.substring(0, sourceFileName.indexOf("."));
        String subName = sourceFileName.substring(sourceFileName.lastIndexOf("."));
        String newName = prefixName + "-" + dateString + subName;

        File targetFile = new File(targetPath, newName);
        request.getSession().setAttribute(UPLOAD_EXCEL_FILE_NAME, targetFile.getName());

        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        try {
            file.transferTo(targetFile);

            return targetFile;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
