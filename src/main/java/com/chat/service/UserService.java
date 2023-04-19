package com.chat.service;

import com.chat.dao.UserDao;
import com.chat.utils.Email;
import com.chat.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service//表示是服务层
public class UserService {

    @Autowired
    Email email;

    //发送邮箱方法
    public Result sendCode(String userEmail){
        //生成验证码
        String code="";
        for (int i=1;i<=6;i++){
            code+=new Random().nextInt(10);
        }
        //发送验证码
        boolean result = email.sendEmail(userEmail, "验证码", "你的验证码是:" + code);
        return result?Result.ok("发送成功!"):Result.error("发送失败!");

    }
}
