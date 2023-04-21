package com.chat.service;

import com.chat.dao.ChatRecordsDao;
import com.chat.entity.ChatRecords;
import com.chat.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//表示是服务层
public class ChatRecordsService {
    @Autowired
    ChatRecordsDao dao;

    public Result chatList(ChatRecords cr){
        List<ChatRecords> ChatList = dao.selectByToId(cr);
        return Result.ok(ChatList);
    }
}
