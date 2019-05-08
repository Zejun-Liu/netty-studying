package com.jiuxian.netty.handler.action;

import com.jiuxian.netty.handler.message.NettyMessage;
import io.netty.channel.ChannelHandlerContext;

/**
 * Author: LIU ZEJUN
 * Date: 2019-05-08 12:56:00
 * Comment: 消息处理接口
 */
public interface NettyMessageTypeAction {

    void action(ChannelHandlerContext ctx, NettyMessage messageFrame);
}
