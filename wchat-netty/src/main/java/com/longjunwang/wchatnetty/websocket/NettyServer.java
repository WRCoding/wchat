package com.longjunwang.wchatnetty.websocket;

import com.longjunwang.wchatnetty.RegisterServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.NettyRuntime;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.UnknownHostException;

/**
 * desc: nettyServer
 *
 * @author ink
 * date:2023-09-24 10:03
 */
@Slf4j
@Component
@Order(1)
public class NettyServer {


    @Value("${server.nettyPort}")
    private String nettyPort;
    private final EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private final EventLoopGroup workerGroup = new NioEventLoopGroup(NettyRuntime.availableProcessors());

    private ChannelFuture channelFuture;

    @PostConstruct
    public void init() throws InterruptedException {
        startWebSocketServer();
    }

    private void startWebSocketServer() throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
//                .option(ChannelOption.SO_BACKLOG, 128)
//                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new NettyWebSocketInitial());
        // 启动服务器，监听端口，阻塞直到启动成功
        channelFuture = serverBootstrap.bind(Integer.parseInt(nettyPort)).sync();
        if (!RegisterServer.registerServer()){
            close();
            log.error("Netty服务启动失败，端口：7779");
        }else{
            log.info("Netty服务启动成功，端口：7779");
        }
    }

    private void close() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        channelFuture.channel().close();
    }

}
