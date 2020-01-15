package com.cloud.service.impl;

import com.cloud.config.exception.CustomException;
import com.cloud.config.exception.CustomExceptionType;
import com.cloud.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author kxj
 * @date 2020/1/4 15:49
 * @Desc
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public String test(int num) {
        if (num < 0) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "输入异常");
        }
        return "xx";
    }
}
