package com.longjunwang.wchatcommon.entity;

import lombok.Data;

/**
 * desc: Message
 *
 * @author ink
 * date:2023-10-06 09:54
 */
@Data
public class Message {
    private String msgId;
    private String sendId;
    private String receiveId;
    private Integer msgType;
    private String content;
    private String createTime;
    private String updateTime;
}
