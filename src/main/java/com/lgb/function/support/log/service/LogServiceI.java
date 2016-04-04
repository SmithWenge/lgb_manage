package com.lgb.function.support.log.service;

import com.lgb.function.support.log.LogContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LogServiceI {
    Page<LogContent> query4Page(LogContent logContent, Pageable pageable);
}
