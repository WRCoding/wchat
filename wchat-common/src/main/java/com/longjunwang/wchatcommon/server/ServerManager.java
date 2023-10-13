package com.longjunwang.wchatcommon.server;

import cn.hutool.json.JSONUtil;
import com.longjunwang.wchatcommon.pojo.ServerInfo;
import com.longjunwang.wchatcommon.route.RouterHandle;
import com.longjunwang.wchatcommon.util.RedisKey;
import com.longjunwang.wchatcommon.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * desc: serverManager
 *
 * @author ink
 * date:2023-10-04 19:35
 */
@Service
@Slf4j
public class ServerManager {

    @Resource
    private RouterHandle routerHandle;
    public ServerInfo getServerInfo(){
        ServerInfo serverInfo = routerHandle.getServerInfo(getAllServer());
        log.info("获取到的服务信息：{}", JSONUtil.toJsonStr(serverInfo));
        return serverInfo;
    }

    private List<ServerInfo> getAllServer(){
        return parse(RedisUtil.getList(RedisKey.SERVER_INFOS));
    }

    private List<ServerInfo> parse(List<String> list) {
        return list.stream().map(item -> JSONUtil.toBean(item, ServerInfo.class)).collect(Collectors.toList());
    }
}
