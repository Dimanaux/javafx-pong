package com.example.pong;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Paddle implements Observer {
    private static final int STEP = 10;
    public static final int DEFAULT_HEIGHT = 100;
    public static final int DEFAULT_WIDTH = 30;

    private Rectangle rectangle;

    public Paddle() {
        rectangle = new Rectangle(
                DEFAULT_WIDTH,
                DEFAULT_HEIGHT,
                Color.WHITE
        );
    }

    public Paddle(double x, double y) {
        this();
        rectangle.setX(x);
        rectangle.setY(y);
    }

    public Node asNode() {
        return rectangle;
    }

    private void moveVertically(int step) {
        rectangle.setY(rectangle.getY() + step);
    }

    public void moveUp() {
        moveVertically(-STEP);
    }

    public void moveDown() {
        moveVertically(STEP);
    }

    @Override
    public void onNext(String message) {
        KeyCode code = KeyCode.valueOf(message);
        switch (code) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
        }
    }
}
