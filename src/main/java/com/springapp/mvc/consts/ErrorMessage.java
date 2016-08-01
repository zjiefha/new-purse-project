package com.springapp.mvc.consts;

/**
 * Created by zhangjiefeng on 16/3/26.
 */
public class ErrorMessage {
    /**
     * 错误信息
     */
    //错误登录信息
    private Integer errorCode;
    private String errorMessage;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorMessage(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    //成功状态
    public static final ErrorMessage SUCCESS = new ErrorMessage(0, "");

    /**
     * 用户错误提示信息
     */
    public static final ErrorMessage TO_LOGIN = new ErrorMessage(501, "请先登录");
    public static final ErrorMessage ERROR_MOBILE = new ErrorMessage(502, "手机号格式错误");
    public static final ErrorMessage ERROE_PASSWORD = new ErrorMessage(503, "密码格式错误");
    public static final ErrorMessage ERROR_SIGNUP = new ErrorMessage(504, "注册失败");
    public static final ErrorMessage ERROR_SIGNIN = new ErrorMessage(505, "登录失败");
    public static final ErrorMessage ERROR_EXIST_MOBILE = new ErrorMessage(506, "手机号不存在");
    public static final ErrorMessage EXIST_MOBILE = new ErrorMessage(507, "手机号已存在,请直接登录");
    public static final ErrorMessage ERROR_LOGIN = new ErrorMessage(508, "账号或者密码错误");
    public static final ErrorMessage ERROR_TOKEN = new ErrorMessage(509, "token错误,请重新登录");
    public static final ErrorMessage ERROR_USER_INFO = new ErrorMessage(510, "用户数据获取失败或者用户不存在");
    public static final ErrorMessage ERROR_UPDATE_USER_INFO = new ErrorMessage(511, "用户数据修改失败");
    public static final ErrorMessage ERROR_NULL = new ErrorMessage(512, "参数不能全为空");
    public static final ErrorMessage ERROR_UPDATE_MOBILE = new ErrorMessage(513, "手机号码已存在，无法修改");
    public static final ErrorMessage ERROR_UPDATE_PASSWORD = new ErrorMessage(513, "密码修改失败");

    public static final ErrorMessage ERROR_MONEY_NULL = new ErrorMessage(520, "金钱不能为空");
    public static final ErrorMessage ERROR_SPONSORID_NULL = new ErrorMessage(521, "用户为空");
    public static final ErrorMessage ERROR_POSITION_NULL = new ErrorMessage(522, "地址为空");
    public static final ErrorMessage ERROR_ORDER_NULL = new ErrorMessage(523, "订单不存在");
    public static final ErrorMessage ERROR_ORDER_PARAMS = new ErrorMessage(523, "参数错误");

    public static final ErrorMessage ERROR_ORDER_CONT_ABANDON = new ErrorMessage(530, "订单不能放弃");
    public static final ErrorMessage ERROR_ORDER_CONT_COMMENT = new ErrorMessage(531, "订单不是完成状态或者不是发表者");
    public static final ErrorMessage ERROR_COMMENT = new ErrorMessage(532, "评论失败");
    public static final ErrorMessage ERROR_FINISHED = new ErrorMessage(533, "完成失败");


}
