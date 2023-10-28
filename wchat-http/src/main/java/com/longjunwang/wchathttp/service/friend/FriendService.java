package com.longjunwang.wchathttp.service.friend;

import com.longjunwang.wchatcommon.constant.ResponseCode;
import com.longjunwang.wchatcommon.entity.FriendRelation;
import com.longjunwang.wchatcommon.entity.UserInfo;
import com.longjunwang.wchatcommon.mapper.FriendRelationMapper;
import com.longjunwang.wchatcommon.mapper.UserInfoMapper;
import com.longjunwang.wchatcommon.pojo.Response;
import com.longjunwang.wchatcommon.pojo.vo.FriendVo;
import com.longjunwang.wchatcommon.pojo.vo.UserInfoVo;
import com.longjunwang.wchatcommon.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
@Slf4j
public class FriendService {

    @Resource
    private FriendRelationMapper friendRelationMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    public Response<String> addFriend(String userId, String friendId) {
        FriendRelation friendRelation = friendRelationMapper.selectOneRelation(userId, friendId);
        if (Objects.nonNull(friendRelation)) {
            return Response.customer(ResponseCode.VERIFY_ERROR, "好友已经添加", null);
        }
        int count = friendRelationMapper.insert(new FriendRelation(userId, friendId));
        if (count == 1) {
            return Response.success("");
        }
        return Response.error(ResponseCode.ERROR);
    }

    public Response<FriendVo> search(String userId, String searchKey) {
        UserInfo userInfo = userInfoMapper.selectByKey(searchKey);
        if (Objects.nonNull(userInfo)) {
            log.info("userId: {}, searchKey: {}", userId, userInfo.getId());
            FriendRelation friendRelation = friendRelationMapper.selectOneRelation(userId, userInfo.getId());
            log.info("friendRelation: {}", friendRelation);
            FriendVo friendVo = CommonUtil.transfer(userInfo, FriendVo.class);
            friendVo.setIsFriend(Objects.nonNull(friendRelation) ? 1 : 0);
            log.info("userId: {}, friendVo: {}", userId, friendVo);
            return Response.success(friendVo);
        }
        return Response.customer(ResponseCode.VERIFY_ERROR,"用户不存在", null);
    }
}
