package com.eplatform.controller;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class RegisterController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("/user/register")
    public String userRegister(
            @RequestParam("username")String username,
            @RequestParam("password") String password,
            Model model)
    {
        //具体的业务
        //username password是否在数据库里面
        String sql="select * from userinfo where mail="+"'"+username+"'";


        try {

            Map<String,Object>validation = jdbcTemplate.queryForMap(sql);
            model.addAttribute("msg","该用户名已注册");
            return "/register";

        } catch (EmptyResultDataAccessException e) {

            sql = "create table `"+username+
                    "`(" +
                    "`id` int not null," +
                    "`ProductName` varchar(50) not null," +
                    "`Quantity` int not null," +
                    "`Price` float not null," +
                    "`Discount` float not null," +
                    "primary key (id)" +
                    ");";
            jdbcTemplate.execute(sql);
            sql = "insert into userinfo (mail, password) value (?,?)";
            jdbcTemplate.update(sql,username,password);
            return "redirect:/";
        }

    }
}
