package com.jiuxian;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-30 17:25:00
 * Comment:
 */


public class ServerBoot {

    private static final int PORT = 8000;

    public static void main(String[] args) {
        Server server = new Server(PORT);
        server.start();
    }
}
