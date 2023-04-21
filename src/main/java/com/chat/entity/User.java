package com.chat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.assertj.core.util.diff.Delta;
import org.springframework.format.annotation.DateTimeFormat;

@Data   //生成get/set方法
@AllArgsConstructor //生成全参构造器
@NoArgsConstructor  //生成无参构造器
@ToString   //生成ToString方法
public class User {
    @TableId(type = IdType.AUTO)    //表示id是主键  且自增
    private Integer id;

    private String userName;

    private String password;

    private String headImg;

    private String sex;

    @DateTimeFormat(pattern = "yyyy-MM-dd") //处理前端接收的
    @JsonFormat(pattern = "yyyy-MM-dd") //处理返回给前端的
    private String birthday;

    private String email;

    private String description;

    @TableField(exist = false)//表示数据库表中没有该字段
    private String checkCode;
}
