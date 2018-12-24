import com.example.pong.BallController;
import com.example.pong.Paddle;
import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BallControllerTest {
    static Bounds bounds;
    static BallController ballController;
    static Paddle[] paddles;
    static Circle ball;

    @BeforeAll
    static void setUpObstacles() {
        bounds = new AnchorPane().getLayoutBounds();
        paddles = new Paddle[]{
                new Paddle(bounds, bounds.getMinX() + 100),
                new Paddle(bounds, bounds.getMaxX() - 100)
        };

        ballController = new BallController(bounds, paddles[0], paddles[1]);
        ball = ballController.getBall().asNode();
    }

    @Test
    void ballShouldBeInCenterOnStart() {
        double centerX = (bounds.getMaxX() - bounds.getMinX()) / 2;
        double centerY = (bounds.getMaxY() - bounds.getMinY()) / 2;
        assertTrue(ball.contains(centerX, centerY), "ball is not in center on start");
    }

    @Test
    void ballShouldStayInBounds() {
        for (int i = (int) bounds.getMinY() - 20; i < bounds.getMaxY(); i++) {
            ballController.move();
            assertTrue(
                    bounds.contains(ball.getCenterX(), ball.getCenterY()),
                    "ball is out of bounds " + ball.getCenterX() + " " + ball.getCenterY()
            );
        }
    }
}
