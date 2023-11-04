package com.longjunwang.wchatnetty.entity;

import lombok.Data;

@Data
public class ChatMsgReq {
    private String sendId;
    private String receiveId;
    private String content;
    private Integer msgType;
}
