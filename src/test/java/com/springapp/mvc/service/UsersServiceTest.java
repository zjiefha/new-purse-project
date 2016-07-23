package com.springapp.mvc.service;

import com.springapp.mvc.bean.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zhangjiefeng on 16/3/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:spring/applicationContext.xml"})
public class UsersServiceTest {

    @Autowired
    private UsersService usersService;

    @Test
    public void testSignUp() {
        Users users = usersService.signUp("13131", "12121212");
        System.out.println(users);
        System.out.println(users.getId());
    }

    @Test
    public void testFind() {
        Integer i = null;
        Users users = usersService.getUserInfoById(i);
        System.out.println(users);
    }
}
