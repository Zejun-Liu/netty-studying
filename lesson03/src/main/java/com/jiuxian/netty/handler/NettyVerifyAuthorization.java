package com.jiuxian.netty.handler;

import com.jiuxian.core.exception.BaseException;
import com.jiuxian.core.util.JSONUtil;
import com.jiuxian.netty.context.NettyContext;
import com.jiuxian.netty.handler.message.NettyMessage;
import com.jiuxian.netty.handler.message.NettyMessageType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * Author: LIU ZEJUN
 * Date: 2019-05-08 13:01:00
 * Comment: 信息验证
 */
public class NettyVerifyAuthorization extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        NettyMessage nettyMessage = JSONUtil.toBean(msg.text(), NettyMessage.class);
        NettyContext.setChannel(nettyMessage.getFromId(), ctx.channel());
        this.verify(nettyMessage);
        super.channelRead(ctx, nettyMessage);
    }

    private void verify(NettyMessage nettyMessage) {
        if (nettyMessage.getMessageType() == null) {
            throw new BaseException("会话类型不能为空");
        }
        if (nettyMessage.getFromId() == null) {
            throw new BaseException("发送人不能为空");
        }
        if (nettyMessage.getMessageType() == NettyMessageType.CHAT
                && nettyMessage.getReceiverId() == null) {
            throw new BaseException("接收人不能为空");
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        NettyContext.channelGroup.add(ctx.channel());
        super.handlerAdded(ctx);
    }
}
