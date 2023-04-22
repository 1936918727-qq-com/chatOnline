package com.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//视图容器
@Controller
public class ViewController {
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    //聊天室界面
    @RequestMapping("/room")
    public String room(){
        return "chatRoom";
    }
}
