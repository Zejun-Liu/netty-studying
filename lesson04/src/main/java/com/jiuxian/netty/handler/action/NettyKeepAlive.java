package com.jiuxian.netty.handler.action;

import com.jiuxian.netty.handler.message.NettyMessage;
import io.netty.channel.ChannelHandlerContext;

/**
 * Author: LIU ZEJUN
 * Date: 2019-05-08 12:59:00
 * Comment: 心跳
 */
public class NettyKeepAlive implements NettyMessageTypeAction {

    @Override
    public void action(ChannelHandlerContext ctx, NettyMessage messageFrame) {
        System.out.println("收到来自channel为[" + ctx.channel().id() + "]的心跳包...");
    }
}
