package classify;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 会话
 */
public class SendTalk extends Thread {
    Socket socket;

    public SendTalk(Socket sock) {
        this.socket = sock;
    }

    /**
     * 专门用于发送信息
     */
    @Override
    public void run() {
        DataOutputStream out;
        while (true) {
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    System.in));
            try {
                String str;
                str = input.readLine();
                out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF(str);
            } catch (Exception e) {
            }
        }
    }
}
