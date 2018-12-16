package com.example.pong;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class Main extends Application {
    private final static int S_WIDTH = 800;
    private final static int S_HEIGHT = 600;

    private AnchorPane root;
    private Scene scene;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        initialize();
        primaryStage.show();
    }

    private void initialize() {
        prepareWindow();
        stage.setScene(scene);
        root.getChildren().add(createStripe());

        final int PADDLE_CENTER = (S_HEIGHT - Paddle.DEFAULT_HEIGHT) / 2;

        final Paddle p1 = new Paddle(20, PADDLE_CENTER);
        root.getChildren().add(p1.asNode());

        final Paddle p2 = new Paddle(S_WIDTH - 20 - Paddle.DEFAULT_WIDTH, PADDLE_CENTER);
        root.getChildren().add(p2.asNode());

        final Ball b = createBall();
        root.getChildren().add(b.asNode());

        List<String> args = getParameters().getRaw();

        Computer connection;
        if (args.size() > 0) {
            connection = new Slave(args.get(0));
        } else {
            connection = new Host();
        }

        connection.addObserver(p2);

        Observable userInput = new Observable() {
            List<Observer> observers = new LinkedList<>();
            @Override
            public List<Observer> getObservers() {
                return observers;
            }
        };
        scene.setOnKeyPressed(e -> userInput.next(e.getCode().toString()));
        userInput.addObserver(connection);
        userInput.addObserver(p1);
    }

    private static Ball createBall() {
        final int BALL_CENTER_V = (S_HEIGHT - Ball.LENGTH) / 2;
        final int BALL_CENTER_H = (S_WIDTH - Ball.LENGTH) / 2;
        return new Ball(BALL_CENTER_H, BALL_CENTER_V);
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
        final int STRIPE_WIDTH = 10;

        final Color STRIPE_COLOR = Color.rgb(255, 255, 255, 0.5);

        Rectangle stripe = new Rectangle(STRIPE_WIDTH, S_HEIGHT, STRIPE_COLOR);
        final int STRIPE_POSITION = (S_WIDTH - STRIPE_WIDTH) / 2;
        stripe.setX(STRIPE_POSITION);
        stripe.setY(0);
        return stripe;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
