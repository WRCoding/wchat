package com.longjunwang.wchatcommon.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.UUID;

@Slf4j
public class CommonUtil {
    private static final String USER_NAME_PREFIX = "wChat_";

    public static String defaultName(){
        return USER_NAME_PREFIX + UUID.randomUUID().toString().substring(0, 8);
    }

    public static <SOURCE,DEST> DEST transfer(SOURCE source, Class<DEST> destClass){
        if (Objects.nonNull(source)){
            try {
                DEST dest = destClass.getDeclaredConstructor().newInstance();
                org.springframework.beans.BeanUtils.copyProperties(source, dest);
                return dest;
            } catch (Exception e) {
                log.error("covertToDest error, e: {}", e.getMessage());
            }
        }
        return null;
    }
}
