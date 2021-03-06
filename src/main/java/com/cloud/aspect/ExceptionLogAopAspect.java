package com.cloud.aspect;


import com.cloud.annotation.ExceptionLogAnnotation;
import com.cloud.dao.ExceptionLogRepository;
import com.cloud.entity.ExceptionLog;
import com.cloud.utils.ExceptionUtils;
import com.cloud.utils.HttpContextUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Lee
 * @date 2020/1/9 23:48
 */
@Aspect
@Component
public class ExceptionLogAopAspect {

    @Autowired
    private ExceptionLogRepository exceptionLogRepository;

    /**
     * 设置操作异常切入点记录异常日志 扫描所有controller包下操作
     */
    @Pointcut("execution(* com.cloud.controller..*.*(..))")
      public void exceptionLogAopAspect() {
    }

    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.cloud.annotation.ExceptionLogAnnotation)")
     public void exceptionLogPointCut() { }

    @AfterThrowing(value = "exceptionLogPointCut()",throwing = "e")
    public Object aroundAdvice(JoinPoint joinPoint, Throwable e) throws Throwable {
        // 1.方法执行前的处理，相当于前置通知
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 获取方法
        Method method = methodSignature.getMethod();
        // 获取方法上面的注解
        ExceptionLogAnnotation logAnnotation = method.getAnnotation(ExceptionLogAnnotation.class);
        // 获取操作描述的属性值
        String operateDesc = logAnnotation.operateType();
        // 创建一个日志对象(准备记录日志)
        ExceptionLog log = new ExceptionLog();
        // 操作说明
        log.setDescription(operateDesc);
        //获取抛出的异常信息
        log.setExcDetail(ExceptionUtils.stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace()).substring(0,1200));
        String ip = HttpContextUtil.getIpAddress();
        //设置ip
        log.setIp(ip);
        //设置浏览器类型
        log.setBrowserType(HttpContextUtil.getBrowser());
        //设置请求参数
        log.setReqParam(HttpContextUtil.getParam());
        // 设置操作日期
        log.setCreateDate(LocalDateTime.now());
        //getAddresses 设置请求归属地
        log.setIpSource(HttpContextUtil.getAddresses(ip));
        // 添加日志记录
        return exceptionLogRepository.save(log);
    }
}
