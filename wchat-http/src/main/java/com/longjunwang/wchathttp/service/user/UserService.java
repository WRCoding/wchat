package com.longjunwang.wchathttp.service.user;

import cn.hutool.core.util.IdUtil;
import com.longjunwang.wchatcommon.constant.ResponseCode;
import com.longjunwang.wchatcommon.pojo.Response;
import com.longjunwang.wchatcommon.pojo.ServerInfo;
import com.longjunwang.wchatcommon.entity.UserInfo;
import com.longjunwang.wchatcommon.pojo.vo.LoginVo;
import com.longjunwang.wchatcommon.pojo.vo.RegisterVo;
import com.longjunwang.wchatcommon.pojo.vo.UserInfoVo;
import com.longjunwang.wchatcommon.mapper.UserInfoMapper;
import com.longjunwang.wchatcommon.server.ServerManager;
import com.longjunwang.wchatcommon.util.CheckCodeUtil;
import com.longjunwang.wchatcommon.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@Service
public class UserService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private ServerManager serverManager;


    public UserInfo selectByPhoneOrEmail(String key) {
        return userInfoMapper.selectByKey(key);
    }

    public Response<String> register(RegisterVo registerVo) {
        if (Objects.nonNull(selectByPhoneOrEmail(registerVo.getEmail()))){
            return Response.customer(ResponseCode.PARAM_ERROR,"邮箱已经注册", "");
        }
        if (!CheckCodeUtil.verifyCode(registerVo.getEmail(), registerVo.getCheckCode())){
            return Response.customer(ResponseCode.VERIFY_ERROR,"验证码错误或失效", "");
        }
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName(CommonUtil.defaultName());
            userInfo.setId(IdUtil.getSnowflakeNextIdStr());
            userInfo.setEmail(registerVo.getEmail());
            userInfo.setPassWord(registerVo.getPassWord());
            userInfoMapper.insert(userInfo);
            return Response.success("注册成功");
        } catch (Exception e) {
            log.warn("注册失败,邮件可能已经被注册, e: {}", e.getMessage());
            return Response.customer(ResponseCode.PARAM_ERROR,"邮箱已经注册", "");
        }
    }

    public Response<UserInfoVo> login(LoginVo loginVo) {
        log.info("loginVo: {}", loginVo);
        UserInfo userInfo = userInfoMapper.selectByKey(loginVo.getKey());
        if(Objects.isNull(userInfo)){
            return Response.customer(ResponseCode.VERIFY_ERROR,"账号不存在", null);
        }

        if (!userInfo.getPassWord().equals(loginVo.getPassWord())){
            return Response.customer(ResponseCode.VERIFY_ERROR,"密码错误", null);
        }
        return Response.success(initUserInfoVo(userInfo));
    }

    private UserInfoVo initUserInfoVo(UserInfo userInfo) {
        ServerInfo serverInfo = serverManager.getServerInfo();
        String ipAndPort = serverInfo.getIp() + "@" + serverInfo.getNettyPort();
        UserInfoVo userInfoVo = CommonUtil.transfer(userInfo, UserInfoVo.class);
        userInfoVo.setIpAndPort(ipAndPort);
        return userInfoVo;
    }
}
