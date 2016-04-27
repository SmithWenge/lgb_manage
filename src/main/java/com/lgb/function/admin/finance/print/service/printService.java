package com.lgb.function.admin.finance.print.service;

import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.print.repository.printRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class printService implements printServiceI{

    @Autowired
    private printRepositoryI printRepository;
    @Override
    public Finance selectById(int id) {
        return printRepository.selectById(id);
    }

    @Override
    public Page<Finance> selectPrintAll(Finance finance, Pageable pageable) {
        return printRepository.selectPrintAll(finance,pageable);
    }

    @Override
    public Page<Finance> selectPrintBillNum(Finance finance, Pageable pageable) {
        return printRepository.selectPrintBillNum(finance,pageable);
    }
}
