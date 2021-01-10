package com.eplatform.controller;

import com.eplatform.pojo.Commodity;
import com.eplatform.pojo.CommodityDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@EnableAutoConfiguration
@Controller
public class DashboardController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping({"/dashboard"})
    public String dashboard(Model model){
        //获取商品信息
        String sql = "Select * from commodity";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        model.addAttribute("data",maps);
        Commodity temp = new Commodity();
        temp.setProductName("newProduct");
        temp.setDiscount(1);
        model.addAttribute("commodity",temp);
        //获取log信息
        sql="SELECT count(*) as viewAndBuy,sum(Quantity) as Q, DATE_FORMAT(time, '%Y-%m-%d') as datelog from log group by datelog";
        List<Map<String, Object>> datelog = jdbcTemplate.queryForList(sql);
        List<Object> Xaxis= new ArrayList<Object>();
        List<Object> Yaxis_VaB= new ArrayList<Object>();
        List<Object> Yaxis_Q= new ArrayList<Object>();
        for (Map<String, Object> items:datelog ){
            Xaxis.add(items.get("datelog"));
            Yaxis_VaB.add(items.get("viewAndBuy"));
            Yaxis_Q.add(items.get("Q"));
        }
        //charts 1 浏览/销售
        model.addAttribute("xaxis",Xaxis);
        model.addAttribute("yaxis_view",Yaxis_VaB);
        model.addAttribute("yaxis_Q",Yaxis_Q);

        List<Object> Xaxis2= new ArrayList<Object>();
        List<Object> Yaxis_sales= new ArrayList<Object>();
        List<Object> Yaxis_rest2= new ArrayList<Object>();
        //chart 2 商品销售状态
        sql  = "select Sales,ProductName,Quantity as Rest from commodity GROUP BY id";
        datelog = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> items:datelog ){
            Xaxis2.add(items.get("ProductName"));
            Yaxis_sales.add(items.get("Sales"));
            Yaxis_rest2.add(items.get("Rest"));
        }
        model.addAttribute("xaxis2",Xaxis2);
        model.addAttribute("yaxis_sales2",Yaxis_sales);
        model.addAttribute("yaxis_rest2",Yaxis_rest2);
        return "dashboard";
    }


    //log日志
    @GetMapping({"/dashboard-log"})
    public String dashboard_sales(
            HttpSession session
    ){
        String sql = "select * from log";

        List<Map<String,Object>> logData = jdbcTemplate.queryForList(sql);

        session.setAttribute("logData",logData);
        return "dashboard-log";
    }


    @GetMapping("/dashboard/modify/{id}")
    public String modifyCommodity(@PathVariable("id") Integer id, Model model){
        //查出原来的数据
        String sql="Select * from commodity where id = "+ id;

        Map<String, Object> maps = jdbcTemplate.queryForMap(sql);
        Commodity commodity = new Commodity(maps);
        model.addAttribute("commodity",commodity);

        return "dashboard/modify";
    }

    @GetMapping("/dashboard/del/{id}")
    public String delCoomodity(@PathVariable("id") Integer id){
        String sql="delete from commodity where id ="+id;

        jdbcTemplate.execute(sql);
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard/addDetail/{id}")
    public String addDetail(@PathVariable("id") Integer id, Model model){
        //查出原来的数据
        String sql="Select * from commoditydetail where id = "+ id;

        Map<String, Object> maps = jdbcTemplate.queryForMap(sql);
        sql = "select ProductName from commodity where id = "+id;
        Map<String,Object> NameMap=jdbcTemplate.queryForMap(sql);
//        System.out.println( NameMap.get("ProductName"));
        CommodityDetail cd =new CommodityDetail(maps);
        model.addAttribute("CD",cd);
        model.addAttribute("ProductName",NameMap.get("ProductName"));

        return "dashboard/detailModify";
    }


}
