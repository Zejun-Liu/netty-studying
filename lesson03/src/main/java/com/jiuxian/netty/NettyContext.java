package com.jiuxian.netty;

/**
 * Author: LIU ZEJUN
 * Date: 2019-05-08 14:25:00
 * Comment:
 */


public class NettyContext {
    private final static ThreadLocal<Long> local = new ThreadLocal<>();

    public static void set(Long id) {
        local.set(id);
    }

    public static Long get() {
        return local.get();
    }
}
