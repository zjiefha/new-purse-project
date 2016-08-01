package com.springapp.mvc.dao;

import com.springapp.mvc.bean.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangjiefeng on 16/3/25.
 */
@Repository
public interface OrderDao {

    public int add(Order order);

    public Order find(@Param("id") int id);

    public List<Order> findBySponsorId(@Param("sponsorId") int sponsorId);

    public List<Order> findByRecipientId(@Param("recipientId") int recipientId);

    public int update(Order order);

    public List<Order> findAll(@Param("type") int type);

    public List<Order> findAllByTime(@Param("type") int type, @Param("time") Date time);

}
