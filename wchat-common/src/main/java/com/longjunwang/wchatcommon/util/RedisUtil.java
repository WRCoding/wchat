package com.longjunwang.wchatcommon.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RedisUtil {


    private static RedisTemplate<String, Object> redisTemplate;
    private static HashOperations<String, String, Object> hashOperations;

    static {
        RedisUtil.redisTemplate = SpringBeanUtil.getBean("redisTemplate", RedisTemplate.class);
        RedisUtil.hashOperations = redisTemplate.opsForHash();
    }

//    hash
    public static void saveHashData(String key, String hashKey, Object value) {
        hashOperations.put(key, hashKey, value);
    }

    public static Object getHashData(String key, String hashKey) {
        return hashOperations.get(key, hashKey);
    }

//    string

    /**
     * 设置缓存过期时间
     * @param key 键
     * @param timeout 过期时间（秒）
     * @return 是否设置成功
     */
    public static boolean expire(String key, long timeout) {
        try {
            if (timeout > 0) {
                redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取缓存过期时间
     * @param key 键
     * @return 过期时间（秒）
     */
    public static long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断缓存中是否存在该键
     * @param key 键
     * @return 是否存在
     */
    public static boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("hasKey error: {}", e.getMessage());
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key 键
     */
    public static void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 获取缓存
     * @param key 键
     * @return 值
     */
    public static String get(String key) {
        return key == null ? null : (String) redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置缓存
     * @param key 键
     * @param value 值
     * @return 是否设置成功
     */
    public static boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置缓存并设置过期时间
     * @param key 键
     * @param value 值
     * @param timeout 过期时间（秒）
     * @return 是否设置成功
     */
    public static boolean set(String key, Object value, long timeout) {
        try {
            if (timeout > 0) {
                redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}