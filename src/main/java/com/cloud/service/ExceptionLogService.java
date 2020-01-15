package com.cloud.service;

import com.cloud.entity.ExceptionLog;

import java.util.List;

public interface ExceptionLogService {

    /**
     * 获取所有的异常日志
     * @return 异常日志列表
     */
    public List<ExceptionLog> getAllLog();
}
