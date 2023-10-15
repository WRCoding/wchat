package com.longjunwang.wchathttp.service.friend;

import com.longjunwang.wchatcommon.constant.ResponseCode;
import com.longjunwang.wchatcommon.entity.FriendRelation;
import com.longjunwang.wchatcommon.mapper.FriendRelationMapper;
import com.longjunwang.wchatcommon.pojo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
@Slf4j
public class FriendService {

    @Resource
    private FriendRelationMapper friendRelationMapper;


    public Response<String> addFriend(String userId, String friendId){
        FriendRelation friendRelation = friendRelationMapper.selectOneRelation(userId, friendId);
        if (Objects.nonNull(friendRelation)){
            return Response.customer(ResponseCode.VERIFY_ERROR,"好友已经添加", null);
        }
        int count = friendRelationMapper.insert(new FriendRelation(userId, friendId));
        if (count == 1){
            return Response.success("");
        }
        return Response.error(ResponseCode.ERROR);
    }
}
