package com.eplatform.controller;

import com.eplatform.mapper.MailService;
import com.eplatform.pojo.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eplatform.pojo.Mail;
import com.eplatform.mapper.MailService;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class DealController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private MailService mailService;

    @RequestMapping("/Deal")
    public String Deal(
            HttpSession session
    ){
        Object mail = session.getAttribute("loginUser");
        List<Map<String,Object>> checkOutItems = (List<Map<String,Object>>)session.getAttribute("items");
        String sql1,sql2,sql3;
        //清空购物车
        sql1 = "delete  from `"+mail+"`";
        jdbcTemplate.execute(sql1);
        String message = "以下商品已下单，准备发货：" ;
        for (Map<String, Object> item : checkOutItems) {
            //记录log
            sql2 = "insert into log value (?,?,?,?,?,now())";
            jdbcTemplate.update(sql2,
                    mail,
                    item.get("ProductName"),
                    item.get("id"),
                    1,
                    item.get("Quantity"));
            //更新commodity记录
            sql3 = "update commodity set Quantity = Quantity -" + item.get("Quantity") +
                    ",Sales = Sales +" + item.get("Quantity") +
                    " where id = " + item.get("id");
            jdbcTemplate.update(sql3);
            message = message +"\n"+
                    "商品："+item.get("ProductName")+
                    "\t数目："+ item.get("Quantity")+
                    "\t单价："+ item.get("Price")+
                    "\t折扣："+ item.get("Discount");
        }
        //发送邮件通知发货

        message = message +"\n总额："+session.getAttribute("total");
        Mail mailAddr = new Mail();
        mailAddr.setTo((String) mail);
        mailAddr.setSubject("确认订单发货通知");
        mailAddr.setContent(message);
        session.removeAttribute("total");
        session.removeAttribute("items");
        mailService.sendMail(mailAddr);

        return "user/success";
    }
}
