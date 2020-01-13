package com.cloud.service.impl;

import com.cloud.dao.ExceptionLogRepository;
import com.cloud.entity.ExceptionLog;
import com.cloud.service.ExceptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExceptionLogServiceImpl implements ExceptionLogService {

    @Autowired
    private ExceptionLogRepository exceptionLogRepository;

    @Override
    public List<ExceptionLog> getAllLog() {
        return exceptionLogRepository.findAll();
    }
}
