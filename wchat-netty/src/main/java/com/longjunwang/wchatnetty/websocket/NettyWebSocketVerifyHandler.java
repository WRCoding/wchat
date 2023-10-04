package com.longjunwang.wchatnetty.websocket;

import cn.hutool.core.util.StrUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * desc: NettyWebSocketVerifyHandler
 *
 * @author ink
 * date:2023-09-24 14:07
 */
@Slf4j
public class NettyWebSocketVerifyHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有连接接入。。。。");
        super.handlerAdded(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            log.info("HandshakeComplete");
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            log.info("fullHttpRequest");
            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.uri();
            System.out.println(uri);
        } else {
            log.info(msg.getClass().toString());
        }
        super.channelRead(ctx, msg);
    }
}
