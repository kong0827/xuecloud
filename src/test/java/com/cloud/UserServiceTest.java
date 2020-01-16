package com.cloud;

import com.cloud.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kxj
 * @date 2020/1/4 15:51
 * @Desc
 */
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void test() {
        List<String> list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        List<String> collect = list.stream().filter(item -> item == "2").collect(Collectors.toList());
        System.out.println(collect);

    }
}
