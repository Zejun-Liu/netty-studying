package com.jiuxian.netty.handler;

import com.jiuxian.netty.handler.message.NettyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Author: LIU ZEJUN
 * Date: 2019-05-08 11:58:00
 * Comment: 会话
 */
public class NettyChatHandler extends SimpleChannelInboundHandler<NettyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyMessage msg) {
        System.out.println("接受到的数据：" + msg);
        msg.getMessageType().getAction().action(ctx, msg);
    }
}
