package com.jiuxian;

import com.jiuxian.netty.NettyServer;

/**
 * Author: LIU ZEJUN
 * Date: 2019-05-08 11:47:00
 * Comment: 启动类
 */
//测试数据 先连接
//{"messageType":"CONNECT","fromId":1,"receiverId":2,"message":"我是1"}
//{"messageType":"CONNECT","fromId":2,"receiverId":3,"message":"我是2"}

//进行会话
//{"messageType":"CHAT","fromId":1,"receiverId":2,"message":"我是1"}
//{"messageType":"CHAT","fromId":2,"receiverId":3,"message":"我是2"}

public class Application {

    /**
     * 端口号
     */
    public final static int PORT = 8888;

    /**
     * 心跳读空闲
     */
    public final static int READER_IDLE_TIME_SECONDS = 6;
    /**
     * 心跳写空闲
     */
    public final static int WRITER_IDLE_TIME_SECONDS = 8;
    /**
     * 心跳读写空闲
     */
    public final static int ALL_IDLE_TIME_SECONDS = 12;


    public static void main(String[] args) throws InterruptedException {
        NettyServer.getInstance().start();
    }

}
