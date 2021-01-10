package com.eplatform.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class LoadCartController {
    public LoadCartController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/LoadCart")
    public String LoadCart(HttpSession session){
        UpdateCart(session);
        return "redirect:/index";
    }

    public void UpdateCart(HttpSession session){
        Object mail = session.getAttribute("loginUser");
        //加载购物车
        String sql = "select * from `"+ mail+"`";
        List<Map<String,Object>> items = jdbcTemplate.queryForList(sql);
        float total = 0;
        for (Map<String, Object> temp : items) {
            total += (int) temp.get("Quantity") * (float) temp.get("Price") * (float) temp.get("Discount");
        }
        System.out.println(items);
        session.setAttribute("total",total);
        session.setAttribute("items",items);

    }
}
