package com.lgb.function.admin.teacher.service;

import com.google.common.base.Optional;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.teacher.Teacher;
import com.lgb.function.admin.teacher.repository.TeacherRepositoryI;
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
public class TeacherService implements TeacherServiceI {
    @Autowired
    private TeacherRepositoryI teacherRepository;
    @Autowired
    private LogRepositoryI logRepository;

    @Override
    public boolean existCardNum(Teacher teacher) {
        return teacherRepository.selectCardNum(teacher) == 0 ? true : false;
    }

    @Override
    public List<Department> departments() {
        return teacherRepository.selectDepartments();
    }

    @Override
    public boolean add(Teacher teacher, String logUser) {
        boolean tmp = teacherRepository.insert(teacher);

        String subject = teacher.getTeacherSubject();
        String[] subjects = subject.split(",");

        if (tmp) {
            int teacherId = teacherRepository.selectId(teacher.getTeacherCardNum());
            for (String subjectName : subjects) {
                teacherRepository.insert(subjectName, teacherId);
            }
            LogContent logContent = new LogContent(logUser, "添加教师" + teacher.getTeacherName(), 1, 3);
            logRepository.insertLog(logContent);
        }

        return tmp;
    }

    @Override
    public Page<Teacher> query4Page(Teacher teacher, Pageable pageable) {
        return teacherRepository.select4Page(teacher, pageable);
    }

    @Override
    public Teacher selectCard(int teacherId) {
        return teacherRepository.selectCard(teacherId);
    }

    @Override
    public boolean turnCard(Teacher teacher, String logUser) {
        boolean exist = teacherRepository.selectIdAndCard(teacher);
        if (exist) {
            return teacherRepository.updateCard(teacher);
        }

        return false;
    }

    @Override
    public boolean edit(Teacher teacher, String logUser) {
        Teacher existObj = teacherRepository.select(teacher.getTeacherId());

        Optional<Teacher> optional = Optional.fromNullable(existObj);

        if (optional.isPresent()) {
            boolean tmp = teacherRepository.update(teacher);

            if (tmp) {
                LogContent logContent = new LogContent(logUser, "编辑教师" + teacher.getTeacherName(), 1, 4);
                logRepository.insertLog(logContent);
            }

            return tmp;
        }

        return false;
    }

    @Override
    public Teacher select(int teacherId) {
        return teacherRepository.select(teacherId);
    }

    @Override
    public boolean delete(Teacher teacher, String logUser) {
        Teacher existObj = teacherRepository.select(teacher.getTeacherId());

        Optional<Teacher> optional = Optional.fromNullable(existObj);

        if (optional.isPresent()) {
            boolean tmp = teacherRepository.delete(teacher);

            if (tmp) {
                LogContent logContent = new LogContent(logUser, "解聘教师为" + teacher.getTeacherName(), 1, 2);
                logRepository.insertLog(logContent);
            }

            return tmp;
        }

        return false;
    }
}
