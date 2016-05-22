package com.springapp.mvc.Utils;

import com.alibaba.fastjson.JSONObject;
import com.springapp.mvc.bean.Users;
import com.springapp.mvc.consts.ErrorMessage;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ErrorMessages;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangjiefeng on 16/3/26.
 */
public class MapResultUtils {

    //状态 0:success -1:失败
    public static final String STATUS_KEY = "status";
    public static final String RESULT = "result";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String ERROR_CODE = "errorCode";

    //成功返回结果
    public static final int SUCCESS = 0;
    public static final int ERROR = -1;


    public static JSONObject getSuccessResultMap(Object result) {
        return getResultMap(SUCCESS, ErrorMessage.SUCCESS, result);
    }


    public static JSONObject getErrorResultMap(ErrorMessage errorMessage) {
        return getResultMap(ERROR, errorMessage, null);
    }

    public static JSONObject getResultMap(int status, ErrorMessage errorMessage, Object result) {
        JSONObject map = new JSONObject();
        map.put(STATUS_KEY, status);
        map.put(RESULT, result);
        map.put(ERROR_MESSAGE, errorMessage.getErrorMessage());
        map.put(ERROR_CODE, errorMessage.getErrorCode());
        return map;
    }

    public static void main(String[] args) {

    }


}
