package com.longjunwang.wchatcommon.mapper;

import com.longjunwang.wchatcommon.entity.ouath.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    List<UserInfo> selectAll();
    UserInfo selectByEmail(String email);
}
