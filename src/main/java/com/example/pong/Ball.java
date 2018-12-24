package com.example.pong;

import javafx.scene.Node;
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
//        ball.setLayoutX(x);
//        ball.setLayoutY(y);
        ball.setCenterX(x);
        ball.setCenterY(y);
    }

    public Node asNode() {
        return ball;
    }

    public void move(double deltaX, double deltaY) {
        this.ball.setLayoutX(ball.getLayoutX() + deltaX);
        this.ball.setLayoutY(ball.getLayoutY() + deltaY);
    }

}
