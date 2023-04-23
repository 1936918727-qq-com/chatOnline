package com.chat.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data   //生成get/set方法
@AllArgsConstructor //生成全参构造器
@NoArgsConstructor  //生成无参构造器
@ToString   //生成ToString方法
public class Active {
    private Integer id;
    private Integer uid;
    private String text;
    private String medias;
    private Integer seeType;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//处理前端接收的
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//处理后端接收的
    private Date created;

    @TableField(exist = false)
    private String headImg;
}
