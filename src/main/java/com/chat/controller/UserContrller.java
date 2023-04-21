package com.chat.controller;

import com.chat.entity.User;
import com.chat.service.UserService;
import com.chat.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")//父路径
public class UserContrller {
    @Autowired
    UserService service;

    @GetMapping("/sendCode")
    public Result sendCode(String email){
        try {
            return service.sendCode(email);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }

    //注册
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        try {
//            System.out.println(user.getUserName());
            return service.register(user);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常!",e.getMessage());
        }
    }


    /**
     *用户登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        try {
            return service.login(user);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常!",e.getMessage());
        }
    }

}
