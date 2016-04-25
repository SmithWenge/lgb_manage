package com.lgb.function.admin.finance.print.repository;

import com.lgb.function.admin.finance.Finance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface printRepositoryI {
     Finance selectById(int id);
     Page<Finance> selectPrintAll(Finance finance, Pageable pageable);
}
