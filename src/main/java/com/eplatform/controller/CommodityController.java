package com.eplatform.controller;

import com.eplatform.pojo.Commodity;
import com.eplatform.pojo.CommodityDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
@EnableAutoConfiguration
public class CommodityController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("/SubmitAdd")//添加两个表
    public String addCommodity(Commodity commodity){

        //commodity添加
        String sql = "insert into commodity " +
                "(ProductName,Quantity,Price,Sales,ViewTimes,Discount,Date) value" +
                "(?,?,?,?,?,?,now())"
                ;
//        System.out.println(commodity.getId());
//        System.out.println(commodity.getProductName());
        jdbcTemplate.update(sql,
                commodity.getProductName(),
                commodity.getQuantity(),
                commodity.getPrice(),
                commodity.getSales(),
                commodity.getViewTimes(),
                commodity.getDiscount());

        Map<String,Object> ID = jdbcTemplate.queryForMap("SELECT id FROM commodity ORDER BY id DESC LIMIT 1");
        //commoditydetail添加
        sql = "insert into commoditydetail" +
                "(id) value " +
                "(?)";
        jdbcTemplate.update(sql,
                ID.get("id"));

        return "redirect:dashboard";
    }

    @PostMapping("/SubmitUpdate")//update表1
    public String updateCommodity(Commodity commodity){

        String sql = "update commodity set" +
                " ProductName = ? ," +
                "Quantity=?," +
                "Price=?," +
                "Sales=?," +
                "ViewTimes=?," +
                "Discount=?," +
                " Date = now() " +
                "where id = ?"
                ;
//        System.out.println(commodity.getId());
//        System.out.println(commodity.getProductName());
        jdbcTemplate.update(sql,
                commodity.getProductName(),
                commodity.getQuantity(),
                commodity.getPrice(),
                commodity.getSales(),
                commodity.getViewTimes(),
                commodity.getDiscount(),
                commodity.getId());

        return "redirect:dashboard";
    }

    @PostMapping("/SubmitAddDetail")
    public String submitAddDetail(CommodityDetail commodityDetail){
        String sql = "update commoditydetail set" +
                " brief = ? ," +
                "detail=?" +
                "where id = ?"
                ;

        jdbcTemplate.update(sql,
                commodityDetail.getBrief(),
                commodityDetail.getDetail(),
                commodityDetail.getId()
             );
        return "redirect:dashboard";
    }


}
