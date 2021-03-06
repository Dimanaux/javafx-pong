package com.example.pong;

import javafx.geometry.Bounds;

import static com.example.pong.DirectionHorizontal.LEFT;
import static com.example.pong.DirectionHorizontal.RIGHT;
import static com.example.pong.DirectionVertical.DOWN;
import static com.example.pong.DirectionVertical.UP;

public class BallController extends AbstractObservable implements Observable, Observer {
    private static final double BALL_SPEED = 2;
    private final double CENTER_X;
    private final double CENTER_Y;

    private final Ball ball;
    private final Paddle p1;
    private final Paddle p2;
    private final Bounds bounds;
    private DirectionHorizontal directionX;
    private DirectionVertical directionY;

    public BallController(Bounds bounds, Paddle p1, Paddle p2) {
        this.bounds = bounds;
        this.p1 = p1;
        this.p2 = p2;

        CENTER_X = (bounds.getMaxX() - bounds.getMinX()) / 2.0;
        CENTER_Y = (bounds.getMaxY() - bounds.getMinY()) / 2.0;

        this.ball = new Ball();

        reset();
    }

    private void reset() {
        this.directionX = LEFT;
        this.directionY = UP;

        ball.setPosition(CENTER_X, CENTER_Y);
    }

    public Ball getBall() {
        return ball;
    }

    public void move() {
        double deltaX = directionX == LEFT ? -BALL_SPEED : BALL_SPEED;
        double deltaY = directionY == UP ? -BALL_SPEED : BALL_SPEED;
        ball.move(deltaX, deltaY);

        if (ball.asNode().getCenterY() <= Ball.RADIUS) {
            // ceil
            if (directionY == UP) {
                directionY = directionY.opposite();
            }
        } else if (bounds.getMaxY() - ball.asNode().getCenterY() <= 4 * Ball.RADIUS) {
            // floor
            if (directionY == DOWN) {
                directionY = directionY.opposite();
            }
        }

        if (p1.asNode().intersects(ball.asNode().layoutBoundsProperty().get())) {
            // left paddle
            if (directionX == LEFT) {
                directionX = directionX.opposite();
            }
        } else if (p2.asNode().intersects(ball.asNode().getBoundsInLocal())) {
            // right paddle
            if (directionX == RIGHT) {
                directionX = directionX.opposite();
            }
        }

        if (!bounds.contains(ball.asNode().getCenterX(), ball.asNode().getCenterY())) {
            next("BALL:RESET");
            reset();
        }
    }

    @Override
    public void onNext(String message) {
        if ("BALL:RESET".equals(message)) {
            reset();
        }
    }
}
