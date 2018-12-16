package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public abstract class Computer implements Runnable, Observable, Observer {
    static final int DEFAULT_PORT = 6789;

    private final List<Observer> observers = new LinkedList<>();

    @Override
    public final List<Observer> getObservers() {
        return observers;
    }

    @Override
    public void onNext(String message) {
        if (writer != null) {
            writer.println(message);
            writer.flush();
        }
    }

    Socket socket;
    PrintWriter writer;

    private void listen(Socket socket) {
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

    @Override
    public void run() {
        listen(socket);
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
