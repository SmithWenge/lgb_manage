package com.lgb.function.admin.finance.financeCount.service;

import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.financeCount.model.InfoCount;
import com.lgb.function.admin.finance.financeCount.model.JsonModel;

import java.util.List;

public interface FCServiceI {
    InfoCount querySumOfActualTuition();
    InfoCount queryDaySumActualTuition();
    List<JsonModel> queryMonthSumFinance(Finance finance);

}
