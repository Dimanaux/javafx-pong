package com.example.pong;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Ball {
    public static final int LENGTH = 20;

    private Rectangle ball;

    public Ball() {
        ball = new Rectangle(
                LENGTH,
                LENGTH,
                Color.WHITE
        );
    }

    public Ball(double x, double y) {
        this();
        ball.setX(x);
        ball.setY(y);
    }

    public Node asNode() {
        return ball;
    }
}
