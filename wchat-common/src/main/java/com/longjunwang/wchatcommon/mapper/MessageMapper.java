package com.longjunwang.wchatcommon.mapper;

import com.longjunwang.wchatcommon.entity.FriendRelation;
import com.longjunwang.wchatcommon.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MessageMapper {

    int insert(Message message);

    Message selectByMsgId(@Param("msgId") String msgId);
}
