package com.chat.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component //交给IOC管理
public class Email {

    @Autowired
    JavaMailSender sender;

    //获取配置文件中配置的邮箱
    @Value("${spring.mail.username}")
    String myEmail;

    /**
     *
     * @param toEmail 收件人
     * @param title    邮箱标题
     * @param content    邮件内容
     * @return
     */
    //发送邮箱的方法
    public boolean sendEmail(String toEmail,String title,String content){
        try {
            //创建信封对象
            SimpleMailMessage smm = new SimpleMailMessage();
            //设置发件人
            smm.setFrom(myEmail);
            //设置收件人
            smm.setTo(toEmail);
            //设置标题
            smm.setSubject(title);
            //设置内容
            smm.setText(content);

            //发送邮箱
            sender.send(smm);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
