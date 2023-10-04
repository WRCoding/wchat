package com.longjunwang.wchatcommon.util;

/**
 * @author wanglongjun
 */
public class RedisKey {
    private static final String BASE_KEY = "wchat:";

    /**
     * 用户token存放
     */
    public static final String USER_TOKEN_STRING = "token:%d";


    public static String getKey(String key, Object... objects) {
        return BASE_KEY + String.format(key, objects);
    }

}
