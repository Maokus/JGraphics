package View;

import javafx.application.Application;
import javafx.stage.Stage;

public class RunFrom extends Application {
    public void start(Stage primaryStage) throws Exception {
        CreateProj.start(primaryStage);
    }
    public static void main(String[] args){
        launch(args);
    }
}
