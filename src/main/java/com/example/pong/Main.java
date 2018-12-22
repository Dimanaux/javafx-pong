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

    protected List<String> args;

    protected SceneObservable userInput;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        prepareWindow();
        initViews();

        args = getParameters().getRaw();

        root.getChildren().add(p1.asNode());
        root.getChildren().add(p2.asNode());
        root.getChildren().add(ball.asNode());

        primaryStage.show();

        userInput = new SceneObservable(scene);

//        init();

//        connection.run();
    }

    private void initViews() {
        this.stage.setScene(scene);
        this.root.getChildren().add(createStripe());

        this.p1 = new Paddle(20, PADDLE_CENTER);
        this.p2 = new Paddle(S_WIDTH - 20 - Paddle.DEFAULT_WIDTH, PADDLE_CENTER);
        this.ball = new Ball(BALL_CENTER_H, BALL_CENTER_V);
    }

    private void prepareWindow() {
        this.root = new AnchorPane();
        this.scene = new Scene(root, S_WIDTH, S_HEIGHT, Color.BLACK);
        this.stage.setResizable(false);
        this.stage.setTitle("Pong!");
        this.stage.setHeight(S_HEIGHT);
        this.stage.setWidth(S_WIDTH);
    }

    private static Rectangle createStripe() {
        Rectangle stripe = new Rectangle(STRIPE_WIDTH, S_HEIGHT, STRIPE_COLOR);
        stripe.setX(STRIPE_POSITION);
        stripe.setY(0);
        return stripe;
    }
}
