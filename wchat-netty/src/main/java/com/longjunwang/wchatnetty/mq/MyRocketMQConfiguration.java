package com.longjunwang.wchatnetty.mq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyRocketMQConfiguration {

    @Value("${rocketmq.topicName}")
    private String topicName;

    public String getTopicName() {
        return topicName;
    }
}
