package com.example.pong;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.List;

public abstract class Main extends Application {
    public final static int S_WIDTH = 800;
    public final static int S_HEIGHT = 600;
    public final static int PADDLE_CENTER = (S_HEIGHT - Paddle.DEFAULT_HEIGHT) / 2;
    public final static int BALL_CENTER_V = (S_HEIGHT - Ball.LENGTH) / 2;
    public final static int BALL_CENTER_H = (S_WIDTH - Ball.LENGTH) / 2;
    public final static int STRIPE_WIDTH = 10;
    public final static Color STRIPE_COLOR = Color.rgb(255, 255, 255, 0.5);
    public final static int STRIPE_POSITION = (S_WIDTH - STRIPE_WIDTH) / 2;

    protected AnchorPane root;
    protected Scene scene;
    protected Stage stage;

    protected Ball ball;
    protected Paddle p1;
    protected Paddle p2;

    protected Computer connection;

    protected final List<String> args = getParameters().getRaw();

    protected SceneObservable userInput;


    public abstract void init();

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        prepareWindow();
        initViews();

        root.getChildren().add(p1.asNode());
        root.getChildren().add(p2.asNode());
        root.getChildren().add(ball.asNode());

        primaryStage.show();

        init();

        connection.run();
    }

    private void initViews() {
        stage.setScene(scene);
        root.getChildren().add(createStripe());

        p1 = new Paddle(20, PADDLE_CENTER);
        p2 = new Paddle(S_WIDTH - 20 - Paddle.DEFAULT_WIDTH, PADDLE_CENTER);
        ball = new Ball(BALL_CENTER_H, BALL_CENTER_V);
    }

    private void prepareWindow() {
        root = new AnchorPane();
        scene = new Scene(root, S_WIDTH, S_HEIGHT, Color.BLACK);
        stage.setResizable(false);
        stage.setTitle("Pong!");
        stage.setHeight(S_HEIGHT);
        stage.setWidth(S_WIDTH);
    }

    private static Rectangle createStripe() {
        Rectangle stripe = new Rectangle(STRIPE_WIDTH, S_HEIGHT, STRIPE_COLOR);
        stripe.setX(STRIPE_POSITION);
        stripe.setY(0);
        return stripe;
    }
}
