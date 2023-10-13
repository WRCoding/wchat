package com.longjunwang.wchathttp;


import com.longjunwang.wchatcommon.pojo.vo.LoginVo;
import com.longjunwang.wchatcommon.pojo.vo.RegisterVo;
import com.longjunwang.wchatcommon.mapper.UserInfoMapper;
import com.longjunwang.wchatcommon.util.CheckCodeUtil;
import com.longjunwang.wchathttp.service.oauth.OauthService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
@SpringBootTest
@Slf4j
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

    @Test
    void testLogin(){
        LoginVo loginVo = new LoginVo();
        loginVo.setKey("846179345@qq.com");
        loginVo.setPassWord("11111");
        log.info(oauthService.login(loginVo).toString());
    }

    @Test
    void testRegister(){
        RegisterVo registerVo = new RegisterVo();
        registerVo.setEmail("846179345@qq.com");
        registerVo.setCheckCode(CheckCodeUtil.generateCheckCode("846179345@qq.com"));
        registerVo.setPassWord("11111");
        log.info(oauthService.register(registerVo).toString());
    }

}
