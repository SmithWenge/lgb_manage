package com.lgb.function.admin.finance.export.service;

import com.lgb.function.admin.finance.Finance;
import com.lgb.function.admin.finance.export.repository.ExportRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportService implements ExportServiceI{

    @Autowired
    ExportRepositoryI exportRepository;
    @Override
    public List<Finance> exportFinance(Finance finance) {
        return exportRepository.exportFinance(finance);
    }
}
