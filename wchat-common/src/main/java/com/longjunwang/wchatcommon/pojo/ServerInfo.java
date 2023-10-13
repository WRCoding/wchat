package com.longjunwang.wchatcommon.pojo;

import cn.hutool.core.util.IdUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
        String topicName = IdUtil.fastSimpleUUID();
        return new ServerInfo(topicName, address, 7779);
    }
}
