package com.shy.socket.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerZph implements Runnable {

    Socket socketShy;
    Socket socketZph;
    public ServerZph(Socket socketShy,Socket socketZph) {
        this.socketZph = socketZph;
        this.socketShy = socketShy;
    }

    @Override
    public void run() {

        InputStream inputStream1 = null;
        try {
            inputStream1 = socketZph.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes1 = new byte[1024];

        int len1;
        int i = 0;
        while (true){

            try {
                if (i == 0){
                    OutputStream outputStream1 = socketShy.getOutputStream();
                    outputStream1.write("连接成功".getBytes());
                    i++;
                }
                len1 = inputStream1.read(bytes1);
                String c2 = null;
                try {
                    c2 = new String(bytes1,0,len1);
                }catch (StringIndexOutOfBoundsException s){
                    socketShy.close();
                    socketZph.close();
                }
                OutputStream outputStream = socketShy.getOutputStream();
                outputStream.write(c2.getBytes());
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
