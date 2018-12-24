package com.example.pong;

import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

public class Player1 extends Main {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        super.start(primaryStage);
        connection = new Host();

        connection.addObserver(p2);
        userInput.addObserver(connection);
        userInput.addObserver(p1);

        new Thread(connection).start();


        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                ballController.move();
            }
        };
        animationTimer.start();
    }

}
