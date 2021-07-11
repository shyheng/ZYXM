package chat.Client;

import chat.ClientRW.ClientR;
import chat.ClientRW.ClientW;

import java.io.IOException;
import java.net.Socket;

public class ClientH2 {
    public static void main(String[] args){
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1",5555);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread threadShyR = new Thread(new ClientR(socket));
        Thread threadShyW = new Thread(new ClientW(socket));
        threadShyR.start();
        threadShyW.start();

    }
}
