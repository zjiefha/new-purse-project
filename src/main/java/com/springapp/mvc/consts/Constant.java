package com.springapp.mvc.consts;

/**
 * Created by zhangjiefeng on 16/3/27.
 */
public class Constant {
    /**
     * 订单状态
     */
    //订单放弃
    public static final int TYPE_ABANDON = -1;
    //等待处理
    public static final int TYPE_WAIT = 0;
    //接收订单
    public static final int TYPE_ACCEPT = 1;
    //订单完成
    public static final int TYPE_FINISHED = 2;
    //订单过期
    public static final int TYPE_OVERDUE = 3;

    /**
     * 用户类型
     */
    public static final int USER_ALL = 0;
    public static final int USER_SPONSOR = 1;
    public static final int USER_RECIPIENT = 2;


}
