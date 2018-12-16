package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractSocketListener implements Observable {
    List<Observer> observers = new LinkedList<>();

    @Override
    public List<Observer> getObservers() {
        return observers;
    }

    public void listen(Socket socket) {
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
}
