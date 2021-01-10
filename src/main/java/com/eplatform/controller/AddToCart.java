package com.eplatform.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class AddToCart {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/AddToCart/{id}")
    public String AddToCart(
            @PathVariable("id") Integer id,
            @RequestParam("Q") Integer Q,
            HttpSession session
    ){
        //验证用户登录状态
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser==null){
            return "redirect:/index";
        }

        //加入购物车
        System.out.println(Q);
        if(Q>0) {
            String sql = "select * from commodity where id =" + id;
            try{

                Map<String, Object> map = jdbcTemplate.queryForMap(sql);
                sql = "insert into `"+loginUser+"` value (?,?,?,?,?)";
                jdbcTemplate.update(sql,
                        id,
                        map.get("ProductName"),
                        Q,
                        map.get("Price"),
                        map.get("Discount"));}
            catch (DuplicateKeyException e){
                sql = "update `"+loginUser+"` set  Quantity =  Quantity +"+Q;
                jdbcTemplate.update(sql);
            }
            LoadCartController importClass = new LoadCartController(jdbcTemplate);
            importClass.UpdateCart(session);
            String jumpTo = "redirect:/productDetails/" + id;
            return jumpTo;
        }
        else
            return "redirect:/LoadCart";


    }
}
