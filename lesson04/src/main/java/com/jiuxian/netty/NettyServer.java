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

/**
 * 要启动 Netty 服务端,必须要指定三类属性,分别是线程模型、 IO 模型、 连接读写处理逻辑.
 * Netty 服务端启动流程:创建引导类->指定线程模型、 IO 模型、连接读写处理逻辑->绑定端口.
 * 创建两个NioEventLoopGroup,这两个对象可以看做是传统IO编程模型的两大线程组,boosGroup表示监听端口,创建新连接的线程组,workerGroup表示处理每一条连接的数据读写的线程组
 * <p>
 * 创建引导类 ServerBootstrap进行服务端的启动工作,通过.group(boosGroup, workerGroup)给引导类配置两大线程定型引导类的线程模型指定服务端的IO模型为NIO,通过.channel(NioServerSocketChannel.class)来指定IO模型
 * <p>
 * 调用childHandler()方法给引导类创建ChannelInitializer定义后续每条连接的数据读写,业务处理逻辑,泛型参数NioSocketChannel是Netty对NIO类型的连接的抽象,而NioServerSocketChannel也是对NIO类型的连接的抽象
 * <p>
 * serverBootstrap.bind()是异步的方法调用之后是立即返回的,返回值是ChannelFuture,给ChannelFuture添加监听器GenericFutureListener,在GenericFutureListener的operationComplete方法里面监听端口是否绑定成功
 * <p>
 * childHandler()用于指定处理新连接数据的读写处理逻辑,handler()用于指定在服务端启动过程中的一些逻辑
 * <p>
 * attr()方法给服务端的channel即NioServerSocketChannel指定一些自定义属性,通过channel.attr()取出该属性,给NioServerSocketChannel维护一个map
 * <p>
 * childAttr()方法给每一条连接指定自定义属性,通过channel.attr()取出该属性
 * <p>
 * childOption()方法给每条连接设置一些TCP底层相关的属性:
 * <p>
 * ChannelOption.SO_KEEPALIVE表示是否开启TCP底层心跳机制,true为开启
 * <p>
 * ChannelOption.SO_REUSEADDR表示端口释放后立即就可以被再次使用,因为一般来说,一个端口释放后会等待两分钟之后才能再被使用
 * <p>
 * ChannelOption.TCP_NODELAY表示是否开始Nagle算法,true表示关闭,false表示开启,通俗地说,如果要求高实时性,有数据发送时就马上发送,就关闭,如果需要减少发送次数减少网络交互就开启
 * <p>
 * option()方法给服务端channel设置一些TCP底层相关的属性:
 * <p>
 * ChannelOption.SO_BACKLOG表示系统用于临时存放已完成三次握手的请求的队列的最大长度,如果连接建立频繁,服务器处理创建新连接较慢,适当调大该参数
 */
public class NettyServer {
    private static class NettyServerInstance {
        static NettyServer nettyServer = new NettyServer();
    }

    public static NettyServer getInstance() {
        return NettyServerInstance.nettyServer;
    }

    private static ServerBootstrap server;

    /**
     * 1.parent,IOServer.java中的接受新连接线程,主要负责创建新连接
     * 2.child IOClient.java中的负责读取数据的线程,主要用于读取数据以及业务逻辑处理
     */
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
