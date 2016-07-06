package com.springapp.mvc.controller;

import com.springapp.mvc.Utils.CheckParamUtils;
import com.springapp.mvc.Utils.MapResultUtils;
import com.springapp.mvc.bean.Order;
import com.springapp.mvc.bean.Users;
import com.springapp.mvc.consts.ErrorMessage;
import com.springapp.mvc.service.OrderService;
import com.springapp.mvc.service.UsersService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangjiefeng on 16/3/27.
 */
@Controller
@RequestMapping("/51vc/order/")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private UsersService usersService;

    /**
     * 发表订单
     *
     * @param money     金额
     * @param sponsorId 发表者id
     * @param position  地点
     * @return
     */
    @RequestMapping(value = "/issueOrder", method = RequestMethod.POST)
    public
    @ResponseBody
    Map issueOrder(@RequestParam(value = "money") Integer money,
                   @RequestParam(value = "sponsorId") Integer sponsorId,
                   @RequestParam(value = "position") String position,
                   @RequestParam(value = "message", required = false) String message) {
        if (CheckParamUtils.checkParamsNull(money)) {
            return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_MONEY_NULL);
        }
        if (CheckParamUtils.checkParamsNull(sponsorId)) {
            return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_SPONSORID_NULL);
        }
        if (CheckParamUtils.checkParamsNull(position)) {
            return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_POSITION_NULL);
        }
        Users userInfoById = usersService.getUserInfoById(sponsorId);
        if (userInfoById == null) return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_USER_INFO);
        Order order = orderService.issueOrder(money, sponsorId, position, message);
        return MapResultUtils.getSuccessResultMap(order);
    }

    @RequestMapping(value = "/getWaitOrder", method = RequestMethod.GET)
    public
    @ResponseBody
    Map getWaitOrder(@RequestParam(value = "position", required = false) String position,
                     HttpServletRequest httpServletRequest) {
        //根据业务需求加入更新地址的需求
        if (!StringUtils.isBlank(position)) {
            Object userId = httpServletRequest.getAttribute("userId");
            if (!CheckParamUtils.isLogin(userId))
                return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_TOKEN);
            int id = Integer.parseInt(userId.toString());
            usersService.updateUserPosition(id, position);
        }

        List<Order> waitOrder = orderService.getWaitOrder();

        return MapResultUtils.getSuccessResultMap(waitOrder);
    }

    /**
     * 订单接收
     *
     * @param orderId     订单id
     * @param recipientId 接收用户id
     * @return
     */
    @RequestMapping(value = "/acceptOrder", method = RequestMethod.POST)
    public
    @ResponseBody
    Map acceptOrder(@RequestParam(value = "orderId") int orderId,
                    @RequestParam(value = "recipientId") int recipientId) {
        Users userInfoById = usersService.getUserInfoById(recipientId);
        if (CheckParamUtils.checkParamsNull(userInfoById))
            return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_USER_INFO);
        if (!orderService.isAcceptOrder(orderId))
            return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_ORDER_NULL);
        Order order = orderService.acceptOrder(orderId, recipientId);
        return MapResultUtils.getSuccessResultMap(order);
    }

    /**
     * 订单取消
     *
     * @param orderId     订单id
     * @param recipientId 接收用户id
     * @return
     */
    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    public
    @ResponseBody
    Map cancelOrder(@RequestParam(value = "orderId") int orderId,
                    @RequestParam(value = "recipientId") int recipientId) {
        Users userInfoById = usersService.getUserInfoById(recipientId);
        if (CheckParamUtils.checkParamsNull(userInfoById))
            return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_USER_INFO);
        if (!orderService.isAcceptOrderByUserId(orderId, recipientId))
            return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_ORDER_NULL);
        Order order = orderService.cancelOrder(orderId);
        return MapResultUtils.getSuccessResultMap(order);
    }

    /**
     * 放弃订单
     *
     * @param orderId   订单id
     * @param sponsorId 接收用户id
     * @return
     */
    @RequestMapping(value = "/abandonOrder", method = RequestMethod.POST)
    public
    @ResponseBody
    Map abandonOrder(@RequestParam(value = "orderId") int orderId,
                     @RequestParam(value = "sponsorId") int sponsorId) {
        Users userInfoById = usersService.getUserInfoById(sponsorId);
        if (CheckParamUtils.checkParamsNull(userInfoById))
            return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_USER_INFO);
        if (!orderService.isAcceptOrderByUserId(orderId, sponsorId))
            return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_ORDER_NULL);
        Order order = orderService.abandonOrder(orderId);
        return MapResultUtils.getSuccessResultMap(order);
    }


    /**
     * 订单完成
     *
     * @param orderId     订单id
     * @param recipientId 接收用户id
     * @return
     */
    @RequestMapping(value = "/finishedOrder", method = RequestMethod.POST)
    public
    @ResponseBody
    Map finishedOrder(@RequestParam(value = "orderId") int orderId,
                      @RequestParam(value = "recipientId") int recipientId,
                      @RequestParam(value = "evaluation", required = false) String evaluation) {
        Users userInfoById = usersService.getUserInfoById(recipientId);
        if (CheckParamUtils.checkParamsNull(userInfoById))
            return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_USER_INFO);
        if (!orderService.isAcceptOrderByUserId(orderId, recipientId))
            return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_ORDER_NULL);
        Order order = orderService.finishedOrder(orderId, evaluation);
        return MapResultUtils.getSuccessResultMap(order);
    }

    @RequestMapping(value = "/getOrder", method = RequestMethod.GET)
    public
    @ResponseBody
    Map getOrderById(@RequestParam("id") int id) {
        if (CheckParamUtils.checkParamsNull(id))
            return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_ORDER_PARAMS);
        Order order = orderService.getOrder(id);
        return MapResultUtils.getSuccessResultMap(order);
    }

    @RequestMapping(value = "/getOrderByUserId", method = RequestMethod.GET)
    public
    @ResponseBody
    Map getOrderByUserId(@RequestParam("userId") int userId) {
        Users userInfoById = usersService.getUserInfoById(userId);
        if (CheckParamUtils.checkParamsNull(userInfoById))
            return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_USER_INFO);
        List<Order> orderByUserId = orderService.getOrderByUserId(userId);
        return MapResultUtils.getSuccessResultMap(orderByUserId);
    }


}
