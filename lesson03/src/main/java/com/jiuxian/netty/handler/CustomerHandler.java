package com.jiuxian.netty.handler;

import com.jiuxian.netty.NettyContext;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * Author: LIU ZEJUN
 * Date: 2019-05-08 14:19:00
 * Comment:
 */


public class CustomerHandler extends SimpleChannelInboundHandler<Long> {

    // 用于记录和管理所有客户端的channle
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) {
        // 获取客户端传输过来的消息
        System.out.println("接受到的数据：" + msg);
        System.out.println("Context:" + NettyContext.get());

//		for (Channel channel: clients) {
//			channel.writeAndFlush(
//				new TextWebSocketFrame(
//						"[服务器在]" + LocalDateTime.now()
//						+ "接受到消息, 消息为：" + content));
//		}
        // 下面这个方法，和上面的for循环，一致
        channelGroup.writeAndFlush(
                new TextWebSocketFrame(
                        "[服务器在]" + LocalDateTime.now()
                                + "接受到消息, 消息为：" + msg));

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channelGroup.add(ctx.channel());
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        // 当触发handlerRemoved，ChannelGroup会自动移除对应客户端的channel
//		clients.remove(ctx.channel());
        System.out.println("客户端断开，channle对应的长id为：" + ctx.channel().id().asLongText());
        System.out.println("客户端断开，channle对应的短id为：" + ctx.channel().id().asShortText());
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel。。。注册");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel。。。移除");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel。。。活跃");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel。。。不活跃");
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channeld读取完毕。。。");
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("用户事件触发。。。");
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel可写更改");
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("有异常");
        super.exceptionCaught(ctx, cause);
    }

}
