package classify;

import java.io.DataInputStream;
import java.net.Socket;

public class ReceiceTalk extends Thread {
    Socket socket;

    public ReceiceTalk(Socket sock) {
        this.socket = sock;
    }

    /**
     * 专门用于接收消息
     */
    @Override
    public void run() {

        DataInputStream in;
        while (true) {
            try {
                in = new DataInputStream(socket.getInputStream());
                String str = in.readUTF();
            } catch (Exception e) {
            }
        }
    }
}
