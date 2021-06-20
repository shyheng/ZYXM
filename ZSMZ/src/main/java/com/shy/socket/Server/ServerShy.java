package com.shy.socket.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerShy implements Runnable{

    Socket socketShy;
    Socket socketZph;
    public ServerShy(Socket socketShy,Socket socketZph) {
        this.socketZph = socketZph;
        this.socketShy = socketShy;
    }
    @Override
    public void run() {
        InputStream inputStream = null;
        try {
            inputStream = socketShy.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[1024];
        int len;
        int i = 0;
        while (true){
            try {
                if (i == 0){
                    OutputStream outputStream1 = socketZph.getOutputStream();
                    outputStream1.write("连接成功".getBytes());
                    i++;
                }
                len = inputStream.read(bytes);
                String c1 = null;
                try {
                    c1 = new String(bytes,0,len);
                }catch (StringIndexOutOfBoundsException s){
                    socketShy.close();
                    socketZph.close();

                }
                OutputStream outputStream1 = socketZph.getOutputStream();
                outputStream1.write(c1.getBytes());
            } catch (IOException e) {
                try {
                    socketShy.close();
                    socketZph.close();
                    ServerMain.serverSocket.close();
                    String [] s1 = new String[1];
                    ServerMain.main(s1);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }

        }
    }
}
