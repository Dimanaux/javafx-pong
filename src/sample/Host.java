package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Host extends AbstractObservable implements Observable {
    private final ServerSocket serverSocket;
    private Socket slave;

    private Host(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    void run() {
        connect();
        new Thread(this::listen).start();
    }

    private void listen() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(slave.getInputStream()))) {
            String s = reader.readLine();
            while (s != null) {
                this.next(s);
                s = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
