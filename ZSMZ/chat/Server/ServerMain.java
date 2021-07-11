package chat.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerMain {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket;
        serverSocket = new ServerSocket(5555);

        Socket socketH1;
        Socket socketH2;
        Socket socketH3;
        Socket socketH4;
        System.out.println("等待1对方连接");
        socketH1 = serverSocket.accept();
        System.out.println("等待2对方连接");
        socketH2 = serverSocket.accept();
        System.out.println("等待3对方连接");
        socketH3 = serverSocket.accept();
        System.out.println("等待4对方连接");
        socketH4 = serverSocket.accept();
        System.out.println("连接成功");
        Thread threadH1 = new Thread(new ServerH1(socketH1, socketH2,socketH3,socketH4));
        Thread threadH2 = new Thread(new ServerH2(socketH1, socketH2,socketH3,socketH4));
        Thread threadH3 = new Thread(new ServerH3(socketH1, socketH2,socketH3,socketH4));
        Thread threadH4 = new Thread(new ServerH4(socketH1, socketH2,socketH3,socketH4));
        threadH1.start();
        threadH2.start();
        threadH3.start();
        threadH4.start();

    }
}
