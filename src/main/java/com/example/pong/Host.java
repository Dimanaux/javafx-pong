package com.example.pong;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class Host extends Computer implements Observable, Observer {
    private final ServerSocket serverSocket;

    Host() {
        try {
            serverSocket = new ServerSocket(DEFAULT_PORT);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot start on port " + DEFAULT_PORT);
        }
    }

    public void connect() {
        while (true) {
            try {
                super.socket = serverSocket.accept();
                super.writer = new PrintWriter(socket.getOutputStream());
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        connect();
        super.listen(socket);
    }
}
