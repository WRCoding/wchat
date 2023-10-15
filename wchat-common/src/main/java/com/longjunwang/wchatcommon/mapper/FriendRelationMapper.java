package com.longjunwang.wchatcommon.mapper;

import com.longjunwang.wchatcommon.entity.FriendRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FriendRelationMapper {

    int insert(FriendRelation friendRelation);

    FriendRelation selectOneRelation(@Param("userId") String userId, @Param("friendId") String friendId);
}
