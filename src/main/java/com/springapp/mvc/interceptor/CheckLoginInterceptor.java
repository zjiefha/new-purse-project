package com.springapp.mvc.interceptor;

import com.springapp.mvc.Utils.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by zhangjiefeng on 16/4/3.
 */

public class CheckLoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String auth_token = httpServletRequest.getHeader("x-51-vc");
        if (auth_token == null) {
            return true;
        } else {
            try {
                Map<String, Object> stringObjectMap = JwtUtil.parseJwt(auth_token);
                Object id = stringObjectMap.get("id");
                httpServletRequest.setAttribute("userId", id);
            } catch (Exception e) {
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("2");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("3");
    }
}
