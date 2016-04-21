package com.lgb.function.admin.sign.service;


import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.major.Major;
import com.lgb.function.admin.sign.StudentCourse;
import com.lgb.function.admin.sign.repository.StudentLoginRepository;
import com.lgb.function.admin.student.StudentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentLoginService implements StudentLoginServiceI {
    @Autowired
    private StudentLoginRepository studentLoginRepository;

    @Override
    public StudentUser login(StudentUser studentUser) {
        StudentUser loginUser = studentLoginRepository.selectUnique(studentUser);

        return loginUser;
    }

    @Override
    public Page<Course> list(Course course,Pageable pageable) {
        return studentLoginRepository.query4Page(course,pageable);
    }
    @Override
    public Page<Course> querySign4Page(Course course,Pageable pageable) {
        return studentLoginRepository.querySign4Page(course, pageable);
    }
    @Override
    public Course moreCourseInfo(int courseId) {
        return studentLoginRepository.queryForCourse(courseId);
    }

    @Override
    public boolean add(StudentCourse studentCourse) {

        if (studentLoginRepository.signUpVer(studentCourse) != null){
            return false;

        }else {
            boolean tmp = studentLoginRepository.signUp(studentCourse);

            return tmp;
        }
    }
    @Override
    public boolean delete(StudentCourse studentCourse) {
        if (studentLoginRepository.signUpVer(studentCourse) != null){
            boolean tmp = studentLoginRepository.delete(studentCourse);
            return tmp;
        }
        return false;
    }

    @Override
    public List<Department> departments() {
        return studentLoginRepository.selectDepartments();
    }
    @Override
    public List<Major> majors(int departmentId) {
        return studentLoginRepository.selectMajors(departmentId);
    }
}
