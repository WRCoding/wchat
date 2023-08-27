package com.longjunwang.wchathttp.controller.oauth;

import com.longjunwang.wchathttp.service.oauth.OauthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/oauth")
public class OauthController {

    @Resource
    private OauthService oauthService;

    @GetMapping("/callback")
    public String callback(String code) throws IOException {
        log.info("code: {}", code);
        String accessToken = oauthService.getAccessToken(code);
        return code;
    }
}
