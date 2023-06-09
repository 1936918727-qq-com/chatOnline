package com.chat.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chat.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//表示是dao层
public interface UserDao extends BaseMapper<User>{
    User selectByEmail(String email);

    List<User> selectFriends(String email);

}
