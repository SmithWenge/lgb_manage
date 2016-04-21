package com.lgb.function.admin.finance.count.repository;

import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.count.model.InfoCount;
import com.lgb.function.admin.finance.count.model.JsonModel;

import java.util.List;

public interface FCRepositoryI {
    InfoCount querySumOfActualTuition();
    InfoCount queryDaySumActualTuition();
    List<JsonModel> queryMonthSumFinance(Finance finance);
}
