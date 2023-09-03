package com.longjunwang.wchatcommon.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class CheckCodeUtil {

    private static final String CHECK_CODE_PREFIX = "checkCode:";

    private static final long CHECK_CODE_EXPIRE_TIME = 60 * 1000;
    public static String generateCheckCode(String email){
        String checkCode = String.valueOf((int)((Math.random()*9+1)*100000));
        save2Cache(email, checkCode);
        return checkCode;
    }

    public static boolean verifyCode(String email, String checkCode) {
        String cacheCode = (String) RedisUtil.getHashData(CHECK_CODE_PREFIX, email);
        if (Objects.isNull(cacheCode)) {
            return false;
        }
        String[] split = cacheCode.split(":");
        if (split.length != 2) {
            return false;
        }
        if (!split[0].equals(checkCode)) {
            return false;
        }
        long time = Long.parseLong(split[1]);
        return System.currentTimeMillis() - time <= CHECK_CODE_EXPIRE_TIME;
    }

    private static void save2Cache(String email, String checkCode) {
        RedisUtil.saveHashData(CHECK_CODE_PREFIX, email, generateValue(checkCode));
    }

    private static String generateValue(String checkCode) {
        return checkCode + ":" + System.currentTimeMillis();
    }
}
