package com.chat.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chat.entity.ChatRecords;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//表示是dao层
public interface ChatRecordsDao extends BaseMapper<ChatRecords> {
    List<ChatRecords> selectByToId(ChatRecords cr);
}
