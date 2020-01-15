package com.cloud.config.exception;

import com.cloud.config.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author kxj
 * @date 2020/1/4 10:57
 * @Desc 全局异常处理类
 */
@RestControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public Response customerException(CustomException e) {
        if (e.getCode() == CustomExceptionType.SYSTEM_ERROR.getCode()) {
            //400异常不需要持久化，将异常信息以友好的方式告知用户就可以

            //TODO 将500异常信息持久化处理，方便运维人员处理
        }
        return Response.error(e);
    }

    @ExceptionHandler(Exception.class)
    public Response exception(Exception e) {
        //TODO 将异常信息持久化处理，方便运维人员处理
        //没有被程序员发现，并转换为CustomException的异常，都是其他异常或者未知异常.
        return Response.error(new CustomException(CustomExceptionType.OTHER_ERROR, "未知异常"));
    }

}
