package com.lgb.function.admin.student.controller;

import com.google.common.base.Optional;
import com.lgb.arc.excel.Excel;
import com.lgb.arc.excel.ExcelFactory;
import com.lgb.arc.excel.mapper.StudentExcelMapper;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.login.AdminUser;
import com.lgb.function.admin.student.StudentUser;
import com.lgb.function.admin.student.service.StudentServiceI;
import com.lgb.function.admin.teacher.Teacher;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/student")
public class StudentController {
    private static final Logger LOG = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentServiceI studentService;

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public ModelAndView showLog(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE)
                                Pageable pageable, StudentUser studentUser, HttpSession session) {
        StudentUser searchStudent = (StudentUser) session.getAttribute(ConstantFields.SESSION_STU_SEARCH_KEY);

        Optional<StudentUser> optional = Optional.fromNullable(searchStudent);
        if (optional.isPresent()) {
            studentUser = searchStudent;
        }

        ModelAndView mav = new ModelAndView("admin/student/list");
        Page<StudentUser> page = studentService.list(studentUser, pageable);

        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }
    @RequestMapping(value = "/pageSearch")
    public ModelAndView searchLog(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday,
                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
                                  @PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable,
                                  StudentUser studentUser, HttpSession session) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (birthday !=null) {
            String tmpBirthday = sdf.format(birthday);
            studentUser.setStuBirthday(Timestamp.valueOf(tmpBirthday));
        }

        if (startTime !=null) {
            String tmpStartTime = sdf.format(startTime);
            studentUser.setStudentStartDate(Timestamp.valueOf(tmpStartTime));
        }

        session.setAttribute(ConstantFields.SESSION_STU_SEARCH_KEY, studentUser);

        ModelAndView mav = new ModelAndView("admin/student/list");
        Page<StudentUser> page = studentService.list(studentUser, pageable);

        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }

    @RequestMapping(value = "/routePage", method = RequestMethod.GET)
    public ModelAndView showFirstPage(HttpSession session) {
        session.removeAttribute(ConstantFields.SESSION_STU_SEARCH_KEY);

        ModelAndView mav = new ModelAndView("admin/student/list");

        Page<StudentUser> users = studentService.list(new StudentUser(), new PageRequest(0, ConstantFields.DEFAULT_PAGE_SIZE));
        mav.addObject(ConstantFields.PAGE_KEY, users);

        return mav;
    }

    @RequestMapping(value = "/routeEdit/{stuId}", method = RequestMethod.GET)
    public ModelAndView routeEditUser(@PathVariable("stuId") int stuId) {
        StudentUser studentUser = studentService.select(stuId);

        Optional<StudentUser> optional = Optional.fromNullable(studentUser);
        if (optional.isPresent()) {
            ModelAndView mav = new ModelAndView("admin/student/edit");
            mav.addObject(ConstantFields.EDIT_OBJECT_KEY, studentUser);

            return mav;
        }
        return new ModelAndView("redirect:/admin/student/page.action");
    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editStudent(StudentUser studentUser, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminName();

        if (studentService.edit(studentUser, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} update admin student {}.", logUser, studentUser.getStuName());

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.EDIT_SUCCESS_MESSAGE);

            return "redirect:/admin/student/page.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.EDIT_FAILURE_MESSAGE);
        return "redirect:/admin/student/routeEdit/" + studentUser.getStuId() + ".action";
    }

    @RequestMapping(value = "/delete/{stuId}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("stuId") int stuId, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminName();

        if (studentService.delete(stuId, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} delete student's ID {}.", logUser, stuId);

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.DELETE_SUCCESS_MESSAGE);

            return "redirect:/admin/student/page.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.DELETE_FAILURE_MESSAGE);

        return "redirect:/admin/student/page.action";
    }

    @RequestMapping(value = "/routeAdd", method = RequestMethod.GET)
    public String routeAddStudent() {
        return "admin/student/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStudent(StudentUser studentUser, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser)session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminName();

        if (studentService.add(studentUser,  logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} add new student user {}.", logUser, studentUser.getStuName());

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.ADD_SUCCESS_MESSAGE);
            return "redirect:/admin/student/page.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.ADD_FAILURE_MESSAGE);
        return "redirect:/admin/student/routeAdd.action";
    }

    @RequestMapping(value = "/routeTurnCard/{stuId}")
    public ModelAndView routeTurnCard(@PathVariable("stuId") int stuId) {
        StudentUser studentUser = studentService.selectCard(stuId);

        Optional<StudentUser> optional = Optional.fromNullable(studentUser);
        if (optional.isPresent()) {
            ModelAndView mav = new ModelAndView("admin/student/turnCard");

            mav.addObject("studentUser", studentUser);

            return mav;
        }

        return new ModelAndView("redirect:/admin/student/routePage.action");
    }

    @RequestMapping(value = "/turnCard", method = RequestMethod.POST)
    public String turnCard(StudentUser studentUser, HttpSession session, RedirectAttributes redirectAttributes) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (studentService.turnCard(studentUser, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} turn {}'s card.", logUser, studentUser.getStuName());

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.TURN_CARD_SUCCESS_MESSAGE + studentUser.getStuName());

            return "redirect:/admin/student/page.action";
        }

        redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.TURN_CARD_FAILURE_MESSAGE + studentUser.getStuName());
        return "redirect:/admin/student/routeTurnCard/" + studentUser.getStuId() + ".action";
    }

    @RequestMapping(value = "/cardNum", method = RequestMethod.POST)
    @ResponseBody
    public boolean nameExist(StudentUser studentUser) {
        if (studentService.existCardNum(studentUser)) return true;
        else return false;
    }

    @RequestMapping("/export")
    public void adminExport(HttpServletResponse response) {
        List<StudentUser> users = studentService.exportAllStu();
        ExcelFactory<StudentUser> factory = new ExcelFactory<StudentUser>();
        File file = new File("student.xls");

        try {
            WritableWorkbook workbook = factory.createExcel(new FileOutputStream(file),
                    new Excel("学员卡", 0), Arrays.asList("卡号", "姓名", "性别", "生日", "电话1", "电话2", "工作单位", "住址"), users, new StudentExcelMapper());
            workbook.write();
            workbook.close();

            response.setContentType("application/x-export");
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

    // 查看学生所选择的课程
    @RequestMapping("/courses/{stuId}")
    public ModelAndView studentCourses(@PathVariable int stuId) {
        List<Course> courses = studentService.selectCourses(stuId);

        ModelAndView mav = new ModelAndView("admin/student/studentCourses");
        mav.addObject("courses", courses);

        return mav;
    }
}