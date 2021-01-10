package com.eplatform.controller;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@EnableAutoConfiguration
public class AdminController {
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @PostMapping("/admin/login")
    public String admin(
            @RequestParam("username")String username,
            @RequestParam("password") String password,
            Model model,
            HttpSession session
    )
    {
        //具体的业务
        if ("admin".equals(username) && "admin".equals(password)) {
            session.setAttribute("loginAdmin",username);
            return "redirect:/dashboard";
        }
        else{
            model.addAttribute("msg","用户名或密码错误");
            return "admin";
        }
    }
}
