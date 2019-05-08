package com.jiuxian;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-30 17:26:00
 * Comment:
 */


public class Server {

    private int port;

    private ServerSocket serverSocket;

    public Server(int port) {
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("服务启动成功 ...Port:" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务启动失败");
        }

    }

    public void start() {

        new Thread(this::doStart).start();

    }

    private void doStart() {
        while (true) {
            Socket client = null;
            try {
                client = serverSocket.accept();
                new ClientHandler(client).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
