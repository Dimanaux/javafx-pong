package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private final static int S_WIDTH = 800;
    private final static int S_HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Pong!");
        Scene scene = new Scene(root, S_WIDTH, S_HEIGHT, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        final int S_CENTER = S_HEIGHT / 2 - Paddle.DEFAULT_HEIGHT / 2;

        Paddle p = new Paddle(20, S_CENTER);
        root.getChildren().add(p.asView());

        Paddle p2 = new Paddle(S_WIDTH - 20 - Paddle.DEFAULT_WIDTH, S_CENTER);
        root.getChildren().add(p2.asView());

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
