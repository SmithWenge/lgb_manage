package com.lgb.function.admin.major.service;

import com.google.common.base.Optional;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.major.Major;
import com.lgb.function.admin.major.repository.MajorRepositoryI;
import com.lgb.function.support.log.LogContent;
import com.lgb.function.support.log.repository.LogRepositoryI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MajorService implements MajorServiceI {
    private static final Logger LOG = LoggerFactory.getLogger(MajorService.class);

    @Autowired
    private MajorRepositoryI majorRepository;
    @Autowired
    private LogRepositoryI logRepository;

    @Override
    public List<Department> departments() {
        return majorRepository.selectDepartments();
    }

    @Override
    public boolean exist(Major major) {
        return majorRepository.selectName(major) == 0 ? true : false;
    }

    @Override
    public boolean add(Major major, String logUser) {
        boolean tmp = majorRepository.insert(major);

        if (tmp) {
            LogContent logContent = new LogContent(logUser, "添加专业" + major.getMajorName(), 1, 3);
            logRepository.insertLog(logContent);
        }

        return tmp;
    }

    @Override
    public Page<Major> list(Pageable pageable) {
        return majorRepository.select4Page(pageable);
    }

    @Override
    public Major select(int majorId) {
        return majorRepository.select(majorId);
    }

    @Override
    public boolean edit(Major major, String logUser) {
        Major existObj = majorRepository.select(major.getMajorId());

        Optional<Major> optional = Optional.fromNullable(existObj);

        if (optional.isPresent()) {
            boolean tmp = majorRepository.update(major);

            if (tmp) {
                LogContent logContent = new LogContent(logUser, "编辑专业" + major.getMajorName(), 1, 4);
                logRepository.insertLog(logContent);
            }

            return tmp;
        }

        return false;
    }

    @Override
    public boolean delete(int majorId, String logUser) {
        Major existObj = majorRepository.select(majorId);

        Optional<Major> optional = Optional.fromNullable(existObj);

        if (optional.isPresent()) {
            boolean tmp = majorRepository.delete(majorId);

            if (tmp) {
                LogContent logContent = new LogContent(logUser, "删除专业ID为" + majorId, 1, 2);
                logRepository.insertLog(logContent);
            }

            return tmp;
        }

        return false;
    }
}
