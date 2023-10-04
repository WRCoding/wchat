package com.longjunwang.wchatnetty.service;

import com.longjunwang.wchatcommon.util.RedisKey;
import com.longjunwang.wchatcommon.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * desc: userService
 *
 * @author ink
 * date:2023-09-24 11:22
 */
@Service
@Slf4j
public class UserService {


    public boolean verifyToken(String token){
        String value = RedisUtil.get(RedisKey.getKey(RedisKey.USER_TOKEN_STRING, token));
        String cacheToken = getSplit(value, 0);
        return Objects.isNull(cacheToken) || Objects.equals(cacheToken, token);
    }

    public String getUserIdByToken(String token){
        String value = RedisUtil.get(RedisKey.getKey(RedisKey.USER_TOKEN_STRING, token));
        String userId = getSplit(value, 1);
        return userId;
    }

    private String getSplit(String value, int index){
        String[] split = value.split(":");
        return split[index];
    }
}
