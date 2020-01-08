package com.cloud.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 小K
 * @date 2020/1/3 21:43
 * @desc 自定义异常类枚举
 */
@Getter
public enum CustomExceptionType {
    /**
     * 用户输入异常
     */
    USER_INPUT_ERROR(400, "用户输入异常"),
    /**
     * 系统服务异常
     */
    SYSTEM_ERROR(500, "系统服务异常"),
    /**
     * 其他未知异常
     */
    OTHER_ERROR(999, "其他未知异常");

    CustomExceptionType(int code, String typeDesc) {
        this.code = code;
        this.typeDesc = typeDesc;
    }

    /**
     * 异常类型中文描述
     */
    private String typeDesc;

    /**
     * HTTP状态码
     */
    private int code;

}