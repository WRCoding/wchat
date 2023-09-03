package com.longjunwang.wchathttp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
        "com.longjunwang.wchatcommon",
        "com.longjunwang.wchathttp"
})
@MapperScan(basePackages = {"com.longjunwang.wchatcommon.mapper"})
public class WchatHttpApplication {

    public static void main(String[] args) {
        SpringApplication.run(WchatHttpApplication.class, args);
    }

}
