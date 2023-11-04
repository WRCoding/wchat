package com.longjunwang.wchatnetty.websocket;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.StrUtil;
import com.longjunwang.wchatnetty.session.SessionManager;
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
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            log.info("fullHttpRequest");
            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.uri();
            String userId = UrlBuilder.ofHttp(uri).getQuery().get("userId").toString();
            SessionManager.register(userId, ctx.channel());
        } else {
            log.info(msg.getClass().toString());
        }
        super.channelRead(ctx, msg);
    }
}
