package com.example.pong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class Computer extends AbstractObservable implements Runnable, Observable, Observer {
    static final int DEFAULT_PORT = 6789;

    @Override
    public void onNext(String message) {
        if (writer != null) {
            writer.println(message);
            writer.flush();
        }
    }

    Socket socket;
    PrintWriter writer;

    public final void listen(Socket socket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String s = reader.readLine();
            while (s != null) {
                this.next(s);
                s = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        writer.close();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
