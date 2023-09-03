package com.longjunwang.wchathttp.service.user;

import com.longjunwang.wchatcommon.entity.Response;
import com.longjunwang.wchatcommon.entity.ouath.UserInfo;
import com.longjunwang.wchatcommon.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserService {

    @Resource
    private UserInfoMapper userInfoMapper;

    public UserInfo selectByEmail(String email) {
        return userInfoMapper.selectByEmail(email);
    }

    public Response<String> register(String email) {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(email);
//        userInfoMapper.insert(userInfo);
        return Response.success("注册成功");
    }
}
