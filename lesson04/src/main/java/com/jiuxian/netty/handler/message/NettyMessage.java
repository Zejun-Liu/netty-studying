package com.jiuxian.netty.handler.message;

import lombok.Data;

/**
 * Author: LIU ZEJUN
 * Date: 2019-05-08 12:44:00
 * Comment: 发送消息modal
 */

@Data
public class NettyMessage {
    private NettyMessageType messageType;
    private Long fromId;
    private Long receiverId;
    private String message;
}
