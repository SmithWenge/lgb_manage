package com.lgb.function.admin.disciplinary.service;

import com.lgb.function.admin.disciplinary.Disciplinary;
import com.lgb.function.admin.disciplinary.repository.DisciplinaryRepositoryI;
import com.lgb.function.support.log.LogContent;
import com.lgb.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaryService implements DisciplinaryServiceI {
    @Autowired
    private DisciplinaryRepositoryI disciplinaryRepository;
    @Autowired
    private LogRepositoryI logRepository;

    @Override
    public Page<Disciplinary> query4page(Disciplinary disciplinary,Pageable pageable) {
        return disciplinaryRepository.query4Page(disciplinary, pageable);
    }
    @Override
    public Page<Disciplinary> query4Count(Disciplinary disciplinary,Pageable pageable) {
        return disciplinaryRepository.query4Count(disciplinary, pageable);
    }
    @Override
    public List<Disciplinary> queryMore(int stuId) {
        return disciplinaryRepository.queryMore(stuId);
    }

    @Override
    public boolean addDis(Disciplinary disciplinary, String logUser) {

        boolean tmp = disciplinaryRepository.insert(disciplinary);

        if (tmp) {
            disciplinaryRepository.update(disciplinary);

            LogContent logContent = new LogContent(logUser, "添加违纪学员" + disciplinary.getStuName(), 1, 3);
            logRepository.insertLog(logContent);
        }
        return tmp;
    }
    @Override
    public boolean existCardNum(Disciplinary disciplinary) {
        return disciplinaryRepository.query(disciplinary) == 0 ? true : false;
    }
}
