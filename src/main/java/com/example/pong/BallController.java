package com.example.pong;

import javafx.geometry.Bounds;

import static com.example.pong.DirectionHorizontal.LEFT;
import static com.example.pong.DirectionHorizontal.RIGHT;
import static com.example.pong.DirectionVertical.DOWN;
import static com.example.pong.DirectionVertical.UP;

public class BallController {
    private static final double BALL_SPEED = 2;

    private final Ball ball;
    private final Paddle p1;
    private final Paddle p2;
    private final Bounds bounds;
    private DirectionHorizontal directionX;
    private DirectionVertical directionY;

    public BallController(Bounds bounds, Paddle p1, Paddle p2) {
        this.bounds = bounds;

        double CENTER_X = (bounds.getMaxX() - bounds.getMinX()) / 2.0;
        double CENTER_Y = (bounds.getMaxY() - bounds.getMinY()) / 2.0;

        this.ball = new Ball(CENTER_X, CENTER_Y);
        this.p1 = p1;
        this.p2 = p2;
        this.directionX = LEFT;
        this.directionY = DOWN;
    }

    public Ball getBall() {
        return ball;
    }

    public void move() {
        double deltaX = directionX == LEFT ? -BALL_SPEED : BALL_SPEED;
        double deltaY = directionY == DOWN ? -BALL_SPEED : BALL_SPEED;
        ball.move(deltaX, deltaY);

        if (compare(ball.asNode().getLayoutY(), bounds.getMaxY()) >= 0) {
            // floor collision
            if (directionY == DOWN) {
                directionY = directionY.opposite();
            }
        } else if (compare(ball.asNode().getLayoutY(), bounds.getMinY()) <= 0) {
            // ceil collision
            if (directionY == UP) {
                directionY = directionY.opposite();
            }
        }

        if (p1.asNode().intersects(ball.asNode().layoutBoundsProperty().get())) {
            // left paddle collision
            if (directionX == LEFT) {
                directionX = directionX.opposite();
            }
        } else if (p2.asNode().intersects(ball.asNode().getBoundsInLocal())) {
            // right paddle collision
            if (directionX == RIGHT) {
                directionX = directionX.opposite();
            }
        }
    }

    private static int compare(double a, double b) {
        double PRECISION = 15;
        if (Math.abs(a - b) < PRECISION) {
            return 0;
        } else if (a > b) {
            return 1;
        } else {
            // (a < b)
            return -1;
        }
    }
}
