package View;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

import Graphics.LatoButton;

public class AboutScreen {

    private static Stage aboutStage = new Stage();
    private static ComboBox<String> b = new ComboBox<String>();
    private static StackPane root;
    private static LatoButton label;
    private static String language;

    private static void populate(){
        Locale l;
        if(b.getSelectionModel().getSelectedItem()==null){
            language = "en";
        }else {
            language = b.getSelectionModel().getSelectedItem();
        }
        l = new Locale(language,"US");
        ResourceBundle rb = ResourceBundle.getBundle("AboutScreen",l);

        root = new StackPane();
        aboutStage.setScene(new Scene(root,500,500));
        label = new LatoButton(rb.getString("builtby"),20);
        root.getChildren().add(label);
        root.setStyle("-fx-background-color: white");
        root.getChildren().add(b);
    }

    public static void start() throws Exception {
        b.getItems().add("en");
        b.getItems().add("zh");

        populate();

        b.setTranslateY(root.getHeight()/2-30);

        aboutStage.show();

        Application application = new Application() {
            @Override
            public void start(Stage stage) throws Exception {
                getHostServices().showDocument("https://github.com/Maokus/JGraphics");
            }
        };

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
        b.setOnAction(e->{
            populate();
        });
    }
}
