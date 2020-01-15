package com.cloud.config.exception;

import lombok.NoArgsConstructor;
/**
 * @author 小k
 * @date 2020/1/3 21/42
 * @desc自定义异常类
 */
@NoArgsConstructor
public class CustomException extends RuntimeException {
    /**
     * 异常错误编码
     */
    private int code;
    /**
     * 异常信息
     */
    private String message;
    /**
     * 额外数据，支持扩展
     */
    private Object data;

    public CustomException(CustomExceptionType exceptionTypeEnum, String message) {
        this.code = exceptionTypeEnum.getCode();
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
