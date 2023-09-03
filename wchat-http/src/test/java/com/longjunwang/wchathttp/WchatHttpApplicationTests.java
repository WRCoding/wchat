package com.longjunwang.wchathttp;


import com.longjunwang.wchatcommon.mapper.UserInfoMapper;
import com.longjunwang.wchatcommon.util.CheckCodeUtil;
import com.longjunwang.wchatcommon.util.RedisUtil;
import com.longjunwang.wchathttp.service.oauth.OauthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.IOException;
import java.util.List;

@SpringJUnitConfig
@SpringBootTest
class WchatHttpApplicationTests {

    @Autowired
    OauthService oauthService;

    @Autowired
    UserInfoMapper userInfoMapper;
    @Test
    void contextLoads() {
//        UserInfo userInfo = userInfoMapper.selectByEmail("jane@example.com");
//        System.out.println(userInfo);
//        List<UserInfo> userInfos = userInfoMapper.selectAll();
//        userInfos.forEach(System.out::println);
//        Response<String> checkCode = oauthService.getCheckCode("846179345@qq.com");
//        System.out.println(checkCode);
        CheckCodeUtil.generateCheckCode("846179345@qq.com");
    }

}
