package com.longjunwang.wchatnetty.handle.msg;

import cn.hutool.core.util.IdUtil;
import com.longjunwang.wchatcommon.entity.Message;
import com.longjunwang.wchatcommon.enums.MsgTypeEnum;
import com.longjunwang.wchatcommon.mapper.MessageMapper;
import com.longjunwang.wchatcommon.util.RedisUtil;
import com.longjunwang.wchatnetty.entity.ChatMsgReq;
import com.longjunwang.wchatnetty.entity.ChatMsgResp;
import com.longjunwang.wchatnetty.mq.MqProducer;
import com.longjunwang.wchatnetty.service.DispatchMsgService;
import com.longjunwang.wchatnetty.session.SessionManager;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public abstract class AbsMsgHandle {

    @Resource
    protected MessageMapper messageMapper;
    @Resource
    protected MqProducer mqProducer;
    @Resource
    protected DispatchMsgService dispatchMsgService;

    protected abstract MsgTypeEnum getMsgType();

    @Transactional
    public void saveAndSendMsg(ChatMsgReq chatMsgReq){
        String msgId = saveMsg(chatMsgReq);
        sendMsg(msgId);
    }

    public abstract ChatMsgResp wrapMSg(Message message);
    public abstract String saveMsg(ChatMsgReq chatMsgReq);

    public void sendMsg(String msgId){
        dispatchMsgService.dispatchMsg(msgId);
    }
}
