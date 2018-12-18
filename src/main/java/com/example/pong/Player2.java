package com.example.pong;

public class Player2 extends Main {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        connection = new Slave(args.get(0));

        connection.addObserver(p2);
        userInput.addObserver(connection);
        userInput.addObserver(p1);
    }
}
