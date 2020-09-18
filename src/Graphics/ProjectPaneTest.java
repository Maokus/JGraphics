package Graphics;

import Model.Draggable;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.xml.transform.Source;

public class ProjectPaneTest extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        final Text source = new Text(50, 100, "DRAG ME");
        final ProjectPane projectPane = new ProjectPane();
        projectPane.getChildren().add(source);
        Scene scene = new Scene(projectPane,300,300);
        stage.setScene(scene);
        stage.show();
        Draggable.Nature nature = new Draggable.Nature(source);
    }

    public static void main(String[] args){
        launch(args);
    }
}
