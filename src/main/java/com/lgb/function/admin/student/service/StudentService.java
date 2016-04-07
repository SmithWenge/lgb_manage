package com.lgb.function.admin.student.service;

import com.google.common.base.Optional;
import com.lgb.function.admin.student.StudentUser;
import com.lgb.function.admin.student.repository.StudentRepositoryI;
import com.lgb.function.admin.user.repository.UserRepositoryI;
import com.lgb.function.support.log.LogContent;
import com.lgb.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
}
