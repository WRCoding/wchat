package com.longjunwang.wchatcommon.mapper;

import com.longjunwang.wchatcommon.entity.ouath.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    int insert(UserInfo userInfo);
    List<UserInfo> selectAll();
    UserInfo selectByKey(String key);
}
