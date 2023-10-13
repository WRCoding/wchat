package com.longjunwang.wchatnetty;

import cn.hutool.json.JSONUtil;
import com.longjunwang.wchatcommon.pojo.ServerInfo;
import com.longjunwang.wchatcommon.util.RedisKey;
import com.longjunwang.wchatcommon.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.util.List;

/**
 * desc: registerServer
 *
 * @author ink
 * date:2023-10-04 11:09
 */
@Slf4j
public class RegisterServer {

    public static boolean registerServer() {
        try {
            String address  = InetAddress.getLocalHost().getHostAddress();
            ServerInfo serverInfo = ServerInfo.initServerInfo(address);
            String serverInfoJson = JSONUtil.toJsonStr(serverInfo);
            log.info("当前服务信息：{}", serverInfoJson);
            RedisUtil.saveListOne(RedisKey.SERVER_INFOS, serverInfoJson);
            return true;
        } catch (Exception e) {
            log.error("注册服务失败，e: {}", e.getMessage());
            return false;
        }
    }

    public static void getAll(){
        List<String> list = RedisUtil.getList(RedisKey.SERVER_INFOS);
        for (String s : list) {
            ServerInfo serverInfo = JSONUtil.toBean(s, ServerInfo.class);
            log.info("serverInfo：{}", serverInfo);
        }
    }
}
