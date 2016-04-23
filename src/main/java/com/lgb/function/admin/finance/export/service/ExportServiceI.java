package com.lgb.function.admin.finance.export.service;

import com.lgb.function.admin.finance.Finance;
import java.util.List;

public interface ExportServiceI {
    List<Finance> exportFinance(Finance finance);
}
