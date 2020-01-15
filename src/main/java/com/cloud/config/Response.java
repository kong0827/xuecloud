package com.cloud.config;

import com.cloud.config.exception.CustomException;
import com.cloud.config.exception.CustomExceptionType;
import lombok.Data;

@Data
public class Response {

    private boolean isok;
    private int code;
    private String message;
    private Object data;

    /**
     * 请求异常时的响应数据封装
     * @param e 异常
     * @return
     */
    public static Response error(CustomException e) {

        Response resultBean = new Response();
        resultBean.setIsok(false);
        resultBean.setCode(e.getCode());
        if (e.getCode() == CustomExceptionType.USER_INPUT_ERROR.getCode()) {
            resultBean.setMessage(e.getMessage());
        } else if (e.getCode() == CustomExceptionType.SYSTEM_ERROR.getCode()) {
            resultBean.setMessage(e.getMessage() + ",系统出现异常，请联系管理员电话：188888xxxx进行处理!");
        } else {
            resultBean.setMessage("系统出现未知异常，请联系管理员电话：188888xxxx进行处理!");
        }
        return resultBean;
    }

    /**
     * 请求成功响应(无响应数据)
     * @return
     */
    public static Response success() {
        Response resultBean = new Response();
        resultBean.setIsok(true);
        resultBean.setCode(Constant.SUCCESS_CODE);
        resultBean.setMessage(Constant.SUCCESS_MESSAGE);
        return resultBean;
    }

    /**
     * 请求成功响应(有响应数据)
     * @return
     */
    public static Response success(Object data) {
        Response resultBean = new Response();
        resultBean.setIsok(true);
        resultBean.setCode(Constant.SUCCESS_CODE);
        resultBean.setMessage(Constant.SUCCESS_MESSAGE);
        resultBean.setData(data);
        return resultBean;
    }


}