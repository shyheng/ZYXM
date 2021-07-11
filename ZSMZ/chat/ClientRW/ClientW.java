package chat.ClientRW;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientW implements Runnable{
    Socket socket;
    public ClientW(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {

            OutputStream os;
            try {
                os = socket.getOutputStream();
                String s = scanner.next();
                os.write(s.getBytes());
            } catch (IOException e) {
                return;
            }


        }
    }
}
