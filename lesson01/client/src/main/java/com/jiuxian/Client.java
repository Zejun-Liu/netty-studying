package com.jiuxian;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-30 17:37:00
 * Comment:
 */


public class Client {
    private static final String HOST = "127.0.0.1";
    private final static int PORT = 8000;

    @SneakyThrows
    public static void main(String[] args) {
        Socket socket = new Socket(HOST, PORT);
        new Thread(() -> {
            try {
                System.out.println("客户端启动!");
                while (true) {
                    String message = "hello world" + "::" + Thread.currentThread().getName();
                    System.out.println("传递的消息：" + message);
                    socket.getOutputStream().write(message.getBytes());
                    Thread.sleep(5000);

          /*          InputStream inputStream = socket.getInputStream();
                    byte[] data = new byte[1024];
                    int len;
                    while ((len = inputStream.read(data)) != -1) {
                        String callback = new String(data, 0, len);
                        System.out.println("服务端返回:" + callback);
                    }*/
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
