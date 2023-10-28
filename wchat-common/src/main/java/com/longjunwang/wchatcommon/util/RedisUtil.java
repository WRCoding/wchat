package com.longjunwang.wchatcommon.util;


import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author wanglongjun
 */
@Slf4j
@Component
@DependsOn("springBeanUtil")
public class RedisUtil {


    private static RedisTemplate<String, String> redisTemplate;
    private static HashOperations<String, String, String> hashOperations;

    private static ListOperations<String, String> listOperations;

    static {
        RedisUtil.redisTemplate = SpringBeanUtil.getBean("redisTemplate", RedisTemplate.class);
        setConfig();
        RedisUtil.hashOperations = redisTemplate.opsForHash();
        RedisUtil.listOperations = redisTemplate.opsForList();
    }

    private static void setConfig() {
        // 设置key的序列化方式为StringRedisSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 设置value的序列化方式为Jackson2JsonRedisSerializer
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        // 设置hash key的序列化方式为StringRedisSerializer
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // 设置hash value的序列化方式为Jackson2JsonRedisSerializer
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        redisTemplate.afterPropertiesSet();
    }

    public static void saveListOne(String key, String value) throws Exception {
        if (Objects.isNull(listOperations)) {
            log.error("listOperations is null");
            throw new RuntimeException("listOperations is null");
        } else {
            listOperations.rightPush(key, value);
        }
    }

    public static List<String> getList(String key) {
        if (Objects.isNull(listOperations)) {
            log.error("listOperations is null");
            throw new RuntimeException("listOperations is null");
        } else {
            return listOperations.range(key, 0, -1);
        }
    }

    //    hash
    public static void saveHashData(String key, String hashKey, String value) {
        hashOperations.put(key, hashKey, value);
    }

    public static Object getHashData(String key, String hashKey) {
        return hashOperations.get(key, hashKey);
    }


    //    string

    /**
     * 设置缓存过期时间
     *
     * @param key     键
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
     *
     * @param key 键
     * @return 过期时间（秒）
     */
    public static long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断缓存中是否存在该键
     *
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
     *
     * @param key 键
     */
    public static void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public static String get(String key) {
        return key == null ? null : (String) redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置缓存
     *
     * @param key   键
     * @param value 值
     * @return 是否设置成功
     */
    public static boolean set(String key, String value) {
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
     *
     * @param key     键
     * @param value   值
     * @param timeout 过期时间（秒）
     * @return 是否设置成功
     */
    public static boolean set(String key, String value, long timeout) {
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