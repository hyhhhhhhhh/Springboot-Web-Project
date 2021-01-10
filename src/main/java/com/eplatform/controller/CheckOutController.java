package com.eplatform.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class CheckOutController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/CheckOut")
    public String CheckOut(
            HttpSession session,
            Model model
    ){
        List<Map<String,Object>> checkOutItems = (List<Map<String,Object>>)session.getAttribute("items");
        String sql;
        Map<String,Object> result ;
        for (Map<String, Object> item : checkOutItems) {
            //库存检验
            sql = "select Quantity from commodity where id = " + item.get("id");
            result = jdbcTemplate.queryForMap(sql);
            System.out.println(result.get("Quantity"));
            if ((int) result.get("Quantity") < (int) item.get("Quantity")) {
                //库存不足
                model.addAttribute("msg", "有商品库存不足");
                return "user/checkout";
            }

        }
        return "user/checkout";
    }


}
