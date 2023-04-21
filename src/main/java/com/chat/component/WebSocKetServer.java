package com.chat.component;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.chat.entity.User;
import com.chat.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/webSocket/{id}")
@Slf4j
public class WebSocKetServer {
    @Autowired
    UserService service;

    //存储session集合
    private static ConcurrentHashMap<Integer,Session> sessionMap = new ConcurrentHashMap<>();

    //存储用户集合
    private static ConcurrentHashMap<Integer, User> userMap = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "id") String id) {
        System.out.println("【WebSocket连接成功】" + "，id："+ id);
        /**
         * 查询用户
          */
        User user = new User();
        if(ObjectUtils.isNull(id)){
            log.error("token无效或无法解析");
        }
        setMap(session,user);
    }

    private void setMap(Session session, User user){
        int id = user.getId();
        sessionMap.put(id,session);
        userMap.put(id,user);
        int size = sessionMap.size();
        log.warn("用户连接：{}，昵称：{}，当前在线人数为：{}",id,user.getUserName(),size);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("【WebSocket退出成功】");
//        removeMap(session);
    }
    private void removeMap(Session session){
        int id = getUserIdBySession(session);
        if(ObjectUtils.isNull(id)){
            return;
        }
        sessionMap.remove(id);
        userMap.remove(id);
    }
    private int getUserIdBySession(Session session){
        for (Integer id : sessionMap.keySet()) {
            if(sessionMap.get(id).getId().equals(session.getId())){
                return id;
            }
        }
        return 0;
    }

    @OnMessage
    public void onMessage(Session session,String message) throws IOException {
        System.out.println("【WebSocket消息】收到客户端消息：" + message);
        User user = getUserBySession(session);
        if(ObjectUtils.isNull(user)){
            return;
        }
        log.info("APP用户:{},消息:",user.getUserName(),message);
        sendInfo(message,user.getId());
    }

    private User getUserBySession(Session session){
        int id = getUserIdBySession(session);
        if(ObjectUtils.isNull(id)){
            return null;
        }
        return userMap.get(id);
    }

    @RequestMapping("/sendInfo")
    public static void sendInfo(String message,int id) throws IOException {
        log.info("发送的消息到：{},消息内容：{}",id,message);
        if(ObjectUtils.isNull(id) || StringUtils.isBlank(message)){
            log.error("消息不完整");
            return;
        }
//        System.out.println(id);
//        System.out.println(sessionMap.contains(id));
//        if(sessionMap.contains(id)){
//            //用户在线
//            try {
//                sendMessage(sessionMap.get(id),message);
//            }catch (Exception e){
//                log.error("发送给用户{}的消息出错",id);
//            }
//        }else {
//            //用户不在线
//            log.error("用户{}不在线",id);
//            //后续处理
//        }

        sendMessage(sessionMap.get(id),message);
    }

    public static void sendMessage(Session session,String mes) throws IOException {
        session.getBasicRemote().sendText(mes);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("error");
        error.printStackTrace();
    }

}
