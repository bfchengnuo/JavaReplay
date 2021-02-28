package com.bfchengnuo.uselibraries.spring.web;

import com.bfchengnuo.uselibraries.spring.web.util.WebSocketSessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * ws 服务测试
 * ws 中，默认不支持对请求头的修改，所以认证可能要放在 open 的时候做
 * @author 冰封承諾Andy
 * @date 2021/2/28
 */
@ServerEndpoint(value = "/ws/{token}/status/projects/{projectId}")
@Controller
@Slf4j
public class WebsocketController {
    @OnOpen
    public void onOpen(Session session,
                       @PathParam("token") String token,
                       @PathParam("projectId") String projectId) throws IOException {
        // 需要验证 token
        if (WebSocketSessionUtil.checkToken(token)) {
            // 把 session 存储起来
            WebSocketSessionUtil.storageSession("1", projectId, session);
            log.info("session open. ID:" + session.getId());
            return;
        }
        log.info("session open no Authority. ID:" + session.getId());
        session.close();
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session,
                        @PathParam("token") String token,
                        @PathParam("projectId") String projectId) {
        if (WebSocketSessionUtil.checkToken(token)) {
            WebSocketSessionUtil.removeSession("1", projectId, session);
        } else {
            WebSocketSessionUtil.removeSession(projectId, session);
        }
        log.info("session close. ID:" + session.getId());
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("get client ws msg. ID:" + session.getId() + ". msg:" + message);
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("ws error: ", error);
    }
}
