package com.eplatform.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class Search {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @PostMapping({"/search"})
    public String login(@RequestParam("productName")String name,
                        Model model,
                        HttpSession session){
        //具体的业务
        String sql = "select id from commodity where ProductName = "+"'"+name+"'";
        try {
            Map<String, Object> returnMap = jdbcTemplate.queryForMap(sql);
            Object id = returnMap.get("id");
            if ( !(id  instanceof Integer))
                throw new EmptyResultDataAccessException(0) ;
            return "redirect:/productDetails/"+id;

        }catch (EmptyResultDataAccessException e){
            model.addAttribute("msg","查找失败");
        }

        return "forward:/index";

    }
}
