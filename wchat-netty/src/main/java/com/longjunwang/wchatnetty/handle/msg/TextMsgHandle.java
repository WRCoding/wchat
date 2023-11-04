package com.longjunwang.wchatnetty.handle.msg;

import cn.hutool.core.util.IdUtil;
import com.longjunwang.wchatcommon.entity.Message;
import com.longjunwang.wchatcommon.enums.MsgTypeEnum;
import com.longjunwang.wchatnetty.entity.ChatMsgReq;
import com.longjunwang.wchatnetty.entity.ChatMsgResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component(value = "textMsgHandle")
@Slf4j
public class TextMsgHandle extends AbsMsgHandle{



    @Override
    protected MsgTypeEnum getMsgType() {
        return MsgTypeEnum.TEXT;
    }

    @Override
    public ChatMsgResp wrapMSg(Message message) {
        return null;
    }

    @Override
    @Transactional
    public String saveMsg(ChatMsgReq chatMsgReq) {
        log.info("wsMsg: {}", chatMsgReq);
        Message message = Message.builder().msgId(IdUtil.getSnowflakeNextIdStr())
                .sendId(chatMsgReq.getSendId())
                .receiveId(chatMsgReq.getReceiveId())
                .msgType(chatMsgReq.getMsgType())
                .content(chatMsgReq.getContent()).build();
        messageMapper.insert(message);
        return message.getMsgId();
    }
}
