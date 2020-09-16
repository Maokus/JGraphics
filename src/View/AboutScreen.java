package View;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class AboutScreen {
    public static void start() throws Exception {
        //TODO: Apply internationalisation
        Stage aboutStage = new Stage();
        StackPane root = new StackPane();
        aboutStage.setScene(new Scene(root,500,500));
        Button label = new Button("Built by Markus Ang");
        root.getChildren().add(label);
        label.setStyle("-fx-font-family: Lato; -fx-font-size: 20; -fx-background-color: transparent");
        aboutStage.show();
        Application application = new Application() {
            @Override
            public void start(Stage stage) throws Exception {
                getHostServices().showDocument("https://github.com/Maokus/JGraphics");
            }
        };

        label.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                label.setTextFill(new javafx.scene.paint.Color(0,0.3,0.6,1));
            }
        });
        label.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                label.setTextFill(Color.BLACK);
            }
        });
        label.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                label.setTextFill(Color.BLACK);
                try {
                    application.start(aboutStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
