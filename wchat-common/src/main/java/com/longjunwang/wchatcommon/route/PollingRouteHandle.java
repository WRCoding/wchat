package com.longjunwang.wchatcommon.route;

import com.longjunwang.wchatcommon.pojo.ServerInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * desc: PollingRouteHandle
 *
 * @author ink
 * date:2023-10-04 19:43
 */
@Service
public class PollingRouteHandle implements RouterHandle {
    private final AtomicInteger index = new AtomicInteger(0);
    @Override
    public ServerInfo getServerInfo(List<ServerInfo> allServer) {
        int i = index.incrementAndGet() % allServer.size();
        return allServer.get(i);
    }
}
