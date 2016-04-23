package com.lgb.function.admin.finance.export.repository;

import com.lgb.function.admin.finance.Finance;

import java.util.List;

public interface ExportRepositoryI {
    List<Finance> exportFinance(Finance finance);
}
