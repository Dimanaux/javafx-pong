package com.example.pong;

import javafx.stage.Stage;

public class Player2 extends Main {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        super.start(primaryStage);
        connection = new Slave("localhost");

        connection.addObserver(p2);
        userInput.addObserver(connection);
        userInput.addObserver(p1);

        new Thread(connection).start();
    }
}
