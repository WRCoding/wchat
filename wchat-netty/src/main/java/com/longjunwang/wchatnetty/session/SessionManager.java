package com.longjunwang.wchatnetty.session;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

/**
 * desc: 会话管理
 *
 * @author ink
 * date:2023-10-04 10:55
 */
@Slf4j
public class SessionManager {

    private static final ConcurrentHashMap<String, Channel> SESSION_MAP = new ConcurrentHashMap<>();

    public static void register(String userId, Channel channel) {
        log.info("用户：{}，注册会话", userId);
        SESSION_MAP.put(userId, channel);
    }

    public static void unregister(String userId) {
        log.info("用户：{}，注销会话", userId);
        SESSION_MAP.remove(userId);
    }

    public static Channel getChannel(String userId) {
        return SESSION_MAP.get(userId);
    }
}
