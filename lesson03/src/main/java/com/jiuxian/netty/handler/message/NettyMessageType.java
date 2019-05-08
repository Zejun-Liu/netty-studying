package com.jiuxian.netty.handler.message;

import com.jiuxian.netty.handler.action.NettyChat;
import com.jiuxian.netty.handler.action.NettyConnect;
import com.jiuxian.netty.handler.action.NettyKeepAlive;
import com.jiuxian.netty.handler.action.NettyMessageTypeAction;
import lombok.Getter;

/**
 * Author: LIU ZEJUN
 * Date: 2019-05-08 12:50:00
 * Comment: 消息类型
 */
public enum NettyMessageType {
    CONNECT(new NettyConnect(), "第一次(或重连)初始化连接"),
    CHAT(new NettyChat(), "聊天消息"),
    SIGNED(null, "消息签收"),
    KEEPALIVE(new NettyKeepAlive(), "客户端保持心跳"),
    PULL_FRIEND(null, "拉取好友");

    @Getter
    private NettyMessageTypeAction action;
    private String text;

    NettyMessageType(NettyMessageTypeAction action, String text) {
        this.action = action;
        this.text = text;
    }
}
