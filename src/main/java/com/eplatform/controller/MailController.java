package com.eplatform.controller;




import com.eplatform.pojo.Mail;
import com.eplatform.mapper.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;

@RestController
@EnableAutoConfiguration
public class MailController {

    @Autowired
    private MailService mailService;

    // 发送不带格式的文本
    @RequestMapping("/mail")
    public String postMail(
            HttpSession session
    ) {
        Mail mail = new Mail();
//        mail.setTo("1476374192@qq.com");
        mail.setTo("1173214092@qq.com");
        mail.setSubject("automatic");
        mail.setContent("自动邮件发布");
        mailService.sendMail(mail);
        return "发送成功";
    }
}


