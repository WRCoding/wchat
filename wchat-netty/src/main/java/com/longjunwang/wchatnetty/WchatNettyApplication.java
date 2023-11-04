package com.longjunwang.wchatnetty;

import com.longjunwang.wchatcommon.util.SpringBeanUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author wanglongjun
 */
@SpringBootApplication
@Import(SpringBeanUtil.class)
@ComponentScan({
		"com.longjunwang.wchatcommon",
		"com.longjunwang.wchatnetty"
})
@MapperScan(basePackages = {"com.longjunwang.wchatcommon.mapper"})
public class WchatNettyApplication {

	public static void main(String[] args) {
		SpringApplication.run(WchatNettyApplication.class, args);
	}

}
