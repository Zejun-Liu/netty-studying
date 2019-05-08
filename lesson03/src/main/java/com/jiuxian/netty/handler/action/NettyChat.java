package com.jiuxian.netty.handler.action;

import com.jiuxian.core.exception.BaseException;
import com.jiuxian.core.util.JSONUtil;
import com.jiuxian.core.web.RestResponse;
import com.jiuxian.netty.context.NettyContext;
import com.jiuxian.netty.handler.message.NettyMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * Author: LIU ZEJUN
 * Date: 2019-05-08 12:59:00
 * Comment: 会话
 */
public class NettyChat implements NettyMessageTypeAction {

    @Override
    public void action(ChannelHandlerContext ctx, NettyMessage messageFrame) {
        System.out.println("收到来自channel为[" + ctx.channel().id() + "]开启会话...");
        /*NettyContext.channelGroup.writeAndFlush(new TextWebSocketFrame(
                "[服务器在]" + LocalDateTime.now()
                        + "接受到消息, 消息为：" + JSONUtil.toJSON(messageFrame)));*/
        Long receiverId = messageFrame.getReceiverId();

        Channel localChannel = NettyContext.getChannel(receiverId);
        if (localChannel == null) {
            throw new BaseException("接收人：" + receiverId + "不在线!");
        }
        Channel receiverChannel = NettyContext.channelGroup.find(localChannel.id());
        if (receiverChannel == null) {
            throw new BaseException("接收人：" + receiverId + "不在线!");
        }
        receiverChannel.writeAndFlush(new TextWebSocketFrame(JSONUtil.toJSON(new RestResponse<>(messageFrame.getMessage()))));
    }
}
