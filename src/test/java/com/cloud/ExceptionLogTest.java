package com.cloud;

import com.cloud.dao.ExceptionLogRepository;
import com.cloud.entity.ExceptionLog;
import com.cloud.service.ExceptionLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Lee
 * @date 2020/1/8 23:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExceptionLogTest {

    @Autowired
    private ExceptionLogRepository exceptionLogRepository;

    @Autowired
    private ExceptionLogService exceptionLogService;

    @Test
    public void TestOne(){
        ExceptionLog log=new ExceptionLog();
        log.setId((long) 10);
        log.setIp("192.168.1.1");
        log.setIpSource("上海市浦东新区");
        log.setBrowserType("chrome");
        log.setDescription("登录系统");
        ExceptionLog save = exceptionLogRepository.save(log);
        System.out.println(save.toString());
    }

    @Test
    public void TestAnno(){
        List<ExceptionLog> allLogs = exceptionLogService.getAllLog();
    }
}
