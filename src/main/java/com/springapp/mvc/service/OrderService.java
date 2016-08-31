package com.springapp.mvc.service;

import com.springapp.mvc.Utils.CheckParamUtils;
import com.springapp.mvc.bean.Order;
import com.springapp.mvc.consts.Constant;
import com.springapp.mvc.dao.OrderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangjiefeng on 16/3/26.
 */
@Service
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderDao orderDao;

    public Order getOrder(int id) {
        Order order = orderDao.find(id);
        if (CheckParamUtils.checkParamsNull(order)) return null;
        return order;
    }

    public List<Order> getOrderBySponsorId(int userId, int start, int pageSize) {
        List<Order> bySponsorId = orderDao.findBySponsorId(userId,start,pageSize);
        if (CheckParamUtils.checkParamsNull(bySponsorId)) return null;
        return bySponsorId;
    }

    public List<Order> getOrderByRecipientId(int userId, int start, int pageSize) {
        List<Order> bySponsorId = orderDao.findByRecipientId(userId,start,pageSize);
        if (CheckParamUtils.checkParamsNull(bySponsorId)) return null;
        return bySponsorId;
    }

    /**
     * 订单发布
     *
     * @param money      金额
     * @param sponsor_id 发布者id
     * @param position   发布者位置
     * @return
     */
    public Order issueOrder(int money, int sponsor_id, String position, String message, int tip) {
        Order order = new Order();
        order.setSponsorId(sponsor_id);
        order.setMoney(money);
        order.setType(Constant.TYPE_WAIT);
        order.setMessage(message);
        order.setPosition(position);
        order.setTip(tip);
        int add = orderDao.add(order);
        if (add == 1) {
            return order;
        }
        return null;
    }

    /**
     * 获取未完成订单
     *
     * @return
     */
    public List<Order> getWaitOrder() {
        //根据业务需求加入时间限制
        Date time = new Date(new Date().getTime() - 1000 * 3600 * 2);
        List<Order> allOrder = orderDao.findAllByTime(Constant.TYPE_WAIT, time);
        if (allOrder == null) return null;
        return allOrder;
    }

    /**
     * 接受订单
     *
     * @param id          订单id
     * @param recipientId 接收用户id
     * @return
     */
    public Order acceptOrder(int id, int recipientId) {
        Order order = new Order();
        order.setId(id);
        order.setRecipientId(recipientId);
        order.setType(Constant.TYPE_ACCEPT);
        int update = orderDao.update(order);
        if (update == 1) return order;
        return null;
    }

    /**
     * 完成订单
     *
     * @param id 订单id
     * @return
     */
    public Order finishedOrder(int id) {
        Order order = new Order();
        order.setId(id);
        order.setType(Constant.TYPE_FINISHED);
        int update = orderDao.update(order);
        if (update == 1) return order;
        return null;
    }

    /**
     * 评价订单
     *
     * @param id         订单id
     * @param evaluation 订单评价
     * @return
     */
    public Order commentOrder(int id, String evaluation) {
        if (CheckParamUtils.checkParamsNull(evaluation))
            return null;
        Order order = new Order();
        order.setId(id);
        order.setEvaluation(evaluation);
        int update = orderDao.update(order);
        if (update == 1) return order;
        return null;
    }


    /**
     * 取消接收接单
     *
     * @param id 订单id
     * @return
     */
    public Order cancelOrder(int id) {
        Order order = new Order();
        order.setId(id);
        order.setRecipientId(0);
        order.setType(Constant.TYPE_WAIT);
        int update = orderDao.update(order);
        if (update == 1) return order;
        return null;
    }

    /**
     * 放弃接单
     *
     * @param id 订单id
     * @return
     */
    public Order abandonOrder(int id) {
        Order order = new Order();
        order.setId(id);
        order.setRecipientId(0);
        order.setType(Constant.TYPE_ABANDON);
        int update = orderDao.update(order);
        if (update == 1) return order;
        return null;
    }

    /**
     * 查询订单是否可以接收
     */
    public boolean isAcceptOrder(int orderId) {
        Order order = orderDao.find(orderId);
        if (order != null && order.getType() == Constant.TYPE_WAIT) return true;
        return false;
    }

    /**
     * 查询订单是否被某个用户接收
     */
    public boolean isAcceptOrderByUserId(int orderId, int recipientId) {
        Order order = orderDao.find(orderId);
        if (order != null && order.getType() == Constant.TYPE_ACCEPT && order.getRecipientId() == recipientId)
            return true;
        return false;
    }

    /**
     * 查询订单是否被某个用户发布
     */
    public boolean isOrderSponsor(int orderId, int sponsorId) {
        Order order = orderDao.find(orderId);
        if (order != null && order.getSponsorId() == sponsorId)
            return true;
        return false;
    }
}
