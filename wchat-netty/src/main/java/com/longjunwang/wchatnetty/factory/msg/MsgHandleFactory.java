package com.longjunwang.wchatnetty.factory.msg;

import com.longjunwang.wchatcommon.enums.MsgTypeEnum;
import com.longjunwang.wchatcommon.util.SpringBeanUtil;
import com.longjunwang.wchatnetty.handle.msg.AbsMsgHandle;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MsgHandleFactory {

    private static Map<Integer, AbsMsgHandle> msgHandleMap = new ConcurrentHashMap<>();

    @PostConstruct
    public static void register(){
        for (MsgTypeEnum msgTypeEnum : MsgTypeEnum.values()) {
            AbsMsgHandle absMsgHandle = SpringBeanUtil.getBean(msgTypeEnum.getBeanName(), AbsMsgHandle.class);
            msgHandleMap.put(msgTypeEnum.getType(), absMsgHandle);
        }
    }

    public static AbsMsgHandle getMsgHandle(Integer msgType){
        return msgHandleMap.get(msgType);
    }

    public static Map<Integer, AbsMsgHandle> getMsgHandleMap(){
        return msgHandleMap;
    }

}
