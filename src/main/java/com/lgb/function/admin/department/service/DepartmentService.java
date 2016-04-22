package com.lgb.function.admin.department.service;

import com.google.common.base.Optional;
import com.lgb.function.admin.course.leader.CourseLeader;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.department.repository.DepartmentRepositoryI;
import com.lgb.function.admin.login.AdminUser;
import com.lgb.function.support.log.LogContent;
import com.lgb.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentService implements DepartmentServiceI {
    @Autowired
    private DepartmentRepositoryI departmentRepository;
    @Autowired
    private LogRepositoryI logRepository;

    @Override
    public List<AdminUser> listDepartmentAdmins() {
        return departmentRepository.departmentAdmins();
    }

    @Override
    public boolean exist(Department department) {
        return departmentRepository.selectName(department) == 0 ? true : false;
    }

    @Override
    public boolean add(Department department, String logUser) {
        boolean tmp = departmentRepository.insert(department);

        if (tmp) {
            LogContent logContent = new LogContent(logUser, "添加系" + department.getDepartmentName(), 1, 3);
            logRepository.insertLog(logContent);
        }

        return tmp;
    }

    @Override
    public Page<Department> list(Pageable pageable) {
        Page<Department> departments = departmentRepository.select4Page(pageable);
        List<Department> departmentList = departments.getContent();

        for (Department department : departmentList) {
            department.setCourseNum(departmentRepository.courseNum(department.getDepartmentId()));
            department.setStudentNum(departmentRepository.studentNum(department.getDepartmentId()));
        }

        return new PageImpl<Department>(departmentList, pageable, departments.getTotalElements());
    }

    @Override
    public Department select(int departmentId) {
        return departmentRepository.select(departmentId);
    }

    @Override
    public boolean edit(Department department, String logUser) {
        Department existObj = departmentRepository.select(department.getDepartmentId());

        Optional<Department> optional = Optional.fromNullable(existObj);

        if (optional.isPresent()) {
            boolean tmp = departmentRepository.update(department);

            if (tmp) {
                LogContent logContent = new LogContent(logUser, "编辑系" + department.getDepartmentName(), 1, 4);
                logRepository.insertLog(logContent);
            }

            return tmp;
        }

        return false;
    }

    @Override
    public boolean delete(int departmentId, String logUser) {
        Department existObj = departmentRepository.select(departmentId);

        Optional<Department> optional = Optional.fromNullable(existObj);

        if (optional.isPresent()) {
            boolean tmp = departmentRepository.delete(departmentId);

            if (tmp) {
                LogContent logContent = new LogContent(logUser, "删除系ID为" + departmentId, 1, 2);
                logRepository.insertLog(logContent);
            }

            return tmp;
        }

        return false;
    }
}
