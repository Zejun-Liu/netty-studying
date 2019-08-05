package com.jiuxian.netty.handler;

import com.jiuxian.netty.context.NettyContext;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Author: LIU ZEJUN
 * Date: 2019-05-08 15:58:00
 * Comment: 心跳
 */
public class NettyHeartBeatHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {

        // 判断evt是否是IdleStateEvent（用于触发用户事件，包含 读空闲/写空闲/读写空闲 ）
        if (!(evt instanceof IdleStateEvent)) {
            return;
        }
        IdleStateEvent event = (IdleStateEvent) evt;

        if (event.state() == IdleState.READER_IDLE) {
            System.out.println("进入读空闲...");
        } else if (event.state() == IdleState.WRITER_IDLE) {
            System.out.println("进入写空闲...");
        } else if (event.state() == IdleState.ALL_IDLE) {

            System.out.println("channel关闭前，users的数量为：" + NettyContext.channelGroup.size());

            Channel channel = ctx.channel();
            // 关闭无用的channel，以防资源浪费
            channel.close();
            System.out.println("channel关闭后，users的数量为：" + NettyContext.channelGroup.size());
        }
    }
}
