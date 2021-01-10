package com.eplatform.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@EnableAutoConfiguration
public class RemoveFromCart {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/RemoveCartOf/{id}")
    public String RemoveCartOf(
            @PathVariable("id") Integer id,
            HttpSession session
    ){
        String sql = "DELETE from `"+session.getAttribute("loginUser")+"` where id ="+id;

        jdbcTemplate.update(sql);
        return "redirect:/LoadCart";
    }
}
