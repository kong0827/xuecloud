package com.cloud.utils;

/**
 * @author Lee
 * @date 2020/1/13 22:44
 */
public class ExceptionUtils {

    /**
     * 将异常信息转化为字符串
     * @param exceptionName
     * @param exceptionMessage
     * @param elements
     * @return
     */
    public static String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet + "\n");
        }
        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
        return message;
    }
 }
