package com.eplatform.mapper;

import com.eplatform.pojo.Mail;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


@EnableAutoConfiguration
public interface MailService {
    // 发送邮件
    public void sendMail(Mail mail);
}