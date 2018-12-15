package sample;

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

    public Rectangle asView() {
        return rectangle;
    }

    private void moveVertically(int step) {
        rectangle.setY(rectangle.getY() + STEP);
    }

    public void moveUp() {
        moveVertically(-STEP);
    }

    public void moveDown() {
        moveVertically(STEP);
    }

    @Override
    public void onNext(String message) {
        switch (message) {
            case "UP":
                moveUp();
                break;
            case "DOWN":
                moveDown();
                break;
            default:
                break;
        }
    }
}
