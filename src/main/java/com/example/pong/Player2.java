package com.example.pong;

import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

public class Player2 extends Main {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        super.start(primaryStage);
        connection = new Slave(args.size() > 0 ? args.get(0) : "localhost");

        connection.addObserver(p1);
        userInput.addObserver(connection);
        userInput.addObserver(p2);

        new Thread(connection).start();
        connection.addObserver(ballController);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                ballController.move();
            }
        };
        animationTimer.start();
    }
}
