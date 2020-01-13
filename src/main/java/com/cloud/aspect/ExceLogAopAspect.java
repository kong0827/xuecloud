package com.cloud.aspect;

import com.cloud.annotation.ExceLogAnnotation;
import com.cloud.dao.ExceptionLogRepository;
import com.cloud.entity.ExceptionLog;
import com.cloud.utils.ExceptionUtils;
import com.cloud.utils.HttpContextUtil;
import net.bytebuddy.implementation.bytecode.Throw;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author Lee
 * @date 2020/1/9 23:48
 */
@Aspect
@Component
public class ExceLogAopAspect {
    @Autowired
    private ExceptionLogRepository exceptionLogRepository;

    /**
     * 设置操作异常切入点记录异常日志 扫描所有controller包下操作
     */
    @Pointcut("execution(* com.cloud.controller..*.*(..))")
      public void ExceLogAopAspect() {

    }

    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.cloud.annotation.ExceLogAnnotation)")
     public void exceLogPoinCut() { }

    @AfterThrowing(value = "exceLogPoinCut()",throwing = "e")
    public Object aroundAdvice(JoinPoint pjp, Throwable e) throws Throwable {
        // 1.方法执行前的处理，相当于前置通知
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        // 获取方法
        Method method = methodSignature.getMethod();
        // 获取方法上面的注解
        ExceLogAnnotation logAnno = method.getAnnotation(ExceLogAnnotation.class);
        // 获取操作描述的属性值
        String operatedesc = logAnno.operateType();
        // 创建一个日志对象(准备记录日志)
        ExceptionLog log = new ExceptionLog();
        log.setDescription(operatedesc);// 操作说明
        log.setExcdetailt(ExceptionUtils.stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace()).substring(0,1200));//获取抛出的异常信息
        String ip = HttpContextUtil.getIpAddress();
        log.setIp(ip); //设置ip
        log.setBrowsertype(HttpContextUtil.getBrowser());//设置浏览器内存
        log.setReqparam(HttpContextUtil.getParam());  //设置请求参数
        log.setCreatedate(new Date());// 设置操作日期
        log.setIpsource(HttpContextUtil.getAddresses(ip));//getAddresses 设置请求归属地
        return exceptionLogRepository.save(log);// 添加日志记录
    }
}
