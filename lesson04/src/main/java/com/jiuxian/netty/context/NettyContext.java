package com.jiuxian.netty.context;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: LIU ZEJUN
 * Date: 2019-05-08 13:49:00
 * Comment: 可以使用redis进行存储
 */
public class NettyContext {

    private final static ConcurrentHashMap<Long, Channel> userChannel = new ConcurrentHashMap<>();

    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static Channel getChannel(Long userId) {
        return userChannel.get(userId);
    }

    public static void setChannel(Long userId, Channel channel) {
        userChannel.put(userId, channel);
    }

    public static void removeChannel(Long userId) {
        userChannel.remove(userId);
    }

}
