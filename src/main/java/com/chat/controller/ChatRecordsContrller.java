package com.chat.controller;

import com.chat.entity.ChatRecords;
import com.chat.service.ChatRecordsService;
import com.chat.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")//父路径
public class ChatRecordsContrller {
    @Autowired
    ChatRecordsService service;

    @PostMapping("/chatList")
    public Result chatList(@RequestBody ChatRecords cr){
        try {
            return service.chatList(cr);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }


}
