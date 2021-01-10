package com.eplatform.controller;


import com.eplatform.pojo.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class IndexController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping({"/index","/index.html",""})
    public  String index(
            Model model
    ){

        //加载数据库里面的商品信息
        String sql = "Select * from commodity";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        model.addAttribute("data",maps);


        return "index";
    }

    @GetMapping({"/Login","/Login.html","/register"})
    public  String contactUs(){
        return "register";

    }









    //test-part--------------------------------------
    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("msg","helloSpringBoot");

        return "test";
    }
    //test-part--------------------------------------
}
