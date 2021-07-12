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
        Socket socketH2 = null;
        Socket socketH3 = null;
        Socket socketH4 = null;
        System.out.println("等待1对方连接");
        socketH1 = serverSocket.accept();
        Thread threadH1 = new Thread(new ServerH1(socketH1, socketH2,socketH3,socketH4));
        threadH1.start();
        System.out.println("等待2对方连接");
        socketH2 = serverSocket.accept();
        Thread threadH2 = new Thread(new ServerH2(socketH2, socketH1,socketH3,socketH4));
        threadH2.start();
        System.out.println("等待3对方连接");
        socketH3 = serverSocket.accept();
        Thread threadH3 = new Thread(new ServerH3(socketH1, socketH2,socketH3,socketH4));
        threadH3.start();
        System.out.println("等待4对方连接");
        socketH4 = serverSocket.accept();
        Thread threadH4 = new Thread(new ServerH4(socketH1, socketH2,socketH3,socketH4));
        threadH4.start();
        System.out.println("成功连接四个");

    }
}
