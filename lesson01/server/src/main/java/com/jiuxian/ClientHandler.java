package com.jiuxian;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-30 17:32:00
 * Comment:
 */


public class ClientHandler {

    private final Socket socket;

    public ClientHandler(Socket client) {
        this.socket = client;
    }

    public void start() {
        System.out.println("新客户接入::" + Thread.currentThread().getName());
        new Thread(this::doStart).start();
    }

    private void doStart() {
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();

            while (true) {
                byte[] data = new byte[1024];
                int len;
                while ((len = inputStream.read(data)) != -1) {
                    String message = new String(data, 0, len);
                    System.out.println("客户端传来消息：" + message);
                    socket.getOutputStream().write(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
