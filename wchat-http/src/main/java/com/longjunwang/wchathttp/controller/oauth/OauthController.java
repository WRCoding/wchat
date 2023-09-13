package com.longjunwang.wchathttp.controller.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.longjunwang.wchatcommon.entity.Response;
import com.longjunwang.wchatcommon.entity.vo.LoginVo;
import com.longjunwang.wchatcommon.entity.vo.RegisterVo;
import com.longjunwang.wchatcommon.entity.vo.UserInfoVo;
import com.longjunwang.wchathttp.service.oauth.OauthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/oauth")
public class OauthController {

    @Resource
    private OauthService oauthService;

    @GetMapping("/callback")
    public String callback(String code) throws IOException {
        log.info("code: {}", code);
        return oauthService.getAccessToken(code);
    }

    @GetMapping("/user")
    public Response<String> user(String id) throws IOException {
        log.info("id: {}", id);
        return Response.success(UUID.randomUUID().toString());
//        return oauthService.getUserInfo(id);
    }

    @GetMapping("/getCheckCode")
    public Response<String> getCheckCode(String email) {
        return oauthService.getCheckCode(email);
    }

    @PostMapping("/register")
    public Response<String> register(@RequestBody RegisterVo registerVo) {
        return oauthService.register(registerVo);
    }

    @PostMapping("/login")
    public Response<UserInfoVo> login(@RequestBody LoginVo loginVo){
        log.info("loginVo: {}", loginVo);
        return oauthService.login(loginVo);
    }
}
