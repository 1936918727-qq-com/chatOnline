package com.chat.service;

import com.chat.dao.ActiveDao;
import com.chat.entity.Active;
import com.chat.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//表示是服务层
public class ActiveService {
    @Autowired
    ActiveDao dao;

    public Result ActiveList(){
        List<Active> alist = dao.selectActive();
        return Result.ok(200,"查询成功",alist);
    }
}
