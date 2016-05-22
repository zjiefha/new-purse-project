package com.springapp.mvc.dao;

import com.springapp.mvc.bean.Order;
import com.springapp.mvc.consts.Constant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by zhangjiefeng on 16/4/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:spring/applicationContext.xml"})
public class OrderDaoTest {
    @Autowired
    private OrderDao orderDao;

    @Test
    public void testFindAll() {
        List<Order> all = orderDao.findAll(Constant.TYPE_WAIT);
        for (Order order : all) {
            System.out.println(order.getId());
        }
    }
}
