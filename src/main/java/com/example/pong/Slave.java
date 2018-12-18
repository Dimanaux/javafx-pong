package com.example.pong;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Slave extends Computer implements Observable, Observer {
    Slave(String hostName) {
        try {
            super.socket = new Socket(hostName, DEFAULT_PORT);
            super.writer = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot connect to " + hostName);
        }
    }

    @Override
    public void run() {
        super.listen(socket);
    }
}
