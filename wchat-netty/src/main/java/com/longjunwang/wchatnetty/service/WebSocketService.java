package com.longjunwang.wchatnetty.service;

import com.longjunwang.wchatnetty.websocket.NettyUtil;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * desc: WebSocketService
 *
 * @author ink
 * date:2023-09-24 11:09
 */
@Service
@Slf4j
public class WebSocketService {

    @Autowired
    private UserService userService;

    private static  ConcurrentHashMap<String, Channel>  userIdChannelMap = new ConcurrentHashMap<>();

    public boolean verifyToken(String token, Channel channel){
        log.info("token:{}",token);
        boolean verifyToken = userService.verifyToken(token);
        if (!verifyToken){
            return false;
        }
        String userId = userService.getUserIdByToken(token);
        Channel userChannel = userIdChannelMap.get(userId);
        return Objects.nonNull(userChannel) && NettyUtil.equalChannel(userChannel, channel);
    }
}
