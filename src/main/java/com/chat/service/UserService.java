package com.chat.service;


import com.chat.dao.UserDao;
import com.chat.entity.User;
import com.chat.utils.Email;
import com.chat.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service//表示是服务层
public class UserService {
    @Autowired
    UserDao dao;
    @Autowired
    RedisTemplate redisTemplate;

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

    //注册
    @Transactional(rollbackFor = Exception.class)//表示发生异常时  回滚事务
    public Result register(User user){
        //1、判断验证码是否正确
        String code = redisTemplate.opsForValue().get("code") + "";//从redis中取出验证码
        if (!user.getCheckCode().equals(code)){
            return Result.error("验证码输入错误");
        }
        //2、验证码正确   进行注册
        dao.insert(user);
        return Result.ok("注册成功!");
    }
}
