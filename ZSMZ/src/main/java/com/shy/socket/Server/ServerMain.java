package com.shy.socket.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    static ServerSocket serverSocket;
    public static void main(String[] args) throws IOException {


        serverSocket = new ServerSocket(5555);

        Socket socketShy;
        Socket socketZph;
        System.out.println("等待1对方连接");
        socketShy = serverSocket.accept();
        System.out.println("等待2对方连接");
        socketZph = serverSocket.accept();
        System.out.println("连接成功");
        Thread threadZph = new Thread(new ServerZph(socketShy, socketZph));
        Thread threadShy = new Thread(new ServerShy(socketShy, socketZph));
        threadZph.start();
        threadShy.start();

    }
    public static void Reboot(ServerSocket serverSocket){
        ServerMain.serverSocket= serverSocket;
    }
}
