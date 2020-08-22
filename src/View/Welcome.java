package View;

import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.nio.file.Paths;

import static javafx.beans.binding.Bindings.add;
import static javafx.beans.binding.Bindings.divide;

public class Welcome {
    public static void start(Stage primaryStage) throws Exception{
        //Root pane
        Pane root = new Pane();
        root.setStyle("-fx-background-color: white");
        //Welcome images
        ImageView welcome = new ImageView(new Image(Paths.get("src/Assets/Welcome.jpg").toUri().toString()));
        ImageView welcome1= new ImageView(new Image(Paths.get("src/Assets/Welcome1.jpg").toUri().toString()));
        welcome.setScaleY(0.75);
        welcome.setScaleX(0.75);
        welcome1.setScaleY(0.75);
        welcome1.setScaleX(0.75);

        //put Welcome images in the same pane
        StackPane welcomeHolder = new StackPane();
        welcomeHolder.setPrefSize(root.getWidth(),root.getHeight());
        welcomeHolder.getChildren().add(welcome);
        welcomeHolder.getChildren().add(welcome1);
        welcomeHolder.setAlignment(welcome, Pos.TOP_CENTER);
        welcomeHolder.setAlignment(welcome1, Pos.TOP_CENTER);
        welcomeHolder.minHeightProperty().bind(root.heightProperty());
        welcomeHolder.minWidthProperty().bind(root.widthProperty());
        root.getChildren().add(welcomeHolder);
        welcomeHolder.setOpacity(0);

        //buttons
        Button newProjButton = new Button("New Project");
        Button aboutButton = new Button("About");
        aboutButton.translateYProperty().bind(add(30,newProjButton.translateYProperty()));
        newProjButton.setStyle("-fx-background-color: white; -fx-font-family: Lato; -fx-font-size: 20;");
        aboutButton.setStyle("-fx-background-color: white;-fx-font-family: Lato; -fx-font-size: 20;");
        welcomeHolder.getChildren().add(newProjButton);
        welcomeHolder.getChildren().add(aboutButton);

        //Make welcome pulse
        FadeTransition start = new FadeTransition(Duration.seconds(1),welcomeHolder);
        start.setFromValue(0);
        start.setToValue(1);
        FadeTransition fade = new FadeTransition(Duration.seconds(10),welcome1);
        fade.setFromValue(1);
        fade.setToValue(0);
        FadeTransition fadein = new FadeTransition(Duration.seconds(10),welcome1);
        fadein.setFromValue(0);
        fadein.setToValue(1);
        start.setOnFinished((e)->{fade.play();});
        fadein.setOnFinished((e)->{fade.play();});
        fade.setOnFinished((e)->{fadein.play();});

        newProjButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                newProjButton.setTextFill(new Color(0,0.3,0.6,1));
            }
        });
        newProjButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                newProjButton.setTextFill(Color.BLACK);
            }
        });
        aboutButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                aboutButton.setTextFill(new Color(0,0.3,0.6,1));
            }
        });
        aboutButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                aboutButton.setTextFill(Color.BLACK);
            }
        });
        newProjButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CreateProj.start(primaryStage);
            }
        });
        aboutButton.setOnMouseClicked(e->{
            try {
                AboutScreen.start();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });

        //play scene
        primaryStage.setScene(new Scene(root,primaryStage.getWidth(),primaryStage.getHeight()));
        primaryStage.show();
        //begin animation
        start.play();
    }
}
