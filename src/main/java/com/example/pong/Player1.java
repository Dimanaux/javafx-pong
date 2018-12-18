package com.example.pong;

public class Player1 extends Main {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        connection = new Host();

        connection.addObserver(p2);
        userInput.addObserver(connection);
        userInput.addObserver(p1);
    }
}
