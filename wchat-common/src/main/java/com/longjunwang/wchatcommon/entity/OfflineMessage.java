package com.longjunwang.wchatcommon.entity;

import lombok.Data;

@Data
public class OfflineMessage {
    private String msgId;
    private String receiveId;
    private String createTime;
    private String updateTime;

    public OfflineMessage(String msgId, String receiveId) {
        this.msgId = msgId;
        this.receiveId = receiveId;
    }
}
