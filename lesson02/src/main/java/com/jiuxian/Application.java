package com.jiuxian;

import com.jiuxian.netty.NettyServer;

/**
 * Author: LIU ZEJUN
 * Date: 2019-05-08 14:12:00
 * Comment:
 */


public class Application {

    public static void main(String[] args) throws InterruptedException {
        NettyServer.getInstance().start();
    }
}
