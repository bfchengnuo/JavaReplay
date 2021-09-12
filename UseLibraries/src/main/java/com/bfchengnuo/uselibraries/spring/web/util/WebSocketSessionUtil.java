package com.bfchengnuo.uselibraries.spring.web.util;

import cn.hutool.core.collection.CollUtil;
import com.bfchengnuo.uselibraries.enums.UserTypeEnum;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * 处理 ws session 的工具栏
 * @author 冰封承諾Andy
 * @date 2021/2/28
 */
@Component
@Slf4j
public class WebSocketSessionUtil {
    /**
     * 结构：用户类型 : <pid:session>
     */
    // @Resource
    private ImmutableMap<String, ConcurrentMap<String, Set<Session>>> websocketSessionMap;
    /**
     * 如果使用了 spring 的 OAuth2 作为认证，可以使用 ResourceServerTokenServices 来验证 token
     */
    // private ResourceServerTokenServices tokenServices;

    private static ImmutableMap<String, ConcurrentMap<String, Set<Session>>> staticSessionMap;

    /**
     * 保存连接 session
     * @param userType 用户类型
     * @param session /
     */
    public static void storageSession(String userType, String projectId, Session session) {
        ConcurrentMap<String, Set<Session>> psMap;
        if (UserTypeEnum.WS_GENERAL_USER.getCode().equals(userType)) {
            // 普通用户
            psMap = staticSessionMap.get(UserTypeEnum.WS_GENERAL_USER.getCode());
        } else {
            // 其他用户
            psMap = staticSessionMap.get(UserTypeEnum.WS_OTHER_USER.getCode());
        }

        Set<Session> sessions = psMap.get(projectId);
        if (CollUtil.isEmpty(sessions)) {
            sessions = Sets.newHashSet();
        }
        sessions.add(session);
        psMap.put(projectId, sessions);
    }

    /**
     * 连接关闭，删除 session
     * @param userType 用户类型
     * @param projectId 项目ID
     * @param session 连接
     */
    public static void removeSession(String userType, String projectId, Session session) {
        ConcurrentMap<String, Set<Session>> psMap;
        if (UserTypeEnum.WS_GENERAL_USER.getCode().equals(userType)) {
            // 普通用户
            psMap = staticSessionMap.get(UserTypeEnum.WS_GENERAL_USER.getCode());
        } else {
            // 其他用户
            psMap = staticSessionMap.get(UserTypeEnum.WS_OTHER_USER.getCode());
        }
        psMap.get(projectId).remove(session);
    }

    /**
     * 连接关闭，删除 session，搜索所有用户类型
     * @param projectId 项目ID
     * @param session 连接
     */
    public static void removeSession(String projectId, Session session) {
        staticSessionMap.values().forEach(map -> map.get(projectId).remove(session));
    }

    /**
     * 向某项目下所有的 ws 客户端推送状态消息，所有用户类型
     * @param projectId 项目ID
     * @param data json数据
     */
    public static void sendStatusMsg(String projectId, String data) {
        staticSessionMap.values().forEach(map -> {
            Set<Session> sessions = map.get(projectId);
            sendMsg(sessions, data);
        });
    }

    /**
     * 校验 token 是否有效
     * @param token /
     * @return /
     */
    public static boolean checkToken(String token) {
        // Authentication authResult = staticTokenServices.loadAuthentication(token);
        return true;
    }

    /**
     * 批量向客户端发送消息
     * @param sessions 客户端列表
     * @param data json数据
     */
    private static void sendMsg(Set<Session> sessions, String data) {
        if (CollUtil.isEmpty(sessions)) {
            return;
        }
        sessions.forEach(session -> {
            if (session.isOpen()) {
                session.getAsyncRemote().sendText(data);
            } else {
                sessions.remove(session);
                try {
                    session.close();
                } catch (IOException e) {
                    log.warn("ws session not open, close fail.", e);
                }
            }
        });
    }

    @PostConstruct
    public void init() {
        // 静态初始化注入
        staticSessionMap = websocketSessionMap;
    }
}