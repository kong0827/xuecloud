package com.cloud.utils;

/**
 * @author Lee
 * @date 2020/1/13 22:44
 */
public class ExceptionUtils {

    /**
     * 将异常信息转化为字符串
     * @param exceptionName     异常名
     * @param exceptionMessage  异常信息
     * @param elements          栈信息
     * @return
     */
    public static String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strBuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strBuff.append(stet + "\n");
        }

        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strBuff.toString();
        return message;
    }
 }
