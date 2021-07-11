package chat.ClientRW;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientR implements Runnable{
    Socket socket;
    public ClientR(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] bytes = new byte[1024];
        int len = 0;
        while (true){
            try {
                len = inputStream.read(bytes);
            } catch (IOException e) {
                return;
            }
            Date date = new Date();
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
            String time = sdf.format(date);
            System.out.println("____________________");
            System.out.println(time);
            System.out.println("对方说："+new String(bytes,0,len));
            System.out.println("————————————————————");
        }

    }
}
