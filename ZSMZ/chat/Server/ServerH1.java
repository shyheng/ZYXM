package chat.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerH1 implements Runnable{

    Socket socketH1;
    Socket socketH2;
    Socket socketH3;
    Socket socketH4;
    public ServerH1(Socket socketH1,
                    Socket ...sockets) {
        this.socketH1 = socketH1;
        this.socketH2 = sockets[0];
        this.socketH3 = sockets[1];
        this.socketH4 = sockets[2];
    }
    @Override
    public void run() {
        InputStream inputStream = null;
        try {
            inputStream = socketH1.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[1024];
        int len;
        while (true){
            try {
                len = inputStream.read(bytes);
                String h1 = new String(bytes,0,len);
                String Nh1 = "h1 "+h1;

                if (socketH2 != null) {
                    OutputStream outputStreamH2 = socketH2.getOutputStream();
                    outputStreamH2.write(Nh1.getBytes());
                }


                if (socketH3 != null) {
                    OutputStream outputStreamH3 = socketH3.getOutputStream();
                    outputStreamH3.write(Nh1.getBytes());
                }


                if (socketH4 != null) {
                    OutputStream outputStreamH4 = socketH4.getOutputStream();
                    outputStreamH4.write(Nh1.getBytes());
                }

            } catch (IOException e) {
                return;
            }

        }
    }
}
