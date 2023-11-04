package com.longjunwang.wchatnetty.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class MqProducer {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void produceData(String topic, String data){
        log.info("topic: {}, data: {}", topic, data);
        Message<String> message = MessageBuilder.withPayload(data).build();
        rocketMQTemplate.send(topic, message);
    }
}
