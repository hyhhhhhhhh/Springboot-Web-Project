package com.eplatform.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@EnableAutoConfiguration
public class AdminLoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录成功之后，取得用户Sesion

        Object loginUser= request.getSession().getAttribute("loginAdmin");
        if (loginUser==null){
            request.setAttribute("msg","没有权限请先登录");
            request.getRequestDispatcher("/admin").forward(request,response);
            return false;
        }else {
            return true;
        }
    }


}
