package com.longjunwang.wchatnetty.entity;

import lombok.Data;

public class ChatMsgResp {

    private UserInfo userInfo;
    private Message message;
    @Data
    class UserInfo {
        String userId;
        String avatarUrl;
        String email;
        String phone;
    }

    class Message{
        String msgId;
        String sendId;
        String receiveId;
        String sendTime;
        String content;
        Integer msgType;
    }
}
