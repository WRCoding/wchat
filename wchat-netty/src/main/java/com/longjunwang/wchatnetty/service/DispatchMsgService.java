package com.longjunwang.wchatnetty.service;

import cn.hutool.json.JSONUtil;
import com.longjunwang.wchatcommon.entity.Message;
import com.longjunwang.wchatcommon.entity.OfflineMessage;
import com.longjunwang.wchatcommon.mapper.MessageMapper;
import com.longjunwang.wchatcommon.mapper.OfflineMessageMapper;
import com.longjunwang.wchatcommon.util.RedisUtil;
import com.longjunwang.wchatnetty.entity.ChatMsgResp;
import com.longjunwang.wchatnetty.factory.msg.MsgHandleFactory;
import com.longjunwang.wchatnetty.handle.msg.AbsMsgHandle;
import com.longjunwang.wchatnetty.mq.MqProducer;
import com.longjunwang.wchatnetty.session.SessionManager;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
@Slf4j
public class DispatchMsgService {

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private MqProducer mqProducer;

    @Resource
    private OfflineMessageMapper offlineMessageMapper;
    public void dispatchMsg(String msgId){
        Message message = messageMapper.selectByMsgId(msgId);
        String receiveId = message.getReceiveId();
        Channel channel = SessionManager.getChannel(receiveId);
        if (Objects.nonNull(channel)){
            writeMsg(message, channel);
            return;
        }
        String topicName = RedisUtil.get(receiveId);
        if (Objects.nonNull(topicName)){
            log.info("receiveId: {}, topicName: {}", receiveId, topicName);
            mqProducer.produceData(topicName, msgId);
            return;
        }
        log.info("receiveId: {} 不在线", receiveId);
        writeOfflineMsg(receiveId, msgId);
    }

    private void writeOfflineMsg(String receiveId, String msgId) {
        OfflineMessage offlineMsg = new OfflineMessage(msgId, receiveId);
        offlineMessageMapper.insert(offlineMsg);
    }

    private void writeMsg(Message message, Channel channel) {
        AbsMsgHandle msgHandle = MsgHandleFactory.getMsgHandle(message.getMsgType());
        ChatMsgResp chatMsgResp = msgHandle.wrapMSg(message);
        channel.writeAndFlush(new TextWebSocketFrame(JSONUtil.toJsonStr(chatMsgResp)));
    }
}
