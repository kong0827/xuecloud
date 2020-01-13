package com.cloud.controller;

import com.cloud.annotation.ExceLogAnnotation;
import com.cloud.config.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lee
 * @date 2020/1/10 22:40
 */
@RestController
@RequestMapping("/user")
public class UserController {
    //模拟用户登录操作
    @ExceLogAnnotation(operateType = "用户登录")
    @RequestMapping("/login")
    public void login() throws Exception {
        throw new RuntimeException();
    }

}
