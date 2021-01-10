package com.eplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class ProductDetail {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/productDetails/{id}")
    public String productDetails(@PathVariable("id") Integer id, Model model, HttpSession session){

        System.out.println(id);
        //验证登录情况
        Object loginUser =session.getAttribute("loginUser");
        String sql1 = "select * from commodity where id = "+id;
        Map<String,Object> AtrriMap= jdbcTemplate.queryForMap(sql1);
        if (loginUser !=null){
            //记录用户行为，更新数据库 log commodity
            //log

            String sql2 = "insert into log value (?,?,?,?,?,now())";
            jdbcTemplate.update(sql2,loginUser,AtrriMap.get("ProductName"),id,0,0);

        }
        //commodity
        String sql3 = "update commodity set ViewTimes=ViewTimes+1 where id = "+id;
        jdbcTemplate.update(sql3);

        //返回商品详细信息到前端
        //commoditydeatil表读入Detail
        String sql4 = "select brief,detail from commoditydetail where id = "+id;
        Map<String,Object> Description = jdbcTemplate.queryForMap(sql4);

        model.addAttribute("Description",Description);

        //Detail已经存入DetailMap
        model.addAttribute("AtrriMap",AtrriMap);




        return "productDetails";
    }


}
