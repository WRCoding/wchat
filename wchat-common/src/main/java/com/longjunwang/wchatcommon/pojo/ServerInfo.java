package com.longjunwang.wchatcommon.pojo;

import cn.hutool.core.util.IdUtil;
import com.longjunwang.wchatcommon.util.SpringBeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.env.Environment;

import java.util.Objects;
import java.util.Properties;

/**
 * desc: ServerInfo
 *
 * @author ink
 * date:2023-10-04 10:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServerInfo {

    private String topicName;
    private String ip;
    private Integer nettyPort;

    public static ServerInfo initServerInfo(String address){
        Environment environment = SpringBeanUtil.getBean(Environment.class);
        String nettyPort = environment.getProperty("server.nettyPort");
        String topicName = address.replaceAll("\\.", "-") + "-" + nettyPort;
        return new ServerInfo(topicName, address, Integer.valueOf(Objects.nonNull(nettyPort) ? nettyPort : "6789"));
    }


}
