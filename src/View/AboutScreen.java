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

    //Iniialize
    private static Stage aboutStage = new Stage();
    private static ComboBox<String> b = new ComboBox<String>();
    private static StackPane root;
    private static LatoButton label;
    private static String language;

    private static void populate(){
        //i18n
        Locale l;
        if(b.getSelectionModel().getSelectedItem()==null){
            language = "en";
        }else {
            language = b.getSelectionModel().getSelectedItem();
        }
        l = new Locale(language,"US");
        ResourceBundle rb = ResourceBundle.getBundle("Assets/AboutScreen",l);

        //Reinit the whole pane
        root = new StackPane();
        aboutStage.setScene(new Scene(root,500,500));
        label = new LatoButton(rb.getString("builtby"),20);
        root.getChildren().add(label);
        root.setStyle("-fx-background-color: white");
        root.getChildren().add(b);


        Application application = new Application() {
            @Override
            public void start(Stage stage) throws Exception {
                getHostServices().showDocument("https://github.com/Maokus/JGraphics");
            }
        };

        //OnClick event handler
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

    public static void start() throws Exception {
        b.getItems().clear();
        b.getItems().add("en");
        b.getItems().add("zh");

        populate();

        b.setTranslateY(root.getHeight()/2-30);

        aboutStage.show();

        //Send the user to a link in their browser
        Application application = new Application() {
            @Override
            public void start(Stage stage) throws Exception {
                getHostServices().showDocument("https://github.com/Maokus/JGraphics");
            }
        };

        //OnClick event handler
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

        //Repopulate the screen when the language is changed
        b.setOnAction(e->{
            populate();
        });
    }
}
