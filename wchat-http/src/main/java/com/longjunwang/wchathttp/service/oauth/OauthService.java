package com.longjunwang.wchathttp.service.oauth;

import com.longjunwang.wchatcommon.constant.ResponseCode;
import com.longjunwang.wchatcommon.pojo.Response;
import com.longjunwang.wchatcommon.entity.UserInfo;
import com.longjunwang.wchatcommon.pojo.vo.LoginVo;
import com.longjunwang.wchatcommon.pojo.vo.RegisterVo;
import com.longjunwang.wchatcommon.pojo.vo.UserInfoVo;
import com.longjunwang.wchatcommon.util.CheckCodeUtil;
import com.longjunwang.wchathttp.service.mail.MailService;
import com.longjunwang.wchathttp.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Service
public class OauthService {

    @Resource
    private UserService userService;

    @Resource
    private GitHubService gitHubService;

    @Resource
    private MailService mailService;
    public Response<String> getCheckCode(String email) {
        UserInfo userInfo = userService.selectByPhoneOrEmail(email);
        if (Objects.nonNull(userInfo)){
            log.info(userInfo.toString());
            return Response.customer(ResponseCode.PARAM_ERROR,"邮箱已经注册", "");
        }
        mailService.sendMail(email, CheckCodeUtil.generateCheckCode(email));
        return Response.success("验证码已发送");
    }


    public String getAccessToken(String code) throws IOException {
        return gitHubService.getAccessToken(code);
    }

    public Response<String> register(RegisterVo registerVo) {
        return userService.register(registerVo);
    }

    public Response<UserInfoVo> login(LoginVo loginVo) {
        return userService.login(loginVo);
    }
}
