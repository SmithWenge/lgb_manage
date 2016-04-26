package com.lgb.function.admin.finance.print.service;

import com.lgb.function.admin.finance.Finance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface printServiceI {
     Finance selectById(int id);
     Page<Finance> selectPrintAll(Finance finance, Pageable pageable);
     Page<Finance> selectPrintBillNum(Finance finance, Pageable pageable);
}
