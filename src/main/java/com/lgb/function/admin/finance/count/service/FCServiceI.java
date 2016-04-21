package com.lgb.function.admin.finance.count.service;

import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.count.model.InfoCount;
import com.lgb.function.admin.finance.count.model.JsonModel;

import java.util.List;

public interface FCServiceI {
    InfoCount querySumOfActualTuition();
    InfoCount queryDaySumActualTuition();
    List<JsonModel> queryMonthSumFinance(Finance finance);

}
