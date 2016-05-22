package com.springapp.mvc.dao;

import com.springapp.mvc.bean.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zhangjiefeng on 16/3/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:spring/applicationContext.xml"})
public class UsersDaoTest {

    @Autowired
    private UsersDao usersDao;

    @Test
    public void testAdd() {
        Users users = new Users();
        users.setMobile("18613889203");
        users.setPassword("18613889203");
        System.out.println(users.getId());
        int add = usersDao.add(users);
        System.out.println(users.getId());
        System.out.println(add);
    }

    @Test
    public void testFind() {
        Users users = usersDao.find(1);
        System.out.println(users.getId()+"--"+users.getMobile()+"--"+users.getNickName()+"--"+users.getSchool());
    }

    @Test
    public void testUpdate() {
        Users users = new Users();
        users.setId(19);
        users.setSchool("dfdfdf");
        int update = usersDao.update(users);
        System.out.println(update);
    }

    @Test
    public void testExistMobile() {
        String mobile = "1861388903";
        Integer i = usersDao.existMobile(mobile);
        System.out.println(i);
    }

    public static void main(String[] args) {

    }
}
