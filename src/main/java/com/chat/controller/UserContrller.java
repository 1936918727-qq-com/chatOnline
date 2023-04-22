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
            System.out.println("发生了异常-------------------------------------------------------");
            e.printStackTrace();
            return Result.error(500,"发生了异常!",e.getMessage());
        }
    }

    //查询使用用户
    @GetMapping("/list")//localhost:端口号/user/list
    public Result userList(String email){
        try {
            return service.selectFriends(email);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }

    //根据邮箱查询用户id
    @GetMapping("/userid")
    public Result userId(String email){
//        System.out.println("email:"+email);
        try {
            return service.selectByEmail(email);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }

    //根据id查询用户
    @GetMapping("/userInfo")
    public Result selectUserById(String id){
//        System.out.println("email:"+email);
        try {
            return service.selectUserById(id);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }



}
