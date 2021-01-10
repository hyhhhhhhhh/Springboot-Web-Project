package com.eplatform.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;


//扩展springmvc dispatchservlet
//需要扩展springmvc，官方简易这样做：以后还要各种各样Config
@Configuration
@EnableAutoConfiguration
public class MyMvcConfig implements WebMvcConfigurer {

    //视图跳转控制
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/he").setViewName("test");
       // registry.addViewController("/user/login").setViewName("register");
        registry.addViewController("/user/register.html").setViewName("register");
//        registry.addViewController("/user/login").setViewName("/index");
//        registry.addViewController("/main.html").setViewName("dashboard");
//        registry.addViewController("/main/sales.html").setViewName("dashboard-sales");
//        registry.addViewController("/dashboard/modify/dashboard.html").setViewName("/dashboard");
//        registry.addViewController("/dashboard/modify/dashboard-log.html").setViewName("/dashboard-sales");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将url为 /static/** 的请求映射到 /static/ 路径下进行查找
        registry.addResourceHandler("/**").addResourceLocations("classpath:/");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注意/admin/别写成"/admin/**"
        registry.addInterceptor(new AdminLoginHandlerInterceptor()).addPathPatterns("/main.html",
                "/main/sales.html","/dashboard",
                "/dashboard.html","/dashboard-sales",
                "/dashboard-log.html","/dashboard/**");

        //registry.addInterceptor(new LoginHandlerInterceptor()).excludePathPatterns("/admin");
    }


}
