package com.lgb.function.admin.student.service;

import com.google.common.base.Optional;
import com.lgb.arc.utils.EncryptAnDecrypt;
import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.student.StudentUser;
import com.lgb.function.admin.student.repository.StudentRepositoryI;
import com.lgb.function.admin.teacher.Teacher;
import com.lgb.function.admin.user.repository.UserRepositoryI;
import com.lgb.function.support.log.LogContent;
import com.lgb.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class StudentService implements StudentServiceI{
    @Autowired
    private StudentRepositoryI studentRepository;
    @Autowired
    private UserRepositoryI userRepository;
    @Autowired
    private LogRepositoryI logRepository;

    @Override
    public Page<StudentUser> list(StudentUser studentUser,Pageable pageable) {
        return studentRepository.query4Page(studentUser,pageable);
    }

    @Override
    public boolean add(StudentUser studentUser, String logUser) {
        String stuIdentifiedNum = studentUser.getStuIdentifiedNum();
        String stuLastEightNum = stuIdentifiedNum.substring(stuIdentifiedNum.length() - 8, stuIdentifiedNum.length());
        studentUser.setStuLastEightNum(stuLastEightNum);

        String stuTelOne = studentUser.getStuTelOne();
        Optional<String> optional = Optional.fromNullable(stuTelOne);
        if (optional.isPresent()) {
            studentUser.setStuTelOne(EncryptAnDecrypt.encrypt(stuTelOne));
        }

        String stuTelTwo = studentUser.getStuTelTwo();
        Optional<String> optional2 = Optional.fromNullable(stuTelTwo);
        if (optional2.isPresent()) {
            studentUser.setStuTelTwo(EncryptAnDecrypt.encrypt(stuTelTwo));
        }

        boolean tmp = studentRepository.insert(studentUser);

        if (tmp) {
            LogContent logContent = new LogContent(logUser, "添加用户" + studentUser.getStuName(), 1, 3);
            logRepository.insertLog(logContent);
        }
        return tmp;
    }
    @Override
    public StudentUser select(int stuId) {
        return studentRepository.select(stuId);
    }

    @Override
    public boolean edit(StudentUser studentUser, String logUser) {
        String stuIdentifiedNum = studentUser.getStuIdentifiedNum();
        String stuLastEightNum = stuIdentifiedNum.substring(stuIdentifiedNum.length()-8,stuIdentifiedNum.length());
        studentUser.setStuLastEightNum(stuLastEightNum);

        StudentUser user = studentRepository.select(studentUser.getStuId());
        Optional<StudentUser> optional = Optional.fromNullable(user);

        if (optional.isPresent()) {
            boolean tmp = studentRepository.update(studentUser);

            if (tmp) {
                LogContent logContent = new LogContent(logUser, "编辑用户" + studentUser.getStuName(), 1, 4);
                logRepository.insertLog(logContent);
            }

            return tmp;
        }

        return false;
    }

    @Override
    public boolean delete(int stuId, String logUser) {
        StudentUser user = studentRepository.select(stuId);

        Optional<StudentUser> optional = Optional.fromNullable(user);

        if (optional.isPresent()) {
            boolean tmp = studentRepository.delete(stuId);

            if (tmp) {
                LogContent logContent = new LogContent(logUser, "删除用户ID为" + stuId, 1, 2);
                logRepository.insertLog(logContent);
            }

            return tmp;
        }

        return false;
    }

    @Override
    public StudentUser selectCard(int stuID) {
        return studentRepository.selectCard(stuID);
    }

    @Override
    public boolean turnCard(StudentUser studentUser, String logUser) {
        boolean exist = studentRepository.selectIdAndCard(studentUser);
        if (exist) {
            return studentRepository.updateCard(studentUser);
        }

        return false;
    }

    @Override
    public boolean existCardNum(StudentUser studentUser) {
        return studentRepository.selectCardNum(studentUser) == 0 ? true : false;
    }

    @Override
    public List<StudentUser> exportAllStu() {
        return studentRepository.selectForExport();
    }

    @Override
    public List<Course> selectCourses(int stuId) {
        return studentRepository.select4Courses(stuId);
    }

    @Override
    public StudentUser detail(int stuId) {
        return studentRepository.select(stuId);
    }
}
