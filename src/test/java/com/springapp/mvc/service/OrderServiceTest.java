package com.springapp.mvc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zhangjiefeng on 16/3/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:spring/applicationContext.xml"})
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void testIssueOrder() {
//        orderService.issueOrder(1000,1,null);
    }

}
