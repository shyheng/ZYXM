package chat.Client;

import chat.ClientRW.ClientR;
import chat.ClientRW.ClientW;

import java.io.IOException;
import java.net.Socket;

public class ClientH4 {
    public static void main(String[] args){
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1",5555);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread threadR = new Thread(new ClientR(socket));
        Thread threadW = new Thread(new ClientW(socket));
        threadR.start();
        threadW.start();

    }
}
