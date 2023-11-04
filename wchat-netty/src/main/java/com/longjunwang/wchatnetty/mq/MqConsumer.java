package com.longjunwang.wchatnetty.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@RocketMQMessageListener(consumerGroup = "", topic = "")
@Slf4j
@Component
@DependsOn("nettyServer")
public class MqConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {

    @Value("#{serverInfo.getTopicName()}")
    String topicName;

    @Override
    public void onMessage(String message) {
        log.info(message);
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        try {
            log.info(topicName);
            consumer.setConsumerGroup(topicName);
            consumer.subscribe(topicName, "*");
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        }
    }
}
