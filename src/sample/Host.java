package sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Host extends AbstractSocketListener implements Observable {
    private static final int DEFAULT_PORT = 6789;

    private final ServerSocket serverSocket;
    private Socket slave;

    private Host() {
        try {
            serverSocket = new ServerSocket(DEFAULT_PORT);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot create socket on port " + DEFAULT_PORT);
        }
    }

    void run() {
        connect();
        new Thread(() -> super.listen(slave)).start();
    }

    private void connect() {
        while (true) {
            try {
                slave = serverSocket.accept();
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
