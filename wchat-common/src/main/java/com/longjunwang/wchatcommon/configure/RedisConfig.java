package com.longjunwang.wchatcommon.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 设置key的序列化方式为StringRedisSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // 设置value的序列化方式为Jackson2JsonRedisSerializer
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));

        // 设置hash key的序列化方式为StringRedisSerializer
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        // 设置hash value的序列化方式为Jackson2JsonRedisSerializer
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
