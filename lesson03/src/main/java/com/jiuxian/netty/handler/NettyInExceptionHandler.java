package com.jiuxian.netty.handler;

import com.jiuxian.core.exception.BaseException;
import com.jiuxian.core.util.JSONUtil;
import com.jiuxian.core.web.RestResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * Author: LIU ZEJUN
 * Date: 2019-05-08 13:15:00
 * Comment: 统一异常处理
 */
public class NettyInExceptionHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

        if (cause instanceof BaseException) {
            ctx.channel().writeAndFlush(getMessage((BaseException) cause));
        }
    }

    private TextWebSocketFrame getMessage(BaseException e) {
        return new TextWebSocketFrame(JSONUtil.toJSON(new RestResponse<>(e.getCode(), e.getMessage())));
    }
}
