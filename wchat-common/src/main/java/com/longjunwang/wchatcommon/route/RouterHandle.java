package com.longjunwang.wchatcommon.route;

import com.longjunwang.wchatcommon.pojo.ServerInfo;

import java.util.List;

/**
 * desc: RouterHandle
 *
 * @author ink
 * date:2023-10-04 19:41
 */
public interface RouterHandle {

    /**
     * Description: 获取服务器信息
     * @return com.longjunwang.wchatcommon.entity.ServerInfo
     * Author: ink
     * Date: 2023/10/4
    */
    ServerInfo getServerInfo(List<ServerInfo> allServer);
}
