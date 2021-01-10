package com.eplatform.controller;


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
import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class LoginController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @PostMapping({"/user/login", "/user/login.html"})
    public String login(@RequestParam("username")String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session){
        //具体的业务
        String sql = "select mail,password from userinfo where mail = "+"'"+username+"'";
        try {
            Map<String, Object> Validation = jdbcTemplate.queryForMap(sql);
            if (Validation.get("mail")!=null && Validation.get("password").equals(password))
            {
                session.setAttribute("loginUser",username);
                //加载购物车
                return "redirect:/LoadCart";
            }
            else{

                session.removeAttribute("loginUser");
                model.addAttribute("msg","用户名或密码错误");
            }
        }catch (EmptyResultDataAccessException e){
            session.removeAttribute("loginUser");
            model.addAttribute("msg","用户名或密码错误");
        }

        return "forward:/index";

    }
}
