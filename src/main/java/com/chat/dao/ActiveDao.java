package com.chat.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chat.entity.Active;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//表示是dao层
public interface ActiveDao extends BaseMapper<Active> {
    List<Active> selectActive();
}
