package com.jiuxian.netty;

import com.jiuxian.Application;
import com.jiuxian.netty.handler.NettyServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Author: LIU ZEJUN
 * Date: 2019-05-08 11:47:00
 * Comment: Netty服务
 */
public class NettyServer {
    private static class NettyServerInstance {
        static NettyServer nettyServer = new NettyServer();
    }

    public static NettyServer getInstance() {
        return NettyServerInstance.nettyServer;
    }

    private static ServerBootstrap server;

    private NettyServer() {
        EventLoopGroup parent = new NioEventLoopGroup();
        EventLoopGroup child = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(parent, child)
                .channel(NioServerSocketChannel.class)
                .childHandler(new NettyServerInitializer());
    }


    public void start() throws InterruptedException {
        ChannelFuture channelFuture = server.bind(Application.PORT).sync();
        channelFuture.channel().closeFuture().sync();
    }
}
