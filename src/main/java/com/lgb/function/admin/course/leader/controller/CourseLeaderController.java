package com.lgb.function.admin.course.leader.controller;

import com.lgb.arc.excel.Excel;
import com.lgb.arc.excel.ExcelFactory;
import com.lgb.arc.excel.mapper.CourseLeaderExcelMapper;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.course.leader.CourseLeader;
import com.lgb.function.admin.course.leader.service.CourseLeaderServiceI;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/course/leader")
public class CourseLeaderController {
    @Autowired
    private CourseLeaderServiceI courseLeaderService;

    @RequestMapping("/page")
    public ModelAndView listPage(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable) {
        ModelAndView mav = new ModelAndView("admin/leader/list");

        Page<CourseLeader> courses = courseLeaderService.select4Page(pageable);
        mav.addObject(ConstantFields.PAGE_KEY, courses);

        return mav;
    }

    @RequestMapping("/export")
    public void export(HttpServletResponse response) {
        List<CourseLeader> leaders = courseLeaderService.listAll();
        ExcelFactory<CourseLeader> factory = new ExcelFactory<CourseLeader>();
        File file = new File("leaders.xls");

        try {
            WritableWorkbook workbook = factory.createExcel(new FileOutputStream(file),
                    new Excel("班长", 0), Arrays.asList("卡号", "学员名", "教室", "系", "专业", "课程", "性别", "生日", "电话1", "电话2", "座号"), leaders, new CourseLeaderExcelMapper());
            workbook.write();
            workbook.close();

            response.setContentType("application/x-excel");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            response.setHeader("Content-Length", String.valueOf(file.length()));
            int length = 0;
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = response.getOutputStream();
            while (-1 != (length = fis.read(buffer, 0, buffer.length))) {
                os.write(buffer, 0, length);
            }
            fis.close();
            os.flush();
            os.close();
        } catch (RowsExceededException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (WriteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            file.delete();
        }
    }
}
