package com.example.pong;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
    public static final double RADIUS = 10;

    private Circle ball;

    public Ball() {
        ball = new Circle(
                RADIUS,
                Color.WHITE
        );
    }

    public Ball(double x, double y) {
        this();
        ball.setCenterX(x);
        ball.setCenterY(y);
    }

    public Circle asNode() {
        return ball;
    }

    public void move(double deltaX, double deltaY) {
        this.ball.setCenterX(ball.getCenterX() + deltaX);
        this.ball.setCenterY(ball.getCenterY() + deltaY);
    }

}
