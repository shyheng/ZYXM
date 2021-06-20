package com.shy.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientW implements Runnable{
    Socket socket;
    String text;
    public ClientW(Socket socket,String text) {
        this.socket = socket;
        this.text = text;
    }

    @Override
    public void run() {
        while (true)
        {
            OutputStream os;
            try {
                os = socket.getOutputStream();
                os.write(text.getBytes());
                text = "";
            } catch (IOException e) {
                System.out.println();
            }


        }
    }
}
