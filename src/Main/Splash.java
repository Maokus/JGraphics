package Main;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.nio.file.Paths;

public class Splash extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Width and height
        final int WIDTH = 700;
        final int HEIGHT = 500;
        //Root pane
        Pane root = new Pane();
        root.setStyle("-fx-background-color: white");
        //Start screen display
        Media media = new Media(Paths.get("src/Assets/graphics.mp4").toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setPreserveRatio(true);
        mediaView.fitWidthProperty().bind(root.widthProperty());
        //VBOX to hold stuff
        VBox vb = new VBox();
        vb.getChildren().add(mediaView);
        vb.getChildren().add(new Label("By Markus"));
        vb.setAlignment(Pos.CENTER);

        root.getChildren().add(vb);
        //Setting stage things
        primaryStage.setTitle("JGraphics");
        primaryStage.getIcons().add(new Image(Paths.get("src/Assets/JG.jpg").toUri().toString()));
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));

        //transition initialisation
        FadeTransition wait = new FadeTransition(Duration.seconds(10),vb);
        wait.setFromValue(1);
        wait.setToValue(1);
        wait.setCycleCount(1);

        FadeTransition fadeout = new FadeTransition(Duration.seconds(2),vb);
        fadeout.setFromValue(1);
        fadeout.setToValue(0);
        fadeout.setCycleCount(1);

        //Show stage
        primaryStage.show();

        //play animations, then close stage
        wait.play();
        wait.setOnFinished((e) -> {fadeout.play();});

        fadeout.setOnFinished((e) -> {
            try {
                Welcome.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
