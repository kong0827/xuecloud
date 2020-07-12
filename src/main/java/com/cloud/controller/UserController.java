package com.cloud.controller;

import com.cloud.annotation.ExceptionLogAnnotation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lee
 * @date 2020/1/10 22:40
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户操作")
public class UserController {
    //模拟用户登录操作
    @ExceptionLogAnnotation(operateType = "用户登录")
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ApiOperation(value = "登陆方法")
    public void login() throws Exception {
        System.out.println("----");
        throw new RuntimeException();
    }

}
