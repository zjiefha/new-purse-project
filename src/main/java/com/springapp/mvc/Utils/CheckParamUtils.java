package com.springapp.mvc.Utils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangjiefeng on 16/3/26.
 */
public class CheckParamUtils {

    public static boolean checkParamsNull(Object... objects) {
        boolean result = false;
        if (objects.length <= 0) {
            return result;
        }
        for (Object object : objects) {
            if (object instanceof String) {
                result = (result || StringUtils.isBlank((String) object));
            } else if (object instanceof List) {
                result = (result || ((List) object).size() == 0);
            } else {
                result = (result || object == null);
            }
        }
        return result;
    }

    public static boolean isValidMobile(String mobile) {
        String regExp = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(mobile);
        return m.find();
    }

    public static boolean isValidPassword(String password) {
        String regExp = "^[0-9a-zA-Z]{6,16}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(password);
        return m.find();
    }

    public static boolean isLogin(String userId) {
        try {
            Integer.parseInt(userId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static void main(String[] args) {
        boolean validMobile = isLogin("1");
        System.out.println(validMobile);
    }
}
