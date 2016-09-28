package com.lgb.function.admin.student.repository;

import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.student.StudentUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface StudentRepositoryI {
    Page<StudentUser> query4Page(StudentUser studentUser,Pageable pageable);
    boolean insert(StudentUser studentUser);
    StudentUser select(int stuID);
    boolean update(StudentUser studentUser);
    boolean delete(int stuId);
    StudentUser selectCard(int stuID);
    boolean selectIdAndCard(StudentUser studentUser);
    boolean updateCard(StudentUser studentUser);
    int selectCardNum(StudentUser studentUser);
    List<StudentUser> selectForExport();
    List<Course> select4Courses(int stuId);
}
