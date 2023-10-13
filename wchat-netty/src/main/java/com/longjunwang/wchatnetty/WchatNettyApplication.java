package com.longjunwang.wchatnetty;

import com.longjunwang.wchatcommon.util.SpringBeanUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author wanglongjun
 */
@SpringBootApplication
@Import(SpringBeanUtil.class)
public class WchatNettyApplication {

	public static void main(String[] args) {
		SpringApplication.run(WchatNettyApplication.class, args);
	}

}
