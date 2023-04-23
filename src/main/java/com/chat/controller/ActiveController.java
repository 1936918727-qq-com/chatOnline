package com.chat.controller;

import com.chat.service.ActiveService;
import com.chat.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/active")
public class ActiveController {
    @Autowired
    ActiveService service;

    @GetMapping("/list")
    public Result activeList(){
        try {
            return service.ActiveList();
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }
}
