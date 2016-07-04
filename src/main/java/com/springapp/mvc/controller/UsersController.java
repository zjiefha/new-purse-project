package com.springapp.mvc.controller;

import com.alibaba.fastjson.JSONObject;
import com.springapp.mvc.Utils.CheckParamUtils;
import com.springapp.mvc.Utils.JwtUtil;
import com.springapp.mvc.Utils.MapResultUtils;
import com.springapp.mvc.bean.Users;
import com.springapp.mvc.consts.ErrorMessage;
import com.springapp.mvc.service.UsersService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.BitSet;
import java.util.Map;

/**
 * Created by zhangjiefeng on 16/3/26.
 */
@Controller
@RequestMapping("/51vc/user/")
public class UsersController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    public UsersService usersService;

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public
    @ResponseBody
    Map signUp(@RequestParam(value = "mobile") String mobile,
               @RequestParam(value = "password") String password) {

        if (!CheckParamUtils.isValidMobile(mobile)) return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_MOBILE);
        if (!CheckParamUtils.isValidPassword(password))
            return MapResultUtils.getErrorResultMap(ErrorMessage.ERROE_PASSWORD);
        if (usersService.existMobile(mobile)) return MapResultUtils.getErrorResultMap(ErrorMessage.EXIST_MOBILE);
        Users users = usersService.signUp(mobile, password);
        if (users == null) return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_SIGNUP);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", users.getId());
        String jwt = JwtUtil.getJwt(jsonObject.toString());
        JSONObject result = new JSONObject();
        result.put("auth_token", jwt);
        result.put("id", users.getId());
        return MapResultUtils.getSuccessResultMap(result);
    }


    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public
    @ResponseBody
    Map signIn(@RequestParam(value = "mobile") String mobile,
               @RequestParam(value = "password") String password) {

        if (!CheckParamUtils.isValidMobile(mobile)) return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_MOBILE);
        if (!CheckParamUtils.isValidPassword(password))
            return MapResultUtils.getErrorResultMap(ErrorMessage.ERROE_PASSWORD);
        if (!usersService.existMobile(mobile)) return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_EXIST_MOBILE);
        Users users = usersService.signIn(mobile, password);
        if (users == null) return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_LOGIN);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", users.getId());
        String jwt = JwtUtil.getJwt(jsonObject.toString());
        JSONObject result = new JSONObject();
        result.put("auth_token", jwt);
        result.put("id", users.getId());
        return MapResultUtils.getSuccessResultMap(result);
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public
    @ResponseBody
    Map getUserInfo(HttpServletRequest httpServletRequest) {
        Object userId = httpServletRequest.getAttribute("userId");
        if (!CheckParamUtils.isLogin(userId))
            return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_TOKEN);
        int id = Integer.parseInt(userId.toString());

        Users user = usersService.getUserInfoById(id);
        if (user == null) return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_USER_INFO);
        return MapResultUtils.getSuccessResultMap(user);
    }


    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public
    @ResponseBody
    Map updateUserInfo(@RequestParam(value = "nickname", required = false) String nickName,
                       @RequestParam(value = "mobile", required = false) String mobile,
                       @RequestParam(value = "school", required = false) String school,
                       HttpServletRequest httpServletRequest) {

        if (mobile != null) {
            if (!CheckParamUtils.isValidMobile(mobile))
                return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_MOBILE);
            if (usersService.existMobile(mobile))
                return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_UPDATE_MOBILE);
        }
        Object userId = httpServletRequest.getAttribute("userId");
        if (!CheckParamUtils.isLogin(userId))
            return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_TOKEN);
        int id = Integer.parseInt(userId.toString());
        if (StringUtils.isBlank(nickName) && StringUtils.isBlank(mobile) && StringUtils.isBlank(school))
            return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_NULL);

        Users users = usersService.update(id, nickName, mobile, school);
        if (users == null) return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_UPDATE_USER_INFO);
        return MapResultUtils.getSuccessResultMap(users);
    }

    @RequestMapping(value = "/updateUserPosition", method = RequestMethod.POST)
    public
    @ResponseBody
    Map updateUserPosition(@RequestParam(value = "position", required = true) String position,
                           HttpServletRequest httpServletRequest) {

        Object userId = httpServletRequest.getAttribute("userId");
        if (!CheckParamUtils.isLogin(userId))
            return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_TOKEN);
        int id = Integer.parseInt(userId.toString());

        if (StringUtils.isBlank(position))
            return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_NULL);

        Users users = usersService.updateUserPosition(id, position);
        if (users == null) return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_UPDATE_USER_INFO);
        return MapResultUtils.getSuccessResultMap(users);
    }

    @RequestMapping(value = "/updateUserPassword", method = RequestMethod.POST)
    public
    @ResponseBody
    Map updateUserPassword(@RequestParam(value = "password", required = true) String password,
                           HttpServletRequest httpServletRequest) {

        Object userId = httpServletRequest.getAttribute("userId");
        if (!CheckParamUtils.isLogin(userId))
            return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_TOKEN);
        int id = Integer.parseInt(userId.toString());

        if (StringUtils.isBlank(password))
            return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_NULL);

        Users users = usersService.updateUserPassword(id, password);
        if (users == null) return MapResultUtils.getErrorResultMap(ErrorMessage.ERROR_UPDATE_PASSWORD);
        return MapResultUtils.getSuccessResultMap(users);
    }


    public static void main(String[] args) {
        String n = null;
        boolean b = CheckParamUtils.checkParamsNull(n);
        System.out.println(b);

    }

}
