package com.longjunwang.wchatnetty.websocket;

import cn.hutool.json.JSONUtil;
import com.longjunwang.wchatnetty.entity.ChatMsgReq;
import com.longjunwang.wchatnetty.factory.msg.MsgHandleFactory;
import com.longjunwang.wchatnetty.handle.msg.AbsMsgHandle;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

/**
 * desc: NettyWebSocketServerHandler
 *
 * @author ink
 * date:2023-09-24 10:23
 */
@Slf4j
public class NettyWebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame frame) throws Exception {
        String text = frame.text();
        ChatMsgReq chatMsgReq = JSONUtil.toBean(text, ChatMsgReq.class);
        log.info("wsMsg: {}", chatMsgReq);
        AbsMsgHandle msgHandle = MsgHandleFactory.getMsgHandle(chatMsgReq.getMsgType());
        msgHandle.saveAndSendMsg(chatMsgReq);
    }
}
