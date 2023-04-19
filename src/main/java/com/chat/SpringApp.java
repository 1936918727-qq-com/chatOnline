package com.chat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chat.dao")//表示扫描dao包下的注解
public class SpringApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class);
    }
}
