package com.longjunwang.wchatcommon.mapper;

import com.longjunwang.wchatcommon.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    int updateBySelective(UserInfo userInfo);
    int insert(UserInfo userInfo);
    List<UserInfo> selectAll();
    UserInfo selectByKey(String key);
}
