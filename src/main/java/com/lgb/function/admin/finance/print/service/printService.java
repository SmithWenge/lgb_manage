package com.lgb.function.admin.finance.print.service;

import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.print.repository.printRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class printService implements printServiceI{

    @Autowired
    private printRepositoryI printRepository;
    @Override
    public Finance selectById(int id) {
        return printRepository.selectById(id);
    }
}
