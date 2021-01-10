package com.eplatform.controller;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@Controller
@EnableAutoConfiguration
public class UserLogOut {


    @RequestMapping("/user/logout")
    public String logout(
            HttpSession session
    ){

        session.removeAttribute("loginUser");
        session.removeAttribute("total");
        session.removeAttribute("items");
        return "redirect:/index";

    }
}
