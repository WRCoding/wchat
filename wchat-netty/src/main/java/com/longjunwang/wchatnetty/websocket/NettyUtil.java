package com.longjunwang.wchatnetty.websocket;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * @author wanglongjun
 */

public class NettyUtil {

    public static AttributeKey<String> TOKEN = AttributeKey.valueOf("token");

    public static <T> void setAttr(Channel channel, AttributeKey<T> attributeKey, T data) {
        Attribute<T> attr = channel.attr(attributeKey);
        attr.set(data);
    }

    public static <T> T getAttr(Channel channel, AttributeKey<T> ip) {
        return channel.attr(ip).get();
    }

    public static boolean equalChannel(Channel channel, Channel anotherChannel) {
        return channel.id().equals(anotherChannel.id());
    }
}
