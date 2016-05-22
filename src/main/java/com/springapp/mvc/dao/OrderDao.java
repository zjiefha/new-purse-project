package com.springapp.mvc.dao;

import com.springapp.mvc.bean.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangjiefeng on 16/3/25.
 */
@Repository
public interface OrderDao {

    public int add(Order order);
    public Order find(@Param("id")int id);
    public int update(Order order);
    public List<Order> findAll(@Param("type")int type);

}