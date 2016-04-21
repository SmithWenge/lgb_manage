package com.lgb.function.admin.finance.count.service;

import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.count.model.InfoCount;
import com.lgb.function.admin.finance.count.model.JsonModel;
import com.lgb.function.admin.finance.count.repository.FCRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FCService implements FCServiceI{
    @Autowired
    private FCRepositoryI fcRepository;
    @Override
    public InfoCount querySumOfActualTuition() {
        return fcRepository.querySumOfActualTuition();
    }

    @Override
    public InfoCount queryDaySumActualTuition() {
        return fcRepository.queryDaySumActualTuition();
    }

    @Override
    public List<JsonModel> queryMonthSumFinance(Finance finance) {
        return fcRepository.queryMonthSumFinance(finance);
    }
}
